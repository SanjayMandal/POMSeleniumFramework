package com.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.joda.time.LocalDateTime;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

	public class Reports {

	static File gb_Obj_directory;
	public static String gb_Str_Basepath;
	static int gb_Int_Location;
	public static ExtentReports reports;
	public static ExtentTest test;
	public static ExtentTest test1;
	public static int counter = 1;

	public static FunctionalLibrary funcLib = new FunctionalLibrary();

	/****************** Get project path *********************/

	public static void getPath() {
	gb_Obj_directory = new File(".");

	try {
	gb_Str_Basepath = gb_Obj_directory.getCanonicalPath();
	} catch (IOException e) {
	e.printStackTrace();
	}
	gb_Int_Location = gb_Str_Basepath.indexOf("SeleniumAutomation");
	gb_Str_Basepath = gb_Str_Basepath.substring(0, gb_Int_Location);

	}

	public static ExtentReports getInstance() {
	if (reports == null) {

	getPath();

	reports = new ExtentReports(
	gb_Str_Basepath + "//SeleniumAutomation//src//main//resources//AutomationSuite//Reports.html", true);

	}

	return reports;
	}

	public static ExtentTest startTest(String sName, String sDesc) {
	test = getInstance().startTest(sName, sDesc);
	return test;

	}

	public static ExtentTest getTest() {
		return test;

	}

	public static void logStep(LogStatus sStatus, String stepName, String sActual,boolean isScreenShot) {

	try {

	File gb_Obj_directory;
	String gb_Str_Basepath;

	String fwdSlash;
	fwdSlash = "\\";

	DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	Date date = new Date();
	String timestamp = dateFormat.format(date).toString();

	if (LogStatus.FAIL.equals(sStatus)) {
	
	gb_Obj_directory = new File(".");
	gb_Str_Basepath = gb_Obj_directory.getCanonicalPath();
	String imgPath = gb_Str_Basepath + "Result" + fwdSlash + "FailureScreenshots_" + timestamp + ".png";
	getTest().log(LogStatus.FAIL, stepName,
	sActual + test.addScreenCapture(funcLib.screenCapture(imgPath)));
	

	}

	else if (LogStatus.PASS.equals(sStatus)) {
		if (isScreenShot) {

			gb_Obj_directory = new File(".");
			gb_Str_Basepath = gb_Obj_directory.getCanonicalPath();
			String imgPath = gb_Str_Basepath + "Result" + fwdSlash + "Screenshots_" + timestamp + ".png";
			getTest().log(LogStatus.PASS, stepName,
			sActual + test.addScreenCapture(funcLib.screenCapture(imgPath)));
			}
		else { 
			getTest().log(LogStatus.PASS, stepName, sActual);
		}
	}	
		else if (LogStatus.INFO.equals(sStatus)) {
			if (isScreenShot) {

				gb_Obj_directory = new File(".");
				gb_Str_Basepath = gb_Obj_directory.getCanonicalPath();
				String imgPath = gb_Str_Basepath + "Result" + fwdSlash + "Screenshots_" + timestamp + ".png";
				getTest().log(LogStatus.INFO, stepName,
				sActual + test.addScreenCapture(funcLib.screenCapture(imgPath)));
				}
			else { 
				getTest().log(LogStatus.INFO, stepName, sActual);
			}
	}

	} catch (IOException e) {
	e.printStackTrace();
	}
	}

	public static void logStep(LogStatus sStatus, String stepName, String sActual) {

	if(LogStatus.PASS.equals(sStatus)){
	//getTest().setDescription("Decription Test");
	getTest().log(LogStatus.PASS, stepName, sActual);
	}
	else if(LogStatus.FAIL.equals(sStatus)){
	getTest().log(LogStatus.FAIL, stepName, sActual);
	}
	else
	getTest().log(LogStatus.UNKNOWN, stepName, sActual);
	}

	public static void close() {

	if (reports != null) {
	reports.endTest(test);
	reports.flush();
	}
	}

	/*************** auto archival ***************/

	public static void Initialize() {

	getPath();
	File dirReport = new File(
	gb_Str_Basepath + "//SeleniumAutomation//src//main//resources//AutomationSuite//Reports.html");
	File dirArchive = new File(gb_Str_Basepath + "//SeleniumAutomation//src//main//resources//AutomationArchive");
	LocalDateTime now = LocalDateTime.now();
	int year = now.getYear();
	int month = now.getMonthOfYear();
	int day = now.getDayOfMonth();
	int hour = now.getHourOfDay();
	int minute = now.getMinuteOfHour();
	int second = now.getSecondOfMinute();

	String snewFilename1 = year + "_" + month + "_" + day + "_" + hour + "_" + minute + "_" + second;

	File f3 = new File(dirArchive + "\\" + snewFilename1);
	//f3.mkdir();
	try {
		if(dirReport.exists())
		{
			f3.mkdir();
			FileUtils.moveFileToDirectory(dirReport, f3, true);
		}
	} catch (IOException e) {
	e.printStackTrace();
	}

	}

	public static boolean createFolder(String sFolderPath) {

	File theDir = new File(sFolderPath);
	if (!theDir.exists()) {
	theDir.mkdir();
	return true;
	} else {
	return false;
	}
	}

	}

	
	
	



