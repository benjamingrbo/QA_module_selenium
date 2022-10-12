import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.junit.Assert.*;

public class VerifyOrderFunctionality {
    private WebDriver driver;


    //Do the login, not testing it, just setup
    @BeforeMethod
    void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\bgrbo\\Downloads\\chromedriver.exe");
        this.driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("input[id = 'user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();
    }

    //Close Google Chrome driver after test is done
    @AfterMethod
    void finish(){
        driver.quit();
    }

    //Verify that user cannot make an order with empty shopping cart
    @Test
    void verifyEmptyShoppingCartOrder(){
        String expectedMessage = "Your shopping cart is empty";
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        driver.findElement(By.xpath("//button[@id='checkout']")).click();
        String actualMessage = driver.findElement(By.xpath("//*[@id='cart_contents_container'/div/div[2]")).getText();
        assertEquals(expectedMessage, actualMessage);
    }
}
