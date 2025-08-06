package TestSuite;



import java.awt.AWTException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.surecafe.TestBase.TestBase;
import com.surecafe.User.ClientLoginPage;
import com.surecafe.User.RegisterPage;
import com.relevantcodes.extentreports.LogStatus;

public class TC06_Verifyprofileflow extends TestBase {

	public static final Logger log = Logger.getLogger(TC06_Verifyprofileflow.class.getName());
	
	//@BeforeClass
	//public static void startTest() {

	//	test = extent.startTest("TC06_Verifyprofileflow");

	//}

	@BeforeMethod
	public void setup(Method result) throws IOException {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
		init();

	}

	@Test(priority = 1, enabled = true)
	public void Profileflow() throws IOException, InterruptedException, AWTException {

		log.info("<=========== StartedVerifyprofileflow ===========> ");
		openAppUrl();
		ClientLoginPage clp = new ClientLoginPage(driver);
		//clp.clientSignIn(OR.getProperty("emailIDp"), OR.getProperty("Password"));
		clp.clientSignIn("ats2025@yopmail.com", "surecafe");
		clp.Profilepicture();
		//clp.uploadprofilepicture();
		clp.Resumeupload();
		clp.Profile();
				
		
	}
	@AfterMethod
	public void afterMethod(ITestResult result) {
		get_result(result);

	}
	}