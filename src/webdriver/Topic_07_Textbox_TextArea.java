package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email, userID, password, loginPageUrl, name, gender, dayOfBirthInput, dayOfBirthOutput, addressInput, addressOutput, city, state, pin, phone, customerID;
	String editAddressInput, editAddressOutput, editCity, editState, editPin, editPhone, editEmail;
	
	By nameTextboxBy = By.name("name");
	By genderRadioBy = By.xpath("//input[@value='f']"); 
	By genderTextboxBy = By.name("gender"); 
	By dayOfBirthTextboxBy = By.name("dob");
	By addressTextArea = By.name("addr");
	By cityTextboxBy = By.name("city");
	By stateTextboxBy = By.name("state");
	By pinTextboxBy = By.name("pinno");
	By phoneTextboxBy = By.name("telephoneno");
	By emailTextboxBy = By.name("emailid");
	By passwordTextboxBy = By.name("password");
	

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
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// driver.manage().window().maximize();

		driver.get("http://demo.guru99.com/v4/");
	    // Data TC 1 2 3
		name = "Pika Pika";
		gender = "female";
		dayOfBirthInput = "01/01/1997";
		dayOfBirthOutput ="1997-01-01";
		addressInput = "123 PO Bridge\nNew York";
		addressOutput ="123 PO Bridge New York";
		city = "Los Angeles";
		state = "California";
		pin = "112233";
		phone = "0987654321";
		email = "pika" + randomNumber() + "@gmail.com";
		
		// Data TC 4
		editAddressInput = "345 PO Bridge\nCalifornia";
		editAddressOutput = "345 PO Bridge California";
		editCity = "California";
		editState = "Angeles";
		editPin = "123123";
		editPhone = "0987123123";
		editEmail = "sai" + randomNumber() + "@gmail.net";
		

	}

	@Test
	public void TC_01_Register() {

		loginPageUrl = driver.getCurrentUrl();

		driver.findElement(By.xpath("//a[contains(text(),'here')]")).click();

		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		System.out.println(userID);

		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		System.out.println(password);

	}

	@Test
	public void TC_02_Login() {

		driver.get(loginPageUrl);

		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		
		driver.findElement(By.xpath("//marquee[@class='heading3' and text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed();

	}

	@Test
	public void TC_03_New_Customer() {

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(genderRadioBy).click();
		driver.findElement(dayOfBirthTextboxBy).sendKeys(dayOfBirthInput);
		driver.findElement(addressTextArea).sendKeys(addressInput);
		driver.findElement(cityTextboxBy).sendKeys(city);
		driver.findElement(stateTextboxBy).sendKeys(state);
		driver.findElement(pinTextboxBy).sendKeys(pin);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(emailTextboxBy).sendKeys(email);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(By.name("sub")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()=\"Customer Registered Successfully!!!\"]")).isDisplayed());
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dayOfBirthOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), addressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
		
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		
	}

	@Test
	public void TC_04_Edit_Customer() throws Exception {
		
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		Thread.sleep(3);
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		Assert.assertEquals(driver.findElement(nameTextboxBy).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(genderTextboxBy).getAttribute("value"), gender);
		Assert.assertEquals(driver.findElement(dayOfBirthTextboxBy).getAttribute("value"), dayOfBirthOutput);
		Assert.assertEquals(driver.findElement(addressTextArea).getAttribute("value"), addressInput);
		Assert.assertEquals(driver.findElement(cityTextboxBy).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(stateTextboxBy).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(pinTextboxBy).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(phoneTextboxBy).getAttribute("value"), phone);
		Assert.assertEquals(driver.findElement(emailTextboxBy).getAttribute("value"), email);
		
		
		driver.findElement(addressTextArea).clear();
		driver.findElement(addressTextArea).sendKeys(editAddressInput);
		driver.findElement(cityTextboxBy).clear();
		driver.findElement(cityTextboxBy).sendKeys(editCity);
		driver.findElement(stateTextboxBy).clear();
		driver.findElement(stateTextboxBy).sendKeys(editState);
		driver.findElement(pinTextboxBy).clear();
		driver.findElement(pinTextboxBy).sendKeys(editPin);
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys(editPhone);
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(emailTextboxBy).sendKeys(editEmail);
		driver.findElement(By.name("sub")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()=\"Customer details updated Successfully!!!\"]")).isDisplayed());

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(), customerID);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dayOfBirthOutput);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);
		
		
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}