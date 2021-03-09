import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class Login_M {
    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
      //  driver.get("https://www.medallia.com/");
    }
    @Test //(enabled = false)
    public void getTitle() throws InterruptedException {
        driver.get("https://www.medallia.com/");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Medallia | Customer Experience and Employee Experience";
        Assert.assertEquals(actualTitle, expectedTitle, "Fail");
        driver.findElement(By.xpath("//*[@id='demo-request']")).click();
        Thread.sleep(2000);
        WebElement fName = driver.findElement(By.cssSelector("#FirstName"));
        WebElement lName= driver.findElement(By.cssSelector("#LastName"));
        WebElement email=driver.findElement(By.cssSelector("#Email"));
        WebElement phone=driver.findElement(By.cssSelector("#Phone"));
        WebElement title=driver.findElement(By.cssSelector("#Title"));
        fName.sendKeys("Selinay");
        lName.sendKeys("Selin");
        email.sendKeys("selinayselin2@gmail.com");
        phone.sendKeys("1230984576");
        title.sendKeys("qa engineer");
        // contact center
        WebElement selDropDown=driver.findElement(By.cssSelector("#Job_Role__c"));
        selDropDown.click();
        Select jobOptions = new Select(selDropDown);
        jobOptions.selectByVisibleText("Account Management");
        Thread.sleep(1000);

        WebElement company=driver.findElement(By.cssSelector("#Company"));
        company.sendKeys("Medallia");
        // country option
        WebElement country=driver.findElement(By.xpath("//*[@id='Country']"));
        country.click();
        Select countryOp= new Select(country);
        countryOp.selectByVisibleText("United States");
        Thread.sleep(1000);

        WebElement product=driver.findElement(By.cssSelector("#Main_Product_Interest__c"));
        product.click();
        Select productOp= new Select(product);
        productOp.selectByVisibleText("Product Experience");

        driver.findElement(By.cssSelector("#LblmktoCheckbox_14379_0")).click();
        driver.findElement(By.cssSelector("#LblmktoCheckbox_14374_0")).click();
        driver.findElement(By.xpath("//*[@class='mktoButton']")).click();
        Thread.sleep(3000);

    }

    @Test
    public void requestDemo(){


    }

    @Test
    public void scrollDown() throws InterruptedException {
        driver.get("https://www.medallia.com/");
        JavascriptExecutor js= (JavascriptExecutor)driver;
        // js.executeScript("arguments[0].scrollIntoView();",Element );
        WebElement scrollDown=driver.findElement(By.xpath("//*[@class='c-xp-slider__button']"));
        js.executeScript("arguments[0].scrollIntoView(true)",scrollDown);
        Thread.sleep(2000);
        JavascriptExecutor json= (JavascriptExecutor)driver;
        WebElement scrollUp=driver.findElement(By.xpath("(//*[contains(@class,'c-link')])[1]"));
        json.executeScript("arguments[0].scrollIntoView(true)",scrollUp);
        Thread.sleep(2000);

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }






//    WebDriver driver;
//    @BeforeMethod
//    public void setUp(){
//        WebDriverManager.chromedriver().setup();
//        driver=new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.get("https://reg.ebay.com/reg/PartialReg?ru=https%3A%2F%2Fwww.ebay.com%2F");
//
//    }
//
//    @Test
//    public void ebayTest(){
//        WebElement firstName=driver.findElement(By.xpath("//input[@id='firstname']"));
//        WebElement lastName=driver.findElement(By.id("lastname"));
//        WebElement email=driver.findElement(By.id("email"));
//        WebElement password=driver.findElement(By.id("PASSWORD"));
//        WebElement creat=driver.findElement(By.xpath("//button[@id='ppaFormSbtBtn']"));
//        firstName.sendKeys("Birsen");
//        lastName.sendKeys("Yildirim");
//        email.sendKeys("edanuryildirim@hotmail.com");
//        password.sendKeys("123abc.");
//        creat.click();
//
//
//    }
}
