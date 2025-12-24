package project_orangehrm.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class RecruitmentPage extends CommonPage {

    private final By STATUS_FILED = By.cssSelector(".oxd-text--subtitle-2");
    private final By VERIFY_BODY = By.tagName("body");
    private final By DYNAMIC_BTN = By.xpath("//button[normalize-space()='Reject']");

    String DYNAMIC_NAME_FILED = "//label[normalize-space()='%s']/../following-sibling::div//input[@placeholder='%s']";

    public RecruitmentPage(WebDriver driver) {
        super(driver);
    }

    public RecruitmentPage verifyRecruitmentPage(String expectedHeader) {
        verifyHeader(expectedHeader);
        return this;
    }

    public RecruitmentPage clickEyeStatus(String actionName) {
        clickAction(actionName);
        return this;
    }

    public RecruitmentPage uploadResume(String filePath) {
        upload(filePath);
        return this;
    }

    public RecruitmentPage navigateToSection(String mainCategory, String subCategory) {
        super.navigateToSection(mainCategory, subCategory);
        return this;
    }

    public RecruitmentPage navigateToSection(String mainCategory) {
        super.navigateToSection(mainCategory);
        return this;
    }

    public RecruitmentPage typeInDynamicField(String fieldName, String text) {
        typeInField(fieldName, text);
        return this;
    }

    public RecruitmentPage typeInDynamicTextArea(String fieldName, String text) {
        typeInTextArea(fieldName, text);
        return this;
    }

    public RecruitmentPage clickAndSelectDropdown(String dropdownName, String visibleText) {
        selectDropdownOption(dropdownName, visibleText);
        return this;
    }

    public RecruitmentPage clickReject() {
        clickWhenReady(DYNAMIC_BTN);
        return this;
    }

    public RecruitmentPage clickSave() {
        save();
        return this;
    }

    public RecruitmentPage clickToAdd() {
        add();
        return this;
    }

    public RecruitmentPage selectFromList() {
        selectFirstOption();
        return this;
    }

    public RecruitmentPage searchUser() {
        submit();
        return this;
    }

    public RecruitmentPage clickToButton(String value) {
        clickDynamic(value);
        return this;
    }

    public RecruitmentPage typeInDynamicNameFiled(String labelName, String placeholder, String text) {
        typeText(By.xpath(String.format(DYNAMIC_NAME_FILED, labelName, placeholder)),text);
        return this;
    }

    public RecruitmentPage clickToReset() {
        reset();
        return this;
    }

    public RecruitmentPage selectDate(String filed, String day) {
        String DYNAMIC_GRID_CELL = "//div[contains(text(),'%s')]/ancestor::div[@class='oxd-sheet']//input[@placeholder='%s']";
        return this;
    }

    public RecruitmentPage clickEditSwitch() {
        toggleEdit();
        return this;
    }

    public RecruitmentPage deleteSpecificValue(String value) {
        delete(value);
        return this;
    }

    public RecruitmentPage deleteIfExists(String value) {
        super.deleteIfExists(value);
        return this;
    }

    public RecruitmentPage verifySuccessMessage() {
        verifySuccessToast();
        return this;
    }

    public RecruitmentPage verifyRecordExists(String recordName) {
        verifyRecordVisible(recordName);
        return this;
    }

    public RecruitmentPage verifyRecordDeleted(String recordName) {
        verifyRecordNotVisible(recordName);
        return this;
    }

    public RecruitmentPage verifyFieldErrorMessage(String fieldName, String expectedError) {
        verifyFieldError(fieldName);
        return this;
    }

    public RecruitmentPage verifySearchTable() {
        verifyFilterVisible();
        return this;
    }

    public RecruitmentPage verifyBodyContains(String expectedText) {
        assertTextContain(VERIFY_BODY, expectedText);
        return this;
    }

    public RecruitmentPage verifyStatusContains(String expectedStatus) {
        assertTextContain(STATUS_FILED, expectedStatus);
        return this;
    }

    public RecruitmentPage verifyTextContain(By locator, String expectedText) {
        super.verifyTextContain(locator, expectedText);
        return this;
    }
}
