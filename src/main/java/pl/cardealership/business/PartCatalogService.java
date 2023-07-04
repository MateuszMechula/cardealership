package pl.cardealership.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cardealership.business.DAO.PartDAO;
import pl.cardealership.domain.Part;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PartCatalogService {

    private final PartDAO partDAO;

    @Transactional
    public Part findPart(String partSerialNumber) {
        Optional<Part> part = partDAO.findByPartCode(partSerialNumber);
        if (part.isEmpty()) {
            throw new RuntimeException("Could not find Part by part serial number: [%s]".formatted(partSerialNumber));
        }
        return part.get();
    }

}
