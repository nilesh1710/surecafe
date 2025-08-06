package com.surecafe.CustomListener;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.surecafe.Locations.Locations;
import com.surecafe.TestBase.TestBase;



	public class Listener extends TestBase implements ITestListener {
		

		public void onFinish(ITestContext result) {
			log.info("<====Test Finished=====>"+((ITestResult) result).getMethod().getMethodName());
		}

		public void onStart(ITestContext arg0) {
			log.info("The test has started");
			
		}

		public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
			// TODO Auto-generated method stub
			
		}

		public void onTestFailure(ITestResult result) {
			 Calendar calendar=Calendar.getInstance();
			 SimpleDateFormat formater= new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			 String methodName=result.getName();
			 if(!result.isSuccess())
			 {
			 File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			 try
			 {
				String reportDirectory=new File(System.getProperty("user.dir")).getAbsolutePath()+Locations.screenshotlocation;
			    File destFile=new File((String)reportDirectory+"//Failure_Screenshots//"+methodName+"_"+formater.format(calendar.getTime())+".png");
			    FileUtils.copyFile(scrFile, destFile);
			    //This will help you to link the screenshot in TestNg Report
			    Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><img src='"+destFile.getAbsolutePath()+"'height='100'width='100'/></a>");
			 }catch(IOException e)
			 {
				 e.printStackTrace();
			 }
			 }
		}

		public void onTestSkipped(ITestResult result) {
			log.info("<====Test Skipped=====>"+result.getMethod().getMethodName());
			
		}

		public void onTestStart(ITestResult result) {
			log.info("<====Test Started Running=====>"+result.getMethod().getMethodName());
			
		}

		public void onTestSuccess(ITestResult result) {
			Calendar calendar=Calendar.getInstance();
			 SimpleDateFormat formater= new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			 String methodName=result.getName();
			 if(result.isSuccess())
			 {
			 File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			 try
			 {
				String reportDirectory=new File(System.getProperty("user.dir")).getAbsolutePath()+Locations.screenshotlocation;
			    File destFile=new File((String)reportDirectory+"//Success_Screenshots//"+methodName+"_"+formater.format(calendar.getTime())+".png");
			    FileUtils.copyFile(scrFile, destFile);
			    //This will help you to link the screenshot in TestNg Report
			    Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><img src='"+destFile.getAbsolutePath()+"'height='100'width='100'/></a>");
			 }catch(IOException e)
			 {
				 e.printStackTrace();
			 }
			 }
			
		}
	}



