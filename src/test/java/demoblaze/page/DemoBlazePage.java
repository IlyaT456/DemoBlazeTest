package demoblaze.page;

import demoblaze.base.BaseSeleniumPage;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

import static demoblaze.utils.Wait.*;

public class DemoBlazePage extends BaseSeleniumPage {

    public DemoBlazePage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "signin2")
    public WebElement signIn;

    @FindBy(id = "sign-username")
    public WebElement regUserName;

    @FindBy(id = "sign-password")
    public WebElement regUserPassword;

    public WebElement registrationButton() {
        return driver.findElement(By.xpath("//button[@onclick='register()']"));
    }

    @FindBy(id = "login2")
    public WebElement login;

    @FindBy(css = "#loginusername")
    public WebElement loginUserName;

    @FindBy(css = "#loginpassword")
    public WebElement loginUserPassword;

    public WebElement loginButton() {
        return driver.findElement(By.xpath("//button[@onclick='logIn()']"));
    }

    @FindBy(css = "#nameofuser")
    public WebElement nameOfUserLog;

    @FindBy(className = "list-group")
    public WebElement listGroupCategory;

    public WebElement itemListGroupCategory(String item) {
        return driver.findElement(By.xpath("//a[@class='list-group-item' and .='"+item+"']"));
    }

    public WebElement cardTitle(String cardName) {
        return driver.findElement(By.xpath("//a[@class='hrefch' and contains(.,'" + cardName + "')]"));
    }

    @FindBy(css = "div[class='product-content product-wrap clearfix product-deatil']")
    public WebElement productDetail;

    @FindBy(xpath = "//a[@onclick='addToCart(15)']")
    public WebElement addToCartButton;

    public DemoBlazePage openDemoBlazePage() {
        driver.get("https://www.demoblaze.com");
        return this;
    }

    public DemoBlazePage newUserRegistration(String name, String password) {
        signIn.click();
        regUserName.sendKeys(name);
        regUserPassword.sendKeys(password);
        registrationButton().click();
        return this;
    }

    public DemoBlazePage assertRegistrationNewUser(String expectedAlertText) {
        try {
            waitAlertIsPresent5Sec();
            Assert.assertTrue(driver.switchTo().alert().getText().contains(expectedAlertText));
        } catch (AssertionError error) {
            throw new RuntimeException("Пользователь не зарегистрирован");
        }
        return this;
    }

    public DemoBlazePage loginUser(String name, String password) {
        login.click();
        loginUserName.sendKeys(name);
        loginUserPassword.sendKeys(password);
        loginButton().click();
        return this;
    }

    public DemoBlazePage assertLoginUser(String expected) {
        waitVisibility10Sec(nameOfUserLog);
        Assert.assertEquals(nameOfUserLog.getText(), expected);
        return this;
    }

    public DemoBlazePage assertCategoryMenu(List<String> expectedCatMenu) {
        for (String item : expectedCatMenu) {
            Assert.assertTrue(listGroupCategory.getText().contains(item));
        }
        return this;
    }

    public DemoBlazePage selectItemCategory(String item) {
        try {
            itemListGroupCategory(item).click();
        } catch (StaleElementReferenceException e) {
            itemListGroupCategory(item).click();
        }

        return this;
    }

    public DemoBlazePage selectCardTitle(String cardName) {
        cardTitle(cardName).click();
        waitTextToBePresentIn10Sec(productDetail, cardName);

        Assert.assertTrue(productDetail.getText().contains(cardName));
        return this;
    }

    public DemoBlazePage clickAddToCartButton() {
        addToCartButton.click();

        return this;
    }

    public DemoBlazePage assertAddToCart(String expectedAlertText) {
        waitAlertIsPresent5Sec();
        Assert.assertTrue(driver.switchTo().alert().getText().contains(expectedAlertText));
        driver.switchTo().alert().dismiss();
        return this;
    }
}
