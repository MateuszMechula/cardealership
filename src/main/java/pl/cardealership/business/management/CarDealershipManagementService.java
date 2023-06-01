package pl.cardealership.business.management;

import lombok.AllArgsConstructor;
import pl.cardealership.business.DAO.management.CarDealershipManagementDAO;

import java.util.List;

@AllArgsConstructor
public class CarDealershipManagementService {

    private final CarDealershipManagementDAO carDealershipManagementDAO;
    private final FileDataPreparationService fileDataPreparationService;

    public void purge() {
        carDealershipManagementDAO.purge();
    }
    public void init() {
        List<?> entities = fileDataPreparationService.prepareInitData();
        carDealershipManagementDAO.saveAll(entities);
    }
}
