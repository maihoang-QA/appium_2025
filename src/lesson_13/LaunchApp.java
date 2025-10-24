//package lesson_13;
//
//import caps.MobileCapabilityTypeEx;
//import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.MobileElement;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.remote.MobileCapabilityType;
//import io.appium.java_client.service.local.AppiumDriverLocalService;
//import io.appium.java_client.service.local.AppiumServiceBuilder;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
//import java.net.URL;
//import java.util.concurrent.TimeUnit;
//
//public class LaunchApp {
//    public static void main(String[] args) {
//
//        AppiumDriver <MobileElement> appiumDriver = null;
//
//        //Setup DesiredCapabilities
//        try{  DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
//            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
//            desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
//            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
//            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
//            desiredCapabilities.setCapability(MobileCapabilityTypeEx.NEW_COMMAND_TIMEOUT, 120);
//
//            //NEW_COMMAND_TIMEOUT: Appium m lắng nghe hộ t trong 120s ko có tín hiệu j gửi lên thì tức t đã die > clear session đó đi
//
//            //Connect to Appium server: capabilities ----> Appium server port 4725
//            //Khởi tạo url để connect
//            URL appiumServer = new URL("http://127.0.0.1:4725/");
//            //Khởi tạo appiumDriver , lúc này send toàn bộ desiredCapabilities sang appiumServer
//            appiumDriver = new AndroidDriver<>(appiumServer,desiredCapabilities);
//        } catch (Exception ignored) {
//        }
//
//        //Interact witn Monile element
//        if (appiumDriver !=null){
//            appiumDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//            //implicitlyWait: Chờ tối đa trên page là 30s, ko đồng nghĩa là luôn chờ 30s mà chưa có thì tìm còn có rồi sẽ return
//            System.out.println("Connected to appium server successfully!");
//            MobileElement loginLabel = appiumDriver.findElementByAccessibilityId("Login");
//            loginLabel.click();
//        }else {
//            System.out.println("Error when connect Appium server!");
//        }
//    }
//}
