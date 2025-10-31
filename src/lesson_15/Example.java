package lesson_15;

import driver.DriverFactorySample;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class Example {
    public static void main(String[] args) {
        DriverFactorySample.startAppiumServer();
        AppiumDriver<MobileElement> androidDriver = null;
        try{
            androidDriver = DriverFactorySample.getAndroidDriver();
            MobileElement formsLabelElem = androidDriver.findElementByAccessibilityId("Swipe");
            formsLabelElem.click();

            WebDriverWait wait = new WebDriverWait(androidDriver, 30L);
            wait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByXPath("//*[@text='Swipe horizontal']")));

            Dimension windowSize = androidDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            int xStartPoint = 20 * screenWidth /100;
            int xEndPoint = xStartPoint;
            int yStartPoint = 80 * screenHeight /100;
            int yEndPoint =  30 * screenHeight /100;


            PointOption startPoint  =  new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            TouchAction touchAction = new TouchAction(androidDriver);
            int MAX_SWIPE_TIME = 5;
            int swipiTime = 0;
//
            while (swipiTime < MAX_SWIPE_TIME){
                if (androidDriver == null || androidDriver.getSessionId() == null) break;

                List<MobileElement> logo = androidDriver.findElementsByXPath("//*[@text='You found me!!!']");
                if (!logo.isEmpty()) break;
                touchAction
                        .press(startPoint)
                        .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                        .moveTo(endPoint)
                        .release()
                        .perform();
//                Thread.sleep(1000);
                swipiTime++;}
//}

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                Thread.sleep(1000); // cho phép Appium xử lý nốt các thao tác còn lại
                if (androidDriver != null) androidDriver.quit();
            } catch (Exception e) {
                e.printStackTrace();
            }

            DriverFactorySample.stopAppiumServer();
        }
    }
}
