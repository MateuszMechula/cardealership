package pl.cardealership.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.cardealership.business.DAO.SalesmanDAO;
import pl.cardealership.domain.Salesman;
import pl.cardealership.infrastructure.database.repository.jpa.SalesmanJpaRepository;
import pl.cardealership.infrastructure.database.repository.mapper.SalesmanEntityMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class SalesmanRepository implements SalesmanDAO {

    private final SalesmanJpaRepository salesmanJpaRepository;
    private final SalesmanEntityMapper salesmanEntityMapper;

    @Override
    public Optional<Salesman> findByPesel(String pesel) {
        return salesmanJpaRepository.findByPesel(pesel)
                .map(salesmanEntityMapper::mapFromEntity);
    }
}
