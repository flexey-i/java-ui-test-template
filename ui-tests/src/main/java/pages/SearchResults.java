package pages;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;

public class SearchResults {

    // Elements
    public ElementsCollection googleSearchResultTitlesList = $$x("//h3");
    public ElementsCollection yandexSearchResultTitlesList = $$x("//span[@class='OrganicTitleContentSpan']");

    // Actions
    //todo need refactoring)
    @Step("Получить элемент из коллекции по номеру")
    public SelenideElement getResultByIndex(String searchSystem, int index) {
        if (searchSystem.equalsIgnoreCase("google")) {
            return googleSearchResultTitlesList.get(index);
        } else
            return yandexSearchResultTitlesList.get(index);
    }

}
