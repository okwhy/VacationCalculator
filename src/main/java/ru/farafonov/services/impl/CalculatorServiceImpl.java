package ru.farafonov.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.farafonov.components.HolidayManager;
import ru.farafonov.services.CalculatorService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
@RequiredArgsConstructor
@Service
public class CalculatorServiceImpl implements CalculatorService {
    private final HolidayManager holidayManager;
    @Override
    public BigDecimal calculate(BigDecimal avgSalary, int vacationDaysNumber) {
        return avgSalary.divide(BigDecimal.valueOf(29.3),2,RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(vacationDaysNumber));

    }

    @Override
    public BigDecimal calculate(BigDecimal avgSalary, int amountOfVacationDays, LocalDate vacationDate) {
        int correctedAmount=amountOfVacationDays;
        LocalDate dateToCheck=vacationDate;
        for (int i = 0; i < amountOfVacationDays; i++) {

            if(holidayManager.contains(dateToCheck)
                    /*|| vacationDate.getDayOfWeek() == DayOfWeek.SATURDAY
                    || vacationDate.getDayOfWeek() == DayOfWeek.SUNDAY*/) correctedAmount--;

            dateToCheck=dateToCheck.plusDays(1);
        }
        return calculate(avgSalary,correctedAmount);
    }

}
