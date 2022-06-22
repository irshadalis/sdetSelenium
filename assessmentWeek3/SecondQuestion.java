package assessmentWeek3;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SecondQuestion {

	/*
	 * 1. Login to https://login.salesforce.com
		2. Click on toggle menu button from the left corner (Use  -Actions class)
		3. Click view All from App Launcher
		4. Click on Content tab 
		5. Click View All from Today's Task
		6. Click the Display as dropdown under Recently Viewed and select Table
		7. Click the dropdown of first result (use Actions class)and click Create Follow-Up Event(JS Script)
		8. Select the  Account in Assigned to field
		9. Enter Subject as Meeting
		10. Select the Name of the person in Name field
		11. Select the Product in releted to field
		12. Select due date as next month 5th day (End Date)(calendar concept)
		13. Click Save button
		
		Expected result:
		Event Meeting should be created with the given details.
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
		
		Actions builder = new Actions(driver);
		builder.click(icon).perform();

		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//p[text()='Content']")).click();
		
		WebElement taskCard = driver.findElement(By.xpath("//div[contains(@data-component-id,'todayTaskContainer')]"));
		driver.executeScript("arguments[0].scrollIntoView(true);", taskCard);
		driver.findElement(By.xpath("//div[contains(@data-component-id,'todayTaskContainer')]//span[text()='View All']/..")).click();
		
		driver.findElement(By.xpath("//div[contains(@class,'forceListViewManagerDisplaySwitcher')]//button//*[name()='svg']")).click();
		driver.findElement(By.xpath("//li[@title='Table']/a")).click();
		
		WebElement dropdown = driver.findElement(By.xpath("//table[contains(@class,'uiVirtualDataTable')]/tbody/tr[1]/td[7]//a"));
		builder.click(dropdown).perform();
		
		WebElement followupButton = driver.findElement(By.xpath("//a[@title='Create Follow-Up Event']"));
		driver.executeScript("arguments[0].click();", followupButton);

		WebElement assignedTo = driver.findElement(By.xpath("//div[contains(@class,'actionBody')]//span[text()='Assigned To']/../following::div[1]//li//span[@class='pillText']"));
		System.out.println(assignedTo.getText());
		
		driver.findElement(By.xpath("//div[contains(@class,'actionBody')]//span[text()='Assigned To']/../following::div[1]//li//span[@class='pillText']"));
		driver.findElement(By.xpath("//div[contains(@class,'actionBody')]//label[contains(text(),'Subject')]/following::div[1]//input")).clear();
		driver.findElement(By.xpath("//div[contains(@class,'actionBody')]//label[contains(text(),'Subject')]/following::div[1]//input")).sendKeys("Meeting");
		
		WebElement name = driver.findElement(By.xpath("//div[contains(@class,'actionBody')]//span[text()='Name']/../following::div[1]//li//span[@class='pillText']"));
		System.out.println(name.getText());
		
		driver.findElement(By.xpath("//div[contains(@class,'actionBody')]//span[text()='Related To']/../following::div[1]//input")).click();
		driver.findElement(By.xpath("//div[@class='autocompleteWrapper slds-grow']//ul[@role='presentation']/li[1]")).click();
	
		driver.findElement(By.xpath("//legend[text()='End']/following::div[1]//label[contains(text(),'Date')]/following::div")).click();
		
		driver.findElement(By.xpath("//slot/div[contains(@class,'slds-datepicker')]//button[@title='Next Month']")).click();
		driver.findElement(By.xpath("//slot/table//span[text()='5']")).click();
		driver.findElement(By.xpath("//div[@class='actionsContainer']//span[text()='Save']")).click();
	}

}
