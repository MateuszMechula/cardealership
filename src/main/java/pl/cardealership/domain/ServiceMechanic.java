package pl.cardealership.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import pl.cardealership.infrastructure.database.entity.CarServiceRequestEntity;

import java.util.Objects;

@With
@Value
@Builder
@EqualsAndHashCode(of = "serviceMechanicId")
@ToString(of = {"serviceMechanicId", "hours", "comment"})
public class ServiceMechanic {

    Integer serviceMechanicId;
    Integer hours;
    String comment;
    CarServiceRequest carServiceRequest;
    Mechanic mechanic;
    Service service;
}
