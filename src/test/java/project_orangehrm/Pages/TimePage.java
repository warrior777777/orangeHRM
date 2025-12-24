package project_orangehrm.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class TimePage extends CommonPage {

    private final By PUNCH_IN_BTN = By.xpath("//button[normalize-space()='In']");
    private final By PUNCH_OUT_BTN = By.xpath("//button[normalize-space()='Out']");
    private final By VIEW_BTN = By.xpath("//button[normalize-space()='View']");

    String DYNAMIC_GRID_CELL = "//div[contains(text(),'%s')]/ancestor::div[@class='oxd-sheet']//input[@placeholder='%s']";

    public TimePage(WebDriver driver) {
        super(driver);
    }

    public TimePage verifyTimePage(String expectedHeader) {
        verifyHeader(expectedHeader);
        return this;
    }

    public TimePage clickPunchIn() {
        clickWhenReady(PUNCH_IN_BTN);
        return this;
    }

    public TimePage clickPunchOut() {
        clickWhenReady(PUNCH_OUT_BTN);
        return this;
    }

    public TimePage clickView() {
        clickWhenReady(VIEW_BTN);
        return this;
    }

    public TimePage typeInTimesheetGrid(String filed, String day, String hours) {
        String DYNAMIC_GRID_CELL = "//div[contains(text(),'%s')]/ancestor::div[@class='oxd-sheet']//input[@placeholder='%s']";
        return this;
    }

    public TimePage navigateToSection(String mainCategory, String subCategory) {
        super.navigateToSection(mainCategory, subCategory);
        return this;
    }

    public TimePage typeInDynamicField(String fieldName, String text) {
        typeInField(fieldName, text);
        return this;
    }

    public TimePage typeInDynamicTextArea(String fieldName, String text) {
        typeInTextArea(fieldName, text);
        return this;
    }

    public TimePage clickAndSelectDropdown(String dropdownName, String visibleText) {
        selectDropdownOption(dropdownName, visibleText);
        return this;
    }

    public TimePage clickSave() {
        save();
        return this;
    }

    public TimePage clickToAdd() {
        add();
        return this;
    }

    public TimePage clickToReset() {
        reset();
        return this;
    }

    public TimePage selectFromList() {
        selectFirstOption();
        return this;
    }

    public TimePage selectDate(String fieldName, String dateValue) {
        enterDate(fieldName, dateValue);
        return this;
    }

    public TimePage searchUser() {
        submit();
        return this;
    }

    public TimePage verifyPageTitle(String expectedTitle) {
        verifyTitle(expectedTitle);
        return this;
    }

    public TimePage clickToSubmit() {
        submit();
        return this;
    }

    public TimePage clickToEdit(String value) {
        edit(value);
        return this;
    }

    public TimePage deleteSpecificValue(String value) {
        delete(value);
        return this;
    }

    public TimePage clickCheckbox(String labelText) {
        clickOnCheckbox(labelText);
        return this;
    }

    public TimePage clickActionStatus(String actionName) {
        clickAction(actionName);
        return this;
    }

    public TimePage verifyBodyContains(String expectedText) {
        assertTextContain(org.openqa.selenium.By.tagName("body"), expectedText);
        return this;
    }

    public TimePage verifySuccessMessage() {
        verifySuccessToast();
        return this;
    }

    public TimePage verifyErrorMessage() {
        verifyErrorToast();
        return this;
    }

    public TimePage verifyRecordExists(String recordName) {
        verifyRecordVisible(recordName);
        return this;
    }

    public TimePage verifyRecordDeleted(String recordName) {
        verifyRecordNotVisible(recordName);
        return this;
    }

    public TimePage verifyFieldErrorMessage(String fieldName, String expectedError) {
        verifyFieldError(fieldName);
        return this;
    }

    public TimePage verifySearchTable() {
        verifyFilterVisible();
        return this;
    }

    public TimePage verifyTextContain(By locator, String expectedText) {
        super.verifyTextContain(locator, expectedText);
        return this;
    }
}
