package pl.cardealership.integration;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import pl.cardealership.business.*;
import pl.cardealership.business.DAO.*;
import pl.cardealership.business.management.*;
import pl.cardealership.infrastructure.database.repository.*;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarDealershipTest {

    private CarDealershipManagementService carDealershipManagementService;
    private CarPurchaseService carPurchaseService;
    private CarServiceRequestService carServiceRequestService;
    private CarServiceProcessingService carServiceProcessingService;
    private CarService carService;


    @BeforeEach
    void beforeEach() {
        CarDAO carDAO = new CarRepository();
        CustomerDAO customerDAO = new CustomerRepository();
        MechanicDAO mechanicDAO = new MechanicRepository();
        ServiceDAO serviceDAO = new ServiceRepository();
        PartDAO partDAO = new PartRepository();
        FileDataPreparationService fileDataPreparationService = new FileDataPreparationService();
        CarService carService = new CarService(carDAO);
        CustomerService customerService = new CustomerService(customerDAO);
        CarServiceRequestDAO carServiceRequestDAO = new CarServiceRequestRepository();
        MechanicService mechanicService = new MechanicService(mechanicDAO);
        ServiceCatalogService serviceCatalogService = new ServiceCatalogService(serviceDAO);
        PartCatalogService partCatalogService = new PartCatalogService(partDAO);
        ServiceRequestProcessingDAO serviceRequestProcessingDAO = new ServiceRequestProcessingRepository();


        this.carDealershipManagementService = new CarDealershipManagementService(
                new CarDealershipManagementRepository(),
                fileDataPreparationService
        );
        this.carPurchaseService = new CarPurchaseService(
                fileDataPreparationService,
                customerService,
                carService,
                new SalesmanService(new SalesmanRepository())
        );

        this.carServiceRequestService = new CarServiceRequestService(
                fileDataPreparationService,
                carService,
                customerService,
                carServiceRequestDAO);

        this.carServiceProcessingService = new CarServiceProcessingService(
                fileDataPreparationService,
                mechanicService,
                carService,
                serviceCatalogService,
                partCatalogService,
                carServiceRequestService,
                serviceRequestProcessingDAO
                );

        this.carService = new CarService(
          carDAO
        );

    }

    @AfterAll
    static void afterAll() {
        HibernateUtil.closeSessionFactory();
    }


    @Test
    @Order(1)
    void purge() {
        log.info("### RUNNING ORDER 1");
        carDealershipManagementService.purge();
    }

    @Test
    @Order(2)
    void init() {
        log.info("### RUNNING ORDER 2");
        carDealershipManagementService.init();
    }

    @Test
    @Order(3)
    void purchase() {
        log.info("### RUNNING ORDER 3");
        carPurchaseService.purchase();
    }

    @Test
    @Order(4)
    void makeServiceRequests() {
        log.info("### RUNNING ORDER 4");
        carServiceRequestService.requestService();
    }

    @Test
    @Order(5)
    void processServiceRequests() {
        log.info("### RUNNING ORDER 5");
        carServiceProcessingService.process();
    }

    @Test
    @Order(6)
    void printCarHistory() {
        log.info("### RUNNING ORDER 6");
        carService.printCarHistory("1GCEC19X27Z109567");
        carService.printCarHistory("2C3CDYAG2DH731952");
    }


}
