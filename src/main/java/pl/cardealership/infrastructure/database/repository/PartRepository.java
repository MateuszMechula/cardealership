package pl.cardealership.infrastructure.database.repository;

import org.hibernate.Session;
import pl.cardealership.business.DAO.PartDAO;
import pl.cardealership.infrastructure.configuration.HibernateUtil;
import pl.cardealership.infrastructure.database.entity.PartEntity;

import java.util.Objects;
import java.util.Optional;

public class PartRepository implements PartDAO {
    @Override
    public Optional<PartEntity> findByPartCode(String serialNumber) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "SELECT se FROM PartEntity se WHERE se.serialNumber = :serialNumber";
            Optional<PartEntity> result = session.createQuery(query, PartEntity.class)
                    .setParameter("serialNumber", serialNumber)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }
}
