package pages;
import java.time.Duration;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;

public class BookPage extends BasePage {
    private WebDriverWait wait;

    @FindBy(xpath = "//div[contains(@id,'reservationDetails')] //li[contains(@class,'list-group-item d-flex justify-content-between lh-sm')][1]//span[contains(@class,'text-muted')]")
    private WebElement parkingBooked;

    @FindBy(xpath = "//div[contains(@id,'reservationDetails')] //li[contains(@class,'list-group-item d-flex justify-content-between lh-sm')][2]//span[contains(@class,'text-muted')]")
    private WebElement checkInDate;

    @FindBy(xpath = "//div[contains(@id,'reservationDetails')] //li[contains(@class,'list-group-item d-flex justify-content-between lh-sm')][3]//span[contains(@class,'text-muted')]")
    private WebElement checkOutDate;

    @FindBy(xpath="//div[contains(@id,'reservationDetails')] //li[contains(@class,'list-group-item d-flex justify-content-between')][4]//strong")
    private WebElement TotalPrice;

    public BookPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getParkingBooked() {
        WebElement parkingBookedElement = wait.until(ExpectedConditions.visibilityOf(parkingBooked));
        return parkingBookedElement.getText();
    }
    public String getCheckInDate() {
        WebElement checkinDate = wait.until(ExpectedConditions.visibilityOf(checkInDate));
        return checkinDate.getText();
    }
    public String getCheckOutDate() {
        WebElement checkoutDate = wait.until(ExpectedConditions.visibilityOf(checkOutDate));
        return checkoutDate.getText();
    }
    public String getTotalPrice() {
        WebElement totalPrice = wait.until(ExpectedConditions.visibilityOf(TotalPrice));
        return totalPrice.getText();
    }


}
