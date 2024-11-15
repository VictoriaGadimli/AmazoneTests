package app;

import app.pages.*;

public class App {

    public MainPage mainPage;
    public LoginPage loginPage;

    public WishlistPage wishlistPage;

    public CartPage cartPage;

    public PaymentPage paymentPage;

    public App() {
        mainPage = PageBuilder.buildMainPage();
        loginPage = PageBuilder.buildLoginPage();
        wishlistPage = PageBuilder.buildWishlistPage();
        cartPage = PageBuilder.buildCartPage();
        paymentPage = PageBuilder.buildPaymentPage();
    }
}