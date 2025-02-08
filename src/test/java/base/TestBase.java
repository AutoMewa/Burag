package base;

import com.shaft.driver.DriverFactory;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class TestBase {

   protected SHAFT.GUI.WebDriver driver;
   protected SHAFT.TestData.JSON testData;

   @Step("فتح منصة براق")
   public void openBuragApp() {
       ChromeOptions options = new ChromeOptions();
       options.addArguments("--disable-extensions");
       options.addArguments("--incognito");

        driver = new SHAFT.GUI.WebDriver(DriverFactory.DriverType.CHROME , options);
        driver.browser().navigateToURL(testData.getTestData("appURL"));
    }

    @Step("فتح منصة براق")
    public void openBuragAppWithCustomCapabilities(String directory) {
          // Configure preferences for Chrome
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", directory); // Set custom directory
        prefs.put("download.prompt_for_download", false); // Disable download prompt
        prefs.put("plugins.always_open_pdf_externally", true); // Download PDF instead of opening

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-extensions");
        options.addArguments("--incognito");
        // Add the argument to use the system print dialog
        // options.addArguments("--kiosk-printing");
        driver = new SHAFT.GUI.WebDriver(DriverFactory.DriverType.CHROME , options);
        driver.browser().navigateToURL(testData.getTestData("appURL"));
    }

}
