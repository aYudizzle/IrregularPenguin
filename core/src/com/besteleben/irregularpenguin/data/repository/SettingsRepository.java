package com.besteleben.irregularpenguin.data.repository;

import com.besteleben.irregularpenguin.data.objects.ConfigurationData;

/**
 * Interface for the Settings Implementation
 */
public interface SettingsRepository {
    /**
     * loads the configugration
     * @return returns a ConfigurationData Object with the players configurations
     */
    ConfigurationData loadConfig();

    /**
     * saves the configuration
     * @param config the configurationData to save
     */
    void saveConfig(ConfigurationData config);
}
