package pl.cardealership.business.DAO;

import pl.cardealership.domain.CarServiceRequest;
import pl.cardealership.domain.ServiceMechanic;
import pl.cardealership.domain.ServicePart;

public interface ServiceRequestProcessingDAO {
    void process(CarServiceRequest serviceRequest,
                 ServiceMechanic serviceMechanic);
    void process(CarServiceRequest serviceRequest,
                 ServiceMechanic serviceMechanic,
                 ServicePart servicePart);
}
