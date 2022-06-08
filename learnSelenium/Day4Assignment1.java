package learnSelenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day4Assignment1 {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver= new ChromeDriver(options);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.myntra.com");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@placeholder='Search for products, brands and more']")).sendKeys("Shirt"+Keys.ENTER);
		driver.findElement(By.xpath("//span[text()='Brand']/following-sibling::div/span")).click();
		driver.findElement(By.xpath("//span[text()='Brand']/following-sibling::div/input")).sendKeys("U.S. Polo");
//		driver.findElement(By.xpath("//ul[@class='brand-list']//input[@value='U.S. Polo Assn.']")).click();
		driver.findElement(By.xpath("//ul[@class='brand-list']//li[1]")).click();

		driver.findElement(By.xpath("//div[@class='sort-sortBy']")).click();
		driver.findElement(By.xpath("//label[@class='sort-label']/input[contains(text(),'What's New')]")).click();
		
		List<WebElement> shirts = driver.findElements(By.xpath("//div[@class='product-price']/span"));
		for(WebElement shirt: shirts)
			System.out.println(shirt.getText());
		
		
	}

}
