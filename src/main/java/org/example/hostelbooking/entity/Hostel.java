package org.example.hostelbooking.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "hostels")
@FieldNameConstants
@Builder
public class Hostel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String title;

    private String city;

    private String address;

    private Float distance;

    private Float rating;

    private Float ratingCounter;

    @OneToMany(mappedBy = "hostel", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Room> rooms = new ArrayList<>();
}
