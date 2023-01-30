package mediolanumApp;

import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import data.CellsMapper;
import driverManager.UtilsDriver;
import feature.Init;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import utils.ImportCSV;

public class LoginIOSMedioApp {
	
	//static AndroidDriver<MobileElement> driver;
	//AndroidDriver driverAndroid;
	static HashMap<String, CellsMapper> dataFromCSV;
	private static final Logger logger = LogManager.getLogger(LoginIOSMedioApp.class);
	
	private static IOSDriver<MobileElement> driverIOS;

//    public static AndroidDriver<MobileElement> getDriver() {
//	        return driver;
//    }
	String idTestAfter="";
	UtilsDriver utilDriver = new UtilsDriver();

    @BeforeTest
    @Parameters("idTest")
    public void beforeSuite(String idTest) {
    	logger.info("Scenario) medioApp iOS before");	
        System.setProperty("webdriver.chrome.driver", "");
        DriverMedioApp dcControl = new DriverMedioApp();
        try {
        	idTestAfter =idTest;
        	driverIOS = dcControl.startDriverIosRemote(idTest);
			Init.setDriverios(driverIOS);
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @AfterSuite
    public void afterSuite() {
    	logger.info("AFTER - TestId: " +idTestAfter);
        Init.getDriver().quit();
    }   
    
    
	@Test
    @Parameters("idTest")
	public void loginNuovoNumeroFlowe(String idTest) throws EncryptedDocumentException, IOException {
		logger.info("Scenario) medioApp iOS 1");	
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		//utilDriver.takeScreenShotStep(methodName,"Start App" ,"Avvio l'applicazione", driverIOS);
		//utilDriver.screenCapture(driver);
    	//DRIVER PER LOGIN NUOVO NUMERO *************
		//AndroidDriver<MobileElement> driver = dController.startDriverClearCache(idTest);
		driverIOS = Init.getDriverios();
		ImportCSV importCsv = new ImportCSV();
		dataFromCSV = importCsv.getCSVRowDataByIdTest("Login",idTest);
		logger.info("Scenario) medioApp iOS 2");	
		//recupero dati dal csv
		
		WebDriverWait wait = new WebDriverWait(driverIOS, 40);
		driverIOS.findElement(By.xpath("//*[@text='Username']")).sendKeys("company");
		driverIOS.findElement(By.xpath("//*[@text='Password']")).sendKeys("company");
		driverIOS.findElement(By.xpath("//*[@text='loginButton']")).click();
		driverIOS.findElement(By.xpath("//*[@text='makePaymentButton']")).click();
		driverIOS.findElement(By.xpath("//*[@text='Phone']")).sendKeys("123456");
		driverIOS.findElement(By.xpath("//*[@text='Name']")).sendKeys("Test");
		driverIOS.findElement(By.xpath("//*[@text='Amount']")).sendKeys("10");
		driverIOS.findElement(By.xpath("//*[@text='Country']")).sendKeys("US");
		driverIOS.findElement(By.xpath("//*[@text='sendPaymentButton']")).click();
		driverIOS.findElement(By.xpath("//*[@text='Yes']")).click();
		

		
		
	}

}
