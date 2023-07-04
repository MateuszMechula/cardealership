package pl.cardealership.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.cardealership.domain.Service;
import pl.cardealership.infrastructure.database.entity.ServiceEntity;
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceEntityMapper {
    @Mapping(target = "serviceMechanics", ignore = true)
    Service mapFromEntity(ServiceEntity serviceEntity);
}
