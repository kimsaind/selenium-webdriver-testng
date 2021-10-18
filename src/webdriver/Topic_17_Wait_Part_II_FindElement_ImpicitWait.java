package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Part_II_FindElement_ImpicitWait {
	
	String projectPath = System.getProperty("user.dir");
	
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	Actions action;
	WebDriverWait explicitWait;
	
	By confirmEmailTextbox = By.xpath("//input[@name='reg_email_confirmation__']");
	By startButton = By.cssSelector("#start>button");
	By loadingIcon = By.cssSelector(".example #loading");
	By helloWorldText = By.cssSelector("#finish>h4");
	

	@BeforeClass
	public void beforeClass() {

		// Firefox
		// System.setProperty("webdriver.gecko.driver", projectPath +
		// "/browserDrivers/geckodriver");
		// driver = new FirefoxDriver();

		// Chrome
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 15);
		action = new Actions(driver);
	
	}

	@Test
	public void TC_01_Find_Element() {
		driver.get("https://www.facebook.com/");
		//0 matching node: wait for timeout (implicitlyWait)  -> mark fail test case, throw exception
		//retry find every 0.5s
		driver.findElement(By.name("reg_email_confirmation__"));
		
		//1 matching node:return element (no need to wait end of timeout)
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		driver.findElement(By.name("reg_email_confirmation__"));
		
		// > 1 matching node: return elements, interact with 1st element in matching nodes
		sleepInSecond(2);
		scrollToBottomPage();
		sleepInSecond(2);
		driver.findElement(By.cssSelector("#pageFooter a")).click();
		
	}

	@Test
	public void TC_01_Find_Elements() {
		driver.get("https://www.facebook.com/");
		// 0 matching node: wait for timeout (implicitlyWait) -> proceed next step, not mark fail TC and 
		//throw exception
		// retry find every 0.5s
		driver.findElements(By.name("reg_email_confirmation__"));
		

		// 1 matching node:return element (no need to wait end of timeout)
		driver.findElements(By.xpath("//a[text()='Tạo tài khoản mới']"));
		System.out.println(driver.findElements(By.xpath("//a[text()='Tạo tài khoản mới']")).size());
		

		// > 1 matching node: return elements
		sleepInSecond(2);
		scrollToBottomPage();
		sleepInSecond(2);
		System.out.println(driver.findElements(By.cssSelector("#pageFooter a")).size());
	
	}
	
	@Test
	public void TC_03_Implicit_Wait_Less_Than() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		clickElement(startButton);
		
		Assert.assertEquals(driver.findElement(helloWorldText).getText(), "Hello World!");
	}

	@Test
	public void TC_04_Implicit_Wait_Equal() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		clickElement(startButton);
		
		Assert.assertEquals(driver.findElement(helloWorldText).getText(), "Hello World!");
	}

	@Test
	public void TC_05_Implicit_Wait_Greater_Than() {
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		clickElement(startButton);
		
		Assert.assertEquals(driver.findElement(helloWorldText).getText(), "Hello World!");
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	public void clickElement(By by) {
		driver.findElement(by).click();
	}
	public void sleepInSecond(long timeoutInSecound) {
		try {
			Thread.sleep(timeoutInSecound * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}