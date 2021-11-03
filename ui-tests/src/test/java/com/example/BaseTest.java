package com.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.AllureCategoriesUtils;
import utils.AllureEnvironmentUtils;

import java.util.logging.Level;


public class BaseTest {
    private static final String environment = System.getProperty("env");

    @BeforeAll
    @Step("Задаем параметры запуска браузера и подключаем листенер Allure")
    public static void setUp() {

        if (environment.equalsIgnoreCase("local")) {
            Configuration.browser = System.getProperty("selenide.browser");
            Configuration.browserVersion = System.getProperty("selenide.browserVersion");
            Configuration.browserSize = System.getProperty("selenide.browserSize");
            Configuration.browserCapabilities.setCapability("intl.accept_languages", "ru");
            SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
            SelenideLogger.addListener("AllureSelenide", new AllureSelenide().enableLogs(LogType.BROWSER, Level.ALL));
        } else {
            Configuration.remote = "http://localhost:4444/wd/hub";
            Configuration.browser = System.getProperty("selenide.browser");
            Configuration.browserVersion = System.getProperty("selenide.browserVersion");
            Configuration.browserSize = System.getProperty("selenide.browserSize");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
        }
    }

    @AfterAll
    @Step("Создаем файлы с переменными окружения и категориями тестов " +
            "для отображения в отчете")
    public static void createEnvFile() {
        AllureEnvironmentUtils.createAllureEnvFile();
        AllureCategoriesUtils.createAllureCategoriesFile();
    }

}