package com.besteleben.feature_irregularpenguin.data.repository;

import com.besteleben.feature_irregularpenguin.data.objects.ConfigurationData;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * the implementation of the SettingsRepository Interface
 * this implementation saves the configuration in a .properties file.
 */
public class SettingsRepositoryImpl implements SettingsRepository {

    /**
     * the path to the configuration file
     */
    private static final String CONFIG_FILE_PATH = "settings/config.properties";

    /**
     * load the configuration from the .properties file
     * @return a ConfigurationData object containing player settings
     */
    @Override
    public ConfigurationData loadConfig() {
        ConfigurationData config = new ConfigurationData();
        try(FileReader reader = new FileReader(CONFIG_FILE_PATH)){
            Properties properties = new Properties();
            properties.load(reader);
            String playerName = properties.getProperty("playername");
            config.setPlayerName(playerName);
        }catch(IOException exception){
            System.err.println("Config not found!");
        }
        return config;
    }

    /**
     * saves the configuration to a .properties file
     * @param config the configurationData to save
     */
    @Override
    public void saveConfig(ConfigurationData config) {
        try(FileWriter writer = new FileWriter(CONFIG_FILE_PATH)) {
            Properties properties = new Properties();
            properties.setProperty("playername", config.getPlayerName());
            properties.store(writer,"Player Configuration");
        } catch (IOException e) {
            System.err.println("Unable to write File!");
        }
    }
}
