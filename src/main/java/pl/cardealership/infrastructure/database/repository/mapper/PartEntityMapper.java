package pl.cardealership.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.cardealership.domain.Part;
import pl.cardealership.infrastructure.database.entity.PartEntity;
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PartEntityMapper {
    @Mapping(target = "serviceParts", ignore = true)
    Part mapFromEntity(PartEntity partEntity);
}
