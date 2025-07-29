package helpers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AttachUtils {
/*
    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }*/

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName) {
        try {
            String base64 = ((TakesScreenshot) WebDriverRunner.getWebDriver())
                    .getScreenshotAs(OutputType.BASE64);

            if (base64 != null && !base64.isEmpty()) {
                return Base64.getDecoder().decode(base64);
            }
        } catch (Exception e) {
            Allure.addAttachment("Screenshot Error", "text/plain",
                    "Failed to get screenshot: " + e.getMessage());
        }
        return new byte[0];
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
