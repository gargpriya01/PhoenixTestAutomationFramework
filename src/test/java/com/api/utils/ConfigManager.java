package com.api.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	
	private static Properties prop = new Properties(); //Create the object of properties class
	private static String path="config/config.properties";
	private static String env;
	
	private ConfigManager() {
		
	}
	
	static {
		env=System.getProperty("env","qa");
		env=env.toLowerCase().trim();
		System.out.println("Running tests in Env"+env);
		switch (env){
		case "dev":{
			path="config/config.dev.properties";
		}
		case "qa":{
			path="config/config.qa.properties";
		}
		case "uat":{
			path="config/config.uat.properties";
		}
		default:
			path="config/config.qa.properties";
		
		}
		
		InputStream input=Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		if(path==null) {
			throw new RuntimeException("Cannot find file at the path: "+path);
		}
		try {
			
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
// WAP to read the Property file from 
	public static String getProperty(String key) throws IOException {
		
		//load the properties file using the load()
		
		return prop.getProperty(key);
		
	}}