package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Command {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {

		// Firefox
		 System.setProperty("webdriver.gecko.driver", projectPath +
		 "/browserDrivers/geckodriver");
		 driver = new FirefoxDriver();

		// Chrome
		//System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		//driver = new ChromeDriver();
		 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
	
	}

	@Test
	public void TC_01_Browser() {
		
		//Mở ra page URL
		driver.get("https://www.messenger.com/");
		
		//Đóng 1 tab đang active
		driver.close();
		
		//Đóng trình duyệt
		driver.quit();
		
		//Lấy ID hiện tại của window/tab đang active 
	    String messageID =driver.getWindowHandle();
		
		//Lấy ra tất cả ID của tất cả các tab/window đang có
		driver.getWindowHandles();
		
		//Switch/Nhảy đến 1 Tab/Window nào đó
		driver.switchTo().window(messageID);
		
		//Tìm ra 1 element với 1 locator
		driver.findElement(By.id(""));
		
		//Tìm tất cả element và locator nào đó
		driver.findElements(By.id(""));
		
		//Trả về URL của page hiện tại
		driver.getCurrentUrl();
		
		//Trả về HTML soucre của page hiện tại
		driver.getPageSource();
		
		//Trả về title của page hiện tại
		driver.getTitle();
		
		//Get/xóa cookies của page
		driver.manage().deleteAllCookies();
		
		//Get log của browser
		driver.manage().logs().getAvailableLogTypes();
		
		//Chờ tìm element
		//1000=1s
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10000,TimeUnit.MILLISECONDS);
		
		//Chờ cho 1 page load thành công
		driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
		
		//Chờ cho 1 script được executor thành công - JavaScriptExecutor
		driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
		
		//Mở browser full màn hình
		driver.manage().window().fullscreen();
		
		//Maxzimine màn hình
		driver.manage().window().maximize();
		
		//Lấy ra vị trí hiện tại của browser
		driver.manage().window().getPosition();
		
		//Set vào cho browser tại vị trí nào đó
		driver.manage().window().setPosition(new Point(0,0));
		
		//Lấy kích thước hiên tại của của browser
		driver.manage().window().getSize();
		//driver.manage().window().getSize(new Dimension(1920,1080));

	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}