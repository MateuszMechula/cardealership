package pl.cardealership.infrastructure.database.repository.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cardealership.infrastructure.database.entity.ServiceEntity;

@Repository
public interface ServiceJpaRepository extends JpaRepository<ServiceEntity, Integer> {


}
