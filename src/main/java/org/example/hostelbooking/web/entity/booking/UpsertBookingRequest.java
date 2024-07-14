package org.example.hostelbooking.web.entity.booking;

import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertBookingRequest {

    private Instant in;

    private Instant out;

    private Long roomId;

    private Long userId;
}