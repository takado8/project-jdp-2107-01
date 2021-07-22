package com.kodilla.ecommercee.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("status")
    private String status;

    @JsonProperty("userKey")
    private Long userKey;
}
