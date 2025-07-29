package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.className;
import static io.appium.java_client.AppiumBy.id;

public class SearchPage {
    private final SelenideElement inputElement = $(id("org.wikipedia.alpha:id/search_src_text"));
    private final ElementsCollection listElement = $$(id("org.wikipedia.alpha:id/page_list_item_title"));
    private final ElementsCollection articleContent = $$(className("android.widget.TextView"));
    private final ElementsCollection searchResults = $$(id("org.wikipedia.alpha:id/page_list_item_title"));

    public void searchPageSetValue(String value){
        inputElement.sendKeys(value);
    }

    public void clickTheFirstResult(){
        listElement.first().click();
    }

    public void checkSearchResults(){
        searchResults.shouldHave(sizeGreaterThan(0));
    }

    public void checkArticleContent(){
        articleContent.shouldHave(sizeGreaterThan(0));
    }
}
