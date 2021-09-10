package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Selenium_Locator {

	// Varible Selenium WebDriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		// Open Firefox
		driver = new FirefoxDriver();

		// Wait time element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// Open appliction UAT/SAT
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");

	}

	public void TC_01_FindElement() {
		// Single Element

		WebElement loginButton = driver.findElement(By.className(""));
		loginButton.click();

		// findElement: Search Element
		// By.xxx: vs locator
		// Action to Element: senkeys, getText, clear, click,..

		// Multiple Element: List<WebElenment>
		List<WebElement> buttons = driver.findElements(By.className(""));
		buttons.get(0);

	}

	@Test
	public void TC_02_Id() {

		// Select locator
		driver.findElement(By.id("send2")).click();

		// Verify email erro message
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());

	}

	@Test

	public void TC_03_Class() {

		// driver.navigate().refresh();

		driver.findElement(By.className("validate-password")).sendKeys("abcdefgh");

	}

	@Test
	public void TC_04_Name() {
		driver.navigate().refresh();

		driver.findElement(By.name("send")).click();

		// Verify email erro message
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());

	}

	@Test

	public void TC_05_TagName() {

		driver.navigate().refresh();

		// All link - getText
		List<WebElement> loginPageLikes = driver.findElements(By.tagName("a"));

		for (WebElement webElement : loginPageLikes) {

			System.out.println(webElement.getText());
		}

	}

	@Test
	public void TC_06_LinkText() {

		driver.navigate().refresh();

		driver.findElement(By.linkText("Forgot Your Password?")).click();

		Assert.assertTrue(driver.findElement(By.id("email_address")).isDisplayed());

	}

	@Test
	public void TC_07_PartialLinkTex() {

		driver.navigate().refresh();

		driver.findElement(By.partialLinkText("Back to")).click();

		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
	}

	@Test
	public void TC_08_Css() {

		driver.findElement(By.cssSelector("#email")).sendKeys("pikathui01");
		driver.findElement(By.cssSelector("input[name='login[password]'")).sendKeys("abcdfgh");
	}

	@Test
	public void TC_09_Xpath() {

		driver.navigate().refresh();
		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("kimphung");

	}

	@AfterClass
	public void afterClass() {

		driver.close();
	}

}
