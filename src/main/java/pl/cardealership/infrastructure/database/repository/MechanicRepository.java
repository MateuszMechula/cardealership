package pl.cardealership.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.cardealership.business.DAO.MechanicDAO;
import pl.cardealership.domain.Mechanic;
import pl.cardealership.infrastructure.database.repository.jpa.MechanicJpaRepository;
import pl.cardealership.infrastructure.database.repository.mapper.MechanicEntityMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class MechanicRepository implements MechanicDAO {

    private final MechanicJpaRepository mechanicJpaRepository;
    private final MechanicEntityMapper mechanicEntityMapper;

    @Override
    public Optional<Mechanic> findByPesel(String pesel) {
        return mechanicJpaRepository.findByPesel(pesel)
                .map(mechanicEntityMapper::mapFromEntity);
    }
}
