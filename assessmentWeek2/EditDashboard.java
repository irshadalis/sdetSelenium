package assessmentWeek2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditDashboard {

	/*
	 * Edit Dashboard:
		===========
		1. Login to https://login.salesforce.com
		2. Click on the toggle menu button from the left corner
		3. Click View All and click Dashboards from App Launcher
		4. Click on the Dashboards tab 
		5. Search the Dashboard 'Salesforce Automation by *Your Name*'
		6. Click on the Dropdown icon and Select Edit
		7.Click on the Edit Dashboard Properties icon
		8. Enter Description as 'SalesForce' and click on save.
		9. Click on Done and  Click on save in the popup window displayed.
		10. Verify the Description.
	 */
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver= new ChromeDriver(options);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://login.salesforce.com");
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Password@123");
		driver.findElement(By.id("Login")).click();
		
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement icon = null;
		icon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='slds-icon-waffle']")));
		icon.click();
		
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		
		WebElement dashboardsButton = driver.findElement(By.xpath("//p[text()='Dashboards']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", dashboardsButton);
		dashboardsButton.click();
		
		driver.findElement(By.xpath("//input[@placeholder='Search recent dashboards...']")).sendKeys("Salesforce Automation by Irshad");
		driver.findElement(By.xpath("//a[@title='Salesforce Automation by Irshad']/ancestor::tr/td[6]//span/div")).click();
		driver.findElement(By.xpath("//div[contains(@class,'slds-dropdown__list')]//span[text()='Edit']")).click();
		
		driver.switchTo().frame(driver.findElement(By.xpath("(//div[@class='dashboardContainer'])[2]/iframe")));

		driver.findElement(By.xpath("//span[text()='Edit Dashboard Properties']/parent::button")).click();
	
		driver.findElement(By.xpath("//input[@id='dashboardNameInput']")).clear();
		driver.findElement(By.xpath("//input[@id='dashboardNameInput']")).sendKeys("Salesforce");
		driver.findElement(By.xpath("//button[@id='submitBtn']")).click();
		driver.findElement(By.xpath("//button[text()='Done']")).click();
		driver.findElement(By.xpath("//footer/button[text()='Save']")).click();

		System.out.println(driver.findElement(By.xpath("//label[text()='Edit Dashboard name']/following::div[1]//span")).getText());
	}

}
