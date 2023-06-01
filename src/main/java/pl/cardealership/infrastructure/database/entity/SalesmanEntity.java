package pl.cardealership.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@ToString(of = {"salesmanId", "name", "surname", "pesel"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "salesman")
public class SalesmanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salesman_id")
    private Long salesmanId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "pesel", unique = true)
    private String pesel;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "salesman")
    private Set<InvoiceEntity> invoices;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SalesmanEntity that = (SalesmanEntity) o;
        return getSalesmanId() != null && Objects.equals(getSalesmanId(), that.getSalesmanId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
