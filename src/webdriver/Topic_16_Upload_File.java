package webdriver;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Upload_File {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	String uploadFilePath = projectPath + File.separator + "uploadFiles" + File.separator;

	String firstFile = "1.png";
	String secondFile = "2.png";
	String thirdFile = "3.png";
	String firstFilePath = uploadFilePath + firstFile;
	String secondFilePath = uploadFilePath + secondFile;
	String thirdFilePath = uploadFilePath + thirdFile;


	@BeforeClass
	public void beforeClass() {

		// Firefox
		// System.setProperty("webdriver.gecko.driver", projectPath +
		// "/browserDrivers/geckodriver");
		// driver = new FirefoxDriver();

		// Chrome
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

	
	}

	public void TC_01_Sendkey_One_File() {	
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(firstFilePath);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(secondFilePath);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(thirdFilePath);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + firstFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + secondFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + thirdFile + "']")).isDisplayed());

		List<WebElement> startButtonList = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		for (WebElement startButton : startButtonList) {
			startButton.click();
			sleepInSecond(2);
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + firstFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + secondFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + thirdFile + "']")).isDisplayed());

	}
	
	
	public void TC_02_Sendkey_Multiple_File() {	
		By uploadFile = By.xpath("//input[@type='file']");
		driver.findElement(uploadFile).sendKeys(firstFilePath + "\n" + secondFilePath + "\n" + thirdFilePath);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + firstFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + secondFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + thirdFile + "']")).isDisplayed());
		

		List<WebElement> startButtonList = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		for (WebElement startButton : startButtonList) {
			startButton.click();
			sleepInSecond(2);
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + firstFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + secondFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + thirdFile + "']")).isDisplayed());
	}

	@Test
	public void TC_03_uploadFile() {
		driver.get("https://gofile.io/uploadFiles");
		sleepInSecond(1);
		String parentId = driver.getWindowHandle();
		driver.findElement(By.cssSelector("#uploadFile-Input[type='file']")).sendKeys(firstFilePath + "\n" + secondFilePath + "\n" + thirdFilePath);
		sleepInSecond(7);
		
		//Verify download link displayed
		Assert.assertTrue(driver.findElement(By.cssSelector(".card #rowUploadSuccess-downloadPage")).isDisplayed());
		driver.findElement(By.cssSelector(".card #rowUploadSuccess-downloadPage")).click();
		
		//Switch window
		sleepInSecond(2);
		switchToWindowById(parentId);
		sleepInSecond(2);
		
		//Assert: verify image name is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + firstFilePath + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + secondFilePath + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + thirdFilePath + "']")).isDisplayed());
	
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void sleepInSecond(long timeoutInSecound) {
		try {
			Thread.sleep(timeoutInSecound * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void switchToWindowById(String parentId) {
		Set<String> allWindowIds = driver.getWindowHandles();
		for (String windowId : allWindowIds) {
			if (!windowId.equals(parentId)) {
				driver.switchTo().window(windowId);
				break;
			}
		}
	}
}