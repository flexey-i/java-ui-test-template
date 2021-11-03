package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static java.lang.System.getProperty;
import static java.util.Optional.ofNullable;
import static org.apache.commons.io.IOUtils.closeQuietly;

public class AllureEnvironmentUtils {

    public static void createAllureEnvFile() {

        FileOutputStream fos = null;

        try {
            Properties props = new Properties();
            fos = new FileOutputStream("target/allure-results/environment.properties");

            ofNullable(getProperty("selenide.browser")).ifPresent(s -> props.setProperty("Browser", s));
            ofNullable(getProperty("selenide.browserVersion")).ifPresent(s -> props.setProperty("Browser Version", s));
            ofNullable(getProperty("selenide.browserSize")).ifPresent(s -> props.setProperty("Browser window size", s));
            ofNullable(getProperty("selenide.headless")).ifPresent(s -> props.setProperty("Headless mode", s));
            ofNullable(getProperty("env")).ifPresent(s -> props.setProperty("Type of run tests (local or remote)", s));
            ofNullable(getProperty("selenide.baseUrl")).ifPresent(s -> props.setProperty("Base url", s));
            props.store(fos, "See https://github.com/allure-framework/allure-app/wiki/Environment");

            fos.close();
        } catch (IOException ignored) {
        } finally {
            closeQuietly(fos);
        }
    }

}
