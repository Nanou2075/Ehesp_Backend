package com.elearning.elearning.year;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class YearRequest {
    @NotNull(message = "Année de debut  est obligatoire")
    @NotEmpty(message = "Année de debut ne doit pas être null")
    private String beginYear;
    @NotNull(message = "Année de fin est obligatoire")
    @NotEmpty(message = "Année de fin ne doit pas être null")
    private String endYear;
    private String currentYear;

}
