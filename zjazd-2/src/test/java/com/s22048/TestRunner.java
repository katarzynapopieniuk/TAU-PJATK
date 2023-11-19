package com.s22048;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

class TestRunner {

    @BeforeAll
    static void beforeAll() {
        System.setProperty("webdriver.gecko.driver", "");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\s22048\\Downloads\\chromedriver_win32\\chromedriver.exe");
        System.setProperty("webdriver.edge.driver", "C:\\Users\\s22048\\Downloads\\edgedriver_win64\\msedgedriver.exe");
    }

    @AfterAll
    static void afterAll() {
        System.clearProperty("webdriver.gecko.driver");
        System.clearProperty("webdriver.chrome.driver");
        System.clearProperty("webdriver.edge.driver");
    }

    @Test
    void shouldTestOrangeHrm() {
        WebDriver webDriver = new EdgeDriver();
        OrangeHrmTest orangeHrmTest = new OrangeHrmTest();
        orangeHrmTest.run(webDriver);
    }
}