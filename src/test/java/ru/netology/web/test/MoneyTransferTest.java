package ru.netology.web.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPageV1;

import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {
  @Test
  void shouldTransferMoneyFromSecondToFirstCard() {
    open("http://localhost:9999");
    var loginPage = new LoginPageV1();
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    var dashboardPage = verificationPage.validVerify(verificationCode);
    dashboardPage.transferMoneyFirstCard(200);
    int balance1 = dashboardPage.getCardBalance(0);
    int expected1 = 10200;
    int actual1 = balance1;
    Assertions.assertEquals(expected1, actual1);
    int balance2 = dashboardPage.getCardBalance(1);
    int expected2 = 9800;
    int actual2 = balance2;
    Assertions.assertEquals(expected2, actual2);
  }

  @Test
  void shouldTransferMoneyFromFirstToSecondCard() {
    open("http://localhost:9999");
    var loginPage = new LoginPageV1();
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    var dashboardPage = verificationPage.validVerify(verificationCode);
    dashboardPage.transferMoneySecondCard(400);
    int balance1 = dashboardPage.getCardBalance(0);
    int expected1 = 9800;
    int actual1 = balance1;
    Assertions.assertEquals(expected1, actual1);
    int balance2 = dashboardPage.getCardBalance(1);
    int expected2 = 10200;
    int actual2 = balance2;
    Assertions.assertEquals(expected2, actual2);
  }
}
