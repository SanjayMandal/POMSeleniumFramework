package com.parabank;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ObjectRepository.LoginPage;
import com.ObjectRepository.RegisterPage;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.FunctionalLibrary;

public class RegisterToParabank extends FunctionalLibrary {

	private static LoginPage login = null;
	private static RegisterPage register = null;
	String baseURL = "http://parabank.parasoft.com/parabank/index.htm";

	@BeforeSuite
	public void initializeReport() {
		Initialize();
	}

	@BeforeTest
	public void startReport() {
		startTest("RegisterToParabank", "Test started");
	}

	@AfterTest
	public void endReport() {
		close();
	}

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeTest(String browser) {
		driverInit(browser);
		navigateToUrl(baseURL);
	}

	@AfterClass
	public void afterTest() {
		closeBrowser();
	}

	@Test (priority =1)
	public void loginToParabank() {
		login = new LoginPage();
		setText(login.getTxtUsername(), "Test");
		setText(login.getTxtPassword(), "Automation");
		click(login.getBtnLogin());
		Assert.assertEquals(login.getErrorMessage().getText(), "The username and password could not be verified.");
		logStep(LogStatus.INFO, "Verify error message", "Verification passed");
		Assert.assertEquals(login.getErrorMessage().getText(), "The username and password could not be verified.","Verification passed");
	}

	@Test(priority = 2)
	public void registerToParabank() {
		register = new RegisterPage();
		click(login.getLnkRegister());
		setText(register.getTxtFirstname(), "Test");
	}

}
