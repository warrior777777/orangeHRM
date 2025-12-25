package project_orangehrm.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PIMPage extends CommonPage {

    private final By TERMINATE_EMPLOYMENT = By.xpath("//button[normalize-space()='Terminate Employment']");

    private final String DYNAMIC_NAME_FILED = "//label[normalize-space()='%s']/../following-sibling::div//input[@placeholder='%s']";
    private final String DYNAMIC_SIDEBAR_EMPLOYEE = "//div[contains(@class, 'orangehrm-tabs')]//a[contains(text(), '%s')]";
    private final String DYNAMIC_SECTION_ADD_BUTTON = "//h6[contains(., '%s')]/following-sibling::button[contains(., 'Add')]";


    public PIMPage(WebDriver driver) {
        super(driver);
    }

    public PIMPage verifyPIMPage(String expectedHeader) {
        verifyHeader(expectedHeader);
        return this;
    }

    public PIMPage toggleCreateDetails() {
        toggle();
        return this;
    }

    public PIMPage clickToSection(String value) {
        clickWhenReady(By.xpath(String.format(DYNAMIC_SECTION_ADD_BUTTON, value)));
        return this;
    }

    public PIMPage clickSideBarEmp(String value) {
        clickWhenReady(By.xpath(String.format(DYNAMIC_SIDEBAR_EMPLOYEE, value)));
        return this;
    }
    public PIMPage verifyToSection(String element) {
        verifySection(element);
        return this;
    }

    public PIMPage clickOnEmployee(String firstName) {
        clickRecord(firstName);
        return this;
    }
    public PIMPage navigateToSection(String mainCategory, String subCategory) {
        super.navigateToSection(mainCategory, subCategory);
        return this;
    }
    public PIMPage navigateToSection(String mainCategory) {
        super.navigateToSection(mainCategory);
        return this;
    }

    public PIMPage typeInDynamicField(String fieldName, String text) {
        typeInField(fieldName, text);
        return this;
    }

    public PIMPage typeInDynamicNameFiled(String labelName, String placeholder, String text) {
        typeText(By.xpath(String.format(DYNAMIC_NAME_FILED, labelName, placeholder)),text);
        return this;
    }

    public PIMPage typeInDynamicTextArea(String fieldName, String text) {
        typeInTextArea(fieldName, text);
        return this;
    }

    public PIMPage clickAndSelectDropdown(String dropdownName, String visibleText) {
        selectDropdownOption(dropdownName, visibleText);
        return this;
    }


    public PIMPage clickSave() {
        save();
        return this;
    }

    public PIMPage clickTremEmp() {
        clickWhenReady(TERMINATE_EMPLOYMENT);
        return this;
    }

    public PIMPage closePopups() {
        close();
        return this;
    }

    public PIMPage selectDate(String fieldName, String dateValue) {
        enterDate(fieldName, dateValue);
        return this;
    }

    public PIMPage clickPlus(String value) {
        dynamicPlusBtn(value);
        return this;
    }

    public PIMPage clickToRadioButton(String value) {
        clickRadio(value);
        return this;
    }

    public PIMPage clickToAdd() {
        add();
        return this;
    }

    public PIMPage clickToReset() {
        reset();
        return this;
    }

    public PIMPage searchUser() {
        submit();
        return this;
    }

    public PIMPage selectFromList() {
        selectFirstOption();
        return this;
    }

    public PIMPage clickSideTab(String tabName) {
        clickTab(tabName);
        return this;
    }

    public PIMPage deleteSpecificValue(String value) {
        delete(value);
        return this;
    }

    public PIMPage deleteIfExists(String value) {
        super.deleteIfExists(value);
        return this;
    }

    public PIMPage clickToEdit(String value) {
        edit(value);
        return this;
    }

    public PIMPage verifySuccessMessage() {
        verifySuccessToast();
        return this;
    }

    public PIMPage verifyRecordExists(String recordName) {
        verifyRecordVisible(recordName);
        return this;
    }

    public PIMPage verifyRecordDeleted(String recordName) {
        verifyRecordNotVisible(recordName);
        return this;
    }

    public PIMPage verifyFieldErrorMessage(String fieldName, String expectedError) {
        verifyFieldError(fieldName);
        return this;
    }

    public PIMPage verifySearchTable() {
        verifyFilterVisible();
        return this;
    }
}
