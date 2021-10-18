package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Windows_Tab {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	Actions action;
	WebDriverWait explicitWait;
	Alert alert;

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
		
		//driver.manage().window().maximize();
	
	}

	public void TC_01_Switch_Id() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String parentWindowId = driver.getWindowHandle();
		clickElement(By.xpath("//a[text()='GOOGLE']"));
		sleepInSecond(2);
		
		//Switch Google window and verify title
		switchToWindowByTitle("Google");
		Assert.assertEquals(driver.getTitle(), "Google");
		sleepInSecond(1);
		
		//Switch parent window
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(3);
		
		//Switch Facebook window and verify title
		clickElement(By.xpath("//a[text()='FACEBOOK']"));
		sleepInSecond(2);
		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		sleepInSecond(1);
		
		//Switch to parent window
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(2);
		
		//Switch Tiki window and verify title
		clickElement(By.xpath("//a[text()='TIKI']"));
		sleepInSecond(3);
		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		
		//Close all windows and return to parent window
		closeAllWindowsWithoutParent(parentWindowId);
		sleepInSecond(1);
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
		
		
	}
	
	public void TC_02_Windows_Tab() {
		driver.get("https://kyna.vn/");
		List<WebElement> popupKyna = driver.findElements(By.cssSelector("div.fancybox-inner img"));
		if(popupKyna.size() > 0 && popupKyna.get(0).isDisplayed()) {
			driver.findElement(By.cssSelector(".fancybox-item.fancybox-close")).click();
		}
		sleepInSecond(2);
		scrollToBottomByJs();
		sleepInSecond(1);
		String kynaParentId = driver.getWindowHandle();
		
		//Click icon fb 
		clickElement(By.cssSelector("div.hotline a>img[alt='facebook']"));
		sleepInSecond(1);
		switchToWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");
		sleepInSecond(3);
		
		//Switch to parent id
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia");
		sleepInSecond(1);
		
		//Click icon youtube 
		clickElement(By.cssSelector("div.hotline a>img[alt='youtube']"));
		sleepInSecond(3);
		switchToWindowByTitle("Kyna.vn - YouTube");
		sleepInSecond(1);
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/user/kynavn");
		sleepInSecond(2);
		
		//Close all windows and return to parent window
		closeAllWindowsWithoutParent(kynaParentId);
		sleepInSecond(1);
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia");
		Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/");
		
	}

	@Test
	public void TC_03_Windows_Tab() {
		driver.get("http://live.demoguru99.com/index.php/");
		String homePageWindowId = driver.getWindowHandle();
		String productApple = "IPhone";
		String productSony = "Sony Xperia";
		clickElement(By.xpath("//a[text()='Mobile']"));
		sleepInSecond(1);
		
		//Add to Compare IPhone and Sony XPeria
		clickElement(By.xpath("//a[text()='" + productApple + "']/parent::h2/parent::div[@class='product-info']//a[text()='Add to Compare']"));
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector(".success-msg span")).getText(), "The product " + productApple +" has been added to comparison list.");
		clickElement(By.xpath("//a[text()='"+ productSony + "']/parent::h2/parent::div[@class='product-info']//a[text()='Add to Compare']"));
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector(".success-msg span")).getText(), "The product " + productSony + " has been added to comparison list.");
		sleepInSecond(2);
		
		//Click Compare -> New Window
		clickElement(By.xpath("//span[text()='Compare']/parent::span/parent::button"));
		sleepInSecond(2);
		
		//Switch to Compare window
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		sleepInSecond(1);
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		
		//Close window and return to homepage
		closeAllWindowsWithoutParent(homePageWindowId);
		sleepInSecond(1);
		
		//Clear all verify success message
		clickElement(By.xpath("//a[text()='Clear All']"));
		sleepInSecond(3);
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector(".success-msg span")).getText(), "The comparison list was cleared.");
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
	
	public void switchToWindowByTitle(String expectedTitle) {
		Set<String> allWindowIds =  driver.getWindowHandles();
		for (String windowId : allWindowIds) {
			driver.switchTo().window(windowId);
			sleepInSecond(2);
			String currentWindowTitle = driver.getTitle();
			if (currentWindowTitle.equals(expectedTitle)) {
				break;
			}
		}
	}
	
	public void closeAllWindowsWithoutParent(String parentWindowId) {
		Set<String> allWindowIds = driver.getWindowHandles();
		for (String windowId : allWindowIds) {
			if (!windowId.equals(parentWindowId)) {
				driver.switchTo().window(windowId);
				sleepInSecond(2);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindowId);
	}
	
	public void sendKeysToElement(By by, String key) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(key);
	}

	public void clickElement(By by) {
		driver.findElement(by).click();
	}
	public void scrollToBottomByJs() {
		jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
}