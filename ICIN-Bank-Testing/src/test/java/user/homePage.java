package user;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class homePage {
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
	
	@Test(priority = 2)
	public void loginTest() {
			
			driver.findElement(By.id("lCustId")).sendKeys("123");
			driver.findElement(By.id("lpass")).sendKeys("Dhwani@123");
			driver.findElement(By.id("submit")).click();
			
//			driver.findElement(By.xpath("//*[@id=\"logout\"]/a")).click();
	}

}
