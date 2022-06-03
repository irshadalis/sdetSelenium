package learnSelenium;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day2Assignment2 {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.leafground.com/pages/Link.html");
		
		List<WebElement> linksList1 = driver.findElements(By.tagName("a"));
		linksList1.get(0).click();
		System.out.println(driver.getTitle());
		driver.navigate().back();
		
		List<WebElement> linksList2 = driver.findElements(By.tagName("a"));
		System.out.println(linksList2.size());
		System.out.println(linksList2.get(1).getAttribute("href"));
	}

}
