package com.elearning.elearning.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddAddress {
    private String country;
    private String region;
    private String city;
    private String neighborhood;
    private Long door;
    private String phone;
    private Long street;

}
