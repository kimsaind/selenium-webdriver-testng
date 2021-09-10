package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_Xpath_Css_Login {

	String email;

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	// Get Element
	By myaccountBtn = By.xpath("//div[@class='footer-container']//a[@title='My Account']");
	By emailTxt = By.xpath("//*[@id='email']");
	By pwloginTxt = By.xpath("//*[@id='pass']");
	By emailcreateTxt = By.xpath("//*[@id='email_address']");
	By pwcreateTxt = By.xpath("//*[@id='password']");
	By cofirmpwTxt = By.xpath("//*[@id='confirmation']");
	By loginBtn = By.xpath("//*[@id='send2']");
	By createBtn = By.xpath("//div[@class='col-1 new-users']//a[@title='Create an Account']");
	By firstnameTxt = By.xpath("//*[@id='firstname']");
	By lastnameTxt = By.xpath("//*[@id='lastname']");
	By registerBtn = By.xpath("//div[@class='buttons-set']/button[@title='Register']");
	
	// Error mess
	By errorMessageEmail = By.xpath("//*[@id='advice-required-entry-email']");
	By errorMessagePW = By.xpath("//*[@id='advice-required-entry-pass']");
	By errorMessSecusses = By.xpath("//li[@class='success-msg']");
	By errorHello = By.xpath("//div[@class='welcome-msg']/p[@class='hello']");
	By errorDashboard = By.xpath("//div[@class='dashboard']/div[@class='page-title']");
	By errorLogout = By.linkText("//div[@class='header-language-container']/p[@class='welcome-msg']");

	// Function random
	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(9999);
		return number;
	}

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		// Open Firefox
		driver = new FirefoxDriver();

		// Wait time element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// Open appliction UAT/SAT
		driver.get("http://live.demoguru99.com/");

		// Random email
		email = "pika" + randomNumber() + "@gmail.com";

	}

	// @Test
	public void TC_01_Login_with_empty_Email_and_Passwork() {

		// Click myaccount
		driver.findElement(myaccountBtn).click();

		// Click longin
		driver.findElement(loginBtn).click();

		// Error email
		Assert.assertEquals(driver.findElement(errorMessageEmail).getText(), "This is a required field.");

		// Error pass
		Assert.assertEquals(driver.findElement(errorMessagePW).getText(), "This is a required field.");
	}

	// @Test
	public void TC_02_Login_with_invalid_Email() {

		driver.navigate().refresh();

		// Click myaccount
		driver.findElement(myaccountBtn).click();

		// Input email
		driver.findElement(emailTxt).sendKeys("123434234@12312.123123");

		// Input pass
		driver.findElement(pwloginTxt).sendKeys("123456");

		// Click longin
		driver.findElement(loginBtn).click();

		// Error email
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");

	}

	// @Test
	public void TC_03_Login_with_PW_Less6_characters() {

		driver.navigate().refresh();

		// Click myaccount
		driver.findElement(myaccountBtn).click();

		// Input email
		driver.findElement(emailTxt).sendKeys("pikathui0110@gmail.com");

		// Input pass
		driver.findElement(pwloginTxt).sendKeys("123");

		// Click longin
		driver.findElement(loginBtn).click();

		// Error email
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");

	}

	// @Test
	public void TC_04_Login_with_incorrect_Email_PW() {

		driver.navigate().refresh();

		// Click myaccount
		driver.findElement(myaccountBtn).click();

		// Input email
		driver.findElement(emailTxt).sendKeys("automation@gmail.com");

		// Input pass
		driver.findElement(pwloginTxt).sendKeys("123456");

		// Click longin
		driver.findElement(loginBtn).click();

		// Error email
		Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']/li[@class='error-msg']")).getText(),
				"Invalid login or password.");

	}

	@Test
	public void TC_05_Create_New_Account() {

		driver.navigate().refresh();

		// Click myaccount
		driver.findElement(myaccountBtn).click();

		// Click create
		driver.findElement(createBtn).click();

		// Input fristname
		driver.findElement(firstnameTxt).sendKeys("pika");

		// Input last name
		driver.findElement(lastnameTxt).sendKeys("thui");

		// Input email
		driver.findElement(emailcreateTxt).sendKeys(email);

		// Input PW
		driver.findElement(pwcreateTxt).sendKeys("123456");

		// Input cofirm PW
		driver.findElement(cofirmpwTxt).sendKeys("123456");

		// Click register
		driver.findElement(registerBtn).click();

		// Error Create account Sucessceful
		Assert.assertEquals(driver.findElement(errorMessSecusses).getText(),
				"Thank you for registering with Main Website Store.");
		// Assert.assertTrue(driver.findElement(errorHello).isDisplayed());
		// Assert.assertTrue(driver.findElement(errorDashboard).isDisplayed());

		// Click logout
		WebElement logout = driver.findElement(By.xpath("//a[contains(text(),'Log Out')]"));
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", logout);

		// Assert.assertTrue(driver.findElement(errorLogout).isDisplayed());

	}

	// @Test
	public void TC_06_Login_with_Email_PW() {

		driver.navigate().refresh();

		// Click myaccount
		driver.findElement(myaccountBtn).click();

		// Input email
		driver.findElement(emailTxt).sendKeys("pika8075@gmail.com");

		// Input PW
		driver.findElement(pwloginTxt).sendKeys("123456");

		// Click login
		driver.findElement(loginBtn).click();

		Assert.assertTrue(driver.findElement(errorHello).isDisplayed());

	}

	@AfterClass
	public void afterClass() {

		driver.close();
	}

}
