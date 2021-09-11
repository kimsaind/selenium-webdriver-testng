package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Command {
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
		
		//WebBrowser command/ method/ API
		//driver instance/ variable
		
		// WebElement command/ method/ API
		// 1
		driver.findElement(By.id("login")).click();
		
		//2
		WebElement emailTextbox = driver.findElement(By.id("name"));
		emailTextbox.click();
		emailTextbox.sendKeys("abc@gmail.com");
		emailTextbox.isDisplayed();
		
		WebElement element = driver.findElement(By.id(""));
		
		//element xóa dữ liệu trong editable (textbox/ textarea/ dropdown
		element.clear();
		
		//element xóa dữ liệu trong editable (textbox/ textarea/ dropdown
		element.sendKeys("pika");
		element.sendKeys(Keys.ENTER);
		
		//Click vào button/ radio/ checkbox/ image
		element.click();
		
		//Trả về dữ liệu nằm trong attribute của element
		element.getAttribute("placeholder"); 
		
		//Lấy thuộc tính của element: font size/ css/ color/ font style
		element.getCssValue("backgroup-color");
		
		//GUI
		element.getLocation();
		element.getRect();
		element.getSize();
		
		//Take screenshot -> Attach to HTML
		element.getScreenshotAs(OutputType.BASE64);
		element.getScreenshotAs(OutputType.BYTES);
		element.getScreenshotAs(OutputType.FILE);
		
		//Tên thẻ HTML
		//Dùng By.id/ class/ name/ css
		//Đầu ra của step này -> đầu vào của step kia
		
		element = driver.findElement(By.cssSelector("#save-info-button"));
		String saveButtonTagname = element.getTagName();
		
		driver.findElement(By.xpath("//" + saveButtonTagname + "[@name='email']"));
		
		//Lấy text của header/ link/ lable/ erro/ success message
		element.getText();
		
		//Kiểm tra 1 element có hiển thị hay không (hiển thị: người dùng có thể nhìn thấy và thao tác được)
		element.isDisplayed();
		
		//Kiểm tra 1 element có hiển thị hay không (không bị disable/ không phải là readonly field)	
		element.isEnabled();
		
		//Kiểm tra element đã chọn hay chưa (radio/ checkbox/ dropdown)
		element.isSelected();
		
		
		
		
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}