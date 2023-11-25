package com.s22048;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

class TestRunner {

    @BeforeAll
    static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "E:\\selenium\\chromedriver_win32\\chromedriver.exe");
        System.setProperty("webdriver.edge.driver", "E:\\selenium\\edgedriver_win64\\msedgedriver.exe");
    }

    @AfterAll
    static void afterAll() {
        System.clearProperty("webdriver.chrome.driver");
        System.clearProperty("webdriver.edge.driver");
    }

    @Test
    void shouldTestOrangeHrmOnEdge() {
        WebDriver webDriver = new EdgeDriver();
        OrangeHrmTest orangeHrmTest = new OrangeHrmTest();
        orangeHrmTest.run(webDriver);
    }

    @Test
    void shouldTestDemoOpenMRSOnEdge() {
        WebDriver webDriver = new EdgeDriver();
        webDriver.manage().window().maximize();
        DemoOpenMRSTest demoOpenMRSTest = new DemoOpenMRSTest();
        demoOpenMRSTest.run(webDriver);
    }

    @Test
    void shouldTestOrangeHrmOnChrome() {
        WebDriver webDriver = new ChromeDriver();
        OrangeHrmTest orangeHrmTest = new OrangeHrmTest();
        orangeHrmTest.run(webDriver);
    }

    @Test
    void shouldTestDemoOpenMRSOnChrome() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        DemoOpenMRSTest demoOpenMRSTest = new DemoOpenMRSTest();
        demoOpenMRSTest.run(webDriver);
    }
}