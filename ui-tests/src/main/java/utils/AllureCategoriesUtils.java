package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class AllureCategoriesUtils {

    public static void createAllureCategoriesFile() {
        Path sourceFile = Paths.get("src/test/resources/categories.json");
        Path targetFile = Paths.get("target/allure-results/categories.json");

        {

            try {
                Files.copy(sourceFile, targetFile,
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
