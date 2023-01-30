package feature.login;

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
import driverManager.DController;
import driverManager.UtilsDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import utils.ImportCSV;
import feature.Init;

public class PrimoLogin {
	
	//static AndroidDriver<MobileElement> driver;
	//AndroidDriver driverAndroid;
	static HashMap<String, CellsMapper> dataFromCSV;
	private static final Logger logger = LogManager.getLogger(PrimoLogin.class);
	
	private static AndroidDriver<MobileElement> driver;

//    public static AndroidDriver<MobileElement> getDriver() {
//	        return driver;
//    }
	String idTestAfter="";
	UtilsDriver utilDriver = new UtilsDriver();

    @BeforeTest
    @Parameters("idTest")
    public void beforeSuite(String idTest) {
        System.setProperty("webdriver.chrome.driver", "");
        DController dcControl = new DController();
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
        Init.getDriver().quit();
    }    
	    

	@Test
    @Parameters("idTest")
	public void loginNuovoNumeroFlowe(String idTest) throws EncryptedDocumentException, IOException {
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
		
		
		
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//	    wait.until(ExpectedConditions.textToBePresentInElement((WebElement) By.xpath("//*[@text='Ho già']"), "Ho già"));
		WebDriverWait wait = new WebDriverWait(driver, 40);
	
		//step 1 click ho gia un account (login)
		
		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("bm0.zero.tier2:id/btn_welcome_login")));
		logger.info("Scenario) Login nuovo numero - Step 1) click ho gia un account");
		MobileElement loginIn = driver.findElement(By.id("bm0.zero.tier2:id/btn_welcome_login"));
		loginIn.click();
		utilDriver.takeScreenShotStep(methodName,"Login" ,"tap ho gia un Account", driver);
		
