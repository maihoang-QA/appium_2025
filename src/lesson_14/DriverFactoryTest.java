package lesson_14;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DriverFactoryTest {
    public static void main(String[] args) {
        DriverFactorySample.startAppiumServer();

        try {

            AndroidDriver<MobileElement> androidDriver = DriverFactorySample.getAndroidDriver();
            MobileElement loginLable = androidDriver.findElementByAccessibilityId("Login");
            loginLable.click();

            //Find Element by Related Xpath
            List<MobileElement> credsFormElems = androidDriver.findElementsByXPath("//android.widget.EditText");
            final int EMAIL_INPUT_INDEX = 0;
            final int PASSWORD_INPUT_INDEX = 1;

            credsFormElems.get(EMAIL_INPUT_INDEX).sendKeys("mai@gmail.com");
            credsFormElems.get(PASSWORD_INPUT_INDEX).sendKeys("12345678");
//
//            MobileElement emailInputElem = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-email']");
//            MobileElement passwordInputElem = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-password']");
            MobileElement loginBtnElem = androidDriver.findElementByAccessibilityId("button-LOGIN");

//            emailInputElem.sendKeys("mai@gmail.com");
//            passwordInputElem.sendKeys("12345678");
            loginBtnElem.click();
            MobileElement loginFeatureDescElem = androidDriver.findElementByXPath("//*[contains(@text,'When the device')]");
//            MobileElement loginFeatureDescElemUiSel = androidDriver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"When the device\".className\"android.widget.TextView\")\n");
            System.out.println(loginFeatureDescElem.getText());


            WebDriverWait wait = new WebDriverWait(androidDriver, 45);
            //ExplicitWait: Cho chờ trên 1 đối tượng cụ thể
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/alertTitle")));
            MobileElement loginResultDialogElem = androidDriver.findElementById("android:id/alertTitle");
            System.out.println(loginResultDialogElem.getText());
        } catch (Exception ignored) {
        }finally {
            DriverFactorySample.stopAppiumServer();
        }
    }
}
