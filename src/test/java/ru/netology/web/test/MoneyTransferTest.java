package ru.netology.web.test;

import lombok.val;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPageV1;
import ru.netology.web.page.SumTransfer;

public class MoneyTransferTest {

    @Test
    void shouldTransferMoneyBetweenOwnCardsV1Card1() {
        val transferSum = 200;
        val authInfo = DataHelper.getAuthInfo();
        val loginPageV1 = new LoginPageV1();
        val verificationPage = LoginPageV1.validLogin(authInfo);
        val smsCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(smsCode);
        dashboardPage.assertHeadingIsVisible();
        val card1 = new DataHelper.CartInfo(dashboardPage.getCurrentBalance(0), "5559 0000 0000 0001");
        val card2 = new DataHelper.CartInfo(dashboardPage.getCurrentBalance(1), "5559 0000 0000 0002");
        val sumTransfer = dashboardPage.transferMoney0201();
        sumTransfer.executeTransfer(transferSum, card2.getCardNumber());
        val cart1Balance = dashboardPage.getCurrentBalance(0);
        val cart2Balance = dashboardPage.getCurrentBalance(1);
        assertEquals(card2.getCardBalance() - transferSum,  cart2Balance);
        assertEquals(card1.getCardBalance() + transferSum, cart1Balance);
//        Возвращаем изначальную сумму
        val sumTransferBack = dashboardPage.transferMoney0102();
        sumTransfer.clearField();
        sumTransferBack.executeTransfer(transferSum,card1.getCardNumber());
    }

    @Test
    void shouldShowErrorWhenNotEnoughMoney(){
        val transferSum = 200000;
        val authInfo = DataHelper.getAuthInfo();
        val loginPageV1 = new LoginPageV1();
        val verificationPage = LoginPageV1.validLogin(authInfo);
        val smsCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(smsCode);
        dashboardPage.assertHeadingIsVisible();
        val card1 = new DataHelper.CartInfo(dashboardPage.getCurrentBalance(0), "5559 0000 0000 0001");
        val card2 = new DataHelper.CartInfo(dashboardPage.getCurrentBalance(1), "5559 0000 0000 0002");
        val sumTransfer = dashboardPage.transferMoney0201();
        sumTransfer.executeTransfer(transferSum, card2.getCardNumber());
        dashboardPage.negativeBalanceError();
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsCard2() {
        val transferSum = 200;
        val authInfo = DataHelper.getAuthInfo();
        val loginPageV1 = new LoginPageV1();
        val verificationPage = LoginPageV1.validLogin(authInfo);
        val smsCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(smsCode);
        dashboardPage.assertHeadingIsVisible();
        val card1 = new DataHelper.CartInfo(dashboardPage.getCurrentBalance(0), "5559 0000 0000 0001");
        val card2 = new DataHelper.CartInfo(dashboardPage.getCurrentBalance(1), "5559 0000 0000 0002");
        val sumTransfer = dashboardPage.transferMoney0102();
        sumTransfer.executeTransfer(transferSum, card1.getCardNumber());
        val card1Balance = dashboardPage.getCurrentBalance(0);
        val card2Balance = dashboardPage.getCurrentBalance(1);
        assertEquals(card2.getCardBalance() + transferSum, card2Balance);
        assertEquals(card1.getCardBalance() - transferSum, card1Balance);
        //        Возвращаем изначальную сумму
        val sumTransferBack = dashboardPage.transferMoney0201();
        sumTransfer.clearField();
        sumTransferBack.executeTransfer(transferSum,card2.getCardNumber());
    }

    @Test
    void shouldCancelTransferMoneyBetweenOwnCards() {
        val transferSum = 200;
        val authInfo = DataHelper.getAuthInfo();
        val loginPageV1 = new LoginPageV1();
        val verificationPage = LoginPageV1.validLogin(authInfo);
        val smsCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(smsCode);
        dashboardPage.assertHeadingIsVisible();
        val card1 = new DataHelper.CartInfo(dashboardPage.getCurrentBalance(0), "5559 0000 0000 0001");
        val card2 = new DataHelper.CartInfo(dashboardPage.getCurrentBalance(1), "5559 0000 0000 0002");
        val sumTransfer = dashboardPage.transferMoney0102();
        sumTransfer.cancelTransfer(transferSum, card1.getCardNumber());
        assertEquals(card1.getCardBalance(), dashboardPage.getCurrentBalance(0));
    }
}
