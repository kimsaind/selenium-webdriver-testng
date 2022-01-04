package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_00_Test {

	String projectPath = System.getProperty("user.dir");

	WebDriver driver;
	JavascriptExecutor jsExecutor;
	Actions action;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {

		// Firefox
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();

		// Chrome
		// System.setProperty("webdriver.chrome.driver", projectPath +
		// "/browserDrivers/chromedriver");

		// driver = new ChromeDriver();

		driver.get("https://anotepad.com/");
	}

	// @Test
	public void TC_01_Register() {

		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='registerEmail']")).isDisplayed());

		driver.findElement(By.xpath("//input[@id='registerEmail']")).sendKeys("pika01101@gmail.com");
		driver.findElement(By.xpath("//*[@placeholder='New Password']")).sendKeys("12345678");

		driver.findElement(By.xpath("//button[contains(text(),'Create an Account')]")).click();

	}

	@Test
	public void TC_02_() {
		driver.findElement(By.xpath("//a[contains(text(),'Register')]")).click();

		driver.findElement(By.xpath("//input[@id='loginEmail']")).sendKeys("pika01101@gmail.com");
		driver.findElement(By.xpath("//*[@placeholder='Enter Password']")).sendKeys("12345678");

		driver.findElement(By.xpath("//*[@id='g-recaptcha-response']")).isSelected();
		driver.findElement(By.cssSelector("div.checkbox")).isSelected();

		driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
		Assert.assertEquals(driver.getTitle(), "aNotepad");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}
}