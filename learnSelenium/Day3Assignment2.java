package learnSelenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day3Assignment2 {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.instagram.com/accounts/emailsignup/");
		driver.findElement(By.xpath("//input[@name='emailOrPhone']")).sendKeys("test@mail.com");
		driver.findElement(By.xpath("//input[@name='emailOrPhone']")).clear();
		driver.findElement(By.xpath("//input[@name='emailOrPhone']")).sendKeys("9123412340");
		driver.findElement(By.xpath("//input[@name='fullName']")).sendKeys("Test fullName");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Test username");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test password");

//		driver.close();

	}

}
