package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Actions {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String jsHelperPath = projectPath + "/dragAndDrop/drag_and_drop_helper.js";

	Actions action;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Keys key;

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

		action = new Actions(driver);
		explicitWait = new WebDriverWait(driver, 12);
		jsExecutor = (JavascriptExecutor) driver;

		if (osName.contains("Mac OS X")) {
			key = Keys.COMMAND;
		} else {
			key = Keys.CONTROL;
		}

		// driver.manage().window().maximize();

		// System.out.println("Test: " + jsHelperPath);
	}

	public void TC_01_Hover_Textbox() {

		driver.get("https://automationfc.github.io/jquery-tooltip/");

		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();

		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(),
				"We ask for your age only for statistical purposes.");

	}

	public void TC_02_Hover_Menu() {

		driver.get("https://www.myntra.com/");

		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
		sleepInSecond(2);

		action.click(driver.findElement(By.xpath("//a[@class='desktop-categoryName'and text() ='Home & Bath']")))
				.perform();

		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']"))
				.isDisplayed());

	}

	public void TC_03_Hover_Menu_Fahasa() {

		driver.get("https://www.fahasa.com/?attempt=1");
		// Hover mouse to S??ch Trong N?????c -> perform
		action.moveToElement(driver.findElement(By.xpath("//div[@class='container']//a[@title='S??ch Trong N?????c']")))
				.perform();
		sleepInSecond(5);

		// Assert: verify all sub-menu in S??ch Trong N?????c menu -> perform
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ropdown-menu-inner']//a[text()='Ti???u Thuy???t']"))
				.isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='ropdown-menu-inner']//a[text()='Truy???n Ng???n - T???n V??n']"))
						.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ropdown-menu-inner']//a[text()='Light Novel']"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ropdown-menu-inner']//a[text()='Ng??n T??nh']"))
				.isDisplayed());

	}

	public void TC_04_Click_And_Hold_Select_Multiple_Item() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> allNumber = driver.findElements(By.cssSelector("#selectable>li"));
		System.out.println("Number of allNumber = " + allNumber.size());

		// Click and Hold v??o element ?????u ti??n -> Hover -> 4.
		action.clickAndHold(allNumber.get(0)) // Click v??o 1 gi??? chu???t
				.moveToElement(allNumber.get(3)) // Di chu???t ?????n s??? 4
				.release() // Th??? chu???t ra
				.perform(); // Th???c hi???n
		sleepInSecond(3);
		Assert.assertEquals(driver.findElements(By.cssSelector("#selectable>li.ui-selected")).size(), 4);

	}

	public void TC_05_Click_and_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> allNumber = driver.findElements(By.cssSelector("#selectable>li"));

		// Nh???n ph??m Command ho???c Control
		action.keyDown(key).perform();

		// Ch???n element 1-3-6-11
		action.click(allNumber.get(0)).click(allNumber.get(2)).click(allNumber.get(5)).click(allNumber.get(10))
				.perform();

		// Th??? ph??m Command ho???c Control
		action.keyUp(key).perform();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElements(By.cssSelector("#selectable>li.ui-selected")).size(), 4);

	}

	public void TC_06_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Scroll to element
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//button[text()='Double click me']")));
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();

		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");

	}

	public void TC_07_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		// Right click
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();

		// Hover -> Menu
		action.moveToElement(driver.findElement(By.cssSelector(".context-menu-icon-quit"))).perform();
		sleepInSecond(2);

		Assert.assertTrue(
				driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-hover.context-menu-visible"))
						.isDisplayed());

		action.click(driver.findElement(By.cssSelector(".context-menu-icon-quit"))).perform();

		Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");

		driver.switchTo().alert().accept();
	}

	public void TC_08_Drag_And_Drop_HTML4() {

		driver.get("https://automationfc.github.io/kendo-drag-drop/");

		WebElement smallCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement bigCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));

		action.dragAndDrop(smallCircle, bigCircle).perform();
		Assert.assertEquals(bigCircle.getText(), "You did great!");

		// M?? m??u RGB
		System.out.println(bigCircle.getCssValue("background-color"));

		// RGB sang m??u HEX
		System.out.println(Color.fromString(bigCircle.getCssValue("background-color")).asHex());

		// Verify m?? m??u
		Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex(), "#03a9f4");
	}

	public void TC_09_Drag_And_Drop_HTML5_Css() throws IOException {

		driver.get("https://automationfc.github.io/drag-drop-html5/");

		String jsHelperFileContent = getContentFile(jsHelperPath);

		// Inject Jquery lib to site
		// String jqueryscript = readFile(jqueryPath);
		// javascriptExecutor.executeScript(jqueryscript);
		String sourceCss = "#column-a";
		String targetCss = "#column-b";
		jsHelperFileContent = jsHelperFileContent + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \""
				+ targetCss + "\"});";

		// A to B
		// jsHelperFileContent = jsHelperFileContent +
		// "$('#column-a').simulateDragDrop({ dropTarget: '#column-b'});";
		jsExecutor.executeScript(jsHelperFileContent);
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@id='column-a']/header[text()='B']")));

		// B to A
		jsHelperFileContent = jsHelperFileContent + "$('#column-b').simulateDragDrop({ dropTarget: '#column-a'});";
		jsExecutor.executeScript(jsHelperFileContent);
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@id='column-b']/header[text()='A']")));

	}

	@Test
	public void TC_09_Drag_And_Drop_HTML5_Xpath() throws AWTException {

		driver.get("https://automationfc.github.io/drag-drop-html5/");
		dragAndDropHtml5Xpath("//div[@id='column-a']", "//div[@id='column-b']");
		sleepInSecond(5);

		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@id='column-a']/header[text()='B']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@id='column-b']/header[text()='A']")));

	}

	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

	public void sleepInSecond(long timeoutInSecound) {
		try {
			Thread.sleep(timeoutInSecound * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element" + by + "is displayed");
			return true;
		} else {
			System.out.println("Element" + by + "is not displayed");
			return false;
		}
	}

	public void dragAndDropHtml5Xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x,
				((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}