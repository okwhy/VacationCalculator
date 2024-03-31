package ru.farafonov.components;

import java.time.LocalDate;

public interface HolidayManager {
    boolean contains(LocalDate date);
}
