package com.dataproviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.api.utils.CSVReaderUtil;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {
	//data provider needs to return something 
	//[]-single dimensional array
	//[][]- double dimensional array
	//Iterator<>
	@DataProvider(name="LoginAPIDataProvider",parallel=true)
	public static Iterator<UserBean> loginAPIDataProvider() {
		return CSVReaderUtil.loadCSV("testData/LoginCreds.csv");
	}

}
