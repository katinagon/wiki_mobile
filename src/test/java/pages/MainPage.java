package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;

public class MainPage {
    private final SelenideElement search = $(accessibilityId("Search Wikipedia"));
    private final SelenideElement logoWiki = $(id("org.wikipedia.alpha:id/main_toolbar_wordmark"));

    public void clickSearchWikipediaIcon() {
        search.click();
    }

    public void checkSearchIsVisible() {
        search.shouldBe(visible);
    }
}
