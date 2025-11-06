package lesson_16;

import driver.DriverFactorySample;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class test {
    public static void main(String[] args) {
        DriverFactorySample.startAppiumServer(); // Khởi động Appium server
        AppiumDriver<MobileElement> driver = null;

        try {
            // Khởi tạo AndroidDriver
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "Android");
            caps.setCapability("deviceName", "emulator-5554");
            caps.setCapability("automationName", "UiAutomator2");
            caps.setCapability("appPackage", "com.wdiodemoapp");
            caps.setCapability("appActivity", "com.wdiodemoapp.MainActivity");
            caps.setCapability("newCommandTimeout", 120);
            // caps.setCapability("chromedriverExecutable", "C:\\chromedriver\\chromedriver.exe"); // optional
            caps.setCapability("autoGrantPermissions", true);

            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4725/wd/hub"), caps);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            // Click vào màn Webview
            MobileElement webviewLabel = driver.findElementByAccessibilityId("Webview");
            webviewLabel.click();

            // Chờ WebView load + retry loop
            Set<String> contexts = driver.getContextHandles();
            int retries = 0;
            while (contexts.stream().noneMatch(c -> c.contains("WEBVIEW")) && retries < 10) {
                Thread.sleep(2000);
                contexts = driver.getContextHandles();
                System.out.println("Retry " + retries + " - contexts: " + contexts);
                retries++;
            }

            if (contexts.stream().noneMatch(c -> c.contains("WEBVIEW"))) {
                System.out.println("❌ WebView chưa sẵn sàng, không thể switch context.");
            } else {
                String webviewContext = contexts.stream()
                        .filter(c -> c.contains("WEBVIEW"))
                        .findFirst()
                        .get();
                System.out.println("✅ Switching to WebView: " + webviewContext);
                driver.context(webviewContext);

                // Thao tác với WebView
                WebElement navToggle = driver.findElementByCssSelector(".navbar__toggle");
                navToggle.click();

                // Chuyển về Native
                driver.context("NATIVE_APP");
                MobileElement loginLabel = driver.findElementByAccessibilityId("Login");
                loginLabel.click();

                System.out.println("✅ Hoàn tất thao tác trên Native và WebView");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
            DriverFactorySample.stopAppiumServer(); // Stop server
        }
    }
}

