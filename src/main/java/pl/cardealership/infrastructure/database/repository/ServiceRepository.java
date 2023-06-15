package pl.cardealership.infrastructure.database.repository;

import org.hibernate.Session;
import pl.cardealership.business.DAO.ServiceDAO;
import pl.cardealership.infrastructure.database.entity.ServiceEntity;

import java.util.Objects;
import java.util.Optional;

public class ServiceRepository implements ServiceDAO {
    @Override
    public Optional<ServiceEntity> findByServiceCode(String serviceCode) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "SELECT se FROM ServiceJpaRepository se WHERE se.serviceCode = :serviceCode";
            Optional<ServiceEntity> result = session.createQuery(query, ServiceEntity.class)
                    .setParameter("serviceCode", serviceCode)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }
}
