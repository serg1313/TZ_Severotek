package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * @author Администратор
 * @date 13.09.2022
 */

public class BaseTest {

    protected WebDriver driver;

    /**
     * автор Сергей Костенко
     * метод инициализирует драйвер, проводит настройки окна браузера и устанавливает ожидание
     */
    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
    }

    /**
     * автор Сергей Костенко
     * метод закрывает окна браузера и отключает драйвер
     */
    @AfterEach
    void after() {
        driver.quit();
    }

}
