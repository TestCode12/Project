package Browser;
import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class ChromeProfiles 
{
	@Test
	public void chrome()
	{
		//Set driverpath to the location of browser drivers
		//Using chromedriver application which is compatible with current chrome version 108
		
		String driverpath = "...\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverpath);
		System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY,"logs\\chrome.log");
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		
		//If we have multiple profiles in chrome, use following code to initiate a particular profile
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--start-maximized");
		options.addArguments("ignore-certificate-errors");
		options.addArguments("user-data-dir=.....\\Google\\Chrome\\User Data\\Profile 1"); //provide path of your profile
		
		WebDriver driver = new ChromeDriver(options);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		
		driver.get("http://amazon.in");
		
		//Click on the hamburger menu in the top left corner.
		driver.findElement(By.id("nav-hamburger-menu")).click();
		
		//Click on the TV, Appliances and Electronics link under Shop by Department section
		driver.findElement(By.xpath("//div[text()='TV, Appliances, Electronics']")).click();
		
		//click on Televisions under Tv, Audio & Cameras sub section
		driver.findElement(By.linkText("Televisions")).click();
		
		//Checkbox brand Samsung
		String samsungPath= "//span[@class='a-size-base a-color-base' and text()='Samsung']";
		driver.findElement(By.xpath(samsungPath)).click();
		
		//js.executeScript("arguments[0].scrollIntoView(true);",element);
				
		//Sort the Samsung results with price High to Low
		Select s = new Select(driver.findElement(By.id("s-result-sort-select")));
		s.selectByVisibleText("Price: High to Low");		

		//
		String mainId = driver.getWindowHandle();
		driver.findElement(By.xpath("//div[@data-cel-widget='search_result_2']")).click();
		
		//Switch to new tab
		Set<String> winIds= driver.getWindowHandles(); 
		for (String i : winIds) 
		{
			if(!mainId.contentEquals(i)) 
			{
				driver.switchTo().window(i);
			}
		}
		
			
		WebElement e = driver.findElement(By.xpath("//h1[text()=' About this item ']"));
		System.out.println(e.getText());
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(e.getText(),"About this item");
		sa.assertAll();
				
		Reporter.log(driver.findElement(By.xpath("//div[@id='feature-bullets']")).getText());
		
	}	
	/*@org.testng.annotations.AfterMethod
	public void AfterMethod()
	{
		driver.close();
	}*/
	

}

	

