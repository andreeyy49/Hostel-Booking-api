package org.example.hostelbooking.web.dto.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
