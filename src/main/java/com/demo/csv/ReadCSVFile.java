package com.demo.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile {

	public static void main(String[] args) throws IOException, CsvException {
		
		/*
		 *File csvFile=new File("C:\Users\Priya Garg\eclipse-workspace\PhoenixTestAutomationFramework\src\main\resources\testData\LoginCreds.csv");
		 *FileReader fr=new FileReader(csvFile);
		 * 
		 * 
		 * 
		 */
		
		
		
		//code to read the csv file in java
		
		InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("testDataLoginCreds");
		
		InputStreamReader isr=new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isr);
		
		List<String[]> dataList=csvReader.readAll();
		for(String[] dataArray : dataList) {
			for(String data:dataArray) {
				System.out.print(data+" ");
			}
			System.out.println("");
		}

	}

}
