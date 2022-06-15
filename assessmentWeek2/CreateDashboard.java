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

public class CreateDashboard {

	/*
	 * Create Dashboard
	 ***********************
		1. Login to https://login.salesforce.com
		2. Click on the toggle menu button from the left corner
		3. Click View All and click Dashboards from App Launcher
		4. Click on the New Dashboard option
		5. Enter Name as 'Salesforce Automation by *Your Name* ' and Click on Create.
		6.Click on Save and Verify Dashboard name.
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
		
		driver.findElement(By.xpath("//div[@title='New Dashboard']")).click();
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='dashboard']")));

		driver.findElement(By.xpath("//input[@id='dashboardNameInput']")).sendKeys("Salesforce Automation by Irshad");
		driver.findElement(By.xpath("//button[@id='submitBtn']")).click();
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		System.out.println(driver.findElement(By.xpath("//label[text()='Edit Dashboard name']/following::div[1]//span")).getText());
	}

}
