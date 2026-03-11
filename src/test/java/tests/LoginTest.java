package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;







public class LoginTest  {

	public WebDriver driver;



	@BeforeTest
	public void setUp() {

		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test(priority=2)
	public void validLoginTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@name='username']"))
		.sendKeys("Admin");
		driver.findElement(By.xpath("//input[@placeholder='Password']"))
		.sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		String pageTitle=driver.getTitle();
		if(pageTitle.equals("OrangeHRM")) {
			System.out.println("Login Test Passed");
		} else {
			System.out.println("Login Test Failed");
		}
	   
	}
      
	public void login() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@name='username']"))
		.sendKeys("Admin");
		driver.findElement(By.xpath("//input[@placeholder='Password']"))
		.sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		
	}
	@Test(priority=1,enabled=false)
	public void invalidlogin() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@name='username']"))
		.sendKeys("abhi");
		driver.findElement(By.xpath("//input[@placeholder='Password']"))
		.sendKeys("abhi123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		String errorMessage=driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")).getText();
		String expectedErrorMessage="Invalid credentials";
		Assert.assertEquals(errorMessage, expectedErrorMessage);
		System.out.println("Error Message Displayed: " + errorMessage);

		if(errorMessage.equals("Invalid credentials")) {
			System.out.println("Invalid Login Test Passed");
		} 
		else {
			System.out.println("Invalid Login Test Failed");
		}
	}

	@Test(priority=3)
	public void addEmployee() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();

		String addEmployeePage=	driver.findElement(By.xpath("//h6[@class='oxd-text oxd-text--h6 orangehrm-main-title']")).getText();
		if(addEmployeePage.equals("Add Employee")) {
			System.out.println("Add Employee Page is displayed");
		} else {
			System.out.println("Add Employee Page is not displayed");
		}

		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys("Abhishek");
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("Dubey");


		WebElement saveButton=driver.findElement(By.xpath("//button[@type='submit']"));
		if(saveButton.isDisplayed()) {
			saveButton.click();
			System.out.println("Employee added successfully");
		} else {
			System.out.println("Save button is not displayed");}

			WebElement toastMessage=driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-toast-content-text']"));
			if(toastMessage.isDisplayed()) {
				System.out.println("Toast Message Displayed: " + toastMessage.getText());
			} else {
				System.out.println("Toast Message is not displayed");
			}
           WebElement confirmationMessage= driver.findElement(By.xpath("//h6[text()='Personal Details']"));
           if(confirmationMessage.isDisplayed()) {
			   System.out.println("Confirmation Message Displayed: " + confirmationMessage.getText());
		   } else {
			   System.out.println("Confirmation Message is not displayed");
		   }
		}
	

	
	public void logout() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		System.out.println("Logout successful");
	}
	
	@Test(priority=4)
	public void searchEmployee() {
		
		
		
		
	}
	@AfterTest
	public void tearDown() throws InterruptedException {
		
		//logout();
		Thread.sleep(4000);



		driver.quit();
	}

}
