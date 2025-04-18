package ru.praktikum.scooter;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.praktikum.scooter.page.object.MethodsForOrder;

import java.time.Duration;

import static ru.praktikum.scooter.page.object.StaticLocators.getHeadOrderButton;
import static ru.praktikum.scooter.page.object.StaticLocators.getMiddleOrderButton;

@RunWith(Parameterized.class)
public class OrderTest {
    private final By buttonOfOrder;
    private final String name;
    private final String surname;
    private final String address;
    private final int metroStationIndex;
    private final String phoneNumber;
    private final String shippingDate;
    private final String rentalDuration;
    private final String colorOfScooter;
    private final String commentsForCourier;
    private WebDriver driver;


    public OrderTest(By buttonOfOrder, String name, String surname, String address, int metroStationIndex, String phoneNumber, String shippingDate, String rentalDuration, String colorOfScooter, String commentsForCourier) {
        this.buttonOfOrder = buttonOfOrder;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStationIndex = metroStationIndex;
        this.phoneNumber = phoneNumber;
        this.shippingDate = shippingDate;
        this.rentalDuration = rentalDuration;
        this.colorOfScooter = colorOfScooter;
        this.commentsForCourier = commentsForCourier;
    }

    @Parameterized.Parameters
    public static Object[][] getAnswersInHead() {
        return new Object[][]{
                {getHeadOrderButton(), "Иван", "Иванов", "Красная площадь, 1", 10, "88005553535", "20.05.2025", "двое суток", "black", "Жемчуг"},
                {getHeadOrderButton(), "Степан", "Степанов", "Университетский проспект,5", 17, "88002000600", "29.04.2025", "трое суток", "grey", "Безысходность"},
                {getMiddleOrderButton(), "Иван", "Иванов", "Красная площадь, 1", 10, "88005553535", "20.05.2025", "двое суток", "black", "Жемчуг"},
                {getMiddleOrderButton(), "Степан", "Степанов", "Университетский проспект,5", 17, "88002000600", "29.04.2025", "трое суток", "grey", "Безысходность"},
        };
    }

    @Before
    public void StartUp() {
        //WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @Test
    public void checkOrderOnHeadTest() {
        var orderTest = new MethodsForOrder(driver);
        //открываем страницу
        orderTest.openPage();
        //нажимаем на кнопку заказать
        orderTest.clickOnOrderButton(buttonOfOrder);
        //заполняем поля первой страницы заказа
        orderTest.fillFirstPageOfOrder(name, surname, address, metroStationIndex, phoneNumber);
        //жмём кнопку далее
        orderTest.clickOnOnwardButton();
        //заполняем поля второй страницы заказа
        orderTest.fillSecondPageOfOrder(shippingDate, rentalDuration, colorOfScooter, commentsForCourier);
        //жмём кнопку заказать, которая после заполнения полей
        orderTest.clickOnOrderButton();
        //жмём кнопку да
        orderTest.clickOnYesButton();
        //получаем текст сообщения
        String actualAnswerAfterSuccessfulOrder = orderTest.getActualAnswerAfterSuccessfulOrder();
        //сравниваем ожидаемый и полученный текст сообщения
        orderTest.compareAnswersAfterSuccessfulOrder(actualAnswerAfterSuccessfulOrder);
    }


    @After
    public void tearsDown() {
        driver.quit();
    }
}