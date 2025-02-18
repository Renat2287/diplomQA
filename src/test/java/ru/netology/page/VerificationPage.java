package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class VerificationPage {

    private SelenideElement cardNumberInputField = $$("input.input__control").get(0);
    private SelenideElement monthNumberInputField = $$("input.input__control").get(1);
    private SelenideElement yearNumberInputField = $$("input.input__control").get(2);
    private SelenideElement ownerInputField = $$("input.input__control").get(3);
    private SelenideElement inputFieldSvv = $$("input.input__control").get(4);
    private SelenideElement invalidFormat = $(byText("Неверный формат"));
    private SelenideElement expiredCard = $(byText("Истёк срок действия карты"));
    private SelenideElement invalidCardExpirationDate = $(byText("Неверно указан срок действия карты"));
    private SelenideElement requiredField = $(byText("Поле обязательно для заполнения"));
    private SelenideElement bankRefusal = $(byText("Ошибка! Банк отказал в проведении операции."));
    private SelenideElement bankApproval = $(byText("Операция одобрена Банком."));
    private SelenideElement continueButton = $$("button").findBy(exactText("Продолжить"));

    public void inputData(DataHelper.MockCard card) {
        cardNumberInputField.setValue(card.getCardNumber());
        monthNumberInputField.setValue(card.getMonth());
        yearNumberInputField.setValue(card.getYear());
        ownerInputField.setValue(card.getCardHolder());
        inputFieldSvv.setValue(card.getCvv());
        continueButton.click();
    }

    public void operationApproved() {
        bankApproval.shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Операция одобрена Банком."));
    }

    public void operationDenied() {
        bankRefusal.shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Ошибка! Банк отказал в проведении операции."));
    }

    public void incorrectInput() {
        invalidFormat.shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Неверный формат"));
    }

    public void emptyField() {
        requiredField.shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    public void oldMap() {
        expiredCard.shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Истёк срок действия карты"));
    }

    public void cardExpirationDateIsInvalid() {
        invalidCardExpirationDate.shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Неверно указан срок действия карты"));
    }
}
