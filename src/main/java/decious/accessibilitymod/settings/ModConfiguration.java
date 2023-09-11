package decious.accessibilitymod.settings;

import turniplabs.halplibe.HalpLibe;

import java.io.*;
import java.util.Properties;

public class ModConfiguration {
    private static final String CONFIG_DIRECTORY = "./config/";
    private final Properties defaultProperties;
    private final Properties properties;
    private String configFileName = "";

    public ModConfiguration(String modID, Properties defaultProperties) {
        this.configFileName = modID + ".cfg";
        this.defaultProperties = defaultProperties;
        this.properties = new Properties();
        this.properties.putAll(defaultProperties);
        HalpLibe.LOGGER.info("Config file name: " + this.properties);

        File configFile = new File(getFilePath());
        HalpLibe.LOGGER.info("Config file path: " + configFile.getAbsolutePath());
        try {
            if (!configFile.exists()) {
                HalpLibe.LOGGER.info("Config file does not exist. Creating...");
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();
                writeDefaultConfig(configFile, this.defaultProperties);
            } else {
                loadConfig(configFile, this.properties);
                updateConfig(configFile, this.properties);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeDefaultConfig(File configFile, Properties properties) {
        HalpLibe.LOGGER.info("Writing default config to " + configFile.getAbsolutePath());
        try (OutputStream output = new FileOutputStream(configFile)) {
            properties.store(output, "Default config values");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateConfig(File configFile, Properties properties) {
        HalpLibe.LOGGER.info("Updating config at " + configFile.getAbsolutePath());
        try (OutputStream output = new FileOutputStream(configFile)) {
            properties.store(output, "Updated config values");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadConfig(File configFile, Properties properties) {
        try (InputStream input = new FileInputStream(configFile)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFilePath() {
        return CONFIG_DIRECTORY + configFileName;
    }

    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        this.properties.setProperty(key, value);
    }

    public String getString(String key) {
        return this.properties.getProperty(key);
    }

    public Integer getInt(String key) {
        return Integer.parseInt(this.properties.getProperty(key));
    }

    public Float getFloat(String key) {
        return Float.parseFloat(this.properties.getProperty(key));
    }

    public Boolean getBoolean(String key) {
        return Boolean.parseBoolean(this.properties.getProperty(key));
    }

    // private

    public void writeDefaultConfig() {
        File configFile = new File(getFilePath());
        writeDefaultConfig(configFile, this.defaultProperties);
    }

    public void loadConfig() {
        File configFile = new File(getFilePath());
        loadConfig(configFile, this.properties);
    }

    public void updateConfig() {
        File configFile = new File(getFilePath());
        updateConfig(configFile, this.properties);
    }
}
