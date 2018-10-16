package edu;

import com.codeborne.selenide.WebDriverRunner;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SmokeSteps {

	static WebDriver driver;
	static final String URL_BASIC = "https://www.alkosto.com/";

	{
		// Settings for a ChromeDriver
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\1013606332\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver();

		// Settings for a headless browser with HtmlUnit

		// driver = new HtmlUnitDriver();
		// ((HtmlUnitDriver)driver).setJavascriptEnabled(true);
		WebDriverRunner.setWebDriver(driver);
	}

	@After
	public void tearDown() {
		driver.close();
	}

	@Given("^estoy en la pagina de Alkosto$")
	public void estoy_en_la_pagina_de_Alkosto() throws Throwable {
		driver.navigate().to(URL_BASIC);
	}

	@Given("^se muestra el Popup$")
	public void se_muestra_el_Popup() throws Throwable {
		WebDriverWait first = new WebDriverWait(driver, 60);
		first.until(ExpectedConditions.visibilityOfElementLocated(By.id("aw_popup_window")));
	}

	@When("^cierro el popup$")
	public void cierro_el_popup() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		WebElement boton = driver.findElement(By.id("aw_popup_close_btn"));
		WebDriverWait first = new WebDriverWait(driver, 60);
		first.until(ExpectedConditions.elementToBeClickable(boton));
		boton.click();
	}

	@Then("^debo estar en el home$")
	public void debo_estar_en_el_home() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String display = driver.findElement(By.id("aw_popup_close_btn")).getCssValue("display");
		Assert.assertEquals("none", display);
	}
}
