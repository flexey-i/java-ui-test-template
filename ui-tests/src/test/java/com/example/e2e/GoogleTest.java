package com.example.e2e;

import com.example.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.GoogleSearch;
import pages.SearchResults;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;


@DisplayName("Тесты для поисковой системы Google")
public class GoogleTest extends BaseTest {

    @BeforeEach
    @Step("Открываем главную страницу поиска")
    public void goToGoogle() {
        open("https://google.com/ncr");
    }

    @ParameterizedTest(name = "Проверка поиска по слову {0}")
    @CsvSource(
            {
                    "selenide, Selenide: concise UI tests in Java",
                    "JUnit5, JUnit 5 User Guide"
            }
    )
    @Severity(value = SeverityLevel.BLOCKER)
    public void searchForSelenide(String wordForSearch, String textToCheck) {
        // Act
        new GoogleSearch().searchFor(wordForSearch);
        // Assert
        SearchResults results = new SearchResults();
        results.googleSearchResultTitlesList.shouldHave(sizeGreaterThan(1));
        results.getResultByIndex("Google", 0).shouldHave(text(textToCheck));
    }
}
