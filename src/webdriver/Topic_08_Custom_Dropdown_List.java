package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown_List {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitwait;
	JavascriptExecutor jsExecutor;

	public void sleepInSecond(long timeoutInSecound) {
		try {
			Thread.sleep(timeoutInSecound * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// Function check isDisplayed
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

	public void selectItemInDropdown(By parentBy, By chilBy, String expectedTextItem) {
		// Click xổ ra tất cả item
		driver.findElement(parentBy).click();

		// wait cho tất cả các element được load ra
		explicitwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(chilBy));

		List<WebElement> allItems = driver.findElements(chilBy);
		System.out.println("All items = " + allItems.size());

		for (WebElement item : allItems) {

			if (item.getText().trim().equals(expectedTextItem)) {
				if (item.isDisplayed()) {
					item.click();
				} else {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					item.click();
				}
				break;
			}
		}
	}

	public void selectItemEditTableInDropdown(By parentBy, By chilBy, String expectedTextItem) {
		// Click xổ ra tất cả item
		driver.findElement(parentBy).clear();
		driver.findElement(parentBy).sendKeys(expectedTextItem);

		// wait cho tất cả các element được load ra
		explicitwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(chilBy));

		List<WebElement> allItems = driver.findElements(chilBy);
		System.out.println("All items = " + allItems.size());

		for (WebElement item : allItems) {

			if (item.getText().trim().equals(expectedTextItem)) {
				if (item.isDisplayed()) {
					item.click();
				} else {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					item.click();
				}
				break;
			}
		}
	}

	public void selectMultiItemInDropdown(String parentXpath, String childXpath, String[] expectedValueItem) {
		// 1 click vào dropdown cho nó xổ hết các giá trị
		driver.findElement(By.xpath(parentXpath)).click();
		// 2 chờ cho các giá trị load thành công
		List<WebElement> allItems = explicitwait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		// duyệt qua các phần tử cho đến khi thoả mãn điều kiện
		for (WebElement childElement : allItems) {
			for (String item : expectedValueItem) {
				if (childElement.getText().equals(item)) {
					// 3 scroll đến item cần chọn (nếu item displayed thì không cần scroll)
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
					sleepInSecond(1);
					// 4 click vào item cần chọn
					childElement.click();
					sleepInSecond(1);
					// câu lệnh dưới đây để xác nhận số lượng item đã chọn, nếu k0 có nó code vẫn
					// chạy đủ các chuỗi đã nhập
					List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
					System.out.println("Item seleted = " + itemSelected.size());
					if (expectedValueItem.length == itemSelected.size()) {
						break;
					}
				}
			}
		}
	}

	public boolean areItemSelected(String[] months) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberItemSelected = itemSelected.size();
		// Chọn 3
		String allItemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
		System.out.println("Text đã chọn = " + allItemSelectedText);

		if (numberItemSelected <= 3 && numberItemSelected > 0) {
			boolean status = true;
			for (String item : months) {
				if (!allItemSelectedText.contains(item)) {
					status = false;
					return status;
				}
			}
			return status;
		} else if (numberItemSelected >= 12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']"))
					.isDisplayed();
		} else if (numberItemSelected > 3 && numberItemSelected < 12) {
			return driver
					.findElement(By.xpath(
							"//button[@class='ms-choice']/span[text()='" + numberItemSelected + "of 12 selected']"))
					.isDisplayed();
		} else {
			return false;
		}
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

		// Driver ID

		// Wait để áp dụng các trạng thái của element
		explicitwait = new WebDriverWait(driver, 30);

		jsExecutor = (JavascriptExecutor) driver;

		// wait tìm element cho findElement/ findElements
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_JQuery() {

		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		By parent = By.xpath("//span[@id='number-button']");
		By child = By.xpath("//ul[@id='number-menu']/li");

		selectItemInDropdown(parent, child, "5");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplayed(
				By.xpath("//span[@id='number-button']/span[@class ='ui-selectmenu-text' and(text()='5')]")));

		selectItemInDropdown(parent, child, "19");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplayed(
				By.xpath("//span[@id='number-button']/span[@class ='ui-selectmenu-text' and(text()='19')]")));

	}

	// @Test
	public void TC_02_ReatJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		By parent = By.cssSelector("i.dropdown.icon");
		By child = By.cssSelector("div[role='option']>span");

		selectItemInDropdown(parent, child, "Jenny Hess");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and text() ='Jenny Hess']")));

		selectItemInDropdown(parent, child, "Matt");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and text() ='Matt']")));
	}

	// @Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		By parent = By.cssSelector("li[class='dropdown-toggle']");
		By child = By.cssSelector("ul[class='dropdown-menu']>li");

		selectItemInDropdown(parent, child, "Second Option");
		sleepInSecond(3);
		Assert.assertTrue(
				isElementDisplayed(By.xpath("//li[@class='dropdown-toggle' and (contains(text(),'Second Option'))]")));

		selectItemInDropdown(parent, child, "Third Option");
		sleepInSecond(3);
		Assert.assertTrue(
				isElementDisplayed(By.xpath("//li[@class='dropdown-toggle' and (contains(text(),'Third Option'))]")));
	}

	// @Test
	public void TC_04_Angular() {
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

		selectItemInDropdown(By.cssSelector("span[aria-owns='games_options']"), By.cssSelector("ul#games_options>li"),
				"Football");
		sleepInSecond(3);
		Assert.assertEquals(
				driver.findElement(By.cssSelector("span[aria-owns='games_options']>input")).getAttribute("aria-label"),
				"Football");

	}

	// @Test
	public void TC_05_editTable() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");

		selectItemEditTableInDropdown(By.cssSelector("div#default-place>input"),
				By.xpath("//ul[@class='es-list' and @style]/li"), "BMW");
		sleepInSecond(3);
		driver.navigate().refresh();

		selectItemEditTableInDropdown(By.cssSelector("div#default-place>input"),
				By.xpath("//ul[@class='es-list' and @style]/li"), "Audi");
		sleepInSecond(3);
		driver.navigate().refresh();

		// explicitwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")));
	}

	@Test
	public void TC_06_Multi_Selected_Dropdown() {

		String[] firstMonth = { "February", "July", "October" };
		// String[] secondMonth = { "February", "October", "July", "August" };

		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		selectMultiItemInDropdown("(//button[@class='ms-choice'])[1]",
				"//div[@class='form-group row'][2]//div[@class='ms-drop bottom']//li//span", firstMonth);
		sleepInSecond(5);
		Assert.assertTrue(areItemSelected(firstMonth));

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}