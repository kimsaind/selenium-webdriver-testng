package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_IFrame_Frame {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
	
	}

	public void TC_01_Iframe() {
		
		driver.get("https://kyna.vn/");
		
		//Switch facebook 
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));
		//Verify facebook page 167k
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(), "167K lượt thích");
		
		// Return parent page
		driver.switchTo().defaultContent();
		
		//Switch chat iframe
		driver.switchTo().frame(driver.findElement(By.cssSelector("#cs_chat_iframe")));
		
		//Click chat iframe
		clickElement(By.cssSelector("div.button_bar"));
		
		//Click button Send
		clickElement(By.cssSelector("input.submit"));
		Assert.assertEquals(driver.findElement(By.cssSelector("input.input_name+div.error_message")).getText(), "Tên của bạn chưa được nhập");
		Assert.assertEquals(driver.findElement(By.cssSelector("select#serviceSelect+div.error_message")).getText(), "Bạn chưa chọn dịch vụ hỗ trợ");
		
		sendKeysToElement(By.xpath("//input[@ng-model='login.username']"), "Pika");
		sendKeysToElement(By.xpath("//input[@ng-model='login.phone']"),"0987123121");
		select = new Select(driver.findElement(By.xpath("//select[@id='serviceSelect']")));
		Assert.assertFalse(select.isMultiple());
		
		select.selectByVisibleText("TƯ VẤN TUYỂN SINH");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "TƯ VẤN TUYỂN SINH");
		sendKeysToElement(By.xpath("//textarea[@name='message']"),"Test nè");
		clickElement(By.cssSelector("input.submit"));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='floater_inner_seriously']/label[@class='logged_in_name ng-binding']")).getText(), "Pika");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='floater_inner_seriously']/label[@class='logged_in_phone ng-binding']")).getText(), "0987123121");
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='message']")).getText(), "Test nè");

		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Excel");
		driver.findElement(By.xpath("//button[@class='search-button']")).click();
		List <WebElement> result = driver.findElements(By.xpath("//ul[@id='w0']//div[@class='content']/h4"));
		
		Assert.assertEquals(result.size(), 10);
		//Verify course name contains "Excel"
		for (WebElement resultExcel : result) {
			System.out.println(resultExcel.getText());
			Assert.assertTrue(resultExcel.getText().contains("Excel"));
			Assert.assertTrue(resultExcel.getText().toLowerCase().contains("excel"));
			Assert.assertTrue(resultExcel.getText().toLowerCase().matches("(.*)excel(.*)"));
		}
		
	}
	@Test
	public void TC_02_() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		sleepInSecond(1);
		
		//Switch frame login
		driver.switchTo().frame("login_page");
		sendKeysToElement(By.cssSelector("input.form-control"), "automation");
		clickElement(By.xpath("//a[text()='CONTINUE']"));
		sleepInSecond(1);
		
		//Verify password textbox is displayed
		Assert.assertTrue(driver.findElement(By.id("fldPasswordDispId")).isDisplayed());
		sleepInSecond(1);
		
		//Click Terms and Conditions
		clickElement(By.xpath("//div[@class='footer-btm']/a[text()='Terms and Conditions']"));
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void scrollTobuttomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	public void sleepInSecond (long timeOutInSecond) {
		try {
			Thread.sleep(timeOutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void sendKeysToElement(By by, String key) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(key);
	}

	public void clickElement(By by) {
		driver.findElement(by).click();
	}


}