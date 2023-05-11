package ru.netology.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

class RegistrationTest {
    @Test
    void positiveRegisterSimple() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Москва");
        $(".menu-item").click();

        LocalDate currentDate = LocalDate.now();
        LocalDate randomDate = currentDate.plusDays(3 + new Random().nextInt(14));
        String dateValue = randomDate.format(DateTimeFormatter.ofPattern("ddMMyyyy"));

        $("[data-test-id=date]").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(dateValue);
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+79095553322");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).should(appear, Duration.ofMillis(15000));
    }

    @Test
    void positiveRegisterHard() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Мо");
        $$(".menu-item").findBy(text("Москва")).click();

        LocalDate currentDate = LocalDate.now();
        LocalDate targetDate = currentDate.plusDays(7);
        String dateValue = String.valueOf(targetDate.getDayOfMonth());

        $("[data-test-id=date]").click();
        $$(".calendar__day").find(exactText(dateValue)).click();
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+79095553322");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).should(appear, Duration.ofMillis(15000));
    }
}

