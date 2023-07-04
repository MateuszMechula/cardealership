package pl.cardealership.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.cardealership.business.DAO.PartDAO;
import pl.cardealership.domain.Part;
import pl.cardealership.infrastructure.database.repository.jpa.PartJpaRepository;
import pl.cardealership.infrastructure.database.repository.mapper.PartEntityMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class PartRepository implements PartDAO {

    private final PartJpaRepository partJpaRepository;
    private final PartEntityMapper partEntityMapper;


    @Override
    public Optional<Part> findByPartCode(String serialNumber) {
        return partJpaRepository.findBySerialNumber(serialNumber)
                .map(partEntityMapper::mapFromEntity);
    }
}
