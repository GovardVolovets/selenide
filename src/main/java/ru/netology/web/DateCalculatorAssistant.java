package ru.netology.web;

public class DateCalculatorAssistant {
    public static String convertMonthNumberToText(String monthNumber) {
        String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        int index = Integer.parseInt(monthNumber) - 1;
        return months[index];
    }

    public static String[] splitDate(String date) {
        return date.split("\\.");
    }

    public static String getDay(String date) {
        String[] parts = splitDate(date);
        return parts[0];
    }

    public static String getMonthYear(String date) {
        String[] parts = splitDate(date);
        return convertMonthNumberToText(parts[1]) + " " + parts[2];
    }
}
