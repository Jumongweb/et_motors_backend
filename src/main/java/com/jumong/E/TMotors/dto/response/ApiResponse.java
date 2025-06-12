package com.jumong.E.TMotors.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ApiResponse <T>{
    private String message;
    private T data;
}
