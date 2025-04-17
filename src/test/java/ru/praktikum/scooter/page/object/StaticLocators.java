package ru.praktikum.scooter.page.object;

import org.openqa.selenium.By;

public class StaticLocators {
    private static final By HEAD_ORDER_BUTTON = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");
    private static final By MIDDLE_ORDER_BUTTON = By.cssSelector(".Button_Middle__1CSJM.Button_Button__ra12g");
    private static final By CLOSECOOKIE = By.xpath(".//button[text()='да все привыкли']");

    public static By getHeadOrderButton() {
        return HEAD_ORDER_BUTTON;
    }
    public static By getMiddleOrderButton(){
        return MIDDLE_ORDER_BUTTON;
    }

    public static By getCLOSECOOKIE() {
        return CLOSECOOKIE;
    }
}
