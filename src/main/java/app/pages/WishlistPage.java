package app.pages;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class WishlistPage {

    private SelenideElement addToWishlistButton = $("#add-to-wishlist-button-submit");

    public void addToUserWishlist() {
        addToWishlistButton.should(Condition.appear).click();
    }

    private SelenideElement viewYourListLink = $(By.linkText("View Your List"));

    public void viewWishlist() {
        viewYourListLink.click();
    }

    // Селектор для повідомлення про успішне додавання до списку побажань
    private ElementsCollection wishlistConfirmationCollection = $$("div.a-fixed-left-grid-inner");

    // Метод для перевірки, що повідомлення про додавання з'явилося
    public void verifyWishlistConfirmation() {
        wishlistConfirmationCollection.shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

}
