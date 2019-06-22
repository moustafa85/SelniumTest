package Demo.nopCommerce.com;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;;
@Epic("Core Regression")
@Feature("البوابه و الاحالات")
public class TestLogin extends TestBase 
{
	@DataProvider(name="RequestTypes")
	public Object[][] RequestTypesData() throws IOException
	{
		// get data from Excel Reader class 

		ExcelReader ER = new ExcelReader();
		Object[][] mat = ER.getRequestsData();
		if(mat!=null)
			return mat;
		else
		{
			System.out.println("No Data");
			return mat;
		}

	}
	
	@Description("Check Paramters {1}, {2}")
	@Test(enabled =true, dataProvider="RequestTypes", description="الاختبارات")
	public void AccessRequests(String tcID, String ProcessType, String CourtType,String Category, String RequestTypeID, String DocumentTypeID, String Requester, String ApplicantType, String Respondent, String DataType, String testResults)
	{
		Login lgnObject = new Login(driver);
		lgnObject.pressLogin();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		lgnObject.userLogin("mo@mo.com", "Password1");
		ExcelReader ER = new ExcelReader();
		//SoftAssert softAssert = new SoftAssert();
		try
		{
			
			
			if(!tcID.trim().equals("TestCaseID"))
				ER.SetTestCaseResult(""+ConvertToInt(tcID),"10", "Passed");
			else
				Reporter.getCurrentTestResult().setStatus(ITestResult.SKIP);
			lgnObject.logout();
			
		}catch(Exception ExErr)
		{
			Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
			ExErr.printStackTrace();
			ER.SetTestCaseResult(""+ConvertToInt(tcID),"10", "Failed");
		}
		
	}
	
	public int ConvertToInt(String value)
	{
		double id= Double.parseDouble(value);
		int i = (int) Math.round(id);
		return i;
	}
	
	public String SeprateResult(String value)
	{
		String[] tokens = value.split(":");
		return tokens[1];
	}

}
