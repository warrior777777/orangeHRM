package project_orangehrm.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import project_orangehrm.Base.BasePage;

public class CommonPage extends BasePage {

    private final By PAGE_HEADER = By.cssSelector(".oxd-topbar-header-title");
    private final By PAGE_TITLE = By.tagName("h6");
    private final By SEARCH_FILTER = By.cssSelector(".oxd-table-filter");
    private final By SUBMIT_BTN = By.cssSelector("button[type='submit']");
    private final By RESET_BTN = By.xpath("//button[normalize-space()='Reset']");
    private final By ADD_BTN = By.xpath("//button[normalize-space()='Add']");
    private final By SAVE_BTN = By.xpath("//button[normalize-space()='Save']");
    private final By CANCEL_BTN = By.xpath("//button[contains(.,'Cancel')]");
    private final By CONFIRM_DELETE_BTN = By.xpath("//button[contains(.,'Delete')]");
    private final By OK_BTN = By.xpath("//button[normalize-space()='Ok']");
    private final By CONFIRM_BTN = By.xpath("//button[normalize-space()='Confirm']");
    private final By SUCCESS_TOAST = By.xpath("//div[contains(@class, 'oxd-toast--success')]");
    private final By FIRST_OPTION_BTN = By.xpath("//div[@role='listbox']//span");
    private final By INFO_TOAST = By.xpath("//div[contains(@class, 'oxd-toast--info')]");
    private final By ERROR_TOAST = By.xpath("//div[contains(@class, 'oxd-toast--error')]");
    private final By TOGGLE_SWITCH = By.xpath("//span[contains(@class, 'oxd-switch-input')]");
    private final By EDIT_SWITCH_BTN = By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-left']");
    private final By PLUS_BTN = By.xpath("//button[normalize-space()='+']");
    private final By FILE_INPUT = By.cssSelector("input[type='file']");
    private final By DIALOG_SHEET = By.cssSelector(".oxd-dialog-sheet");
    private final By DIALOG_CLOSE = By.cssSelector(".oxd-dialog-close-button");
    private final By BODY_TAG = By.tagName("body");
    private final By CLOSE_POPUPS = By.xpath("//div[@class='oxd-date-input-link --close']");


    private final String VERIFY_SECTION = "//h6[normalize-space()='%s']";
    private final String SIDE_MENU_ITEM = "//a[contains(@class,'oxd-main-menu-item')]/span[text()='%s']";
    private final String TOP_BAR_MENU = "//nav[@aria-label='Topbar Menu']//li[contains(normalize-space(), '%s')]";
    private final String DROPDOWN_MENU = "//ul[@role='menu']//a[contains(normalize-space(), '%s')]";
    private final String DYNAMIC_BTN = "//button[normalize-space()='%s']";
    private final String DYNAMIC_FIELD = "//label[normalize-space()='%s']/../following-sibling::div//input";
    private final String DYNAMIC_TEXTAREA = "//label[normalize-space()='%s']/../following-sibling::div//textarea";
    private final String DYNAMIC_DROPDOWN = "//label[normalize-space()='%s']/../following-sibling::div//div[contains(@class,'oxd-select-text')]";
    private final String DYNAMIC_OPTION = "//div[@role='listbox']//span[contains(normalize-space(), '%s')]";
    private final String DYNAMIC_CHECKBOX = "//label[contains(text(),'%s')]/following-sibling::span[contains(@class,'oxd-checkbox')]";
    private final String DYNAMIC_RADIO_BTN = "//label[contains(., '%s')]//span[contains(@class, 'oxd-radio-input')]";
    private final String DYNAMIC_TABLE_RECORD = "//div[@role='row']//div[normalize-space()='%s']";
    private final String DYNAMIC_SUBSCRIBE_BTN = "//div[normalize-space()='%s']/ancestor::div[@role='row']//button[i[contains(@class, 'bi-person')]]";
    private final String DYNAMIC_EDIT_BTN = "//div[contains(text(),'%s')]/ancestor::div[@role='row']//button[i[contains(@class, 'bi-pencil')]]";
    private final String DYNAMIC_DELETE_BTN = "//div[contains(text(),'%s')]/ancestor::div[@role='row']//button[i[contains(@class, 'bi-trash')]]";
    private final String DYNAMIC_UNIT_DELETE_BTN = "//div[normalize-space()='%s']/ancestor::div[contains(@class, 'oxd-tree-node-content')]//button[i[contains(@class, 'bi-trash')]]";
    private final String DYNAMIC_ERROR_MSG = "//label[normalize-space()='%s']/ancestor::div[contains(@class,'oxd-input-group')]//span";
    private final String DYNAMIC_WIDGET = "//p[normalize-space()='%s']";
    private final String DYNAMIC_SIDE_TAB = "//div[@role='tablist']//a[contains(normalize-space(), '%s')]";
    private final String DYNAMIC_PLUS_BTN = "//div[contains(@class, 'oxd-grid-item') and .//label[contains(normalize-space(), '%s')]]//button[@type='button']";
    private final String DYNAMIC_TOGGLE_SWITCH = "//label[contains(., '%s')]/parent::div/following-sibling::div//span[contains(@class, 'oxd-switch-input')]";
    private final String DYNAMIC_ACTION_BTN = "//div[normalize-space()='%s']/ancestor::div[@role='row']//button[i[contains(@class, 'bi-eye')]]";
    private final String DYNAMIC_LINK = "//a[contains(text(), '%s')]";
    private final String DYNAMIC_ROW_INPUT = "//p[contains(text(), '%s')]/ancestor::div[@role='row']//input";

