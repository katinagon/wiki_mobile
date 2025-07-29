package drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;

public class BrowserStackDriver implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MobileConfig config = ConfigFactory.create(MobileConfig.class);

        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("browserstack.user", System.getProperty("bs_user"));
        caps.setCapability("browserstack.key", System.getProperty("bs_key"));

        caps.setCapability("app", config.app());
        caps.setCapability("device", config.device());
        caps.setCapability("os_version", config.osVersion());

        caps.setCapability("project", "Mobile Tests");
        caps.setCapability("build", "browserstack-build");
        caps.setCapability("name", config.platformName() + " test");

        try {
            return new RemoteWebDriver(new URL("https://hub.browserstack.com/wd/hub"), caps);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
