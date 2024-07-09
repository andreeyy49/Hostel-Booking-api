package org.example.hostelbooking.web.entity.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertRoomRequest {

    private String name;

    private String description;

    private String number;

    private Float price;

    private Integer maxPeopleSize;

    private String hostelId;

    private String bookingTime;

}
