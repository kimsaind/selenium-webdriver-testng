package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_Xpath_Css_Register {

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
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

	}

	@Test
	public void TC_01_Register_with_empty_data() {

		// Click button Register
		driver.findElement(By.xpath("//*[@type='submit']")).click();

		// Verify Name erro message
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='txtFirstname-error']")).isDisplayed());

		// Verify Email erro message
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='txtEmail-error']")).isDisplayed());

		// Verify Cofirm email erro message
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='txtCEmail-error']")).isDisplayed());

		// Verify Passwork erro message
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='txtPassword-error']")).isDisplayed());

		// Verify Passwork Cofirm erro message
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='txtCPassword-error']")).isDisplayed());

		// Verify Phone erro message
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='txtPhone-error']")).isDisplayed());

	}

	@Test
	public void TC_02_Register_with_invalid_Email() {

		driver.navigate().refresh();

		// Input name
		driver.findElement(By.xpath("//*[@id='txtFirstname']")).sendKeys("Pika pika");

		// Input passwork
		driver.findElement(By.xpath("//*[@id='txtPassword']")).sendKeys("01102020");

		// Input cofirm passwork
		driver.findElement(By.xpath("//*[@id='txtCPassword']")).sendKeys("01102020");

		// Input phone
		driver.findElement(By.xpath("//*[@id='txtPhone']")).sendKeys("0987654321");

		// Click button Register
		driver.findElement(By.xpath("//*[@type='submit']")).click();

		// Verify Email erro message
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='txtEmail-error']")).isDisplayed());

		// Verify Cofirm email erro message
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='txtCEmail-error']")).isDisplayed());

	}

	@Test
	public void TC_03_Register_with_incorrect_Cofirm_Email() {

		driver.navigate().refresh();

		// Input name
		driver.findElement(By.xpath("//*[@id='txtFirstname']")).sendKeys("Pika ahihi");

		// Input mail
		driver.findElement(By.xpath("//*[@id='txtEmail']")).sendKeys("pika@gmail.com");

		// Input passwork
		driver.findElement(By.xpath("//*[@id='txtPassword']")).sendKeys("10101010101");

		// Input cofirm passwork
		driver.findElement(By.xpath("//*[@id='txtCPassword']")).sendKeys("10101010101");

		// Input phone
		driver.findElement(By.xpath("//*[@id='txtPhone']")).sendKeys("0987654321");

		// Click button Register
		driver.findElement(By.xpath("//*[@type='submit']")).click();

		// Verify Cofirm email erro message
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='txtCEmail-error']")).isDisplayed());

	}

	@Test
	public void TC_04_Register_with_Passwork_Less6_characters() {

		driver.navigate().refresh();

		// Input name
		driver.findElement(By.xpath("//*[@id='txtFirstname']")).sendKeys("Pika nè");

		// Input mail
		driver.findElement(By.xpath("//*[@id='txtEmail']")).sendKeys("pika@gmail.com.vn");

		// Input mail
		driver.findElement(By.xpath("//*[@id='txtCEmail']")).sendKeys("pika@gmail.com.vn");

		// Input passwork
		driver.findElement(By.xpath("//*[@id='txtPassword']")).sendKeys("1234");

		// Input cofirm passwork
		driver.findElement(By.xpath("//*[@id='txtCPassword']")).sendKeys("1234");

		// Input phone
		driver.findElement(By.xpath("//*[@id='txtPhone']")).sendKeys("0987654321");

		// Click button Register
		driver.findElement(By.xpath("//*[@type='submit']")).click();

		// Verify Passwork erro message
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='txtPassword-error']")).isDisplayed());

		// Verify Passwork Cofirm erro message
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='txtCPassword-error']")).isDisplayed());

	}

	@Test
	public void TC_05_Register_with_incorrect_Cofirm_Passwork() {

		driver.navigate().refresh();

		// Input name
		driver.findElement(By.xpath("//*[@id='txtFirstname']")).sendKeys("Pika ahihi");

		// Input mail
		driver.findElement(By.xpath("//*[@id='txtEmail']")).sendKeys("pika@gmail.vn");

		// Input mail
		driver.findElement(By.xpath("//*[@id='txtCEmail']")).sendKeys("pika@gmail.vn");

		// Input passwork
		driver.findElement(By.xpath("//*[@id='txtPassword']")).sendKeys("1234");

		// Input cofirm passwork
		driver.findElement(By.xpath("//*[@id='txtCPassword']")).sendKeys("12345");

		// Input phone
		driver.findElement(By.xpath("//*[@id='txtPhone']")).sendKeys("0987654321");

		// Click button Register
		driver.findElement(By.xpath("//*[@type='submit']")).click();

		// Verify Passwork Cofirm erro message
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='txtCPassword-error']")).isDisplayed());

	}

	@Test
	public void TC_06_Register_with_invalid_Phone_number() {

		driver.navigate().refresh();

		// Input name
		driver.findElement(By.xpath("//*[@id='txtFirstname']")).sendKeys("Pika ơi");

		// Input mail
		driver.findElement(By.xpath("//*[@id='txtEmail']")).sendKeys("pika@gmail.net");

		// Input mail
		driver.findElement(By.xpath("//*[@id='txtCEmail']")).sendKeys("pika@gmail.net");

		// Input passwork
		driver.findElement(By.xpath("//*[@id='txtPassword']")).sendKeys("123456");

		// Input cofirm passwork
		driver.findElement(By.xpath("//*[@id='txtCPassword']")).sendKeys("123456");

		// Input phone
		driver.findElement(By.xpath("//*[@id='txtPhone']")).sendKeys("09876543213111");

		// Click button Register
		driver.findElement(By.xpath("//*[@type='submit']")).click();

		// Verify Phone erro message
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='txtPhone-error']")).getText(),
				"Số điện thoại phải từ 10-11 số.");

	}

	@AfterClass
	public void afterClass() {

		driver.quit();
	}

}
