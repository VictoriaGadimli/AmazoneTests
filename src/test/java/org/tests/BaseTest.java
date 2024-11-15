package org.tests;

import app.App;
import app.AppConfig;
import app.Driver;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    protected static App app;

    @BeforeAll
    public static void setUp() {
        Driver.initDriver();
        app = new App();
    }

    @BeforeEach
    public void InjectAntiBotCookie()  {
        open(AppConfig.baseUrl + "not-exist-page");

        Cookie cookie = new Cookie("name", "value");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
    }

    @AfterEach
    public void clearCookies() {
        Driver.clearCookies();
    }

    @AfterAll
    public static void tearDown() {
        Driver.close();
    }
}
