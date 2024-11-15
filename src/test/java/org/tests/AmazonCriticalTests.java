package org.tests;

import app.AppConfig;
import app.pages.SearchResultsPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

public class AmazonCriticalTests extends BaseTest {

    @Test
    @Description("Verify that a user can successfully log in with valid credentials.")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyUserCanLoginSuccessfully() throws InterruptedException {
        app.loginPage.open();
        app.loginPage.login(AppConfig.userEmail, AppConfig.userPassword);

        app.loginPage.accountListElement.shouldHave(Condition.text("Hello, Victoria"));
    }

    @Test
    @Description("Verify that searching for a product displays relevant search results.")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyProductSearchDisplaysResults() {
        app.mainPage.open();
        var resultsPage = app.mainPage.searchProduct("laptop");
        resultsPage.getProductCollection().shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

    @Test
    @Description("Verify that searching for a product displays relevant search results.")
    @Severity(SeverityLevel.CRITICAL)
    public void addTwoProductsToCartTest() throws InterruptedException {
        app.loginPage.open();
        var mainPage = app.loginPage.login(AppConfig.userEmail, AppConfig.userPassword);

        // Додати 2 товари в корзину
        String[] productNames = {"macbook", "ipad"};
        for (int i = 0; i < productNames.length; i++) {
            SearchResultsPage resultsPage = mainPage.searchProduct(productNames[i]);
            resultsPage.getAddCartButtonCollection().findBy(Condition.text("Add to Cart")).click();
        }

        // Перевірка, що обидва товари в кошику
        mainPage.cartItemCount.shouldHave(Condition.text(String.valueOf(productNames.length)));
    }

    @Test
    @Description("Add a new payment card to the account and verify that it is successfully added.")
    @Severity(SeverityLevel.CRITICAL)
    public void addPaymentCardTest() throws InterruptedException {
        app.loginPage.open();
        var mainPage = app.loginPage.login(AppConfig.userEmail, AppConfig.userPassword);

        // Перейти до сторінки "Your Payments"
        var paymentPage = mainPage.goToPayments();

        // Почати додавання нової карти
        paymentPage.startAddingNewCard();

        // Ввести дані карти
        paymentPage.enterCardDetails("4040 0000 0000 0000", "Victoria Test", "07", "2027", "123");
        Thread.sleep(3000);

        // Зберегти карту
        paymentPage.saveCard();

        // Перевірити, що карта успішно додана
        paymentPage.verifyCardAddedSuccessfully();
    }
}
