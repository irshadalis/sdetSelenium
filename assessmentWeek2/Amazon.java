package assessmentWeek2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	/*
	 * Amazon:
		======
		1.Load the URL https://www.amazon.in/
		2.search as oneplus 9 pro 
		3.Get the price of the first product
		4. Click the ratings and print the percentage of 5 star customer ratings for the first displayed product
		5. Click the first text link of the first image
		6. Take a screen shot of the product displayed
		7. Click 'Add to Cart' button
		8. Get the cart subtotal
		9. click proceed to pay
		10.Click continue and verify the error message email field 
		11.close the browser
	 */
	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver= new ChromeDriver(options);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro"+Keys.ENTER);
		
		System.out.println(driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText());
		driver.findElement(By.xpath("(//div[@class='a-section'])[1]//i[contains(@class,'a-icon')]")).click();
		
		System.out.println(driver.findElement(By.xpath("//div[@class='a-popover-content']//tr[1]/td[3]//a")).getText());
		driver.findElement(By.xpath("(//h2[contains(@class,'a-size-mini')])[1]/a")).click();
		
		Set<String> tabs = driver.getWindowHandles();
		Iterator<String> iterator = tabs.iterator();
		String currentTab = iterator.next();
		driver.switchTo().window(iterator.next());
		
		File screenshot = driver.findElement(By.id("leftCol")).getScreenshotAs(OutputType.FILE);
		File destination = new File("./snap/product.png");
		FileUtils.copyFile(screenshot, destination);
		
		driver.findElement(By.xpath("//input[@value='Add to Cart']")).click();
		System.out.println(driver.findElement(By.xpath("//span[@id='attach-accessory-cart-subtotal']")).getText());

		driver.findElement(By.xpath("//span[contains(text(),'Proceed to checkout')]/../..")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Continue')]/../..")).click();
		System.out.println(driver.findElement(By.xpath("//div[@id='auth-email-missing-alert']/div/div")).getText());
		
	}

}
