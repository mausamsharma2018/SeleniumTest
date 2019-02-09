package test;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestWebApp {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		try {
			driver.get("https://www.straitstimes.com");
			//WebDriverWait popup_element = new WebDriverWait(driver, 20);
			// popup_element.until(ExpectedConditions.presenceOfElementLocated(By.id("//*[@id=\\\"celtra-object-118\\\"]")));

			Thread.sleep(5000);
			// close the advertisement popup
			driver.switchTo().frame(driver.findElement(By.xpath("/html/body/div[11]/iframe")));
			driver.findElement(By.xpath("//*[@id=\"celtra-object-118\"]")).click();

			driver.manage().window().maximize();

			// click the login link
			driver.findElement(By.xpath("//*[@id=\"navbar\"]/div/div[2]/nav/div[2]/div/ul/li[1]/a")).click();

			// fill the login details
			driver.findElement(By.xpath("//*[@id=\"j_username\"]")).sendKeys("digitaltest10");
			driver.findElement(By.xpath("//*[@id=\"j_password\"]")).sendKeys("Sphdigital1");

			// submit the login
			driver.findElement(By.xpath("//*[@id=\"loginForm\"]/button")).click();

			Thread.sleep(2000);
			// verify user login
			WebElement loggedInUser = driver.findElement(By.name("login-user-name"));
			//WebElement user = driver.findElement(By.xpath("//*[@id=\"navbar\"]/div/div[2]/nav/div[2]/div/ul/li[1]/a"));
			Assert.assertEquals(loggedInUser.getText(),"digitaltest10");

			WebDriverWait some_element = new WebDriverWait(driver, 20);
			Thread.sleep(5000);
			// some_element.until(ExpectedConditions.visibilityOfElementLocated(By.id("//*[@id=\\\"block-system-main\\\"]/div/div/div/div/div[4]/div/div/div/div[5]/div/div/div/div/a")));

			// Click on the main article
			WebElement mainContentElem = driver.findElement(By.xpath(
					"//*[@id=\"block-system-main\"]/div/div/div/div/div[4]/div/div/div/div[5]/div/div/div/div/a"));
			
			WebElement elem = driver.findElement(By.className("block-link")); //Name("title"));
			String expected = elem.getAttribute("title");
			//System.out.println("Text : "+ expected);
			
			mainContentElem.click();

			WebElement mainElem = driver.findElement(By.xpath("//*[@id=\"block-system-main\"]/div/div[1]/div/header/h1"));
			String actual = mainElem.getText();
			//System.out.println("Text header : "+ actual);
			
			//Verify that the page has been navigated to the main article
			Assert.assertEquals(expected, actual);
			
			// check the main article has an image
			WebElement anyImageFile = driver.findElement(By.tagName("img"));
			Boolean ImagePresent = (Boolean) ((JavascriptExecutor) driver).executeScript(
					"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
					anyImageFile);
			Assert.assertTrue(ImagePresent);
			System.out.println("Test Case Passed !!!");
			
			closeResources(driver);
		} catch (Exception ex) {
			System.out.println("Test Case Failed");
			ex.printStackTrace();
			closeResources(driver);
		}
	}

	private static void closeResources(WebDriver driver) {
		if (driver != null) {
			try {
				// call logout and close the driver
				driver.findElement(By.xpath("//*[@id=\"navbar\"]/div/div[2]/nav/div[2]/div/ul/li[2]/a")).click();
			
				Thread.sleep(2000);
				driver.close();
				driver.quit();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
