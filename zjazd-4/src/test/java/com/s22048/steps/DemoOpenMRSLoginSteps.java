package com.s22048.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoOpenMRSLoginSteps {

    WebDriver webDriver;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("webdriver.edge.driver", "E:\\selenium\\edgedriver_win64\\msedgedriver.exe");
    }

    @AfterAll
    static void afterAll() {
        System.clearProperty("webdriver.edge.driver");
    }

    @Given("User is on page https:\\/\\/demo.openmrs.org\\/openmrs\\/login.htm")
    public void userIsOnPageHttpsDemoOpenmrsOrgOpenmrsLoginHtm() {
        webDriver = new EdgeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://demo.openmrs.org/openmrs/login.htm");
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @When("User logs in using valid admin credentials")
    public void userLogsInUsingValidAdminCredentials() {
        webDriver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        webDriver.findElement(By.cssSelector("input[type='password'][name='password']")).sendKeys("Admin123");
        webDriver.findElement(By.cssSelector("#Inpatient\\ Ward")).click();
        webDriver.findElement(By.cssSelector("input[type='submit']")).submit();
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Then("User is logged in as admin")
    public void userIsLoggedInAsAdmin() {
        assertEquals("admin", webDriver.findElement(By.cssSelector("li.nav-item:nth-child(1)")).getText());
        webDriver.close();
    }
}
