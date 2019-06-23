package Demo.nopCommerce.com;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.qameta.allure.Attachment;

public class TestBase 
{
	public static WebDriver driver ; 
	String URL;

	public static DesiredCapabilities chromeOption() 
	{
		DesiredCapabilities options =DesiredCapabilities.chrome();
		//ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default.content_settings.popups", 0);
		//chromePrefs.put("download.default_directory", downloadPath);
		//options.setExperimentalOption("prefs", chromePrefs);
		//options.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
		//options.addArguments("user-data-dir=/path/to/your/custom/profile");
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		return options;
	}

	@BeforeSuite
	@Parameters({"browser"})
	public void startDriver(@Optional("chrome") String browserName) 
	{
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Resources\\chromedriver.exe");
			System.out.println(""+System.getProperty("user.dir")+"\\Resources\\chromedriver.exe");
			driver = new ChromeDriver(chromeOption()); 
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		URL = "https://demo.nopcommerce.com/";
		//URL = "https://www.google.com";
		driver.navigate().to(URL);
	}

	@AfterSuite
	public void stopDriver() 
	{
		driver.quit();
	}

	@AfterMethod
	public void onTestFailure(ITestResult result) 
	{
		
		if(result.getStatus()==ITestResult.FAILURE)
		{
			System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
			// attach screenshots to report
			saveFailureScreenShot(driver);
		}
	}

	@Attachment(value = "filename", type = "image/png")
	public byte[] saveFailureScreenShot(WebDriver idriver) 
	{
		System.out.println("Taking a screenshot");
		System.out.println("Hello");
		System.out.println("Hello");
		return ((TakesScreenshot)idriver).getScreenshotAs(OutputType.BYTES);
		
	}

	public static void captureScreenshot(WebDriver driver , String screenshotname) 
	{
		Path dest = Paths.get("./Screenshots",screenshotname+".png"); 
		try 
		{
			Files.createDirectories(dest.getParent());
			FileOutputStream out = new FileOutputStream(dest.toString());
			out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
			out.close();
		} catch (IOException e) 
		{
			System.out.println("Excpetion while taking screenshot"+ e.getMessage());
		}
	}
}
