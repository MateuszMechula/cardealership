package pl.cardealership.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.cardealership.domain.Salesman;
import pl.cardealership.infrastructure.database.entity.SalesmanEntity;
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SalesmanEntityMapper {
    @Mapping(target = "invoices", ignore = true)
    Salesman mapFromEntity(SalesmanEntity salesmanEntity);
}
