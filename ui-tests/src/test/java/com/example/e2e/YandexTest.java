package com.example.e2e;

import com.example.BaseTest;
import dataprovider.YandexSearchArgumentProvider;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import pages.SearchResults;
import pages.YandexSearch;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

@DisplayName("Тесты для поисковой системы Yandex")
public class YandexTest extends BaseTest {

    @BeforeEach
    @Step("Открываем главную страницу поиска")
    public void goToGoogle() {
        open("https://yandex.ru/");
    }

    @ParameterizedTest(name = "Проверка поиска по слову {0}")
    @DisplayName("Проверка поиска по слову Москва")
    @ArgumentsSource(YandexSearchArgumentProvider.class)
    @Severity(value = SeverityLevel.BLOCKER)
    public void searchForSelenide(String wordForSearch, String textToCheck) {
        // Act
        new YandexSearch().searchFor(wordForSearch);
        // Assert
        SearchResults results = new SearchResults();
        results.yandexSearchResultTitlesList.shouldHave(sizeGreaterThan(1));
        results.getResultByIndex("Yandex", 0).shouldHave(text(textToCheck));
    }
}
