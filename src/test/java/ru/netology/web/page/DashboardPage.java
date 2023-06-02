package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
  private ElementsCollection cards = $$(".list__item div");
  private final String balanceStart = "баланс: ";
  private final String balanceFinish = " р.";

  public DashboardPage() {
  }

  public int getCardBalance(int id) {
    val text = cards.get(id).text();
    return extractBalance(text);
  }

  private int extractBalance(String text) {
    val start = text.indexOf(balanceStart);
    val finish = text.indexOf(balanceFinish);
    val value = text.substring(start + balanceStart.length(), finish);
    return Integer.parseInt(value);
  }

  public DashboardPage transferMoneyFirstCard(int sum) {
    $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button").click();
    $("[data-test-id='amount'] input").setValue(String.valueOf(sum));
    $("[data-test-id='from'] input").setValue("5559 0000 0000 0002");
    $("[data-test-id='action-transfer']").click();
    return new DashboardPage();
  }
  public DashboardPage transferMoneySecondCard(int sum) {
    $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button").click();
    $("[data-test-id='amount'] input").setValue(String.valueOf(sum));
    $("[data-test-id='from'] input").setValue("5559 0000 0000 0001");
    $("[data-test-id='action-transfer']").click();
    return new DashboardPage();
  }
}
