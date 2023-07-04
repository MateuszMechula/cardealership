package pl.cardealership.infrastructure.database.repository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.cardealership.business.DAO.ServiceRequestProcessingDAO;
import pl.cardealership.domain.CarServiceRequest;
import pl.cardealership.domain.ServiceMechanic;
import pl.cardealership.domain.ServicePart;
import pl.cardealership.infrastructure.database.entity.CarServiceRequestEntity;
import pl.cardealership.infrastructure.database.entity.PartEntity;
import pl.cardealership.infrastructure.database.entity.ServiceMechanicEntity;
import pl.cardealership.infrastructure.database.entity.ServicePartEntity;
import pl.cardealership.infrastructure.database.repository.jpa.CarServiceRequestJpaRepository;
import pl.cardealership.infrastructure.database.repository.jpa.PartJpaRepository;
import pl.cardealership.infrastructure.database.repository.jpa.ServiceMechanicJpaRepository;
import pl.cardealership.infrastructure.database.repository.jpa.ServicePartJpaRepository;
import pl.cardealership.infrastructure.database.repository.mapper.ServiceMechanicEntityMapper;
import pl.cardealership.infrastructure.database.repository.mapper.ServicePartEntityMapper;

import java.util.Objects;

@Repository
@AllArgsConstructor
public class ServiceRequestProcessingRepository implements ServiceRequestProcessingDAO {

    private final ServiceMechanicJpaRepository serviceMechanicJpaRepository;
    private final CarServiceRequestJpaRepository carServiceRequestJpaRepository;
    private final ServicePartJpaRepository servicePartJpaRepository;
    private final PartJpaRepository partJpaRepository;
    private final ServiceMechanicEntityMapper serviceMechanicEntityMapper;
    private final ServicePartEntityMapper servicePartEntityMapper;

    @Override
    @Transactional
    public void process(CarServiceRequest serviceRequest, ServiceMechanic serviceMechanic) {
        ServiceMechanicEntity serviceMechanicEntity = serviceMechanicEntityMapper.mapToEntity(serviceMechanic);
        serviceMechanicJpaRepository.saveAndFlush(serviceMechanicEntity);
        if (Objects.nonNull(serviceRequest.getCompletedDateTime())) {
            CarServiceRequestEntity carServiceRequestEntity = carServiceRequestJpaRepository
                    .findById(serviceRequest.getCarServiceRequestId())
                    .orElseThrow();
            carServiceRequestEntity.setCompletedDateTime(serviceRequest.getCompletedDateTime());
            carServiceRequestJpaRepository.saveAndFlush(carServiceRequestEntity);
        }
    }

    @Override
    @Transactional
    public void process(CarServiceRequest serviceRequest, ServiceMechanic serviceMechanic, ServicePart servicePart) {

        PartEntity partEntity = partJpaRepository.findById(servicePart.getPart().getPartId()).orElseThrow();
        ServicePartEntity servicePartEntity = servicePartEntityMapper.mapToEntity(servicePart);
        servicePartEntity.setPart(partEntity);
        servicePartJpaRepository.saveAndFlush(servicePartEntity);
        process(serviceRequest, serviceMechanic);
    }
}
