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

public class TC01B_Verifyloginbyfacebook extends TestBase {

	public static final Logger log = Logger.getLogger(TC01B_Verifyloginbyfacebook.class.getName());
	//@BeforeClass
	//public static void startTest() {

	//	test = extent.startTest("TC01B_Verifyloginbyfacebook");

	//}

	@BeforeMethod
	public void setup(Method result) throws IOException {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
		init();

	}

	@Test(priority = 1, enabled = true)
	public void Signin() throws IOException, InterruptedException, AWTException {

		log.info("<=========== Started Verifying Signin facebook ===========> ");
		openAppUrl();
		ClientLoginPage clp = new ClientLoginPage(driver);
		//clp.clientSignIn(OR.getProperty("emailID"), OR.getProperty("createAccountPassword"));
				//clp.clientSignIn("pdhole@tiuconsulting.com", "surecafe");
		clp.Facebooksigin();
		
	}
	@AfterMethod
	public void afterMethod(ITestResult result) {
		get_result(result);

	}
	
	
}
