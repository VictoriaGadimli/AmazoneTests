package app.pages;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class PaymentPage {
    private SelenideElement addCardButton = $("input.a-button-input");
    private SelenideElement addCreditOrDebitCardButton = $$("input.a-button-input").get(3);
    private SelenideElement cardNumberField = $("input[type='tel']");
    private SelenideElement cardNameField = $("input[type='text']");
    private ElementsCollection dropdowns = $$("span[data-action='a-dropdown-button']");
    private SelenideElement monthDropdownOptions = $$("ul[role='listbox'] .a-dropdown-link").get(7);
    private SelenideElement yearDropdownOptions = $$("ul[role='listbox'] .a-dropdown-link").findBy(Condition.text("2027"));
    private SelenideElement cvvField = $("input[type='password']");
    private SelenideElement addCreditCardButton = $(By.name("ppw-widgetEvent:AddCreditCardEvent"));
    private SelenideElement saveCardButton = $(By.name("ppw-widgetEvent:SavePaymentMethodDetailsEvent"));
    private SelenideElement successMessageCardAddedSuccessfully = $("span.a-list-item span.a-color-success");

    //removePaymentCardTest
    private SelenideElement firstCard = $$("div.a-fixed-left-grid-col").get(12);  // Знайти карту для видалення

    private SelenideElement buttonRemoveFromWallet = $("input.apx-remove-link-button"); // Кнопки для першого видалення
    private ElementsCollection deleteButtons = $$("input[type='submit']");  // Кнопки для підтвердження видалення
    private SelenideElement successMessageCardRemovedSuccessfully = $("span.a-list-item span.a-color-success");


    // Метод для початку додавання нової карти
    public void startAddingNewCard(){
        addCardButton.click();
        addCreditOrDebitCardButton.click();
    }

    // Метод для введення даних карти
    public void enterCardDetails(String cardNumber, String cardName, String expirationMonth, String expirationYear, String cvv) {
        switchTo().frame(0);
        cardNumberField.setValue(cardNumber);
        cardNameField.setValue(cardName);

        // Вибір місяця закінчення
        dropdowns.get(0).click();
        monthDropdownOptions.click();

        // Вибір року закінчення
        dropdowns.get(1).click();
        yearDropdownOptions.click();

        // Введення CVV
        cvvField.setValue(cvv);

        // Натиснути кнопку додавання карти
        addCreditCardButton.click();
    }

    // Метод для збереження карти
    public void saveCard() {
        saveCardButton.click();
        switchTo().defaultContent();
    }

    // Метод для перевірки успішного додавання карти
    public void verifyCardAddedSuccessfully() {
        successMessageCardAddedSuccessfully.should(Condition.appear).shouldHave(Condition.text("Payment method added"));
    }

    // Метод для вибору карти для видалення
    public void selectCardForDeletion() throws InterruptedException {
        firstCard.click();
        Thread.sleep(3000);
    }

    // Метод для підтвердження видалення карти (перше підтвердження)
    public void confirmDeletion() throws InterruptedException {
        buttonRemoveFromWallet.click();
        Thread.sleep(3000);
    }

    // Метод для остаточного підтвердження видалення
    public void confirmFinalDeletion() {
        deleteButtons.get(5).click();
    }

    // Метод для перевірки, що карта успішно видалена
    public void verifyCardRemovedSuccessfully() {
        successMessageCardRemovedSuccessfully.should(Condition.appear).shouldHave(Condition.matchText("Your payment method has been removed successfully."));
    }
}
