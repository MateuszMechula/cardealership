package pl.cardealership.business.DAO;

import java.util.List;

public interface CarDealershipManagementDAO {

    void purge();

    void saveAll(List<?> entities);
}
