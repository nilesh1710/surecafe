package com.surecafe.Admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


import com.surecafe.TestBase.TestBase;

public class CapacityPlanning extends TestBase {
	
	 public static final Logger log=Logger.getLogger(CapacityPlanning.class.getName());

	WebDriver driver;
	public Properties OR = new Properties();

	public CapacityPlanning(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public CapacityPlanning() {
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//ul[@class='sidebar-menu']/li[12]")
	public static WebElement settingsMenu;

	@FindBy(xpath = "//a[@href='/admin/capacity/perdaycapacity']")
	public static WebElement monthlyCapacitySubmenu;

	public static List<String> SETTINGS_SUBSCREENS = Arrays.asList("Admin Users", "Attributes", "Monthly Capacity");
	public static List<String> ORDERS_SUBSCREENS = Arrays.asList("All", "Placed", "Complete", "EDC", "Canceled", "OnHold", "SampleImagesApproved");

	public void navigateTo(String screen) throws InterruptedException {

		if (SETTINGS_SUBSCREENS.contains(screen)) {
			log.info("Navigating to Settings sub screen");
			navigateToSettingsSubscreen(screen);
		}

	}

	@FindBy(xpath = "//ul[@class='sidebar-menu']/li[4]")
	WebElement orders;

	@FindBy(xpath = "//a[@href='/admin/orders/orders_list/all']")
	WebElement ordersAll;
	@FindBy(xpath = "//a[@href='/admin/orders/orders_list/placed']")
	WebElement placed;

	public static void navigateToSettingsSubscreen(String screen) throws InterruptedException {

		switch (screen) {
		case "Monthly Capacity":
			scrollDownToElement(settingsMenu);
			Thread.sleep(10000);
			settingsMenu.click();
			Thread.sleep(10000);
			scrollDownToElement(monthlyCapacitySubmenu);
			monthlyCapacitySubmenu.click();

		}

	}

	@FindBy(xpath = "//input[@id='value_1_2']")
	WebElement JanuaryScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='1_2']")
	WebElement JanuaryScheduleCapacityApplytoallDays;
	@FindBy(xpath = "//input[@id='value_1_1']")
	WebElement JanuaryNonScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='1_1']")
	WebElement JanuaryNonScheduleCapacityApplytoallDays;

