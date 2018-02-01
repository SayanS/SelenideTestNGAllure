package com.test.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultsPage extends BasePage {
    @Step
    public SearchResultsPage ensureResultsContains(String text ) {
        $(By.partialLinkText(text)).shouldBe(visible);
        return this;
    }
    @Step
    public SearchResultsPage ensureResultsHaveSize(int size ) {
        getResults().shouldHave(size(size));
        return this;
    }
    @Step
    public ElementsCollection getResults() {
        return $$(By.className("rc"));
    }
}
