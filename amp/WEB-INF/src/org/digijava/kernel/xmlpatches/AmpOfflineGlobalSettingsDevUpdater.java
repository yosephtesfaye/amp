package org.digijava.kernel.xmlpatches;

import java.sql.Connection;

import org.dgfoundation.amp.ar.viewfetcher.SQLUtils;
import org.digijava.kernel.persistence.PersistenceManager;
import org.digijava.module.aim.helper.GlobalSettingsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is used by XML Patcher to update amp offline global settings when the enviroment is dev
 *
 * @author Viorel Chihai
 */
public class AmpOfflineGlobalSettingsDevUpdater {
    
    static final String REGISTRY_STG_URL = "https://amp-registry-stg.ampsite.net/";
    static final String OFFLINE_ENABLED = "true";
    
    static final String AMP_DEVELOPMENT_ENV_NAME = "AMP_DEVELOPMENT";

    protected Logger logger = LoggerFactory.getLogger(AmpOfflineGlobalSettingsDevUpdater.class);
    
    private void updateAmpOfflineGlobalSettingsForDev() {
        if (isDevServer()) { 
            PersistenceManager.doInTransaction(s -> {
                s.doWork(connection -> {
                    updateGlobalSettings(connection);
                });
            });
        }
    }

    private void updateGlobalSettings(Connection connection) {
            updateGlobalSettings(connection, GlobalSettingsConstants.AMP_REGISTRY_URL, REGISTRY_STG_URL);
            updateGlobalSettings(connection, GlobalSettingsConstants.AMP_OFFLINE_ENABLED, OFFLINE_ENABLED);
    }

    private void updateGlobalSettings(Connection connection, String name, String value) {
        SQLUtils.executeQuery(connection,
                String.format("UPDATE amp_global_settings SET settingsvalue = '%s' WHERE settingsname ='%s'", 
                        value, name));
    }
    
    private boolean isDevServer() {
        return Boolean.parseBoolean(System.getProperty(AMP_DEVELOPMENT_ENV_NAME));
    }
    
    public static void run() {
        new AmpOfflineGlobalSettingsDevUpdater().updateAmpOfflineGlobalSettingsForDev();
    }
}
