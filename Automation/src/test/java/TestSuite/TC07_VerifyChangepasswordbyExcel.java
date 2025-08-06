package TestSuite;

import java.awt.AWTException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
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

public class TC07_VerifyChangepasswordbyExcel extends TestBase {
	
	public static final Logger log = Logger.getLogger(TC07_VerifyChangepasswordbyExcel.class.getName());
	private static final String By = null;

//	@BeforeClass
	//public static void startTest() {

	//	test = extent.startTest("TC07_VerifyChangepasswordbyExcel");

//	}


	@BeforeMethod
	public void setup(Method result) throws IOException {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
		init();

	}
	
	@Test(priority = 1, enabled = false)
	public void ChangepasswordbyExcel() throws IOException, InterruptedException, AWTException {

		log.info("<===========Started Verifying ChangepasswordbyExcel===========> ");
		openAppUrl();
		ClientLoginPage clp = new ClientLoginPage(driver);
		//clp.clientSignIn("ats51@yopmail.com", "surecafe");
		RegisterPage rp = new RegisterPage(driver);
		clp.Changepassword();
		//clp.YopmailChangepasswordexcel(newpassword,confirmpassword,errormessage);
	}
	
	
	@DataProvider(name = "exceldata") String[][] dataprovidefunc() throws
	  BiffException, IOException {
	  
	  String[][] obj = getData("KredSafe_Login_Data.xlsx", "Sheet6"); 
	  return obj;
	  
	  
	  }
	 
	
	@Test(dataProvider = "exceldata", priority = 2, enabled = true)
	public void verifyLoginFunctionality( String newpassword,String confirmpassword,String errormessage) throws IOException, InterruptedException, AWTException {

		//log.info("<===========Started Verifying ChangepasswordbyExcel===========> ");
		openAppUrl();
		ClientLoginPage clp = new ClientLoginPage(driver);
		
		clp.Changepassword();
		clp.YopmailChangepasswordexcel(newpassword,confirmpassword,errormessage);
		
	}


	@AfterMethod	
	public void afterMethod(ITestResult result) {
		get_result(result);

	}
	
}
