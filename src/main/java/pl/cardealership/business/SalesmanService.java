package pl.cardealership.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cardealership.business.DAO.SalesmanDAO;
import pl.cardealership.domain.Salesman;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SalesmanService {

    private final SalesmanDAO salesmanDAO;

    @Transactional
    public Salesman findSalesman(String pesel) {
        final Optional<Salesman> salesmanByPesel = salesmanDAO.findByPesel(pesel);
        if (salesmanByPesel.isEmpty()) {
            throw new RuntimeException("Could not find Salesman by pesel: [%s]".formatted(pesel));
        }
        return salesmanByPesel.get();
    }
}
