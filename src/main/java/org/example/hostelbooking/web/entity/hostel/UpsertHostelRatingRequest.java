package org.example.hostelbooking.web.entity.hostel;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertHostelRatingRequest {

    @NotNull(message = "Рейтинг должен быть указан!")
    @Min(value = 1, message = "Рейтинг не может быть меньше {value}!")
    @Max(value = 5, message = "Рейтинг не может быть больше {value}!")
    private Float newMark;

}
