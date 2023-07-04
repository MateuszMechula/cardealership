package pl.cardealership.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cardealership.business.DAO.MechanicDAO;
import pl.cardealership.domain.Mechanic;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MechanicService {

    private final MechanicDAO mechanicDAO;

    @Transactional
    public Mechanic findMechanic(String pesel) {
        Optional<Mechanic> mechanic = mechanicDAO.findByPesel(pesel);
        if (mechanic.isEmpty()) {
            throw new RuntimeException("Could not find mechanic by pesel: [%s]".formatted(pesel));
        }
        return mechanic.get();
    }
}
