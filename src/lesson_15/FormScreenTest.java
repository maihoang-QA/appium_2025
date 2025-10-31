package lesson_15;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import driver.DriverFactorySample;

public class FormScreenTest {
    public static void main(String[] args) {
        DriverFactorySample.startAppiumServer();
        try{
            AppiumDriver<MobileElement> androidDriver = DriverFactorySample.getAndroidDriver();
            MobileElement formsLabelElem = androidDriver.findElementByAccessibilityId("Forms");
            formsLabelElem.click();
            MobileElement activeBtnElem = androidDriver.findElementByAccessibilityId("button-Active");
            activeBtnElem.click();

        }catch (Exception ignore){

        }finally {
            DriverFactorySample.stopAppiumServer();
        }
    }
}
