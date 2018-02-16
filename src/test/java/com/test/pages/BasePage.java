package com.test.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class BasePage {
    public HeaderSection headerSection=new HeaderSection();
    public void clearCookies() {
        Selenide.clearBrowserCookies();
    }

    @Step
    public void ensureThatNotificationContains(String expectedText){
        String notification=".notification.notification-visible div";
        $(notification).waitUntil(Condition.text(expectedText),20000).shouldHave(text(expectedText));
    }
}
