package utils.screen;

import driver.DriverFactorySample;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SwipeUtil {

    public static void main(String[] args) {
        By selector;

//        SwipeUtil.verticallyUntilISee(selector);
//        SwipeUtil.verticallyUntilISee(selector, percentage);
//        SwipeUtil.verticallyUntilISee(selector, percentage, maxTimes);
//        SwipeUtil.horizontallyUntilISee(selector);
//        SwipeUtil.horizontallyUntilISee(selector, percentage);
//        SwipeUtil.horizontallyUntilISee(selector, percentage, maxTimes);
    }




    public static void verticallyUntilISee(String[] args) {
        DriverFactorySample.startAppiumServer();
        try{
            AppiumDriver<MobileElement> androidDriver = DriverFactorySample.getAndroidDriver();
            MobileElement formsLabelElem = androidDriver.findElementByAccessibilityId("Swipe");
            formsLabelElem.click();

            // Check to see whether we are on FormScreen
            //Chờ đến khi switch xuất hiện
            WebDriverWait wait = new WebDriverWait(androidDriver, 30L);
            wait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByXPath("//*[@text='Swipe horizontal']")));

            //Get Mobile window size
            Dimension windowSize = androidDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            //Caculator touch point
            int xStartPoint = 50 * screenWidth /100;
            int xEndPoint = 10 * screenWidth /100;;
            int yStartPoint = 50 * screenHeight /100;
            int yEndPoint = yStartPoint;

            //Convert to PointOptions
            //PointOptions: Đặt ngón tay lên màn hình n sẽ chuyển sang tọa độ x,y
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            //Perform Touch action
            TouchAction touchAction = new TouchAction(androidDriver);
            int MAX_SWIPE_TIME = 5;
            int swipeTime = 0;

            while (swipeTime < MAX_SWIPE_TIME){
                List<MobileElement> matchedCards = androidDriver.findElementsByXPath("//*[@text='EXTENDABLE']");
                if (!matchedCards.isEmpty()) break;
                touchAction
                        .press(startPoint)
                        .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                        .moveTo(endPoint)
                        .release()
                        .perform();
                swipeTime++;
            }

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            DriverFactorySample.stopAppiumServer();
        }
    }

}


