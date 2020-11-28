package com.soten.eatgo.user.service;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class SessionResponseDto {

    private String accessToken;

}
