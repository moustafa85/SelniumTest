package Demo.nopCommerce.com;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;


public class PageBase 
{
	protected  WebDriver drvier ; 
	public Select select ; 
	public Actions action ; 
	public JavascriptExecutor jse ; 
	
	public PageBase(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	protected static void clickButton(WebElement btnElement) 
	{
		btnElement.click();
	}
	@Step("Type a text {2}")
	protected static void setTextElementText(WebElement txtElement , String value) 
	{
		txtElement.sendKeys(value);
	}
}
