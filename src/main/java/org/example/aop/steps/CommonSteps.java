package org.example.aop.steps;

import io.qameta.allure.Step;
import org.example.aop.annotations.WithRetries;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import java.util.Random;

/**
 * Created on 25.01.2021
 *
 * @author Yuri Kudryavtsev
 * skype: yuri.kudryavtsev.indeed
 * email: ykudryavtsev@maxilect.com
 */

public class CommonSteps {
    @Step("Проверяем, что случайное число = {0}")
    @WithRetries(20)
    public static void checkRandom(int desiredValue) {
        Random random = new Random();
        int i = random.nextInt(10);
        MatcherAssert.assertThat(
                "Неправильный результат",
                i,
                Matchers.equalTo(desiredValue)
        );
    }
}
