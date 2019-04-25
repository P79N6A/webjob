package webfood.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class PropertiesUtil {
	
	private static final String configFile = "/config.properties";
	private static final String mailFile = "/mail.properties";
	
	private static Properties configProperties = getProperties(configFile);;
	private static Properties mailProperties = getProperties(mailFile);
	
	private static Properties getProperties(String filename) {
		Properties properties = new Properties();
		try {
			
			FileInputStream fs=new FileInputStream(new File(PropertiesUtil.class.getResource(filename).toURI())) ;
			properties.load(fs);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	public static String getConfigProperty(String key) {
		if(key == null) {
			return null;
		}
		return configProperties.getProperty(key);
	}
	
	public static String getMailProperty(String key) {
		getProperties(mailFile);
		if(key == null) {
			return null;
		}
		return mailProperties.getProperty(key);
	}
	
	public static Properties getMailProperties() {
		return mailProperties;
	}
	
	public static Properties getConfigProperties() {
		return configProperties;
	}
}
