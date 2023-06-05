package pl.cardealership.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.Objects;

@With
@Value
@Builder
public class CarServiceRequest {
    Customer customer;
    Car car;
    String customerComment;

    @With
    @Value
    @Builder
    public static class Car {
         String vin;
         String brand;
         String model;
         Integer year;

        public Boolean shouldExistsInCarToBuy() {
        return     Objects.nonNull(brand)
                && Objects.nonNull(model)
                && Objects.nonNull(year);
        }
    }
    @With
    @Value
    @Builder
    public static class Address {
        String country;
        String city;
        String postalCode;
        String address;
    }
    @With
    @Value
    @Builder
    public static class Customer {
         String name;
         String surname;
         String phone;
         String email;
         Address address;
    }

}
