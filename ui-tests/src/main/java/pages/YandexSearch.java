package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class YandexSearch {

    // Elements
    SelenideElement searchField = $x("//input[contains(@class,'input__control')]");

    // Actions
    @Step("Ввести в поисковую строку слово {text} и нажать Enter")
    public void searchFor(String text) {
        searchField.setValue(text).pressEnter();
    }
}
