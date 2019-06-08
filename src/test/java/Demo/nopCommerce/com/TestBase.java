package Demo.nopCommerce.com;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

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
		//driver.quit();
	}

}
