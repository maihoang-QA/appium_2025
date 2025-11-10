package lesson_17;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwipeToOpenNotification {
    public static void main(String[] args) {
        DriverFactorySample.startAppiumServer();
        try {
            AppiumDriver<MobileElement> androidDriver = DriverFactorySample.getAndroidDriver();
            //Get Mobile window size
            Dimension windowSize = androidDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            //Caculator touch point
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = xStartPoint;
            int yStartPoint = 0;
            int yEndPoint = 50 * screenHeight / 100;

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
            Thread.sleep(2000);


            //Get the info inside the notifications by getting a list
            List<MobileElement> notificationElems = androidDriver.findElements(By.id("android:id/notification_main_column"));

            if (notificationElems.isEmpty())
                throw new RuntimeException("Notification List is empty");

            Map<String, String> notificationList  = new HashMap<>();

            notificationElems.forEach(notification -> {
                String notificationTitle = notification.findElement(By.id("android:id/title")).getText();
                String notificationContent = notification.findElement(By.id("android:id/big_text")).getText();
                notificationList.put(notificationTitle, notificationContent);

            });

            touchAction
                    .press(endPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(startPoint)
                    .release()
                    .perform();
            Thread.sleep(2000);

            notificationList.keySet().forEach(key ->{
                System.out.println(key + ": " + notificationList.get(key));
            });

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            DriverFactorySample.stopAppiumServer();
        }

    }
}
