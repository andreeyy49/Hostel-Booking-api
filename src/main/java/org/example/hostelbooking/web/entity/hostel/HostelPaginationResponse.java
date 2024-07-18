package org.example.hostelbooking.web.entity.hostel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HostelPaginationResponse {

    private HostelListResponse hostelList;

    private Integer size;
}
