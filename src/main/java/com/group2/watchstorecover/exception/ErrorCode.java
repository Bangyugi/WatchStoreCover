package com.group2.watchstorecover.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public enum ErrorCode {
    ERR_VALID(101,"Error not define"),
    ERR_VALID_ARGUMENT(102,"Error not define")
    ;
    int code;
    String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
