package learnSelenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day2Facebook {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://en-gb.facebook.com/");
		
		driver.findElement(By.linkText("Create New Account")).click();
		driver.findElement(By.name("firstname")).sendKeys("firstname");
		driver.findElement(By.name("lastname")).sendKeys("lastname");
		driver.findElement(By.name("reg_email__")).sendKeys("reg_email__@test.com");
		driver.findElement(By.name("reg_email_confirmation__")).sendKeys("reg_email__@test.com");
		driver.findElement(By.name("reg_passwd__")).sendKeys("testpassword");
		
		Select date = new Select(driver.findElement(By.name("birthday_day")));
		date.selectByValue("13");
		Select month = new Select(driver.findElement(By.name("birthday_month")));
		month.selectByValue("5");
		Select year = new Select(driver.findElement(By.name("birthday_year")));
		year.selectByValue("1993");
		
		driver.findElement(By.xpath("//label[text()='Female']")).click();

	}

}
