package ru.praktikum.scooter;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.EnvConfig;
import ru.praktikum.scooter.page.object.MethodsForFAQ;

import java.time.Duration;


@RunWith(Parameterized.class)
public class AboutImportantTest {
    private WebDriver driver;
    private final int indexOfAnswer;
    private final String expectedTextOfAnswer;

    public AboutImportantTest(int indexOfAnswer, String expectedTextOfAnswer) {
        this.indexOfAnswer = indexOfAnswer;
        this.expectedTextOfAnswer = expectedTextOfAnswer;
    }

    @Parameterized.Parameters (name = "Тестовые данные: {0} {1}")
    public static Object[][] getAnswers() {
        return new Object[][]{
                {0, EnvConfig.ANSWER_ABOUT_COST},
                {1, EnvConfig.ANSWER_ABOUT_ONLY_ONE_SCOOTER},
                {2, EnvConfig.ANSWER_ABOUT_RENT_START},
                {3, EnvConfig.ANSWER_ABOUT_ORDER_TODAY},
                {4, EnvConfig.ANSWER_ABOUT_RENT_PROLONG},
                {5, EnvConfig.ANSWER_ABOUT_CHARGE},
                {6, EnvConfig.ANSWER_ABOUT_ORDER_CANCEL},
                {7, EnvConfig.ANSWER_DELIVERY_LOCATION},
        };
    }

    @Before
    public void StartUp() {
        // WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @Test
    public void checkTextMessageAfterClickTest() {
        var onMainPage = new MethodsForFAQ(driver);
        //Открыли страницу
        onMainPage.openPage();
        //Скроллим
        onMainPage.scrollAndWait();
        //Кликаем по стрелочкам и ждем, пока появится
        onMainPage.clickOnButtonAndWait(indexOfAnswer);
        //Получаем текст ответа
        String actualAnswer = onMainPage.getTextFromAnswer(indexOfAnswer);
        //сравниваем
        onMainPage.compareAnswers(expectedTextOfAnswer, actualAnswer);
    }


    @After
    public void tearsDown() {
        driver.quit();
    }
}