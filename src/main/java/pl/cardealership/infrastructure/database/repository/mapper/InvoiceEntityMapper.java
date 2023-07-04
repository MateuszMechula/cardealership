package pl.cardealership.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.cardealership.domain.Invoice;
import pl.cardealership.infrastructure.database.entity.InvoiceEntity;
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InvoiceEntityMapper {


    InvoiceEntity mapToEntity(Invoice invoice);
}
