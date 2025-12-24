package project_orangehrm.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class PerformancePage extends CommonPage {

    private final By VERIFY_ADMINISTRATOR_CARD = By.cssSelector(".orangehrm-card-container");
    private final By VIEW_TRACKER_BTN = By.xpath("//button[i[contains(@class,'bi-eye')]]");
    private final By ACTIVATE_BTN = By.xpath("//button[normalize-space()='Activate']");
    private final By SUBMIT_RATING_BTN = By.xpath("//button[normalize-space()='Submit']");

    private final String VIEW_REVIEW_BTN = "//div[@role='row']//button[i[contains(@class,'bi-eye')]]";

    public PerformancePage(WebDriver driver) {
        super(driver);
    }

    public PerformancePage verifyPerformancePage(String expectedHeader) {
        verifyHeader(expectedHeader);
        return this;
    }

    public PerformancePage navigateToSection(String mainCategory, String subCategory) {
        super.navigateToSection(mainCategory, subCategory);
        return this;
    }

    public PerformancePage navigateToSection(String mainCategory) {
        super.navigateToSection(mainCategory);
        return this;
    }

    public PerformancePage setRatingForKPI(String kpiName, String rating) {
        setRowValue(kpiName, rating);
        return this;
    }

    public PerformancePage clickActionStatus(String actionName) {
        clickAction(actionName);
        return this;
    }

    public PerformancePage clickToEdit(String value) {
        edit(value);
        return this;
    }

    public PerformancePage clickOnRecordLink(String linkText) {
        clickLink(linkText);
        return this;
    }

    public PerformancePage clickAddLog() {
        add();
        return this;
    }

    public PerformancePage clickView(String actionName) {
        super.clickAction(actionName);
        return this;
    }

    public PerformancePage clickViewTracker() {
        clickWhenReady(VIEW_TRACKER_BTN);
        return this;
    }

    public PerformancePage clickViewReview() {
        clickWhenReady(By.xpath(VIEW_REVIEW_BTN));
        return this;
    }

    public PerformancePage clickActivateReview() {
        clickWhenReady(ACTIVATE_BTN);
        return this;
    }

    public PerformancePage submitFinalRating() {
        clickWhenReady(SUBMIT_RATING_BTN);
        return this;
    }

    public PerformancePage searchUser() {
        submit();
        return this;
    }

    public PerformancePage typeInDynamicField(String fieldName, String text) {
        typeInField(fieldName, text);
        return this;
    }

    public PerformancePage typeInDynamicTextArea(String fieldName, String text) {
        typeInTextArea(fieldName, text);
        return this;
    }

    public PerformancePage clickAndSelectDropdown(String dropdownName, String visibleText) {
        selectDropdownOption(dropdownName, visibleText);
        return this;
    }

    public PerformancePage selectDate(String fieldName, String dateValue) {
        typeInField(fieldName, dateValue);
        return this;
    }

    public PerformancePage clickSave() {
        save();
        return this;
    }

    public PerformancePage clickToAdd() {
        add();
        return this;
    }

    public PerformancePage clickToReset() {
        reset();
        return this;
    }

    public PerformancePage selectFromList() {
        selectFirstOption();
        return this;
    }

    public PerformancePage deleteSpecificValue(String value) {
        delete(value);
        return this;
    }

    public PerformancePage deleteIfExists(String value) {
        super.deleteIfExists(value);
        return this;
    }

    public PerformancePage verifySuccessMessage() {
        verifySuccessToast();
        return this;
    }

    public PerformancePage verifyRecordExists(String recordName) {
        verifyRecordVisible(recordName);
        return this;
    }

    public PerformancePage verifyRecordDeleted(String recordName) {
        verifyRecordNotVisible(recordName);
        return this;
    }

    public PerformancePage verifyFieldErrorMessage(String fieldName, String expectedError) {
        verifyFieldError(fieldName);
        return this;
    }

    public PerformancePage verifySearchTable() {
        verifyFilterVisible();
        return this;
    }

    public PerformancePage verifyBodyContains(String expectedText) {
        assertVisible(VERIFY_ADMINISTRATOR_CARD,"Administrator Card Is Not Visible");
        return this;
    }

    public PerformancePage verifyTextContain(By locator, String expectedText) {
        super.verifyTextContain(locator, expectedText);
        return this;
    }
}
