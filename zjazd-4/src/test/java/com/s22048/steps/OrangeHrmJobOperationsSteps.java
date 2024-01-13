package com.s22048.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrangeHrmJobOperationsSteps {

    private static final Logger logger = Logger.getLogger(OrangeHrmJobOperationsSteps.class.getSimpleName());

    WebDriver webDriver;

    int startRecordsAmount;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("webdriver.edge.driver", "E:\\selenium\\edgedriver_win64\\msedgedriver.exe");
    }

    @AfterAll
    static void afterAll() {
        System.clearProperty("webdriver.edge.driver");
    }

    @Given("User is logged on OrangeHrm page")
    public void userIsLoggedOnOrangeHrmPage() {
        webDriver = new EdgeDriver();
        webDriver.get("https://opensource-demo.orangehrmlive.com/web/index.php");
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        logger.log(Level.INFO, "Logging in");
        webDriver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        webDriver.findElement(By.cssSelector("input[type='password'][name='password']")).sendKeys("admin123");
        webDriver.findElement(By.cssSelector("button[type='submit']")).submit();

        logger.log(Level.INFO, "Visit admin module");
        webDriver.findElement(By.cssSelector(String.format("a[href*='%s']", "viewAdminModule"))).click();

        logger.log(Level.INFO, "Visit job titles");
        webDriver.findElement(By.cssSelector("li.oxd-topbar-body-nav-tab:nth-child(2)")).click();
        webDriver.findElement(By.cssSelector(".oxd-dropdown-menu > li:nth-child(1) > a:nth-child(1)")).click();

        WebElement e = webDriver.findElement(By.xpath("//*[text()[contains(.,'Records Found')]]"));
        startRecordsAmount = Integer.parseInt(StringUtils.substringBetween(e.getText(), "(", ")"));
    }

    @When("User adds a job title")
    public void userAddsAJobTitle() {
        logger.log(Level.INFO, "Add job title");
        webDriver.findElement(By.cssSelector(".oxd-button")).click();

        webDriver.findElement(By.cssSelector("input.oxd-input:nth-child(1)")).sendKeys("aaaaa");
        webDriver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[5]/button[2]")).submit();

        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
    }

    @Then("Job title is added and can be removed")
    public void jobTitleIsAddedAndCanBeRemoved() {
        WebElement e = webDriver.findElement(By.xpath("//*[text()[contains(.,'Records Found')]]"));
        int afterAddingNewTitleRecordsAmount = Integer.parseInt(StringUtils.substringBetween(e.getText(), "(", ")"));

        logger.log(Level.INFO, "Remove job title");
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[3]/div/div/div[1]/div/div/div[1]/div[2]/div/div/button[1]")).click();
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/div[3]/div/div/div/div[3]/button[2]")).click();

        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        e = webDriver.findElement(By.xpath("//*[text()[contains(.,'Records Found')]]"));
        int afterRemoveTitleRecordsAmount = Integer.parseInt(StringUtils.substringBetween(e.getText(), "(", ")"));

        webDriver.close();

        assertEquals(startRecordsAmount + 1, afterAddingNewTitleRecordsAmount);
        assertEquals(startRecordsAmount, afterRemoveTitleRecordsAmount);
    }
}
