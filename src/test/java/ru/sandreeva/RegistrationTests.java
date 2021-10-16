package ru.sandreeva;


import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ru.sandreeva.TestData.firstName;
import static ru.sandreeva.TestData.lastName;

public class RegistrationTests extends TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void successfulRegistrationTest() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        $("#firstName").val(firstName);
        $("#lastName").val(lastName);
        $("#userEmail").val("alex@egorov.com");
        $("[name=gender][value=Other]").parent().click();

        $("#userNumber").val("1231231231");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("2005");
        $(".react-datepicker__day--028:not(.react-datepicker__day--outside-month)").click();

        $("#subjectsInput").val("Math").pressEnter();
        $("#state").scrollIntoView(true);
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#currentAddress").val("Qa guru street 7");


        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();

        $("#submit").click();

        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Alex" + " Egorov"), text("alex@egorov.com"));
        $(".table-responsive").$(byText("Student Name")).parent()
                .shouldHave(text(firstName + " " + lastName));
        $x("//td[text()='Student Name']").parent()
                .shouldHave(text(firstName + " " + lastName));

    }
}
