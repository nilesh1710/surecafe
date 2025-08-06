package com.surecafe.User;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;
import javax.mail.search.SearchTerm;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.surecafe.TestBase.TestBase;
import junit.framework.Assert;

public class RegisterPage extends TestBase {

	public static final Logger log = Logger.getLogger(RegisterPage.class.getName());

	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@href='https://dev.kredsafe.net/users/register/options']")
	WebElement registerHereLink;
	@FindBy(xpath = "//a[@href='https://dev.kredsafe.net/guest/verify']")
	WebElement registerUsingOnlineApplication;
	@FindBy(xpath = "//input[@class='emailInputText']")
	WebElement emailIDInput;
	@FindBy(xpath = "//input[@id='search-mobile']")
	WebElement yopmilInput;
	@FindBy(xpath = "//*[@id=\"id_frm_submit_reg\"]")
	WebElement registerSubmit;
	@FindBy(xpath = "//a[@title='Register Now']")
	WebElement yopmailRegisterNowButton;
	@FindBy(xpath = "//*[@id=\"idOTP\"]")
	WebElement idOTP;
	@FindBy(xpath = "//*[@id=\"btnSubmit\"]")
	WebElement verifyButton;

	public void registerUser(String emailID) throws IOException, InterruptedException, MessagingException {

		scrollDown();
		scrollDownToElement(registerHereLink);
		wait_in_seconds(5);
		try {
			if (registerHereLink.isDisplayed()) {
				System.out.printf("Clicking on the register link ");
				registerHereLink.click();
			}
		} catch (NoSuchElementException e) {

		}
		wait_in_seconds(10);
		scrollDownToElement(registerUsingOnlineApplication);
		registerUsingOnlineApplication.click();
		emailIDInput.sendKeys(emailID);
		wait_in_seconds(10);
		scrollDownToElement(registerSubmit);
		registerSubmit.click();
	}

	@FindBy(xpath = "//input[@id='identifierId']")
	WebElement emailField;
	@FindBy(xpath = "//span[text()='Next']")
	WebElement nextButton;
	@FindBy(xpath = "//input[@name='Passwd']")
	WebElement passwordField;
	@FindBy(xpath = "//*[@id=\\\":1v\\\"]")
	WebElement emailKredSafe;
	@FindBy(xpath = "//tbody/tr/td[1]/a[contains(@href,'https://dev.kredsafe.net/register')]")
	WebElement registerButon;
	@FindBy(xpath = "//a[contains(@href,'https://surecafe.tiuconsulting.us/frontend/auth/reset-password')]")
	WebElement resetpassword;
	
	@FindBy(xpath = "//*[@id=\"mail\"]/div/div/main/p[2]/a")
	WebElement resetpassword1;
	
	@SuppressWarnings("resource")
	public static void accessGmail(String emailID, String emailpassword) throws InterruptedException {
		// Set up ChromeDriver using WebDriverManager
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "D:/KredSafe (2)/KredSafe/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://mail.google.com/");
		WebElement emailField = driver.findElement(By.id("identifierId"));
		emailField.sendKeys(emailID);
		WebElement nextButton = driver.findElement(By.xpath("//span[text()='Next']"));
		nextButton.click();
		Thread.sleep(10000);
		WebElement passwordField = driver.findElement(By.name("Passwd"));
		passwordField.sendKeys(emailpassword);
		nextButton = driver.findElement(By.xpath("//span[text()='Next']"));
		nextButton.click();
		wait_for_page_load(20);
		// Pause for manual action
		System.out.println("Please perform manual action and press Enter to continue...");
		new java.util.Scanner(System.in).nextLine();
		Thread.sleep(30000);
		/* driver.quit() ; */
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("Switch to tab...");
		driver.switchTo().window(tabs.get(1));
	}
	
	
	@FindBy(xpath = "//input[@formcontrolname='password']")
    WebElement rpassword;
    @FindBy(xpath = "(//input[@type='password'])[2]")
    WebElement cpassword;
    @FindBy(xpath = "//input[@type='submit' and @value='Submit' and contains(@class, 'btn')]")
    WebElement rsubmit;

    @FindBy(xpath = "//a[contains(@href, 'https://surecafe.tiuconsulting.us/frontend/auth/reset-password?')]")
    WebElement resetLink;
    
    @SuppressWarnings("resource")
    public void accessYopmail() throws InterruptedException {
            
        driver.manage().window().maximize();
        driver.get("https://yopmail.com/");
        WebElement emailField = driver.findElement(By.id("login"));
        emailField.sendKeys("ats51@yopmail.com");
        WebElement submitYopmail = driver.findElement(By.id("refreshbut"));
        submitYopmail.click();
        wait_in_seconds(10);
        driver.switchTo().frame("ifmail");
        resetLink.click();
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("Switch to tab...");
		driver.switchTo().window(tabs.get(1));
         wait_for_page_load(10);
        rpassword.sendKeys("surecafe");
        cpassword.sendKeys("surecafe");
         waitInSeconds(10);
         click_Element_Using_JS(rsubmit);
         
         /*    // Get all window handles
         Set<String> allWindows = driver.getWindowHandles();
         // Store the current window handle
         String mainWindow = driver.getWindowHandle();
         // Switch to the new window
         for (String windowHandle : allWindows) {
             if (windowHandle.equals(mainWindow)) {
                 driver.switchTo().window(windowHandle);

                 String get_currenturl = driver.getCurrentUrl();
                 System.out.println("Current URL is:" + get_currenturl);

             }

         }*/
    }

    

	@FindBy(xpath = "//a[contains(text(),'Verify')]")
	WebElement SignupVerify;
	
	@FindBy(xpath = "//a[contains(@class, 'cust-btn') and contains(text(), 'Back To Login')]")
	WebElement Backtologin;
	
	//a[contains(@class, 'cust-btn') and contains(text(), 'Back To Login')]
	@FindBy(xpath = "//*[@id=\"email\"]")
	WebElement emails;
	 
	public void accessYopmailsignup(String Email) throws InterruptedException {
		// Set up ChromeDriver using WebDriverManager
		driver.manage().window().maximize();
        driver.get("https://yopmail.com/");
        WebElement emailField = driver.findElement(By.id("login"));
        emailField.sendKeys(Email);
        WebElement submitYopmail = driver.findElement(By.id("refreshbut"));
        submitYopmail.click();
        wait_in_seconds(10);
        driver.switchTo().frame("ifmail");
        SignupVerify.click();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("Switch to tab...");
		driver.switchTo().window(tabs.get(1));
		wait_in_seconds(10);
		click_Element_Using_JS(Backtologin);
		/*wait_in_seconds(10);
		wait_for_page_load(20);
		WebElement rgYopmailButton;
		System.out.println("Try catch block...");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();",
				rgYopmailButton = driver.findElement(By.xpath("//*[@title= 'Register Now']")));
		wait_for_element_present(rgYopmailButton);
		boolean dis;
		try {
			if (dis = rgYopmailButton.isDisplayed()) {
				System.out.println("The element is " + dis);
				rgYopmailButton.click();
			}
		} catch (NoSuchElementException e) {
			rgYopmailButton.click();
		}
		// Pause for manual action System.out.println("Please perform manual action and
		// press Enter to continue..."); new java.util.Scanner(System.in).nextLine();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("Switch to tab...");
		driver.switchTo().window(tabs.get(1));
		WebElement FirstName = driver.findElement(By.xpath("//*[@id=\\\"first_name\\\"]"));
		FirstName.sendKeys("ns");*/
	}

	@FindBy(xpath = "//input[@id='login']")
	WebElement yopmailField;
	@FindBy(xpath = "<input type=\"text\" id=\"webmail\" onkeyup=\"try { kp(event); } catch (ex) {}\">")
	WebElement OTP;
	@FindBy(xpath = "//*[@id=\"refreshbut\"]/button")
	WebElement submitYopmail;
	@FindBy(xpath = "//a[contains(@href, 'https://dev.kredsafe.net/register') and @title='Register Now']")
	WebElement registerYopmailButton;

	public void accessYopmailForOnlineRegister(String email) throws InterruptedException, IOException {
		waitInSeconds(10);
		yopmailField.sendKeys(email);
		waitInSeconds(5);
		submitYopmail.click();
		waitInSeconds(5);
		// wait_for_element_present(registerYopmailButton);
		waitInSeconds(5);
		waitInSeconds(5);
		driver.switchTo().frame("ifmail");
		mouseClickByLocator(registerYopmailButton);
		System.out.println("Try catch block...");
		boolean dis;
		try {
			if (dis = registerYopmailButton.isDisplayed()) {
				System.out.println("The element is " + dis);
				wait_in_seconds(10);
				wait_for_element_present(registerYopmailButton);
				scrollToElement(registerYopmailButton);
				click_Element_Using_JS(registerYopmailButton);
			}
		} catch (NoSuchElementException e) {
			wait_in_seconds(10);
			wait_for_element_present(registerYopmailButton);
			wait_for_element_present(registerYopmailButton);
			registerYopmailButton.click();
		}
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("Switch to tab...");
		driver.switchTo().window(tabs.get(1));
	}

	@FindBy(xpath = "//*[@id='first_name']")
	WebElement FirstName;
	@FindBy(xpath = "//*[@id='last_name']")
	WebElement LasName;
	@FindBy(xpath = "//*[@id='passwordL']")
	WebElement Password;
	@FindBy(xpath = "//*[@id='password_conf']")
	WebElement ConfirmPassword;
	@FindBy(xpath = "//*[@id='mobilenumber']")
	WebElement MobileNumber;
	@FindBy(xpath = "//*[@id='zip']")
	WebElement Zipcode;
	@FindBy(xpath = "//*[@id='id_frm_submit']")
	WebElement accoutRegisterButton;

	public void createAnAccount(String firstName, String lastName, String createAccountPassword,
			String createAccountCPassword, String mobileNumber) {
		wait_for_element_present(FirstName);
		FirstName.sendKeys(firstName);
		LasName.sendKeys(lastName);
		Password.sendKeys(createAccountPassword);
		ConfirmPassword.sendKeys(createAccountCPassword);
		MobileNumber.sendKeys(mobileNumber);
		Zipcode.sendKeys("111111");
		accoutRegisterButton.click();
	}

	@FindBy(xpath = "/html/body/div[1]/div[1]/div/div/div[2]/div/div/div[2]/a")
	WebElement accoutRegisterUploadButton;
	@FindBy(xpath = "//*[@id=\"resume_file_upload\"]")
	WebElement uploadResumeButton;
	@FindBy(xpath = "//*[@id=\"id_frm_submit_resume\"]")
	WebElement uploadResumeSubmitButton;
	@FindBy(xpath = "//button[@id=\"id_frm_submit_resume_data\"]")
	WebElement submitButton;
	@FindBy(xpath = "//input[@id=\"zip\"]")
	WebElement zipCode;
	@FindBy(xpath = "//span[@class=\"select2-search select2-search--inline\"]")
	WebElement specialitiesSelect;
	@FindBy(xpath = "//ul[@class=\"select2-results__options\"]")
	WebElement specialtiesDropDown;
	@FindBy(xpath = "//*[@id=\"select2-industry_specialitiesR-results\"]/li[1]")
	WebElement selectFromDropDown;

	public void registerUserByUploadingResume() throws InterruptedException, AWTException {
		wait_for_page_load(5);
		scrollToElement(registerHereLink);
		click_Element_Using_JS(registerHereLink);
		wait_in_seconds(10);
		accoutRegisterUploadButton.click();
		wait_in_seconds(3);
		boolean dis = uploadResumeButton.isDisplayed();
		log.info("The element is dislayed " + dis);
		driver.navigate().refresh();
		wait_in_seconds(10);
		WebElement upload = driver.findElement(By.xpath("//*[@id='resume_file_upload']"));
		Actions act = new Actions(driver);
		act.moveToElement(upload).click().perform();
		wait_in_seconds(5);
		file_upload("C:\\Users\\Admin\\Downloads\\DerekSmith(1).doc");
		uploadResumeSubmitButton.click();
		wait_for_page_load(10);
		// zipCode.sendKeys("123456");
		// specialitiesSelect.click();
		// selectFromDropDown.click();
		wait_in_seconds(5);
		scrollToElement(submitButton);
		scrollDownToElement(submitButton);
		click_Element_Using_JS(submitButton);
	}

	@FindBy(xpath = "//a[contains(@href, 'https://dev.kredsafe.net/password/reset/') and @title='Set Password']")
	WebElement setPasswordYopmail;
	@FindBy(xpath = "//input[@id='passwordL']")
	WebElement passwordL;
	@FindBy(xpath = "//input[@id=\"password_conf\"]")
	WebElement password_conf;
	@FindBy(xpath = "//button[@type=\"submit\"]")
	WebElement passwordSubmit;
	@FindBy(xpath = "//a[contains(@href, '#') and @id='generatePassword']")
	WebElement generatePassword;
	@FindBy(xpath = "//span[@class='fa fa-fw field-icon toggle-password fa-eye-slash']")
	WebElement viewPassword;

	public void setPasswordYopmail(String resumeEmail) throws InterruptedException, IOException {
		waitInSeconds(10);
		yopmailField.sendKeys(resumeEmail);
		waitInSeconds(5);
		submitYopmail.click();
		waitInSeconds(5);
		driver.switchTo().frame("ifmail");
		// mouseClickByLocator(setPasswordYopmail);
		setPasswordYopmail.click();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("Switch to tab...");
		driver.switchTo().window(tabs.get(1));
		wait_for_page_load(10);
		wait_for_element_present(passwordL);
		passwordL.sendKeys("Nilesh@2025");
		password_conf.sendKeys("Nilesh@2025");
		String gPassword = passwordL.getText();
		System.out.println("The gpassword is :" + gPassword);
		wait_in_seconds(10);
		scrollDownToElement(passwordSubmit);
		scrollToElement(passwordSubmit);
		wait_in_seconds(10);
		passwordSubmit.click();
	}

	@FindBy(xpath = "(//button[contains(text(), \"Subscribe\")])[3]")
	WebElement subscribeButton;
	@FindBy(xpath = "//button[@id='home-tab_1']")
	WebElement creditCard;
	@FindBy(xpath = "//button[@id='home-tab_2']")
	WebElement Paypal;
	@FindBy(xpath = "//*[@id=\"confirm_pay_btn\"]")
	///html/body/div[4]/div[1]/section/div/div/div[2]/div/div/div/div[2]/div/form/div/div[2]/button
	WebElement confirmAndPayButton;
	@FindBy(xpath = "//input[@id='cardNumber']")
	WebElement cardNumber;
	@FindBy(xpath = "//input[@id='cardExpiry']")
	WebElement cardExpiry;
	@FindBy(xpath = "//input[@id='cardCvc']")
	WebElement cardCVC;
	@FindBy(xpath = "//input[@id='billingName']")
	WebElement billingName;
	@FindBy(xpath = "//input[@id='billingAddressLine1']")
	WebElement billingAddress;
	@FindBy(xpath = "//input[@id='billingPostalCode']")
	WebElement billingPostalCode;
	@FindBy(xpath = "//input[@id='billingLocality']")
	WebElement billingCity;
	@FindBy(xpath = "//select[@id='billingAdministrativeArea']")
	WebElement billingState;
	@FindBy(xpath = "//*[@id=\"billing-address-autocomplete-results\"]/div[1]/button/div/svg/g/rect")
	WebElement cancelBillingAddress;
	@FindBy(xpath = "//div[@class='SubmitButton-IconContainer']")
	WebElement paySubmitButton;
	@FindBy(xpath = "//a[@href='https://dev.kredsafe.net/admin/home']")
	WebElement clickHere;
	@FindBy(xpath = "//*[@id=\"id_frm_submit\"]")
	WebElement saveAndContinue1;
	@FindBy(xpath = "//*[@id=\"noId\"]")
	WebElement No;
	
	

	@SuppressWarnings("deprecation")
	public void subscriptionPlans() throws InterruptedException, IOException {
		try {
			wait_for_page_load(10);
			String get_currenturl = driver.getCurrentUrl();
			System.out.println("Current URL is:" + get_currenturl);
			System.out.println("URL matching --> Part executed");
			if (get_currenturl.equals("https://dev.kredsafe.net/user/subscription/cart")) {
				wait_for_element_present(creditCard);
				// wait_in_seconds(10);
				creditCard.click();
				wait_in_seconds(10);
				wait_for_element_present(confirmAndPayButton);
				click_Element_Using_JS(confirmAndPayButton);
				// confirmAndPayButton.click();
				wait_for_page_load(10);
				wait_for_element_present(cardNumber);
				cardNumber.sendKeys("4111111111111111");
				cardExpiry.sendKeys("09/25");
				cardCVC.sendKeys("111");
				billingName.sendKeys("hcheeti");
				billingAddress.sendKeys("ct");
				billingCity.sendKeys("Nagpur");
				billingPostalCode.sendKeys("441108");
				Select state = new Select(billingState);
				state.getOptions().get(2).click();
				wait_in_seconds(10);
				paySubmitButton.click();
				wait_for_page_load(10);
				String get_url = driver.getCurrentUrl();
				Assert.assertEquals("https://dev.kredsafe.net/user/subscription/dashboard", get_url);
				System.out.println("Current URL is:" + get_url);
				System.out.println("URL matching --> Part executed");
			}
		} catch (NoSuchElementException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		try {
			waitInSeconds(10);
			String get_currenturl = driver.getCurrentUrl();
			if (get_currenturl.equals("https://dev.kredsafe.net/user/subscriptions")) {
				wait_for_element_present(subscribeButton);
				// scrollDown();
				scrollDownToElement(subscribeButton);
				// scrollToElement(subscribeButton);
				// wait_for_element_present(subscribeButton);
				wait_in_seconds(5);
				click_Element_Using_JS(subscribeButton);
				// subscribeButton.click();
				wait_for_page_load(10);
				wait_for_element_present(creditCard);
				wait_in_seconds(20);
				click_Element_Using_JS(creditCard);
				// creditCard.click();
				waitInSeconds(5);
				wait_for_element_present(confirmAndPayButton);
				scrollDownToElement(confirmAndPayButton);
				click_Element_Using_JS(confirmAndPayButton);
				wait_for_element_present(cardNumber);
				cardNumber.sendKeys("4111111111111111");
				cardExpiry.sendKeys("09/25");
				cardCVC.sendKeys("111");
				billingName.sendKeys("hcheeti");
				billingAddress.sendKeys("Nagpur");
				billingCity.sendKeys("Nagpur");
				billingPostalCode.sendKeys("441108");
				Select state = new Select(billingState);
				state.getOptions().get(2).click();
				scrollDown();
				scrollDownToElement(paySubmitButton);
				wait_in_seconds(5);
				click_Element_Using_JS(paySubmitButton);
				// paySubmitButton.click();
				wait_for_page_load(10);
				String get_url = driver.getCurrentUrl();
				Assert.assertEquals("https://dev.kredsafe.net/user/subscription/dashboard", get_url);
				System.out.println("Current URL is:" + get_url);
				System.out.println("URL matching --> Part executed");
			}
		} catch (NoSuchElementException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}

	}

	public void FormPlans() throws InterruptedException, IOException {
		wait_for_page_load(10);
		wait_for_element_present(confirmAndPayButton);
		wait_in_seconds(5);
		scrollToElement(confirmAndPayButton);
		//scrollDown();
		click_Element_Using_JS(confirmAndPayButton);
		waitInSeconds(10);
		cardNumber.sendKeys("4111111111111111");
		cardExpiry.sendKeys("09/25");
		cardCVC.sendKeys("111");
		billingName.sendKeys("hcheeti");
		billingAddress.sendKeys("Nagpur");
		billingCity.sendKeys("Nagpur");
		billingPostalCode.sendKeys("441108");
		Select state = new Select(billingState);
		state.getOptions().get(2).click();
		wait_for_element_present(paySubmitButton);
		wait_in_seconds(5);
		scrollDown();
		click_Element_Using_JS(paySubmitButton);
	}

	public static String readGmail(String USER_NAME, String PASSWORD)
			throws MessagingException, IOException, InterruptedException {
		RegisterPage gmailHelper = new RegisterPage(userName, password);
		String keyword = "Daily Status - KredSafe Automation Testing";
		@SuppressWarnings("deprecation")
		Date mailSendTime = new Date("Oct 21, 2024, 10:42 PM");
		System.out.println("Mail send time : " + mailSendTime.toString());
		List<Message> inboxMessageList = gmailHelper.searchEmail(0, "INBOX", keyword);
		Message firstMessage = inboxMessageList.get(0);
		String body = gmailHelper.getText(true, firstMessage);
		System.out.println("Message Subject : " + firstMessage.getSubject());
		System.out.println("Message Content : " + body);
		return body;
	}

	private static String imapHost = "imap.gmail.com";
	private static String imapPort = "993";
	private static String userName = null;
	private static String password = null;

	/**
	 * Constructor
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public RegisterPage(String userName, String password) {

		RegisterPage.userName = userName;
		RegisterPage.password = password;
	}

	/**
	 * Set IMAP session
	 */
	public Session setIMAPSession() {

		// IMAP settings
		Properties properties = new Properties();
		// server setting
		properties.put("mail.imap.host", imapHost);
		properties.put("mail.imap.port", imapPort);
		// SSL setting
		properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.imap.socketFactory.fallback", "false");
		properties.setProperty("mail.imap.socketFactory.port", String.valueOf(imapPort));
		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("hcheeti@tiuconsulting.com", "sTGV@8777*");// Specify the Username and
																								// // the PassWord
			}

		});
		return session;
	}

	/**
	 * Searches for e-mail messages containing the specified keyword in Subject
	 * field.
	 * 
	 * @param no         of seconds to wait before checking for mails
	 * @param folderName (e.g. INBOX)
	 * @param keyword    Search with this Keyword in subject line
	 * @param Date       object after which emails to be searched. Null for any date
	 * @throws InterruptedException
	 */
	public List<Message> searchEmail(int noOfSecToWait, String folderName, final String keyword)
			throws InterruptedException {
		Session session = setIMAPSession();
		List<Message> returnList = new ArrayList<>();
		try {
			Thread.sleep(noOfSecToWait * 1000);
			System.out.println("Connects to Message Store");
			Store store = session.getStore("imap");
			store.connect(userName, password);
			System.out.println("Opens folder : " + folderName);
			Folder folderInbox = store.getFolder(folderName);
			folderInbox.open(Folder.READ_ONLY);
			System.out.println("Creates search condition for Folder : " + folderName
					+ ". Searching message with Subject line : " + keyword);
			SearchTerm searchCondition = new SearchTerm() {
				private static final long serialVersionUID = 1L;

				@Override
				public boolean match(Message message) {
					try {
						if (message.getSubject().contains(keyword)) {
							return true;
						}
					} catch (MessagingException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}
					return false;
				}
			};
			System.out.println("Performs search through the folder : " + folderName);
			Message[] foundMessages = folderInbox.search(searchCondition);

			System.out.println("Number of messages found : " + foundMessages.length);
			for (int i = 0; i < foundMessages.length; i++) {
				Message message = foundMessages[i];
				/* assuming you retrieved 'message' from your folder object */
				Message copyOfMessage = new MimeMessage((MimeMessage) message);
				returnList.add(copyOfMessage);
			}
			System.out.println("Disconnecting...");
			folderInbox.close(false);
			store.close();
		} catch (NoSuchProviderException ex) {
			System.out.println("No provider found.\n" + ex.getMessage());
			ex.printStackTrace();
		} catch (MessagingException ex) {
			System.out.println("Could not connect to the message store.\n" + ex.getMessage());
			ex.printStackTrace();
		}
		return returnList;
	}

	/**
	 * Return the primary text content of the message part.
	 */
	public String getText(boolean textIsHtml, Part p) throws MessagingException, IOException {

		if (p.isMimeType("text/*")) {
			String s = (String) p.getContent();
			textIsHtml = p.isMimeType("text/html");
			return s;
		}
		if (p.isMimeType("multipart/alternative")) {

			System.out.println("refer html text over plain text");
			// prefer html text over plain text
			Multipart mp = (Multipart) p.getContent();
			String text = null;
			for (int i = 0; i < mp.getCount(); i++) {
				Part bp = mp.getBodyPart(i);
				if (bp.isMimeType("text/plain")) {
					if (text == null)
						text = getText(textIsHtml, bp);
					continue;
				} else if (bp.isMimeType("text/html")) {
					String s = getText(textIsHtml, bp);
					if (s != null)
						return s;
				} else {
					return getText(textIsHtml, bp);
				}
			}
			return text;
		} else if (p.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) p.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				String s = getText(textIsHtml, mp.getBodyPart(i));
				if (s != null)
					return s;
			}
		}

		return null;
	}

	@FindBy(xpath = "//*[@id=\"mail\"]/div/div/table/tbody/tr/td/p[2]")
	WebElement OTPCode;

	public void accessYopmaiforOTP() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://yopmail.com/");
		WebElement emailField = driver.findElement(By.id("login"));
		//clp.clientSignIn(OR.getProperty("emailID4")));
		emailField.sendKeys("ts1234@yopmail.com");
		WebElement submitYopmail = driver.findElement(By.id("refreshbut"));
		submitYopmail.click();
		wait_for_page_load(20);
		waitInSeconds(10);
		driver.switchTo().frame("ifmail");
		String OTP = OTPCode.getText();
		System.out.printf("Capture OTP code is: " + OTP);
	}

	@SuppressWarnings("resource")
	public void verifyOTPFunctionality(String source, String clientSignIn) throws InterruptedException {
		switch (source) {
		case "EmailOTP":
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
			String code = OTP.substring(99);
			System.out.printf("Extracted OTP code is: " + code);
			String otpcode = code.substring(0, 6);
			System.out.printf("\nOTP code is:" + otpcode);
			ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
			System.out.println("Switch to tab...");
			driver.switchTo().window(tab.get(0));
			idOTP.sendKeys(otpcode);
			verifyButton.click();
			break;
		case "SMSOTP":
			waitInSeconds(10);
			// Pause for manual action
			System.out.println(
					"Please check the Registered Mobile number for the OTP received, enter the OTP from the phone SMS and press Enter to continue...");
			new java.util.Scanner(System.in).nextLine();
			System.out.println("Clicking on verify button");
			verifyButton.click();
			break;
		default:
			break;
		}
	}

	public void verifyCompleteSection() throws InterruptedException, AWTException {

		String get_currenturl = driver.getCurrentUrl();
		System.out.println("Current URL is:" + get_currenturl);
		System.out.println("URL matching --> Part executed");
		for (int i = 0; i <= 10; i++) {
			if (!get_currenturl.contains("https://dev.kredsafe.net/user/statement")) {
				scrollDownToElement(saveAndContinue1);
				wait_in_seconds(5);
				scrollDown();
				saveAndContinue1.click();
			}

		}

	}

	@FindBy(xpath = "/html/body/div[3]/aside/div/div[2]/nav/ul/li[8]/a/i")
	WebElement Subscription;
	@FindBy(xpath = "//*[@id=\"subBoxInfoClose\"]/section/div/div/div[2]/div/div/div/div[2]/div[9]/a")
	WebElement Upgradeplan;
	@FindBy(xpath = "//*[@id=\"upgradePlanTable\"]/tbody/tr[2]/td[7]/a/i")
	WebElement Upgrade;
	
	//@FindBy(xpath = "	/html/body/div[3]/div[1]/section/div/div/div[2]/div/div/div/div[2]/div[11]/div/div[2]/div/table/tbody/tr/td[6]/a\r\n"
			//+ "")
	//WebElement Upgrade;

	@SuppressWarnings("deprecation")
	public void subscriptionPlans1() throws InterruptedException, IOException {
		try {
			wait_for_page_load(10);
			String get_currenturl = driver.getCurrentUrl();
			System.out.println("Current URL is:" + get_currenturl);
			System.out.println("URL matching --> Part executed");
			if (get_currenturl.equals("https://dev.kredsafe.net/user/subscription/cart")) {
				wait_for_element_present(creditCard);
				// wait_in_seconds(10);
				creditCard.click();
				wait_in_seconds(10);
				wait_for_element_present(confirmAndPayButton);
				click_Element_Using_JS(confirmAndPayButton);
				// confirmAndPayButton.click();
				wait_for_page_load(10);
				wait_for_element_present(cardNumber);
				cardNumber.sendKeys("4111111111111111");
				cardExpiry.sendKeys("09/25");
				cardCVC.sendKeys("111");
				billingName.sendKeys("hcheeti");
				billingAddress.sendKeys("ct");
				billingCity.sendKeys("Nagpur");
				billingPostalCode.sendKeys("441108");
				Select state = new Select(billingState);
				state.getOptions().get(2).click();
				wait_in_seconds(10);
				paySubmitButton.click();
				wait_for_page_load(10);
				String get_url = driver.getCurrentUrl();
				Assert.assertEquals("https://dev.kredsafe.net/user/subscription/dashboard", get_url);
				System.out.println("Current URL is:" + get_url);
				System.out.println("URL matching --> Part executed");
			}
		} catch (NoSuchElementException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		try {
			waitInSeconds(10);
			String get_currenturl = driver.getCurrentUrl();
			if (get_currenturl.equals("https://dev.kredsafe.net/user/subscriptions")) {
				wait_for_element_present(subscribeButton);
				// scrollDown();
				scrollDownToElement(subscribeButton);
				// scrollToElement(subscribeButton);
				// wait_for_element_present(subscribeButton);
				wait_in_seconds(5);
				click_Element_Using_JS(subscribeButton);
				// subscribeButton.click();
				wait_for_page_load(10);
				wait_for_element_present(creditCard);
				wait_in_seconds(20);
				click_Element_Using_JS(creditCard);
				// creditCard.click();
				waitInSeconds(5);
				wait_for_element_present(confirmAndPayButton);
				scrollDownToElement(confirmAndPayButton);
				click_Element_Using_JS(confirmAndPayButton);
				wait_for_element_present(cardNumber);
				cardNumber.sendKeys("4111111111111111");
				cardExpiry.sendKeys("09/25");
				cardCVC.sendKeys("111");
				billingName.sendKeys("hcheeti");
				billingAddress.sendKeys("Nagpur");
				billingCity.sendKeys("Nagpur");
				billingPostalCode.sendKeys("441108");
				Select state = new Select(billingState);
				state.getOptions().get(2).click();
				scrollDown();
				scrollDownToElement(paySubmitButton);
				wait_in_seconds(5);
				click_Element_Using_JS(paySubmitButton);
				// paySubmitButton.click();
				wait_for_page_load(10);
				String get_url = driver.getCurrentUrl();
				Assert.assertEquals("https://dev.kredsafe.net/user/subscription/dashboard", get_url);
				System.out.println("Current URL is:" + get_url);
				System.out.println("URL matching --> Part executed");
			}
		} catch (NoSuchElementException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		try {
			waitInSeconds(10);
			String get_currenturl = driver.getCurrentUrl();
			if (get_currenturl.equals("https://dev.kredsafe.net/user/subscription/upgrade/cart")) {
				wait_for_page_load(10);
				wait_for_element_present(creditCard);
				wait_in_seconds(20);
				click_Element_Using_JS(creditCard);
				// creditCard.click();
				waitInSeconds(10);
				wait_for_element_present(confirmAndPayButton);
				scrollDownToElement(confirmAndPayButton);
				click_Element_Using_JS(confirmAndPayButton);
				wait_for_element_present(cardNumber);
				cardNumber.sendKeys("4111111111111111");
				cardExpiry.sendKeys("09/25");
				cardCVC.sendKeys("111");
				billingName.sendKeys("hcheeti");
				billingAddress.sendKeys("Nagpur");
				billingCity.sendKeys("Nagpur");
				billingPostalCode.sendKeys("441108");
				Select state = new Select(billingState);
				state.getOptions().get(2).click();
				scrollDown();
				scrollDownToElement(paySubmitButton);
				wait_in_seconds(5);
				click_Element_Using_JS(paySubmitButton);
				// paySubmitButton.click();
				wait_for_page_load(10);
				String get_url = driver.getCurrentUrl();
			}
		} catch (NoSuchElementException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void Renewsubscription() throws InterruptedException, IOException {
		wait_for_page_load(10);
		wait_for_element_present(Subscription);
		click_Element_Using_JS(Subscription);
		wait_for_element_present(Upgradeplan);
		click_Element_Using_JS(Upgradeplan);
		scrollToElement(Upgrade);
		wait_for_element_present(Upgrade);
		click_Element_Using_JS(Upgrade);
	}

	public void Renewplan() throws InterruptedException, IOException {
		try {
			wait_for_page_load(10);
			String get_currenturl = driver.getCurrentUrl();
			System.out.println("Current URL is:" + get_currenturl);
			System.out.println("URL matching --> Part executed");
			if (get_currenturl.equals("https://dev.kredsafe.net/user/subscription/cart")) {
				wait_for_element_present(creditCard);
				// wait_in_seconds(10);
				creditCard.click();
				wait_in_seconds(10);
				wait_for_element_present(confirmAndPayButton);
				click_Element_Using_JS(confirmAndPayButton);
				// confirmAndPayButton.click();
				wait_for_page_load(10);
				wait_for_element_present(cardNumber);
				cardNumber.sendKeys("4111111111111111");
				cardExpiry.sendKeys("09/25");
				cardCVC.sendKeys("111");
				billingName.sendKeys("hcheeti");
				billingAddress.sendKeys("ct");
				billingCity.sendKeys("Nagpur");
				billingPostalCode.sendKeys("441108");
				Select state = new Select(billingState);
				state.getOptions().get(2).click();
				wait_in_seconds(10);
				paySubmitButton.click();
				wait_for_page_load(10);
				String get_url = driver.getCurrentUrl();
				Assert.assertEquals("https://dev.kredsafe.net/user/subscription/dashboard", get_url);
				System.out.println("Current URL is:" + get_url);
				System.out.println("URL matching --> Part executed");
			}
		} catch (NoSuchElementException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		try {
			waitInSeconds(10);
			String get_currenturl = driver.getCurrentUrl();
			if (get_currenturl.equals("https://dev.kredsafe.net/user/subscriptions")) {
				wait_for_element_present(subscribeButton);
				// scrollDown();
				scrollDownToElement(subscribeButton);
				// scrollToElement(subscribeButton);
				// wait_for_element_present(subscribeButton);
				wait_in_seconds(5);
				click_Element_Using_JS(subscribeButton);
				// subscribeButton.click();
				wait_for_page_load(10);
				wait_for_element_present(creditCard);
				wait_in_seconds(20);
				click_Element_Using_JS(creditCard);
				// creditCard.click();
				waitInSeconds(5);
				wait_for_element_present(confirmAndPayButton);
				scrollDownToElement(confirmAndPayButton);
				click_Element_Using_JS(confirmAndPayButton);
				wait_for_element_present(cardNumber);
				cardNumber.sendKeys("4111111111111111");
				cardExpiry.sendKeys("09/25");
				cardCVC.sendKeys("111");
				billingName.sendKeys("hcheeti");
				billingAddress.sendKeys("Nagpur");
				billingCity.sendKeys("Nagpur");
				billingPostalCode.sendKeys("441108");
				Select state = new Select(billingState);
				state.getOptions().get(2).click();
				scrollDown();
				scrollDownToElement(paySubmitButton);
				wait_in_seconds(5);
				click_Element_Using_JS(paySubmitButton);
				// paySubmitButton.click();
				wait_for_page_load(10);
				String get_url = driver.getCurrentUrl();
				Assert.assertEquals("https://dev.kredsafe.net/user/subscription/dashboard", get_url);
				System.out.println("Current URL is:" + get_url);
				System.out.println("URL matching --> Part executed");
			}
		} catch (NoSuchElementException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		try {
			waitInSeconds(10);
			String get_currenturl = driver.getCurrentUrl();
			if (get_currenturl.equals("https://dev.kredsafe.net/user/subscription/upgrade/cart")) {
				wait_for_page_load(10);
				// wait_for_element_present(creditCard);
				// wait_in_seconds(20);
				// click_Element_Using_JS(creditCard);
				// creditCard.click();No
				waitInSeconds(10);
				//scrollDownToElement(No);
				//click_Element_Using_JS(No);
				wait_for_element_present(confirmAndPayButton);
				scrollDownToElement(confirmAndPayButton);
				click_Element_Using_JS(confirmAndPayButton);
				wait_for_element_present(cardNumber);
				cardNumber.sendKeys("4111111111111111");
				cardExpiry.sendKeys("09/25");
				cardCVC.sendKeys("111");
				billingName.sendKeys("hcheeti");
				billingAddress.sendKeys("Nagpur");
				billingCity.sendKeys("Nagpur");
				billingPostalCode.sendKeys("441108");
				Select state = new Select(billingState);
				state.getOptions().get(2).click();
				scrollDown();
				scrollDownToElement(paySubmitButton);
				wait_in_seconds(5);
				click_Element_Using_JS(paySubmitButton);
			}
		} catch (NoSuchElementException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}

	}
}
