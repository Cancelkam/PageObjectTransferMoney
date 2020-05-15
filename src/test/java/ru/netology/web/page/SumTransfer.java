package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Selenide.$;

@Data
public class SumTransfer {

    private SelenideElement sumField = $("[data-test-id=amount] .input .input__control");
    private SelenideElement fromCardField = $(".input__control[type='tel']");
    private SelenideElement transferMoneyButton = $("[data-test-id='action-transfer']");
    private SelenideElement cancelButton = $("[data-test-id='action-cancel']");


    public DashboardPage executeTransfer(int sum, String fromCard) {
        sumField.setValue(String.valueOf(sum));
        fromCardField.setValue(fromCard);
        transferMoneyButton.click();
        return new DashboardPage();
    }

    public DashboardPage cancelTransfer(int sum, String fromCard) {
        sumField.setValue(String.valueOf(sum));
        fromCardField.setValue(fromCard);
        cancelButton.click();
        return new DashboardPage();
    }

}


