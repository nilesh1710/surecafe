
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
import org.openqa.selenium.bidi.module.BrowsingContext;


public class TC01A_Verifyloginbygmail extends TestBase {

	public static final Logger log = Logger.getLogger(TC01A_Verifyloginbygmail.class.getName());
	//@BeforeClass
	//public static void startTest() {

		//test = extent.startTest("TC01A_Verifyloginbygmail");

	//}

	@BeforeMethod
	public void setup(Method result) throws IOException {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
		init();

	}

	@Test(priority = 1, enabled = true)
	public void Signin() throws IOException, InterruptedException, AWTException {

		log.info("<=========== Started Verifying Signin ===========> ");
		openAppUrl();
		ClientLoginPage clp = new ClientLoginPage(driver);
		//clp.clientSignIn(OR.getProperty("emailID"), OR.getProperty("createAccountPassword"));
				//clp.clientSignIn("pdhole@tiuconsulting.com", "surecafe");
		//clp.Googlelogin();
		clp.Googleloginfirefox();
		
		
	}
	@AfterMethod
	public void afterMethod(ITestResult result) {
		get_result(result);

	}
	
	
}
