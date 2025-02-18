package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MockCard {
        private String cardNumber;
        private String month;
        private String year;
        private String cardHolder;
        private String cvv;
    }

    public static String getCurrentMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getCurrentYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateRandomCVV() {
        int cvv = new Random().nextInt(999) + 1;
        return String.format("%03d", cvv);
    }

    public static String getYearPlusSix() {
        return LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getPreviousYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static MockCard approvedCard() {
        Faker faker = new Faker();
        String month = getCurrentMonth();
        String year = getCurrentYear();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String cvv = generateRandomCVV();
        return new MockCard("4444444444444441", month, year, holder, cvv);
    }

    public static MockCard declinedCard() {
        Faker faker = new Faker();
        String month = getCurrentMonth();
        String year = getCurrentYear();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String cvv = generateRandomCVV();
        return new MockCard("4444444444444442", month, year, holder, cvv);
    }

    public static MockCard emptyCardNumberField() {
        Faker faker = new Faker();
        String month = getCurrentMonth();
        String year = getCurrentYear();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String cvv = generateRandomCVV();
        return new MockCard("", month, year, holder, cvv);
    }

    public static MockCard emptyFieldMonth() {
        Faker faker = new Faker();
        String month = getCurrentMonth();
        String year = getCurrentYear();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String cvv = generateRandomCVV();
        return new MockCard("4444444444444441", "", year, holder, cvv);
    }

    public static MockCard emptyFieldYear() {
        Faker faker = new Faker();
        String month = getCurrentMonth();
        String year = getCurrentYear();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String cvv = generateRandomCVV();
        return new MockCard("4444444444444441", month, "", holder, cvv);
    }

    public static MockCard emptyFieldOwner() {
        Faker faker = new Faker();
        String month = getCurrentMonth();
        String year = getCurrentYear();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String cvv = generateRandomCVV();
        return new MockCard("4444444444444441", month, year, "", cvv);
    }

    public static MockCard emptyFieldSVV() {
        Faker faker = new Faker();
        String month = getCurrentMonth();
        String year = getCurrentYear();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String cvv = generateRandomCVV();
        return new MockCard("4444444444444441", month, year, holder, "");
    }

    public static MockCard inTheMonthFieldTheValueIsZero() {
        Faker faker = new Faker();
        String month = getCurrentMonth();
        String year = getCurrentYear();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String cvv = generateRandomCVV();
        return new MockCard("4444444444444441", "00", year, holder, cvv);
    }

    public static MockCard fieldMonthWithValueThirteen() {
        Faker faker = new Faker();
        String month = getCurrentMonth();
        String year = getCurrentYear();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String cvv = generateRandomCVV();
        return new MockCard("4444444444444441", "13", year, holder, cvv);
    }

    public static MockCard expiredCard() {
        Faker faker = new Faker();
        String month = getCurrentMonth();
        String year = getPreviousYear();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String cvv = generateRandomCVV();
        return new MockCard("4444444444444441", month, year, holder, cvv);
    }

    public static MockCard yearFromTheFuture() {
        Faker faker = new Faker();
        String month = getCurrentMonth();
        String year = getYearPlusSix();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String cvv = generateRandomCVV();
        return new MockCard("4444444444444441", month, year, holder, cvv);
    }

    public static MockCard randomNumbersInTheOwnerField() {
        String month = getCurrentMonth();
        String year = getCurrentYear();
        String holder = generateRandomCVV();
        String cvv = generateRandomCVV();
        return new MockCard("4444444444444441", month, year, holder, cvv);
    }

    public static MockCard firstAndLastNameInRussian() {
        Faker faker = new Faker(new Locale("ru"));
        String month = getCurrentMonth();
        String year = getCurrentYear();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String cvv = generateRandomCVV();
        return new MockCard("4444444444444441", month, year, holder, cvv);
    }

    public static MockCard specialCharactersOnTheOwnerField() {
        Faker faker = new Faker();
        String month = getCurrentMonth();
        String year = getCurrentYear();
        String  holder = faker.regexify("[!@#$%^&*()№;:?<>]{5}");
        String cvv = generateRandomCVV();
        return new MockCard("4444444444444441", month, year, holder, cvv);
    }

    public static MockCard oneLetterInTheOwnerField() {
        Faker faker = new Faker();
        String month = getCurrentMonth();
        String year = getCurrentYear();
        String holder = faker.letterify("?");
        String cvv = generateRandomCVV();
        return new MockCard("4444444444444441", month, year, holder, cvv);
    }

    public static MockCard oneDigitInTheSvvField() {
        Faker faker = new Faker();
        String month = getCurrentMonth();
        String year = getCurrentYear();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String cvv = String.valueOf(faker.number().numberBetween(1, 10));
        return new MockCard("4444444444444441", month, year, holder, cvv);
    }

    public static MockCard twoDigitsInTheSvvField() {
        Faker faker = new Faker();
        String month = getCurrentMonth();
        String year = getCurrentYear();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String cvv = String.format("%02d", faker.number().numberBetween(0, 100));
        return new MockCard("4444444444444441", month, year, holder, cvv);
    }
}
