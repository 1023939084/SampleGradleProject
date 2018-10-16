package edu;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FourthCoffeeSteps {
	static WebDriver driver;
	static final String URL_HOME = "http://test-automation.herokuapp.com/bakery";
	static final String URL_ORDER_BREAD = "http://test-automation.herokuapp.com/bakery/placeorder/4";
	static final String TITLE_CONFIRMATION = "Order Confirmation";
	HomePage homePage = null;
	PlaceOrderPage placeOrderPage = null;
	ConfirmationPage confirmationPage = null;

	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\1013606332\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver();
		homePage = new HomePage(driver);
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Given("^ingreso a fourth coffee$")
	public void ingreso_a_fourth_coffee() throws Throwable {
		homePage.abrir();
	}

	@Then("^debo ver titulo del home \"([^\"]*)\"$")
	public void debo_ver_titulo_del_home(String title) throws Throwable {
		Assert.assertEquals(title, homePage.getTitle());
	}

	@Then("^debo ver (\\d+) productos$")
	public void debo_ver_productos(int arg1) throws Throwable {
		Assert.assertEquals(6, homePage.getItemsQuantity());
	}

	@When("^doy clic en el botron Order Now del producto Bread$")
	public void doy_clic_en_el_boton_Order_Now_del_producto_Bread() throws Throwable {
		driver.findElement(By.xpath("//*[@id=\"products\"]/li[4]/div[2]/a")).click();
	}

//	@Then("^debo ir a la pagina Place Your Order: Bread$")
//	public void debo_ir_a_la_pagina_placeorder() throws Throwable {
//		Assert.assertEquals("Place Your Order: Bread", driver.findElement(By.tagName("H1")).getText());
//	}
//
//	@Given("^que estoy en la pagina Place Your Order: Bread$")
//	public void estoy_en_la_pagina_placeorder() throws Throwable {
//		driver.navigate().to(URL_ORDER_BREAD);
//	}
//
//	@When("^ingreso el correo \"([^\"]*)\"$")
//	public void ingreso_el_correo(String arg1) throws Throwable {
//		driver.findElement(By.name("email")).sendKeys("test@test.com");
//	}
//
//	@When("^ingreso la direccion \"([^\"]*)\"$")
//	public void ingreso_la_direccion(String arg1) throws Throwable {
//		driver.findElement(By.name("address")).sendKeys("Cl. 11 #3142, Bogotá");
//	}
//
//	@When("^ingreso cantidad \"([^\"]*)\"$")
//	public void ingreso_cantidad(String arg1) throws Throwable {
//		driver.findElement(By.name("quantity")).sendKeys("3");
//	}
//
//	@When("^presiono el boton Place Order$")
//	public void presiono_el_boton_Place_Order() throws Throwable {
//		driver.findElement(By.tagName("form")).submit();
//	}
//	
//	@Then("^debo ver Order Confirmation$")
//	public void debo_ver_Order_Confirmation() throws Throwable {
//		Assert.assertEquals(TITLE_CONFIRMATION, driver.findElement(By.tagName("H1")).getText());
//	}

//	@Given("^estoy en la pagina Order Confirmation$")
//	public void estoy_en_la_pagina_Order_Confirmation() throws Throwable {
//	}
//
//	@When("^presiono el boton Continue Shopping$")
//	public void presiono_el_boton_Continue_Shopping() throws Throwable {
//	}
//
//	@When("^doy clic en el botron Home$")
//	public void doy_clic_en_el_botron_Home() throws Throwable {
//	}
}
