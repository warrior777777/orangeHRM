package project_orangehrm.Base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import project_orangehrm.Utils.AllureAttachment;

import java.time.Duration;
import java.time.LocalDateTime;


public class BasePage {
    protected WebDriver driver;
    protected FluentWait<WebDriver> fluentWait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(WebDriverException.class);
    }

    public void assertVisible(By locator, String message) {
        try {
            fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            Assert.fail(message + " | Locator: " + locator.toString());
        }
    }

    public void assertNotVisible(By locator, String message) {
        Assert.assertTrue(fluentWait.until(ExpectedConditions.invisibilityOfElementLocated(locator)), message);
    }

    public void hoverOverElement(By locator) {
        WebElement element = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        new Actions(driver).moveToElement(element).perform();
    }

    public void assertTextNotContains(By locator, String unexpectedText, String message) {
        String actualText = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
        Assert.assertFalse(actualText.contains(unexpectedText), message);

    }

    public void selectFromList(By locator, String visibleText) {
        WebElement dropdownElement = driver.findElement(locator);
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(visibleText);
    }

    public void typeText(By locator, String text) {
        fluentWait.until(webDriver -> {
            WebElement field = webDriver.findElement(locator);
            field.click();
            field.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
            field.clear();
            field.sendKeys(text);
            return true;
        });
    }


    public void clickWhenReady(By locator) {
        fluentWait.until(webDriver -> {
            WebElement element = webDriver.findElement(locator);
            element.click();
            return true;
        });
    }

    public void assertTextContain(By locator, String expectedText) {
        try {
            fluentWait.until(ExpectedConditions.textToBePresentInElementLocated(locator, expectedText));
        } catch (TimeoutException e) {
            String actualText = driver.findElement(locator).getText();
            String errorMessage = String.format(
                    "Text mismatch for element defined by [%s]. Expected to contain: [%s], but found: [%s]",
                    locator.toString(), expectedText, actualText);
            Assert.fail(errorMessage);
        }
    }

    public void takeScreenshot(String name) {
        AllureAttachment.addScreenshot(name + " - " + LocalDateTime.now());
    }

    public void assertElementCount(By locator, int expectedCount, String elementName) {
        try {
            fluentWait.until(ExpectedConditions.numberOfElementsToBe(locator, expectedCount));
        } catch (TimeoutException e) {
            int actualCount = driver.findElements(locator).size();
            String errorMessage = String.format(
                    "Element count mismatch for '%s' (Locator: %s). Expected: %d, but found: %d",
                    elementName, locator.toString(), expectedCount, actualCount);
            Assert.fail(errorMessage);
        }
    }

    public void clickJS(By locator) {
        WebElement element = fluentWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

    }

    public void clickWithScrollJS(By locator) {
        WebElement element = fluentWait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        fluentWait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void clickAndScrollWithJS(By locator) {
        WebElement element = fluentWait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void scrollAndClick(By locator) {
        WebElement element = fluentWait.until(ExpectedConditions.elementToBeClickable(locator));
        new Actions(driver)
                .moveToElement(element)
                .click()
                .perform();
    }

    public String getValidationMessage(By locator) {
        return fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator))
                .getAttribute("validationMessage");
    }

    public void alertConfirm() {
        fluentWait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    public void verifyFieldIsRequiredErrorMessage(By locator) {
        String message = getValidationMessage(locator);
        Assert.assertFalse(message.isEmpty(), "Expected validation message for " + locator + " but found none.");
    }

}