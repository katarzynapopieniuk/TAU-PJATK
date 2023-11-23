package com.s22048;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class OrangeHrmTest implements SeleniumTest {

    private static final Logger logger = Logger.getLogger(OrangeHrmTest.class.getSimpleName());

    @Override
    public void run(WebDriver webDriver) {
        webDriver.get("https://opensource-demo.orangehrmlive.com/web/index.php");
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        logger.log(Level.INFO, "Check page title");
        assertEquals("OrangeHRM", webDriver.getTitle());

        logger.log(Level.INFO, "Try to log in with wrong login");
        logIn(webDriver, "badLogin", "admin123");
        assertFalse(isLoggedIn(webDriver));

        logger.log(Level.INFO, "Try to log in with wrong password");
        logIn(webDriver, "Admin", "badPassword");
        assertFalse(isLoggedIn(webDriver));

        logger.log(Level.INFO, "Try to log in with correct login and password");
        logIn(webDriver, "Admin", "admin123");
        assertTrue(isLoggedIn(webDriver));

        logger.log(Level.INFO, "Visit admin module");
        clickLink(webDriver, "viewAdminModule");

        logger.log(Level.INFO, "Visit job titles");
        webDriver.findElement(By.cssSelector("li.oxd-topbar-body-nav-tab:nth-child(2)")).click();
        webDriver.findElement(By.cssSelector(".oxd-dropdown-menu > li:nth-child(1) > a:nth-child(1)")).click();

        WebElement e = webDriver.findElement(By.xpath("//*[text()[contains(.,'Records Found')]]"));
        int startRecordsAmount = Integer.parseInt(StringUtils.substringBetween(e.getText(), "(", ")"));

        logger.log(Level.INFO, "Add job title");
        webDriver.findElement(By.cssSelector(".oxd-button")).click();

        webDriver.findElement(By.cssSelector("input.oxd-input:nth-child(1)")).sendKeys("aaaaa");
        webDriver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[5]/button[2]")).submit();

        webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        e = webDriver.findElement(By.xpath("//*[text()[contains(.,'Records Found')]]"));
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

    private void logIn(WebDriver webDriver, String username, String password) {
        webDriver.findElement(By.cssSelector("input[name='username']")).sendKeys(username);
        webDriver.findElement(By.cssSelector("input[type='password'][name='password']")).sendKeys(password);
        webDriver.findElement(By.cssSelector("button[type='submit']")).submit();
    }

    private boolean isLoggedIn(WebDriver webDriver) {
        try {
            webDriver.findElement(By.className("oxd-userdropdown"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private static void clickLink(WebDriver webDriver, String partialLink) {
        webDriver.findElement(By.cssSelector(String.format("a[href*='%s']", partialLink))).click();
    }
}