//		wait.until(ExpectedConditions.elementToBeClickable(By
//		        .xpath("//android.widget.Button[contains(@text, 'OK')]")));
		
		//step 2 click ok sulla modale OTP
		wait.until(ExpectedConditions.elementToBeClickable(By.id("bm0.zero.tier2:id/btn_dialog_ok")));
		logger.info("Scenario) Login nuovo numero - Step 2) click ok sulla modale OTP");
		MobileElement otpPermission = driver.findElement(By.id("bm0.zero.tier2:id/btn_dialog_ok"));
		otpPermission.click();
		utilDriver.takeScreenShotStep(methodName,"Autorizzazione SMS" ,"OK - autorizza la lettura automatica del codice OTP via SMS", driver);

		//step 3 click ok sulla modale permesso android SMS
		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("com.android.packageinstaller:id/permission_allow_button")));
		logger.info("Scenario) Login nuovo numero - Step 3) click ok sulla modale permesso android SMS");
		MobileElement smsPermission = driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
		smsPermission.click();
		utilDriver.takeScreenShotStep(methodName,"Android Permission" ,"accetto i permessi android", driver);
		
		//step 4 inserisci il numero di telefono 
		
		wait.until(ExpectedConditions.elementToBeClickable(By
						.id("bm0.zero.tier2:id/view_phone_number")));
		logger.info("Scenario) Login nuovo numero - Step 4) inserisci il numero di telefono: "+dataFromCSV.get("8").getValore());
		MobileElement phoneNumber = driver.findElement(By.id("bm0.zero.tier2:id/view_phone_number"));
		phoneNumber.sendKeys(dataFromCSV.get("8").getValore());
		//step 4.1 send il numero di telefono 	
		logger.info("Scenario) Login nuovo numero - Step 4.1) invia il numero di telefono");
		MobileElement sendPhoneNumber = driver.findElement(By.id("bm0.zero.tier2:id/btn_phone_number_continue"));
		sendPhoneNumber.click();
		utilDriver.takeScreenShotStep(methodName,"Numero Telefonico" ,"Inserisco il numero di telefono: " +dataFromCSV.get("8").getValore(), driver);
		
		//step 5 inserisci il passcode
		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("bm0.zero.tier2:id/login_text_entry")));
		logger.info("Scenario) Login nuovo numero - Step 5) inserisci il passcode"+dataFromCSV.get("9").getValore());
		MobileElement passCode = driver.findElement(By.id("bm0.zero.tier2:id/login_text_entry"));
		passCode.sendKeys(dataFromCSV.get("9").getValore());
		utilDriver.takeScreenShotStep(methodName,"Insert Passcode" ,"inserisco il passcode: "+dataFromCSV.get("9").getValore(), driver);
		
		//step 6 il codice OTP ricevuto via SMS
		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("bm0.zero.tier2:id/otpPinEntry")));
		logger.info("Scenario) Login nuovo numero - Step 6) il codice OTP ricevuto via SMS");
		MobileElement smsCode = driver.findElement(By.id("bm0.zero.tier2:id/otpPinEntry"));
		smsCode.sendKeys(dataFromCSV.get("10").getValore());
		//step 6.1 send il codice OTP ricevuto via SMS
		logger.info("Scenario) Login nuovo numero - Step6.1) send OTP ricevuto via SMS");
		MobileElement sendSmsCode = driver.findElement(By.id("bm0.zero.tier2:id/btn_next"));
		sendSmsCode.click();
		utilDriver.takeScreenShotStep(methodName,"Insert SMS code" ,"inserisco il codice ricevuto via SMS: "+dataFromCSV.get("10").getValore(), driver);
		
		//step 7 skip finger print
		wait.until(ExpectedConditions.elementToBeClickable(By
						.id("bm0.zero.tier2:id/btn_biometric_notnow")));
		logger.info("Scenario) Login nuovo numero - Step 7) skip finger print");
		MobileElement skipFingerPrint = driver.findElement(By.id("bm0.zero.tier2:id/btn_biometric_notnow"));
		skipFingerPrint.click();
		utilDriver.takeScreenShotStep(methodName,"FingerPrint" ,"Skip Biometric", driver);
		
		//WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("//android.widget.TextView[contains(@text, 'Security token')]")));
		logger.info("Scenario) Login nuovo numero - Step 8) skip banner");
		MobileElement nextSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/dialog_gemalto_tutorial_right_btn"));
		utilDriver.takeScreenShotStep(methodName,"Banner" ,"Skip banner 1", driver);
		nextSecurityToken.click();
		utilDriver.takeScreenShotStep(methodName,"Banner" ,"Skip banner 2", driver);
		nextSecurityToken.click();
		utilDriver.takeScreenShotStep(methodName,"Banner" ,"Skip banner 3", driver);
		nextSecurityToken.click();
		//click money button
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/moneyFragment")));
			MobileElement moneyButton = driver.findElement(By.id("bm0.zero.tier2:id/moneyFragment"));
			moneyButton.click();
		//click home button
		wait.until(ExpectedConditions.elementToBeClickable(By
			        .id("bm0.zero.tier2:id/homeFragment")));
		MobileElement homeButton = driver.findElement(By.id("bm0.zero.tier2:id/homeFragment"));
		homeButton.click();
		utilDriver.takeScreenShotStep(methodName,"Money" ,"money page (jump)", driver);
		//wait and sett security token
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/gemaltoShortTutorialEnableBtn")));
		MobileElement abilitaSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/gemaltoShortTutorialEnableBtn"));
		utilDriver.takeScreenShotStep(methodName,"Security Token Banner" ,"Visualizzo il Dialog per il security token", driver);
		abilitaSecurityToken.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By
			        .id("bm0.zero.tier2:id/insert_pin_entry")));
		MobileElement insertPassCode = driver.findElement(By.id("bm0.zero.tier2:id/insert_pin_entry"));
		insertPassCode.sendKeys(dataFromCSV.get("11").getValore());
		utilDriver.takeScreenShotStep(methodName,"Security Token" ,"Insert PassCode: "+dataFromCSV.get("11").getValore(), driver);
		MobileElement sendPassCode = driver.findElement(By.id("bm0.zero.tier2:id/insert_pin_continue"));
		sendPassCode.click();
		//sett security token
		wait.until(ExpectedConditions.elementToBeClickable(By
			        .id("bm0.zero.tier2:id/security_token_login_text_entry")));
		MobileElement insertSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/security_token_login_text_entry"));
		insertSecurityToken.sendKeys(dataFromCSV.get("11").getValore());
		MobileElement sendSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/security_token_continue_btn"));
		utilDriver.takeScreenShotStep(methodName,"Security Token" ,"Confirm Security token"+dataFromCSV.get("11").getValore(), driver);
		sendSecurityToken.click();
		
		//codice troppo facile continuare
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/btn_dialog_ko")));
		MobileElement tokenFacile = driver.findElement(By.id("bm0.zero.tier2:id/btn_dialog_ko"));
		utilDriver.takeScreenShotStep(methodName,"Security Token" ,"Dialog Token Facile - OK", driver);
		tokenFacile.click();
		
		//sett security token
		wait.until(ExpectedConditions.elementToBeClickable(By
			        .id("bm0.zero.tier2:id/security_token_login_text_entry")));
		insertSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/security_token_login_text_entry"));
		insertSecurityToken.sendKeys(dataFromCSV.get("11").getValore());
		sendSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/security_token_continue_btn"));
		utilDriver.takeScreenShotStep(methodName,"Security Token" ,"Confirm Security token"+dataFromCSV.get("11").getValore(), driver);
		sendSecurityToken.click();
		
		
		//step 7 skip finger print
		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("bm0.zero.tier2:id/sca_use_biometric_not_now_btn")));
		skipFingerPrint = driver.findElement(By.id("bm0.zero.tier2:id/sca_use_biometric_not_now_btn"));
		utilDriver.takeScreenShotStep(methodName,"Dialog FingerPrint" ,"Skip Dialog FingerPrint", driver);
		skipFingerPrint.click();
		// security created
		//step 7 skip finger print
		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("bm0.zero.tier2:id/security_token_created_btn")));
		MobileElement securityContinua = driver.findElement(By.id("bm0.zero.tier2:id/security_token_created_btn"));
		utilDriver.takeScreenShotStep(methodName,"Security Token" ,"Security token settato pagina di Successo - ok", driver);
		securityContinua.click();
		
	}
	

}
