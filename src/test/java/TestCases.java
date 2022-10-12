import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestCases {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\bgrbo\\Downloads\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://www.saucedemo.com/");
        WebElement usernameInput = driver.findElement(By.cssSelector("input[id = 'user-name']"));
        usernameInput.sendKeys("standard_user");
        WebElement passwordInput = driver.findElement(By.xpath("//input[@id='password']"));
        passwordInput.sendKeys("secret_sauce");
        WebElement submitButton = driver.findElement(By.name("login-button"));
        submitButton.click();
    }


    @AfterMethod
    void finish(){
        driver.quit();
    }


    @Test
    void verifyPresenceOfWebElements(){
        WebElement productHeader = driver.findElement(By.className("title"));
        boolean isProductHeaderPresent = productHeader.isDisplayed();
        assertTrue(isProductHeaderPresent);

        WebElement shoppingCart = driver.findElement(By.className("shopping_cart_link"));
        boolean isShoppingCartPresent = shoppingCart.isDisplayed();
        assertTrue(isShoppingCartPresent);

        WebElement burgerMenu = driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']"));
        boolean isBurgerMenuPresent = burgerMenu.isDisplayed();
        assertTrue(isBurgerMenuPresent);

        burgerMenu.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='logout_sidebar_link']")));
        WebElement logout = driver.findElement(By.xpath("//a[@id='logout_sidebar_link']"));
        boolean isLogoutPresent = logout.isDisplayed();
        assertTrue(isLogoutPresent);
    }

    @Test(priority = 1)
    void verifyOrderFunctionality(){
        WebElement backpackLink = driver.findElement(By.id("item_4_title_link"));
        backpackLink.click();

        String backpackTitle = driver.findElement(By.xpath("//div[@class='inventory_details_name large_size']")).getText();
        assertEquals("Sauce Labs Backpack", backpackTitle);

        String backpackDescription = driver.findElement(By.xpath("//div[@class='inventory_details_desc large_size']")).getText();
        assertEquals("carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
                backpackDescription);

        String backpackPrice = driver.findElement(By.xpath("//div[@class='inventory_details_price']")).getText();
        assertEquals("$29.99", backpackPrice);

        WebElement addToCartButton = driver.findElement(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory']"));
        addToCartButton.click();

        WebElement backButton = driver.findElement(By.xpath("//button[@name='back-to-products']"));
        backButton.click();

        WebElement addJacketToCart = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-fleece-jacket']"));
        addJacketToCart.click();

        WebElement cartIcon = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        cartIcon.click();

        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();

        WebElement firstnameInput = driver.findElement(By.xpath("//input[@id='first-name']"));
        firstnameInput.sendKeys("John");

        WebElement lastnameInput = driver.findElement(By.xpath("//input[@id='last-name']"));
        lastnameInput.sendKeys("Doe");

        WebElement zipCodeInput = driver.findElement(By.name("postalCode"));
        zipCodeInput.sendKeys("71000");

        WebElement continueButton = driver.findElement(By.cssSelector("input[id='continue']"));
        continueButton.click();

        WebElement finishButton = driver.findElement(By.id("finish"));
        finishButton.click();

        String header = driver.findElement(By.xpath("//h2[@class='complete-header']")).getText();
        assertEquals("THANK YOU FOR YOUR ORDER", header);

    }
}
