package org.example.hostelbooking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String number;

    private Float price;

    private Integer maxPeopleSize;

    private Instant bookingTime;

    @ManyToOne
    @JoinColumn(name = "hostel_id")
    @ToString.Exclude
    private Hostel hostel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Booking> bookings = new ArrayList<>();
}
