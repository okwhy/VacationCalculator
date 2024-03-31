package ru.farafonov.responses.errorhandling;

import lombok.*;
import org.springframework.util.ObjectUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APIResponseError {
    private String code;
    private String message;
    private String description;

    public String getDescription() {
        return ObjectUtils.isEmpty(description)?message : description;
    }
}
