package com.group2.watchstorecover.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppException extends RuntimeException{
    private ErrorCode errorCode;

}
