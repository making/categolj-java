package am.ik.categolj.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {
    private static final String CONFIG_BASE_NAME = "categolj.properties";

    public static Properties loadProperties() {
        Properties p = new Properties();
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        InputStream s = cl.getResourceAsStream(CONFIG_BASE_NAME);
        try {
            p.load(s);
        } catch (IOException ignored) {
        }
        return p;
    }
}
