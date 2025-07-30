package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.SearchPage;

import static io.qameta.allure.Allure.step;

@Owner("goncharova-ek")
@DisplayName("Тесты на поиск")
public class SearchTests extends TestBase {
    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();

    @DisplayName("Успешный поиск")
    @Test
    void successfulSearchTest() {
        step("Нажимаем на поисковую строку", () -> {
            mainPage.clickSearchWikipediaIcon();
        });

        step("Вводим 'Appium' в поисковую строку", () -> {
            searchPage.searchPageSetValue("Appium");
        });

        step("Проверяем наличие результатов поиска", () -> {
            searchPage.checkSearchResults();
        });
    }

    @DisplayName("Открытие статьи из результатов поиска")
    @Test
    void openArticleTest() {
        step("Нажимаем на поисковую строку", () -> {
            mainPage.clickSearchWikipediaIcon();
        });

        step("Вводим 'Appius' в поисковую строку", () -> {
            searchPage.searchPageSetValue("Appius");
        });

        step("Открываем первую статью из результатов", () -> {
            searchPage.clickTheFirstResult();
        });

        step("Проверяем наличие контента", () -> {
            searchPage.checkArticleContent();
        });
    }

    @DisplayName("Возврат на главную страницу со страницы поиска")
    @Test
    void successfulSearchTest2() {
        step("Нажимаем на поисковую строку", () -> {
            mainPage.clickSearchWikipediaIcon();
        });

        step("Нажимаем на кнопку назад", () -> {
            searchPage.clickBackBtn();
        });

        step("Проверяем наличие поисковой строки на главной странице", () -> {
            mainPage.checkSearchIsVisible();
        });
    }
}
