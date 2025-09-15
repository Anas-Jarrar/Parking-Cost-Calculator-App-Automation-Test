package org.example;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BookPage;
import pages.MainPage;

import java.time.LocalDate;

public class AppTest extends BaseTest {

    @Test(priority = 1, enabled = true)
    public void CheckCalculateProcessForDays() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);

        navigate("https://practice.expandtesting.com/webpark");
        mainPage.selectParkingType("Economy");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,400)");
        LocalDate todayDate = LocalDate.now();
        LocalDate tomorrowDate = todayDate.plusDays(1);
        mainPage.setEntryDate(todayDate);
        mainPage.setExitDate(tomorrowDate);
        mainPage.clickCalculate();
        String actualPrice = mainPage.getResult();
        System.out.println("Calculated price = " + actualPrice);
        Assert.assertEquals(actualPrice, "9.00€");
    }

    @Test( priority = 2,enabled = true)
    public void CheckCalculateProcessForMonths() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);

        navigate("https://practice.expandtesting.com/webpark");
        mainPage.selectParkingType("Economy");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,400)");
        LocalDate todayDate = LocalDate.now();
        LocalDate afterMothDate = todayDate.plusDays(30);
        mainPage.setEntryDate(todayDate);
        mainPage.setExitDate(afterMothDate);
        mainPage.clickCalculate();
        String actualPrice = mainPage.getResult();
        System.out.println("Calculated price = " + actualPrice);
        Assert.assertEquals(actualPrice, "234.00€");
    }

    @Test(priority = 3,enabled = true)
    public void CheckExitdatebeforeEntryDate() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);

        navigate("https://practice.expandtesting.com/webpark");
        mainPage.selectParkingType("Economy");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,400)");
        LocalDate todayDate = LocalDate.now();
        LocalDate afterMothDate = LocalDate.now().plusDays(30);
        mainPage.setEntryDate(afterMothDate);
        mainPage.setExitDate(todayDate);
        mainPage.clickCalculate();
        String Actualtext = mainPage.getFaildText();
        System.out.println("Actualtext : " + Actualtext);
        Assert.assertEquals(Actualtext, "The entry date and time must be before the exit date and time!");
    }
    @Test(priority = 4,enabled = true)
    public void CheckExitdateafterEntryDate() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        BookPage bookPage = new BookPage(driver);
        navigate("https://practice.expandtesting.com/webpark");
        mainPage.selectParkingType("Economy");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,400)");
        LocalDate todayDate = LocalDate.now();
        mainPage.setEntryDate(todayDate);
        mainPage.setEntryTime("05:00");
        LocalDate tomorrowDate=LocalDate.now().plusDays(1);
        mainPage.setExitDate(tomorrowDate);
        mainPage.setExitTime("05:00");
        mainPage.clickCalculate();
        mainPage.clickBookNow();
        driver.navigate().back();
        mainPage.clickCalculate();
        mainPage.clickBookNow();
        js.executeScript("window.scrollTo(0,400)");
        String ParkingBooked = bookPage.getParkingBooked();
        System.out.println("ParkingBooked  : " + ParkingBooked);
        Assert.assertEquals(ParkingBooked, "Economy");
        String expectedCheckIn=todayDate+" "+"05:00";
        System.out.println("ActualCheckIn  : " + bookPage.getCheckInDate());
        Assert.assertEquals(bookPage.getCheckInDate(), expectedCheckIn);
        String expectedCheckOut=tomorrowDate+" "+"05:00";
        Assert.assertEquals(bookPage.getCheckOutDate(), expectedCheckOut);
        String expectedTotalPrice="9.00€";
        Assert.assertEquals(bookPage.getTotalPrice(), expectedTotalPrice);
    }


}
