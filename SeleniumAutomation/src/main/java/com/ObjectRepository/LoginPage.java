package com.ObjectRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilities.FunctionalLibrary;

public class LoginPage {

	@FindBy(name="username")
	private WebElement txtUsername;
	
	@FindBy(name="password")
	private WebElement txtPassword;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement btnLogin;
	
	@FindBy(xpath="//a[text()='Register']")
	private WebElement lnkRegister;
	
	@FindBy(xpath="//*[@id='rightPanel']/p")
	private WebElement errorMessage;
	
	public LoginPage(){
		PageFactory.initElements(FunctionalLibrary.driver, this);
	}

	public WebElement getTxtUsername() {
		return txtUsername;
	}

	public void setTxtUsername(WebElement txtUsername) {
		this.txtUsername = txtUsername;
	}

	public WebElement getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(WebElement txtPassword) {
		this.txtPassword = txtPassword;
	}

	public WebElement getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(WebElement btnLogin) {
		this.btnLogin = btnLogin;
	}

	public WebElement getLnkRegister() {
		return lnkRegister;
	}

	public void setLnkRegister(WebElement lnkRegister) {
		this.lnkRegister = lnkRegister;
	}
	public WebElement getErrorMessage() {
		return errorMessage;
	}
}
