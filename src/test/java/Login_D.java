import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Login_D {
    WebDriver driver;
    static String baseUrl;
    @BeforeMethod

    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @Test
    public void loginDeluxe(){
        driver.get("https://www.deluxe.com/");
        String actualTitle=driver.getTitle();
        String expectedTitle="Trusted Solutions For Businesses and Financial Institutions - Deluxe";
        Assert.assertEquals(actualTitle, expectedTitle, "Fail");
    }
    @Test
    public void checks() throws InterruptedException {
        driver.get("https://www.deluxe.com/");
        WebElement hoverOver=driver.findElement(By.xpath("//*[contains(text(),'Buy Checks')]"));
        Actions act = new Actions(driver);
        act.moveToElement(hoverOver).perform();
        WebElement clickElement=driver.findElement(By.xpath("(//*[contains(text(),'Personal Checks')])[1]"));
        act.moveToElement(clickElement).click().perform();
        Thread.sleep(3000);

        Set<String> handles = driver.getWindowHandles();  // birden fazla pencere oldugu icin
        System.out.println("number of windows " + handles.size());
        Iterator<String> itr = handles.iterator();
        while (itr.hasNext()) {
            String parentHandle=itr.next();
            System.out.println("parent handle = " +parentHandle);
            String childHandle = itr.next();
            System.out.println("child handle = " + childHandle);
            if (!parentHandle.equals(childHandle)) {
                driver.switchTo().window(childHandle);
                String actualTitle=driver.getTitle();
                String expectedTitle="Personal Checks - Order Deluxe Checks";
                Assert.assertEquals(actualTitle, expectedTitle, "Fail");

            }
        }
    }


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
