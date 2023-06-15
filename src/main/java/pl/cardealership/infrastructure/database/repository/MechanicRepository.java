package pl.cardealership.infrastructure.database.repository;

import org.hibernate.Session;
import pl.cardealership.business.DAO.MechanicDAO;
import pl.cardealership.infrastructure.database.entity.MechanicEntity;

import java.util.Objects;
import java.util.Optional;

public class MechanicRepository implements MechanicDAO {
    @Override
    public Optional<MechanicEntity> findByPesel(String pesel) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "SELECT se FROM MechanicJpaRepository se WHERE se.pesel = :pesel";
            Optional<MechanicEntity> result = session.createQuery(query, MechanicEntity.class)
                    .setParameter("pesel", pesel)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }
}
