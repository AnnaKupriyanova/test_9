package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class Main {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\IntelliJ IDEA\\projects\\lab9\\drivers\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testEIOS() {
        driver.get("https://eios.kemsu.ru/a/eios");

        WebElement loginField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='username']")));
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='password']")));
        loginField.clear();
        passwordField.clear();
        loginField.sendKeys("stud71631");
        passwordField.sendKeys("forlabtest");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        loginButton.click();
        WebElement off = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class=\"css-rsbsf6 efn4aem0\"]")));
        off.click();

        WebElement myFIOLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'css-10pdxt6') and contains(@class, 'efn4aem0')]")));
        String FIO = myFIOLink.getText();
        assertEquals("Куприянова А.А.", FIO);
        System.out.println("Мой акк eios)");

        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'css-1oslnw8') and contains(@class, 'efn4aem0')]")));
        logoutButton.click();

        wait.until(ExpectedConditions.urlToBe("https://eios.kemsu.ru/a/eios"));
        System.out.println("Выход выполнен)");
    }

    @Test
    public void testHabrSearch() {
        driver.get("https://habr.com/ru/all/");

        WebElement searchIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class, 'tm-header-user-menu__icon_search')]")));
        searchIcon.click();
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='q']")));
        searchField.sendKeys("Selenium WebDriver");
        assertEquals(searchField, driver.switchTo().activeElement());
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class, 'tm-svg-icon') and contains(@class, 'tm-search__icon')]")));
        searchButton.click();
        WebElement seleniumArticle = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Что такое Selenium?")));
        seleniumArticle.click();
        WebElement dateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title='2012-09-28, 16:14']")));
        assertEquals("28 сен 2012 в 16:14", dateElement.getText());

        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        WebElement footer = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/ru/articles/' and contains(@class, 'footer-menu__item-link')]")));
        footer.click();
    }

    @After
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(1000);
            driver.quit();
        }
    }
}