package org.example.aop;


import org.example.aop.steps.CommonSteps;
import org.example.aop.steps.GoogleSteps;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

/**
 * Created on 24.01.2021
 *
 * @author Yuri Kudryavtsev
 * skype: yuri.kudryavtsev.indeed
 * email: ykudryavtsev@maxilect.com
 */

public class Tests {
    @Test
    void errorsSkippingTest() {
        MatcherAssert.assertThat("test", true, Matchers.equalTo(false));
        MatcherAssert.assertThat("test2", 1, Matchers.equalTo(2));
        MatcherAssert.assertThat("test3", "olol", Matchers.equalTo("pishpish"));
    }

    @Test
    public void allureLogTest() {
        GoogleSteps.openSearchPage();
        GoogleSteps.searchFor("java");
        String resultStats = GoogleSteps.getResultStats();
        MatcherAssert.assertThat(
                "Неправильная сводка по результам поиска",
                resultStats,
                Matchers.equalTo("-")
        );
    }

    @Test
    public void retriesTest() {
        CommonSteps.checkRandom(2);
        CommonSteps.checkRandom(3);
        CommonSteps.checkRandom(5);
    }
}
