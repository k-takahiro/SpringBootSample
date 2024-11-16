package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class Configurator {
    private Map<String, String> config;
    private static Configurator INSTANCE;

    static {
        try {
            INSTANCE = new Configurator();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Configurator() throws IOException {
        // InputStream istream = ClassLoader.getSystemResourceAsStream("config.properties");
        // 先頭のスラッシュなしはエラーとなる
        InputStream istream = getClass().getResourceAsStream("/config.properties");
        // InputStream istream = getClass().getResourceAsStream("config.properties");
        Properties prop = new Properties();
        prop.load(istream);
        config = prop.stringPropertyNames().stream()
        .collect(Collectors.toMap(key -> key, key -> (String)prop.get(key)));
    }

    public static Configurator getInstance() {
        return INSTANCE;
    }

    public String getValueByKey(String key) {
        return config.get(key);
    }
}
