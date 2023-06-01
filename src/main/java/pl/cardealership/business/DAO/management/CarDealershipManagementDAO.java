package pl.cardealership.business.DAO.management;

import java.util.List;

public interface CarDealershipManagementDAO {

    void purge();

    void saveAll(List<?> entities);
}
