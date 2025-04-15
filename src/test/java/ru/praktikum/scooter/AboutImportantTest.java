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

    @Parameterized.Parameters
    public static Object[][] getAnswers() {
        return new Object[][]{
                {0, EnvConfig.FIRST_ANSWER},
                {1, EnvConfig.SECOND_ANSWER},
                {2, EnvConfig.THIRD_ANSWER},
                {3, EnvConfig.FOURTH_ANSWER},
                {4, EnvConfig.FIFTH_ANSWER},
                {5, EnvConfig.SIXTH_ANSWER},
                {6, EnvConfig.SEVENTH_ANSWER},
                {7, EnvConfig.EIGHTH_ANSWER},
        };
    }

    @Before
    public void StartUp() {
        // WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @Test
    public void checkTextMessageAfterClick() {
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
