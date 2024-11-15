package app.pages;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage extends BasePage{
    private SelenideElement accountListField = $("#nav-link-accountList");
    private SelenideElement paymentsLinkField = $$(".ya-card__whole-card-link").findBy(Condition.text("Your Payments"));

    // Селектор для лічильника кількості товарів у кошику
    public SelenideElement cartItemCount = $("#nav-cart-count");

    public MainPage() {
        super("");
    }

    public SearchResultsPage searchProduct(String query) {
        Selenide.$(By.id("twotabsearchtextbox")).setValue(query).pressEnter();

        return Selenide.page(SearchResultsPage.class);
    }

    // Метод для переходу до сторінки "Your Payments"
    public PaymentPage goToPayments(){
        accountListField.click();
        paymentsLinkField.click();
        return Selenide.page(PaymentPage.class);
    }
}
