package com.ObjectRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilities.FunctionalLibrary;

public class RegisterPage {

	@FindBy(id="customer.firstName")
	private WebElement txtFirstname;
	
	
	public RegisterPage(){
		PageFactory.initElements(FunctionalLibrary.driver, this);
	}


	public WebElement getTxtFirstname() {
		return txtFirstname;
	}


	public void setTxtFirstname(WebElement txtFirstname) {
		this.txtFirstname = txtFirstname;
	}
}
