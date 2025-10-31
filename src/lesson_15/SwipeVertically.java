package lesson_15;

import driver.DriverFactorySample;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SwipeVertically {
    public static void main(String[] args) {
        DriverFactorySample.startAppiumServer();
        try{
            AppiumDriver<MobileElement> androidDriver = DriverFactorySample.getAndroidDriver();
            MobileElement formsLabelElem = androidDriver.findElementByAccessibilityId("Forms");
//            MobileElement formsLabelElem = androidDriver.findElementByAccessibilityId("Swipe");
            formsLabelElem.click();

            // Check to see whether we are on FormScreen
            //Chờ đến khi switch xuất hiện
            WebDriverWait wait = new WebDriverWait(androidDriver, 30L);
            wait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByAccessibilityId("switch")));

//            wait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByXPath("//*[@text='Swipe horizontal']")));
            //Get Mobile window size
            Dimension windowSize = androidDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            //Caculator touch point
            int xStartPoint = 50 * screenWidth /100;
            int xEndPoint = xStartPoint;
            int yStartPoint = 90 * screenHeight /100;
            int yEndPoint = 10 * screenHeight /100;

            //Convert to PointOptions
            //PointOptions: Đặt ngón tay lên màn hình n sẽ chuyển sang tọa độ x,y
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            //Perform Touch action
            TouchAction touchAction = new TouchAction(androidDriver);

            //Swipe up
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(endPoint)
                    .release()
                    .perform();

            //Swipe down
            touchAction
                    .press(endPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(startPoint)
                    .release()
                    .perform();

            //Swipe up
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(endPoint)
                    .release()
                    .perform();
//
//            MobileElement activeBtnElem = androidDriver.findElementByAccessibilityId("button-Active");
//            activeBtnElem.click();

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            DriverFactorySample.stopAppiumServer();
        }

    }
}
