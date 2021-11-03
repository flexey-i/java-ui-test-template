package pages;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class GoogleSearch {

  // Elements
  SelenideElement searchField = $(Selectors.byName("q"));

  // Actions
  @Step("Ввести в поисковую строку слово {text} и нажать Enter")
  public void searchFor(String text) {
    searchField.setValue(text).pressEnter();
  }
}
