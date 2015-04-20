package video.test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import framework.imagerecognition.SikuliImageRecognition;
import browsers.use.WebDriverFactory;


public class PlayVideoTest {
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	@BeforeMethod
	@Parameters ("browserName")
	public void setUp(String browserName) throws Exception{
		/* chose browser */
		driver = WebDriverFactory.getInstance(browserName);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		driver.get("http://sqadays.com/en/index");
	}
	
	@Test
	public void playingVideo() throws InterruptedException{
		wait = new WebDriverWait(driver, 15);
		//driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'player')]")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[contains(@src,'player')]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//iframe[contains(@src,'player')]")));
		SikuliImageRecognition imageRecognition = new SikuliImageRecognition();
		imageRecognition.clickOnImage("play_button.png");
		Thread.sleep(1000);
		imageRecognition.clickOnImage("pause_button.png");
		Thread.sleep(10000);
	}
	@AfterMethod
	public void tearDown() throws Exception{
		if (driver != null){
			WebDriverFactory.killDriverInstance();
		}
	}
	
	
}
