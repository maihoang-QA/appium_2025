package lesson_16;

import driver.DriverFactorySample;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HandleHybridContext {
    public static void main(String[] args) {
        DriverFactorySample.startAppiumServer();
        try {
            AppiumDriver<MobileElement> appiumDriver = DriverFactorySample.getAndroidDriver();
            MobileElement webviewLabelElem = appiumDriver.findElementByAccessibilityId("Webview");
            webviewLabelElem.click();
            Thread.sleep(9000);

           //Trả về tập hợp (Set) các context mà Appium đang quản lý trong phiên làm việc hiện tại.
            //Context ở đây nghĩa là môi trường chạy mà Appium có thể điều khiển
            appiumDriver.getContextHandles().forEach(context ->{
                System.out.println(context);
            });

            appiumDriver.context("WEBVIEW_com.wdiodemoapp");
            WebElement navToggleBtnElem = appiumDriver.findElementByCssSelector(".navbar__toggle");
            navToggleBtnElem.click();

            List<MobileElement> menuItems = appiumDriver.findElementsByCssSelector(".menu__list-item a");
            List<MenuItem> menuItemList = new ArrayList<>();
            for (MobileElement menuItem : menuItems) {
                String menuText = menuItem.getText();
                String menuHyperLink = menuItem.getAttribute("href");
                if (StringUtils.isEmpty(menuText))
                    menuItemList.add(new MenuItem("GitHub", menuHyperLink));
                else
                    menuItemList.add(new MenuItem(menuText, menuHyperLink));
            }
            menuItemList.forEach(
                System.out::println
            );

            //Switch to navite context
            appiumDriver.context("NATIVE_APP");

            //Interact with native context elemens
            MobileElement loginLabelElem = appiumDriver.findElementByAccessibilityId("Login");
            loginLabelElem.click();

            //Giữ app dưới back sau 3s n tự động lauch app lại
            appiumDriver.runAppInBackground(Duration.ofSeconds(30));

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DriverFactorySample.stopAppiumServer();

        }

    }

    public static class MenuItem{
        private String text;
        private String hyperLink;

        public MenuItem(String hyperLink, String text) {
            this.hyperLink = hyperLink;
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public String getHyperLink() {
            return hyperLink;
        }

        @Override
        public String toString() {
            return "MenuItem{" +
                    "text='" + text + '\'' +
                    ", hyperLink='" + hyperLink + '\'' +
                    '}';
        }
    }
}
