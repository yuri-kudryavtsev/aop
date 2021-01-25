package org.example.aop.steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

/**
 * Created on 25.01.2021
 *
 * @author Yuri Kudryavtsev
 * skype: yuri.kudryavtsev.indeed
 * email: ykudryavtsev@maxilect.com
 */

public class GoogleSteps {
    @Step("Получаем сводку по результатам поиска")
    @Attachment
    public static String getResultStats() {
        return Selenide.$("#result-stats").text();
    }

    @Step("Выполняем поиск по запросу: {0}")
    public static void searchFor(String request) {
        Selenide.$("[name='q']").setValue(request);
        Selenide.$("[name='btnK']").click();
    }

    @Step("Открываем страницу поиска")
    public static void openSearchPage() {
        Selenide.open("https://www.google.ru/");
    }
}
