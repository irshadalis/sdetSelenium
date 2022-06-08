package day5Assessment;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RedbusApplication {

	/*
	 * Redbus application
		===============
		Launch the url https://www.redbus.in/
		Enter From -Madiwala Bangalore
		Enter To Koyambedu Chennai
		Select the Date 10-Jun-2022
		Click Search buses
		Click After 6pm under Departure time
		Click Sleeper under Bus types
		Select the Primo
		Get the number of buses found
		Get the Bus fare and sort them in ascending order
		Close the application
	 */
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.redbus.in/");
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//input[@id='src']")).sendKeys("Madiwala");
		driver.findElement(By.xpath("//ul[contains(@class,'autoFill')]/li[1]"));
		
		driver.findElement(By.xpath("//input[@id='dest']")).sendKeys("Koyambedu");
		driver.findElement(By.xpath("//ul[contains(@class,'autoFill')]/li[1]"));
		
		driver.findElement(By.xpath("//input[@id='onward_cal']")).click();
		driver.findElement(By.xpath("//td[text()='11']")).click();
		driver.findElement(By.id("search_btn")).click();
		
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(15));
//		driver.findElement(By.xpath("//div[text()='DEPARTURE TIME']/following-sibling::ul//label[text()='After 6 pm']")).click();
		WebElement depTimeFilter = null;
		depTimeFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='DEPARTURE TIME']/following-sibling::ul//label[text()='After 6 pm']")));
		depTimeFilter.click();

		driver.findElement(By.xpath("//div[text()='BUS TYPES']/following-sibling::ul//label[contains(text(),'SLEEPER')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Primo')]")).click();

		System.out.println(driver.findElement(By.xpath("//span[@class='f-bold busFound']")).getText());
		driver.findElement(By.xpath("//a[text()='Fare']")).click();
		List<WebElement> busfaresWebElements = driver.findElements(By.xpath("//ul[@class='bus-items']//div[@class='seat-fare ']//span"));
		for(WebElement busfaresWebElement: busfaresWebElements) {
			System.out.println(busfaresWebElement.getText());
		}
	}

}
