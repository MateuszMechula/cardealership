package pl.cardealership.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.Set;


@With
@Value
@Builder
@EqualsAndHashCode(of = "salesmanId")
@ToString(of = {"salesmanId", "name", "surname", "pesel"})
public class Salesman {

    Long salesmanId;
    String name;
    String surname;
    String pesel;
    Set<Invoice> invoices;
}