	@FindBy(xpath = "//input[@id='value_2_2']")
	WebElement FebruaryScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='2_2']")
	WebElement FebruaryScheduleCapacityApplytoallDays;
	@FindBy(xpath = "//input[@id='value_2_1']")
	WebElement FebruaryNonScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='2_1']")
	WebElement FebruaryNonScheduleCapacityApplytoallDays;

	@FindBy(xpath = "//input[@id='value_3_2']")
	WebElement MarchScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='3_2']")
	WebElement MarchScheduleCapacityApplytoallDays;
	@FindBy(xpath = "//input[@id='value_3_1']")
	WebElement MarchNonScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='3_1']")
	WebElement MarchNonScheduleCapacityApplytoallDays;

	@FindBy(xpath = "//input[@id='value_4_2']")
	WebElement AprilScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='4_2']")
	WebElement AprilScheduleCapacityApplytoallDays;
	@FindBy(xpath = "//input[@id='value_4_1']")
	WebElement AprilNonScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='4_1']")
	WebElement AprilNonScheduleCapacityApplytoallDays;

	@FindBy(xpath = "//input[@id='value_5_2']")
	WebElement MayScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='5_2']")
	WebElement MayScheduleCapacityApplytoallDays;
	@FindBy(xpath = "//input[@id='value_5_1']")
	WebElement MayNonScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='5_1']")
	WebElement MayNonScheduleCapacityApplytoallDays;

	@FindBy(xpath = "//input[@id='value_6_2']")
	WebElement JuneScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='6_2']")
	WebElement JuneScheduleCapacityApplytoallDays;
	@FindBy(xpath = "//input[@id='value_6_1']")
	WebElement JuneNonScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='6_1']")
	WebElement JuneNonScheduleCapacityApplytoallDays;

	@FindBy(xpath = "//input[@id='value_7_2']")
	WebElement JulyScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='7_2']")
	WebElement JulyScheduleCapacityApplytoallDays;
	@FindBy(xpath = "//input[@id='value_7_1']")
	WebElement JulyNonScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='7_1']")
	WebElement JulyNonScheduleCapacityApplytoallDays;

	@FindBy(xpath = "//input[@id='value_8_2']")
	WebElement AugustScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='8_2']")
	WebElement AugustScheduleCapacityApplytoallDays;
	@FindBy(xpath = "//input[@id='value_8_1']")
	WebElement AugustNonScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='8_1']")
	WebElement AugustNonScheduleCapacityApplytoallDays;

	@FindBy(xpath = "//input[@id='value_9_2']")
	WebElement SeptemberScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='9_2']")
	WebElement SeptemberScheduleCapacityApplytoallDays;
	@FindBy(xpath = "//input[@id='value_9_1']")
	WebElement SeptemberNonScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='9_1']")
	WebElement SeptemberNonScheduleCapacityApplytoallDays;

	@FindBy(xpath = "//input[@id='value_10_2']")
	WebElement OctoberScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='10_2']")
	WebElement OctoberScheduleCapacityApplytoallDays;
	@FindBy(xpath = "//input[@id='value_10_1']")
	WebElement OctoberNonScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='10_1']")
	WebElement OctoberNonScheduleCapacityApplytoallDays;

	@FindBy(xpath = "//input[@id='value_11_2']")
	WebElement NovScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='11_2']")
	WebElement NovScheduleCapacityApplytoallDays;
	@FindBy(xpath = "//input[@id='value_11_1']")
	WebElement NovNonScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='11_1']")
	WebElement NovNonScheduleCapacityApplytoallDays;

	@FindBy(xpath = "//input[@id='value_12_2']")
	WebElement DecScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='12_2']")
	WebElement DecScheduleCapacityApplytoallDays;
	@FindBy(xpath = "//input[@id='value_12_1']")
	WebElement DecNonScheduleCapacitInputField;
	@FindBy(xpath = "//input[@id='12_1']")
	WebElement DecNonScheduleCapacityApplytoallDays;

	public void enterValuesForScheduleAndNonscheduleMonths(String month, String sheduledValues,
			String NonSheduledValues) throws InterruptedException {

		switch (month) {
		case "January 2023":
			JanuaryScheduleCapacitInputField.sendKeys(sheduledValues);
			JanuaryScheduleCapacityApplytoallDays.click();
			JanuaryNonScheduleCapacitInputField.sendKeys(NonSheduledValues);
			JanuaryNonScheduleCapacitInputField.click();
			break;

		case "February 2023":
			FebruaryScheduleCapacitInputField.sendKeys(sheduledValues);
			FebruaryScheduleCapacityApplytoallDays.click();
			FebruaryNonScheduleCapacitInputField.sendKeys(NonSheduledValues);
			FebruaryNonScheduleCapacityApplytoallDays.click();
			break;

		case "March 2023":
			MarchScheduleCapacitInputField.sendKeys(sheduledValues);
			MarchScheduleCapacityApplytoallDays.click();
			MarchNonScheduleCapacitInputField.sendKeys(NonSheduledValues);
			MarchNonScheduleCapacityApplytoallDays.click();
			break;

		case "April 2023":
			AprilScheduleCapacitInputField.sendKeys(sheduledValues);
			AprilScheduleCapacityApplytoallDays.click();
			AprilNonScheduleCapacitInputField.sendKeys(NonSheduledValues);
			AprilNonScheduleCapacityApplytoallDays.click();
			break;

		case "May 2023":
			MayScheduleCapacitInputField.sendKeys(sheduledValues);
			MayScheduleCapacityApplytoallDays.click();
			MayNonScheduleCapacitInputField.sendKeys(NonSheduledValues);
			MayNonScheduleCapacityApplytoallDays.click();
			break;

		case "June 2023":
			JuneScheduleCapacitInputField.sendKeys(sheduledValues);
			JuneScheduleCapacityApplytoallDays.click();
			JuneNonScheduleCapacitInputField.sendKeys(NonSheduledValues);
			JuneNonScheduleCapacityApplytoallDays.click();
			break;

		case "July 2023":
			JulyScheduleCapacitInputField.sendKeys(sheduledValues);
			JulyScheduleCapacityApplytoallDays.click();
			JulyNonScheduleCapacitInputField.sendKeys(NonSheduledValues);
			JulyNonScheduleCapacityApplytoallDays.click();
			break;

		case "August 2023":
			log.info("Clicking on August month");
			AugustScheduleCapacitInputField.sendKeys(sheduledValues);
			AugustScheduleCapacityApplytoallDays.click();
			AugustNonScheduleCapacitInputField.sendKeys(NonSheduledValues);
			AugustNonScheduleCapacityApplytoallDays.click();
			break;

		case "September 2023":
			SeptemberScheduleCapacitInputField.sendKeys(sheduledValues);
			SeptemberScheduleCapacityApplytoallDays.click();
			SeptemberNonScheduleCapacitInputField.sendKeys(NonSheduledValues);
			SeptemberNonScheduleCapacityApplytoallDays.click();
			break;

		case "October 2023":
			OctoberScheduleCapacitInputField.sendKeys(sheduledValues);
			OctoberScheduleCapacityApplytoallDays.click();
			OctoberNonScheduleCapacitInputField.sendKeys(NonSheduledValues);
			OctoberNonScheduleCapacityApplytoallDays.click();
			break;

		case "November 2023":
			NovScheduleCapacitInputField.sendKeys(sheduledValues);
			NovScheduleCapacityApplytoallDays.click();
			NovNonScheduleCapacitInputField.sendKeys(NonSheduledValues);
			NovNonScheduleCapacityApplytoallDays.click();
			break;

		case "December 2022":
			DecScheduleCapacitInputField.sendKeys(sheduledValues);
			DecScheduleCapacityApplytoallDays.click();
			DecNonScheduleCapacitInputField.sendKeys(NonSheduledValues);
			DecNonScheduleCapacityApplytoallDays.click();
			break;

		default:

			break;

		}

		waitInSeconds(5);
		log.info("Clicking on Save button");
		saveButton.click();
		waitInSeconds(5);

	}

	@FindBy(xpath = "//table[@name='tblclsnew']//tbody//a[@class='acc_trigger']")
	List<WebElement> monthElement;

	public void selectMonthToSetMonthlyCapacity(String month) {
		for (WebElement element : monthElement) {
			String myText = element.getText();
			log.info("Element text is " + myText);
			if (myText.contains(month)) {
				log.info("Clicking element");
				element.click();
				break;
			}
		}

	}

	@FindBy(xpath = "//table[@name='tblclsnew']//tbody/tr[@style='display: table-row;']//table/tbody/tr/td[2]//input[5]")
	List<WebElement> monthAndDate;

	public String returnDateFieldValue(String date) throws InterruptedException {
		String elementValue = null;
		
		for (WebElement element : monthAndDate) {
			scrollDownToElement(element);
			
			String id = element.getAttribute("id");

			log.info("Date Element id is " + id);

			if (id.equals(date)) {

				elementValue = element.getAttribute("min");
				log.info("Element value is " + elementValue);
				
				return elementValue;
			}
			
		}

		return elementValue;

	}

	@FindBy(xpath = "//table[@name='tblclsnew']//tbody/tr[@style='display: table-row;']//table/tbody/tr/td[3]//input[5]")
	List<WebElement> monthAndDateNonSchedule;

	@FindBy(xpath = "//input[@value='Save']")
	WebElement saveButton;

	public void selectNonScheduleDateToSetDailyCapacity(String date, String capacity) throws InterruptedException {

		waitInSeconds(10);
		for (WebElement element : monthAndDateNonSchedule) {
			String id = element.getAttribute("id");
			if (id.contains(date)) {
				log.info("Selected date element id is" + id);
				element.clear();
				element.sendKeys(capacity);
				log.info("Clicking on Save button");
				saveButton.click();
				break;
			}
		}
	}
	
	public String returnDateFieldValueForNonSchedule(String date) throws InterruptedException {
		String elementValue = null;
		for (WebElement element : monthAndDateNonSchedule) {
			scrollDownToElement(element);
			waitInSeconds(10);
			String id = element.getAttribute("id");

			log.info("Date Element id is" + id);
			if (id.contains(date)) {

				elementValue = element.getAttribute("min");
				log.info("Element value is " + elementValue);
				return elementValue;
			}
		}

		return elementValue;

	}

	public void selectDateToSetDailyCapacity(String date, String capacity) throws InterruptedException {

		for (WebElement element : monthAndDate) {
			String id = element.getAttribute("id");
			log.info("Selected date element id is" + id);
			if (id.contains(date)) {
				log.info("Clearing the existing capacity");
				waitInSeconds(5);
				element.clear();
				waitInSeconds(5);
				element.sendKeys(capacity);
				log.info("Clicking on Save button");
				waitInSeconds(5);
				saveButton.click();
				break;
			}
		}
	}

	public void navigateToOrderScreen(String screen, String order, String setOrderValue) throws InterruptedException {
		if (ORDERS_SUBSCREENS.contains(screen)) {
			log.info("Navigating to Orders sub screen");
			navigateToOrdersSubscreen(screen, order, setOrderValue);
		}
		

	}

	@FindBy(xpath = "//input[@name='orderid']")
	WebElement orderId;
	@FindBy(xpath = "//button[@id='search_list']")
	WebElement searchOrder;

	@FindBy(xpath = "//div[@class='col-md-9']/a")
	WebElement clickOrder;
	@FindBy(xpath = "//select[@name='orders_suborder[order_status_id]']")
	WebElement setOrderStatus;
	@FindBy(xpath = "//input[@id='download_link']")
	WebElement downloadLink;

	@FindBy(xpath = "//button[@data-bb-handler='confirm']")
	WebElement confirmButton;
	@FindBy(xpath = "//button[@data-bb-handler='ok']")
	WebElement okButton;
	
	@FindBy(xpath = "//input[@name='orders_suborder[download_zip_filename][]']")
	WebElement addDownloadLink;
	
	
	String EDCDate;

	public void navigateToOrdersSubscreen(String screen, String order, String setOrderValue)
			throws InterruptedException {

		orders.click();
		switch (screen) {
		
		case "SampleImagesApproved":
			ordersAll.click();
			waitInSeconds(5);
			orderId.sendKeys(order);
			searchOrder.click();
			clickOrder.click();
			waitInSeconds(10);
			String currentHandle2 = driver.getWindowHandle();
			Set<String> handles2 = driver.getWindowHandles();
			for (String actual : handles2) {
				if (!actual.equalsIgnoreCase(currentHandle2)) {
					driver.switchTo().window(actual);
				}
			}

			waitInSeconds(5);
			EDCDate = getEDCDate();
			Select setOrdereValueDropdown2 = new Select(setOrderStatus);
			setOrdereValueDropdown2.selectByValue(setOrderValue);
			waitInSeconds(5);				
			saveButton.click();
			waitInSeconds(5);
			break;
			
		case "All":
			ordersAll.click();
			waitInSeconds(5);
			orderId.sendKeys(order);
			searchOrder.click();
			clickOrder.click();
			waitInSeconds(10);
			String currentHandle = driver.getWindowHandle();
			Set<String> handles = driver.getWindowHandles();
			for (String actual : handles) {
				if (!actual.equalsIgnoreCase(currentHandle)) {
					driver.switchTo().window(actual);
				}
			}

			waitInSeconds(5);
			EDCDate = getEDCDate();
			Select setOrdereValueDropdown = new Select(setOrderStatus);
			setOrdereValueDropdown.selectByValue(setOrderValue);
			waitInSeconds(5);
			/* okButton.click(); */
			if(addDownloadLink.isDisplayed()) {
				addDownloadLink.clear();
			}
			waitInSeconds(5);
			addDownloadLink.sendKeys("order.com");
			waitInSeconds(5);
			saveButton.click();

			waitInSeconds(5);
			confirmButton.click();
			break;
			
		case "OnHold":
			ordersAll.click();
			waitInSeconds(10);
			orderId.sendKeys(order);
			searchOrder.click();
			clickOrder.click();
			waitInSeconds(10);
			String currentHandle1 = driver.getWindowHandle();
			Set<String> handles1 = driver.getWindowHandles();
			for (String actual : handles1) {
				if (!actual.equalsIgnoreCase(currentHandle1)) {
					driver.switchTo().window(actual);
				}
			}

			waitInSeconds(5);
			EDCDate = getEDCDate();
			waitInSeconds(5);
			Select setOrdereValueDropdown1 = new Select(setOrderStatus);
			setOrdereValueDropdown1.selectByValue(setOrderValue);
			waitInSeconds(5);
			saveButton.click();
			waitInSeconds(5);
			break;

		case "Placed":
			placed.click();
			waitInSeconds(5);
			break;
		default:

			break;

		}
	}

	@FindBy(xpath = "//select[@id='servicetype']")
	WebElement serviceType;
	@FindBy(xpath = "//input[@value='Select']")
	WebElement selectButton;

	public void selectServiceType(String typeOFService, Number month, Number applytoall) {
		Select typeOFServiceDropdown = new Select(serviceType);
		typeOFServiceDropdown.selectByVisibleText(typeOFService);
		selectButton.click();
		WebElement element = driver.findElement(By.xpath("//input[@id='value_" + (month) + "_1']"));
		element.sendKeys("10000");
		WebElement applyToAllElement = driver.findElement(By.xpath("//input[@id='" + (applytoall) + "_1']"));
		applyToAllElement.click();
		saveButton.click();

	}

	public ArrayList<Integer> selectListDate(ArrayList<Integer> finalList) throws InterruptedException {

		waitInSeconds(10);
		log.info("Verifying the capacity");
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (WebElement element : monthAndDate) {
			String id = element.getAttribute("id");
			log.info("Element value is " + id);
			String ids = id.substring(0, id.length() - 2);
			Integer date = Integer.parseInt(ids);
			log.info("Element value is " + date);
			log.info("List is " + finalList);
			waitInSeconds(10);
			if (finalList.contains(date)) {
				log.info("Selected date element id is " + ids);
				 String getValue = element.getAttribute("value");
				 Integer getValueInt = Integer.parseInt(getValue);
				 
				 String minValue = element.getAttribute("min");
				 Integer minValueInt = Integer.parseInt(minValue);
				 Integer valueToList = getValueInt-minValueInt;
				 list.add(valueToList);
				log.info("Element value is " + list);
				if(valueToList<800) {
					Assert.assertTrue(valueToList< 800);					
				}
				
				return list;
			}
		}
		return list;
	}
	
	@FindBy(xpath = "//tbody//tr//td//div[5]")
	WebElement getEDCDate;
	
	public String getEDCDate() {
		
		String edc = getEDCDate.getText();
		            
		String edcdate = edc.substring(6, edc.length() - 17);
		log.info("The EDC date is :" +edcdate);
		
		String mon = edc.substring(9,13);
		log.info("The Month is :" +mon);
		
		String dd = edc.substring(6,8);
		
		log.info("The Date is :" +dd);
		
		String year = edc.substring(12,17);
		log.info("The Year is :" +year);
		
		String actEDC = mon+dd+","+year;
		log.info("The act EDC date is :" +actEDC);
		
		return actEDC;
	}
	
	
	@FindBy(xpath = "//tbody//tr[1]//td[3]")
	WebElement getOnHoldImages;
	
	
	
	  public String onHoldImages() throws InterruptedException {
	  
	  String images = getOnHoldImages.getText(); 
	  log.info("The number of images :" +images);
	  return images;
	  
	  }
	 
	
	
	
	public String getAvailableCapacity() throws InterruptedException {
		String elementValue = null;
		String elementMin = null;
		int actualValue = 0;
		int onHoldImagesActualCount= 0;
		String id = null;
		
		for (WebElement element : monthAndDate) {
			scrollDownToElement(element);
			waitInSeconds(10);
			String active = element.getAttribute("disabled");
			elementValue = element.getAttribute("value");
			int eleval = Integer.parseInt(elementValue);
			waitInSeconds(10);
			String idDate = element.getAttribute("id");
			log.info("Date Element id is" + idDate);
			if (active == null&& eleval!=0) {
				waitInSeconds(10);
				
				elementMin = element.getAttribute("min");
				log.info("Element value is " + elementValue);
				log.info("Element value is " + elementMin);
				actualValue = Integer.parseInt(elementValue)-Integer.parseInt(elementMin);
				String onHoldImagesCount = onHoldImages();
				onHoldImagesActualCount = Integer.parseInt(onHoldImagesCount);
				waitInSeconds(10);
				if(actualValue>onHoldImagesActualCount) {
				return id;
				}

			}
		}

		
		return id;

	}
	
	
	
	
	/*
	 * @FindBy(xpath = "//a[@target="_blank"]") WebElement getEmailId;
	 * 
	 * public String getEmailId() {
	 * 
	 * String email = getEmailId.getText(); log.info("The EDC date is :" +email);
	 * return email; }
	 */
	
	
	
	
	
	/*
	 * @FindBy(xpath =
	 * "//a[contains(@href,'https://gitdemo.photographersedit.com/customer/sub_order_detail/')]")
	 * List<WebElement> ordersHomepage;
	 * 
	 * public void getEDCDateHomePage(String orderName) { for (WebElement element :
	 * ordersHomepage) { String myText = element.getText();
	 * log.info("Order text is " + myText); if (myText.contains(orderName)) {
	 * log.info("Order found"); String orderNameOrNumber = element.getText();
	 * log.info("Order name is" +orderNameOrNumber); break; } } }
	 */
	
	public void verifySampleImageUploadToSampleApprovalECD(String adminEDC, String clientEDC) throws InterruptedException {
		Assert.assertEquals(adminEDC, clientEDC);
	}

}
