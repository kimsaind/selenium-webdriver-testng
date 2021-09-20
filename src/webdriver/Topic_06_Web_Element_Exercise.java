package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email = "pika@gmail.com";
	String age = "Under 18";
	String education = "HCM";

	By emailTextbox = By.xpath("//input[@id='mail']");
	By ageUnder18Radio = By.cssSelector("#under_18");
	By educationTextarea = By.xpath("//textarea[@id='edu']");

	By user5Text = By.xpath("//h5[contains(text(),'Name: User5')]");

	By passwordTextbox = By.xpath("//form[@method='get']//input[@id='password']");
	By developmentCheckbox = By.xpath("//input[@id='development']");
	By sider1 = By.xpath("//input[@id='slider-1']");
	By sider2 = By.xpath("//input[@id='slider-2']");

	By javaCheckbox = By.xpath("//input[@id='java']");

	// Testcase 4
	By email4Textbox = By.xpath("//input[@id='email']");
	By newusernameTextbox = By.xpath("//input[@id='new_username']");
	By password4Textbox = By.xpath("//input[@id='new_password']");
	By onenuberText = By.xpath("//li[@class='number-char completed']");
	By oneuppercaseText = By.xpath("//li[@class='uppercase-char']");
	By onelowercaseText = By.xpath("//li[@class='lowercase-char completed']");
	By onespecialText = By.xpath("/li[@class='special-char completed']");
	By characters8Text = By.xpath("//li[@class='8-char completed']");
	By loginButton = By.xpath("//button[@id='create-account']");
	By marketingnewsletterCheckbox = By.xpath("//input[@id='marketing_newsletter']");

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
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_Is_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		if (isElementDisplayed(emailTextbox)) {
			senkeytoElement(emailTextbox, email);
		}

		if (isElementDisplayed(ageUnder18Radio)) {
			senkeytoClick(ageUnder18Radio);
		}

		if (isElementDisplayed(educationTextarea)) {
			senkeytoElement(educationTextarea, education);
		}

		Assert.assertFalse(isElementDisplayed(user5Text));
	}

	// Function check isDisplayed
	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element" + by + "is displayed");
			return true;
		} else {
			System.out.println("Element" + by + "is not displayed");
			return false;
		}
	}

	// Function check isEnable
	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element " + by + "is enabled");
			return true;
		} else {
			System.out.println("Element " + by + "is not enabled");
			return false;
		}
	}

	// Function check isSelect
	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element " + by + "is select");
			return true;
		} else {
			System.out.println("Element " + by + "is not select");
			return false;
		}
	}

	// Function senkey to element
	public void senkeytoElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);
	}

	// Function click to element
	public void senkeytoClick(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}

	// @Test
	public void TC_02_Is_Enabled() {

		driver.get("https://automationfc.github.io/basic-form/index.html");

		// enable
		Assert.assertTrue(isElementEnabled(emailTextbox));
		Assert.assertTrue(isElementEnabled(ageUnder18Radio));
		Assert.assertTrue(isElementEnabled(educationTextarea));
		Assert.assertTrue(isElementEnabled(developmentCheckbox));
		Assert.assertTrue(isElementEnabled(sider1));
		Assert.assertTrue(isElementEnabled(passwordTextbox));

		// disable
		Assert.assertFalse(isElementEnabled(sider2));

	}

	// @Test
	public void TC_03_isSelected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		senkeytoClick(ageUnder18Radio);
		senkeytoClick(developmentCheckbox);
		senkeytoClick(javaCheckbox);

		Assert.assertTrue(isElementSelected(ageUnder18Radio));
		Assert.assertTrue(isElementSelected(developmentCheckbox));
		Assert.assertTrue(isElementSelected(javaCheckbox));

		// bỏ chọn
		senkeytoClick(javaCheckbox);
		Assert.assertFalse(isElementSelected(javaCheckbox));
	}

	@Test
	public void TC_04_Register_validate() {
		driver.get("https://login.mailchimp.com/signup/");

		//Input mail & name
		senkeytoElement(email4Textbox, "pika@gmail.com");
		senkeytoElement(newusernameTextbox, "pika");

		// Lower case
		senkeytoElement(password4Textbox, "pikaa");
		Assert.assertTrue(isElementDisplayed(onelowercaseText));
		Assert.assertFalse(isElementEnabled(loginButton));

		// Upper case
		driver.findElement(password4Textbox).clear();
		senkeytoElement(password4Textbox, "Pikaaa");
		Assert.assertFalse(isElementEnabled(loginButton));

		// Number
		driver.findElement(password4Textbox).clear();
		senkeytoElement(password4Textbox, "1aaaaaaaa");
		Assert.assertFalse(isElementEnabled(loginButton));

		// Special
		driver.findElement(password4Textbox).clear();
		senkeytoElement(password4Textbox, "123@aa");
		Assert.assertFalse(isElementEnabled(loginButton));

		// >=8 chars
		driver.findElement(password4Textbox).clear();
		senkeytoElement(password4Textbox, "1234abc");
		Assert.assertFalse(isElementEnabled(loginButton));

		// Full valid data
		driver.findElement(password4Textbox).clear();
		senkeytoElement(password4Textbox, "1234@Abcd");
		Assert.assertTrue(isElementEnabled(loginButton));

		// Checkbox
		senkeytoClick(marketingnewsletterCheckbox);
		Assert.assertTrue(isElementSelected(marketingnewsletterCheckbox));

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}