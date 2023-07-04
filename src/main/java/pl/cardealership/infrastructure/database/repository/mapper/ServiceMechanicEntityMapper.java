package pl.cardealership.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.cardealership.domain.ServiceMechanic;
import pl.cardealership.infrastructure.database.entity.ServiceMechanicEntity;
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceMechanicEntityMapper {
    ServiceMechanicEntity mapToEntity(ServiceMechanic serviceMechanic);
}
