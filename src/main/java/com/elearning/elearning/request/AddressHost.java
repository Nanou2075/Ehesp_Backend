package com.elearning.elearning.request;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AddressHost {
    private String country;
    private String region;
    private String city;
    private String town;
    private String province;

}
