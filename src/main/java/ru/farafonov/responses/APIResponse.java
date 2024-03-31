package ru.farafonov.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;
import ru.farafonov.responses.errorhandling.APIResponseError;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<T> {
    private Integer httpStatus;
    private T result;
    private APIResponseError error;
    public static <T> APIResponse <T> ok(T res){
        return APIResponse.<T>builder()
                .httpStatus(HttpStatus.OK.value())
                .result(res)
                .build();
    }
    public static <T> APIResponse<T> badRequest(T res,String code,String msg,String dsc){
        APIResponseError error=APIResponseError.builder()
                .code(code)
                .message(msg)
                .description(dsc)
                .build();
        return APIResponse.<T>builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .result(res)
                .error(error)
                .build();
    }
}
