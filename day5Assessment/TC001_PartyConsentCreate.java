package day5Assessment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC001_PartyConsentCreate {

	/*
	 * TC001: Party Consent-Create
	 ****************************
		1. Login to https://login.salesforce.com
		2. Click on the toggle menu button from the left corner
		3. Click View All and click Party Consent from App Launcher
		4. Click on the Dropdown icon in the Party Consent tab
		5. Click on New Party Consent
		6. Enter Name as 'Salesforce Automation by *Your Name*'
		7.Select the Individuals from the 'Party' field -
		8.Create New Individual
		9.Enter first and last name
		10. Click save and verify the toaster message
		11.Select Business Brand
		12.Create new Business Brand
		13.Enter the Name
		14.Enter Orgid
		15.Click save and verify the Toaster message
		16.Click the consent captured contact point type as Email
		17.Click save and verify Party Consent Name"
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
		//driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();

		driver.findElement(By.xpath("//button[text()='View All']")).click();
		WebElement partyConsentButton =driver.findElement(By.xpath("//p[text()='Party Consent']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", partyConsentButton);
		partyConsentButton.click();

		driver.findElement(By.xpath("//div[text()='New']")).click();

		WebElement nameField = null;
		nameField =	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//label[@class='label inputLabel uiLabel-left form-element__label uiLabel'][1]/../input[1]"))));
		nameField.sendKeys("Salesforce Automation by Irshad");

		driver.findElement(By.xpath("//input[@title='Search Individuals']")).click();
		driver.findElement(By.xpath("//span[@title='New Individual']")).click();

		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Irshad");
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Ali");
		driver.findElement(By.xpath("//button[@class='slds-button slds-button--neutral uiButton--default uiButton--brand uiButton forceActionButton']")).click();

		System.out.println(driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText());

		driver.findElement(By.xpath("//input[@title='Search Business Brands']")).click();
		driver.findElement(By.xpath("//span[@title='New Business Brand']")).click();

		driver.findElement(By.xpath("(//span[text()='Name'])[3]/parent::label/../input")).sendKeys("NewBusinessName");
		driver.findElement(By.xpath("//span[text()='Org Id']/parent::label/../input")).sendKeys("NewOrgId");
		driver.findElement(By.xpath("//div[@class='modal-footer slds-modal__footer']//span[text()='Save']")).click();

		System.out.println(driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText());

		driver.findElement(By.xpath("(//a[text()='--None--'])[1]")).click();
		driver.findElement(By.xpath("//a[@title='Email']")).click();
		driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
	}

}
