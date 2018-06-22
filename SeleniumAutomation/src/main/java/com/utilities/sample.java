package com.utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class sample {
	
	public static void main(String args[])
	{
		ExtentReports reports = new ExtentReports("D:\\sample\\Reports.html",true);
		ExtentTest test = reports.startTest("Sample Report", "Sample Description");
		test.log(LogStatus.PASS, "Step1", "Desc1");
		test.log(LogStatus.FAIL, "Step2", "Desc2");
		test.log(LogStatus.PASS, "Step3", "Desc3");
		test.log(LogStatus.FAIL, "Step4", "Desc5");
		
		reports.endTest(test);
		//reports.flush();
		//reports.close();
		
		ExtentTest test1 = reports.startTest("Sample Report1", "Sample Description1");
		test1.log(LogStatus.PASS, "Step1", "Desc11");
		test1.log(LogStatus.FAIL, "Step2", "Desc22");
		test1.log(LogStatus.PASS, "Step3", "Desc33");
		
	
		reports.endTest(test1);
		reports.flush();
		reports.close();
		
	}
}
