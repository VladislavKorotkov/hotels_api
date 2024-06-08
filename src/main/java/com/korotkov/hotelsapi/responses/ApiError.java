package com.korotkov.hotelsapi.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiError {
    private int status;
    private String message;
    private List<String> errors;

}