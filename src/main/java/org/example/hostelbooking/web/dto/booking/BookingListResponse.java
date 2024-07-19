package org.example.hostelbooking.web.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingListResponse {

    private List<BookingResponse> bookings = new ArrayList<>();

}
