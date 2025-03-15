package com.elearning.elearning.security.token;

import com.elearning.elearning.exception.enums.Permission;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class TokenValue {
    private String accessToken;
    private  String refreshToken;
    private  Boolean password ;
    private Permission permission ;
    private String url ;


}
