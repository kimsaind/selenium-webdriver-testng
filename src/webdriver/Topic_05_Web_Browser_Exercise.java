package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {

		// Firefox
		// System.setProperty("webdriver.gecko.driver", projectPath +
		// "/browserDrivers/geckodriver");
		// driver = new FirefoxDriver();

		// Chrome
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Verify_URL() {

		driver.get("http://live.demoguru99.com/");

		// Click my account
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();

		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://live.demoguru99.com/index.php/customer/account/login/");

		// Click create my account
		driver.findElement(By.xpath("//div[@class='col-1 new-users']//a[@title='Create an Account']")).click();

		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl, "http://live.demoguru99.com/index.php/customer/account/create/");

	}

	@Test
	public void TC_02_Verify_Title() {
		// driver.navigate().refresh();
		driver.get("http://live.demoguru99.com/");

		// Click my account
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Customer Login");

		// Click create my account
		driver.findElement(By.xpath("//div[@class='col-1 new-users']//a[@title='Create an Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

	}

	@Test
	public void TC_03_Verify_Navigation() {

		// driver.navigate().refresh();
		driver.get("http://live.demoguru99.com/");

		// Click my account
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();

		// Click create my account
		driver.findElement(By.xpath("//div[@class='col-1 new-users']//a[@title='Create an Account']")).click();

		// Back to My account
		driver.navigate().back();

		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");

		// Forward to Register
		driver.navigate().forward();

		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

	}

	@Test
	public void TC_04_Page_Source() {

		// driver.navigate().refresh();
		driver.get("http://live.demoguru99.com/");

		// Click my account
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));

		// Click create my account
		driver.findElement(By.xpath("//div[@class='col-1 new-users']//a[@title='Create an Account']")).click();
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}