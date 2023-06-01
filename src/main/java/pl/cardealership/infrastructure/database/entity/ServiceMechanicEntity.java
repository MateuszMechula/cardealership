package pl.cardealership.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString(of = {"serviceMechanicId", "hours", "comment"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "service_mechanic")
public class ServiceMechanicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_mechanic_id")
    private Integer serviceMechanicId;

    @Column(name = "hours")
    private Integer hours;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_service_request_id")
    private CarServiceRequestEntity carServiceRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mechanic_id")
    private MechanicEntity mechanic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private ServiceEntity service;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ServiceMechanicEntity that = (ServiceMechanicEntity) o;
        return getServiceMechanicId() != null && Objects.equals(getServiceMechanicId(), that.getServiceMechanicId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
