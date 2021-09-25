package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	public void sleepInSecond(long timeoutInSecound) {
		try {
			Thread.sleep(timeoutInSecound * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void checkToCheckbox(By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}

	public void uncheckToCheckbox(By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}

	public void clickToelementByJS(By by) {
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
	}

	public void clickToCheckboxByJS(By by) {
		if (!driver.findElement(by).isSelected()) {
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
		}

	}

	public void unCheckToCheckboxByJS(By by) {
		if (driver.findElement(by).isSelected()) {
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
		}
	}

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

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_Button() {

		driver.get("https://www.fahasa.com/customer/account/create");

		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

		By loginBy = By.cssSelector("button.fhs-btn-login");

		// Verify button disable
		Assert.assertFalse(driver.findElement(loginBy).isEnabled());

		driver.findElement(By.cssSelector("input#login_username")).sendKeys("0987654321");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
		sleepInSecond(1);
		// Verify button enable
		Assert.assertTrue(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());

		// Verify màu button
		/*
		 * String background =
		 * driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue(
		 * "background-color"); System.out.println("Background:" + background); String
		 * hexaColor = Color.fromString(background).asHex().toUpperCase();
		 * System.out.println("hexaColor:" + hexaColor) Assert.assertEquals(hexaColor,
		 * "#C92127");
		 */

		Assert.assertEquals(Color
				.fromString(driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color"))
				.asHex().toUpperCase(), "#C92127");

		driver.navigate().refresh();

		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

		// Remove disable attribute of button login
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled');", driver.findElement(loginBy));
		sleepInSecond(1);

		driver.findElement(loginBy).click();
		Assert.assertEquals(driver
				.findElement(By.xpath("//div[@class='fhs-input-alert' and text()='Thông tin này không thể để trống']"))
				.getText(), "Thông tin này không thể để trống");
	}

	// @Test
	public void TC_02_Default_Checkbox_Radio() {
        //Radio
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

		By petrolTwo = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		By diesel = By.xpath("//label[text()='1.6 Diesel, 77kW']/preceding-sibling::input");

		// Verify petrolTwo deselected
		Assert.assertFalse(driver.findElement(petrolTwo).isSelected());

		driver.findElement(petrolTwo).click();
		sleepInSecond(1);

		// Verify petrolTwo selected
		Assert.assertTrue(driver.findElement(petrolTwo).isSelected());

		// Bỏ chọn petrolTwo chọn diesel
		driver.findElement(diesel).click();
		sleepInSecond(1);

		// Verify diesel selected
		Assert.assertTrue(driver.findElement(diesel).isSelected());
		
		//Check box
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

		By luggage = By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input");

		// Verify luggage deselected
		Assert.assertFalse(driver.findElement(luggage).isSelected());

		checkToCheckbox(luggage);
		sleepInSecond(1);
		// Verify luggage selected
		Assert.assertTrue(driver.findElement(luggage).isSelected());

		uncheckToCheckbox(luggage);
		sleepInSecond(1);
		Assert.assertFalse(driver.findElement(luggage).isSelected());

	}

	//@Test
	public void TC_03_Custom_Checkbox_Radio() {
		// Radio Custom
		driver.get("https://material.angular.io/components/radio/examples");
		By summerRadioBy = By.xpath("//input[@value='Summer']");
		By winterRadioBy = By.xpath("//input[@value='Winter']");

		// Verify petrolTwo deselected
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value='Winter']")).isSelected());

		clickToelementByJS(winterRadioBy);
		sleepInSecond(2);

		// Verify petrolTwo selected
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Winter']")).isSelected());

		clickToelementByJS(summerRadioBy);
		sleepInSecond(2);

		// Verify petrolTwo selected
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Summer']")).isSelected());

		// Checkbox Custom
		driver.get("https://material.angular.io/components/checkbox/examples");

		By checkedCheckboxBy = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
		By indeterminateCheckboxBy = By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input");
		
		clickToCheckboxByJS(checkedCheckboxBy);
		Assert.assertTrue(driver.findElement(checkedCheckboxBy).isSelected());
		clickToCheckboxByJS(indeterminateCheckboxBy);
		Assert.assertTrue(driver.findElement(indeterminateCheckboxBy).isSelected());
		
		unCheckToCheckboxByJS(indeterminateCheckboxBy);
		sleepInSecond(1);
		Assert.assertFalse(driver.findElement(indeterminateCheckboxBy).isSelected());

	}
	//@Test
	public void TC_04_Custom_Checkbox_Radio_Google_Doc() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		By canthoRadioBy = By.cssSelector("div[aria-label='Cần Thơ']");
		
		Assert.assertEquals(driver.findElement(canthoRadioBy).getAttribute("aria-checked"),"false");
		
		driver.findElement(canthoRadioBy).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());
		
	}


	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}