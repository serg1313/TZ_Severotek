package tests;

import baseTest.BaseTest;
import helper.properties.Properties;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import steps.YandexSteps;

import java.util.List;

/**
 * @author Администратор
 * @date 13.09.2022
 */

public class YandexTests extends BaseTest {
    @Feature("Проверка поиска ноутбуков по заданным параметрам на сайте Яндекс Маркет")
    @DisplayName("Проверка поиска ноутбуков по заданным параметрам на сайте Яндекс Маркет")
    @MethodSource("helper.DataProvider#parameters")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    public void yandexSearchTest(String section, String subSection, List<Integer> prices, String brands) {
        YandexSteps yandexSteps = new YandexSteps(driver);
        yandexSteps.openYandexPage(Properties.urlProperties.yandexExchangeUrl());
        yandexSteps.openCatalog();
        yandexSteps.openSectionAndSubSection(section, subSection);
        yandexSteps.setPrice(prices);
        yandexSteps.setManufacturers(brands);
        yandexSteps.checkFiltersApplyOnItems(brands, prices);
    }
}