    public CommonPage(WebDriver driver) {
        super(driver);
    }

    public CommonPage navigateToModule(String moduleName) {
        clickWhenReady(getLocator(SIDE_MENU_ITEM, moduleName));
        return this;
    }

    public CommonPage navigateToSection(String mainCategory, String subCategory) {
        clickWhenReady(getLocator(TOP_BAR_MENU, mainCategory));
        clickWhenReady(getLocator(DROPDOWN_MENU, subCategory));
        return this;
    }

    public CommonPage navigateToSection(String mainCategory) {
        clickWhenReady(getLocator(TOP_BAR_MENU, mainCategory));
        return this;
    }

    public CommonPage waitInSeconds(int seconds) {
        hardWait(seconds);
        return this;
    }

    public CommonPage typeInField(String fieldName, String text) {
        type(getLocator(DYNAMIC_FIELD, fieldName), text);
        return this;
    }

    public CommonPage typeInTextArea(String fieldName, String text) {
        type(getLocator(DYNAMIC_TEXTAREA, fieldName), text);
        return this;
    }

    public CommonPage selectDropdownOption(String dropdownName, String visibleText) {
        clickWhenReady(getLocator(DYNAMIC_DROPDOWN, dropdownName));
        clickWhenReady(getLocator(DYNAMIC_OPTION, visibleText));
        return this;
    }

    public CommonPage selectOption(String visibleText) {
        clickWhenReady(getLocator(DYNAMIC_OPTION, visibleText));
        return this;
    }

    public CommonPage selectFirstOption() {
        clickWhenReady(FIRST_OPTION_BTN);
        return this;
    }

    public CommonPage clickDynamic(String btnText) {
        clickWhenReady(getLocator(DYNAMIC_BTN, btnText));
        return this;
    }

    public CommonPage clickTab(String tabName) {
        clickWhenReady(getLocator(DYNAMIC_SIDE_TAB, tabName));
        return this;
    }

    public CommonPage close() {
        clickWhenReady(CLOSE_POPUPS);
        return this;
    }

    public CommonPage clickRadio(String value) {
        clickWhenReady(getLocator(DYNAMIC_RADIO_BTN, value));
        return this;
    }

    public CommonPage clickSubscribe(String value) {
        clickWhenReady(getLocator(DYNAMIC_SUBSCRIBE_BTN, value));
        return this;
    }

    public CommonPage clickAction(String value) {
        clickWhenReady(getLocator(DYNAMIC_ACTION_BTN, value));
        return this;
    }

    public CommonPage clickOnCheckbox(String labelText) {
        clickWhenReady(getLocator(DYNAMIC_CHECKBOX, labelText));
        return this;
    }

    public CommonPage clickLink(String linkText) {
        clickWhenReady(getLocator(DYNAMIC_LINK, linkText));
        return this;
    }

    public CommonPage clickRecord(String recordName) {
        clickWhenReady(getLocator(DYNAMIC_TABLE_RECORD, recordName));
        return this;
    }

    public CommonPage dynamicPlusBtn(String value) {
        clickWhenReady(getLocator(DYNAMIC_PLUS_BTN, value));
        return this;
    }

    public CommonPage dynamicToggle(String value) {
        clickJS(getLocator(DYNAMIC_TOGGLE_SWITCH, value));
        return this;
    }

    public CommonPage save() {
        clickWhenReady(SAVE_BTN);
        return this;
    }

    public CommonPage submit() {
        clickWhenReady(SUBMIT_BTN);
        return this;
    }

    public CommonPage ok() {
        clickWhenReady(OK_BTN);
        return this;
    }

    public CommonPage add() {
        clickWhenReady(ADD_BTN);
        return this;
    }

    public CommonPage reset() {
        clickWhenReady(RESET_BTN);
        return this;
    }

    public CommonPage cancel() {
        clickWhenReady(CANCEL_BTN);
        return this;
    }

    public CommonPage confirm() {
        clickWhenReady(CONFIRM_BTN);
        return this;
    }

    public CommonPage confirmDeletion() {
        clickWhenReady(CONFIRM_DELETE_BTN);
        return this;
    }

