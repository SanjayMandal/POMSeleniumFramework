
/**
 * @author Sanjay
 *
 */

package com.utilities;

import java.io.*;
import java.text.*;
import java.util.*;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.joda.time.LocalDateTime;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;


public class FunctionalLibrary extends Reports{

	public static WebDriver driver = null;
	private static String homeWindow = null;
	
	/****************** Driver Initialization *********************/
	
	public static WebDriver driverInit(String browser) {
		switch(browser){
			case "chrome":
				System.setProperty("webdriver.chrome.driver", "src/main/resources/lib/chromedriver.exe");
				driver = new ChromeDriver();
				break;
			case "ie":
				System.setProperty("webdriver.ie.driver", "src/main/resources/lib/IEDriverServer.exe");
				DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
				capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				capability.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				driver = new InternetExplorerDriver(capability);
				break;
			case "ff":
				driver = new FirefoxDriver();
				break;
			default:
				break;
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		return driver;
	}

	public static void accept_Alert() {
		try {
		   Alert alert = driver.switchTo().alert();
		   alert.accept();
		} 
		catch (Exception e) {
		}
 }

	/** Authenticating the alert
	 * @param actions
	 * @param username
	 * @param password
	 * @return
	 */
	/*public static WebDriver authentication_Alert(String username, String password) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.authenticateUsing(new UserAndPassword(username, password));
		}
		catch (Exception e) {
		}
		return driver;
	}*/

	/** Screen Capture 
	 * @return 
	**/

	public String screenCapture(String imgLocation){
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(imgLocation));
		} 
		catch (IOException e) {
		}
		return imgLocation;
	}
	
	/**
	 * Method to switch to the newly opened window
	 */
	public static void switchToWindow() {
		homeWindow = driver.getWindowHandle();
		for (String window : driver.getWindowHandles()) {
			driver.switchTo().window(window);
		}
	}

	/**
	 * To navigate to the main window from child window
	 */
	public static void switchToMainWindow() {
		for (String window : driver.getWindowHandles()) {
			if (!window.equals(homeWindow)) {
				driver.switchTo().window(window);
				driver.close();
			}
			driver.switchTo().window(homeWindow);
		}
	}

	/**
	 * This method returns the no.of windows present
	 * 
	 * @return
	 */
	public static int getWindowCount() {
		return driver.getWindowHandles().size();
	}

	/****************** frames *********************/

	public static void frames(WebElement frameElement) {
		try{
			driver.switchTo().frame(frameElement);
		}
		catch(Exception e){
		}
	}
	
	public static void switchToDefaultcontent() {
		try {
			driver.switchTo().defaultContent();	
		}
		catch(Exception e){
		}
	}
	
	public static void navigateToUrl(String url) {
		try {
			driver.get(url);
			logStep(LogStatus.PASS, "Launching browser & navigating to url", "Browser has been launched successfully",false);
		} catch (Exception e) {
			logStep(LogStatus.FAIL, "Launching browser & navigating to url", "Not able to launch ", true);

		}
	}
	
	/*public static void navigateToUrl(String url) {
		try {
			driver.navigate().to(url);
		} catch (Exception e) {
		}
	}*/
	
	public static void closeBrowser() {
		try {
			driver.close();
		}
		catch (Exception e) {
		}
	}

	public static void setText(WebElement element, String value) {
		try {
			waitForElementVisibility(element);
			element.clear();
			element.sendKeys(value);
			logStep(LogStatus.PASS, "Entering Text ", "Text has been entered successfully",false);
		}
		catch (Exception e) {
			logStep(LogStatus.FAIL, "Entering Text ","Not able to enter",true);
		}
	}
	
	public static void implicitWait(long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	/**
	 * Verifying the visibility of element only for assert conditions
	 */

	public static boolean isElementPresent(WebElement element) {
		boolean elementPresent = false;
		try {
				waitForElementVisibility(element);
				if (element.isDisplayed()) {
					elementPresent = true;
				}
		}
		catch (Exception e) {
		
		}
		return elementPresent;
	}
	
	public static void click(WebElement element) {
		try{
				waitForElementVisibility(element);
				element.click();
				logStep(LogStatus.PASS, "Click on element ", "Element has been clicked successfully", false);
		} catch (Exception e) {
			logStep(LogStatus.FAIL, "Click on element ", "Not able to click",true);
		}
	}
	public static void click_1(WebElement element) {
		try{
				waitForElementVisibility(element);
				element.click();
				logStep(LogStatus.PASS, "Click on element ", "Element has been clicked successfully", true);
		} catch (Exception e) {
			logStep(LogStatus.FAIL, "Click on element ", "Not able to click",true);
		}
	}
	public static void click_2(WebElement element) {
		try{
				waitForElementVisibility(element);
				element.click();
				logStep(LogStatus.PASS, "Click on element ", "Element has been clicked successfully", true);
		} catch (Exception e) {
			logStep(LogStatus.FAIL, "Click on element ", "Not able to click",true);
		}
	}
	public static void clear(WebElement element) {
		try{
				waitForElementVisibility(element);
				element.clear();
			} catch (Exception e) {
			
		}
	}

	/******************
	 * getting the text from non editable field
	 *********************/

	public static String getText(WebElement element) {
		String text = null;
		try {
				waitForElementVisibility(element);
				if (element.getText() != null){
					text = element.getText();
				}	
		}
		catch (Exception e) {
		}
		return text;
	}

	/******************
	 * getting the text from editable field
	 *********************/

	public static String getValue(WebElement element) {
		String value = null;
		try {
				waitForElementVisibility(element);
				if (element.getAttribute("value") != null){
					value = element.getAttribute("value");
				}
		} 
		catch (NullPointerException e) {
		}
		return value;
	}

	/**
	 * Method to select the option from dropdown by value
	 */
	public static void selectByValue(WebElement element, String value){
		try{
			Select obj_select = new Select(element);
			obj_select.selectByValue(value);
		}
		catch(Exception e){
		}
	}
	
	/**
	 * Method to select the option from dropdown by visible text
	 */
	public static void selectByText(WebElement element, String text){
		try{
			Select obj_select = new Select(element);
			obj_select.selectByVisibleText(text);
		}
		catch(Exception e){
		}
	}
	
	/**
	 * Method to select the option from dropdown by index
	 */
	public static void selectByIndex(WebElement element, int index){
		try{
			Select obj_select = new Select(element);
			obj_select.selectByIndex(index);
		}
		catch(Exception e){
		}
	}

	/**
	 * Method to perform mouseover action on required element
	 * 
	 * @param element
	 */
	public void jsMouseOver(WebElement element) {
		String code = "var fireOnThis = arguments[0];" + "var evObj = document.createEvent('MouseEvents');"
				+ "evObj.initEvent( 'mouseover', true, true );" + "fireOnThis.dispatchEvent(evObj);";
		((JavascriptExecutor) driver).executeScript(code, element);
	}

	/**
	 * Method to wait for page load using javascript
	 */
	public static void jsWaitForPageLoad() {
		String pageReadyState = (String) ((JavascriptExecutor)driver).executeScript("return document.readyState");
		while(!pageReadyState.equals("complete"))
		{
			pageReadyState = (String) ((JavascriptExecutor)driver).executeScript("return document.readyState");
			
		}
	}
	
	/**
	 * To pause execution until get expected elements visibility
	 * 
	 * @param element
	 */
	public static void waitForElementVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * To pause the execution
	 * @throws  
	 */
	public static void pause(int milliSeconds) throws InterruptedException {
		Thread.sleep(milliSeconds);
	}
	
	/**
	 * To create a connectivity to Database and update the scenario execution status
	 */
	public static void createDBConnection(String scName, String scStatus){
		try{
			//convert current date from string to sql date data type
			String currentDate = getCurrentDate();
			DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = dateFormatter.parse(currentDate);
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			
			 String[] tcName = scName.split("-"); 
			 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			 String url = "jdbc:ucanaccess://src\\main\\resources\\lib\\Sabre_Automation_Reports.accdb";
			 Connection conn=DriverManager.getConnection(url);
			 String qry = "INSERT INTO TestCaseStatus (TestCase_Name, TestScenario_Name, Exec_Date, Execution_Status)" + "VALUES (?, ?, ?, ?)";
			 PreparedStatement stment = conn.prepareStatement(qry);
			
			 stment.setString(1, tcName[0]);
			 stment.setString(2, tcName[1]);
			 stment.setDate(3, sqlDate);
			 stment.setString(4, scStatus);
			 stment.executeUpdate();
			 conn.commit();
			 conn.close();
		 }
		 catch(Exception err){
		     System.out.println(err);
		 }
	}
	
	public static String getCurrentDate(){
		LocalDateTime now = LocalDateTime.now();
		int year = now.getYear();
		int month = now.getMonthOfYear();
		int day = now.getDayOfMonth();
		String exec_Date = year + "-" + month+ "-" + day;
		return exec_Date;
	}
	
	public static String getCurrentTime(){
		LocalDateTime now = LocalDateTime.now();
		int hour = now.getHourOfDay();
		int minute = now.getMinuteOfHour();
		int second = now.getSecondOfMinute();
		String exec_StartTime = hour+ ":" + minute+ ":" +second;
		return exec_StartTime;
	}
}


