package org.remotearduino.app.config.error;

import lombok.extern.slf4j.Slf4j;
import org.remotearduino.app.modules.common.hexagonal.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleCustomException(RuntimeException ex) {
        var httpError = switch (ex) {
            case NotFoundBusinessException e -> HttpStatus.NOT_FOUND;
            case NotAllowedBusinessException e -> HttpStatus.FORBIDDEN;
            case InputBusinessException e -> HttpStatus.BAD_REQUEST;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };

        if (ex instanceof BusinessException e) {
            return Mono.just(ResponseEntity.status(httpError)
                            .body(buildErrorResponse(e)));
        }

        log.error("Unhandled error", ex);
        var errorResponse = new ErrorResponse("500", "Internal Server Error");
        return Mono.just(ResponseEntity.internalServerError().body(errorResponse));
    }

    private ErrorResponse buildErrorResponse(BusinessException exception) {
        return new ErrorResponse(exception.getCode(), exception.getMessage());
    }
}
