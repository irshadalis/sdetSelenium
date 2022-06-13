package day8;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment1 {

	/*
	 * Classroom:1
		==========
		1) Go to https://www.nykaa.com/
		2) Click Brands and Search L'Oreal Paris
		3) Click L'Oreal Paris
		4) Check the title contains L'Oreal Paris
		5) Click sort By and select customer top rated
		6) Click Category and click Hair->Click haircare->Shampoo
		7) Click->Concern->Color Protection
		8)check whether the Filter is applied with Shampoo
		9) Click on L'Oreal Paris Colour Protect Shampoo
		10) GO to the new window and select size as 175ml
		11) Print the MRP of the product
		12) Click on ADD to BAG
		13) Go to Shopping Bag
		14) Print the Grand Total amount
		15) Click Proceed
		16) Click on Continue as Guest
		17) Check if this grand total is the same in step 14
		18) Close all windows
	 */
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver= new ChromeDriver(options);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//a[text()='brands']")));
		actions.build().perform();
		
		driver.findElement(By.xpath("//input[@id='brandSearchBox']")).sendKeys("L'Oreal Paris");
		driver.findElement(By.xpath("//a[contains(text(),'Oreal Paris')]")).click();
		
		System.out.println(driver.getTitle());
		
		driver.findElement(By.xpath("//div[@id='filter-sort']")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();

		List<WebElement> filters = driver.findElements(By.xpath("//span[@class='filter-value']"));
		for(WebElement filter: filters)
			System.out.println(filter.getText());
		
		driver.findElement(By.xpath("//div[contains(text(),'Colour Protect Shampoo')]")).click();
		
//		System.out.println(driver.getWindowHandle());
		Set<String> tabs = driver.getWindowHandles();
//		System.out.println(tabs);

		Iterator<String> iterator = tabs.iterator();
		String currentTab = iterator.next();
		String newTab = iterator.next();
		driver.switchTo().window(newTab);
		
		System.out.println(driver.getTitle());
		Select selectSize = new Select(driver.findElement(By.xpath("//select[@title='SIZE']")));
		System.out.println(selectSize.getFirstSelectedOption().getText());
		System.out.println(driver.findElement(By.xpath("//span[text()='MRP:']/following::span")).getText());
		driver.findElement(By.xpath("//span[text()='Add to Bag']")).click();
		driver.findElement(By.xpath("//span[text()='Account']/parent::div/following::div")).click();
		
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='portal-root']//iframe[contains(@src,'mobileCartIframe')]")));
		
		String grandTotal = driver.findElement(By.xpath("//div[@class='payment-tbl-data']//div[@class='value medium-strong']")).getText();
		System.out.println(grandTotal);
		
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
		
		String finalGrandTotal = driver.findElement(By.xpath("//div[text()='Grand Total']/following::div[1]/span")).getText();
		System.out.println(finalGrandTotal);
		
		driver.quit();
		
	}

}
