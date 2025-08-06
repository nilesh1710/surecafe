package TestSuite;

import java.awt.AWTException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.surecafe.TestBase.TestBase;
import com.surecafe.User.ClientLoginPage;
import com.surecafe.User.RegisterPage;
import com.relevantcodes.extentreports.LogStatus;



public class TC03_Verifyuploadprofilepicture extends TestBase {
	public static final Logger log = Logger.getLogger(TC03_Verifyuploadprofilepicture.class.getName());

	//@BeforeClass
	//public static void startTest() {

		//est = extent.startTest("TC03_Verifyuploadprofilepicture");

	//}
	
	@BeforeMethod
	public void setup(Method result) throws IOException {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
		init();
	}	
	
	@Test(priority = 1, enabled = true)
	public void ProfilePcture() throws IOException, InterruptedException, AWTException {

		log.info("<===========Started Verifying ProfilePcture===========> ");
		openAppUrl();
		ClientLoginPage clp = new ClientLoginPage(driver);
		//clp.clientSignIn(OR.getProperty("emailID"), OR.getProperty("createAccountPassword"));
		//clp.clientSignIn("ats2025@yopmail.com","surecafe");
		wait_for_page_load(5);
		waitInSeconds(10);
		//For Negative testing change the upload file path and file types.
		//clp.ssm();
		//clp.submissin();//JRGos project
		
		clp.uploadprofilepicture();
			
		//clp.Profilepicture();
		//clp.Resumeupload();
		
	}
	
	
	
	@Test(priority = 2, enabled = false)
	public void Resumeupload() throws IOException, InterruptedException, AWTException {

		log.info("<===========Started Verifying ProfilePcture===========> ");
		openAppUrl();
		ClientLoginPage clp = new ClientLoginPage(driver);
		clp.clientSignIn(OR.getProperty("emailID"), OR.getProperty("createAccountPassword"));
		wait_for_page_load(5);
		waitInSeconds(10);
		clp.Resumeupload();
		
	}
	@AfterMethod
	public void afterMethod(ITestResult result) {
		get_result(result);

	}
	
	
	
}