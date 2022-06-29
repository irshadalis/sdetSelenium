package assessmentWeek4;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstQuestion {

	/*
		Testcase:1   Create New User
		========================
		Test Steps:
		1) Launch the app https://www.salesforce.com/in/
		2) Click Login
		3) Login with the credentials
		4) Click on the App Launcher Icon left to Setup
		5) Click on View All
		6) Click on User Provisioning Request
		7) Click on the open in SalesForce Classic
		8) Click on Create New View
		9) Enter View Name as Snorky[Your name]
		10) Enter View Unique Name as Snorky_26[yourname_anynumber]
		11) Click on My User Provisioning Requests Under Step two
		12) Under Field Select First DropDown as Name
		13) Under Operator Get the List of values availble from dropdown
		14) Get the size of DropDown
		15) Under Field Select First DropDown as Created Date
		16) Under Step 3 Get the List of Available Fields
		17) Under Step 3 Get the List of Selected Fields
		18)  Select an Option From Available Field  and Click Add Option 
		19) Verify whether Field is added to Selected Fields 
		20) Under Step 4  Click on Visible to All Users 
		21) Click on Save
		22) Verify New User is Created
		23) Get the Title of the Page
		24) Close Other Browsers Than Current Browser
	*/
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver= new ChromeDriver(options);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://login.salesforce.com");
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Password@123");
		driver.findElement(By.id("Login")).click();
		
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement icon = null;
		icon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='slds-icon-waffle']")));
		icon.click();
		
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		
		WebElement userProvisioningRequestButton = driver.findElement(By.xpath("//p[text()='User Provisioning Requests']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", userProvisioningRequestButton);
		userProvisioningRequestButton.click();
		
		driver.findElement(By.linkText("Open in Salesforce Classic.")).click();
		
		Set<String> tabs = driver.getWindowHandles();
		Iterator<String> iterator = tabs.iterator();
		String currentWindow = iterator.next();
		String classicSalesforce = iterator.next();
		driver.switchTo().window(classicSalesforce);
		
		driver.findElement(By.linkText("Create New View")).click();
		driver.findElement(By.id("fname")).sendKeys("Irshad");
		driver.findElement(By.id("devname")).sendKeys("_1041");
		driver.findElement(By.id("fscope1")).click();
		
		Select field = new Select(driver.findElement(By.xpath("//table[@class='declarativeFilterTable']//tr[2]/td[2]/select")));
		field.selectByValue("Name");
		
		Select operator = new Select(driver.findElement(By.xpath("//table[@class='declarativeFilterTable']//tr[2]/td[2]/select")));
		List<WebElement> operatorOptions = operator.getOptions();
		for(WebElement operatorOption: operatorOptions)
			System.out.println(operatorOption.getText());
		System.out.println(operatorOptions.size());
		
		field.selectByValue("CreatedDate");
		
		List<WebElement> availableFields = driver.findElements(By.xpath("//select[@id='colselector_select_0']/option"));
		for(WebElement availableField: availableFields)
			System.out.println(availableField.getText());
		
		List<WebElement> selectedFields = driver.findElements(By.xpath("//select[@id='colselector_select_1']/option"));
		for(WebElement selectedField: selectedFields)
			System.out.println(selectedField.getText());
		
		Select availableField = new Select(driver.findElement(By.xpath("//select[@id='colselector_select_0']")));
		availableField.selectByIndex(0);
		
		driver.findElement(By.xpath("//div[text()='Add']/following::div[1]/a")).click();
		
		List<WebElement> updatedSelectedFields = driver.findElements(By.xpath("//select[@id='colselector_select_1']/option"));
		for(WebElement updatedSelectedField: updatedSelectedFields) {
			if(updatedSelectedField.getText().equals("Created Date")) {
				System.out.println("Element added");
				break;
			}
		}
		
		driver.findElement(By.id("fsharefshareall")).click();
		driver.findElement(By.xpath("//input[@title='Save']")).click();
		
		System.out.println(driver.getTitle());
		driver.switchTo().window(currentWindow).close();
	}

}
