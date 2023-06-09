package pl.cardealership.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cardealership.business.DAO.CarServiceRequestDAO;
import pl.cardealership.business.management.FileDataPreparationService;
import pl.cardealership.domain.CarServiceRequest;
import pl.cardealership.domain.CarToBuy;
import pl.cardealership.domain.CarToService;
import pl.cardealership.domain.Customer;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarServiceRequestService {
    private final FileDataPreparationService fileDataPreparationService;
    private final CarService carService;
    private final CustomerService customerService;
    private final CarServiceRequestDAO carServiceRequestDAO;
    public void requestService() {
        Map<Boolean, List<CarServiceRequest>> serviceRequests = fileDataPreparationService.createCarServiceRequests()
                .stream()
                .collect(Collectors.groupingBy(
                        elem -> elem.getCar().carBoughtHere()));

        serviceRequests.get(true).forEach(this::saveServiceRequestsForExistingCar);
        serviceRequests.get(false).forEach(this::saveServiceRequestsForNewCar);
    }

    private void saveServiceRequestsForNewCar(CarServiceRequest request) {
        CarToService car = carService.saveCarToService(request.getCar());
        Customer customer = customerService.saveCustomer(request.getCustomer());


        CarServiceRequest carServiceRequest = buildCarServiceRequest(request, car, customer);
        Set<CarServiceRequest> existingCarServiceRequests = customer.getCarServiceRequests();
        existingCarServiceRequests.add(carServiceRequest);
        customerService.saveServiceRequest(customer.withCarServiceRequests(existingCarServiceRequests));
    }

    private void saveServiceRequestsForExistingCar(CarServiceRequest request) {

        CarToService car = carService.findCarToService(request.getCar().getVin())
                .orElse(findInCarToBuyAndSaveInCarToService(request.getCar()));
        Customer customer = customerService.findCustomer(request.getCustomer().getEmail());

        CarServiceRequest carServiceRequest = buildCarServiceRequest(request, car, customer);
        Set<CarServiceRequest> existingCarServiceRequests = customer.getCarServiceRequests();
        existingCarServiceRequests.add(carServiceRequest);
        customerService.saveServiceRequest(customer.withCarServiceRequests(existingCarServiceRequests));
    }

    private CarServiceRequest buildCarServiceRequest
            (CarServiceRequest request,
             CarToService car,
             Customer customer) {

        OffsetDateTime when = OffsetDateTime.now();

        return CarServiceRequest.builder()
                .carServiceRequestNumber(generateCarServiceRequestNumber(when))
                .receivedDateTime(when)
                .customerComment(request.getCustomerComment())
                .customer(customer)
                .car(car)
                .build();
    }

    private String generateCarServiceRequestNumber(OffsetDateTime when) {
        return "%s.%s.%s-%s.%s.%s.%s".formatted(
            when.getYear(),
            when.getMonth().ordinal(),
            when.getDayOfMonth(),
            when.getHour(),
            when.getMinute(),
            when.getSecond(),
            randomInt(10,100)
        );
    }

    @SuppressWarnings("SameParameterValue")
    private int randomInt(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }


    private CarToService findInCarToBuyAndSaveInCarToService(CarToService car) {
        CarToBuy carToBuy = carService.findCarToBuy(car.getVin());
        return carService.saveCarToService(carToBuy);
    }

    @Transactional
    public CarServiceRequest findAnyActiveServiceRequest(String carVin) {
        Set<CarServiceRequest> serviceRequests=  carServiceRequestDAO.findActiveServiceRequestsByCarVin(carVin);
        if (serviceRequests.size() != 1) {
            throw new RuntimeException(
                    "There should be only one actived service reqeust at a time, car vin: [%s]".formatted(carVin));
        }
        return serviceRequests.stream()
                .findAny()
                .orElseThrow(() -> new RuntimeException(
                        "Could not find any serviceRequests, car vin: [%s]".formatted(carVin)));
    }
}
