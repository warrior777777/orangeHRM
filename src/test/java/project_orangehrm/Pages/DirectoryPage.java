package project_orangehrm.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DirectoryPage extends CommonPage {

    private final By DIRECTORY_CARD = By.cssSelector(".orangehrm-directory-card");
    private final By DIRECTORY_CARD_HEADER = By.cssSelector(".orangehrm-directory-card-header");
    private final By NO_RECORDS_FOUND = By.xpath("//span[contains(text(),'No Records Found')]");
    private final By CONTACT_ICON = By.cssSelector(".orangehrm-directory-card i.bi-telephone");

    private final String EMPLOYEE_CARD = "//div[contains(@class,'orangehrm-directory-card')]//p[contains(text(),'%s')]";
    private final String EMPLOYEE_CARD_CLICKABLE = "//div[contains(@class,'orangehrm-directory-card')]//p[contains(text(),'%s')]/ancestor::div[contains(@class,'orangehrm-directory-card')]";

    public DirectoryPage(WebDriver driver) {
        super(driver);
    }

    public DirectoryPage verifyDirectoryPage(String expectedHeader) {
        verifyHeader(expectedHeader);
        return this;
    }

    public DirectoryPage typeInDynamicField(String fieldLabel, String value) {
        typeInField(fieldLabel, value);
        return this;
    }

    public DirectoryPage selectFromList() {
        selectFirstOption();
        return this;
    }

    public DirectoryPage clickAndSelectDropdown(String dropdownName, String visibleText) {
        selectDropdownOption(dropdownName, visibleText);
        return this;
    }

    public DirectoryPage searchUser() {
        submit();
        return this;
    }

    public DirectoryPage clickToReset() {
        reset();
        return this;
    }

    public DirectoryPage verifyRecordExists(String recordName) {
        assertVisible(getLocator(EMPLOYEE_CARD, recordName), "Employee not found: " + recordName);
        return this;
    }

    public DirectoryPage verifySearchTable() {
        assertVisible(DIRECTORY_CARD, "Directory cards not displayed");
        return this;
    }

    public DirectoryPage clickEmployeeCard(String employeeName) {
        clickWhenReady(getLocator(EMPLOYEE_CARD_CLICKABLE, employeeName));
        return this;
    }

    public DirectoryPage verifyEmployeeDetailsPopup() {
        assertVisible(DIRECTORY_CARD_HEADER, "Employee details popup not displayed");
        return this;
    }

    public DirectoryPage verifyNoRecordsFound() {
        assertVisible(NO_RECORDS_FOUND, "No Records Found message not displayed");
        return this;
    }

    public DirectoryPage verifyContactInfoVisible() {
        assertVisible(CONTACT_ICON, "Contact info not visible");
        return this;
    }
}

