package Demo.nopCommerce.com;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;


import junit.framework.Assert;

public class TestLogin extends TestBase 
{
	@Test(enabled = true)
	public void login()
	{
		
		System.out.println("Welcome to MVN");
		System.out.println("*********************");
		System.out.println("*********************");
		Login lgnObject = new Login(driver);
		lgnObject.pressLogin();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		lgnObject.userLogin("mo@mo.com", "Password1");
		// adding comment
		System.out.println("*********************");
		
	}

}
