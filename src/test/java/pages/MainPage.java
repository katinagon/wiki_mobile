package pages;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class MainPage {
    private final String search = "Search Wikipedia";

    public void clickSearchWikipediaIcon() {
        $(accessibilityId(search)).click();
    }
}
