package org.example.hostelbooking.web.dto.hostel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HostelListResponse {

    private List<HostelResponse> hostels = new ArrayList<>();
}
