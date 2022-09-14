package steps;

import baseClass.BasePageClass;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.MainYandexMarketPage;
import pages.MenuCatalogPage;
import pages.YandexMarketSearchPage;

import java.util.List;

/**
 * @author Администратор
 * @date 13.09.2022
 */

public class YandexSteps extends BasePageClass {

    /**
     * автор Сергей Костенко
     * поле объекта класса поисковика Яндекс Маркет
     */
    private YandexMarketSearchPage marketSearchPage;

    public YandexSteps(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * автор Сергей Костенко
     * метод осуществляет открытие Яндекс браузера
     * @param url
     */
    @DisplayName("Открываем главную страницу Яндекс Маркета")
    @Step("Открываем главную страницу {url}")
    public void openYandexPage(String url) {
        driver.get(url);
    }


    /**
     * автор Сергей Костенко
     * метод осуществляет переход в каталог товаров Яндекс Маркета
     */
    @Step("Переходим в каталог Яндекс Маркета")
    public void openCatalog() {
        new MainYandexMarketPage(driver).goToCatalog();
    }

    /**
     * автор Сергей Костенко
     * метод поочередно осуществляет выбор вида товара и подвида товара
     * @param section вид товара
     * @param subSection подвид товара
     */
    @Step("Переходим в раздел {section} и выбираем раздел {subSection}")
    public void openSectionAndSubSection(String section, String subSection) {
        new MenuCatalogPage(driver)
                .selectSection(section)
                .selectSubSection(subSection);
    }

    /**
     * автор Сергей Костенко
     * метод устанавливает фильтрацию стоимости товара ОТ и ДО
     * @param prices
     */
    @Step("Устанавливаем цену товара в диапазоне {prices}")
    public void setPrice(List<Integer> prices) {
        marketSearchPage = new YandexMarketSearchPage(driver);
        marketSearchPage.setPrice(prices);
    }

    /**
     * автор Сергей Костенко
     * метод производит выбор производителей товара по наименованию
     * @param brand наименование производителя
     */
    @Step("Выбираем производителей ноутбуков {brand}")
    public void setManufacturers(String brand) {
        marketSearchPage.selectManufacturers(brand);
    }

    /**
     * автор Сергей Костенко
     * производит сравнение списка товаров по указанным характеристикам
     * @param brand имя производителя
     * @param prices стоимость
     */
    @Step("Проверить, что все товары соотвествуют фильтру {brand} и {prices} на нескольких страницах")
    public void checkFiltersApplyOnItems(String brand, List<Integer> prices) {
        List<String> namesList = marketSearchPage.getFoundItemNames();
        List<Integer> pricesList = marketSearchPage.getFoundItemPrices();

       // namesList.forEach(System.out::println);

        for(String item : namesList) {
            boolean isNameAsFilter = item.toLowerCase().contains(brand.toLowerCase());
            Assertions.assertTrue(isNameAsFilter, item + " не подходит под именные фильтры");
        }

        for(Integer item: pricesList){
            boolean isPriceAsFilter = item >= prices.get(0) && item <= prices.get(1);
            Assertions.assertTrue(isPriceAsFilter, item + " не подходит по ценовые фильтры");
        }
    }

}






