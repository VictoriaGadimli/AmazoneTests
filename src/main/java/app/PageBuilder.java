package app;

import app.pages.*;

public class PageBuilder {

    public static MainPage buildMainPage() {
        return new MainPage();
    }

    public static LoginPage buildLoginPage() {
        return new LoginPage();
    }
    public static WishlistPage buildWishlistPage() {
        return new WishlistPage();
    }

    public static CartPage buildCartPage() {
        return new CartPage();
    }

    public static PaymentPage buildPaymentPage(){
        return new PaymentPage();
    }
}