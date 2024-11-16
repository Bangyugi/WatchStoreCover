package com.group2.watchstorecover.exception;



import com.group2.watchstorecover.dto.response.ApiResponse;
import jakarta.transaction.TransactionalException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class GlobalException {
    /**
     * Handles AppException
     *
     * @param appException the exception thrown by a service
     * @return ResponseEntity with error code and message indicating the exception
     */
    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> appException(AppException appException) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(appException.getErrorCode().getCode())
                .message(appException.getErrorCode().getMessage())
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }

    /**
     * Handles TransactionalException
     *
     * @param transactionalException the exception thrown when a transaction is failed
     * @return ResponseEntity with error code and message indicating the transaction is failed
     */
    @ExceptionHandler(TransactionalException.class)
    public ResponseEntity<?> transactionalException(TransactionalException transactionalException) {
        return ResponseEntity.badRequest().body(transactionalException.getMessage());
    }

    /**
     * Handles RuntimeException.
     *
     * @param runtimeException the exception thrown during the runtime of the program.
     * @return ResponseEntity with a message indicating the runtime error.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeException (RuntimeException runtimeException){
        return ResponseEntity.badRequest().body(runtimeException.getMessage());
    }

    /**
     * Handles IOException
     *
     * @param ioException the exception thrown when an input/output operation is failed
     * @return ResponseEntity with error code and message indicating the input/output operation is failed
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> ioeException (IOException ioException){
        return ResponseEntity.badRequest().body(ioException.getMessage());
    }

    /**
     * Handles MethodArgumentNotValidException
     *
     * @param validException
     * @return ResponseEntity with error code and message
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validException(MethodArgumentNotValidException validException){
        String message = validException.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.ERR_VALID;
        try {
            errorCode = ErrorCode.valueOf(message);
        }
        catch (RuntimeException runtimeException)
        {
            System.out.println("Error not define");
        }

        ApiResponse apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }

    /**
     * Handles IllegalArgumentException.
     *
     * @param illegalArgumentException the exception thrown when an illegal argument is passed.
     * @return ResponseEntity with error code and message indicating the illegal argument.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public  ResponseEntity<?> illegalArgumentException(IllegalArgumentException illegalArgumentException){
        ErrorCode errorCode  = ErrorCode.ERR_VALID_ARGUMENT;
        errorCode.setMessage(illegalArgumentException.getMessage());
        ApiResponse apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
