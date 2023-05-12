package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.web.DateGenerator.generateDate;

class RegistrationTest {
    @Test
    void positiveRegisterSimple() {

        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Москва");
        $(".menu-item").click();

        String planningDate = generateDate(5, "dd.MM.yyyy");

        $("[data-test-id=date]").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(planningDate);
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+79095553322");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).should(appear, Duration.ofMillis(15000));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void positiveRegisterHard() {

        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Мо");
        $$(".menu-item").findBy(text("Москва")).click();

        String planningDate = generateDate(7, "dd.MM.yyyy");

        String day = DateCalculatorAssistant.getDay(planningDate);
        String monthYear = DateCalculatorAssistant.getMonthYear(planningDate);

        $("[data-test-id=date]").click();

        while (!$(".calendar__name").text().contains(monthYear)) {
            $$(".calendar__arrow_direction_right").get(1).click();
        }

        $$(".calendar__day").find(exactText(day)).click();
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+79095553322");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).should(appear, Duration.ofMillis(15000));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}

