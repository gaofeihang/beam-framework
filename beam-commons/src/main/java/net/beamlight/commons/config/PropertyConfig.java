package net.beamlight.commons.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on May 6, 2015
 *
 * @author gaofeihang
 * @since 1.0.0
 */
public class PropertyConfig {
    
    private static final Logger LOG = LoggerFactory.getLogger(PropertyConfig.class);
    
    private static Map<String, PropertyConfig> instances = new HashMap<String, PropertyConfig>();
    
    private Properties properties = new Properties();
    
    public PropertyConfig(String fileName) {
        load(fileName);
    }
    
    public static synchronized PropertyConfig getInstance(String fileName) {
        PropertyConfig config = instances.get(fileName);
        if (config == null) {
            config = new PropertyConfig(fileName);
            instances.put(fileName, config);
        }
        return config;
    }
    
    private void load(String fileName) {
        InputStream inputStream = null;
        try {
            // Read *.properties
            URL url = Thread.currentThread().getContextClassLoader().getResource(fileName);

            if (url != null) {
                inputStream = url.openStream();

            } else {
                // Read *-default.properties
                url = PropertyConfig.class.getClassLoader().getResource(fileName.replace(".properties", "-default.properties"));
                inputStream = url.openStream();
            }
            
            LOG.info("Configuration file: {}.", url);
            
            properties.load(inputStream);
            
        } catch (Exception e) {
            LOG.error("Error on reading configuration file!", e);

        } finally {
            try {
                inputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public String getProperty(String key) {
        String value = System.getProperty(key);
        return value == null ? properties.getProperty(key) : value;
    }

    public String getProperty(String key, String defaultValue) {
        String value = getProperty(key);
        return value == null ? defaultValue : value;
    }
    
}
