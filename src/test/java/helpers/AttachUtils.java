package helpers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AttachUtils {
/*
    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }*/

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName) {
        // 1. Пробуем стандартный способ
        byte[] screenshot = getScreenshotBytes();

        // 2. Если не получилось - пробуем через временный файл
        if (screenshot == null || screenshot.length == 0) {
            screenshot = getScreenshotViaFile();
        }

        // 3. Если все равно не получилось - возвращаем пустой массив
        return screenshot != null ? screenshot : new byte[0];
    }

    private static byte[] getScreenshotBytes() {
        try {
            return ((TakesScreenshot) WebDriverRunner.getWebDriver())
                    .getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            return null;
        }
    }

    private static byte[] getScreenshotViaFile() {
        try {
            File file = Selenide.screenshot(OutputType.FILE);
            return file != null ? Files.readAllBytes(file.toPath()) : null;
        } catch (Exception e) {
            return null;
        }
    }

    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] pageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String addVideo(String sessionId) {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + BrowserStack.videoUrl(sessionId)
                + "' type='video/mp4'></video></body></html>";
    }
}
