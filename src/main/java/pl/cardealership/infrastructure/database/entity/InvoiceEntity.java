package pl.cardealership.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.OffsetDateTime;
import java.util.Objects;


@Getter
@Setter
@ToString(of = {"invoiceId", "invoiceNumber", "dateTime"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoice")
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Long invoiceId;

    @Column(name = "invoice_number", unique = true)
    private String invoiceNumber;

    @Column(name = "date_time")
    private OffsetDateTime dateTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_to_buy_id")
    private CarToBuyEntity car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salesman_id")
    private SalesmanEntity salesman;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        InvoiceEntity that = (InvoiceEntity) o;
        return getInvoiceId() != null && Objects.equals(getInvoiceId(), that.getInvoiceId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
