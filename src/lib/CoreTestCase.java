package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;


public class CoreTestCase extends TestCase{

    private static final String PLATFORM_IOS="ios";
    private static final String PLATFORM_ANDROID="android";

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {

        super.setUp();
        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
        this.getAppiumDriverByPlatformEnv(capabilities);
        this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(Duration seconds)
    {
        driver.runAppInBackground(seconds);
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception
    {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if(platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "7.0");
            capabilities.setCapability("AutomationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "/Users/user/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");
        } else if(platform.equals(PLATFORM_IOS)) {
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 8");
        capabilities.setCapability("platformVersion", "13.2");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("udid", "84980541-DA15-45CB-A2E9-073505A317C3");
        capabilities.setCapability("app", "/Users/user/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
    } else throw new Exception( "Cannot get run platform from env variable. Platform value" + platform);
    return capabilities;
    }

    private void getAppiumDriverByPlatformEnv(Capabilities capabilities) throws Exception
    {
        String platform = System.getenv("PLATFORM");

        if(platform.equals(PLATFORM_ANDROID)) {
            driver = new AndroidDriver(new URL(AppiumURL), capabilities);
        } else if(platform.equals(PLATFORM_IOS)) {
            driver = new IOSDriver(new URL(AppiumURL), capabilities);
        } else throw new Exception( "Cannot set Appium Driver from env variable. Platform value" + platform);

    }

}
