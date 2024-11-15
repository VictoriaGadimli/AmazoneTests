package app.pages;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage extends BasePage {

    public SelenideElement accountListElement = $("#nav-link-accountList");
    private SelenideElement signInLinkField = $("#nav-link-accountList");
    private SelenideElement loginField = $("#ap_email");
    private SelenideElement passwordField = $("#ap_password");

    public LoginPage() {
        super("");
    }


    public MainPage login(String email, String password) throws InterruptedException {
        signInLinkField.click();
        loginField.setValue(email).pressEnter();

        Thread.sleep(10000);

        passwordField.setValue(password).pressEnter();

        return page(MainPage.class);
    }
}