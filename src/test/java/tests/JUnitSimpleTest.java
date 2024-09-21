package tests;

import com.codeborne.selenide.Configuration;
import data.Locale;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;

public class JUnitSimpleTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
    }

    @BeforeEach
    void beforeEach() {
        open("https://google.com");
    }

    @Disabled("Указываем причину пропуска теста") // Тест не пройдет
    @DisplayName("Адрес https://ru.selenide.org должен быть в выдаче по запросу 'Selenide'") // Описание или название теста
    @Test
    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")}) // Метки серьезности и вида теста
    void selenideSitUrlShouldBePresentInResultOfSearchInGoogleBySelenideQuery() {
        $("[name=q]").setValue("Selenide").pressEnter();
        $("#search").shouldHave(text("https://ru.selenide.org"));
    }

    @Disabled("Указываем причину пропуска теста")
    @DisplayName("Адрес https://qameta.io должен быть в выдаче по запросу 'Allure testops'")
    @Test
    void allureTest() {
        $("[name=q]").setValue("Allure testops").pressEnter();
        $("#search").shouldHave(text("https://qameta.io"));
    }

    // Параметризованные тесты
    @CsvSource({
            "Selenide, https://ru.selenide.org",
            "Allure testops, https://qameta.io"
    })
//    @CsvFileSource(resources = "/testData.csv") // Вариант второй, вместо @CsvSource
    @ParameterizedTest(name = "Адрес {1} должен быть в выдаче по запросу {0}")
    void paramSelenideSitUrlShouldBePresentInResultOfSearchInGoogleBySelenideQuery(
            String productName,
            String productUrl
    ) {
        $("[name=q]").setValue(productName).pressEnter();
        $("#search").shouldHave(text(productUrl));
    }
}
