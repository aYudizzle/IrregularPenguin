package com.besteleben.irregularpenguin.data.repository;

import com.besteleben.irregularpenguin.data.objects.ConfigurationData;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class SettingsRepositoryImpl implements SettingsRepository {

    private static final String CONFIG_FILE_PATH = "settings/config.properties";

    /**
     * load config .properties
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
     * save config .properties
     *
     * @param config
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
