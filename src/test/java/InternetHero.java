import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InternetHero {
    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @Test
    public void digestAuthontication(){
      //  driver.get("https://.........@the-internet.herokuapp.com/digest_auth");
        String username="admin";
        String password="admin";
        driver.get("https://"+username+ ":" + password +"@the-internet.herokuapp.com/digest_auth");
    }
    @Test
    public void upLoad() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/upload");
        WebElement file=driver.findElement(By.cssSelector("#file-upload"));
        file.sendKeys("/Users/Eda/Desktop/Happy.png");
        Thread.sleep(2000);
        WebElement upload2=driver.findElement(By.cssSelector("#file-submit"));
        upload2.click();
        String actual=driver.findElement(By.cssSelector("#uploaded-files")).getText();

        Assert.assertEquals(actual, "Happy.png");


    }
    @Test
    public void iframe() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/iframe");

        driver.switchTo().frame("mce_0_ifr"); //

        WebElement textBox=driver.findElement(By.xpath("//*[@id='tinymce']//p"));
        textBox.clear();
        textBox.sendKeys("Happy Valentines Day");

        Thread.sleep(2000);
        driver.switchTo().defaultContent();
        driver.findElement(By.linkText("Elemental Selenium")).click();



    }
    @Test
    public void iframeNested() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/nested_frames");

        WebElement bigFrame= driver.findElement(By.xpath("//*[@name='frame-top']"));
        driver.switchTo().frame(bigFrame);
        Thread.sleep(2000);

//        WebElement secondFrame= driver.findElement(By.xpath("//*[@name='frame-left']"));
//        driver.switchTo().frame("frame-left");
//        Thread.sleep(2000);

        WebElement thirdFrame= driver.findElement(By.xpath("//*[@name='frame-middle']"));
        driver.switchTo().frame("frame-middle");
        Thread.sleep(2000);

        String actual= driver.findElement(By.cssSelector("#content")).getText();
        Assert.assertEquals(actual,"MIDDLE");

        }



    @AfterMethod
    public void tearDown(){
        driver.quit();
    }



}
