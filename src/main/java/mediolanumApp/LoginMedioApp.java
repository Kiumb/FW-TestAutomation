package mediolanumApp;

import java.io.IOException;
import java.time.Duration;
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
import io.appium.java_client.touch.WaitOptions;
import utils.ImportCSV;

public class LoginMedioApp {
	
	//static AndroidDriver<MobileElement> driver;
	//AndroidDriver driverAndroid;
	static HashMap<String, CellsMapper> dataFromCSV;
	private static final Logger logger = LogManager.getLogger(LoginMedioApp.class);
	
	private static AndroidDriver<MobileElement> driver;

//    public static AndroidDriver<MobileElement> getDriver() {
//	        return driver;
//    }
	String idTestAfter="";
	UtilsDriver utilDriver = new UtilsDriver();

    @BeforeTest
    @Parameters("idTest")
    public void beforeSuite(String idTest) {
        //System.setProperty("webdriver.chrome.driver", "");
        DriverMedioApp dcControl = new DriverMedioApp();
        try {
        	idTestAfter =idTest;
			driver = dcControl.startDriverClearCache(idTest);
			Init.setDriver(driver);
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @AfterSuite
    public void afterSuite() {
    	logger.info("AFTER - TestId: " +idTestAfter);
        //Init.getDriver().quit();
    }   
    
    
	@Test
    @Parameters("idTest")
	public void loginMedioApp(String idTest) throws EncryptedDocumentException, IOException {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		utilDriver.takeScreenShotStep(methodName,"Start App" ,"Avvio l'applicazione", driver);
		//utilDriver.screenCapture(driver);
    	//DRIVER PER LOGIN NUOVO NUMERO *************
		//AndroidDriver<MobileElement> driver = dController.startDriverClearCache(idTest);
		driver = Init.getDriver();
		ImportCSV importCsv = new ImportCSV();
		dataFromCSV = importCsv.getCSVRowDataByIdTest("Login",idTest);
		logger.info("Scenario) Login nuovo numero");	
		//recupero dati dal csv
		
		WebDriverWait wait = new WebDriverWait(driver, 45);
		
		//step 1 click permessi (login)
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.packageinstaller:id/permission_allow_button")));
		logger.info("Scenario) Login nuovo utente - Step 1) consenti permessi");
		MobileElement loginIn = driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
		loginIn.click();
		utilDriver.takeScreenShotStep(methodName,"Permessi Android" ,"tap consenti", driver);
		
		//step 2 click permessi posizione(login)
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.packageinstaller:id/permission_allow_button")));
		logger.info("Scenario) Login nuovo utente - Step 2) consenti permessi posizione");
		MobileElement permessiPosizione = driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
		permessiPosizione.click();
		utilDriver.takeScreenShotStep(methodName,"Permessi Posizione Android" ,"tap consenti", driver);
		
		//step 3 insert account (login)
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.mediolanum.android.fullbanca:id/usernameEditText")));
		logger.info("Scenario) Login nuovo utente - Step 3) Inserisci Codice cliente");
		MobileElement codiceCliente = driver.findElement(By.id("com.mediolanum.android.fullbanca:id/usernameEditText"));
		codiceCliente.sendKeys(dataFromCSV.get("8").getValore());
		utilDriver.takeScreenShotStep(methodName,"Inserisci Codice cliente" ,"codice cliente: "+dataFromCSV.get("8").getValore(), driver);
		
		//step 4 insert codice Segreto (login)
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.mediolanum.android.fullbanca:id/pwdEditText")));
		logger.info("Scenario) Login nuovo utente - Step 4) Inserisci Codice Segreto");
		MobileElement codiceSegreto = driver.findElement(By.id("com.mediolanum.android.fullbanca:id/pwdEditText"));
		codiceSegreto.sendKeys(dataFromCSV.get("9").getValore());
		utilDriver.takeScreenShotStep(methodName,"Inserisci Codice Segreto" ,"codice cliente: "+dataFromCSV.get("9").getValore(), driver);
		
		//step 5 click accedi
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.mediolanum.android.fullbanca:id/accessButton")));
		logger.info("Scenario) Login nuovo utente - Step 5) Tap Btn Accedi");
		MobileElement tapBtnAccedi = driver.findElement(By.id("com.mediolanum.android.fullbanca:id/accessButton"));
		tapBtnAccedi.click();
		utilDriver.takeScreenShotStep(methodName,"Esegui l'accesso" ,"tap btn Accedi", driver);
		
		logger.info("log time before delay");
		//WaitOptions.waitOptions(Duration.ofSeconds(20));
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("log time after delay");
		
		
		//step 6 banner INIZIA
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.mediolanum.android.fullbanca:id/button")));
		logger.info("Scenario) Login nuovo utente - Step 6) Tap Btn Inizia Banner");
		MobileElement tapBtnInizia = driver.findElement(By.id("com.mediolanum.android.fullbanca:id/button"));
		tapBtnInizia.click();
		utilDriver.takeScreenShotStep(methodName,"Esegui l'accesso" ,"tap Banner Inizia", driver);
		
		//step 6 banner INIZIA tap X
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.mediolanum.android.fullbanca:id/close")));
		logger.info("Scenario) Login nuovo utente - Step 6) Tap Btn X Banner");
		MobileElement tapBtnX = driver.findElement(By.id("com.mediolanum.android.fullbanca:id/close"));
		tapBtnX.click();
		utilDriver.takeScreenShotStep(methodName,"Esegui l'accesso" ,"tap Banner Inizia tap X", driver);
		
		
		//step 7 banner FINGER PRINT
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.mediolanum.android.fullbanca:id/not_now_button")));
		logger.info("Scenario) Login nuovo utente - Step 6) Tap Btn non ora finger print");
		MobileElement tapSkipFingerPrint = driver.findElement(By.id("com.mediolanum.android.fullbanca:id/not_now_button"));
		tapSkipFingerPrint.click();
		utilDriver.takeScreenShotStep(methodName,"Esegui l'accesso" ,"tap Non Ora - Banner Fingerprint", driver);
		
		
		
		//step 8 notifiche push
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.mediolanum.android.fullbanca:id/dialogOkButton")));
		logger.info("Scenario) Login nuovo utente - Step 6) Tap Btn ok notifiche push");
		MobileElement tapOKpushNotification = driver.findElement(By.id("com.mediolanum.android.fullbanca:id/dialogOkButton"));
		tapOKpushNotification.click();
		utilDriver.takeScreenShotStep(methodName,"Esegui l'accesso" ,"tap ok - Banner notifiche push", driver);
		
		
		
	}

}
