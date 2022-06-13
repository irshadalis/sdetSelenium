package day8;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment2 {

	/*
	 * Classroom:2:
		==========
		1. Launch URL "http://leaftaps.com/opentaps/control/login"
		2. Enter UserName and Password Using Id Locator
		3. Click on Login Button using Class Locator
		4. Click on CRM/SFA Link
		5. Click on contacts Button
		6. Click on Merge Contacts using Xpath Locator
		7. Click on Widget of From Contact
		8. Click on First Resulting Contact
		9. Click on Widget of To Contact
		10. Click on Second Resulting Contact
		11. Click on Merge button using Xpath Locator
		12. Accept the Alert
		13. Verify the title of the page
	 */
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		
		driver.findElement(By.id("username")).sendKeys("DemosalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.partialLinkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		driver.findElement(By.xpath("//span[text()='From Contact']/../following::td[1]/a")).click();
		
		Set<String> tabs = driver.getWindowHandles();
		Iterator<String> iterator = tabs.iterator();
		String currentTab = iterator.next();
		String newTab = iterator.next();
		driver.switchTo().window(newTab);

		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//button[@class='x-btn-text x-tbar-loading']")).click();
		driver.findElement(By.xpath("(//table[@class='x-grid3-row-table'])[1]//tr[1]/td[1]")).click();
		
		System.out.println(driver.getWindowHandles());
		driver.switchTo().window(driver.getWindowHandles().iterator().next());
		
		driver.findElement(By.xpath("//span[text()='To Contact']/../following::td[1]/a")).click();
		
		tabs = driver.getWindowHandles();
		iterator = tabs.iterator();
		currentTab = iterator.next();
		newTab = iterator.next();
		driver.switchTo().window(newTab);

		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//button[@class='x-btn-text x-tbar-loading']")).click();
		driver.findElement(By.xpath("(//table[@class='x-grid3-row-table'])[2]//tr[1]/td[1]")).click();

		System.out.println(driver.getWindowHandles());
		driver.switchTo().window(driver.getWindowHandles().iterator().next());
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		
		driver.switchTo().alert().accept();
		System.out.println(driver.getTitle());

	}
}
