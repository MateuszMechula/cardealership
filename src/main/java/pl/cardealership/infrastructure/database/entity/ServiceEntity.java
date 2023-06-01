package pl.cardealership.infrastructure.database.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString(of = {"serviceId", "serviceCode", "description", "price"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "service")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer serviceId;

    @Column(name = "service_code", unique = true)
    private String serviceCode;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "service")
    private Set<ServiceMechanicEntity> serviceMechanics;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ServiceEntity that = (ServiceEntity) o;
        return getServiceId() != null && Objects.equals(getServiceId(), that.getServiceId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
