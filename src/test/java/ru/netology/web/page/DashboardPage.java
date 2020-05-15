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
  private ElementsCollection transferButton = $$ ("[data-test-id='action-deposit']");
  private SelenideElement refreshButton = $("[data-test-id='action-reload']");


  public void assertHeadingIsVisible() {
    heading.shouldBe(visible);
  }

  public Integer getCurrentBalance(Integer num) {
    return Integer.decode(balanceList.get(num).getText().split("\\s+")[5]);
  }

  public SumTransfer transferMoney0102() {
    transferButton.last().click();
    return new SumTransfer();
  }

  public SumTransfer transferMoney0201() {
    transferButton.first().click();
    return new SumTransfer();
  }

}
