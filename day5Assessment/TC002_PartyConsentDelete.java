package day5Assessment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC002_PartyConsentDelete {

	/*
	 * TC002:Party Consent-Delete
	 ********************************
		1. Login to https://login.salesforce.com
		2. Click on the toggle menu button from the left corner
		3. Click View All and click Party Consent from App Launcher
		4. Click on the Party Consent tab
		5. Search Name as 'Salesforce Automation by *Your Name*'
		6.Click on the Dropdown icon and Select Delete
		7.Click on the Delete option in the displayed popup window.
		8. Verify Whether Party Consent is Deleted using Party Consent name"
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
		
		driver.findElement(By.xpath("//input[@placeholder='Search this list...']")).sendKeys("Salesforce Automation by Irshad"+Keys.ENTER);
		driver.findElement(By.xpath("//div[@class='forceVirtualActionMarker forceVirtualAction']/a")).click();
		driver.findElement(By.xpath("//a[@title='Delete']")).click();
		driver.findElement(By.xpath("//span[text()='Delete']")).click();
		
		if(driver.findElements(By.xpath("//table[contains(@class,'slds-table')]/tbody/tr")).size()==0) {
			System.out.println(driver.findElement(By.xpath("//div[contains(@class,'emptyContentInner')]//span")).getText());
		}
	}

}
