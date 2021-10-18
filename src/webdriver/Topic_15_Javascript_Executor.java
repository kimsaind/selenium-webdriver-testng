package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Javascript_Executor {

	String projectPath = System.getProperty("user.dir");

	WebDriver driver;
	JavascriptExecutor jsExecutor;
	Actions action;
	WebDriverWait explicitWait;
	Select select;
	String emailAddress, loginPageUrl, userID, password, name, dob, address, city, state, pin, phone, customerID,
			editAddress, editCity, editState, editPin, editPhone, editEmail;

	By nameTextboxBy = By.name("name");
	By genderTextboxBy = By.name("gender");
	By dobTextboxBy = By.name("dob");
	By addressTextboxBy = By.name("addr");
	By cityTextboxBy = By.name("city");
	By stateTextboxBy = By.name("state");
	By pinTextboxBy = By.name("pinno");
	By phoneTextboxBy = By.name("telephoneno");
	By emailTextboxBy = By.name("emailid");
	By passwordTextboxBy = By.name("password");
	By submitButton = By.name("sub");

	@BeforeClass
	public void beforeClass() {

		// Firefox
		// System.setProperty("webdriver.gecko.driver", projectPath +
		// "/browserDrivers/geckodriver");
		// driver = new FirefoxDriver();

		// Chrome
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 25);
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();

		emailAddress = "pika" + randomNumber() + "@mail.com";
		// Data test for New Customer
		name = "Bookgy Man";
		dob = "1997-01-10";
		address = "123 BAO APA";
		city = "Los Angeles";
		state = "California";
		pin = "324123";
		phone = "0987123121";
		password = "12345678";

	}

	public void TC_01_Live_Guru_99_Javascript_Executor() {

		driver.get("http://live.demoguru99.com/");
		String domainPage = (String) executeForBrowser("return document.domain;");
		String urlPage = (String) executeForBrowser("return document.URL;");

		System.out.println("domainPage:" + domainPage);
		System.out.println("urlPage:" + urlPage);
		Assert.assertEquals(domainPage, "live.techpanda.org");
		Assert.assertEquals(urlPage, "http://live.techpanda.org/");
		sleepInSecond(3);

		// Add Samsung to cart and verify success message
		hightlightElement("//a[text()='Mobile']");
		sleepInSecond(2);
		clickToElementByJS("//a[text()='Mobile']");

		hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		sleepInSecond(2);
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		sleepInSecond(3);

		Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));

		// Customer Service
		sleepInSecond(3);
		hightlightElement("//a[text()='Customer Service']");
		sleepInSecond(1);
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(2);
		String titleCustomerService = (String) executeForBrowser("return document.title;");
		Assert.assertEquals(titleCustomerService, "Customer Service");
		scrollToBottomPage();
		sleepInSecond(3);
		sendkeyToElementByJS("//input[@id='newsletter']", emailAddress);
		clickToElementByJS("//button[@title='Subscribe']");
		sleepInSecond(2);
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));

		// Navigate to demo.guru99.com/v4
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(3);
		String domainHomeGuru = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(domainHomeGuru, "demo.guru99.com");
	}

	public void TC_02_Verify_HMTL5_Validation_Message() {
		driver.get("https://automationfc.github.io/html5/index.html");
		String validationMessage;
		sleepInSecond(1);

		// Click Submit button and verify error message
		clickElement(By.name("submit-btn"));
		validationMessage = getElementValidationMessage("//input[@id='fname']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		sleepInSecond(3);

		// Input firstName and click Submit
		sendKeysToElement(By.xpath("//input[@id='fname']"), "Pika pika");
		clickElement(By.name("submit-btn"));
		validationMessage = getElementValidationMessage("//input[@id='pass']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		sleepInSecond(3);

		// Input firstName, password and click Submit
		sendKeysToElement(By.xpath("//input[@id='fname']"), "Pika pika");
		sendKeysToElement(By.xpath("//input[@id='pass']"), password);
		clickElement(By.name("submit-btn"));
		validationMessage = getElementValidationMessage("//input[@id='em']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		sleepInSecond(3);

		// Input firstName, password, invalid data to email and click Submit
		sendKeysToElement(By.xpath("//input[@id='fname']"), "Pika pika");
		sendKeysToElement(By.xpath("//input[@id='pass']"), password);
		sendKeysToElement(By.xpath("//input[@id='em']"), "31!%");
		clickElement(By.name("submit-btn"));
		sleepInSecond(2);
		validationMessage = getElementValidationMessage("//input[@id='em']");
		Assert.assertEquals(validationMessage, "Please include an '@' in the email address. '31!%' is missing an '@'.");
		sleepInSecond(3);

		// Input firstName, password, invalid data to email and click Submit
		sendKeysToElement(By.xpath("//input[@id='fname']"), "Pika pika");
		sendKeysToElement(By.xpath("//input[@id='pass']"), password);
		sendKeysToElement(By.xpath("//input[@id='em']"), "");
		clickElement(By.name("submit-btn"));
		sleepInSecond(2);
		validationMessage = getElementValidationMessage("//input[@id='em']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		sleepInSecond(3);

		// Input firstName, password, email and click Submit
		sendKeysToElement(By.xpath("//input[@id='fname']"), "Pika pika");
		sendKeysToElement(By.xpath("//input[@id='pass']"), password);
		sendKeysToElement(By.xpath("//input[@id='em']"), emailAddress);
		clickElement(By.name("submit-btn"));
		sleepInSecond(2);
		validationMessage = getElementValidationMessage(
				"//b[contains(text(),'ADDRESS')]/parent::label/following-sibling::select");
		Assert.assertEquals(validationMessage, "Please select an item in the list.");
		sleepInSecond(1);

		/*
		 * var a = $x("//input[@id='em']")[0]; a.validationMessage;
		 */
	}

	public void TC_03_Verify_HMTL5_Validation_Message() {
		//Ubuntu Page
		driver.get("https://login.ubuntu.com/");
		List<WebElement> popupShopee = driver.findElements(By.cssSelector(".p-modal__dialog"));
		String validationMessage;
		sleepInSecond(2);
		if(popupShopee.size() > 0 && popupShopee.get(0).isDisplayed()) {
			System.out.println("Popup is displayed");
			driver.findElement(By.cssSelector("#cookie-policy-button-accept")).click();
		}else {
			System.out.println("Popup is NOT displayed");
		}
		sendKeysToElement(By.xpath("//form[@id='login-form']//input[@id='id_email']"), "a");
		clickElement(By.name("continue"));
		sleepInSecond(1);
		validationMessage = getElementValidationMessage("//div[@class='login-form']//input[@id='id_email' and @name='email']");
		Assert.assertEquals(validationMessage, "Please include an '@' in the email address. 'a' is missing an '@'.");
		
		//Rode Page
		driver.get("https://warranty.rode.com/");
		clickElement(By.xpath("//button[contains(text(),'Register')]"));
		validationMessage = getElementValidationMessage("//input[@id='firstname']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		sleepInSecond(1);
		
		//Pexels Page
		driver.get("https://www.pexels.com/vi-vn/join-contributor/");
		scrollToElement("//button[contains(text(),'Tạo tài khoản mới')]");
		//driver.findElement(By.xpath("//button[contains(text(),'Tạo tài khoản mới')]")).click();
		clickElement(By.xpath("//button[contains(text(),'Tạo tài khoản mới')]"));
		validationMessage = getElementValidationMessage("//input[@id='user_first_name']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		sleepInSecond(3);
	}
	
	public void TC_04_Remove_Attribute() {
		driver.get("http://demo.guru99.com/v4");
		
		sendKeysToElement(By.name("uid"),"mngr357473");
		sendKeysToElement(By.name("password"),"vAgybad");
		clickElement(By.name("btnLogin"));
		sleepInSecond(3);
		
		//Click New Customer
		clickElement(By.xpath("//a[text()='New Customer']"));
		driver.findElement(nameTextboxBy).sendKeys(name);
		
		removeAttributeInDOM("//input[@id='dob']", "type");
		sleepInSecond(1);
		
		driver.findElement(dobTextboxBy).sendKeys(dob);
		sleepInSecond(3);
		driver.findElement(addressTextboxBy).sendKeys(address);
		driver.findElement(cityTextboxBy).sendKeys(city);
		driver.findElement(stateTextboxBy).sendKeys(state);
		driver.findElement(pinTextboxBy).sendKeys(pin);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(submitButton).click();
		
		// Assert
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		Assert.assertEquals(driver.findElement(By.cssSelector(".heading3")).getText(),
				"Customer Registered Successfully!!!");
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				emailAddress);
	}
	@Test
	public void TC_05_Create_An_Account() {
		driver.get("http://live.demoguru99.com/");
		sleepInSecond(3);
		clickToElementByJS("//div[@id='header-account']//a[text()='My Account' and @title='My Account']");
		sleepInSecond(3);
		
		// Click Register link
		clickElement(By.xpath("//a[@title='Create an Account']"));
		sleepInSecond(2);
		
		//Input all mandatory fields
		sendKeysToElement(By.id("firstname"), address);
		sendKeysToElement(By.id("lastname"), address);
		sendKeysToElement(By.id("email_address"), emailAddress);
		sendKeysToElement(By.id("password"), "123456");
		sendKeysToElement(By.id("confirmation"), "123456");
		clickElement(By.cssSelector(".back-link+.button"));
		sleepInSecond(2);
		
		//Assert
		Assert.assertEquals(driver.findElement(By.cssSelector(".success-msg span")).getText(), "Thank you for registering with Main Website Store.");
		
		//Logout
		clickToElementByJS("//a[text()='Log Out']");
		sleepInSecond(3);
		
		//Assert redirect, Guru99 logo is displayed
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".page-title img")));
		Assert.assertTrue(driver.findElement(By.cssSelector(".page-title img")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(9999);
		return number;
	}

	public void sleepInSecond(long timeoutInSecound) {
		try {
			Thread.sleep(timeoutInSecound * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		if (status) {
			return true;
		}
		return false;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public void sendKeysToElement(By by, String key) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(key);
	}

	public void clickElement(By by) {
		driver.findElement(by).click();
	}
	
	public void scrollToElement(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

}