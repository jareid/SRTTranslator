package com.jreid.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourceUtil {
    private static final Logger LOGGER = LogManager.getLogger(ResourceUtil.class);
    private static final String GOOGLE_RESOURCES = "google";

    //Resource Bundles
    public static final ResourceBundle GOOGLE_BUNDLE = ResourceBundle.getBundle(GOOGLE_RESOURCES);

    /**
     * Returns a string from the resource bundle. We don't want to crash because
     * of a missing String. Returns the key if not found.
     *
     * @param resourceBundle the specific resource bundle to use
     * @param key The key of the resource to get
     */
    public static String getResourceString(ResourceBundle resourceBundle, String key) {
        return getResourceString( resourceBundle, key, null );
    }

    /**
     * Returns a string from the resource bundle and binds it with the given
     * arguments. If the key is not found, return the key
     *
     * @param resourceBundle the specific resource bundle to use
     * @param key The key of the resource to get
     * @param args Argumenbts to bind to the resource
     */
    public static String getResourceString( ResourceBundle resourceBundle,
                                            String key,
                                            Object[] args) {
        String resourceValueFinal;
        if (resourceBundle != null) {
            try {
                String resourceValue = resourceBundle.getString(key);
                if ( args != null ) {
                    resourceValueFinal = resourceValue;
                } else {
                    resourceValueFinal = MessageFormat.format(resourceValue , args );

                }
                LOGGER.debug("Value found: " + resourceValueFinal + " for key: " + key);
                return resourceValueFinal;

            } catch (MissingResourceException mi) {
                LOGGER.error("Missing resource for key: ");
                return key;
            } catch (NullPointerException nullPointerException) {
                LOGGER.error("NPE occurred " + nullPointerException.getMessage());
                return "!" + key + "!";
            }
        } else {
            LOGGER.debug("Properties file was not loaded correctly!!");
        }
        return null;
    }

    public static String getGoogleApiKey() {
        return ResourceUtil.getResourceString(ResourceUtil.GOOGLE_BUNDLE, "apiKey");
    }
}
