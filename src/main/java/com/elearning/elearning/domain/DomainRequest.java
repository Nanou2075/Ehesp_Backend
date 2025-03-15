package com.elearning.elearning.domain;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DomainRequest {
    @NotNull(message = "Nom est obligatoire")
    @NotEmpty(message = "Nom ne doit pas être null")
    private String name;


}
