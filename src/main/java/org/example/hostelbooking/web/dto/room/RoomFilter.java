package org.example.hostelbooking.web.dto.room;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomFilter {

    @NotNull(message = "Размер страницы должен быть указан!")
    @Positive(message = "Размер страницы должен быть больше нуля!")
    private Integer pageSize;

    @NotNull(message = "Номер страницы должен быть указан!")
    @PositiveOrZero(message = "Номер страницы не должен быть меньше нуля!")
    private Integer pageNumber;

    private Long id;

    private String name;

    private Float minPrice;

    private Float maxPrice;

    private Integer maxPeopleSize;

    private Instant inBooking;

    private Instant outBooking;

    private Long hostelId;
}
