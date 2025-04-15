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

public class MethodsForFAQ {
    //Локатор для скролла
    public final By scrollToOFAQ = By.xpath(".//div[text()='Вопросы о важном']");
    private final WebDriver driver;


    public MethodsForFAQ(WebDriver driver) {
        this.driver = driver;
    }

    //открытие страницы
    public void openPage() {
        driver.get(EnvConfig.BASE_URL);
        driver.findElement(EnvConfig.CLOSECOOKIE).click();
    }

    //скролл и ождание элемента
    public void scrollAndWait() {
        WebElement element = driver.findElement(scrollToOFAQ);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(element));
    }

    //выбор вопроса из FAQ
    public void clickOnButtonAndWait(int indexOfAnswer) {
        driver.findElement(By.id("accordion__heading-" + indexOfAnswer)).click();
        By panelWithAnswer = By.id("accordion__panel-" + indexOfAnswer);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(panelWithAnswer));
    }

    //получаем текст ответа
    public String getTextFromAnswer(int indexOfAnswer) {
        By panelWithAnswer = By.id("accordion__panel-" + indexOfAnswer);
        return driver.findElement(panelWithAnswer).getText();
    }

    //сравниваем полученный и ожидаемы тексты
    public void compareAnswers(String actualTextOfAnswer, String actualAnswer) {
        assertEquals("Неверный текст", actualTextOfAnswer, actualAnswer);
    }
}
