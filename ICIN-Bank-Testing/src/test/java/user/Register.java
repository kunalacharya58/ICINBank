package user;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Register {
WebDriver driver;
	
	@Test(priority = 1)
	public void openWebBrowser() {
		System.out.println("-------Handles REGISTRATION FORM and LOGIN ERRORS-------");
		 
		System.setProperty("webdriver.chrome.driver", "E:\\tools\\chromedriver.exe");
		driver = new ChromeDriver();
		 driver.manage().window().maximize();
		  driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		  driver.manage().deleteAllCookies();
		  driver.get("https://localhost:4200/register");
		  
	}
	
	
	@Test(priority = 2)
	public void emptyUsernameTest() {
		
		driver.findElement(By.id("username")).sendKeys("sam");
		
		driver.findElement(By.id("register")).click();
	
	}
	
//	@Test(priority = 3)
//	public void invalidDobTest() {
//		
//		
//		driver.findElement(By.id("dob")).sendKeys("10-8-2200");
//		driver.findElement(By.id("register")).click();
//	
//	}
	
	@Test(priority = 3)
	public void loginTest() {
		
		driver.findElement(By.id("username")).sendKeys("sam");
		driver.findElement(By.id("firstName")).sendKeys("sam");
		driver.findElement(By.id("lastName")).sendKeys("shetty");
		driver.findElement(By.id("email")).sendKeys("sam@dgh.hgj");
		driver.findElement(By.id("phone")).sendKeys("9999999999");
		driver.findElement(By.id("dob")).sendKeys("10-8-1998");
		driver.findElement(By.id("address")).sendKeys("abcefghi");
		driver.findElement(By.id("password")).sendKeys("Deeksha@123");
		driver.findElement(By.id("confPass")).sendKeys("Deeksha@123");
		
		driver.findElement(By.id("register")).click();
	
	}
	
	
	
	

}
