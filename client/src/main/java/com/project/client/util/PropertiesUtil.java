package com.project.client.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;


public class PropertiesUtil{
    
	private static Logger logger = LogManager.getLogger(PropertiesUtil.class);
	private static Resource urlResource = new ClassPathResource("/url.properties");
	private static Properties urlProps;
	
	static {	
		try {
			urlProps = PropertiesLoaderUtils.loadProperties(urlResource);
		} catch (IOException e) {
			logger.error("Não foi posssível carregar resouce", e);
		}
	}
	
	public static String getUrlValue(String key) {
		return urlProps.getProperty(key);
	}		
    
}
