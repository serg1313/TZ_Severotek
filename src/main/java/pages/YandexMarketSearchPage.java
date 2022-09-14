package pages;

import baseClass.BasePageClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Администратор
 * @date 13.09.2022
 */

public class YandexMarketSearchPage extends BasePageClass {
    public YandexMarketSearchPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * автор Сергей Костенко
     * поле кнопки показать всех производителей ноутбуков
     */
    private By btnShowAll = By.xpath("//span[text()='Показать всё']");
    /**
     * автор Сергей Костенко
     * поле описания товара
     */
    private By itemHeaders = By.xpath("//*[contains(@data-zone-name, 'title')]");

    /**
     * автор Сергей Костенко
     * поле стоимости товара
     */
    private By itemPrices = By.xpath("//*[contains(@data-zone-name, 'title')]//following::div[@data-zone-name='price']");
    /**
     * автор Сергей Костенко
     * поля фильтрации товара по цене
     */
    private By priceFields = By.xpath("//div[@data-auto='filter-range-glprice']//following-sibling::input[1]");

    /**
     * автор Сергей Костенко
     * метод вносит в фильтр поиска ограниченние по стоимости товара ОТ и ДО заданные значения
     */
    public void setPrice(List<Integer> prices) {
        driver.findElements(priceFields).get(0).sendKeys(prices.get(0).toString());
        driver.findElements(priceFields).get(1).sendKeys(prices.get(1).toString());
    }

    /**
     * автор Сергей Костенко
     * Выбирает производителя из списка
     *
     * @param brands список производителей
     */
    public void selectManufacturers(String brands) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Показать всё']")));
        By firstElement = By.xpath("//div[@data-auto='item']");
        String textFirstItemBeforeResults = driver.findElement(firstElement).getText();
        driver.findElement(btnShowAll).click();
        driver.findElement(By.xpath("//label[@role='checkbox']//span[text()='" + brands + "']")).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(firstElement, textFirstItemBeforeResults)));
    }


    /**
     * автор Сергей Костенко
     * Получает название товаров
     *
     * @return Возвращает список с названиями найденных товаров
     */
    public List<String> getFoundItemNames() {
        By pagination = By.xpath("//div[@data-auto='pagination-next']");
        if (driver.findElements(pagination).size() > 0) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(pagination));
        }

        return driver.findElements(itemHeaders).stream()
                .map(x -> x.getText().toLowerCase()).collect(Collectors.toList());
    }

    /**
     * автор Сергей Костенко
     * Получает цены
     *
     * @return Возвращает список c ценами у найденных товаров
     */
    public List<Integer> getFoundItemPrices() {
        return driver.findElements(itemPrices).stream()
                .map(x -> Integer.parseInt(x.getText().split("₽")[0]
                        .replaceAll(" ", "")
                        .replaceAll("₽", ""))).collect(Collectors.toList());
    }

}
