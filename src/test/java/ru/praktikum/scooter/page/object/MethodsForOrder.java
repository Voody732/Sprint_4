package ru.praktikum.scooter.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.EnvConfig;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static ru.praktikum.scooter.page.object.StaticLocators.getCLOSECOOKIE;
import static ru.yandex.praktikum.EnvConfig.EXPECTED_ANSWER_AFTER_SUCCESSFUL_ORDER;

public class MethodsForOrder {
    private final WebDriver driver;
    //локатор для поля Имя
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    //локатор для поля Фамилия
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    //локатор для поля Адрес
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //локатор для поля Станция метро
    private final By metroStationList = By.xpath(".//input[@placeholder='* Станция метро']");
    //локатор для поля Телефон
    private final By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //локадор для кнопки Далее
    private final By onwardButton = By.className("Button_Middle__1CSJM");
    //локатор для поля Дата
    private final By shippingDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //локатор, чтоб закрыть календарь (клик по пустому месту, лучше не придумал)
    private final By toCloseCalendarWindow = By.className("Order_Header__BZXOb");
    //локатор для выпадающего списка времени аренды
    private final By openRentalDurationList = By.className("Dropdown-control");
    //локатор для поля Комментарий для курьера
    private final By commentsForCourierField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //локатор для кнопки Заказать
    private final By orderButtonAfterFillFields = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    //локатор для кнопки Да
    private final By yesButtonInOrder = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Да']");
    //локатор для сообщения об успешном заказе
    private final By actualAnswerAfterSuccessfulOrderPanel = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");
    private final String staticPartOfXpathOfMetroStationChoice = ".//button[@value='";
    private final String staticPartOfXpathOfRentalDurationChoice = ".//div[text()='";
    private final String staticPartOfXpathOfColorOfScooterChoice = ".//label[@for='";


    public MethodsForOrder(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(EnvConfig.BASE_URL);
        driver.findElement(getCLOSECOOKIE()).click();
    }

    public void clickOnOrderButton(By buttonOfOrder) {

        WebElement element = driver.findElement(buttonOfOrder);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(buttonOfOrder).click();
    }

    public void fillFirstPageOfOrder(String name, String surname, String address, int metroStationIndex, String phoneNumber) {

        By metroStationChoice = By.xpath(staticPartOfXpathOfMetroStationChoice + metroStationIndex + "']");
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(metroStationList).click();
        driver.findElement(metroStationChoice).click();
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void clickOnOnwardButton() {

        driver.findElement(onwardButton).click();
    }

    public void fillSecondPageOfOrder(String shippingDate, String rentalDuration, String colorOfScooter, String commentsForCourier) {

        By rentalDurationChoice = By.xpath(staticPartOfXpathOfRentalDurationChoice + rentalDuration + "']");

        By colorOfScooterChoice = By.xpath(staticPartOfXpathOfColorOfScooterChoice + colorOfScooter + "']");
        driver.findElement(shippingDateField).sendKeys(shippingDate);
        driver.findElement(toCloseCalendarWindow).click();
        driver.findElement(openRentalDurationList).click();
        driver.findElement(rentalDurationChoice).click();
        driver.findElement(colorOfScooterChoice).click();
        driver.findElement(commentsForCourierField).sendKeys(commentsForCourier);
    }

    public void clickOnOrderButton() {

        driver.findElement(orderButtonAfterFillFields).click();
    }

    public void clickOnYesButton() {
        driver.findElement(yesButtonInOrder).click();
    }

    public String getActualAnswerAfterSuccessfulOrder() {
        WebElement element = driver.findElement(actualAnswerAfterSuccessfulOrderPanel);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return driver.findElement(actualAnswerAfterSuccessfulOrderPanel).getText().split("\n")[0];
    }

    public void compareAnswersAfterSuccessfulOrder(String actualAnswerAfterSuccessfulOrder) {
        assertEquals("Ожидаемый текст не отображается", EXPECTED_ANSWER_AFTER_SUCCESSFUL_ORDER, actualAnswerAfterSuccessfulOrder);
    }
}