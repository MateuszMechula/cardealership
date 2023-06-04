package pl.cardealership.infrastructure.database.repository;

import org.hibernate.Session;
import pl.cardealership.business.DAO.CustomerDAO;
import pl.cardealership.infrastructure.configuration.HibernateUtil;
import pl.cardealership.infrastructure.database.entity.CustomerEntity;

import java.util.Objects;
import java.util.Optional;

public class CustomerRepository implements CustomerDAO {
    @Override
    public Optional<CustomerEntity> findByEmail(String email) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "SELECT se FROM CustomerEntity se WHERE se.email = :email";
            Optional<CustomerEntity> result = session.createQuery(query, CustomerEntity.class)
                    .setParameter("email", email)
                    .uniqueResultOptional();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public void issueInvoice(CustomerEntity customer) {
        try(Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            if (Objects.isNull(customer.getCustomerId())) {
                session.persist(customer);
            }

            customer.getInvoices().stream()
                .filter(invoice -> Objects.isNull(invoice.getInvoiceId()))
                .forEach(invoice -> {
                    invoice.setCustomer(customer);
                    session.persist(invoice);
                });

            session.getTransaction().commit();

        }
    }
}
