package org.example.hostelbooking.web.dto.booking;

import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertBookingRequest {

    private Instant inBooking;

    private Instant outBooking;

    private Long roomId;

    private Long userId;
}
