package pl.cardealership.business.DAO;

import pl.cardealership.infrastructure.database.entity.CarServiceRequestEntity;
import pl.cardealership.infrastructure.database.entity.ServiceMechanicEntity;
import pl.cardealership.infrastructure.database.entity.ServicePartEntity;

public interface ServiceRequestProcessingDAO {
    void process(CarServiceRequestEntity serviceRequest,
                 ServiceMechanicEntity serviceMechanicEntity);
    void process(CarServiceRequestEntity serviceRequest,
                 ServiceMechanicEntity serviceMechanicEntity,
                 ServicePartEntity servicePartEntity);
}
