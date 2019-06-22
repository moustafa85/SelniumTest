package Demo.nopCommerce.com;

import java.sql.Driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class Login extends PageBase
{
	@FindBy(id="Email")
	WebElement txtEmail;
	
	@FindBy(id="Password")
	WebElement txtPassword;
	
	@FindBy(linkText="Log in")
	WebElement lnkLogin;
	
	@FindBy(xpath ="//input[@class='button-1 login-button']")
	WebElement btnLogin;
	
	@FindBy(partialLinkText ="Log out")
	WebElement lnkLogout;
	
	public Login(WebDriver driver) 
	{
		super(driver);
		jse = (JavascriptExecutor) driver; 
		action = new Actions(driver); 
	}
	@Step("Process: Click an item")
	public void pressLogin()
	{
		clickButton(lnkLogin);
	}
	@Step("Process: Log in")
	public void userLogin(String userName, String password)
	{
		setTextElementText(txtEmail, userName);
		setTextElementText(txtPassword, password);
		clickButton(btnLogin);
		//System.out.println("Add logger please");//adding a comment
	}
	@Step("Process: Log out")
	public void logout()
	{
		clickButton(lnkLogout);
	}

}
