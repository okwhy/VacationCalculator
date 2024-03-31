package ru.farafonov.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.farafonov.responses.APIResponse;
import ru.farafonov.responses.errorhandling.ErrorsMsg;
import ru.farafonov.services.CalculatorService;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Validated
public class VacationCalculatorController {
    private final CalculatorService calculatorService;
    @GetMapping("/calculate")
    public  APIResponse<BigDecimal> calc(@RequestParam   @Min(value = 0, message = ErrorsMsg.NOT_VALID_SALARY)BigDecimal avgSalary,
                                   @RequestParam @Min(value = 0, message = ErrorsMsg.NOT_VALID_AMOUNT) int amountOfVacationDays,
                                   @RequestParam(required = false)
                                             @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate vacationDate){

        if (vacationDate!=null) {
            return APIResponse.ok( calculatorService.calculate(avgSalary,amountOfVacationDays, vacationDate));
        }
        return APIResponse.ok(calculatorService.calculate(avgSalary,amountOfVacationDays));
    }
}
