package com.zsy.moudle.admin.model;

import lombok.Data;

@Data
public class TokenDTO {
    private String token;
    private Long expires;
}
