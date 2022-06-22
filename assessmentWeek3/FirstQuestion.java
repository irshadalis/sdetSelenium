package assessmentWeek3;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstQuestion {

	/*
	 * Test Steps:
		----------------
		1) Launch the app (Sales force)
		2) Click Login
		3) Login with the credentials
		4) Click on Global Actions SVG icon(Use SVG concept)
		5) Click New Task
		6) Enter subject as "Bootcamp "                                          
		7) Select Contact from DropDown
		8) Select status as 'Waiting on someone else'
		9) Save and verify the 'Task created' message
		
		Expected Result:
		New Case should be created successfully
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
		
		driver.findElement(By.xpath("//a[contains(@class,'globalCreateTrigger')]//*[name()='svg']")).click();
		driver.findElement(By.xpath("//span[text()='New Task']")).click();
		driver.findElement(By.xpath("//label[text()='Subject']/following::div[1]//input")).sendKeys("Bootcamp");
		driver.findElement(By.xpath("//input[@title='Search Contacts']")).click();
		driver.findElement(By.xpath("//div[@class='listContent']/ul/li[1]/a")).click();
		driver.findElement(By.xpath("//span[text()='Status']/../..//a[text()='Not Started']")).click();
		driver.findElement(By.xpath("//a[@title='Waiting on someone else']")).click();
		driver.findElement(By.xpath("//div[contains(@class,'bottomBarRight')]//span[text()='Save']")).click();
		
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement toast = null;		
		toast = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class,'toastMessage')]")));
		System.out.println(toast.getText());		
	}

}
