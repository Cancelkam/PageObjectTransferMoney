package ru.netology.web.test;

import lombok.val;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPageV1;

public class MoneyTransferTest {

    @Test
    void shouldTransferMoneyBetweenOwnCardsV1Card1() {
        val authInfo = DataHelper.getAuthInfo();
        val loginPageV1 = new LoginPageV1();
        val verificationPage = LoginPageV1.validLogin(authInfo);
        val smsCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(smsCode);
        dashboardPage.assertHeadingIsVisible();
        val cart1 = new DataHelper.CartInfo(dashboardPage.getCurrentBalance(0), "5559 0000 0000 0001");
        val cart2 = new DataHelper.CartInfo(dashboardPage.getCurrentBalance(1), "5559 0000 0000 0002");
        val sumTransfer = dashboardPage.transferMoney0102();
        sumTransfer.executeTransfer(200, cart1.getCardNumber());
        assertEquals(cart1.getCardBalance() - 200, dashboardPage.getCurrentBalance(0));
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsCard2() {
        val authInfo = DataHelper.getAuthInfo();
        val loginPageV1 = new LoginPageV1();
        val verificationPage = LoginPageV1.validLogin(authInfo);
        val smsCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(smsCode);
        dashboardPage.assertHeadingIsVisible();
        val cart1 = new DataHelper.CartInfo(dashboardPage.getCurrentBalance(0), "5559 0000 0000 0001");
        val cart2 = new DataHelper.CartInfo(dashboardPage.getCurrentBalance(1), "5559 0000 0000 0002");
        val sumTransfer = dashboardPage.transferMoney0201();
        sumTransfer.executeTransfer(200, cart2.getCardNumber());
        assertEquals(cart2.getCardBalance() - 200, dashboardPage.getCurrentBalance(1));
    }

    @Test
    void shouldCancelTransferMoneyBetweenOwnCards() {
        val authInfo = DataHelper.getAuthInfo();
        val loginPageV1 = new LoginPageV1();
        val verificationPage = LoginPageV1.validLogin(authInfo);
        val smsCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(smsCode);
        dashboardPage.assertHeadingIsVisible();
        val cart1 = new DataHelper.CartInfo(dashboardPage.getCurrentBalance(0), "5559 0000 0000 0001");
        val cart2 = new DataHelper.CartInfo(dashboardPage.getCurrentBalance(1), "5559 0000 0000 0002");
        val sumTransfer = dashboardPage.transferMoney0102();
        sumTransfer.cancelTransfer(200, cart1.getCardNumber());
        assertEquals(cart1.getCardBalance(), dashboardPage.getCurrentBalance(0));
    }
}
