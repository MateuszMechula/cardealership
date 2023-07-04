package pl.cardealership.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import pl.cardealership.business.DAO.ServiceDAO;
import pl.cardealership.domain.Service;

import java.util.Optional;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ServiceCatalogService {

    private final ServiceDAO serviceDAO;
    @Transactional
    public Service findService(String serviceCode) {
        final Optional<Service> service = serviceDAO.findByServiceCode(serviceCode);
        if (service.isEmpty()) {
            throw new RuntimeException("Could not find service by service code: [%s]".formatted(service));
        }
        return service.get();
    }
}
