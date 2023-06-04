package pl.cardealership.business;

import lombok.AllArgsConstructor;
import pl.cardealership.business.DAO.SalesmanDAO;
import pl.cardealership.infrastructure.database.entity.SalesmanEntity;

import java.util.Optional;

@AllArgsConstructor
public class SalesmanService {

    private final SalesmanDAO salesmanDAO;
    public SalesmanEntity findSalesman(String pesel) {
        final Optional<SalesmanEntity> salesmanByPesel = salesmanDAO.findByPesel(pesel);
        if (salesmanByPesel.isEmpty()) {
            throw new RuntimeException("Could not find Salesman by pesel: [%s]".formatted(pesel));
        }
        return salesmanByPesel.get();
    }
}
