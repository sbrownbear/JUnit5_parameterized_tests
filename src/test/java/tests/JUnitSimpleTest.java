package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;

public class JUnitSimpleTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;
        Configuration.pageLoadStrategy = "eager";
    }

    @BeforeEach
    void beforeEach() {
        open("https://google.com");
    }

    @Test
    void selenideSitUrlShouldBePresentInResultOfSearchInGoogleBySelenideQuery() {
        $("[name=q]").setValue("Selenide").pressEnter();
        $("#search").shouldHave(text("https://ru.selenide.org"));
    }

    @Test
    void allureTest() {
        $("[name=q]").setValue("Allure testops").pressEnter();
        $("#search").shouldHave(text("https://qameta.io"));
    }
}
