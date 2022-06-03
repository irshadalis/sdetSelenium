package learnSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day1 {

	public static void main(String[] args) {
		//System.setProperty("webdriver.chrome.driver", "./driver/firefoxdriver.exe");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/main");
		driver.findElement(By.id("username")).sendKeys("DemosalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		
		driver.findElement(By.className("decorativeSubmit")).click();
		System.out.println(driver.getTitle());
		
		driver.findElement(By.partialLinkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Leads")).click();
		driver.findElement(By.linkText("Create Lead")).click();
		
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys("Test company");
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys("Test firstName");
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys("Test lastName");
		
		//driver.close();
		
	}
}
