package pl.cardealership.infrastructure.database.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString(of = {"carServiceRequestId", "carServiceRequestNumber","receivedDateTime", "completedDateTime", "customerComment"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car_service_request")
public class CarServiceRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_service_request_id")
    private Integer carServiceRequestId;

    @Column(name = "car_service_request_number", unique = true)
    private Integer carServiceRequestNumber;

    @Column(name = "received_date_time")
    private OffsetDateTime receivedDateTime;

    @Column(name = "completed_date_time")
    private OffsetDateTime completedDateTime;

    @Column(name = "customer_comment")
    private String customerComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_to_service_id")
    private CarToServiceEntity car;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "carServiceRequest")
    private Set<ServiceMechanicEntity> serviceMechanics;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "carServiceRequest")
    private Set<ServicePartEntity> serviceParts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CarServiceRequestEntity that = (CarServiceRequestEntity) o;
        return getCarServiceRequestId() != null && Objects.equals(getCarServiceRequestId(), that.getCarServiceRequestId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}