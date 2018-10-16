package edu;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PruebasSelenium {
	static WebDriver driver;
	static final String URL_BASIC = "http://test-automation.herokuapp.com/selenium/basic.html";
	static final String URL_GOOGLE = "http://google.com";
	static final String URL_INTEREST = "http://test-automation.herokuapp.com/selenium/interest";
	static final String URL_ALERT = "http://test-automation.herokuapp.com/selenium/alerts.html";
	static final String URL_FRAME = "http://test-automation.herokuapp.com/selenium/frames/index.html";
	static final String URL_AJAX = "http://test-automation.herokuapp.com/selenium/ajax.html";
	static final String TITLE_BASIC = "Basic Web Page";

	{
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\1013606332\\Downloads\\chromedriver.exe");
//		driver = new ChromeDriver();	
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		DesiredCapabilities capability = DesiredCapabilities.chrome();

		try {
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Close the browser
	 */
	@org.junit.After
	public void tearDown() {
		driver.quit();
	}

	/*
	 * @Test public void pruebaUno() { driver.get("http://www.google.com"); String
	 * sourceCode = driver.getPageSource(); System.out.println(sourceCode);
	 * Assert.assertEquals(true, false); }
	 */

	@Test
	public void testTitle() {
		driver.get(URL_BASIC);
		String title = driver.getTitle();
		Assert.assertEquals(TITLE_BASIC, title);
	}

//	@Test
	public void testUrl() {
		driver.get(URL_BASIC);
		String url = driver.getCurrentUrl();
		Assert.assertEquals(URL_BASIC, url);
	}

//	@Test
	public void testTitleNavigate() {
		driver.navigate().to(URL_GOOGLE);
		driver.navigate().to(URL_BASIC);
		driver.navigate().back();
		driver.navigate().forward();
		Assert.assertEquals(TITLE_BASIC, driver.getTitle());
	}

//	@Test
	public void testTextParagrahpOne() {
		driver.navigate().to(URL_BASIC);
		String text = driver.findElement(By.id("parrafo1")).getText();
		Assert.assertEquals("Parrafo 1", text);
	}

//	@Test
	public void testTextParagrahpFour() {
		driver.navigate().to(URL_BASIC);
		String text = driver.findElements(By.tagName("p")).get(3).getText();
		Assert.assertEquals("Parrafo 4", text);
	}

//	@Test
	public void testTextParagrahpThreeWayOne() {
		driver.navigate().to(URL_BASIC);
		String text = driver.findElement(By.className("parrafo3")).getText();
		Assert.assertEquals("Parrafo 3", text);
	}

//	@Test
	public void testTextParagrahpThreeWayTwo() {
		driver.navigate().to(URL_BASIC);
		String text = driver.findElement(By.cssSelector("body > p.parrafo3")).getText();
		Assert.assertEquals("Parrafo 3", text);
	}

//	@Test
	public void testTextParagrahpThreeWayThree() {
		driver.navigate().to(URL_BASIC);
		String text = driver.findElement(By.xpath("/html/body/p[3]")).getText();
		Assert.assertEquals("Parrafo 3", text);
	}

//	@Test
	public void testClickHref() {
		// Given
		driver.navigate().to(URL_BASIC);

		// When
		driver.findElement(By.linkText("Enlace 1")).click();

		// Then
		Assert.assertEquals("Google", driver.getTitle());
	}

//	@Test
	/**
	 * Validate messages of error on submit form without content
	 */
	public void testInterestShowAllErrors() {
		// Given
		driver.navigate().to(URL_INTEREST);

		// When
		driver.findElement(By.id("interestForm")).submit();
		List<WebElement> errors = driver.findElements(By.xpath("//label[@class=\"error\"]"));

		// Then
		Assert.assertEquals(5, errors.size());
	}

//	@Test
	/**
	 * If input is text then 0.00
	 */
	public void testInterestInputText() {
		// Given
		driver.navigate().to(URL_INTEREST);

		// When
		driver.findElement(By.name("amount")).sendKeys("text");
		driver.findElement(By.name("rate")).sendKeys("text");

		WebElement element = driver.findElement(By.name("rate_units"));
		Select select = new Select(element);
		select.selectByValue("month");

		driver.findElement(By.name("time")).sendKeys("text");
		driver.findElements(By.name("time_units")).get(0).click();
		driver.findElement(By.id("interestForm")).submit();

		String text = driver.findElement(By.id("interest")).getText();

		// Then
		Assert.assertEquals("0.00", text);
	}

//	@Test
	/**
	 * Counting by the monthly rate and time
	 */
	public void testInteresCountingOne() {
		// Given
		driver.navigate().to(URL_INTEREST);

		// When
		driver.findElement(By.name("amount")).sendKeys("1000");
		driver.findElement(By.name("rate")).sendKeys("5");

		WebElement element = driver.findElement(By.name("rate_units"));
		Select select = new Select(element);
		select.selectByValue("month");

		driver.findElement(By.name("time")).sendKeys("2");
		driver.findElements(By.name("time_units")).get(1).click();
		driver.findElement(By.id("interestForm")).submit();

		String text = driver.findElement(By.id("interest")).getText();

		// Then
		Assert.assertEquals("100.00", text);
	}

//	@Test
	/**
	 * Counting by the annual rate and time
	 */
	public void testInteresCountingTwo() {
		// Given
		driver.navigate().to(URL_INTEREST);

		// When
		driver.findElement(By.name("amount")).sendKeys("1000");
		driver.findElement(By.name("rate")).sendKeys("5");

		WebElement element = driver.findElement(By.name("rate_units"));
		Select select = new Select(element);
		select.selectByValue("year");

		driver.findElement(By.name("time")).sendKeys("2");
		driver.findElements(By.name("time_units")).get(0).click();
		driver.findElement(By.id("interestForm")).submit();

		String text = driver.findElement(By.id("interest")).getText();

		// Then
		Assert.assertEquals("100.00", text);
	}

//	@Test
	/**
	 * Counting by the monthly rate and annual time
	 */
	public void testInteresCountingThree() {
		// Given
		driver.navigate().to(URL_INTEREST);

		// When
		driver.findElement(By.name("amount")).sendKeys("1000");
		driver.findElement(By.name("rate")).sendKeys("5");

		WebElement element = driver.findElement(By.name("rate_units"));
		Select select = new Select(element);
		select.selectByValue("month");

		driver.findElement(By.name("time")).sendKeys("2");
		driver.findElements(By.name("time_units")).get(0).click();
		driver.findElement(By.id("interestForm")).submit();

		String text = driver.findElement(By.id("interest")).getText();

		// Then
		Assert.assertEquals("1200.00", text);
	}

//	@Test
	/**
	 * Counting by the annual rate and monthly time
	 */
	public void testInteresCountingFour() {
		// Given
		driver.navigate().to(URL_INTEREST);

		// When
		driver.findElement(By.name("amount")).sendKeys("1000");
		driver.findElement(By.name("rate")).sendKeys("5");

		WebElement element = driver.findElement(By.name("rate_units"));
		Select select = new Select(element);
		select.selectByValue("year");

		driver.findElement(By.name("time")).sendKeys("2");
		driver.findElements(By.name("time_units")).get(1).click();
		driver.findElement(By.id("interestForm")).submit();

		String text = driver.findElement(By.id("interest")).getText();

		// Then
		Assert.assertEquals("8.33", text);
	}

//	@Test
	/**
	 * Validate show message from alert input text
	 */
	public void testAlertOne() {
		// Given
		driver.navigate().to(URL_ALERT);

		// When
		driver.findElement(By.id("show-prompt")).click();
		Alert alert = driver.switchTo().alert();
		alert.sendKeys("hola mundo");
		alert.accept();

		// Then
		Assert.assertEquals("hola mundo", driver.findElement(By.id("result-value")).getText());
	}

//	@Test
	/**
	 * Validate frame white page
	 */
	public void testFrameOne() {
		// Given
		driver.navigate().to(URL_FRAME);

		// When
		driver.switchTo().frame("menu");
		driver.findElement(By.linkText("White Page")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("content");

		// Then
		Assert.assertEquals("White Page", driver.findElements(By.tagName("h1")).get(0).getText());
	}

//	@Test
	/**
	 * Validate title from new window green page
	 */
	public void testWindowsOne() {
		// Given
		driver.navigate().to(URL_FRAME);

		// When
		driver.switchTo().frame("content");
		driver.findElements(By.tagName("ul")).get(2).findElement(By.linkText("Green page")).click();

		Set<String> setWindows = driver.getWindowHandles();
		List<String> listWindows = new ArrayList<>(setWindows);
		driver.switchTo().window(listWindows.get(1));

		String text = driver.findElements(By.tagName("h1")).get(0).getText();

		// Then
		Assert.assertEquals("Green Page", text);
	}

//	@Test
	/**
	 * Validate text from AJAX request
	 */
	public void testAjaxOne() {
		// Given
		driver.navigate().to(URL_AJAX);

		// When
		WebElement make = driver.findElement(By.id("make"));
		Select selectMake = new Select(make);
		selectMake.selectByValue("Audi");

		WebElement model = driver.findElement(By.id("model"));
		Select selectModel = new Select(model);

		WebDriverWait first = new WebDriverWait(driver, 10);
		first.until(ExpectedConditions.elementToBeClickable(model));

		selectModel.selectByValue("A6");

		driver.findElement(By.id("accept")).click();

		WebDriverWait second = new WebDriverWait(driver, 10);
		second.until(ExpectedConditions.visibilityOfElementLocated(By.id("value")));

		String text = driver.findElement(By.id("value")).getText();

		// Then
		Assert.assertEquals("Audi-A6", text);
	}

//	@Test
	/**
	 * Validate text from AJAX request
	 */
	public void testAjaxTwo() {
		// Given
		driver.navigate().to(URL_AJAX);

		// When
		WebElement make = driver.findElement(By.id("make"));
		Select selectMake = new Select(make);
		selectMake.selectByValue("Audi");

		WebElement model = driver.findElement(By.id("model"));
		Select selectModel = new Select(model);

		WebDriverWait first = new WebDriverWait(driver, 10);
		first.until(ExpectedConditions.elementToBeClickable(model));

		selectModel.selectByValue("A6");

		driver.findElement(By.id("accept")).click();

		WebDriverWait second = new WebDriverWait(driver, 10);
		second.until(ExpectedConditions.visibilityOfElementLocated(By.id("value")));

		String text = driver.findElement(By.id("value")).getText();

		// Then
		Assert.assertEquals("Audi-A6", text);
	}
}
