package pl.cardealership.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.cardealership.domain.ServicePart;
import pl.cardealership.infrastructure.database.entity.ServicePartEntity;
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServicePartEntityMapper {
    ServicePartEntity mapToEntity(ServicePart servicePart);

}
