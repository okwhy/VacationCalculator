package ru.farafonov.services;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CalculatorService {
    BigDecimal calculate(BigDecimal avgSalary, int amountOfVacationDays);
    BigDecimal calculate(BigDecimal avgSalary, int amountOfVacationDays, LocalDate vacationDate);
}
