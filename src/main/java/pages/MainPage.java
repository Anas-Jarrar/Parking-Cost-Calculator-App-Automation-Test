package pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;

public class MainPage extends BasePage {
    private WebDriverWait wait;
    @FindBy(xpath = "//select[contains(@class,'form-control')]")
    private WebElement parkingLotDropdown;

    @FindBy(xpath = "//input[@placeholder='Entry Date']")
    private WebElement entryDateInput;

    @FindBy(id="entryTime")
    private WebElement  entryTimeInput ;

    @FindBy(xpath = "//div[contains(@class,'open')]//select[contains(@class,'flatpickr-monthDropdown-months')]")
    private WebElement selectMonth;

    @FindBy(xpath = "//input[@placeholder='Exit Date']")
    private WebElement exitDateInput;

    @FindBy(id="exitTime")
    private WebElement exitTimeInput;

    @FindBy(xpath = "//div[contains(@class,'d-flex')]//button[contains(@class,'btn btn-primary mt-3')]")
    private WebElement calculateBtn;

    @FindBy(id = "resultValue")
    private WebElement theResult;

    @FindBy(id="resultMessage")
    private WebElement TheFailedText;

    @FindBy(id = "reserveOnline")
    private WebElement BookNowBtn;


    public MainPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectParkingType(String type) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOf(parkingLotDropdown));
        Select select = new Select(dropdown);
        select.selectByValue(type);
    }

    public void setEntryDate(LocalDate dateToSelect) throws InterruptedException {
        WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(entryDateInput));
        Thread.sleep(1000);
        dateInput.click();
        WebElement monthSelect = wait.until(ExpectedConditions.visibilityOf(selectMonth));
        Select select = new Select(monthSelect);
        select.selectByVisibleText(dateToSelect.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        String dayAriaLabel = dateToSelect.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " "
                + dateToSelect.getDayOfMonth() + ", " + dateToSelect.getYear();
        WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//div[contains(@class,'open')]//div[contains(@class,'dayContainer')]//span[contains(@aria-label,'"
                        + dayAriaLabel + "')]")));
        Thread.sleep(2000);
        dayElement.click();
    }
    public void setEntryTime(String time) {
        WebElement entryTime = wait.until(ExpectedConditions.visibilityOf(entryTimeInput));
        entryTime.clear();
        entryTime.sendKeys(time);
    }

    public void setExitDate(LocalDate dateToSelect) throws InterruptedException {
        WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(exitDateInput));
        Thread.sleep(1000);
        dateInput.click();
        WebElement monthSelect = wait.until(ExpectedConditions.visibilityOf(selectMonth));
        Select select = new Select(monthSelect);
        select.selectByVisibleText(dateToSelect.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        String dayAriaLabel = dateToSelect.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " "
                + dateToSelect.getDayOfMonth() + ", " + dateToSelect.getYear();
        WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//div[contains(@class,'open')]//div[contains(@class,'dayContainer')]//span[contains(@aria-label,'"
                        + dayAriaLabel + "')]")));
        Thread.sleep(2000);
        dayElement.click();
    }
    public void setExitTime(String time) throws InterruptedException {
        WebElement exitTime = wait.until(ExpectedConditions.visibilityOf(exitTimeInput));
        exitTime.clear();
        exitTime.sendKeys(time);
    }

    public void clickCalculate() throws InterruptedException {
        Thread.sleep(2000);
        WebElement calculateButton = wait.until(ExpectedConditions.elementToBeClickable(calculateBtn));
        calculateButton.click();
    }

    public String getResult() {
        String ActualResult = wait.until(ExpectedConditions.visibilityOf(theResult)).getText();
        return ActualResult;
    }
    public String getFaildText() {
        String ActualFaildText=wait.until(ExpectedConditions.visibilityOf(TheFailedText)).getText();
        return ActualFaildText;
    }
    public void clickBookNow() throws InterruptedException {
        Thread.sleep(2000);
        WebElement BookNowButton = wait.until(ExpectedConditions.elementToBeClickable(BookNowBtn));
        BookNowButton.click();
    }

}
