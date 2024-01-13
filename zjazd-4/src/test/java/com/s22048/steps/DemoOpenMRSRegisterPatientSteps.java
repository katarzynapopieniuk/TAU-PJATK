package com.s22048.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoOpenMRSRegisterPatientSteps {

    private static final Pattern DATE_PATTERN = Pattern.compile("(\\d{2}\\.[A-Za-z]{3}\\.\\d{4})");
    WebDriver webDriver;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("webdriver.edge.driver", "E:\\selenium\\edgedriver_win64\\msedgedriver.exe");
    }

    @AfterAll
    static void afterAll() {
        System.clearProperty("webdriver.edge.driver");
    }
    @Given("User is on register patient panel")
    public void userIsOnRegisterPatientPanel() {
        webDriver = new EdgeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://demo.openmrs.org/openmrs/login.htm");
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        webDriver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        webDriver.findElement(By.cssSelector("input[type='password'][name='password']")).sendKeys("Admin123");
        webDriver.findElement(By.cssSelector("#Inpatient\\ Ward")).click();
        webDriver.findElement(By.cssSelector("input[type='submit']")).submit();
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @When("User provides patient data and accepts")
    public void userProvidesPatientDataAndAccepts() {
        webDriver.findElement(By.cssSelector("#referenceapplication-registrationapp-registerPatient-homepageLink-referenceapplication-registrationapp-registerPatient-homepageLink-extension")).click();

        webDriver.findElement(By.cssSelector("input[name='givenName']")).sendKeys("Testname");
        webDriver.findElement(By.cssSelector("input[name='middleName']")).sendKeys("Testmiddlename");
        webDriver.findElement(By.cssSelector("input[name='familyName']")).sendKeys("Testfamilyname");
        webDriver.findElement(By.cssSelector("#next-button")).click();

        webDriver.findElement(By.cssSelector("#gender-field > option:nth-child(1)")).click();
        webDriver.findElement(By.cssSelector("#next-button")).click();

        webDriver.findElement(By.cssSelector("#birthdateDay-field")).sendKeys("1");
        webDriver.findElement(By.cssSelector("#birthdateMonth-field")).click();
        webDriver.findElement(By.cssSelector("#birthdateMonth-field > option:nth-child(2)")).click();
        webDriver.findElement(By.cssSelector("#birthdateYear-field")).sendKeys("2000");
        webDriver.findElement(By.cssSelector("#next-button")).click();

        webDriver.findElement(By.cssSelector("#address1")).sendKeys("Testaddress");
        webDriver.findElement(By.cssSelector("#next-button")).click();
        webDriver.findElement(By.cssSelector("#next-button")).click();
        webDriver.findElement(By.cssSelector("#next-button")).click();
        webDriver.findElement(By.cssSelector("#submit")).click();

        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
    }

    @Then("Registered patient data should be equal to provided data")
    public void registeredPatientDataShouldBeEqualToProvidedData() {
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        assertEquals("Testname", webDriver.findElement(By.cssSelector(".PersonName-givenName")).getText());
        assertEquals("Testmiddlename", webDriver.findElement(By.cssSelector(".PersonName-middleName")).getText());
        assertEquals("Testfamilyname", webDriver.findElement(By.cssSelector(".PersonName-familyName")).getText());

        String gender = webDriver.findElement(By.cssSelector(".gender-age > span:nth-child(1)")).getText();
        assertEquals("Male", StringUtils.removeAll(gender, "\\s"));

        String ageBirthDate = webDriver.findElement(By.cssSelector(".gender-age > span:nth-child(2)")).getText();
        Matcher matcher = DATE_PATTERN.matcher(ageBirthDate);
        assertTrue(matcher.find());
        String birthdate = matcher.group(1);
        assertEquals("01.Jan.2000", birthdate);

        webDriver.findElement(By.cssSelector("#org\\.openmrs\\.module\\.coreapps\\.deletePatient > div:nth-child(1) > div:nth-child(2)")).click();
        webDriver.findElement(By.cssSelector("#delete-reason")).sendKeys("delete reason");
        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        webDriver.findElement(By.cssSelector("button.confirm:nth-child(7)")).click();
        webDriver.close();
    }
}
