package lesson_17;

import driver.DriverFactorySample;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class TakingScreenShot {
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

            //Wait until we are on Login Screen
            WebDriverWait wait = new WebDriverWait(androidDriver, 5L);
            MobileElement loginBtnElemen = androidDriver.findElementByAccessibilityId("button-LOGIN");
            wait.until(ExpectedConditions.visibilityOf(loginBtnElemen));

            //Talking whole screen
            File base64loginBtnData = loginBtnElem.getScreenshotAs(OutputType.FILE);
            String loginBtnImgFileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("loginBtnElem.png");
            FileUtils.copyFile(base64loginBtnData, new File(loginBtnImgFileLocation));


            //Taking element screenshot FAB - Floating Action Button

            File base64ScreenShotData = androidDriver.getScreenshotAs(OutputType.FILE);
            String fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("loginForm.png");
            FileUtils.copyFile(base64ScreenShotData, new File(fileLocation));

            //Talking area screenshot
            List <MobileElement> viewGroupElems = androidDriver.findElementsByXPath("//android.view.ViewGroup/android.view.ViewGroup[2]");
            if (!viewGroupElems.isEmpty()) {
                File base64NavData = viewGroupElems.get(viewGroupElems.size() - 1).getScreenshotAs(OutputType.FILE);
                String navImgFileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("nav.png");
                FileUtils.copyFile(base64NavData, new File(navImgFileLocation));
            }

        } catch (Exception ignored) {
        }finally {
            DriverFactorySample.stopAppiumServer();
        }
    }
}
