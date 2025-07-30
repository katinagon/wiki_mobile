package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class MainPage {
    private final SelenideElement search = $(accessibilityId("Search Wikipedia"));

    public void clickSearchWikipediaIcon() {
        search.click();
    }

    public void checkSearchIsVisible() {
        search.shouldBe(visible);
    }
}
