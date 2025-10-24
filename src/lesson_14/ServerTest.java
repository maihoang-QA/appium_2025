package lesson_14;

//import caps.MobileCapabilityTypeEx;
import caps.MobileCapabilityTypeEx;
import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.MobileElement;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ServerTest {
    public static void main(String[] args) throws MalformedURLException {
        //Khởi tạo appium server > dùng Appium Builder
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        //usingAnyFreePort: ko chỉ định cái port nào hết có cái port nào nó tìm dc nó sẽ khởi ta
        appiumServiceBuilder.withIPAddress("127.0.0.1").usingPort(4725)
                .withArgument(() -> "--base-path", "wd/hub");;
        AppiumDriverLocalService appiumServer = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        appiumServer.start();


        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
        desiredCapabilities.setCapability("noReset", "false");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.NEW_COMMAND_TIMEOUT, 120);

        AppiumDriver<MobileElement> appiumDriver = new AndroidDriver<>(appiumServer.getUrl(), desiredCapabilities);
        appiumDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);

        MobileElement loginLable = appiumDriver.findElementByAccessibilityId("Login");
        loginLable.click();



        //server stop
        String killNodeWindowsCmd = "taskkill /F /IM.node.exe";
        String killNodeLinuxCmd = "killall node";
        String currentOs = System.getProperty("os.name").toLowerCase();
        String killNodeCmd = currentOs.startsWith("window")
                ? killNodeWindowsCmd : killNodeLinuxCmd;

        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(killNodeCmd);
        } catch (IOException e) {
           e.printStackTrace();
        }

    }
}
