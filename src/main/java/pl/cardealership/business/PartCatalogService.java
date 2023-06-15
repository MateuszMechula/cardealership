package pl.cardealership.business;

import lombok.AllArgsConstructor;
import pl.cardealership.business.DAO.PartDAO;
import pl.cardealership.infrastructure.database.entity.PartEntity;

import java.util.Optional;

@AllArgsConstructor
public class PartCatalogService {

    private final PartDAO partDAO;

    public PartEntity findPart(String partSerialNumber) {
        Optional<PartEntity> part = partDAO.findByPartCode(partSerialNumber);
        if (part.isEmpty()) {
            throw new RuntimeException("Could not find PartEntity by part serial number: [%s]".formatted(partSerialNumber));
        }
        return part.get();
    }

}
