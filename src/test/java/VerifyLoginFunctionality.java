import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.junit.Assert.*;

public class VerifyLoginFunctionality {
    private WebDriver driver;

    @BeforeMethod
    void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\bgrbo\\Downloads\\chromedriver.exe");
        this.driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    void finish(){
        driver.quit();
    }

    @Test
    void verifyValidUsernameAndPassword(){
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        driver.findElement(By.cssSelector("input[id = 'user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    //Negative test case
    @Test
    void verifyValidUsernameAndInvalidPassword(){
        String expectedMessage = "Epic sadface: Username and password do not match any user in this service";
        driver.findElement(By.cssSelector("input[id = 'user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("bad_password");
        driver.findElement(By.name("login-button")).click();
        String actualMessage = driver.findElement(By.xpath("//*[@id='login_button_container']/div/form/div[3]/h3")).getText();
        assertEquals(expectedMessage, actualMessage);
    }

    //Negative test case
    @Test
    void verifyBlankFields(){
        String expectedMessage = "Epic sadface: Username is required";
        driver.findElement(By.name("login-button")).click();
        String actualMessage = driver.findElement(By.xpath("//*[@id='login_button_container']/div/form/div[3]/h3")).getText();
        assertEquals(expectedMessage, actualMessage);
    }

    //Negative test case
    @Test
    void verifyValidUsernameAndEmptyPassword(){
        String expectedMessage = "Epic sadface: Password is required";
        driver.findElement(By.cssSelector("input[id = 'user-name']")).sendKeys("standard_user");
        driver.findElement(By.name("login-button")).click();
        String actualMessage = driver.findElement(By.xpath("//*[@id='login_button_container']/div/form/div[3]/h3")).getText();
        assertEquals(expectedMessage, actualMessage);
    }

    //Negative test case
    @Test
    void verifyEmptyUsernameAndRandomPassword(){
        String expectedMessage = "Epic sadface: Username is required";
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("bad_password");
        driver.findElement(By.name("login-button")).click();
        String actualMessage = driver.findElement(By.xpath("//*[@id='login_button_container']/div/form/div[3]/h3")).getText();
        assertEquals(expectedMessage, actualMessage);
    }

    //Negative test case
    @Test
    void verifyNonexistingAccount(){
        String expectedMessage = "Epic sadface: Username and password do not match any user in this service";
        driver.findElement(By.cssSelector("input[id = 'user-name']")).sendKeys("random_user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("random_password");
        driver.findElement(By.name("login-button")).click();
        String actualMessage = driver.findElement(By.xpath("//*[@id='login_button_container']/div/form/div[3]/h3")).getText();
        assertEquals(expectedMessage, actualMessage);
    }
}
