package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
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
		//driver.manage().window().maximize();
	
	}

	public void TC_01_Fixed_Popup() {
		
		driver.get("https://ngoaingu24h.vn/");
		
		//Check popup not isDisplayed
		By loginPopup = By.xpath("//div[@id='modal-login-v1']/div");
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		sleepInSecond(2);
		
		//Check popup isDisplayed
		driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.id("account-input")).sendKeys("pika");
		driver.findElement(By.id("password-input")).sendKeys("pika");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='modal-login-v1']//div[@class='row error-login-panel']"))
				.getText(), "Tài khoản không tồn tại!");
		
		driver.findElement(By.xpath("//div[@id='modal-login-v1']//button[@class= 'close' ]")).click();
		sleepInSecond(2);
		
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
	
	}
	
	public void TC_02_Random_Popup_In_Dom() {
		
		driver.get("https://blog.testproject.io/");
		WebElement popup = driver.findElement(By.cssSelector("div.mailch-wrap"));
		

		//Wait for page loaded success
		Assert.assertTrue(isJQueryLoadedSuccess(driver));
		// Nếu có xuất hiện popup
		if (popup.isDisplayed()) {
			System.out.println("Popup is displayed");
			driver.findElement(By.cssSelector("div#close-mailch")).click();
			sleepInSecond(2);
			Assert.assertTrue(popup.isDisplayed());
		} 
		
		// Chuyển sang bước 2: tìm kiếm
		driver.findElement(By.xpath("//aside[@id='secondary']//input[@type='search']")).sendKeys("Selenium");
		sleepInSecond(2);
		driver.findElement(By.xpath("//section[@id='search-2']//span[@class='glass']")).click();
		//Wait for page loaded success
		Assert.assertTrue(isJQueryLoadedSuccess(driver));
		List<WebElement> postTitles = driver.findElements(By.xpath("//h3[@class='post-title']/a"));
		System.out.println(postTitles.size());
		for (WebElement postTitle : postTitles) {
		
			Assert.assertTrue(postTitle.getText().contains("Selenium"));
		}
	}
	@Test
	public void TC_03_Random_Popup_In_Not_Dom() {
		
		driver.get("https://shopee.vn/");
		List<WebElement> popupShopee = driver.findElements(By.cssSelector("#modal img"));
		if(popupShopee.size() > 0 && popupShopee.get(0).isDisplayed()) {
			System.out.println("Popup is displayed");
			driver.findElement(By.cssSelector(".shopee-popup__close-btn")).click();
		}else {
			System.out.println("Popup is NOT displayed");
		}
		sendKeysToElement(By.cssSelector(".shopee-searchbar-input>input"), "Macbook");
		clickElement(By.cssSelector(".shopee-searchbar__main+button"));
		sleepInSecond(2);
		
		
	}

	public void sleepInSecond(long timeoutInSecound) {
		try {
			Thread.sleep(timeoutInSecound * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public boolean isJQueryLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver,60);
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}
	
	public void sendKeysToElement(By by, String key) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(key);
	}

	public void clickElement(By by) {
		driver.findElement(by).click();
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	} 
}