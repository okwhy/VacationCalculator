package ru.farafonov.responses.errorhandling;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.farafonov.responses.APIResponse;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public APIResponse<String> handleTypeMismatch(MethodArgumentTypeMismatchException e){
        Class reqtype=e.getRequiredType();
        if (reqtype== LocalDate.class) return APIResponse.
                badRequest(null,"ER-002","Type Mismatch",ErrorsMsg.WRONG_FORMAT_DATE);
        if (reqtype== BigDecimal.class) return APIResponse.
                badRequest(null,"ER-002","Type Mismatch",ErrorsMsg.WRONG_FORMAT_SALARY);
        if (reqtype== int.class) return APIResponse.
                badRequest(null,"ER-002","Type Mismatch",ErrorsMsg.WRONG_FORMAT_DAYS);
        return APIResponse.
                badRequest(null,"ER-002","Type Mismatch",null);
    }
}
