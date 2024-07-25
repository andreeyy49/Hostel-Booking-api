package org.example.hostelbooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bookingData")
public class BookingData {

    private String id;

    private Long userId;

    private Instant inBooking;

    private Instant outBooking;

}
