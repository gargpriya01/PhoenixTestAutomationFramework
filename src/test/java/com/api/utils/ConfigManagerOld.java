package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOld {
	
	private static Properties prop = new Properties(); //Create the object of properties class
	
	private ConfigManagerOld() {
		
	}
	
	static {
		File configFile=new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"config"+File.separator+"config.properties");
		FileReader fileReader;
		try {
			fileReader = new FileReader(configFile);
			prop.load(fileReader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
// WAP to read the Property file from 
	public static String getProperty(String key) throws IOException {
		
		//load the properties file using the load()
		
		return prop.getProperty(key);
		
	}}