package pl.cardealership.business.management;

import lombok.AllArgsConstructor;
import pl.cardealership.business.CarService;
import pl.cardealership.business.CustomerService;
import pl.cardealership.business.DAO.CarServiceRequestDAO;
import pl.cardealership.domain.CarServiceRequest;
import pl.cardealership.infrastructure.database.entity.CarServiceRequestEntity;
import pl.cardealership.infrastructure.database.entity.CarToBuyEntity;
import pl.cardealership.infrastructure.database.entity.CarToServiceEntity;
import pl.cardealership.infrastructure.database.entity.CustomerEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

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
                        elem -> elem.getCar().shouldExistsInCarToBuy()));

        serviceRequests.get(true).forEach(this::saveServiceRequestsForExistingCar);
        serviceRequests.get(false).forEach(this::saveServiceRequestsForNewCar);
    }

    private void saveServiceRequestsForNewCar(CarServiceRequest request) {
        CarToServiceEntity car = carService.findCarToService(request.getCar().getVin())
                .orElse(findInCarToBuyAndSaveInCarToService(request.getCar()));

        CustomerEntity customer = customerService.findCustomer(request.getCustomer().getEmail());
        CarServiceRequestEntity carServiceRequestEntity = buildCarServiceRequestEntity(request, car, customer);
        customer.addServiceRequest(carServiceRequestEntity);
        customerService.saveServiceRequest(customer);
    }

    private void saveServiceRequestsForExistingCar(CarServiceRequest request) {

        CarToServiceEntity car = carService.saveCarToService(request.getCar());
        CustomerEntity customer = customerService.saveCustomer(request.getCustomer());

        CarServiceRequestEntity carServiceRequestEntity = buildCarServiceRequestEntity(request, car, customer);
        customer.addServiceRequest(carServiceRequestEntity);
        customerService.saveServiceRequest(customer);
    }

    private CarServiceRequestEntity buildCarServiceRequestEntity
            (CarServiceRequest request,
             CarToServiceEntity car,
             CustomerEntity customer) {

        OffsetDateTime when = OffsetDateTime.now();

        return CarServiceRequestEntity.builder()
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

    private CarToServiceEntity findInCarToBuyAndSaveInCarToService(CarServiceRequest.Car car) {
        CarToBuyEntity carToBuy = carService.findCarToBuy(car.getVin());
        return carService.saveCarToService(carToBuy);
    }

    public CarServiceRequestEntity findAnyActiveServiceRequest(String carVin) {
        Set<CarServiceRequestEntity> serviceRequests=  carServiceRequestDAO.findActiveServiceRequestsByCarVin(carVin);
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
