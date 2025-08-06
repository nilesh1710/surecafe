package com.surecafe.Admin;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.surecafe.TestBase.TestBase;

public class LoginPage {	
	
	 public static final Logger log=Logger.getLogger(LoginPage.class.getName());
	
	WebDriver driver;
	public Properties OR = new Properties();

	public LoginPage(WebDriver driver) {
		this.driver = driver;		
		PageFactory.initElements(driver, this);		
	}
	
	@FindBy(xpath = "//input[@name='username']")
	 public static WebElement loginField;	
	
	@FindBy(xpath = "//input[@name='password']")
	 public static WebElement passwordField;
	
	@FindBy(xpath = "//button[@class='btn btn-primary btn-block btn-flat']")
	 public static WebElement signInButton;	
	
	@FindBy(xpath = "//button[contains(text(),'Accept')]")
	 public static WebElement acceptButton;
	public void adminSignIn(String username, String password)  {	
		
		loginField.sendKeys(username);
		passwordField.sendKeys(password);
		try {
		if(acceptButton.isDisplayed()) {
			acceptButton.click();
		}
		}
		catch(NoSuchElementException e) {
			
		}
		signInButton.click();
	}

	

}
