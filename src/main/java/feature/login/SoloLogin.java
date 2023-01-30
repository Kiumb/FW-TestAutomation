package feature.login;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import appiumtest.AppiumTest;
import data.CellsMapper;
import driverManager.DController;
import driverManager.UtilsDriver;
import feature.Init;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import utils.ImportCSV;

public class SoloLogin {
	

	static HashMap<String, CellsMapper> dataFromCSV;
	private static final Logger logger = LogManager.getLogger(SoloLogin.class);
	private static AndroidDriver<MobileElement> driver;
	UtilsDriver utilDriver = new UtilsDriver();
	String idTestAfter="";


  @BeforeTest
  @Parameters("idTest")
  public void beforeSuite(String idTest) {
      //System.setProperty("webdriver.chrome.driver", "");
      DController dcControl = new DController();
      try {
    	    idTestAfter =idTest;
			driver = dcControl.startDriverSaveCache(idTest);
			Init.setDriver(driver);
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
	public void onlyLogin(String idTest) throws EncryptedDocumentException, IOException {
		
		ImportCSV importCsv = new ImportCSV();
		dataFromCSV = importCsv.getCSVRowDataByIdTest("Login", idTest);
		
		driver = Init.getDriver();
		logger.info("OnlyLogin idTest: "+idTest+" driver info: "+driver.getRemoteAddress());

		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		utilDriver.takeScreenShotStep(methodName, "Solo Login","Avvio l'applicazione", driver);
		//step 1 click ho gia un account (login)
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/login_text_entry")));
		MobileElement passCode = driver.findElement(By.id("bm0.zero.tier2:id/login_text_entry"));
		utilDriver.takeScreenShotStep(methodName, "Insert PassCode","Inserisco il passcode: "+"00000", driver);
		passCode.sendKeys("00000");
		
		System.out.println("wait 2s");
		WaitOptions.waitOptions(Duration.ofSeconds(2));
		
		//attendi che il saldo sia visibile
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/userBalanceBox")));
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/homeUserBalance")));
		MobileElement saldoDisponibile = driver.findElement(By.id("bm0.zero.tier2:id/homeUserBalance"));
		//saldoDisponibile.findElement(By.id("bbm0.zero.tier2:id/homeUserBalance")).getText();	
		//String valoreLetto = saldoDisponibile.findElement(By.id("bbm0.zero.tier2:id/homeUserBalance")).getText();
		//System.out.println(valoreLetto);

		

	}

}
