package ru.farafonov.components.impl;

import org.springframework.stereotype.Component;
import ru.farafonov.components.HolidayManager;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class HolidayManagerImpl implements HolidayManager {
    private  final Set<Holiday> holidaysDates;
    public HolidayManagerImpl(){
        holidaysDates=new HashSet<>();
        addBetween(LocalDate.of(0, Month.JANUARY, 1),
                LocalDate.of(0, Month.JANUARY, 8));
        holidaysDates.add(new Holiday(Month.FEBRUARY, 23));
        holidaysDates.add(new Holiday(Month.MARCH, 8));
        holidaysDates.add(new Holiday(Month.MAY, 1));
        holidaysDates.add(new Holiday(Month.MAY, 9));
        holidaysDates.add(new Holiday(Month.JUNE, 12));
        holidaysDates.add(new Holiday(Month.NOVEMBER, 4));
    }
    private void addBetween(LocalDate start,LocalDate end){
        List<LocalDate> dates = Stream.iterate(start, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(start, end)+1)
                .collect(Collectors.toList());
        for (LocalDate date: dates) {
            holidaysDates.add(new Holiday(date));
        }
    }
    public boolean contains(LocalDate date){
        return holidaysDates.contains(date);
    }
    private static class Holiday{
        int month;
        int day;
        public Holiday(LocalDate ld) {
            this.month=ld.getMonthValue();
            this.day=ld.getDayOfMonth();
        }
        public Holiday(Month month,int day){
            this.month=month.getValue();
            this.day=day;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Holiday holiday = (Holiday) o;
            return month == holiday.month && day == holiday.day;
        }

        @Override
        public int hashCode() {
            return Objects.hash(month, day);
        }
    }
}
