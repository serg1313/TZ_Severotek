package helper.properties;

import helper.properties.interfaces.UrlProperties;
import org.aeonbits.owner.ConfigFactory;

/**
 * @author Администратор
 * @date 13.09.2022
 */

public class Properties {
    /**
     * автор Сергей Костенко
     * поле для хранения значение url адреса считанного из properies файла
     */
    public static final UrlProperties urlProperties = ConfigFactory.create(UrlProperties.class);
}
