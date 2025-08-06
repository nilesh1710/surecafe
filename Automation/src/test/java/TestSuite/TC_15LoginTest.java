package TestSuite;

import java.awt.AWTException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.surecafe.TestBase.TestBase;
import com.surecafe.User.ClientLoginPage;
import com.surecafe.User.RegisterPage;

import junit.framework.Assert;

import com.relevantcodes.extentreports.LogStatus;



public class TC_15LoginTest extends TestBase {
    
	public static final Logger log = Logger.getLogger(TC_15LoginTest.class.getName());

	//@BeforeClass
	//public static void startTest() {

		//test = extent.startTest("TC03_verifyProfileDetailsFunctionality");

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
				clp.clientSignIn("ats51@yopmail.com", "surecafe");
				//clp.profileview();
		clp.Logout();
	
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = captureScreenshot(result.getName());
            test.log(LogStatus.FAIL, result.getThrowable());
            test.log(LogStatus.FAIL, "Screenshot below: " + test.addScreenCapture(screenshotPath));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(LogStatus.PASS, "Test passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(LogStatus.SKIP, "Test skipped: " + result.getThrowable());
        }
        
        extent.endTest(test);
        extent.flush();
        driver.quit();
    }

	private String captureScreenshot(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	}
