package org.example.hostelbooking.web.dto.hostel;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HostelFilter {
    @NotNull(message = "Размер страницы должен быть указан!")
    @Positive(message = "Размер страницы должен быть больше нуля!")
    private Integer pageSize;

    @NotNull(message = "Номер страницы должен быть указан!")
    @PositiveOrZero(message = "Номер страницы не должен быть меньше нуля!")
    private Integer pageNumber;

    private Long id;

    private String name;

    private String title;

    private String city;

    private String address;

    private Float distance;

    private Float rating;

    private Float ratingCounter;
}
