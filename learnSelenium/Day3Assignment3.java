package learnSelenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day3Assignment3 {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://www.leafground.com/pages/Edit.html");
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("test@test.com");
		driver.findElement(By.xpath("//input[@value='Append ']")).sendKeys("test Append"+Keys.TAB);
		System.out.println(driver.findElement(By.xpath("//input[@value='TestLeaf']")).getText());
		driver.findElement(By.xpath("//input[@value='Clear me!!']")).clear();
		System.out.println(driver.findElement(By.xpath("//label[contains(text(),'Confirm that edit field is disabled')]/../input")).isEnabled());
	}
}
