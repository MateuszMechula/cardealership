package pl.cardealership.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cardealership.infrastructure.database.entity.PartEntity;

@Repository
public interface PartJpaRepository extends JpaRepository<PartEntity, Integer> {

}
