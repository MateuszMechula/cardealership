package pl.cardealership.infrastructure.database.repository;

import org.hibernate.Session;
import pl.cardealership.business.DAO.ServiceRequestProcessingDAO;
import pl.cardealership.infrastructure.database.entity.CarServiceRequestEntity;
import pl.cardealership.infrastructure.database.entity.ServiceMechanicEntity;
import pl.cardealership.infrastructure.database.entity.ServicePartEntity;

import java.util.Objects;

public class ServiceRequestProcessingRepository implements ServiceRequestProcessingDAO {
    @Override
    public void process(
            CarServiceRequestEntity serviceRequest, ServiceMechanicEntity serviceMechanicEntity) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(serviceMechanicEntity);
            if (Objects.nonNull(serviceRequest.getCompletedDateTime())) {
                session.merge(serviceRequest);
            }
            session.getTransaction().commit();
        }

    }

    @Override
    public void process(
            CarServiceRequestEntity serviceRequest,
            ServiceMechanicEntity serviceMechanicEntity,
            ServicePartEntity servicePartEntity) {


        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            session.persist(serviceMechanicEntity);

            session.persist(servicePartEntity);

            if (Objects.nonNull(serviceRequest.getCompletedDateTime())) {
                session.merge(serviceRequest);
            }
            session.getTransaction().commit();
        }
    }
}
