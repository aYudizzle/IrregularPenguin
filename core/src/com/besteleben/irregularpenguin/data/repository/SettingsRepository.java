package com.besteleben.irregularpenguin.data.repository;

import com.besteleben.irregularpenguin.data.objects.ConfigurationData;

/**
 * Interface for the Settings Implementation
 */
public interface SettingsRepository {
    /**
     * load config .properties
     */
    ConfigurationData loadConfig();

    /**
     * save config .properties
     */
    void saveConfig(ConfigurationData config);
}
