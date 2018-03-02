package com.test.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public abstract class BasePage {
    public HeaderSection headerSection=page(HeaderSection.class);
    private String notification=".notification.notification-visible div";
    public void clearCookies() {
        Selenide.clearBrowserCookies();
    }

    @Step
    public void ensureThatNotificationContains(String expectedText){
        $(notification).waitUntil(Condition.text(expectedText),20000).shouldHave(text(expectedText));
        $(notification).waitUntil(disappear,10000);
    }


}
