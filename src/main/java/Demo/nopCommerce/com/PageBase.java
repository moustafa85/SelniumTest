package Demo.nopCommerce.com;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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
	protected static void setTextElementText(WebElement txtElement , String value) 
	{
		txtElement.sendKeys(value);
	}
	
	
}
