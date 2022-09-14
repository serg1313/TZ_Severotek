package helper;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Администратор
 * @date 13.09.2022
 */

public class DataProvider {
    /**
     *автор Сергей Костенко
     * передает тестовые данные в тестовый метод
     * @return поток аргументов для считывания тестовым методом
     */
    public static Stream<Arguments> parameters() {
        String brand = "Lenovo";
        List<Integer> priceList = Arrays.asList(25000, 30000);
        return Stream.of(
                Arguments.of("Компьютеры", "Ноутбуки", priceList, brand)
        );
    }
}
