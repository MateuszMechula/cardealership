package pl.cardealership.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.cardealership.domain.CarServiceRequest;
import pl.cardealership.infrastructure.database.entity.CarServiceRequestEntity;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarServiceRequestEntityMapper {
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "car", ignore = true)
    @Mapping(target = "serviceMechanics", ignore = true)
    @Mapping(target = "serviceParts", ignore = true)
    CarServiceRequest mapFromEntity(CarServiceRequestEntity obj);

    @Mapping(target = "customer.address", ignore = true)
    @Mapping(target = "customer.carServiceRequests", ignore = true)
    CarServiceRequestEntity mapToEntity(CarServiceRequest carServiceRequest);
}