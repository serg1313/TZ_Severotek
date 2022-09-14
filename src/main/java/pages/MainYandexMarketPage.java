package pages;

import baseClass.BasePageClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Администратор
 * @date 13.09.2022
 */

public class MainYandexMarketPage extends BasePageClass {
    /**
     * автор Сергей Костенко
     * поле кнопки каталог
     */
    protected By btnCatalog = By.xpath("//button[@id='catalogPopupButton']");
    public MainYandexMarketPage(WebDriver webDriver) {
        super(webDriver);
    }
    /**
     * автор Сергей Костенко
     * метод проводит нажатие на кнопку на сайте Яндекс Маркет
     */
    public void goToCatalog() {
        driver.findElement(btnCatalog).click();
    }
}
