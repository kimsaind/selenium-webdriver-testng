package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Part_I_Element_Status {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Actions action;

	String projectPath = System.getProperty("user.dir");
	By confirmEmailTextbox = By.xpath("//input[@name='reg_email_confirmation__']");

	@BeforeClass
	public void beforeClass() {

		// Firefox
		// System.setProperty("webdriver.gecko.driver", projectPath +
		// "/browserDrivers/geckodriver");
		// driver = new FirefoxDriver();

		// Chrome
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();

		explicitWait = new WebDriverWait(driver, 15);

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// driver.manage().window().maximize();

		driver.get("https://www.facebook.com/");

	}

	public void TC_01_Visible() { // Có hiển thị trên UI.
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		driver.findElement(By.name("reg_email__")).sendKeys("auto@gmail.com");
		// Hiển thị trên UI, có trong DOM
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(confirmEmailTextbox));
	}
	
	public void TC_02_Invisible() { // Không hiển thị trên UI, có trong DOM hay không không quan trọng
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();

		// Element không có trên UI, nhưng có trong DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(confirmEmailTextbox));

		// Element không có trên UI, không có trên DOM
		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(2);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(confirmEmailTextbox));
	}
	
	public void TC_03_Presence() { // Chỉ cần có trong DOM, có hay không trên UI không quan trọng
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		// Wait presence (in DOM - k0 co tren UI)
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(confirmEmailTextbox));

		// Wait presence (in DOM - co tren UI)
		driver.findElement(By.name("reg_email__")).sendKeys("auto@gmail.com");
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(confirmEmailTextbox));
	}

	@Test
	public void TC_04_Staleness() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		driver.findElement(By.id("SubmitCreate")).click();
		
		//1
		WebElement accError = driver.findElement(By.xpath("//div[@id='create_account_error']"));
		
		//2. Element is to updated (no longer attached to DOM)
		driver.navigate().refresh();
		
		//3. Wait for emailTextbox is staleness (wait for element not in old state)
		explicitWait.until(ExpectedConditions.stalenessOf(accError));
		
		//StaleElementException: element đã bị thay đổi trạng thái nhưng vẫn lấy ra để thao tác

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
}