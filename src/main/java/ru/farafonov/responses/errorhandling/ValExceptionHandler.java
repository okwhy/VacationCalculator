package ru.farafonov.responses.errorhandling;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.farafonov.responses.APIResponse;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@RestControllerAdvice
public class ValExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public APIResponse<String> handleException(ConstraintViolationException e){
        Set<ConstraintViolation<?>> set=e.getConstraintViolations();
        StringBuilder msg = new StringBuilder(" | ");
        for (ConstraintViolation<?> cv : set){
            msg.append(cv.getMessage()).append(" | ");
        }
        return APIResponse.badRequest(null,"ER-001","Violation of constraints", msg.toString());
    }
}
