package lesson_17;

import driver.DriverFactorySample;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandleMultipleApps {
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
            MobileElement loginBtnElem = androidDriver.findElementByAccessibilityId("button-LOGIN");
            loginBtnElem.click();

            // Put WebDriverio demo app into background
            androidDriver.runAppInBackground(Duration.ofSeconds(-1));

            //Open Settings application
            androidDriver.activateApp("com.android.settings");

            //Do sth here
            androidDriver.findElementByXPath("//*[@text='Network & internet']").click();
            Thread.sleep(2000);

            androidDriver.findElementByXPath("//*[@text='Internet']").click();
            Thread.sleep(5000);
            MobileElement wifiSwitchBtnElem = androidDriver.findElement(By.id("android:id/switch_widget"));
            boolean isWifiOn = wifiSwitchBtnElem.getText().equals("ON");
            if (isWifiOn){
                //Change to OFF
                wifiSwitchBtnElem.click();
                //Change to ON
                wifiSwitchBtnElem.click();

            }

            //IF ON --> OFF and then ON | ELSE --> ON


            //Re-launch WebDriverIO app
            androidDriver.activateApp("com.wdiodemoapp");


        } catch (Exception ignored) {
        }finally {
            DriverFactorySample.stopAppiumServer();
        }
    }
}
