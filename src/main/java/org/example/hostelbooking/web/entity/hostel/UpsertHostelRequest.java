package org.example.hostelbooking.web.entity.hostel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertHostelRequest {

    private String name;

    private String title;

    private String city;

    private String address;

    private Float distance;

}
