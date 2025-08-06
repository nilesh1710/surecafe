package com.surecafe.TestBase;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.surecafe.Locations.Locations;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import jxl.read.biff.BiffException;


public class TestBase {

	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	public static WebDriver driver;
	public Properties OR = new Properties();
	
	public static ExtentReports extent;
	public static ExtentTest test;

	public WebDriver getDriver() {

		return driver;
	}

	
	/*...................> Static Block to generate Reports <..........................*/

	static
	{
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat formater= new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent=new ExtentReports(Locations.extentReportPath+formater.format(calendar.getTime())+".html",false, DisplayOrder.NEWEST_FIRST);
		extent
		.addSystemInfo("Host Name", "HP Desktop")
		.addSystemInfo("Role", "Automation Engineer")
		.addSystemInfo("User Name", "Nilesh Shekdar");
		extent.loadConfig(new File(Locations.extentConfigPath));

	}
	/*...............> Method to initialize browser and to get URL of the page <...............*/

	public void openAdminUrl() throws IOException {

		load_data();
		select_browser(OR.getProperty("browser"));
		get_url(OR.getProperty("adminLoginUrl"));

	}

	/*...................> Method to initialize browser and to get URL of the page <..........................*/

	public void init_agency() throws IOException
	{
		load_data();
		String log4jConfPath="log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		select_browser(OR.getProperty("browser"));
		get_url(OR.getProperty("Test_Url_Agency"));
	}
	
	public void openAppUrl() throws IOException {

		load_data();
		select_browser(OR.getProperty("browser"));
		get_url(OR.getProperty("applicationUrl"));

	}
	
	public void openUserEmailUrl() throws IOException {

		load_data();
		select_browser(OR.getProperty("browser"));
		get_url(OR.getProperty("userEmailBoxUrl"));

	}

	/*................> Method to load data from config.properties file <......................*/

	public void load_data() throws IOException {

		File config = new File(
				"C:\\Users\\Admin\\Downloads\\Automation (2)\\Automation\\src\\main\\java\\com\\surecafe\\config\\config.properties");
		FileInputStream f1 = new FileInputStream(config);
		
		OR.load(f1);
	}
	
