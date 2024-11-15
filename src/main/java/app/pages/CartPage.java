package app.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {
    // Селектор для кнопки переходу до кошика
    private SelenideElement cartButton = $("#nav-cart");

    // Селектор для кнопки видалення товару з кошика
    private SelenideElement deleteButton = $("input[value='Delete']");

    // Метод для переходу до кошика
    public void goToCart() {
        cartButton.click();
    }

    // Метод для видалення першого товару з кошика
    public void removeFirstItemFromCart() {
        deleteButton.click();
    }
}
