package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentMethod {
    private SelenideElement buy = $$("button").findBy(exactText("Купить"));
    private SelenideElement buyOnCredit = $$("button").findBy(exactText("Купить в кредит"));

    public VerificationPage buyButton() {
        buy.click();
        return new VerificationPage();
    }

    public VerificationPage buyOnCreditButton() {
        buyOnCredit.click();
        return new VerificationPage();
    }
}
