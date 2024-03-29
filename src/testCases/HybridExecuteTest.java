package testCases;

import java.io.IOException;
import java.util.Properties;

import operation.ReadObject;
import operation.UIOperation;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import excelExportAndFileIO.ReadGuru99ExcelFile;

public class HybridExecuteTest {
	WebDriver driver = null;
    @Test(dataProvider="hybridData")
	public void testLogin(String testcaseName,String keyword,String objectName,String objectType,String value) throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:\\Sanjay\\Selenium_Training\\HybridFramework\\HybridFramework\\jars\\chromedriver.exe");
		driver= new ChromeDriver();
		
      
    	
    	//if(testcaseName!=null&&testcaseName.length()!=0){
    		//driver=new FirefoxDriver();
    	//}
		
        ReadObject object = new ReadObject();
        Properties allObjects =  object.getObjectRepository();
        UIOperation operation = new UIOperation(driver);
      	//Call perform function to perform operation on UI
    			operation.perform(allObjects, keyword, objectName,
    				objectType, value);
    	    
	}

    
    @DataProvider(name="hybridData")
	public Object[][] getDataFromDataprovider() throws IOException{
    	Object[][] object = null; 
    	ReadGuru99ExcelFile file = new ReadGuru99ExcelFile();
        
         //Read keyword sheet
         Sheet guru99Sheet = file.readExcel("D:\\Sanjay\\Selenium_Training\\HybridFramework\\HybridFramework\\TestCase.xlsx","TestCase.xlsx", "KeywordFramework");
       //Find number of rows in excel file
     	int rowCount = guru99Sheet.getLastRowNum()-guru99Sheet.getFirstRowNum();
     	object = new Object[rowCount][5];
     	for (int i = 0; i < rowCount; i++) {
    		//Loop over all the rows
    		Row row = guru99Sheet.getRow(i+1);
    		//Create a loop to print cell values in a row
    		for (int j = 0; j < row.getLastCellNum(); j++) {
    			//Print excel data in console
    			object[i][j] = row.getCell(j).toString();
    		}
    		
    		break;
         
    	}
     	System.out.println("");
     	  return object;	 
	}
}
