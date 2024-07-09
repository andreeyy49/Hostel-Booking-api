package org.example.hostelbooking.web.entity.hostel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertHostelRequest {

    private String name;

    private String title;

    private String city;

    private String address;

    private Float distance;

    private List<String> roomsIds = new ArrayList<>();
}