    public CommonPage toggle() {
        clickWhenReady(TOGGLE_SWITCH);
        return this;
    }

    public CommonPage toggleEdit() {
        clickWhenReady(EDIT_SWITCH_BTN);
        return this;
    }

    public CommonPage edit(String value) {
        clickWhenReady(getLocator(DYNAMIC_EDIT_BTN, value));
        return this;
    }

    public CommonPage delete(String value) {
        clickWhenReady(getLocator(DYNAMIC_DELETE_BTN, value));
        clickWhenReady(CONFIRM_DELETE_BTN);
        return this;
    }

    public CommonPage deleteIfExists(String value) {
        safeDelete(getLocator(DYNAMIC_DELETE_BTN, value), CONFIRM_DELETE_BTN);
        return this;
    }


    public CommonPage deleteUnit(String value) {
        clickWhenReady(getLocator(DYNAMIC_UNIT_DELETE_BTN, value));
        clickWhenReady(CONFIRM_DELETE_BTN);
        return this;
    }

    public CommonPage setRowValue(String rowIdentifier, String value) {
        driver.findElement(getLocator(DYNAMIC_ROW_INPUT, rowIdentifier)).sendKeys(value);
        return this;
    }

    public CommonPage enterDate(String fieldName, String dateValue) {
        return typeInField(fieldName, dateValue);
    }

    public CommonPage upload(String filePath) {
        driver.findElement(FILE_INPUT).sendKeys(filePath);
        return this;
    }

    public CommonPage verifyDashboard(String expectedHeader) {
        assertTextContain(PAGE_HEADER, expectedHeader);
        return this;
    }

    public CommonPage verifyHeader(String expectedHeader) {
        assertTextContain(PAGE_HEADER, expectedHeader);
        return this;
    }

    public CommonPage verifyTitle(String expectedTitle) {
        assertTextContain(PAGE_TITLE, expectedTitle);
        return this;
    }

    public CommonPage verifySuccessToast() {
        assertVisible(SUCCESS_TOAST, "Success message not displayed");
        return this;
    }

    public CommonPage verifyInfoToast() {
        softAssertVisible(INFO_TOAST, "Info message not displayed");
        return this;
    }

    public CommonPage verifyErrorToast() {
        softAssertVisible(ERROR_TOAST, "Error message not displayed");
        return this;
    }

    public CommonPage verifyRecordVisibleSuccessHybrid(String recordName) {
        try {
            verifySuccessToast();
        } catch (Throwable e) {
            verifyRecordVisible(recordName);
        }
        return this;
    }

    public CommonPage verifyRecordNotVisibleSuccessHybrid(String recordName) {
        try {
            verifySuccessToast();
        } catch (Throwable e) {
            verifyRecordNotVisible(recordName);
        }
        return this;
    }

    public CommonPage verifyElementVisible(String elementText) {
        assertVisible(getLocator(DYNAMIC_WIDGET, elementText), "Element not visible: " + elementText);
        return this;
    }

    public CommonPage verifySection(String elementText) {
        assertVisible(getLocator(VERIFY_SECTION,elementText),"Element not visible: " + elementText );
        return this;
    }

    public CommonPage verifyRecordVisible(String recordName) {
        assertVisible(getLocator(DYNAMIC_TABLE_RECORD, recordName), "Record not found: " + recordName);
        return this;
    }

    public CommonPage verifyRecordNotVisible(String recordName) {
        assertNotVisible(getLocator(DYNAMIC_TABLE_RECORD, recordName), "Record still visible: " + recordName);
        return this;
    }

    public CommonPage verifyFieldError(String fieldName) {
        assertVisible(getLocator(DYNAMIC_ERROR_MSG, fieldName), "Field error not displayed for: " + fieldName);
        return this;
    }

    public CommonPage verifyFilterVisible() {
        assertVisible(SEARCH_FILTER, "Search filter not visible");
        return this;
    }

    public CommonPage verifyDialogText(String expectedText) {
        assertTextContain(DIALOG_SHEET, expectedText);
        return this;
    }

    public CommonPage verifyBodyContains(String expectedText) {
        assertTextContain(BODY_TAG, expectedText);
        return this;
    }

    public CommonPage verifyTextContain(By locator, String expectedText) {
        assertTextContain(locator, expectedText);
        return this;
    }

    public boolean isElementVisible(String elementText) {
        try {
            return driver.findElement(getLocator(DYNAMIC_WIDGET, elementText)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public CommonPage dismissDialog() {
        clickWhenReady(DIALOG_CLOSE);
        return this;
    }

    public CommonPage waitToastDisappear() {
        assertNotVisible(SUCCESS_TOAST, "Toast still visible");
        return this;
    }

    public void type(By locator, String text) {
        typeText(locator, text);
    }

    protected By getLocator(String xpath, String value) {
        return By.xpath(String.format(xpath, value));
    }
}