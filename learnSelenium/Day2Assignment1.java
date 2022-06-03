package learnSelenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day2Assignment1 {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://leaftaps.com/opentaps/control/main");
		driver.findElement(By.id("username")).sendKeys("DemosalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		
		driver.findElement(By.className("decorativeSubmit")).click();
		System.out.println(driver.getTitle());
		
		driver.findElement(By.partialLinkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Leads")).click();
		driver.findElement(By.linkText("Create Lead")).click();
		
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys("Test company");
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys("Test firstName");
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys("Test lastName");
		driver.findElement(By.id("createLeadForm_firstNameLocal")).sendKeys("Test firstNameLocal");
		driver.findElement(By.id("createLeadForm_departmentName")).sendKeys("Test departmentName");
		driver.findElement(By.id("createLeadForm_description")).sendKeys("Test description");
		driver.findElement(By.id("createLeadForm_primaryEmail")).sendKeys("test@test.com");
		
		List<WebElement> selectClassList = driver.findElements(By.tagName("Select"));
		for(WebElement element: selectClassList) {
			Select select = new Select(element);
			select.selectByIndex(0);
		}
		
		Select select1 = new Select(driver.findElement(By.id("createLeadForm_generalStateProvinceGeoId")));
		List<WebElement> select1Options = select1.getOptions();
		for(WebElement element: select1Options) {
			System.out.println(element.getText());
		}
		
		driver.findElement(By.className("smallSubmit")).click();
		
		driver.findElement(By.linkText("Edit")).click();
		
		driver.findElement(By.id("updateLeadForm_description")).clear();
		driver.findElement(By.id("updateLeadForm_importantNote")).sendKeys("Test importantNote");
		
		driver.findElement(By.className("smallSubmit")).click();
		System.out.println(driver.getTitle());
		driver.close();
		
	}
}
