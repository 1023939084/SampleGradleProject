package edu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends ObjectPage {
	static final String URL = "http://test-automation.herokuapp.com/bakery";
	static final String TITLE = "Fourth Coffee";

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void abrir() {
		driver.navigate().to(URL);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public int getItemsQuantity() {
		return driver.findElement(By.id("products")).findElements(By.tagName("li")).size();
	}

	public void clickOrderButton(int product) {
		driver.findElement(By.xpath("//*[@id=\"products\"]/li[" + product + "]/div[2]/a")).click();
	}
}
