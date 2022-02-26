package com.labs;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Swaglabs extends Report {
	public WebDriver driver;
	SoftAssert softassert = new SoftAssert();

	@BeforeTest
	public void drivers() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	public void logintest() {

		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("user-name")).sendKeys("standard_user"); // username
		driver.findElement(By.id("password")).sendKeys("secret_sauce"); // password
		driver.findElement(By.id("login-button")).click(); // login btn

		String ExpectedTilte = "Swag Labs";
		driver.findElement(By.xpath("//span[contains(text(),'Products')]")).click(); // PRODUCTS

		String ActualTitle = driver.getTitle();
		AssertJUnit.assertEquals(ActualTitle, ExpectedTilte);
		System.out.println("Verifying PRODUCTS Page Heading ");

	}

	@Test(priority = 1)
	public void AddToCart() {

		test = extent.createTest("Swaglabs", "Swaglab AddToCart test case ");
		logintest();

		driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click(); // add to cart btn
		driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click(); // cart icon
		driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click(); // checkout btn
		driver.findElement(By.id("first-name")).sendKeys("standard_user"); // firstname
		driver.findElement(By.id("last-name")).sendKeys("standard_user"); // lastname
		driver.findElement(By.id("postal-code")).sendKeys("standard_user"); // postal address
		driver.findElement(By.xpath("//*[@id=\"continue\"]")).click(); // continue
		driver.findElement(By.xpath("//*[@id=\"finish\"]")).click(); // Finish
		
		String ExpectedTilte = "Swag Labs";
		driver.findElement(By.xpath("//span[contains(text(),'Checkout: Complete!')]")); // PRODUCTS

		String ActualTitle = driver.getTitle();
		AssertJUnit.assertEquals(ActualTitle, ExpectedTilte);
		System.out.println("Verifying Checkout Complete Page Heading ");
		
		driver.close();
		
	}

	
	public void Logout() {

		//test = extent.createTest("Swaglabs", "Swaglab logout test case ");

		//driver.findElement(By.xpath("//*[@id=\"back-to-products\"]")).click(); // back home
		//logintest();
		driver.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]")).click(); // click on menu btn
		driver.findElement(By.xpath("//*[@id=\"logout_sidebar_link\"]")).click(); // click on logout
		driver.close();

		//test.info("Test completed");

	}




}
