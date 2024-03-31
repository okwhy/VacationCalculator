package ru.farafonov.responses.errorhandling;

public final class ErrorsMsg {
    private ErrorsMsg(){

    }
    public static final String NOT_VALID_SALARY = "Средняя зарплата не должна быть меньше нуля";
    public static final String NOT_VALID_AMOUNT = "Количество дней отпуска не должно быть меньше нуля";
    public static final String WRONG_FORMAT_DATE ="Дата выхода в отпуск введена в неверном формате";
    public static final String WRONG_FORMAT_SALARY ="Зарплата введена в неверном формате";
    public static final String WRONG_FORMAT_DAYS ="Количество дней отпуска введено в неверном формате";
}
