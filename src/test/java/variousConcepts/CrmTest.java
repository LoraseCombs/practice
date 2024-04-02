package variousConcepts;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CrmTest {
	
	WebDriver driver;
	
	String browser;
	String url;
	String username;
	String password;
	
	
	String dashboardValidationField = ("Dashboard");
	String addcustomerValidationField = ("New Customer");

//	Element List - By type
	By USER_NAME_FIELD =By.xpath("//input[@id='user_name']");
	By PASSWORD_FIELD =By.xpath("//*[@id=\"password\"]");
	By SIGNIN_BUTTON_FIELD =By.xpath("//*[@id=\"login_submit\"]");
	By DASHBOARD_VALIDATION_FIELD = By.xpath("/html/body/div[1]/section/div/div[2]/div/div/header/div/strong");
	By CUSTOMER_MENU_FIELD = By.xpath("/html/body/div[1]/aside[1]/div/nav/ul[2]/li[2]/a/span");
	By ADD_CUSTOMER_MENU_FIELD = By.xpath("//*[@id=\"customers\"]/li[2]/a/span");
	By NEW_CUSTOMER_VALIDATION_FIELD = By.xpath("/html/body/div[1]/section/div/div[2]/div/div[1]/div[1]/div/div/header/div/strong");
	By FULL_NAME_FIELD = By.xpath("//*[@id=\"general_compnay\"]/div[1]/div/input");
	By COMPANY_DROPDOWN_FIELD = By.xpath("//select[@name='company_name']");
	By EMAIL_FIELD = By.xpath("//*[@id=\"general_compnay\"]/div[3]/div/input");
	By PHONE_FIELD = By.xpath("//*[@id=\"phone\"]");
	By ADDRESS_FIELD = By.xpath("//*[@id=\"general_compnay\"]/div[5]/div/input");
	By CITY_FIELD = By.xpath("//*[@id=\"general_compnay\"]/div[6]/div/input");
	By ZIPCODE_FIELD = By.xpath("//*[@id=\"port\"]");
	

	@BeforeClass
	public void readConfig() {
	//InputStream //BufferedReader // FileReader //Scanner
		
		try {
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			browser = prop.getProperty("browser");
			
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@BeforeMethod
	public void init() {

		
		
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "driver1\\chromedriver-win64\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "driver1\\msedgedriver.exe");
			driver = new EdgeDriver();
		}else {
			System.out.println("Please define a valid browser.");
		}
		
		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	
	@Test
	public void testLogin() {
		
	driver.findElement(USER_NAME_FIELD).sendKeys(username);
	driver.findElement(PASSWORD_FIELD).sendKeys(password);
	driver.findElement(SIGNIN_BUTTON_FIELD).click();
	
	Assert.assertEquals(driver.findElement(DASHBOARD_VALIDATION_FIELD).getText(), dashboardValidationField, "Dashboard Page Not Found!!!");
		
	}
	
//	@AfterMethod
//	public void tearDown() {
//		
//		driver.close();
//		driver.quit();
//	}
	
//	@Test
//	public void testAlert() {
//		driver.findElement(SINGIN_BUTTON_FIELD).click();
//		Assert.assertEquals(driver.switchTo().alert().getText(), userAlertValidationText, "Alert is not available!");
//		driver.switchTo().alert().accept();
//		
//		driver.findElement(USER_NAME_FIELD).sendKeys(userName);
//		driver.findElement(SINGIN_BUTTON_FIELD).click();
//		Assert.assertEquals(driver.switchTo().alert().getText(), passwordAlertValidationText, "Alert is not available!");
//		driver.switchTo().alert().accept();
//		
//	}
	
	@Test
	public void testAddCustomer() {
		
		testLogin();
		driver.findElement(CUSTOMER_MENU_FIELD);
		driver.findElement(ADD_CUSTOMER_MENU_FIELD);
		Assert.assertEquals(addcustomerValidationField, driver.findElement(NEW_CUSTOMER_VALIDATION_FIELD), "New customer page not found!!!");
		
		Random rnd = new Random();
		int randomNum = rnd.nextInt(999);
		
		
	}
	
}
