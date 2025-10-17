package com.tekprymid.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseDet {
    private String message;
    private boolean error;
    private HttpStatus httpStatus;
    private Object data;
}
