package pl.cardealership.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString(of = {"mechanicId", "name", "surname", "pesel"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mechanic")
public class MechanicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mechanic_id")
    private Long mechanicId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "pesel")
    private String pesel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mechanic")
    private Set<ServiceMechanicEntity> serviceMechanics;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MechanicEntity that = (MechanicEntity) o;
        return getMechanicId() != null && Objects.equals(getMechanicId(), that.getMechanicId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
