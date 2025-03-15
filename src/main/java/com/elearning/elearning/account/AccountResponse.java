package com.elearning.elearning.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponse {
    private String mail;
    private String phone;
    private String fullName;


}
