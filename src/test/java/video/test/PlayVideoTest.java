package video.test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsers.use.WebDriverFactory;
import framework.imagerecognition.SikuliImageRecognition;


public class PlayVideoTest {
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected boolean isAvailable;   
	
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
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@src,'player.vi')]")));
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[contains(@src,'player.')]")));
		SikuliImageRecognition imageRecognition = new SikuliImageRecognition();
		driver.switchTo().frame(driver.findElement(By.xpath("//*[contains(@src,'player.v')]")));
		imageRecognition.clickOnImage("play_button.png");
		//Thread.sleep(1000);
		imageRecognition.clickOnImage("pause_button.png");

		// Cheking that video was played. Script with id 'player-comscore' appears only after you click on PLAY
		
		Assert.assertTrue(driver.findElement(By.id("player-comscore")).isEnabled(), "element is diplayed");
		Thread.sleep(10000);
		driver.switchTo().defaultContent();
	}
	@Test
	public void playingVideoUsingSikuly(){
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@src,'player.vi')]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[contains(@src,'player.')]")));
		SikuliImageRecognition imageRecognition = new SikuliImageRecognition();
		imageRecognition.clickOnImage("play_button.png");
		imageRecognition.clickOnImage("pause_button.png");
		isAvailable = imageRecognition.moveOnImage("facebook1.png");
		Assert.assertTrue(isAvailable, "Video is playing");
		driver.switchTo().defaultContent();
	}
	
	@AfterMethod
	public void tearDown() throws Exception{
		if (driver != null){
			WebDriverFactory.killDriverInstance();
		}
	}
	
	
}
