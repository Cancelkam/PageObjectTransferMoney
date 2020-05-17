package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Data
public class DashboardPage {
  private SelenideElement heading = $("[data-test-id=dashboard]");
  private ElementsCollection balanceList = $$(".list__item div");
  private SelenideElement transferButton0201 = $ ("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] .button");
  private SelenideElement transferButton0102 = $ ("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] .button");
  private SelenideElement refreshButton = $ ("[data-test-id='action-reload']");
  private SelenideElement errorNotification = $("[data-test-id='error-notification']");

  public void assertHeadingIsVisible() {
    heading.shouldBe(visible);
  }

  public Integer getCurrentBalance(Integer num) {
    return Integer.decode(balanceList.get(num).getText().split("\\s+")[5]);
  }

  public SumTransfer transferMoney0201() {
    transferButton0201.click();
    return new SumTransfer();
  }

  public SumTransfer transferMoney0102() {
    transferButton0102.click();
    return new SumTransfer();
  }

  public void negativeBalanceError() {
    errorNotification.shouldBe(visible);
  }

}
