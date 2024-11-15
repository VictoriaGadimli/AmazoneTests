package app.pages;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Selenide.*;

public class SearchResultsPage {
    // Селектори для елементів сортування
    private SelenideElement sortDropdownLabelField = $("span.a-dropdown-label");
    private ElementsCollection sortOptionsField = $$(".a-dropdown-item");
    private  ElementsCollection pricesField = $$("span.a-price-whole");

    // Колекція всіх результатів пошуку
    private ElementsCollection searchResults = $$("span.a-size-medium.a-color-base.a-text-normal");

    // Метод для відкриття меню сортування
    public void openSortDropdown() {

        sortDropdownLabelField.click();
    }

    // Метод для вибору сортування за ціною (від низької до високої)
    public ElementsCollection selectSortByPriceLowToHigh() {
        sortOptionsField.findBy(Condition.text("Price: Low to High")).click();
        return pricesField;
    }

    public void selectFirstProduct() {

        searchResults.first().click();
    }

    public ElementsCollection getProductCollection() {
        return Selenide.$$("div.s-main-slot div.s-result-item");
    }
    public ElementsCollection getAddCartButtonCollection() {
        return Selenide.$$("button.a-button-text");
    }

    public ElementsCollection getAdd() {
        return Selenide.$$("div.a-fixed-left-grid-inner");
    }
}