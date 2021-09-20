package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Default_Dropdown_List {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;

	public void sleepInSecound(long timeoutInSecound) {
		try {
			Thread.sleep(timeoutInSecound * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// Function random
	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(9999);
		return number;
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
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_Nopcommerce() {

		driver.get("https://demo.nopcommerce.com");

		String firstName = "Hai";
		String lastName = "Ha";
		String emailAddress = "pika" + randomNumber() + "@gmail.com";
		String day = "1";
		String month = "October";
		String year = "1997";
		String company = "GE";
		String password = "123456";

		By genderFamaleBy = By.id("gender-female");
		By firstNameBy = By.name("FirstName");
		By lastNameBy = By.name("LastName");
		By dayDropdownBy = By.name("DateOfBirthDay");
		By monthDropdownBy = By.name("DateOfBirthMonth");
		By yearDropdownBy = By.name("DateOfBirthYear");
		By emailBy = By.id("Email");
		By companyBy = By.id("Company");

		driver.findElement(By.className("ico-register")).click();
		sleepInSecound(3);
		driver.findElement(genderFamaleBy).click();
		sleepInSecound(3);
		driver.findElement(firstNameBy).sendKeys(firstName);
		sleepInSecound(3);
		driver.findElement(lastNameBy).sendKeys(lastName);

		select = new Select(driver.findElement(dayDropdownBy));
		// Chọn 1 item A
		select.selectByVisibleText(day);

		// Kiểm tra dropdown có phải là mutiple select hay không
		Assert.assertFalse(select.isMultiple());

		// Kiểm tra đã chọn đúng item A chưa
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);

		// Get ra tổng số item trong dropdown là bao nhiêu => Verify giá trị bằng bao
		// nhiêu
		Assert.assertEquals(select.getOptions().size(), 32);

		select = new Select(driver.findElement(monthDropdownBy));
		select.selectByVisibleText(month);

		select = new Select(driver.findElement(yearDropdownBy));
		select.selectByVisibleText(year);

		driver.findElement(emailBy).sendKeys(emailAddress);
		driver.findElement(companyBy).sendKeys(company);

		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),
				"Your registration completed");

		driver.findElement(By.cssSelector("a.ico-account")).click();
		Assert.assertTrue(driver.findElement(genderFamaleBy).isDisplayed());
		Assert.assertEquals(driver.findElement(firstNameBy).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(lastNameBy).getAttribute("value"), lastName);

		select = new Select(driver.findElement(dayDropdownBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);

		select = new Select(driver.findElement(monthDropdownBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);

		select = new Select(driver.findElement(yearDropdownBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);

		Assert.assertEquals(driver.findElement(emailBy).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(companyBy).getAttribute("value"), company);

	}

	@Test
	public void TC_02_Role() {

		driver.get("https://www.rode.com/wheretobuy");

		String country = "Vietnam";

		By countryDropdownBy = By.id("where_country");
		By searchButtonBy = By.id("search_loc_submit");

		select = new Select(driver.findElement(countryDropdownBy));
		select.selectByVisibleText(country);
		Assert.assertFalse(select.isMultiple());
		Assert.assertEquals(select.getFirstSelectedOption().getText(), country);
		driver.findElement(searchButtonBy).click();
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='result_count']/span [text() ='29']")).isDisplayed());

		List<WebElement> storeName = driver
				.findElements(By.xpath("//div[@id='search_results']//div[@class='store_name']"));
		Assert.assertEquals(storeName.size(), 29);

		for (WebElement store : storeName) {
			System.out.println(store.getText());
		}
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}