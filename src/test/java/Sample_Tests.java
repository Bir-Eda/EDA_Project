import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class Sample_Tests {
    private Home_Page _home_page;
    private MyAccount_Page _myAccount_page;
    private Signin_Page _signin_page;
    WebDriver _driver;

    @Override
    public void setup_for_tests() throws Exception{
        _driver = new DriverSetup(_driverInfo).startDriver(_environment.getUrl());
        _homePage = new Home_Page(_driver);

    }
    @Test(groups={"all_Tests", "resgression_Tests"})
    public void PT101_Verify_Bread_Crumb_Value() throws  Exception{
        _myAccountPage = new NavigationBar_Page(_driver)
                .signIn.clickToNavigate()
                .signIn(_loginInfo);
        -myAccount_page.myAccountBreadcrump.verify()
                .enabled()
                .displayed()
                .textEquals("My account");
    }

    @Test(groups = {"all_Tests", "resgression_Test"})
    public void PT102_ProductPage_Verify_CheckboxesAreDisplayed() throws Exception{
        Product_Page product_page = _home_page.product_ByIndex(1).navigate_ProductPage();


        List<WebElement> checkboxes =_driver.findElements(By.xpath("//*[@type='checkbox']"));

        SoftAssert softAssert=new SoftAssert();
        for(int i=0; i<checkboxes.size(); i++){
            softAssert.assertTrue(checkboxes.get(i).isDisplayed(), "Checkbox "+ String.valueOf(i) +" was not displayed");
        }
        softAssert.assertAll();

    }
}

















