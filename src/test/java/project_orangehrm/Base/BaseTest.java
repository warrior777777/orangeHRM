package project_orangehrm.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import project_orangehrm.Utils.AllureAttachment;
import java.time.Duration;

public class BaseTest {

    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    protected WebDriver driver;

    public WebDriver getDriver() {
        return tlDriver.get();
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(@Optional("edge") String browser) {
        WebDriver currentDriver;

        String headlessProp = System.getProperty("headless");
        boolean isHeadless = headlessProp != null && Boolean.parseBoolean(headlessProp);

        String osName = System.getProperty("os.name").toLowerCase();
        boolean isLinux = osName.contains("linux") || osName.contains("nix") || osName.contains("nux");

        if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--remote-allow-origins=*");

            if (isLinux) {
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
            }

            if (isHeadless) {
                options.addArguments("--headless=new");
                if (!isLinux) {
                    options.addArguments("--window-size=1920,1080");
                }
            }
            currentDriver = new EdgeDriver(options);

        } else if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");

            if (isLinux) {
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
            }

            if (isHeadless) {
                options.addArguments("--headless=new");
                if (!isLinux) {
                    options.addArguments("--window-size=1920,1080");
                }
            }
            currentDriver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();

            if (isLinux) {
                options.addArguments("--width=1920");
                options.addArguments("--height=1080");
            }

            if (isHeadless) {
                options.addArguments("-headless");
            }
            currentDriver = new FirefoxDriver(options);

        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        tlDriver.set(currentDriver);
        this.driver = currentDriver;

        AllureAttachment.setDriver(getDriver());

        if (!isHeadless && !isLinux) {
            try {
                getDriver().manage().window().maximize();
            } catch (Exception e) {
            }
        }

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().get("https://opensource-demo.orangehrmlive.com/");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        WebDriver driver = tlDriver.get();
        if (ITestResult.FAILURE == result.getStatus()) {
            if (driver != null) {
                AllureAttachment.addScreenshot("Screenshot Failure - " + result.getName());
            }
        }
        if (driver != null) {
            driver.quit();
        }
        tlDriver.remove();
    }
}