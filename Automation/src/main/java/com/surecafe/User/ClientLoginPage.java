package com.surecafe.User;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import com.surecafe.TestBase.TestBase;
import junit.framework.Assert;

public class ClientLoginPage extends TestBase {

	public static final Logger log = Logger.getLogger(ClientLoginPage.class.getName());

	WebDriver driver;
	public Properties OR = new Properties();

	public ClientLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id=\"mat-surecafe-body\"]/app-root/app-layout/div/div/app-login/app-auth/main/div/div/div[2]/div/div[4]/div/div/form/div[1]/input")
	WebElement loginField;
	@FindBy(xpath = "//*[@id=\"mat-surecafe-body\"]/app-root/app-layout/div/div/app-login/app-auth/main/div/div/div[2]/div/div[4]/div/div/form/div[2]/input")
	WebElement passwordField;
	@FindBy(xpath = "//*[@id=\"mat-surecafe-body\"]/app-root/app-layout/div/div/app-login/app-auth/main/div/div/div[2]/div/div[4]/div/div/form/div[3]/button")
	WebElement signInButton;
	@FindBy(xpath = "//button[contains(text(),'Accept')]")
	WebElement acceptButton;
	@FindBy(xpath = "//a[@href='login']")
	WebElement loginLink;
	@FindBy(xpath = "//*[@id=\"idOTP\"]")
	WebElement OTPText1;
	@FindBy(xpath = "//*[@id=\"btnSubmit\"]")
	WebElement otpverifybutton;
	@FindBy(xpath = "//a[@href='https://dev.kredsafe.net/admin/home']")
	WebElement clickHere;
	@FindBy(xpath = "//span[@class=\"val_error\"]")
	List<WebElement> incorrectpassword;
	//@FindBy(xpath = "//form/div[2]/comment()")
	//List<WebElement> incorrectpassword;
	
	@FindBy(xpath = "//*[@id=\"flashMsgTxt\"]")
	WebElement errormsg;
	@FindBy(xpath = "//*[@id=\"zip\"]")
	WebElement zipcode;
	@FindBy(xpath = "//*[@id=\"mail\"]/div/div/table/tbody/tr/td/p[2]")
	WebElement OTPCode;
	@FindBy(xpath = "//*[@id=\"idOTP\"]")
	WebElement idOTP;
	@FindBy(xpath = "//*[@id=\"btnSubmit\"]")
	WebElement verifyButton;

	public void accessYopmaiforOTP() throws InterruptedException {

		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://yopmail.com/");
		WebElement emailField = driver.findElement(By.id("login"));
		emailField.sendKeys("ts1234@yopmail.com");
		WebElement submitYopmail = driver.findElement(By.id("refreshbut"));
		submitYopmail.click();
		wait_for_page_load(20);
		waitInSeconds(10);
		driver.switchTo().frame("ifmail");
		String OTP = OTPCode.getText();
		System.out.printf("Capture OTP code is: " + OTP);
	}

	public void accessYopmaiforOTP(String username) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get(" https://yopmail.com/ ");
		WebElement emailField = driver.findElement(By.id("login"));
		emailField.sendKeys(username);
		WebElement submitYopmail = driver.findElement(By.id("refreshbut"));
		submitYopmail.click();
		wait_for_page_load(20);
		waitInSeconds(10);
		driver.switchTo().frame("ifmail");
		String OTP = OTPCode.getText();
		System.out.printf("Capture OTP code is: " + OTP);
		String code = OTP.substring(99);
		System.out.printf("Extracted OTP code is: " + code);
		String otpcode = code.substring(0, 6);
		System.out.printf("\nOTP code is:" + otpcode);
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("Switch to tab...");
		driver.switchTo().window(tab.get(0));
		idOTP.sendKeys(otpcode);
		verifyButton.click();

	}

	/*
	 * Client Sign In method, pass user name and password from config file Call this
	 * method for login to the application This is the most common reused method
	 */
	public void clientSignIn(String username, String password) throws InterruptedException {

		waitInSeconds(3);

		try {
			if (loginLink.isDisplayed()) {
				loginLink.click();
			}
		} catch (NoSuchElementException e) {

		}
		waitInSeconds(3);
		log.info("Clicking on accept button");
		try {
			if (acceptButton.isDisplayed()) {
				acceptButton.click();
			}
		} catch (NoSuchElementException e) {

		}
		waitInSeconds(3);
		loginField.sendKeys(username);
		passwordField.sendKeys(password);
		wait_in_seconds(5);
		scrollDownToElement(signInButton);
		wait_in_seconds(5);
		signInButton.click();
		wait_in_seconds(5);

		String get_currenturl = driver.getCurrentUrl();
		System.out.println("Current URL is:" + get_currenturl);
		if (get_currenturl.contains("https://dev.kredsafe.net/accept-otp")) {
			System.out.println("The url is  " + get_currenturl);
			accessYopmaiforOTP(username);

		}

	}

	@FindBy(xpath = "//p[contains(text(),'Personal Details')]")
	WebElement personalDetails;
	@FindBy(xpath = "//input[@name='first_name']")
	WebElement firstName;
	@FindBy(xpath = "//input[@name='last_name']")
	WebElement lastName;
	@FindBy(xpath = "//input[@name='dob']")
	WebElement dob;
	@FindBy(xpath = "//input[@val_error_msg='SSN is required.']")
	WebElement SSN;
	@FindBy(xpath = "//input[@ID='phone']")
	WebElement phoneNumber;
	@FindBy(xpath = "//input[@ID='email']")
	WebElement email;
	@FindBy(xpath = "//input[@ID='zip']")
	WebElement zipCode;
	@FindBy(xpath = "//select[@id='country']")
	WebElement country;
	@FindBy(xpath = "//select[@id='state-list']")
	WebElement stateList;
	@FindBy(xpath = "//input[@id='city']")
	WebElement city;
	@FindBy(xpath = "//select[@id='industry_id']")
	WebElement industryType;

	// *[@id="industry_id"]
	@FindBy(xpath = "//select[@id='category']")
	WebElement categoryType;
	@FindBy(xpath = "//button[@id='id_frm_submit']")
	WebElement submitButton;
	@FindBy(xpath = "//span[@class=\"select2-search select2-search--inline\"]")
	WebElement specialitiesSelect;
	@FindBy(xpath = "//li[@class=\"select2-results__option select2-results__option--selectable select2-results__option--highlighted\"]")
	WebElement selectFromDropDown;
	@FindBy(xpath = "//*[@id=\"id_frm_submit\"]")
	WebElement saveAndContinue1;
	@FindBy(xpath = "//*[@id=\"id_frm_submit\"]")
	WebElement License;

	@FindBy(xpath = "//*[@id=\"userNameId\"]")
	WebElement Profilenameclick;
	@FindBy(xpath = "//*[@id=\"oldpassword\"]")
	WebElement oldpw;
	@FindBy(xpath = "//*[@id=\"passwordch\"]")
	WebElement newpw;
	@FindBy(xpath = "//*[@id=\"password_conf\"]")
	WebElement newpwc;
	@FindBy(xpath = "//*[@id=\"id_frm_submit\"]")
	WebElement pwsubmit;
	@FindBy(xpath = "//*[@id=\"loginForm\"]/div[4]/div[2]/a")
	WebElement forgotpassword;
	@FindBy(xpath = "//*[@id=\"email\"]")
	WebElement emailidf;
	@FindBy(xpath = "//*[@id=\"id_frm_submit\"]")
	WebElement submitemail;
	@FindBy(xpath = "//a[contains(@href, 'https://dev.kredsafe.net/user/change-password')]")
	WebElement Changepasswordlink;
	@FindBy(xpath = "//*[@id=\"passwordChangeAlertModal\"]/div/div/div[2]/button\r\n" + "")
	WebElement popok;
	@FindBy(xpath = "/html/body/div[3]/nav/ul[1]/li[2]/span/img")
	WebElement menuclick;
	@FindBy(xpath = "//li[contains(@class,'pendingItemLink')]//a")
	WebElement pendinglink;
	@FindBy(xpath = "/html/body/div[3]/div[1]/section[2]/div/div/div/form/div/div[1]/div[2]/div/div/div/div[3]/ul/li[1]/a")
	WebElement pending;
	@FindBy(xpath = "/html/body/div[3]/aside/div/div[2]/nav/ul/li[6]/a")
	WebElement formclick;
	@FindBy(xpath = "	/html/body/div[3]/aside/div/div[2]/nav/ul/li[7]/a/p\r\n" + "")
	WebElement Packetclick;
	@FindBy(xpath = "//button[@class='button addToCart']")
	WebElement Addtocart;
	@FindBy(xpath = "//button[@title='Add to cart']")
	WebElement PacketAddtocart;
	@FindBy(xpath = "//*[@id=\"example-select-all\"]")
	WebElement boxclick;
	@FindBy(xpath = "/html/body/div[3]/div[1]/section[1]/div/div/div[2]/div/div/div[1]/a")
	WebElement Profilestep;
	@FindBy(xpath = "/html/body/div[3]/div[1]/section[1]/div/div/div[2]/div/div/div[1]/a")
	WebElement Profilesteplink;
	@FindBy(xpath = "//a[@class='remove_me' and @rel='1']")
	WebElement Closebutton;
	@FindBy(xpath = "/html/body/div[12]/div/div/div[3]/button[2]/span")
	WebElement YesOk;
	@FindBy(xpath = "/html/body/div[5]/div/div/div[3]/button[2]/span")
	WebElement YesOk1;
	@FindBy(xpath = "//*[@id=\"id_frm_submit\"]")
	WebElement Submitp;
	@FindBy(xpath = "//*[@id=\"statement\"]")
	WebElement sectioncompleted;
	@FindBy(xpath = "/html/body/div[3]/nav/ul[1]/li[2]/span/img")
	WebElement Menu;
	@FindBy(xpath = "/html/body/div[3]/div[1]/section[2]/div/div/div[1]/div/div[2]/button/span/i")
	WebElement Archive;
	@FindBy(xpath = "//input[@id=\"checkAll\"]")
	WebElement ArchiveAll;
	@FindBy(xpath = "//*[@id=\"documents\"]/tbody/tr[2]/td[1]//input")
	WebElement Archivechk;
	@FindBy(xpath = "//*[@id=\"documents\"]/tbody/tr[4]/td[1]//input")
	WebElement Archivechk1;
	@FindBy(xpath = "//*[@id=\"documents_filter\"]/label/input")
	WebElement SearchArchive;
	@FindBy(xpath = "/html/body/div[3]/div[4]/div/div/div[2]/a/span")
	WebElement Restore;
	@FindBy(xpath = "/html/body/div[3]/aside/div/div[2]/nav/ul/li[2]/a/p")
	WebElement Profileclick;
	@FindBy(xpath = "	/html/body/div[3]/div[1]/section[2]/div/div/div[2]/div/div/div/form/div/div[2]/ul/li[1]/label/a\r\n"
			+ "")
	WebElement Eductionlink;
	@FindBy(xpath = "/html/body/div[3]/div[1]/section[2]/div/div/div[2]/div/div/div/form/div/div[2]/ul/li[2]/label/a")
	WebElement Internship;
	@FindBy(xpath = "//*[@id=\"userSignOutId\"]/span")
	WebElement Signout;
	@FindBy(xpath = "//*[@id=\"activities\"]/tbody/tr")
	List<WebElement> listofactivity;
	@FindBy(xpath = "//*[@id=\"activities\"]/tbody/tr[" + "]")
	WebElement Lastactivityelement;
	@FindBy(xpath = "//*[@id=\"passwordChangeAlertModal\"]/div/div/div[2]/button")
	WebElement OKpopup;
	@FindBy(xpath = "//*[@id=\"email2\"]")
	WebElement PrimarayEmail;
	@FindBy(xpath = "//input[@type='radio' and @value='email2']")
	WebElement MakePrimarayEmail;
	@FindBy(xpath = "/html/body/div[5]/div/div/div[3]/button[2]")
	WebElement YesEmail;
	@FindBy(xpath = "	/html/body/div[3]/div[1]/section[1]/div/div/div[2]/div/div/div[2]/div/a/button\r\n" + "")
	WebElement Boardingbutton;
	@FindBy(xpath = "//*[@id=\"passwordChangeAlertModal\"]/div/div/div[2]/button\r\n" + "")
	WebElement OK;
	@FindBy(xpath = "/html/body/div[3]/div[1]/section[2]/div/div/div[2]/div/div/div/form/div/p/a\r\n" + "")
	WebElement Clear;
	
	@FindBy(xpath = "//a[@href='https://meet.google.com/eee-rbvc-aqr' and text()='Click']")
	WebElement Meetinglink;
	
	@FindBy(xpath = "//a[@href='javascript:void(0);' and normalize-space(text())='Data engineer']")
	WebElement joblink;
	
	@FindBy(xpath = "//a[@href='javascript:void(0);' and normalize-space(text())='Laravel Programmer']")
	WebElement joblink1;
	
	@FindBy(xpath = "//img[@src='assets/image/logo.svg']")
	WebElement logoclick;
	
	
	@FindBy(xpath = "	//*[@id=\"mat-surecafe-body\"]/app-root/app-layout/div[2]/div[2]/app-candidate-dashboard/main/div[2]/div[1]/div/div[1]/input")
	WebElement searchjob;
	


	public void Meetinglink() throws InterruptedException {
		wait_for_page_load(10);
		scrollDownToElement(Meetinglink);
		//Meetinglink.click();
		click_Element_Using_JS(Meetinglink);
		
	}
	
	@FindBy(xpath = "/html/body/app-root/app-layout/div[1]/nav/div/ul/li/a[2]/div/span")
	WebElement profilenameclick;
	
	@FindBy(xpath = "//a[@class='del-acc dropdown-item' and contains(normalize-space(), 'Delete Account')]")
	WebElement accountdelete;
	
	@FindBy(xpath = "//button[normalize-space(text())='Confirm']")
	WebElement confirmdelete;
	
	@FindBy(xpath = "//button[text()='Cancel']")
	WebElement cancle;
	
	
	@FindBy(xpath = "//a[contains(@class, 'dropdown-item') and contains(normalize-space(), 'Log out')]")
	WebElement Logout;
	
	@FindBy(xpath = "//a[contains(@class, 'dropdown-item') and contains(normalize-space(), 'Profile')]")
	WebElement profileview;
	
	
	@FindBy(xpath = "//a[normalize-space(text())='Experience']")
	WebElement Experience;
	
	@FindBy(xpath = "//a[normalize-space(text())='Certification Process']")
	WebElement Certification;
	
	@FindBy(xpath = "//a[normalize-space(text())='Additional Projects']")
	WebElement Projects;
	
	
	@FindBy(xpath = "//a[normalize-space(text())='View Resume']")
	WebElement Viewresume;
	
	@FindBy(xpath = "//a[normalize-space(text())='Education']")
	WebElement Education;
	
	//a[normalize-space(text())='Experience']
	//a[normalize-space(text())='Certification Process']
	//a[normalize-space(text())='Additional Projects']
	//a[normalize-space(text())='View Resume']
	//a[normalize-space(text())='Education']

	
	public void profileview() throws InterruptedException {
		waitInSeconds(10);
		profilenameclick.click();
		//click_Element_Using_JS(profilenameclick);
		wait_in_seconds(10);
		profileview.click();
		//click_Element_Using_JS(Logout);
		waitInSeconds(10);
        scrollDownToElement(Experience);
        click_Element_Using_JS(Experience);
        //Experience.click();
        waitInSeconds(10);
        Certification.click();
        waitInSeconds(10);
        Projects.click();
        waitInSeconds(10);
        Viewresume.click();
        waitInSeconds(10);
        Education.click();
        waitInSeconds(10);
        logoclick.click();
	}
	

	
	public void Logout() throws InterruptedException {
		waitInSeconds(10);
		profilenameclick.click();
		//click_Element_Using_JS(profilenameclick);
		wait_in_seconds(10);
		Logout.click();
		//click_Element_Using_JS(Logout);
		
		
	}

	
	public void accountdelete() throws InterruptedException {
		waitInSeconds(10);
		profilenameclick.click();
		//click_Element_Using_JS(profilenameclick);
		wait_in_seconds(10);
		click_Element_Using_JS(accountdelete);
		wait_in_seconds(10);
		//click_Element_Using_JS(cancle);
		click_Element_Using_JS(confirmdelete);
		
	}
	public void searhjobtitlelink() throws InterruptedException {
		wait_for_page_load(10);
		searchjob.sendKeys("Laravel Programmer");
		//searchjob.click();
		wait_in_seconds(5);
		click_Element_Using_JS(joblink1);
		wait_in_seconds(10);
		click_Element_Using_JS(logoclick);
		wait_for_page_load(10);
		click_Element_Using_JS(joblink);
		wait_in_seconds(10);
		click_Element_Using_JS(logoclick);
		
	}
	public void jobtitlelink() throws InterruptedException {
		wait_in_seconds(5);
		click_Element_Using_JS(joblink);
		wait_in_seconds(10);
		click_Element_Using_JS(logoclick);
		wait_for_page_load(10);
		click_Element_Using_JS(joblink1);
		wait_in_seconds(10);
		click_Element_Using_JS(logoclick);
		
	}
	

	public void personalDetails() throws InterruptedException {

		waitInSeconds(10);
		click_Element_Using_JS(profile);
		click_Element_Using_JS(personalDetails);
		wait_in_seconds(5);
		dob.clear();
		dob.sendKeys("11/11/1990");
		SSN.sendKeys("405633499");
		zipCode.sendKeys("441601");
		waitInSeconds(5);

		Select countryDropdown = new Select(country);
		countryDropdown.selectByVisibleText("United States");
		wait_in_seconds(3);
		// scrollDownToElement(saveAndContinue);
		Select state = new Select(stateList);
		state.selectByVisibleText("Arizona");
		waitInSeconds(5);
		city.clear();
		city.sendKeys("Thanda");

		Select indusrty = new Select(industryType);
		indusrty.getOptions().get(16).click();

		Select category = new Select(categoryType);
		// category.getOptions().get(2).click();
		// specialitiesSelect.click();

		waitInSeconds(10);
		// wait_for_element_present(selectFromDropDown);
		// selectFromDropDown.click();

		scrollDownToElement(saveAndContinue1);
		wait_in_seconds(5);
		scrollDown();
		saveAndContinue1.click();
		// Clear.click();
		int list = listofactivity.size();
		WebElement inputField = driver.findElement(By.xpath("//*[@id=\"activities\"]/tbody/tr[" + (list) + "]"));
		scrollToElement(inputField);

	}

	@FindBy(xpath = "//input[@type=\"email\"]")
	WebElement gemail;
	
	@FindBy(xpath = "//input[@type=\"password\"]")
	WebElement passwordgmail;
	

	@FindBy(xpath = "//*[@id=\"yDmH0d\"]/div[1]/div[1]/div[2]/div/div/div[2]/div/div/div[1]/form/span/section/div/div/div/div/ul/li[1]/div/div[1]/div/div[2]/div[1]\r\n"
			+ "")
	// *[@id="yDmH0d"]/div[1]/div[1]/div[2]/div/div/div[2]/div/div/div[1]/form/span/section/div/div/div/div/ul/li[1]/div/div[1]/div/div[2]/div[1]
	WebElement Googleemail;

	@FindBy(xpath = "//*[@id=\"container\"]/div/div[2]")
	WebElement googleSignin;
	
	@FindBy(xpath = "//iframe[contains(@src, 'accounts.google.com/gsi/button')]")
	WebElement Googlefirefox;
	@FindBy(xpath = "//*[@id=\"identifierNext\"]/div/button/span")
	WebElement next;
	@FindBy(xpath = "//*[@id=\"passwordNext\"]/div/button/span")
	WebElement submitgmail;
	
	

	@FindBy(xpath = "//a[@ href=\"https://www.gladdensociety.org/grants/\"][1]")
	WebElement gl;

	//@FindBy(xpath = "/html/body/header/div[2]/div/div[3]/div/div/div/nav[1]/ul/li[4]/ul/li[1]/a")
	//WebElement gll;
	
	
	@FindBy(xpath = "//a[@href='https://www.gladdensociety.org/grants/jrgos-grant-request-application/' and text()='JRGOS Gladden Funding/Sponsorship Request Application']")
	WebElement gll;
	
	
	@FindBy(xpath = "//a[@href=\"https://www.gladdensociety.org/sponsorships/\"]")
	WebElement sp;
	// a[@href="https://www.gladdensociety.org/sponsorships/"]
	//@FindBy(xpath = "/html/body/header/div[2]/div/div[3]/div/div/div/nav[1]/ul/li[11]/ul/li[1]/a")
	WebElement spm;
	@FindBy(xpath = "//a[@href='https://www.gladdensociety.org/sponsorships/' and text()='Sponsor JRGOS Annual Luncheon and Symposiums']")
	WebElement spm1;
	
	public void glydon() throws InterruptedException, AWTException {
	//wait_for_element_present(sp);
				// click_Element_Using_JS(sp);
				// waitInSeconds(5);
				// wait_for_element_present(spm);
				// click_Element_Using_JS(spm1);
				 wait_for_element_present(gl);
				 click_Element_Using_JS(gl);
				  			 
				 
				 waitInSeconds(10);
			//wait_for_element_present(gll);
				 click_Element_Using_JS(gll);
				// mouseover_Element_Using_JS(gl);
				// Select sponship = new Select(gl);
		
	}
	@FindBy(xpath = "//span[@class='align-middle' and text()='Jobs']")
	WebElement jobclick;
	
	@FindBy(xpath = "//button[contains(@class, 'cust-btn') and contains(normalize-space(text()), 'Apply Now')]")
	WebElement Applyclick;
	
	
	public void applyjob() throws InterruptedException, AWTException {
		       wait_for_page_load(10);
		       wait_for_element_present(jobclick);
		       click_Element_Using_JS(jobclick);
		       wait_for_page_load(10);
		       wait_for_element_present(Applyclick);
		       click_Element_Using_JS(Applyclick);
		       wait_in_seconds(10);
		       click_Element_Using_JS(logoclick);
		}
	
	@FindBy(xpath = "//iframe[@title='Sign in with Google Button' and contains(@src, 'accounts.google.com/gsi/button')]")
	WebElement ifmail1;
	
	@FindBy(xpath = "//iframe[contains(@src, 'accounts.google.com/gsi/button')]")
	WebElement ifmail;
	@FindBy(xpath = "//*[@id=\"container\"]/div/div[1]")
	WebElement Googlef;
	@FindBy(xpath = "//*[@id=\"container\"]/div/div[2]")
	WebElement Googlef1;
	public void Googlelogin() throws InterruptedException, AWTException {
		wait_for_page_load(10);
	  	    WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@src, '')]"));
	        driver.switchTo().frame(iframe);
		// Wait for the Google button and click it
	  //wait_in_seconds(10);
	  googleSignin.click();
		//click_Element_Using_JS(googleSignin);
		// Get all window handles and switch to the new one
		Set<String> allWindows = driver.getWindowHandles();
		String mainWindow = driver.getWindowHandle();

		for (String windowHandle : allWindows) {
		    if (!windowHandle.equals(mainWindow)) {
		        driver.switchTo().window(windowHandle);
		        break;
		    }
		}

		waitInSeconds(10);

		// Wait for the Gmail input, fill it in, click next
		wait_for_element_present(gemail);
		gemail.sendKeys("nshekdar@tiuconsulting.com");
		next.click();

		// Enter the password and click submit
		wait_for_element_present(passwordgmail);
		passwordgmail.sendKeys("ddd");
		submitgmail.click();

	}
	public void Googleloginfirefox() throws InterruptedException, AWTException {
		wait_for_page_load(10);
	  	    WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@src,'accounts.google.com/gsi/button')]"));
	  	   // wait_for_element_present(ifmail);
	       driver.switchTo().frame(ifmail);
	      	// Wait for the Google button and click it
	   wait_in_seconds(10);
	   WebElement Googlef1 = driver.findElement(By.className("//span[text()='Sign in with Google']/ancestor::button\""));

	   Googlef1.click();
      // click_Element_Using_JS(Googlef1);
		      //click_Element_Using_JS(Googlef1);
	  	// Get all window handles and switch to the new one
		Set<String> allWindows = driver.getWindowHandles();
		String mainWindow = driver.getWindowHandle();
		for (String windowHandle : allWindows) {
		    if (!windowHandle.equals(mainWindow)) {
		        driver.switchTo().window(windowHandle);
		        break;
		    }
		    
		}
		waitInSeconds(10);

		// Wait for the Gmail input, fill it in, click next
		wait_for_element_present(gemail);
		gemail.sendKeys("nshekdar@tiuconsulting.com");
		next.click();

		// Enter the password and click submit
		wait_for_element_present(passwordgmail);
		passwordgmail.sendKeys("dddd");
		submitgmail.click();

	}
	public void Googleloginfirefox1() throws InterruptedException, AWTException {
		 wait_for_page_load(10);

		    // ⚠️ Make sure 'ifmail' is properly initialized — example below
		    WebElement ifmail = driver.findElement(By.xpath("//iframe[contains(@src, '')]"));
		    driver.switchTo().frame(ifmail);

		    wait_in_seconds(10); // Wait for iframe to fully load

		    // Click the Google Sign-In button
		  //  WebElement Googlef1 = driver.findElement(By.xpath("//div[@id='g_id_onload' or contains(@class,'g_id_signin')]"));
		    Googlef1.click(); 
		    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			System.out.println("Switch to tab...");
			driver.switchTo().window(tabs.get(1));// Or use JavaScript if normal click fails

		    // Switch back to main page before window switching
		    driver.switchTo().defaultContent();

		    // Wait for the new window (Google login) to open
		    Thread.sleep(3000);  // Better to use WebDriverWait with number of windows > 1

		    // Store main window handle
		    String mainWindow = driver.getWindowHandle();
		    Set<String> allWindows = driver.getWindowHandles();

		    // Switch to the Google login popup
		    for (String windowHandle : allWindows) {
		        if (!windowHandle.equals(mainWindow)) {
		            driver.switchTo().window(windowHandle);
		            break;
		        }
		    }

		    // Now you're in the Google login popup
		    // Automate the email and password input (if Google allows — often CAPTCHA blocks automation)

		    WebElement emailField = driver.findElement(By.id("identifierId"));
		    emailField.sendKeys("your.email@gmail.com");

		    driver.findElement(By.id("identifierNext")).click();

		    Thread.sleep(2000);  // Wait for password field
		    WebElement passwordField = driver.findElement(By.name("password"));
		    passwordField.sendKeys("yourPassword");

		    driver.findElement(By.id("passwordNext")).click();

		    // Wait for redirection
		    Thread.sleep(5000);

		    // Switch back to the main window
		    driver.switchTo().window(mainWindow);

		    // Now the user should be logged in
			
	}
	
	
	@FindBy(xpath = "/html/body/app-root/app-layout/div/div/app-login/app-auth/main/div/div/div[2]/div/div[4]/div/div/div/div[1]/button/span")
	WebElement facebook;
	
	@FindBy(xpath = "//*[@id=\"email\"]")
	WebElement facebookemail;
	
	@FindBy(xpath = "//*[@id=\"pass\"]")
	WebElement facebookpassword;
	
	@FindBy(xpath = "//*[@name='login' and @type='submit']")
	
	WebElement facebooklogin;
	
	//*[@id="email"]
	
	public void Facebooksigin() throws InterruptedException, AWTException {
		waitInSeconds(10);
		wait_for_page_load(10);
		//WebElement ifmail = driver.findElement(By.xpath("//iframe[contains(@src, '')]"));
		//driver.switchTo().frame(ifmail);
		wait_for_element_present(facebook);
		click_Element_Using_JS(facebook);
		// Get all window handles
		Set<String> allWindows = driver.getWindowHandles();
		// Store the current window handle
		String mainWindow = driver.getWindowHandle();

		// Switch to the new window
		for (String windowHandle : allWindows) {
			if (!windowHandle.equals(mainWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		wait_in_seconds(10);
		// wait_for_element_present(Googleemail);
		// click_Element_Using_JS(Googleemail);
		wait_for_element_present(facebookemail);
		
		facebookemail.sendKeys("nshekdar@tiuconsulting.com");
		facebookpassword.sendKeys("nshekdar@tiuconsulting.com");
		//wait_for_element_present(facebooklogin);
		scrollDownToElement(facebooklogin);
		click_Element_Using_JS(facebooklogin);
		//facebooklogin.click();
		
		
	}
	
	
	

	private void mouseover_Element_Using_JS(WebElement gl2) {
		// TODO Auto-generated method stub

	}

	//@FindBy(xpath = "(//a[text()='Sign Up'])[1]")
	@FindBy(xpath = "//a[text()='Sign Up']")
		WebElement Signup;
	@FindBy(xpath = "//*[@id=\"first_name\"]")
	WebElement firstname;
	@FindBy(xpath = "//*[@id=\"last_name\"]")
	WebElement lastname;
	@FindBy(xpath = "//*[@id=\"mobile\"]")
	WebElement mobilen;
	@FindBy(xpath = "//*[@id=\"email\"]")
	WebElement emails;
	@FindBy(xpath = "//*[@id=\"password\"]")
	WebElement password;
	@FindBy(xpath = "//*[@id=\"c_password\"]")
	WebElement cpassword;
	@FindBy(xpath = "	//*[@id=\"mat-surecafe-body\"]/app-root/app-layout/div/div/app-signup/app-auth/main/div/div/div[2]/div/div[4]/div/div/form/div[7]/input\r\n"
			+ "")
	WebElement checkbox;
	@FindBy(xpath = "//a[text()='Sign Up']")
	WebElement signup;
	
	@FindBy(xpath = "//button[text()='Sign Up']")
	WebElement signupbutton;

	//*[@id="mat-surecafe-body"]/app-root/app-layout/div/div/app-signup/app-auth/main/div/div/div[2]/div/div[4]/div/div/form/div[8]/button
	public void Signup(String FirstName, String LastName, String createAccountPassword, String createAccountcPassword,
			String Mobile, String Email) throws InterruptedException {
		// waitInSeconds(10);
		wait_for_page_load(10);
		scrollDownToElement(signup);
		signup.click();
       wait_in_seconds(10);
       //click_Element_Using_JS(Signup);
		wait_for_element_present(firstname);
		firstname.sendKeys(FirstName);
		lastname.sendKeys(LastName);
		mobilen.sendKeys(Mobile);
		emails.sendKeys(Email);
		password.sendKeys(createAccountPassword);
		cpassword.sendKeys(createAccountcPassword);
		click_Element_Using_JS(checkbox);
		wait_in_seconds(10);
		scrollDownToElement(signupbutton);
		waitInSeconds(5);
		signupbutton.click();
		waitInSeconds(10);
		//click_Element_Using_JS(signupbutton);
		//click_Element_Using_JS(signupbutton);
	}

	@FindBy(xpath = "//span[text()='Profile']")
	WebElement profile;
	@FindBy(xpath = "//div[@id='home']//form//span/i")
	WebElement Uploadimage;
	//@FindBy(xpath = "//*[@id=\"home\"]/form/div/div[3]/div[1]/div[1]/input")
	@FindBy(xpath = "//input[@type='file' and contains(@class, 'inpt140')]")
	WebElement Uploadresume;
	
	@FindBy(xpath = "//input[@type='button' and @value='Next']")
	WebElement next1;
	
	@FindBy(xpath = "//input[@type='button' and @value='Next']")
	WebElement next2;
	@FindBy(xpath = "//input[@type='submit' and @value='Submit' and contains(@class, 'cust-btn')]")
	WebElement submitp;
	
	@FindBy(xpath = "//select[@formcontrolname='country_id']")
	WebElement countrys;
	
	@FindBy(xpath = "//input[@formcontrolname='zipcode' and @type='text']")
	WebElement zipcodes;
	
	@FindBy(xpath = "//li[normalize-space(text())='441601, Gondiya Taluka, Gondiya, Maharashtra, India']")
	WebElement zipcodesclick;
	
	//li[normalize-space(text())='441601, Gondiya Taluka, Gondiya, Maharashtra, India']

	
	//select[@formcontrolname='country_id']
	@FindBy(xpath = "//i[@class='fa fa-camera']")
	WebElement profilepicture;
	@FindBy(xpath = "//i[contains(@class, 'fa') and contains(@class, 'fa-close')]")
	WebElement removeprofilepicture;
	@FindBy(xpath = "//button[text()='Confirm']")
	WebElement confirm;
	
	
	
	@FindBy(xpath = "//input[@formcontrolname='certification_name']")
	WebElement certificatenames;
	
	@FindBy(xpath = "//input[@formcontrolname='issuing_organization']")
	WebElement Organization;
	
	@FindBy(xpath = "//*[@id=\"home\"]/form/div/div[1]/div[4]/div/div[4]/div/input")
	WebElement location;
	
	@FindBy(xpath = "//*[@id=\"home\"]/form/div/div[1]/div[1]/div/div[3]/div/input")
	WebElement location1;
	
	@FindBy(xpath = "//input[@formcontrolname='major_study_field']")
	WebElement majorstudy;
	
	//input[@formcontrolname='major_study_field']
	//input[@formcontrolname='city']
	
	@FindBy(xpath = "//input[@formcontrolname='city']")
	WebElement namecity;
	
	public void deleterofilepicture() throws InterruptedException, AWTException {
		wait_for_page_load(10);
		profile.click();
		wait_in_seconds(10);
		removeprofilepicture.click();
		wait_in_seconds(10);
		//wait_for_element_present(cancle);
		//click_Element_Using_JS(cancle);
		wait_for_element_present(confirm);
		click_Element_Using_JS(confirm);
	}
	
	//*[@id="home"]/form/div/div[1]/span[1]/i
	
	@FindBy(xpath = "(//div[@class='dz-message'])[1]")
	WebElement sub1;
	@FindBy(xpath = "(//div[@class='dz-message'])[2]")
	WebElement sub2;
	@FindBy(xpath = "(//div[@class='dz-message'])[3]")
	WebElement sub3;
	@FindBy(xpath = "(//div[@class='dz-message'])[4]")
	WebElement sub4;
	
	@FindBy(xpath = "/html/body/div[1]/div/a[1]")
	WebElement login;
	
	@FindBy(xpath = "//*[@id=\"user_login\"]")
	WebElement user;
	
	@FindBy(xpath = "//*[@id=\"user_pass\"]")
	WebElement pass;
	@FindBy(xpath = "//*[@id=\"wp-submit\"]")
	WebElement submitg;
	
	@FindBy(xpath = "//a[@id=\"sm-17512864765323154-15\"]/@href")
	WebElement sg;
	
	@FindBy(xpath = "(//a[@href='https://www.gladdensociety.org/match-day-video-submission/' and text()='Match Day Video Submission'])[1]")
	WebElement mg;
	
	@FindBy(xpath = "//input[@name='wpforms[fields][102_3414]']")
	
	WebElement pic;
	
	
@FindBy(xpath = "//*[@id=\"wpforms-2739-field_91-container\"]/div/div[1]/div/div[1]/div/div[2]/ul/li[1]/i")
	
	WebElement pictek;
	//*[@id="wpforms-2739-field_91-container"]/div/div[1]/div/div[1]/div/div[2]/ul/li[1]/i
	@FindBy(xpath = "//a[@href=\"https://www.gladdensociety.org/jrgos-case-corner-submission/\" and text()=\"JRGOS Case Corner Submission\"]")
	WebElement cms;
	
	//input[@id='wpforms-2739-field_93-633']
	
	@FindBy(xpath = "(//a[normalize-space(text()[1])='Content Submission']/@href)[1]")
	WebElement cs;
	
	@FindBy(xpath = "(//a[@href=\"https://www.gladdensociety.org/jrgos-content/\" and contains(@class, \"elementor-item-active\") and text()=\"Social Media Submission")
	WebElement sms;
	
	
	@FindBy(xpath = "//*[@id=\"gray-belowbanner\"]/div/div/div[2]/div[8]/div/a")
	WebElement cst;
	
	@FindBy(xpath = "//*[@id=\"wpforms-submit-2739\"]")
	WebElement finalsubmit;
	@FindBy(xpath = "//*[@id=\"wpforms-2739-field_64\"]")
	WebElement Diagnosis;
	
	@FindBy(xpath = "//*[@id=\"wpforms-2739-field_68\"]")
	WebElement perl;
	
	@FindBy(xpath = "//a[text()=\"Submit Your Social Media Content\"]")
	WebElement ssm;
	
	@FindBy(xpath = "//button[@id=\"wpforms-submit-2741\" and @type=\"submit\" and text()=\"Submit\"]")
	WebElement ssmsubmit;
	
	
	//*[@id="wpforms-2739-field_68"]
	//a[text()="Submit Your Social Media Content"]
	public void ssm() throws InterruptedException, AWTException {
		wait_for_page_load(5);
		wait_for_element_present(login);
		click_Element_Using_JS(login);
		wait_for_element_present(user);
		user.sendKeys("vvaidya@tiuconsulting.com");
		pass.sendKeys("Tiu@gladden1");
		click_Element_Using_JS(submitg);
		waitInSeconds(10);
		driver.get("https://www.gladdensociety.org/jrgos-content/");
	wait_for_page_load(10);
	click_Element_Using_JS(ssm);
	wait_for_page_load(10);
	scrollDownToElement(ssmsubmit);
	click_Element_Using_JS(ssmsubmit);
	}
	public void submissin() throws InterruptedException, AWTException {
		wait_for_page_load(5);
		wait_for_element_present(login);
		click_Element_Using_JS(login);
		wait_for_element_present(user);
		user.sendKeys("vvaidya@tiuconsulting.com");
		pass.sendKeys("Tiu@gladden1");
		click_Element_Using_JS(submitg);
		waitInSeconds(10);
		driver.get("https://www.gladdensociety.org/jrgos-content/");
	wait_for_page_load(10);
	click_Element_Using_JS(ssm);
	wait_for_page_load(10);
	scrollDownToElement(ssmsubmit);
	click_Element_Using_JS(ssmsubmit);
	//Diagnosis.sendKeys("Tiu@gladden1"); 
	//pic.sendKeys("Tiu@gladden1");
//perl.sendKeys("Tiu@gladden1");
//	scrollDownToElement(finalsubmit);
	//click_Element_Using_JS(finalsubmit);
	//pic.sendKeys("Tiu@gladden1");
	//Actions act1 = new Actions(driver);
	//WebElement upload = driver.findElement(By.xpath("//*[@id=\"wpforms-2739-field_92-4136-container\"]/div[1]"));
	//act1.moveToElement(upload).click().perform();
	//file_upload("C:\\Users\\Admin\\Desktop\\Tesst Data\\surecafetest.jpg");
	//WebElement pic  = driver.findElement(By.xpath("//*[@id=\"wpforms-2739-field_87-container\"]/div[1]/div"));
	//pic.sendKeys("Your Text Here");
	
	//click_Element_Using_JS(cms);
	//click_Element_Using_JS(pictek);
	//waitInSeconds(5);
	//click_Element_Using_JS(pictek);
	//scrollDownToElement(pic);
	//pic.sendKeys("Tiu@gladden1");
	
		//sg.click();
		//click_Element_Using_JS(sg);
		//wait_in_seconds(20);
		//click_Element_Using_JS(mg);
		//wait_in_seconds(5);
		//sub.click();
	//	click_Element_Using_JS(sub1);
		//wait_in_seconds(10);
		//profilepicture.click();
		//wait_in_seconds(10);
		//Actions act = new Actions(driver);
		//WebElement upload = driver.findElement(By.xpath("(//div[@class='dz-message'])[1]"));
		//WebElement upload = driver.findElement(By.xpath("//i[@class='fa fa-camera']"));
		//act.moveToElement(upload).click().perform();
		//For Negative testing change the upload file path and file types.
		//file_upload("C:\\Users\\Admin\\Desktop\\Tesst Data\\surecafetest.jpg");
		//wait_in_seconds(5);
		//click_Element_Using_JS(sub2);
		//wait_in_seconds(10);
		//profilepicture.click();
		//wait_in_seconds(10);
		//Actions act1 = new Actions(driver);
	//	WebElement upload1 = driver.findElement(By.xpath("(//div[@class='dz-message'])[2]"));
		//WebElement upload = driver.findElement(By.xpath("//i[@class='fa fa-camera']"));
		//act1.moveToElement(upload1).click().perform();
		//For Negative testing change the upload file path and file types.
		//C:\Users\Admin\Desktop\Tesst Data\surecafetest.jpgfile_upload("C:\\Users\\Admin\\Desktop\\Tesst Data\\surecafetest.jpg");
	}
	public void uploadprofilepicture() throws InterruptedException, AWTException {
		wait_for_page_load(5);
		profile.click();
		wait_in_seconds(10);
		profilepicture.click();
		wait_in_seconds(10);
		//Actions act = new Actions(driver);
		WebElement upload = driver.findElement(By.xpath("//*[@id=\"home\"]/form/div/div[3]/div[1]/div[1]/input"));
		//WebElement upload = driver.findElement(By.xpath("//i[@class='fa fa-camera']"));
		//act.moveToElement(upload).click().perform();
		//For Negative testing change the upload file path and file types.
		file_upload("C:\\Users\\Admin\\Desktop\\Tesst Data\\surecafetest.jpg");
	}

	public void Profilepicture() throws InterruptedException, AWTException {
		waitInSeconds(10);
		click_Element_Using_JS(profile);
		// click_Element_Using_JS(personalDetails);
		wait_in_seconds(5);
		// wait_for_element_present(Uploadimage);
		//click_Element_Using_JS(Uploadimage);
		wait_in_seconds(5);
		WebElement upload = driver.findElement(By.xpath("//*[@id=\"home\"]/form/div/div[3]/div[1]/div[1]/input"));
	//	Actions act = new Actions(driver);
	//	act.moveToElement(upload).click().perform();
		wait_in_seconds(5);
		//For Negative testing change the upload file path and file types.
		file_upload("C:\\Users\\Admin\\Desktop\\Tesst Data\\surecafetest.jpg");

	}
	public void Resumeupload() throws InterruptedException, AWTException {
		waitInSeconds(10);
		click_Element_Using_JS(profile);
		//click_Element_Using_JS(personalDetails);
		wait_in_seconds(5);
		//wait_for_element_present(Uploadresume);
		//click_Element_Using_JS(Uploadresume);
		wait_in_seconds(5);
		
		//WebElement upload = driver.findElement(By.xpath("//div[@id='home']//form//span/i"));
		WebElement upload = driver.findElement(By.xpath("//input[@type='file' and contains(@class, 'inpt140')]"));
	//	Actions act = new Actions();
	//	act.moveToElement(upload).click().perform();
		wait_in_seconds(5);
		//For Negative testing change the upload file path and file types.
		file_upload("C:\\Users\\Admin\\Downloads\\Testprofile.docx");
		wait_in_seconds(10);
		//scrollDownToElement(next1);
		//click_Element_Using_JS(next1);
		//wait_in_seconds(10);
		/*scrollDownToElement(next2);
		click_Element_Using_JS(next2);
		
		wait_in_seconds(10);
		wait_for_element_present(zipcodes);
        zipcodes.sendKeys("441601");
		
		Select countryDropdown = new Select(countrys);
		countryDropdown.selectByVisibleText("India");
		
		scrollDownToElement(submitp);
		click_Element_Using_JS(submitp);*/
			
		
	}
	
	@FindBy(xpath = "(//textarea[@id='editor3' and @formcontrolname='work_job_description'])[3]")
	WebElement jobrole;
	
	@FindBy(xpath = "/html/body/app-root/app-layout/div[2]/div[2]/app-dashboard/div/div/div/div[2]/div/div[2]/div/div/form/div/div[1]/div[7]/div/div[10]/div/textarea")
	WebElement projectundertaken;
	
	
	@FindBy(xpath = "//textarea[@id='editor4' and @formcontrolname='work_projects_undertaken' and @placeholder='<Type your message here...>']")
	WebElement techused;
	
	@FindBy(xpath = "/html/body/app-root/app-layout/div[2]/div[2]/app-dashboard/div/div/div/div[2]/div/div[2]/div/div/form/div/div[1]/div[1]/div/div[11]/div/textarea")
	WebElement achivment;
	
	
	@FindBy(xpath = "//input[@formcontrolname='dob' and @type='date']")
	WebElement UDOB;
	@FindBy(xpath = "//textarea[@formcontrolname='soft_skills']")
	WebElement softskill;
	
	@FindBy(xpath = "//input[@type='text' and @formcontrolname='current_designation']")
	WebElement Designation;
	
	@FindBy(xpath = "//select[@formcontrolname='relocation_preferences']")
	WebElement citytp;
	

	
	
	
	public void Profile() throws InterruptedException, AWTException {
		wait_in_seconds(10);
		click_Element_Using_JS(profile);
	     //UDOB.clear();
		UDOB.sendKeys("11/11/1993");
		//wait_for_element_present(zipcodes);
		zipcodes.clear();
        zipcodes.sendKeys("441601");
		//wait_for_element_present(zipcodesclick);
	   //click_Element_Using_JS(zipcodesclick);
		Select countryDropdown = new Select(countrys);
		countryDropdown.selectByVisibleText("India");
		namecity.sendKeys("Gondia");
		citytp.sendKeys("NO");
		Designation.sendKeys("SR Java Developer");
		softskill.sendKeys("Communication");
		scrollDownToElement(next1);
		click_Element_Using_JS(next1);
		wait_for_page_load(5);;
		majorstudy.sendKeys("AI");
		certificatenames.clear();
		certificatenames.sendKeys("sunJava");
		Organization.clear();
		Organization.sendKeys("Cdac");
		scrollDownToElement(next1);
		click_Element_Using_JS(next1);
		wait_for_page_load(10);
		//location1.clear();
		location1.sendKeys("Pune");
		//wait_for_element_present(jobrole);
		jobrole.sendKeys("QA");
		wait_in_seconds(5);
		//projectundertaken.sendKeys("ECommerce");
		//wait_in_seconds(5);
		techused.sendKeys("Java");
		wait_in_seconds(5);
		achivment.sendKeys("ECommerce");
		//location.clear();
	//	location.sendKeys("Mumbai");
		wait_in_seconds(5);
		scrollDownToElement(submitp);
		click_Element_Using_JS(submitp);
		}
	

	public void Profilenaviagtion() throws InterruptedException, AWTException {
		wait_in_seconds(10);
		// stepComplete.click();
		// below code for List of activity count is
		wait_in_seconds(10);
		wait_for_page_load(10);
		int list = listofactivity.size();
		WebElement inputField = driver.findElement(By.xpath("//*[@id=\"activities\"]/tbody/tr[" + (list) + "]"));
		System.out.println("List of activity count is " + list);
		scrollToElement(inputField);
	}

	public void RestoreIntership() throws InterruptedException, AWTException {

		waitInSeconds(10);
		wait_for_element_present(Profilesteplink);
		Profilesteplink.click();
		wait_for_page_load(10);
		wait_for_element_present(Closebutton);
		Closebutton.click();
		YesOk.click();
		wait_in_seconds(10);
		scrollDownToElement(Submitp);
		click_Element_Using_JS(Submitp);
		wait_in_seconds(10);
		wait_for_element_present(Closebutton);
		Closebutton.click();
		wait_in_seconds(5);
		YesOk1.click();
		wait_for_page_load(10);
		wait_for_element_present(Archive);
		click_Element_Using_JS(Archive);
		wait_in_seconds(10);
		wait_in_seconds(10);
		wait_for_element_present(Archivechk);
		click_Element_Using_JS(Archivechk);
		wait_in_seconds(10);
		wait_for_element_present(Archivechk1);
		click_Element_Using_JS(Archivechk1);
		scrollDownToElement(Restore);
		wait_in_seconds(10);
		click_Element_Using_JS(Restore);
		wait_in_seconds(10);
		wait_for_element_present(Archive);
		click_Element_Using_JS(Archive);
	}

	public void Steptonecompleted() throws InterruptedException, AWTException {
		// Code for profile navigation from step tp completed page.
		waitInSeconds(10);
		wait_for_element_present(Menu);
		Menu.click();
		wait_for_element_present(Profileclick);
		Profileclick.click();
		wait_in_seconds(10);
		scrollToElement(sectioncompleted);
		wait_for_element_present(sectioncompleted);
		click_Element_Using_JS(sectioncompleted);
		// Code for profile navigation from step tp completed page.
		wait_for_page_load(10);
		wait_for_element_present(Internship);
		click_Element_Using_JS(Internship);
		wait_for_page_load(10);
		wait_for_element_present(Closebutton);
		Closebutton.click();
		YesOk1.click();
		wait_in_seconds(10);
		scrollDownToElement(Submitp);
		click_Element_Using_JS(Submitp);
		wait_in_seconds(10);
		wait_for_element_present(Closebutton);
		Closebutton.click();
		YesOk.click();
		wait_in_seconds(10);
		scrollDownToElement(Submitp);
		click_Element_Using_JS(Submitp);
		wait_in_seconds(10);
		Clear.click();
	}

	public void PrimaryEmail() throws InterruptedException, AWTException {
		wait_for_page_load(10);
		wait_for_element_present(profile);
		click_Element_Using_JS(profile);
		wait_in_seconds(10);
		click_Element_Using_JS(personalDetails);
		wait_for_element_present(PrimarayEmail);
		scrollToElement(PrimarayEmail);
		PrimarayEmail.sendKeys("ts733765@yopmail.com");
		wait_for_element_present(MakePrimarayEmail);
		click_Element_Using_JS(MakePrimarayEmail);
		wait_in_seconds(10);
		wait_for_element_present(YesEmail);
		click_Element_Using_JS(YesEmail);
	}
	
	@FindBy(xpath = "/html/body/app-root/app-layout/div/div/app-login/app-auth/main/div/div/div[2]/div/div[4]/div/div/form/div[3]/a\r\n"
			+ "")
	WebElement forgotlink;
	
	@FindBy(xpath = "//*[@id=\"mat-surecafe-body\"]/app-root/app-layout/div/div/app-forgot-password/main/div/div/div[2]/div/div[3]/div/div/form/div[1]/input\r\n"
			+ "")
	
	WebElement Emailid;
	
	@FindBy(xpath = "//*[@id=\"mat-surecafe-body\"]/app-root/app-layout/div/div/app-forgot-password/main/div/div/div[2]/div/div[3]/div/div/form/div[2]/input")
	WebElement Submit;
	
	

	public void Changepassword() throws InterruptedException, AWTException {
		scrollDownToElement(forgotlink);
		click_Element_Using_JS(forgotlink);
		wait_in_seconds(10);
		Emailid.sendKeys("ats434@yopmail.com");
		wait_in_seconds(5);
		click_Element_Using_JS(Submit);

	
		// wait_for_element_present(Changepasswordlink);
		// click_Element_Using_JS(Changepasswordlink);

	
	}

	public void LoginLogout() throws InterruptedException, AWTException {
		wait_for_page_load(10);
		Profilenameclick.click();
		waitInSeconds(10);		Changepasswordlink.click();
		wait_for_element_present(Signout);
		click_Element_Using_JS(Signout);
	}

	public void Forgotpassword() throws InterruptedException, AWTException {
		scrollDown();
		click_Element_Using_JS(forgotpassword);
		wait_in_seconds(5);
		wait_for_element_present(submitemail);
		emailidf.sendKeys("swapj@yopmail.com");
		waitInSeconds(5);
		submitemail.click();
	}

	public void Formpayment() throws InterruptedException, AWTException {
		wait_in_seconds(10);
		menuclick.click();
		waitInSeconds(10);
		Packetclick.click();
		// driver.navigate().refresh();
		waitInSeconds(10);
		boxclick.click();
		// formclick.click();
		wait_in_seconds(10);
		// boxclick.click();

		scrollDownToElement(PacketAddtocart);
		wait_in_seconds(10);
		click_Element_Using_JS(PacketAddtocart);

		// PacketAddtocart.click();
	}

	@SuppressWarnings("deprecation")
	public void Formpaymentvalidation() throws InterruptedException, AWTException {
		wait_in_seconds(10);
		menuclick.click();
		waitInSeconds(10);
		Packetclick.click();
		String errormessage = errormsg.getText();
		System.out.print("The error message is " + errormessage);
		if (errormessage.equals("Please complete your profile before accessing the forms and packets area.")) {
			System.out.print("The actual error message for validation is " + errormessage);
			Assert.assertEquals("Please complete your profile before accessing the forms and packets area.",
					errormessage);
		}
	}

	@FindBy(xpath = "//li[contains(@class,'pendingItemLink')]//a")
	List<WebElement> pendinglinkactivity;
	@FindBy(xpath = "//*[@id=\"pendingContUl\"]/li[5]/a")
	List<WebElement> pendinglinkactivity5;
	@FindBy(xpath = "	//*[@id=\"pendingContUl\"]/li[3]/a\r\n" + "")
	List<WebElement> pendinglinkactivity3;
	@FindBy(xpath = "/html/body/div[3]/div[1]/section[2]/div/div/div[1]/div/div[1]/h1")
	WebElement workexppage;
	@FindBy(xpath = "/html/body/div[3]/div[1]/section[2]/div/div/div/form/div/div[1]/div[2]/div/div/div/div[3]/ul/li[1]/a")
	WebElement pendingtab1;
	@FindBy(xpath = "//*[@id=\"pendingActivityTab\"]")
	WebElement pendingtab;
	@FindBy(xpath = "/html/body/div[3]/div[1]/section[2]/div/div/div[1]/div/div[1]/h1")
	WebElement edupage;
	@FindBy(xpath = "/html/body/div[3]/div[1]/section[2]/div/div/div[1]/div/div[1]/h1")
	WebElement intership;
	@FindBy(xpath = "	/html/body/div[3]/aside/div/div[2]/nav/ul/li[1]/a\r\n" + "")
	WebElement Hoemclick;
	@FindBy(xpath = "//h1[@class='card-title']")
	WebElement sectionTitle;
	@FindBy(xpath = "//*[@id=\"pendingContUl\"]/li[9]/a")
	WebElement PacketTitle;
	@FindBy(xpath = "/html/body/div[3]/div[1]/section[2]/div/div/div/form/div/div[1]/div[2]/div/div/div/div[3]/ul/li[8]/a")
	WebElement formTitle;
	@FindBy(xpath = "//h1[@class='card-title cardTitleRes']")
	WebElement cardtitleSES;
	@FindBy(xpath = "	/html/body/div[3]/nav/ul[1]/li[2]/span/img\r\n" + "")
	WebElement menuclick1;
	@FindBy(xpath = "/html/body/div[3]/aside/div/div[2]/nav/ul/li[2]/a/p")
	WebElement profileclick;
	@FindBy(xpath = "//*[@id=\"work_experiences\"]")
	WebElement work;
	@FindBy(xpath = "//*[@id=\"company_name\"]")
	WebElement cname;
	@FindBy(xpath = "//*[@id=\"job_title\"]")
	WebElement jname;
	@FindBy(xpath = "//*[@id=\"city\"]")
	WebElement cityname;
	@FindBy(xpath = "	/html/body/div[3]/div[1]/section[1]/div/div/div[2]/div/div/div[1]/a\r\n" + "")
	WebElement link;
	@FindBy(xpath = "//*[@id=\"id_frm_submit\"]/span")
	WebElement submit;
	@FindBy(xpath = "//*[@id=\"institution_name\"]")
	WebElement intname;
	@FindBy(xpath = "//*[@id=\"subjectStudy\"]")
	WebElement subject;
	@FindBy(xpath = "//*[@id=\"city\"]")
	WebElement citye;
	@FindBy(xpath = "//*[@id=\"work_experience_items\"]/div[1]/div/div[2]/div/div[1]/div/input")
	WebElement intsname;
	@FindBy(xpath = "//*[@id=\"work_experience_items\"]/div[1]/div/div[2]/div/div[2]/div/input")
	WebElement sstudy;
	@FindBy(xpath = "//*[@id=\"work_experience_items\"]/div[1]/div/div[2]/div/div[5]/div/input")
	WebElement cityp;
	@FindBy(xpath = "//*[@id=\"job_title\"]")
	WebElement tjob;
	@FindBy(xpath = "//*[@id=\"employer\"]")
	WebElement iemr;
	@FindBy(xpath = "//*[@id=\"city\"]")
	WebElement icity;
	@FindBy(xpath = "//*[@id=\"work_experience_items\"]/div[1]/div/div[2]/div/div[1]/div/input")
	WebElement fjobt;
	@FindBy(xpath = "//*[@id=\"work_experience_items\"]/div[1]/div/div[2]/div/div[2]/div/input")
	WebElement fsemp;
	@FindBy(xpath = "//*[@id=\"work_experience_items\"]/div[1]/div/div[2]/div/div[5]/div/input")
	WebElement fscity;
	@FindBy(xpath = "//*[@id=\"work_experience_items\"]/div[1]/div/div[2]/div/div[1]/div/input")
	WebElement rsjobt;
	@FindBy(xpath = "//*[@id=\"work_experience_items\"]/div[1]/div/div[2]/div/div[2]/div/input")
	WebElement rssemp;
	@FindBy(xpath = "//*[@id=\"work_experience_items\"]/div[1]/div/div[2]/div/div[5]/div/input")
	WebElement rscity;
	@FindBy(xpath = "//*[@id=\"work_experience_items\"]/div[1]/div/div[2]/div/div[1]/div/input")
	WebElement certificate;
	@FindBy(xpath = "//*[@id=\"certification_date\"]")
	WebElement certificates;

	@FindBy(xpath = "//*[@id=\"work_experience_items\"]/div[1]/div/div[2]/div/div[1]/div/input")
	WebElement certificatename;
	@FindBy(xpath = "//*[@id=\"work_experience_items\"]/div[1]/div/div[2]/div/div[1]/div/input")
	WebElement Boardcertificate;
	@FindBy(xpath = "//*[@id=\"work_experience_items\"]/div[1]/div/div[2]/div/div[2]/div/input")
	WebElement Authrity;
	@FindBy(xpath = "//*[@id=\"certification_date_0\"]")
	WebElement cdate;
	@FindBy(xpath = "//*[@id=\"expiration_date_0\"]")
	WebElement Edate;
	@FindBy(xpath = "/html/body/div[3]/div[1]/section[2]/div/div/div[2]/div/div/div/form/div/p/a")
	WebElement clickhere;
	@FindBy(xpath = "//*[@id=\"first_name\"]")
	WebElement fname;
	@FindBy(xpath = "//*[@id=\"last_name\"]")
	WebElement lname;
	@FindBy(xpath = "//*[@id=\"datepicker\"]")
	WebElement DOB;
	@FindBy(xpath = "//*[@id=\"ssn-field\"]")
	WebElement SSNp;
	@FindBy(xpath = "	//*[@id=\"phone\"]")
	WebElement mobile;
	@FindBy(xpath = "//*[@id=\"email\"]")
	WebElement Email;
	@FindBy(xpath = "//*[@id=\"city\"]")
	WebElement Cityp;

	public void Profileflow() throws InterruptedException, AWTException {
		wait_in_seconds(10);
		link.click();
		// menuclick.click();
		// profileclick.click();
		// wait_in_seconds(10);
		// fname.sendKeys("Harry");
		// lname.sendKeys("Sam");
		// DOB.sendKeys("07/23/1992");
		SSNp.sendKeys("506634417");
		// mobile.sendKeys("4567765498");
		// Email.sendKeys("harrysam@yopmail.com");
		Cityp.sendKeys("NY");
		wait_for_element_present(submit);
		scrollDownToElement(submit);
		click_Element_Using_JS(submit);
		wait_in_seconds(10);
		cname.sendKeys("Test");
		rssemp.sendKeys("Test");
		cityname.sendKeys("NY");
		scrollDownToElement(submit);
		click_Element_Using_JS(submit);
		wait_for_element_present(intname);
		intname.sendKeys("Test");
		subject.sendKeys("Test");
		citye.sendKeys("NY");
		scrollDownToElement(submit);
		click_Element_Using_JS(submit);
		// Professional
		wait_in_seconds(10);
		intsname.sendKeys("Test");
		sstudy.sendKeys("Test");
		cityp.sendKeys("NY");
		scrollDownToElement(submit);
		click_Element_Using_JS(submit);
		// internship
		wait_in_seconds(10);
		tjob.sendKeys("Test");
		iemr.sendKeys("Test");
		icity.sendKeys("NY");
		scrollDownToElement(submit);
		click_Element_Using_JS(submit);
		// resisdency
		wait_in_seconds(10);
		rsjobt.sendKeys("Test");
		rssemp.sendKeys("Test");
		rscity.sendKeys("NY");
		scrollDownToElement(submit);
		click_Element_Using_JS(submit);
		// fellowship
		wait_in_seconds(10);
		fjobt.sendKeys("Test");
		fsemp.sendKeys("Test");
		fscity.sendKeys("NY");
		scrollDownToElement(submit);
		click_Element_Using_JS(submit);
		// certificate
		wait_in_seconds(10);
		certificate.sendKeys("Test");
		scrollDownToElement(submit);
		click_Element_Using_JS(submit);
		// Boardcertificate
		wait_in_seconds(10);
		Boardcertificate.sendKeys("Test");
		Authrity.sendKeys("Test");
		cdate.sendKeys("04/12/2025");
		wait_for_element_present(Edate);
		Edate.sendKeys("04/30/2025");
		scrollDownToElement(submit);
		click_Element_Using_JS(submit);
		wait_in_seconds(10);
		wait_for_element_present(clickhere);
		click_Element_Using_JS(clickhere);
	}

	@SuppressWarnings("deprecation")
	public void Pendingactivity() throws InterruptedException, AWTException {

		wait_in_seconds(5);
		wait_for_element_present(pendingtab);
		click_Element_Using_JS(pendingtab);
		wait_in_seconds(5);
		for (WebElement element : pendinglinkactivity) {
			String activityname = element.getText();
			System.out.print("The Activity Name is " + activityname);
			if (activityname.contains("Overview")) {
				String keyPress = Keys.CONTROL.toString() + Keys.SHIFT.toString() + Keys.ENTER.toString();
				element.sendKeys(keyPress);
				ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
				driver.switchTo().window(tab.get(1));
				driver.close();
				// switch to parent
				driver.switchTo().window(tab.get(0));
				continue;

			} else if (activityname.contains("Work Experience")) {
				String keyPress = Keys.CONTROL.toString() + Keys.SHIFT.toString() + Keys.ENTER.toString();
				element.sendKeys(keyPress);
				ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
				driver.switchTo().window(tab.get(1));
				wait_for_element_present(sectionTitle);
				String sectionname = sectionTitle.getText();
				System.out.print("The section name is " + activityname);
				waitInSeconds(5);
				Assert.assertEquals(activityname, sectionname);
				System.out.print("The validationsection name is " + sectionname);
				driver.close();
				// switch to parent
				driver.switchTo().window(tab.get(0));
				// continue;
			} else if (activityname.contains("Education")) {
				String keyPress = Keys.CONTROL.toString() + Keys.SHIFT.toString() + Keys.ENTER.toString();
				element.sendKeys(keyPress);
				ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
				driver.switchTo().window(tab.get(1));
				wait_for_element_present(sectionTitle);
				String sectionname = sectionTitle.getText();
				System.out.print("The section name is " + activityname);
				waitInSeconds(5);
				Assert.assertEquals(activityname, sectionname);
				System.out.print("The validationsection name is " + sectionname);
				driver.close();
				// switch to parent
				driver.switchTo().window(tab.get(0));
				// continue;
			}

			else if (activityname.contains("Internship")) {
				String keyPress = Keys.CONTROL.toString() + Keys.SHIFT.toString() + Keys.ENTER.toString();
				element.sendKeys(keyPress);
				ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
				driver.switchTo().window(tab.get(1));
				wait_for_element_present(sectionTitle);
				String sectionname = sectionTitle.getText();
				System.out.print("The section name is " + activityname);
				waitInSeconds(5);
				Assert.assertEquals(activityname, sectionname);
				System.out.print("The validationsection name is " + sectionname);
				driver.close();
				// switch to parent
				driver.switchTo().window(tab.get(0));
				// continue;
			} else if (activityname.contains("Residency")) {
				String keyPress = Keys.CONTROL.toString() + Keys.SHIFT.toString() + Keys.ENTER.toString();
				element.sendKeys(keyPress);
				ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
				driver.switchTo().window(tab.get(1));
				wait_for_element_present(sectionTitle);
				String sectionname = sectionTitle.getText();
				System.out.print("The section name is " + activityname);
				waitInSeconds(5);
				Assert.assertEquals(activityname, sectionname);
				System.out.print("The validationsection name is " + sectionname);
				driver.close();
				// switch to parent
				driver.switchTo().window(tab.get(0));
				// continue;
			} else if (activityname.contains("Fellowship")) {
				String keyPress = Keys.CONTROL.toString() + Keys.SHIFT.toString() + Keys.ENTER.toString();
				element.sendKeys(keyPress);
				ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
				driver.switchTo().window(tab.get(1));
				wait_for_element_present(sectionTitle);
				String sectionname = sectionTitle.getText();
				System.out.print("The section name is " + activityname);
				waitInSeconds(5);
				Assert.assertEquals(activityname, sectionname);
				System.out.print("The validationsection name is " + sectionname);
				driver.close();
				// switch to parent
				driver.switchTo().window(tab.get(0));
				// continue;
			} else if (activityname.contains("State Licenses or Certificates")) {
				String keyPress = Keys.CONTROL.toString() + Keys.SHIFT.toString() + Keys.ENTER.toString();
				element.sendKeys(keyPress);
				ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
				driver.switchTo().window(tab.get(1));
				wait_for_element_present(cardtitleSES);
				String sectionname = cardtitleSES.getText();
				System.out.print("The section name is " + activityname);
				waitInSeconds(5);
				Assert.assertEquals(activityname, sectionname);
				System.out.print("The validationsection name is " + sectionname);
				driver.close();
				// switch to parent
				driver.switchTo().window(tab.get(0));
				// continue;
			} else if (activityname.contains("Board Certification / Eligibility")) {
				String keyPress = Keys.CONTROL.toString() + Keys.SHIFT.toString() + Keys.ENTER.toString();
				element.sendKeys(keyPress);
				ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
				driver.switchTo().window(tab.get(1));
				wait_for_element_present(sectionTitle);
				String sectionname = sectionTitle.getText();
				System.out.print("The section name is " + activityname);
				waitInSeconds(5);
				Assert.assertEquals(activityname, sectionname);
				System.out.print("The validationsection name is " + sectionname);
				driver.close(); // switch to parent
				driver.switchTo().window(tab.get(0));
				// continue;
			} else if (activityname.contains("Pending Forms")) {
				String keyPress = Keys.CONTROL.toString() + Keys.SHIFT.toString() + Keys.ENTER.toString();
				element.sendKeys(keyPress);
				ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
				driver.switchTo().window(tab.get(1));
				wait_for_element_present(errormsg);
				String errormessage = errormsg.getText();
				System.out.print("The error message is " + errormessage);
				if (errormessage.equals("Please complete your profile before accessing the forms and packets area.")) {
					System.out.print("The actual error message for validation is " + errormessage);
					Assert.assertEquals("Please complete your profile before accessing the forms and packets area.",
							errormessage);
					// switch to parent
					driver.close();
					driver.switchTo().window(tab.get(0));
					// continue;
				}
			} else if (activityname.contains("Pending Packets")) {
				String keyPress = Keys.CONTROL.toString() + Keys.SHIFT.toString() + Keys.ENTER.toString();
				element.sendKeys(keyPress);
				ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
				driver.switchTo().window(tab.get(1));
				wait_for_element_present(errormsg);
				String pendingpacket = errormsg.getText();
				System.out.print("The error message is " + pendingpacket);
				if (pendingpacket.equals("Please complete your profile before accessing the forms and packets area.")) {
					System.out.print("The actual error message for validation is " + pendingpacket);
					Assert.assertEquals("Please complete your profile before accessing the forms and packets area.",
							pendingpacket);
					// switch to parent
					driver.close();
					driver.switchTo().window(tab.get(0));
					// continue;
				}
			} else {
				System.out.print("No pending activities");
				// continue;
			}

		}
	}

	@FindBy(xpath = "//input[@id='company_name']")
	WebElement companyName;
	@FindBy(xpath = "//input[@id='job_title']")
	WebElement jonTitle;
	@FindBy(xpath = "//select[@id='state-list_0']")
	WebElement stateList0;
	@FindBy(xpath = "//input[@id='currently_working_status']")
	WebElement yesRadioButton;

	@FindBy(xpath = "//select[@id='start_month_0']")
	WebElement startMonth;
	@FindBy(xpath = "//select[@id='we_start_year_identity_0']")
	WebElement startYear;

	public void workExperience() throws InterruptedException {
		wait_in_seconds(3);
		companyName.clear();
		companyName.sendKeys("TIU");
		jonTitle.clear();
		jonTitle.sendKeys("QE");
		Select countryDropdown = new Select(country);
		countryDropdown.selectByVisibleText("United States");
		Select state = new Select(stateList0);
		state.selectByVisibleText("Arizona");
		city.clear();
		city.sendKeys("Thanda");
		wait_in_seconds(3);
		yesRadioButton.click();
		Select month = new Select(startMonth);
		month.selectByVisibleText("Jan");
		Select year = new Select(startYear);
		year.selectByVisibleText("2010");
		scrollDownToElement(saveAndContinue1);
		wait_in_seconds(5);
		scrollDown();
		saveAndContinue1.click();
	}

	@FindBy(xpath = "//input[@id='institution_name']")
	WebElement institution_name;
	@FindBy(xpath = "//input[@id='subjectStudy']")
	WebElement subjectStudy;
	@FindBy(xpath = "//input[@name='education[0][currently_studying]' and@ value = '1']")
	WebElement yesRadioButtonEducation;

	public void education() throws InterruptedException {
		institution_name.sendKeys("High School");
		subjectStudy.sendKeys("EEE");
		Select countryDropdown = new Select(country);
		countryDropdown.selectByVisibleText("United States");
		Select state = new Select(stateList0);
		state.selectByVisibleText("Arizona");
		city.clear();
		city.sendKeys("Thanda");
		wait_in_seconds(10);
		scrollDownToElement(yesRadioButtonEducation);
		yesRadioButtonEducation.click();
		Select month = new Select(startMonth);
		month.selectByVisibleText("Jan");
		Select year = new Select(startYear);
		year.selectByVisibleText("2010");
		scrollDownToElement(saveAndContinue1);
		wait_in_seconds(5);
		scrollDown();
		saveAndContinue1.click();
	}

	@FindBy(xpath = "//input[@name='education[0][institution_name]']")
	WebElement institutionName;
	@FindBy(xpath = "//input[@name='education[0][stream]']")
	WebElement subjectofStudy;
	@FindBy(xpath = "//input[@name='education[0][city]']")
	WebElement city1;

	public void professionalEducation() throws InterruptedException {
		institutionName.sendKeys("TIU");
		subjectofStudy.sendKeys("sos");
		Select state = new Select(stateList0);
		state.selectByVisibleText("Arizona");
		city1.sendKeys("Thanda");
		Select month = new Select(startMonth);
		month.selectByVisibleText("Jan");
		Select year = new Select(startYear);
		year.selectByVisibleText("2010");
		scrollDownToElement(saveAndContinue1);
		wait_in_seconds(5);
		scrollDown();
		saveAndContinue1.click();
	}

	@FindBy(xpath = "//input[@name='internship[0][job_title]']")
	WebElement jobTtitle;
	@FindBy(xpath = "//input[@name='internship[0][employer]']")
	WebElement employer;
	@FindBy(xpath = "//input[@name='internship[0][city]']")
	WebElement internshipCity;
	@FindBy(xpath = "//select[@id='end_month_0']")
	WebElement endMonth;
	@FindBy(xpath = "//select[@id='we_end_year_identity_0']")
	WebElement endYear;

	public void internship() throws InterruptedException {
		jobTtitle.sendKeys("Lead");
		employer.sendKeys("TIU");
		Select state = new Select(stateList0);
		state.selectByVisibleText("Arizona");
		internshipCity.sendKeys("Thanda");
		Select month = new Select(startMonth);
		month.selectByVisibleText("Jan");
		Select year = new Select(startYear);
		year.selectByVisibleText("2010");
		Select endmonth = new Select(endMonth);
		endmonth.selectByVisibleText("Jan");
		Select endyear = new Select(endYear);
		endyear.selectByVisibleText("2019");
		scrollDownToElement(saveAndContinue1);
		wait_in_seconds(5);
		scrollDown();
		saveAndContinue1.click();
	}

	@FindBy(xpath = "//input[@name='residency[0][job_title]']")
	WebElement jobTtitleResidence;
	@FindBy(xpath = "//input[@name='residency[0][employer]']")
	WebElement employerResidence;
	@FindBy(xpath = "//input[@name='residency[0][city]']")
	WebElement residenceCity;

	public void residency() throws InterruptedException {
		jobTtitleResidence.sendKeys("QWERTY");
		employerResidence.sendKeys("ASD");
		Select state = new Select(stateList0);
		state.selectByVisibleText("Arizona");
		residenceCity.sendKeys("Thanda");
		Select month = new Select(startMonth);
		month.selectByVisibleText("Jan");
		Select year = new Select(startYear);
		year.selectByVisibleText("2010");
		Select endmonth = new Select(endMonth);
		endmonth.selectByVisibleText("Jan");
		Select endyear = new Select(endYear);
		endyear.selectByVisibleText("2019");
		scrollDownToElement(saveAndContinue1);
		wait_in_seconds(5);
		scrollDown();
		saveAndContinue1.click();
	}

	@FindBy(xpath = "//input[@name='fellowship[0][job_title]']")
	WebElement jobTtitlefellowship;
	@FindBy(xpath = "//input[@name='fellowship[0][employer]']")
	WebElement employerfellowship;
	@FindBy(xpath = "//input[@name='fellowship[0][city]']")
	WebElement fellowshipCity;

	public void fellowship() throws InterruptedException {
		scrollDownToElement(saveAndContinue1);
		wait_in_seconds(5);
		scrollDown();
		saveAndContinue1.click();
	}

	@FindBy(xpath = "//input[@name='certificate[0][certification_name]']")
	WebElement certification_name;
	@FindBy(xpath = "//input[@name='certificate[0][certification_date]']")
	WebElement certification_date;
	@FindBy(xpath = "//select[@id='authority_<?php echo e(0); ?>']")
	WebElement stateAuthority;

	public void licensesOrCertificates() throws InterruptedException {
		scrollDownToElement(saveAndContinue1);
		wait_in_seconds(10);
		wait_for_page_load(10);
		scrollDown();
		saveAndContinue1.click();
	}

	@FindBy(xpath = "//a[contains(@href, 'https://dev.kredsafe.net/user/documents')]")
	WebElement documentsSection1;
	@FindBy(xpath = "//a[@title=' Upload File']")
	WebElement uploadFile;
	@FindBy(xpath = "//input[@name='document_type' and@ value = '10']")
	WebElement documentTypeother;
	@FindBy(xpath = "//input[@name='document_type' and@ value = '1']")
	WebElement documentTypeDEA;
	@FindBy(xpath = "//input[@name='document_type' and@ value = '2']")
	WebElement documentTypeResidency;
	@FindBy(xpath = "//input[@name='document_type' and@ value = '3']")
	WebElement documentTypeStateControlledSubstance;
	@FindBy(xpath = "//input[@name='document_type' and@ value = '4']")
	WebElement documentTypeMedicalLicence;
	@FindBy(xpath = "//input[@name='document_type' and@ value = '5']")
	WebElement documentTypeMedicalDegree;
	@FindBy(xpath = "//input[@name='document_type' and@ value = '6']")
	WebElement documentTypeIntershipcertificate;
	@FindBy(xpath = "//input[@name='document_type' and@ value = '7']")
	WebElement documentTypeFellowshipcertificate;
	@FindBy(xpath = "//input[@name='document_type' and@ value = '8']")
	WebElement documentTypeECFMG;
	@FindBy(xpath = "//input[@name='document_type' and@ value = '9']")
	WebElement documentTypeBoardcertificate;
	@FindBy(xpath = "//button[@id='submitComment']")
	WebElement docSubmitcomment;
	@FindBy(xpath = "//button[@id='docSubmit']")
	WebElement docSubmit;
	@FindBy(xpath = "//a[contains(@href, 'https://dev.kredsafe.net/uploadedFile/MTM5MQ==/112.pdf') and @title = 'View document']")
	WebElement viewDocument;
	@FindBy(xpath = "//a[contains(@href, '#') and @title = 'View/Add Note']")
	WebElement addNote;

	public void documentsSection() throws InterruptedException, AWTException {
		waitInSeconds(10);
		click_Element_Using_JS(documentsSection1);
		wait_in_seconds(5);
		uploadFile.click();
		waitInSeconds(10);
		wait_for_element_present(documentTypeDEA);
		click_Element_Using_JS(documentTypeDEA); // documentTypeECFMG.click();
		wait_in_seconds(5);
		WebElement upload = driver.findElement(By.xpath("//input[@class='fileUpload inputOtherBrd']"));
	//	Actions act = new Actions(driver);
		///act.moveToElement(upload).click().perform();
		waitInSeconds(5);
		file_upload("C:\\Users\\Admin\\Downloads\\Anil_Kumar_Salesforce_Admin.docx");
		waitInSeconds(10);
		docSubmit.click();
		waitInSeconds(10);
		click_Element_Using_JS(uploadFile);
		waitInSeconds(10);
		documentTypeResidency.click();
		wait_in_seconds(5);
		WebElement upload1 = driver.findElement(By.xpath("//input[@class='fileUpload inputOtherBrd']"));
		//Actions act1 = new Actions(driver);
		//act1.moveToElement(upload1).click().perform();
		waitInSeconds(5);
		file_upload("C:\\Users\\Admin\\Downloads\\DerekSmith(1).doc");
		waitInSeconds(10);
		docSubmit.click();
		waitInSeconds(10);
		click_Element_Using_JS(uploadFile);
		waitInSeconds(10);
		// wait_for_element_present(documentTypeStateControlledSubstance);
		click_Element_Using_JS(documentTypeStateControlledSubstance);
		// documentTypeStateControlledSubstance.click();
		wait_in_seconds(5);
		WebElement upload2 = driver.findElement(By.xpath("//input[@class='fileUpload inputOtherBrd']"));
		//Actions act2 = new Actions(driver);
		//act2.moveToElement(upload2).click().perform();
		waitInSeconds(5);
		file_upload("C:\\Users\\Admin\\Downloads\\Dice_Resume_CV_Akash_Patel.pdf");
		waitInSeconds(10);
		docSubmit.click();
		waitInSeconds(10);
		click_Element_Using_JS(uploadFile);
		waitInSeconds(10);
		documentTypeMedicalLicence.click();
		wait_in_seconds(5);
		WebElement upload3 = driver.findElement(By.xpath("//input[@class='fileUpload inputOtherBrd']"));
		//Actions act3 = new Actions(driver);
		//act3.moveToElement(upload3).click().perform();
		waitInSeconds(5);
		file_upload("C:\\Users\\Admin\\Downloads\\DerekSmith.odt");
		waitInSeconds(10);
		docSubmit.click();
		waitInSeconds(10);
		click_Element_Using_JS(uploadFile);
		waitInSeconds(10);
		documentTypeMedicalDegree.click();
		wait_in_seconds(5);
		WebElement upload4 = driver.findElement(By.xpath("//input[@class='fileUpload inputOtherBrd']"));
	//	Actions act4 = new Actions(driver);
	//	act4.moveToElement(upload4).click().perform();
		waitInSeconds(5);
		file_upload("C:\\Users\\Admin\\Downloads\\surecafetest.jpeg");
		waitInSeconds(10);
		docSubmit.click();
		waitInSeconds(10);
		click_Element_Using_JS(uploadFile);
		waitInSeconds(10);
		documentTypeIntershipcertificate.click();
		wait_in_seconds(5);
		WebElement upload5 = driver.findElement(By.xpath("//input[@class='fileUpload inputOtherBrd']"));
		//Actions act5 = new Actions(driver);
		//act5.moveToElement(upload5).click().perform();
		waitInSeconds(5);
		file_upload("C:\\Users\\Admin\\Downloads\\surecafetest.jpg");
		waitInSeconds(10);
		docSubmit.click();
		waitInSeconds(10);
		click_Element_Using_JS(uploadFile);
		waitInSeconds(10);
		documentTypeFellowshipcertificate.click();
		wait_in_seconds(5);
		WebElement upload6 = driver.findElement(By.xpath("//input[@class='fileUpload inputOtherBrd']"));
		///Actions act6 = new Actions(driver);
		//act6.moveToElement(upload6).click().perform();
		waitInSeconds(5);
		file_upload("C:\\Users\\Admin\\Downloads\\surecafetest.png");
		waitInSeconds(10);
		docSubmit.click();
		waitInSeconds(10);
		click_Element_Using_JS(uploadFile);
		waitInSeconds(10);
		documentTypeECFMG.click();
		wait_in_seconds(5);
		WebElement upload7 = driver.findElement(By.xpath("//input[@class='fileUpload inputOtherBrd']"));
		//Actions act7 = new Actions(driver);
		//act7.moveToElement(upload7).click().perform();
		waitInSeconds(5);
		file_upload("C:\\Users\\Admin\\Downloads\\surecafetest.gif");
		waitInSeconds(10);
		docSubmit.click();
		waitInSeconds(10);
		click_Element_Using_JS(uploadFile);
		waitInSeconds(10);
		documentTypeBoardcertificate.click();
		wait_in_seconds(5);
		WebElement upload8 = driver.findElement(By.xpath("//input[@class='fileUpload inputOtherBrd']"));
		//Actions act8 = new Actions(driver);
		//act8.moveToElement(upload8).click().perform();
		waitInSeconds(5);
		file_upload("C:\\Users\\Admin\\Downloads\\PavanKalyan-Data Engineer.docx");
		waitInSeconds(10);
		docSubmit.click();
		waitInSeconds(10);
		click_Element_Using_JS(uploadFile);
		waitInSeconds(10);
		documentTypeother.click();
		wait_in_seconds(5);
		WebElement upload9 = driver.findElement(By.xpath("//input[@class='fileUpload inputOtherBrd']"));
		//Actions act9 = new Actions(driver);
		waitInSeconds(5);
		file_upload("C:\\Users\\Admin\\Downloads\\Harideep.docx");
		waitInSeconds(10);
		docSubmit.click();
	}

	@FindBy(xpath = "//a[contains(@href, 'https://dev.kredsafe.net/user/notes')]")
	WebElement notesSection;
	@FindBy(xpath = "//*[@id=\"noteList\"]/tbody/tr/td[6]/a/i")
	WebElement notesSection1;
	@FindBy(xpath = "//a[contains(@href, 'https://dev.kredsafe.net/user/comments/view/User/MTAyNw==') and @title = 'View Note']")
	WebElement editMessage;
	@FindBy(xpath = "//*[@id=\"message_info\"]")
	WebElement enterMessages;
	@FindBy(xpath = "//*[@id=\"submitComment\"]")
	WebElement Submitnote;

	public void notesSection() throws InterruptedException, AWTException {
		waitInSeconds(10);
		click_Element_Using_JS(notesSection);
		wait_for_element_present(notesSection1);
		click_Element_Using_JS(notesSection1);
		// notesSection1.click();
		wait_for_element_present(enterMessages);
		scrollDownToElement(enterMessages);
		enterMessages.sendKeys("Test Notes");
		wait_in_seconds(10);
		scrollToElement(Submitnote);
		click_Element_Using_JS(Submitnote);
		// Submitnote.click();
	}

	public void verifyCompleteSection() throws InterruptedException, AWTException {
		String get_currenturl = driver.getCurrentUrl();
		System.out.println("Current URL is:" + get_currenturl);
		System.out.println("URL matching --> Part executed");
		for (int i = 0; i <= 7; i++) {
			if (!get_currenturl.contains("https://dev.kredsafe.net/user/statement")) {
				scrollDownToElement(saveAndContinue1);
				wait_in_seconds(5);
				scrollDown();
				saveAndContinue1.click();
			}
		}
		wait_for_element_present(clickHere);
		wait_in_seconds(10);
		clickHere.click();
		wait_in_seconds(10);
		Addtocart.click();
	}

	public void Formpayment1() {
		menuclick.click();
		formclick.click();
	}
	
	
	@FindBy(xpath = "//input[@formcontrolname='password']")
    WebElement rpassword;
    @FindBy(xpath = "(//input[@type='password'])[2]")
    WebElement cspassword;
    @FindBy(xpath = "//input[@type='submit' and @value='Submit' and contains(@class, 'btn')]")
    WebElement rsubmit;

    @FindBy(xpath = "//a[contains(@href, 'https://surecafe.tiuconsulting.us/frontend/auth/reset-password?')]")
    WebElement resetLink;
	
	public void YopmailChangepasswordexcel( String newpassword, String confirmpassword, String errorpassword)
			throws InterruptedException {
		driver.manage().window().maximize();
        driver.get("https://yopmail.com/");
        WebElement emailField = driver.findElement(By.id("login"));
        emailField.sendKeys("ats434@yopmail.com");
        WebElement submitYopmail = driver.findElement(By.id("refreshbut"));
        submitYopmail.click();
        wait_in_seconds(10);
        driver.switchTo().frame("ifmail");
        resetLink.click();
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("Switch to tab...");
		driver.switchTo().window(tabs.get(1));
         wait_for_page_load(10);
       // rpassword.sendKeys("12345678");
        //cspassword.sendKeys("12345678");
       //  waitInSeconds(10);
       //  click_Element_Using_JS(rsubmit);
		// Changepasswordlink.click();
		
         rpassword.sendKeys(newpassword);
         cspassword.sendKeys(confirmpassword);
		waitInSeconds(10);
		rsubmit.click();
		wait_in_seconds(10);
		for (WebElement e : incorrectpassword) {
			String errormessage = e.getText();
			System.out.print("The error message is " + errormessage);
			if (errormessage.equals("Passwords must match")) {
				System.out.print("The actual error message for validation is " + errormessage);
				AssertJUnit.assertEquals(errorpassword, errormessage);
			}
			if (errormessage.equals("Password is required")) {
				System.out.print("The actual error message for validation is " + errormessage);
				AssertJUnit.assertEquals(errorpassword, errormessage);
			}
			if (errormessage.equals("Confirm Password is required")) {
				System.out.print("The actual error message for validation is " + errormessage);
				AssertJUnit.assertEquals(errorpassword, errormessage);
			}
			
		}

		}

	public void Changepassword(String oldpassword, String newpassword, String confirmpassword, String errorpassword)
			throws InterruptedException {
		Profilenameclick.click();
		click_Element_Using_JS(Changepasswordlink);
		// Changepasswordlink.click();
		oldpw.sendKeys(oldpassword);
		newpw.sendKeys(newpassword);
		newpwc.sendKeys(confirmpassword);
		waitInSeconds(10);
		pwsubmit.click();
		wait_in_seconds(10);
		for (WebElement e : incorrectpassword) {
			String errormessage = e.getText();
			System.out.print("The error message is " + errormessage);
			if (errormessage.equals("Password does not match with the required criteria.")) {
				System.out.print("The actual error message for validation is " + errormessage);
				AssertJUnit.assertEquals(errorpassword, errormessage);
			}
			if (errormessage.equals("Incorrect password.")) {
				System.out.print("The actual error message for validation is " + errormessage);
				AssertJUnit.assertEquals(errorpassword, errormessage);
			}
			if (errormessage.equals("Incorrect confirm password.")) {
				System.out.print("The actual error message for validation is " + errormessage);
				AssertJUnit.assertEquals(errorpassword, errormessage);
			}

		}
	}

	public void clientSignInn(String username, String password, String errorpassword) throws InterruptedException {
		waitInSeconds(3);
		try {
			if (loginLink.isDisplayed()) {
				loginLink.click();
			}
		} catch (NoSuchElementException e) {

		}
		waitInSeconds(3);
		log.info("Clicking on accept button");
		try {
			if (acceptButton.isDisplayed()) {
				acceptButton.click();
			}
		} catch (NoSuchElementException e) {

		}
		waitInSeconds(3);
		loginField.sendKeys(username);
		passwordField.sendKeys(password);
		wait_in_seconds(10);
		scrollDownToElement(signInButton);
		wait_in_seconds(5);
		signInButton.click();
		wait_in_seconds(10);
		for (WebElement e : incorrectpassword) {
			String errormessage = e.getText();
			System.out.print("The error message is " + errormessage);
			if (errormessage.equals("Invalid Username or Password")) {
				System.out.print("The actual error message for validation is " + errormessage);
				AssertJUnit.assertEquals(errorpassword, errormessage);
				

			}
			if (errormessage.equals("Email is required")) {
				System.out.print("The actual error message for validation is " + errormessage);
				AssertJUnit.assertEquals(errorpassword, errormessage);
				

			}
			if (errormessage.equals("Password is required\r\n"
					+ "")) {
				System.out.print("The actual error message for validation is " + errormessage);
				AssertJUnit.assertEquals(errorpassword, errormessage);
				

			}

		}

	}
	
	@FindBy(xpath = "//*[@id=\"notification-bell\"]/a/i")
	WebElement belllicon;
	@FindBy(xpath = "//*[@id=\"notification-bell\"]/div/a[1]")
	WebElement packet;
	@FindBy(xpath = "/html/body/div[3]/aside/div/div[2]/nav/ul/li[6]/a/p")
	WebElement form;

	@SuppressWarnings("deprecation")
	public void Verifypaymentnotification() throws InterruptedException {
		// belllicon.click();
		click_Element_Using_JS(belllicon);
		waitInSeconds(10);
		// wait_for_element_present(form);
		click_Element_Using_JS(form);
		wait_for_element_present(errormsg);
		String errormessage = errormsg.getText();
		System.out.print("The error message is " + errormessage);
		if (errormessage.equals("Please complete your profile before accessing the forms and packets area.")) {
			System.out.print("The actual error message for validation is " + errormessage);
			Assert.assertEquals("Please complete your profile before accessing the forms and packets area.",
					errormessage);
		}
	}

	@FindBy(xpath = "//*[@class=\"stepComplete\"]")
	WebElement stepComplete;
	@FindBy(xpath = "//*[@class=\"card-title\"]")
	WebElement cardTitle;
	@FindBy(xpath = "//span[@style='display: block;']")
	List<WebElement> mandatoryFields;
	@FindBy(xpath = "//select[@class='autoupdate country']")
	WebElement peContryDropdown;
	@FindBy(xpath = "//*[@class=\"card-title cardTitleRes\"]")
	WebElement certResCardTitle;
	@FindBy(xpath = "//input[@name='certificate[0][authority]']")
	WebElement certificateAuthority;
	// @FindBy(xpath = "//input[@name='workexperience[0][currently_working_status]'
	// and @value=\"1\"]")
	@FindBy(xpath = "//*[@id=\"currently_working_status\"]")
	WebElement workingYesButton;
	// @FindBy(xpath = "//input[@name='education[0][currently_studying]' and
	// @value=\"1\"]")

	@FindBy(xpath = "//*[@id=\"work_experience_items\"]/div[1]/div/div[2]/div/div[6]/div/label[1]/input[1]")
	WebElement eduYesButton;
	@FindBy(xpath = "//*[@name=\"workexperience[0][company_name]\"]")
	WebElement cwname;
	@FindBy(xpath = "//*[@name=\"workexperience[0][job_title]\"]")
	WebElement title;

	@FindBy(xpath = "//button[@id=\"id_frm_submit\"]")
	WebElement save;

	public void stepsToComplete(String SSNNumber) throws InterruptedException {
		wait_for_element_present(stepComplete);
		String stepcompletion = stepComplete.getText();
		System.out.println("The steps to complete on the Dashboard on login is " + stepcompletion);
		if (stepcompletion.equals("Congratulations! You have completed your on-boarding process.")) {
			System.out.println("No steps to be completed as the The step complete message is " + stepcompletion);
		}
		int number = 0;
		if (stepcompletion.contains("step(s) to complete")) {
			String stepsToComplete = stepComplete.getText();
			System.out.println("The number of steps to complete is " + stepsToComplete);
			String stepsNumber = stepsToComplete.substring(9);
			System.out.println("Extracted steps completion message is: " + stepsNumber);
			String extNumber = stepsNumber.substring(0, 1);
			number = Integer.parseInt(extNumber);
			System.out.println("The steps number extracted is::" + number);
			waitInSeconds(5);
			for (int i = 0; i < number; i++) {
				try {
					if (stepComplete.isDisplayed()) {
						System.out.println("Clicking on step complete link");
						stepComplete.click();
					}
				} catch (NoSuchElementException e) {

				}
				String certCardTitle = null;
				String sectionName = null;
				try {
					if (certResCardTitle.isDisplayed()) {
						certCardTitle = certResCardTitle.getText();
					}
				} catch (NoSuchElementException e) {

				}

				try {
					if (cardTitle.isDisplayed()) {
						sectionName = cardTitle.getText();
					}
				} catch (NoSuchElementException e) {

				}
				if (number >= 1) {
					for (int j = 0; j < number; j++) {
						wait_for_page_load(10);
						try {
							if (certResCardTitle.isDisplayed()) {
								certCardTitle = certResCardTitle.getText();
								System.out.println("The section title is " + certCardTitle);
							}
						} catch (NoSuchElementException e) {

						}

						try {
							if (cardTitle.isDisplayed()) {
								sectionName = cardTitle.getText();
								System.out.println("The section name is " + sectionName);
							}
						} catch (NoSuchElementException e) {

						}
						if (sectionName != null && sectionName.contains("Personal Details")) {
							wait_for_element_present(submit);
							scrollDownToElement(submit);
							click_Element_Using_JS(submit);
							for (WebElement e : mandatoryFields) {
								String redField = e.getText();
								switch (redField) {
								case "Date of Birth is required.":
									System.out.println("The Date of birth is ");
									dob.clear();
									dob.sendKeys("11/11/1990");
									break;
								case "SSN is required.":
									System.out.println("The SSN number");
									SSNp.clear();
									SSNp.sendKeys(SSNNumber);
									SSNp.sendKeys(Keys.TAB);
									wait_in_seconds(15);
									break;
								case "Zip Code is required.":
									System.out.println("The Zip code field is required for Personal details section");
									zipcode.sendKeys("07086");
									break;
								case "Country is required.":
									System.out.println("The Country name is::United States");
									Select countryDropdown = new Select(country);
									countryDropdown.selectByVisibleText("United States");
									break;
								case "State is required.":
									System.out.println("The State is required for Personal details section");
									wait_for_element_present(stateList);
									Select state = new Select(stateList);
									state.selectByValue("AL");
									break;
								case "The city field is required.":
									System.out.println("The city field is required for Personal details section");
									city.sendKeys("NY");
									break;

								case "Specialties are required":
									System.out.println("Specialties are required for Personal details section");
									Select indusrty = new Select(industryType);
									indusrty.selectByValue("255");
									Select category = new Select(categoryType);
									category.selectByValue("Allied Health");
									waitInSeconds(5);
									scrollDown();
									scrollToElement(specialitiesSelect);
									specialitiesSelect.click();
									click_Element_Using_JS(selectFromDropDown);
									// selectFromDropDown.click();
									scrollDown();
									scrollDownToElement(submit);
									click_Element_Using_JS(submit);
									waitInSeconds(10);
									break;

								default:
									break;
								}

							}
						}

						else if (sectionName != null && sectionName.contains("Work Experience")) {
							wait_for_element_present(submit);
							scrollDown();
							scrollDownToElement(submit);
							click_Element_Using_JS(submit);
							for (WebElement e : mandatoryFields) {
								String redField = e.getText();
								switch (redField) {
								case "Company Name is required.":
									System.out.println("The Company name is::United States");
									cwname.sendKeys("Tiu");
									title.sendKeys("Sr Doctor");
									break;
								case "Job Title is required.":
									title.sendKeys("Sr Doctor");
									break;

								case "Country is required.":
									System.out.println("The Country name is::United States");
									Select countryDropdown = new Select(country);
									countryDropdown.selectByVisibleText("United States");
									break;
								case "State is required.":
									System.out.println("The Country name in Work experience::United States");
									wait_for_element_present(stateList0);
									Select state = new Select(stateList0);
									state.selectByValue("AL");
									break;
								case "City / Region is required.":
									System.out.println("City / Region is required in work experience.");
									city.sendKeys("NY");
									wait_in_seconds(5);
									wait_for_element_present(workingYesButton);
									click_Element_Using_JS(workingYesButton);
									// workingYesButton.click();
									Select month = new Select(startMonth);
									month.selectByVisibleText("Jan");
									Select year = new Select(startYear);
									year.selectByVisibleText("2010");
									scrollDownToElement(submit);
									click_Element_Using_JS(submit);
									waitInSeconds(10);
									break;
								default:
									break;
								}

							}
						}

						else if (sectionName != null && sectionName.equals("Education")) {
							wait_for_element_present(submit);
							scrollDown();
							scrollDownToElement(submit);
							click_Element_Using_JS(submit);
							for (WebElement e : mandatoryFields) {
								String redField = e.getText();
								switch (redField) {
								case "Institution Name is required.":
									System.out.println("The Institution Name is");
									intname.sendKeys("Test");
									subject.sendKeys("Test");
									break;
								case "Country is required.":
									System.out.println("The Country name is::United States");
									wait_for_element_present(country);
									Select countryDropdown = new Select(country);
									countryDropdown.selectByVisibleText("United States");
									break;
								case "State is required.":
									System.out.println("The State name is required i Educatio section");
									wait_for_element_present(stateList0);
									Select state = new Select(stateList0);
									state.selectByVisibleText("Arizona");
									break;
								case "City / Region is required.":
									System.out.println("City / Region is required.");
									city.sendKeys("NY");
									wait_in_seconds(10);
									click_Element_Using_JS(eduYesButton);
									Select month = new Select(startMonth);
									month.selectByVisibleText("Jan");
									Select year = new Select(startYear);
									year.selectByVisibleText("2010");
									scrollDownToElement(submit);
									click_Element_Using_JS(submit);
									waitInSeconds(10);
									break;
								default:
									break;
								}

							}
						} else if (sectionName != null && sectionName.contains("Professional Education")) {
							wait_for_element_present(submit);
							scrollDown();
							scrollDownToElement(submit);
							click_Element_Using_JS(submit);
							for (WebElement e : mandatoryFields) {
								String redField = e.getText();
								switch (redField) {
								case "Institution Name is required.":
									intsname.sendKeys("Test");
									break;
								case "Country is required.":
									institutionName.sendKeys("TIU");
									subjectofStudy.sendKeys("sos");
									System.out.println("The Country name is::United States");
									Select countryDropdown = new Select(peContryDropdown);
									countryDropdown.selectByVisibleText("United States");
									break;
								case "State is required.":
									System.out.println("The PE selection of state name is::Arizona");
									wait_for_element_present(stateList0);
									Select state = new Select(stateList0);
									state.selectByVisibleText("Arizona");
									break;
								case "City / Region is required.":
									System.out.println("City / Region is required.");
									city1.sendKeys("Thanda");
									wait_in_seconds(5);
									wait_for_element_present(eduYesButton);
									click_Element_Using_JS(eduYesButton);
									// eduYesButton.click();
									Select month = new Select(startMonth);
									month.selectByVisibleText("Jan");
									Select year = new Select(startYear);
									year.selectByVisibleText("2010");
									scrollDownToElement(saveAndContinue1);
									wait_in_seconds(5);
									scrollDown();
									click_Element_Using_JS(saveAndContinue1);
									// saveAndContinue1.click();
									wait_in_seconds(10);
									break;
								default:
									break;
								}

							}
						} else if (sectionName != null && sectionName.contains("Internship")) {

							wait_for_element_present(submit);
							scrollDownToElement(submit);
							click_Element_Using_JS(submit);
							for (WebElement e : mandatoryFields) {
								String redField = e.getText();
								switch (redField) {
								case "Country is required.":
									jobTtitle.sendKeys("Lead");
									employer.sendKeys("TIU");
									System.out.println("The Country name is::United States");
									Select countryDropdown = new Select(peContryDropdown);
									countryDropdown.selectByVisibleText("United States");
									break;
								case "State is required.":
									System.out.println("The Internship selection of state name is::Arizona");
									wait_for_element_present(stateList0);
									Select state = new Select(stateList0);
									state.selectByVisibleText("Arizona");
									break;
								case "City / Region is required.":
									System.out.println("City / Region is required in internship.");
									internshipCity.sendKeys("Thanda");
									wait_in_seconds(3);
									Select month = new Select(startMonth);
									month.selectByVisibleText("Jan");
									Select year = new Select(startYear);
									year.selectByVisibleText("2010");
									Select endmonth = new Select(endMonth);
									endmonth.selectByVisibleText("Jan");
									Select endyear = new Select(endYear);
									endyear.selectByVisibleText("2019");
									scrollDownToElement(saveAndContinue1);
									click_Element_Using_JS(saveAndContinue1);
									// saveAndContinue1.click();
									wait_in_seconds(10);
									break;
								default:
									break;
								}

							}
						} else if (sectionName != null && sectionName.contains("Residency")) {
							wait_for_element_present(submit);
							scrollDown();
							scrollDownToElement(submit);
							click_Element_Using_JS(submit);
							for (WebElement e : mandatoryFields) {
								String redField = e.getText();
								switch (redField) {

								case "Job title is required.":
									rsjobt.sendKeys("AIIMS");
									break;
								case "Country is required.":
									jobTtitleResidence.sendKeys("QWERTY");
									employerResidence.sendKeys("ASD");
									System.out.println("The Country name is::United States");
									Select countryDropdown = new Select(peContryDropdown);
									countryDropdown.selectByVisibleText("United States");
									break;
								case "State is required.":
									System.out.println("The Residency selection of state name is::Arizona");
									wait_for_element_present(stateList0);
									Select state = new Select(stateList0);
									state.selectByVisibleText("Arizona");
									break;
								case "City / Region is required.":
									System.out.println("City / Region is required.");
									residenceCity.sendKeys("Thanda");
									wait_in_seconds(3);
									Select month = new Select(startMonth);
									month.selectByVisibleText("Jan");
									Select year = new Select(startYear);
									year.selectByVisibleText("2010");
									Select endmonth = new Select(endMonth);
									endmonth.selectByVisibleText("Jan");
									Select endyear = new Select(endYear);
									endyear.selectByVisibleText("2019");
									scrollDownToElement(saveAndContinue1);
									click_Element_Using_JS(saveAndContinue1);
									// saveAndContinue1.click();
									wait_in_seconds(10);
									break;
								default:
									break;
								}

							}
						} else if (sectionName != null && sectionName.contains("Fellowship")) {
							wait_for_element_present(submit);
							scrollDown();
							click_Element_Using_JS(submit);
							for (WebElement e : mandatoryFields) {
								String redField = e.getText();
								switch (redField) {
								case "Employer is required.":
									fsemp.sendKeys("Test");
									break;
								case "Country is required.":
									jobTtitlefellowship.sendKeys("QWERTY");
									employerfellowship.sendKeys("ASD");
									System.out.println("The Country name is::United States");
									Select countryDropdown = new Select(peContryDropdown);
									countryDropdown.selectByVisibleText("United States");
									break;
								case "State is required.":
									System.out.println("The Fellowship selection of state name is::Arizona");
									wait_for_element_present(stateList0);
									Select state = new Select(stateList0);
									state.selectByVisibleText("Arizona");
									break;
								case "City / Region is required.":
									System.out.printf("City / Region is required.");
									fellowshipCity.sendKeys("Thanda");
									wait_in_seconds(3);
									Select month = new Select(startMonth);
									month.selectByVisibleText("Jan");
									Select year = new Select(startYear);
									year.selectByVisibleText("2010");
									Select endmonth = new Select(endMonth);
									endmonth.selectByVisibleText("Jan");
									Select endyear = new Select(endYear);
									endyear.selectByVisibleText("2019");
									scrollDownToElement(saveAndContinue1);
									click_Element_Using_JS(saveAndContinue1);
									// saveAndContinue1.click();
									wait_in_seconds(10);
									break;
								default:
									break;
								}

							}
						}

						else if (certCardTitle != null && certCardTitle.equals("State Licenses or Certificates")) {
							System.out.println("There are 2 or 1 steps to complete");
							wait_for_element_present(saveAndContinue1);
							scrollDownToElement(submit);
							wait_in_seconds(10);
							scrollDown();
							click_Element_Using_JS(submit);
							for (WebElement e : mandatoryFields) {
								String redField = e.getText();
								scrollDownToElement(e);
								switch (redField) {
								case "Certification Name is required.":
									System.out.println("The State Certification Name is required");
									wait_for_element_present(certification_name);
									certification_name.sendKeys("asd");
									break;
								case "State Authority is required.":
									Select selectStateAuthority = new Select(stateAuthority);
									selectStateAuthority.selectByValue("AL");
									certification_date.sendKeys("02/02/2025");
									scrollDown();
									wait_in_seconds(5);
									scrollDown();
									scrollToElement(saveAndContinue1);
									click_Element_Using_JS(saveAndContinue1);
									wait_for_page_load(10);
									// saveAndContinue1.click();
									wait_in_seconds(10);
									break;
								default:
									break;
								}
							}
						}

						else if (sectionName != null && sectionName.equals("Board Certification / Eligibility")) {
							System.out.printf("There is 1 steps to complete");
							wait_for_element_present(submit);
							scrollDownToElement(submit);
							wait_in_seconds(10);
							scrollDown();
							click_Element_Using_JS(submit);
							for (WebElement e : mandatoryFields) {
								String redField = e.getText();
								scrollDownToElement(e);
								switch (redField) {
								case "Certificate Name is required.":
									System.out.printf("The Board Certificate Name is required");
									wait_for_element_present(certification_name);
									certification_name.sendKeys("asd");

									break;
								case "Authority is required.":
									certificateAuthority.sendKeys("ASDF");
									// certification_date.sendKeys("02/02/2025");
									break;
								case "Date of Certification is required.":
									certification_date.sendKeys("02/02/2025");
									scrollDown();
									wait_in_seconds(5);
									click_Element_Using_JS(save);
									wait_in_seconds(5);
									clickhere.click();
									break;
								default:
									break;

								}

							}
						}

					}
				} else if (number <= 2) {

					if (certCardTitle != null && certCardTitle.equals("State Licenses or Certificates")) {
						System.out.println("There are 2 or 1 steps to complete");
						wait_for_element_present(saveAndContinue1);
						scrollDownToElement(submit);
						wait_in_seconds(10);
						wait_for_page_load(10);
						scrollDown();
						click_Element_Using_JS(submit);
						for (WebElement e : mandatoryFields) {
							String redField = e.getText();
							scrollDownToElement(e);
							switch (redField) {
							case "Certification Name is required.":
								System.out.println("The Certification Name is required");
								wait_for_element_present(certification_name);
								certification_name.sendKeys("asd");
								break;
							case "State Authority is required.":
								Select selectStateAuthority = new Select(stateAuthority);
								selectStateAuthority.selectByValue("AL");
								break;
							case "Date of Certification is required.":
								certification_date.sendKeys("02/02/2025");
								scrollDown();
								wait_in_seconds(5);
								scrollDown();
								saveAndContinue1.click();
								wait_in_seconds(5);
								break;
							default:
								break;
							}
						}
					} else if (sectionName.equals("Board Certification / Eligibility")) {
						System.out.println("There is 1 steps to complete");
						wait_for_element_present(submit);
						scrollDownToElement(submit);
						wait_in_seconds(10);
						scrollDown();
						click_Element_Using_JS(submit);
						for (WebElement e : mandatoryFields) {
							String redField = e.getText();
							scrollDownToElement(e);
							switch (redField) {
							case "Certificate Name is required.":
								System.out.printf("The Certificate Name is required");
								wait_for_element_present(certification_name);
								certification_name.sendKeys("asd");
								break;
							case "Authority is required.":
								certificateAuthority.sendKeys("ASDF");
								break;
							case "Date of Certification is required.":
								cdate.sendKeys("05/05/2025");
								scrollDown();
								// scrollToElement(save);
								save.click();
								wait_for_page_load(5);
								System.out.println("This is the  Board Certificate Section");
								// saveAndContinue1.click();
								// wait_for_element_present(clickhere);
								click_Element_Using_JS(clickhere);
								clickhere.click();
								break;
							default:
								break;

							}
						}
					}
				}
			}
		}

	}
}
