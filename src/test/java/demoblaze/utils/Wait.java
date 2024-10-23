package demoblaze.utils;

import demoblaze.base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Wait extends BaseTest {

    public Wait(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public static void waitVisibility5Sec(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitVisibility10Sec(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitTextToBePresentIn10Sec(WebElement element, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static void waitToBeClickable10Sec(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitAlertIsPresent5Sec() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent());
    }
}
