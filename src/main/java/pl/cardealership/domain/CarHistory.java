package pl.cardealership.domain;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Value
@Builder
@ToString(of = "carVin")
public class CarHistory {

    String carVin;
    List<CarServiceRequest> serviceRequests;

    @Value
    @Builder
    @ToString(of = {"serviceRequestNumber", "receivedDateTime", "completedDateTime", "customerComment"})
    public static class CarServiceRequest {
        String serviceRequestNumber;
        OffsetDateTime receivedDateTime;
        OffsetDateTime completedDateTime;
        String customerComment;
        List<Service> services;
        List<Part> parts;
    }

}
