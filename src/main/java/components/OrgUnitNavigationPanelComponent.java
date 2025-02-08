package components;

import com.shaft.driver.SHAFT;
import lombok.Getter;

public class OrgUnitNavigationPanelComponent {

    protected SHAFT.GUI.WebDriver driver;
    @Getter
    protected HorizontalMenusComponent hMComponent;

    public OrgUnitNavigationPanelComponent(SHAFT.GUI.WebDriver driver, HorizontalMenusComponent hMComponent) {
        this.driver = driver;
        this.hMComponent = hMComponent;
    }
}
