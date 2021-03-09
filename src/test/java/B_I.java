
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;

public class B_I {

    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver();
    }
    @Test
    public void isBrokenImage() throws IOException {
        driver.get("https://the-internet.herokuapp.com/broken_images");
       WebElement status = driver.findElement(By.xpath("//*[@class='example']//img[3]"));

        HttpClient client = HttpClientBuilder.create().build();  //  this is interface
        HttpGet request = new HttpGet(status.getAttribute("src"));
        HttpResponse response = client.execute(request);
        if(response.getCode()!=200){
            System.out.println("broken");
        }
        else{
            System.out.println("image is good");
        }

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}





















