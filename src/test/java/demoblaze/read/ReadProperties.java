package demoblaze.read;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadProperties {

    public static String readProperties(String key) {
        Properties properties = new Properties();

        try {
            FileInputStream fs = new FileInputStream("src/test/local.properties");
            properties.load(fs);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return properties.getProperty(key);
    }
}
