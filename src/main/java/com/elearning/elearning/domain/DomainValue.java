package com.elearning.elearning.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DomainValue {
    private String name;
    private int number;
}
