package com.besteleben.feature_irregularpenguin.service;

import com.besteleben.feature_irregularpenguin.data.repository.SettingsRepository;
import com.besteleben.feature_irregularpenguin.data.objects.ConfigurationData;

/**
 * Service for the Players Configuration
 */
public class SettingsService {
    /** reference to the data source */
    private final SettingsRepository dataSource;
    /**
     * configuration object to communicate between service and controller.
     */
    private ConfigurationData config;

    /**
     * creates the SettingsService with a given datasource
     * @param dataSource the datasource for the settings
     */
    public  SettingsService(SettingsRepository dataSource){
        this.dataSource = dataSource;
        config = new ConfigurationData();
        config = dataSource.loadConfig();
    }

    /**
     * saves the config in the datasource
     */
    public void saveConfig(){
        dataSource.saveConfig(config);
    }

    /**
     * Gets config.
     *
     * @return value of config
     */
    public ConfigurationData getConfig() {
        return config;
    }
}
