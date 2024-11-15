package org.tests;

import app.AppConfig;
import app.pages.CartPage;
import app.pages.WishlistPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AmazonExtendedTests extends BaseTest {

    @Test
    @Description("Verify that products are sorted by price from low to high.")
    @Severity(SeverityLevel.NORMAL)
    public void verifySortProductsByPriceLowToHigh() throws InterruptedException {
        app.mainPage.open();
        var resultsPage = app.mainPage.searchProduct("laptop");
        Thread.sleep(2000);

        resultsPage.openSortDropdown();
        ElementsCollection prices = resultsPage.selectSortByPriceLowToHigh();

        // Перевірка, що товари відсортовані за зростанням ціни
        for (int i = 0; i < prices.size() - 1; i++) {
            String price1Text = prices.get(i).getText().replace(",", "");
            String price2Text = prices.get(i + 1).getText().replace(",", "");

            if (!price1Text.isEmpty() && !price2Text.isEmpty()) {
                int price1 = Integer.parseInt(price1Text);
                int price2 = Integer.parseInt(price2Text);

                // Переконатися, що кожна наступна ціна не менша за попередню
                assertTrue(price1 <= price2, "Сортування за зростанням ціни працює некоректно");
            }
        }
    }

    @Test
    @Description("Add a product to the wishlist and verify that it is successfully added.")
    @Severity(SeverityLevel.MINOR)
    public void addProductToWishlistTest() throws InterruptedException {
        app.loginPage.open();
        var mainPage = app.loginPage.login(AppConfig.userEmail, AppConfig.userPassword);

        // Пошук товару
        var resultsPage = mainPage.searchProduct("macbook pro");

        resultsPage.selectFirstProduct();

        WishlistPage wishlistPage = new WishlistPage();
        wishlistPage.addToUserWishlist();
        wishlistPage.viewWishlist();

        Thread.sleep(3000);
        // Перевірка повідомлення про успішне додавання до списку побажан
        wishlistPage.verifyWishlistConfirmation();
    }

    @Test
    @Description("Remove one product from the cart and verify that only one item remains.")
    @Severity(SeverityLevel.NORMAL)
    public void removeOneProductFromCartTest() throws InterruptedException {
        app.loginPage.open();
        var mainPage = app.loginPage.login(AppConfig.userEmail, AppConfig.userPassword);

        // Перед цим тестом переконайтеся, що в кошику є 2 товари
        CartPage cartPage = new CartPage();
        cartPage.goToCart();
        cartPage.removeFirstItemFromCart();

        // Перевірити, що в кошику залишився один товар
        mainPage.cartItemCount.shouldHave(Condition.text(String.valueOf(1)));
    }

    @Test
    @Description("Remove a payment card from the account and verify that it is successfully removed.")
    @Severity(SeverityLevel.NORMAL)
    public void removePaymentCardTest() throws InterruptedException {
        app.loginPage.open();
        var mainPage = app.loginPage.login(AppConfig.userEmail, AppConfig.userPassword);

        // Перейти до сторінки "Your Payments"
        var paymentPage = mainPage.goToPayments();

        // Вибрати карту для видалення
        paymentPage.selectCardForDeletion();

        // Підтвердити видалення карти
        paymentPage.confirmDeletion();

        // Остаточно підтвердити видалення карти
        paymentPage.confirmFinalDeletion();

        // Перевірка, що карта успішно видалена
        paymentPage.verifyCardRemovedSuccessfully();
    }
}

