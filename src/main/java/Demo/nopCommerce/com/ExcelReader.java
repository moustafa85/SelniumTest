package Demo.nopCommerce.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader 
{

	static FileInputStream fis = null ; 
	String filePath = System.getProperty("user.dir")+"\\Resources\\TestingScenarios.xlsx";
	public FileInputStream getFileInputStream() 
	{
		//String filePath = System.getProperty("user.dir")+"\\Resources\\testdata.xlsx"; 
		//String filePath = System.getProperty("user.dir")+"\\Resources\\TestingScenarios.xlsx";
		//System.out.println("File: "+ filePath);
		File srcFile = new File(filePath);

		try {
			fis = new FileInputStream(srcFile);
		} catch (FileNotFoundException e) {
			System.out.println("Test Data file not found. treminating Process !! : Check file path of TestData");
			System.exit(0);
		}
		return fis ; 
	}

	public Object[][] getRequestsData() throws IOException
	{

		fis = getFileInputStream(); 

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0); 

		int TotalNumberOfRows = (sheet.getLastRowNum()+1);
		int TotalNumberOfCols = 11 ; 

		String[][] arrayExcelData = new String[TotalNumberOfRows][TotalNumberOfCols] ; 

		for (int i = 0; i < TotalNumberOfRows; i++) 
		{
			System.out.println("Row "+i);
			for (int j = 0; j < TotalNumberOfCols; j++) {
				XSSFRow row = sheet.getRow(i);
				arrayExcelData[i][j] = ""+row.getCell(j).toString(); 
				//System.out.print(""+arrayExcelData[i][j]+"\t | ");
			}
			System.out.println("");
		}

		wb.close();
		return arrayExcelData; 
	}

	public Object[][] getSheetData(int SheetNo) throws IOException
	{
		fis = getFileInputStream(); 
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(SheetNo); 

		int TotalNumberOfRows = (sheet.getLastRowNum()+1);
		int TotalNumberOfCols = 11 ; 

		String[][] arrayExcelData = new String[TotalNumberOfRows][TotalNumberOfCols] ; 

		for (int i = 0; i < TotalNumberOfRows; i++) 
		{
			for (int j = 0; j < TotalNumberOfCols; j++) {
				XSSFRow row = sheet.getRow(i);
				arrayExcelData[i][j] = row.getCell(j).toString(); 
			}
		}

		wb.close();
		return arrayExcelData; 
	}
	
	public void SetTestCaseResult(String TCID, String ColumnIndex, String Result)
	{
		try
		{
			fis = getFileInputStream(); 
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			 
			XSSFRow row = sheet.getRow(Integer.parseInt(TCID));
			XSSFCell cell = row.getCell(Integer.parseInt(ColumnIndex));  
            if (cell == null)  
                cell = row.createCell(Integer.parseInt(ColumnIndex));
              
            cell.setCellValue(Result);
			FileOutputStream outFile =new FileOutputStream(new File(filePath));
            wb.write(outFile);
            outFile.close();
			
		}catch(Exception er)
		{
			er.printStackTrace();
		}
		
	}
}
