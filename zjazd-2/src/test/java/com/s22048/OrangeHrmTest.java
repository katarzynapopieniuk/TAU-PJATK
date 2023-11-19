package com.s22048;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class OrangeHrmTest implements SeleniumTest {
    @Override
    public void run(WebDriver webDriver) {
        webDriver.get("https://opensource-demo.orangehrmlive.com/web/index.php");
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        assertEquals("OrangeHRM", webDriver.getTitle());

        logIn(webDriver, "badLogin", "admin123");
        assertFalse(isLoggedIn(webDriver));

        logIn(webDriver, "Admin", "badPassword");
        assertFalse(isLoggedIn(webDriver));

        logIn(webDriver, "Admin", "admin123");
        assertTrue(isLoggedIn(webDriver));

        clickLink(webDriver, "viewAdminModule");

        webDriver.findElement(By.cssSelector("button[type=button][class='oxd-button oxd-button--medium oxd-button--secondary']")).click();

        webDriver.findElement(By.cssSelector("div[class='oxd-select-text oxd-select-text--active']")).click();

        WebElement element = webDriver.findElement(By.cssSelector("div[role='listbox']"));

        webDriver.close();
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
