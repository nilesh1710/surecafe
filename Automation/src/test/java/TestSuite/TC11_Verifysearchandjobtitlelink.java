package TestSuite;



import java.awt.AWTException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.surecafe.TestBase.TestBase;
import com.surecafe.User.ClientLoginPage;

public class TC11_Verifysearchandjobtitlelink extends TestBase{
	public static final Logger log = Logger.getLogger(TC11_Verifysearchandjobtitlelink.class.getName());
	//@BeforeClass
	//public static void startTest() {

	//	test = extent.startTest("TC11_Verifysearchandjobtitlelink");


//}
	@BeforeMethod
	public void setup(Method result) throws IOException {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
		init();

	}

@Test(priority = 1, enabled = true)
public void Joblink() throws IOException, InterruptedException, AWTException {

	log.info("<=========== Started Verifying Joblink ===========> ");
	openAppUrl();
	ClientLoginPage clp = new ClientLoginPage(driver);
	clp.clientSignIn(OR.getProperty("emailIDp"), OR.getProperty("createAccountPassword"));
	clp.searhjobtitlelink();
			//clp.clientSignIn("pdhole@tiuconsulting.com", "surecafe");
	
}
@AfterMethod
public void afterMethod(ITestResult result) {
	get_result(result);

}
}

