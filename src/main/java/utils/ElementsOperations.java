package utils;

import com.shaft.driver.SHAFT.GUI.WebDriver;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;

public class ElementsOperations {

  public static boolean confirmValueExistenceInTable(String searchString, By tableLocator, WebDriver driver) {
    driver.element().waitUntilPresenceOfAllElementsLocatedBy(tableLocator);
    List<Map<String, String>> tableRowsData = driver.element().getTableRowsData(tableLocator);
    driver.browser().captureScreenshot();
    return tableRowsData.stream()
        .flatMap(map -> map.values().stream())
        .anyMatch(searchString::equals);
  }

}
