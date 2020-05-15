package ru.netology.web.data;

import lombok.Data;
import lombok.Value;

public class DataHelper {
  private DataHelper() {}

  @Value
  public static class AuthInfo {
    private String login;
    private String password;
  }

  public static AuthInfo getAuthInfo() {
    return new AuthInfo("vasya", "qwerty123");
  }

  public static AuthInfo getOtherAuthInfo(AuthInfo original) {
    return new AuthInfo("petya", "123qwerty");
  }

  @Value
  public static class VerificationCode {
    private String code;
  }

  public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {

    return new VerificationCode("12345");
  }

  @Data
  public static class CartInfo {
    private int cardBalance;
    private String cardNumber;

      public CartInfo(int cardBalance, String cardNumber) {
        this.cardBalance = cardBalance;
        this.cardNumber = cardNumber;
      }
  }

}
