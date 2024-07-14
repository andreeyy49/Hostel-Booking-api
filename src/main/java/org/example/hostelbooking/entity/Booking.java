package org.example.hostelbooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant in;

    private Instant out;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @ToString.Exclude
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
}
