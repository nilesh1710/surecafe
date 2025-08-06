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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.surecafe.TestBase.TestBase;
import com.surecafe.User.ClientLoginPage;
import com.surecafe.User.RegisterPage;
import com.relevantcodes.extentreports.LogStatus;

import jxl.read.biff.BiffException;

public class TC08_Verifyloginbyexcel extends TestBase {
	
	public static final Logger log = Logger.getLogger(TC08_Verifyloginbyexcel.class.getName());
	private static final String By = null;

	//@BeforeClass
	//public static void startTest() {

	//	test = extent.startTest("TC08_Verifyloginbyexcel");

	//}

	@BeforeMethod
	public void setup(Method result) throws IOException {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
		init();

	}
	
	@Test(priority = 1, enabled = false)
	public void clientLogin() throws IOException, InterruptedException, AWTException {

		log.info("<===========Started Verifying OTP Functionality===========> ");
		openAppUrl();
		ClientLoginPage clp = new ClientLoginPage(driver);
		clp.clientSignIn(OR.getProperty("emailID11"), OR.getProperty("createAccountPassword11"));
		//clp.clientSignIn("ts1234@yopmail.com", "Nilesh@2030");
		RegisterPage rp = new RegisterPage(driver);
		rp.accessYopmaiforOTP();
		rp.verifyOTPFunctionality(OR.getProperty("OTPSource"), null);
		
	}
	
	
	@DataProvider(name = "exceldata") String[][] dataprovidefunc() throws
	  BiffException, IOException {
	  
	  String[][] obj = getData("KredSafe_Login_Data.xlsx", "Sheet5"); 
	  return obj;
	  
	  }
	 
	
	@Test(dataProvider = "exceldata", priority = 2, enabled = true)
	public void verifyLoginFunctionality(String username, String password,String errormessage) throws IOException, InterruptedException, AWTException {

		log.info("<===========Started Verifying Login Functionality===========> ");
		openAppUrl();
		ClientLoginPage clp = new ClientLoginPage(driver);
		clp.clientSignInn(username, password,errormessage);
		
	}


	@AfterMethod	
	public void afterMethod(ITestResult result) {
		get_result(result);

	}


}
