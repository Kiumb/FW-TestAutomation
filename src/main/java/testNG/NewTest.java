package testNG;

import org.testng.annotations.Test;

import driverManager.DController;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import utils.ImportCSV;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;

public class NewTest {
	
	private static final Logger logger = LogManager.getLogger(ImportCSV.class);
	
	static AndroidDriver<MobileElement> driver;
	
	
	
	@Test
    @Parameters("idTest")
    public void hi(String name) {
        Assert.assertNotNull(name);
        Reporter.log("Name is:" + name, true);
        DController dController = new DController();
		//DRIVER PER LOGIN NUOVO NUMERO *************
		try {
			driver = dController.startDriverClearCache(name);
			//DRIVER SOLO LOGIN  *************
			//driver = dController.startDriverSaveCache();
		  //DRIVER PER VIRTUAL DEVICE: LOGIN NUOVO NUMERO *************
			//driver = dController.startEmulatorDriverClearCache();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebDriverException e) {
			logger.error("Appium Error : "+e.getMessage());
			System.exit(1);
			// TODO: handle exception
		}
    }
	
	
	
  


  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

  @BeforeSuite
  public void beforeSuite() {
  }

  @AfterSuite
  public void afterSuite() {
  }

}
