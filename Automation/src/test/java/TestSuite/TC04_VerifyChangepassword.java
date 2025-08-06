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



public class TC04_VerifyChangepassword extends TestBase {
	public static final Logger log = Logger.getLogger(TC04_VerifyChangepassword.class.getName());

	//@BeforeClass
	//public static void startTest() {

	//test = extent.startTest("TC04_VerifyChangepassword");

	//}
		
	@BeforeMethod
	public void setup(Method result) throws IOException {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
		init();	
	}
	
	@Test(priority = 1, enabled = true)
	public void clientLogin() throws IOException, InterruptedException, AWTException {
		
		log.info("<===========Started Verifying TC04_VerifyChangepassword===========> ");
		openAppUrl();
		ClientLoginPage clp = new ClientLoginPage(driver);
		RegisterPage rp = new RegisterPage(driver);
		//clp.clientSignIn(OR.getProperty("emailID1"), OR.getProperty("createAccountPassword1"));
		wait_for_page_load(5);
		waitInSeconds(10);
		clp.Changepassword();
		rp.accessYopmail();
		
	    
	}
	@AfterMethod
	public void afterMethod(ITestResult result) {
		get_result(result);

	}
}