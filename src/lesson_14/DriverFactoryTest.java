package lesson_14;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class DriverFactoryTest {
    public static void main(String[] args) {
        DriverFactorySample.startAppiumServer();
        try {

            AndroidDriver<MobileElement> androidDriver = DriverFactorySample.getAndroidDriver();
            MobileElement loginLable = androidDriver.findElementByAccessibilityId("Login");
            loginLable.click();

            //Find Element by Related Xpath
            MobileElement emailInputElem = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-email']");
            MobileElement passwordInputElem = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-password']");
            MobileElement loginBtnElem = androidDriver.findElementByAccessibilityId("button-LOGIN");

            emailInputElem.sendKeys("mai@gmail.com");
            passwordInputElem.sendKeys("12345678");
            loginBtnElem.click();

        } catch (Exception ignored) {
        }finally {
            DriverFactorySample.stopAppiumServer();
        }
    }
}
