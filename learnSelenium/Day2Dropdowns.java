package learnSelenium;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;

public class Day2Dropdowns {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("http://www.leafground.com/pages/Dropdown.html");
		
		Select indexSelect = new Select(driver.findElement(By.xpath("//select[@id='dropdown1']")));
		indexSelect.selectByIndex(1);
		
		Select textSelect = new Select(driver.findElement(By.xpath("//select[@name='dropdown2']")));
		textSelect.selectByVisibleText("Appium");
		
		Select valueSelect = new Select(driver.findElement(By.xpath("//select[@id='dropdown3']")));
		valueSelect.selectByValue("3");
		
		Select dropdownOptions = new Select(driver.findElement(By.xpath("//select[@class='dropdown']")));
		System.out.println(dropdownOptions.getOptions().size());
		
		driver.findElement(By.xpath("//div[@class='example'][5]/select")).sendKeys("Loadrunner");
		
		Select multipleSelect = new Select(driver.findElement(By.xpath("//div[@class='example'][6]/select")));
		if(multipleSelect.isMultiple()) {
			multipleSelect.selectByIndex(1);
			multipleSelect.selectByValue("2");
		}
	}

}
