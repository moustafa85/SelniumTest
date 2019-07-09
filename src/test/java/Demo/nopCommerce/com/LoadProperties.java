package Demo.nopCommerce.com;

import java.io.FileInputStream;
import java.util.Properties;

public class LoadProperties
{
	
	public static Properties propObject=LoadProperities(System.getProperty("user.dir")+"\\Config.properities");
	
	public static Properties LoadProperities(String Path)
	{
		Properties tempObj = new Properties();
		try
		{
			FileInputStream stream = new FileInputStream(Path);
			tempObj.load(stream);
			
		}catch(Exception erro)
		{
			erro.printStackTrace();
		}
		return tempObj;
	}

}
