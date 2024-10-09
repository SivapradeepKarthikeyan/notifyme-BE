package com.notifyMe.notifyMe.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotifyMeResponse {
    String status;
    String message;
    int statusCode;
    Object data;
}
