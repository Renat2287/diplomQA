package ru.netology.test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.PaymentMethod;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyATourAndBuyATourOnCreditTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        Selenide.open("http://localhost:8080");
    }

    @AfterEach
    public void cleanBase() {
        SQLHelper.cleanDatabase();
    }

    @Test
    void successfulPurchaseByCard() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyButton();
        payment.inputData(DataHelper.approvedCard());
        payment.operationApproved();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }

    @Test
    void successfulPurchaseOnCredit() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyOnCreditButton();
        payment.inputData(DataHelper.approvedCard());
        payment.operationApproved();
        assertEquals("APPROVED", SQLHelper.getCreditRequestStatus());
    }

    @Test
    void buyingATourWithACardWithADeclinedCard() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyButton();
        payment.inputData(DataHelper.declinedCard());
        payment.operationDenied();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
    }

    @Test
    void buyingATourOnCreditWithADeclinedCard() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyOnCreditButton();
        payment.inputData(DataHelper.declinedCard());
        payment.operationDenied();
        assertEquals("DECLINED", SQLHelper.getCreditRequestStatus());
    }

    @Test
    void purchaseOfATourByCardWithAnEmptyFieldCardNumber() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyButton();
        payment.inputData(DataHelper.emptyCardNumberField());
        payment.incorrectInput();
    }

    @Test
    void purchaseOfATourOnCreditWithAnEmptyFieldCardNumber() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyOnCreditButton();
        payment.inputData(DataHelper.emptyCardNumberField());
        payment.incorrectInput();
    }

    @Test
    void buyingATourUsingACardWithAnEmptyMonthField() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyButton();
        payment.inputData(DataHelper.emptyFieldMonth());
        payment.incorrectInput();
    }

    @Test
    void purchaseOfATourOnCreditWithAnEmptyFieldMonth() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyOnCreditButton();
        payment.inputData(DataHelper.emptyFieldMonth());
        payment.incorrectInput();
    }

    @Test
    void buyingATourUsingACardWithAnEmptyFieldYear() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyButton();
        payment.inputData(DataHelper.emptyFieldYear());
        payment.incorrectInput();
    }

    @Test
    void purchaseOfATourOnCreditWithAnEmptyFieldYear() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyOnCreditButton();
        payment.inputData(DataHelper.emptyFieldYear());
        payment.incorrectInput();
    }

    @Test
    void purchaseOfATourOnACardWithAnEmptyFieldOwner() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyButton();
        payment.inputData(DataHelper.emptyFieldOwner());
        payment.emptyField();
    }

    @Test
    void purchaseOfATourOnCreditWithAnEmptyOwnerField() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyOnCreditButton();
        payment.inputData(DataHelper.emptyFieldOwner());
        payment.emptyField();
    }

    @Test
    void purchasingATourUsingACardWithAnEmptyCVVField() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyButton();
        payment.inputData(DataHelper.emptyFieldSVV());
        payment.incorrectInput();
    }

    @Test
    void purchaseOfATourOnCreditWithAnEmptyCVVField() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyOnCreditButton();
        payment.inputData(DataHelper.emptyFieldSVV());
        payment.incorrectInput();
    }

    @Test
    void buyingATourUsingACardWithAMonthValueOfZero() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyButton();
        payment.inputData(DataHelper.inTheMonthFieldTheValueIsZero());
        payment.cardExpirationDateIsInvalid();
    }

    @Test
    void purchaseOfATourOnCreditWithAMonthValueOfZero() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyOnCreditButton();
        payment.inputData(DataHelper.inTheMonthFieldTheValueIsZero());
        payment.cardExpirationDateIsInvalid();
    }

    @Test
    void purchaseOfTourByCardWithMonthValueThirteen() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyButton();
        payment.inputData(DataHelper.fieldMonthWithValueThirteen());
        payment.cardExpirationDateIsInvalid();
    }

    @Test
    void purchaseOfATourOnCreditWithTheValueOfTheMonthThirteen() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyOnCreditButton();
        payment.inputData(DataHelper.fieldMonthWithValueThirteen());
        payment.cardExpirationDateIsInvalid();
    }

    @Test
    void buyingATourWithAnExpiredCard() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyButton();
        payment.inputData(DataHelper.expiredCard());
        payment.oldMap();
    }

    @Test
    void buyingATourOnCreditWithAnExpiredCard() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyOnCreditButton();
        payment.inputData(DataHelper.expiredCard());
        payment.oldMap();
    }

    @Test
    void purchaseOfATourUsingACardWithTheWrongYearOfIssue() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyButton();
        payment.inputData(DataHelper.yearFromTheFuture());
        payment.cardExpirationDateIsInvalid();
    }

    @Test
    void purchaseOfATourOnCreditWithTheWrongYearOfIssue() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyOnCreditButton();
        payment.inputData(DataHelper.yearFromTheFuture());
        payment.cardExpirationDateIsInvalid();
    }

    @Test
    void buyingATourOnAMapWithWaterInTheOwnerOfNumbersField() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyButton();
        payment.inputData(DataHelper.randomNumbersInTheOwnerField());
        payment.incorrectInput();
    }

    @Test
    void purchaseOfATourOnCreditWithWaterInTheOwnerOfNumbersField() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyOnCreditButton();
        payment.inputData(DataHelper.randomNumbersInTheOwnerField());
        payment.incorrectInput();
    }

    @Test
    void buyingATourOnAMapWithWaterInTheFieldOwnerOfCyrillicLetters() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyButton();
        payment.inputData(DataHelper.firstAndLastNameInRussian());
        payment.incorrectInput();
    }

    @Test
    void purchaseOfATourOnCreditWithWaterInTheFieldOwnerOfCyrillicLetters() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyOnCreditButton();
        payment.inputData(DataHelper.firstAndLastNameInRussian());
        payment.incorrectInput();
    }

    @Test
    void purchaseOfATourOnAMapWithWaterInTheFieldOwnerOfSpecialCharacters() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyButton();
        payment.inputData(DataHelper.specialCharactersOnTheOwnerField());
        payment.incorrectInput();
    }

    @Test
    void purchaseOfATourOnCreditWithWaterInTheOwnerOfSpecialCharactersField() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyOnCreditButton();
        payment.inputData(DataHelper.specialCharactersOnTheOwnerField());
        payment.incorrectInput();
    }

    @Test
    void buyingATourOnAMapWithWaterInTheOwnerOfOneLetterField() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyButton();
        payment.inputData(DataHelper.oneLetterInTheOwnerField());
        payment.incorrectInput();
    }

    @Test
    void purchaseOfATourOnCreditWithWaterInTheOwnerOfOneLetterField() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyOnCreditButton();
        payment.inputData(DataHelper.oneLetterInTheOwnerField());
        payment.incorrectInput();
    }

    @Test
    void purchaseOfATourByCardWithWaterInTheSVVFieldOfOneDigit() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyButton();
        payment.inputData(DataHelper.oneDigitInTheSvvField());
        payment.incorrectInput();
    }

    @Test
    void purchaseOfATourOnCreditWithWaterInTheSVVFieldOfOneDigit() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyOnCreditButton();
        payment.inputData(DataHelper.oneDigitInTheSvvField());
        payment.incorrectInput();
    }

    @Test
    void purchaseOfATourByCardWithWaterInTheSVVFieldOfTwoDigits() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyButton();
        payment.inputData(DataHelper.twoDigitsInTheSvvField());
        payment.incorrectInput();
    }

    @Test
    void purchaseOfATourOnCreditWithWaterInTheSVVFieldOfTwoDigits() {
        var startPage = new PaymentMethod();
        var payment = startPage.buyOnCreditButton();
        payment.inputData(DataHelper.twoDigitsInTheSvvField());
        payment.incorrectInput();
    }
}
