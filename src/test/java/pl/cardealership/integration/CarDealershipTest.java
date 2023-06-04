package pl.cardealership.integration;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import pl.cardealership.business.CarPurchaseService;
import pl.cardealership.business.CarService;
import pl.cardealership.business.CustomerService;
import pl.cardealership.business.SalesmanService;
import pl.cardealership.business.management.CarDealershipManagementService;
import pl.cardealership.business.management.FileDataPreparationService;
import pl.cardealership.infrastructure.configuration.HibernateUtil;
import pl.cardealership.infrastructure.database.repository.CarDealershipManagementRepository;
import pl.cardealership.infrastructure.database.repository.CarRepository;
import pl.cardealership.infrastructure.database.repository.CustomerRepository;
import pl.cardealership.infrastructure.database.repository.SalesmanRepository;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarDealershipTest {

    private CarDealershipManagementService carDealershipManagementService;
    private CarPurchaseService carPurchaseService;


    @BeforeEach
    void beforeEach() {
        this.carDealershipManagementService = new CarDealershipManagementService(
                new CarDealershipManagementRepository(),
                new FileDataPreparationService()
        );
        this.carPurchaseService = new CarPurchaseService(
                new FileDataPreparationService(),
                new CustomerService(new CustomerRepository()),
                new CarService(new CarRepository()),
                new SalesmanService(new SalesmanRepository())
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
    }

    @Test
    @Order(5)
    void processServiceRequests() {
        log.info("### RUNNING ORDER 5");
    }

    @Test
    @Order(6)
    void printCarHistory() {
        log.info("### RUNNING ORDER 6");
    }


}
