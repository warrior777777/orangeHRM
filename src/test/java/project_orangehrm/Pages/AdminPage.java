package project_orangehrm.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminPage extends CommonPage {


    private final By SEC_SAVE_BTN = By.cssSelector(".orangehrm-card-container:nth-of-type(2) button[type='submit']");
    private final String DYNAMIC_UNIT_DELETE_BTN = "//div[normalize-space()='%s']/ancestor::div[contains(@class, 'oxd-tree-node-content')]//button[i[contains(@class, 'bi-trash')]]";

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public AdminPage verifyAdminPage(String expectedHeader) {
        verifyHeader(expectedHeader);
        return this;
    }

    public AdminPage navigateToSection(String mainCategory, String subCategory) {
        super.navigateToSection(mainCategory, subCategory);
        return this;
    }

    public AdminPage navigateToSection(String mainCategory) {
        super.navigateToSection(mainCategory);
        return this;
    }

    public AdminPage waitInSeconds(int seconds) {
        super.hardWait(seconds);
        return this;
    }

    public AdminPage typeInDynamicField(String fieldName, String text) {
        typeInField(fieldName, text);
        return this;
    }

    public AdminPage typeInDynamicTextArea(String fieldName, String text) {
        typeInTextArea(fieldName, text);
        return this;
    }

    public AdminPage toggleEditMode() {
        toggle();
        return this;
    }

    public AdminPage clickAndSelectDropdown(String dropdownName, String visibleText) {
        selectDropdownOption(dropdownName, visibleText);
        return this;
    }

    public AdminPage clickToSave() {
        save();
        return this;
    }

    public AdminPage clickToSecSave() {
        clickWhenReady(SEC_SAVE_BTN);
        return this;
    }

    public AdminPage clickToCancel() {
        cancel();
        return this;
    }

    public AdminPage clickToRadioButton(String value) {
        clickRadio(value);
        return this;
    }

    public AdminPage clickToAdd() {
        add();
        return this;
    }

    public AdminPage clickToReset() {
        reset();
        return this;
    }

    public AdminPage searchUser() {
        submit();
        return this;
    }

    public AdminPage selectFromList() {
        selectFirstOption();
        return this;
    }

    public AdminPage deleteSpecificValue(String value) {
        delete(value);
        return this;
    }

    public AdminPage deleteIfExists(String value) {
        super.deleteIfExists(value);
        return this;
    }

    public AdminPage deleteSpecificUnit(String value) {
        deleteUnit(value);
        return this;
    }

    public AdminPage clickToEdit(String value) {
        edit(value);
        return this;
    }

    public AdminPage clickToSubscribe(String value) {
        clickSubscribe(value);
        return this;
    }

    public AdminPage verifySuccessMessage() {
        verifySuccessToast();
        return this;
    }

    public AdminPage verifyInfoMessage() {
        verifyInfoToast();
        return this;
    }

    public AdminPage verifyRecordExists(String recordName) {
        verifyRecordVisible(recordName);
        return this;
    }

    public AdminPage verifyRecordDeleted(String recordName) {
        verifyRecordNotVisible(recordName);
        return this;
    }

    public AdminPage verifySearchTable() {
        verifyFilterVisible();
        return this;
    }

    public AdminPage clickToDynamicToggle(String value) {
        dynamicToggle(value);
        return this;
    }

    public AdminPage verifyFieldErrorMessage(String fieldName, String expectedError) {
        verifyFieldError(fieldName);
        return this;
    }
}
