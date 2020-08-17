package user;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Login {
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
		  driver.get("https://localhost:4200/login");
		  
	}
	
//	@Test(priority = 2)
	public void emptyUsernameTest() {
			
			driver.findElement(By.id("lCustId")).sendKeys("123");	
			driver.findElement(By.id("submit")).click();
			
	}
	
	@Test(priority = 3)
	public void emptyPasswordTest() {
			
			driver.findElement(By.id("lpass")).sendKeys("Dhwani@123");	
			driver.findElement(By.id("submit")).click();
			
	}
	
	@Test(priority = 3)
	public void emptyTest() {
			
			driver.findElement(By.id("submit")).click();
			
	}
	
	@Test(priority = 4)
	public void wrongLoginTest() {
			
			driver.findElement(By.id("lCustId")).sendKeys("678");
			driver.findElement(By.id("lpass")).sendKeys("987654");
			driver.findElement(By.id("submit")).click();
			
			
	}
	
	@Test(priority = 5)
	public void clearFields() {
		driver.findElement(By.id("lCustId")).clear();
		driver.findElement(By.id("lpass")).clear();
	}
	
	
	@Test(priority = 6)
	public void loginTest() {
			
			driver.findElement(By.id("lCustId")).sendKeys("deeksha");
			driver.findElement(By.id("lpass")).sendKeys("Deeksha@123");
			driver.findElement(By.id("submit")).click();
			
			driver.findElement(By.xpath("//*[@id=\"logout\"]/a")).click();
	}
	
	@Test(priority = 7)
	public void notYetRegistered() {
		
		driver.findElement(By.xpath("/html/body/app-root/app-login/div/body/div/div[1]/section[1]/form/div[3]/a")).click();;
		
	}
	
	
	
	
}