	public void select_browser(String browser) {
		if (browser.equalsIgnoreCase("firefox")) {

			log.info("Creating object of " + browser);
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Admin\\Downloads\\Automation (2)\\Automation\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		if (browser.equalsIgnoreCase("chrome")) {
			log.info("Creating object of " + browser);
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Admin\\Downloads\\Automation (2)\\Automation\\drivers\\chromedriver.exe");
			
			
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		if (browser.equalsIgnoreCase("ie")) {
			log.info("Creating object of " + browser);
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		}

	}

	public void get_url(String url) {
		log.info("Navigating to URL");
		driver.get(url);
		//driver.manage().window().maximize();
	}

	public void scrollDown() {

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	
	public void scrollToElement(WebElement element) {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
	}
	
	public static void scrollDownToElement(WebElement element) {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}

	public void clickElement(WebElement element) {

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + element.getLocation().y + ")");
		element.click();
	}
	
	/*..................> Normal wait method using Thread.sleep method <.......................*/
	
	public void waitInSeconds(int i) throws InterruptedException{
		
		Thread.sleep(1000*i);
	}

	
	/*...................> Method to CaptureScreenshot <..........................*/

	public String capture_screen(String fileName) 
	{
		if (fileName == "") 
		{
			fileName = "blank";
		}
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + Locations.captureScreenShotPath;
			destFile = new File((String) reportDirectory + fileName + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			// This will help us to link the screen shot in testNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}

	/*...................> Method to close a Browser <..........................*/

	public void closeBrowser() {
		wait_for_page_load(15);
		driver.close();
		log.info("browser closed");
		extent.endTest(test);
		extent.flush();
	}
	
	
	public static void wait_for_page_load(int time)
	{
		driver.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS);
	}

	/*...................> Method for generating extent reports <..........................*/

	public void get_result(ITestResult result) {
		/*if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName() + " test is pass");
			String screen = capture_screen(result.getName());
			test.log(LogStatus.PASS, test.addScreenCapture(screen));
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
			String screen = capture_screen(result.getName());
			test.log(LogStatus.PASS, test.addScreenCapture(screen));
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.ERROR, result.getName() + " test is failed" + result.getThrowable());
			String screen = capture_screen(result.getName());
			test.log(LogStatus.FAIL, test.addScreenCapture(screen));
		} else if (result.getStatus() == ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName() + " test is started");
		}*/
		 if (result.getStatus() == ITestResult.FAILURE) {
	             test.log(LogStatus.FAIL, result.getThrowable());
	            String screen = capture_screen(result.getName());
				test.log(LogStatus.FAIL, test.addScreenCapture(screen));
	        } else if (result.getStatus() == ITestResult.SUCCESS) {
	            test.log(LogStatus.PASS, "Test passed");
	            String screen = capture_screen(result.getName());
				test.log(LogStatus.PASS, test.addScreenCapture(screen));
	        } else if (result.getStatus() == ITestResult.SKIP) {
	            test.log(LogStatus.SKIP, "Test skipped: " + result.getThrowable());
	        }
	        
	        extent.endTest(test);
	        extent.flush();
	        //driver.quit();
	}
	
	public static void wait_for_element_present(WebElement wb)
	{
		WebDriverWait wait=new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOf(wb));
	}
	
	/*...................> Method to initialize browser and to get URL of the page <..........................*/

	public void init() throws IOException
	{
		
		String log4jConfPath="log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		
	}
	
	
	public static void mouseClickByLocator(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
	    executor.executeScript("arguments[0].click();", element);
	}
	
	public static void wait_in_seconds(int i) throws InterruptedException
	{
		Thread.sleep(1000*i);
	}
	
	/*...................> Robot class to upload a File <..........................*/

	public void file_upload(String path) throws InterruptedException, AWTException
	{
		Robot r=new Robot();
		wait_in_seconds(3);
		StringSelection s=new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
		wait_in_seconds(4);
		log.info("Uploading file");
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
		wait_in_seconds(3);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		wait_in_seconds(3);
	}
	
	public static void click_Element_Using_JS(WebElement ele) 
	{
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", ele);

	}

	public static void click_Element_Using_Actions(WebElement ele) 
	{
	//Actions actions = new Actions(driver);
	//actions.moveToElement(ele).click().perform();
	}
	
	
	
	
	public static FileInputStream fis;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	private static XSSFCell cell;
	
	public String[][] getData(String excelName, String sheetName) throws BiffException, IOException
	{
		System.out.println(System.getProperty("user.dir"));
		String excellocation = System.getProperty("user.dir")+"//src//main//java//com//surecafe//Data//"+excelName;
		
		System.out.println(excellocation);
		return readExcel(excellocation, sheetName);
	}
	
	public String[][] readExcel(String excellocation, String sheetName) throws IOException {
		// TODO Auto-generated method stub

		int rownum = get_rowcount(excellocation, sheetName);
		int columncount = get_columncount(excellocation, sheetName, 1);

		String edata[][] = new String[rownum][columncount];
		for (int i = 1; i <= rownum; i++) {

			for (int j = 0; j < columncount; j++) {

				edata[i - 1][j] = get_celldata(excellocation, sheetName, i, j);
				System.out.print(edata[i - 1][j] + " ");
				System.out.print("");

			}

		}
		
		for (String[] row : edata) {
			//System.out.print("The excel sheet value are" + row);
			System.out.println("The excel sheet values are: " + Arrays.toString(row));

		}

		return edata;

	}
	
	
	private int get_columncount(String excellocation, String sheetName, int i) throws IOException {
		fis = new FileInputStream(excellocation);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		int rownum = 0;
		XSSFRow row = sheet.getRow(rownum);
		int columncount = row.getLastCellNum();
		return columncount;
	}

	private int get_rowcount(String excellocation, String sheetName) throws IOException {

		fis = new FileInputStream(excellocation);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();
		return rowcount;

	}

	public static String get_celldata(String filepath, String sheetname, int rownum, int column) throws IOException {

		fis = new FileInputStream(filepath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetname);
		XSSFRow row = sheet.getRow(rownum);
		cell = row.getCell(column);
		String data;

		try {
			DataFormatter formatter = new DataFormatter();
			String celldata = formatter.formatCellValue(cell);

			return celldata;

		} catch (Exception e) {
			data = "";
		}

		return data;

	}
}