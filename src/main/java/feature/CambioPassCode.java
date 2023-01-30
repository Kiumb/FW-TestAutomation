package feature;

import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import appiumtest.AppiumTest;
import data.CellsMapper;
import driverManager.UtilsDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import utils.ImportCSV;

public class CambioPassCode {
	static AndroidDriver<MobileElement> driver;
	static HashMap<String, CellsMapper> dataFromCSV;
	private static final Logger logger = LogManager.getLogger(CambioPassCode.class);
	UtilsDriver utilDriver = new UtilsDriver();
	
	@Test
	@Parameters("idTest")
	public void cambioPassCode (String idTest) throws EncryptedDocumentException, IOException, InterruptedException {
		logger.info("Scenario ) Cambio PassCode ");
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		
		driver = Init.getDriver();
		//recupero dati dal csv
		ImportCSV importCsv = new ImportCSV();
		dataFromCSV = importCsv.getCSVRowDataByIdTest("Cambio PassCode" , idTest);
		System.out.println("Ricarica telefonica Started");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		
		//attendi che il saldo sia visibile
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/homeUserBalance")));
		//MobileElement saldoDisponibile = driver.findElement(By.id("bm0.zero.tier2:id/userBalanceBox"));
		//saldoDisponibile.findElement(By.id("bbm0.zero.tier2:id/homeUserBalance")).getText();	
		//System.out.println(saldoDisponibile.findElement(By.id("bbm0.zero.tier2:id/homeUserBalance")).getText());
		
		//click Profilo button
		logger.info("Step 1) Tap Profilo - ID : id/profileBox");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/profileBox")));
		MobileElement profiloBtn = driver.findElement(By.id("bm0.zero.tier2:id/profileBox"));
		profiloBtn.click();	
		utilDriver.takeScreenShotStep(methodName,"Profile Page" ,"Tap Profile", driver);
		
		//click Profilo button
		logger.info("Step 2) Tap Setting - ID : bm0.zero.tier2:id/profileRightToolbarIcon");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/profileRightToolbarIcon")));
		MobileElement settingBtn = driver.findElement(By.id("bm0.zero.tier2:id/profileRightToolbarIcon"));
		settingBtn.click();	
		utilDriver.takeScreenShotStep(methodName,"Setting Page" ,"Tap Setting", driver);
		
		
		//click PassCode button
		logger.info("Step 3) Tap PassCode Choose - ID : bm0.zero.tier2:id/profileSettingsPassCodeLayout");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/profileSettingsPassCodeLayout")));
		MobileElement passCodeChooseBtn = driver.findElement(By.id("bm0.zero.tier2:id/profileSettingsPassCodeLayout"));
		passCodeChooseBtn.click();	
		utilDriver.takeScreenShotStep(methodName,"Select PassCode" ,"Tap PassCode", driver);
		
		//click PassCode button
		logger.info("Step 4) Tap Cambia PassCode - ID : bm0.zero.tier2:id/passCodeChangeTxt");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/passCodeChangeTxt")));
		MobileElement cambiaPassCodeBtn = driver.findElement(By.id("bm0.zero.tier2:id/passCodeChangeTxt"));
		cambiaPassCodeBtn.click();	
		utilDriver.takeScreenShotStep(methodName,"Change PassCode" ,"Tap change passcode", driver);
		
		//Insert Old PassCode 
		logger.info("Step 5) Insert PassCode - ID : bm0.zero.tier2:id/changePassCodePin");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/changePassCodePin")));
		MobileElement insertOldPassCodeBtn = driver.findElement(By.id("bm0.zero.tier2:id/changePassCodePin"));
		insertOldPassCodeBtn.sendKeys(dataFromCSV.get("1").getValore());	
		utilDriver.takeScreenShotStep(methodName,"Insert Old PassCode" ,"Insert Old PassCode"+dataFromCSV.get("1").getValore(), driver);
		
		//click Continue button
		logger.info("Step 5.1) Tap Continue - ID : bm0.zero.tier2:id/changePassCodeBtn");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/changePassCodeBtn")));
		MobileElement continueBtn = driver.findElement(By.id("bm0.zero.tier2:id/changePassCodeBtn"));
		continueBtn.click();	
		
		//Insert New PassCode 
		logger.info("Step 6) Insert New PassCode - ID : bm0.zero.tier2:id/changePassCodePin");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/changePassCodePin")));
		MobileElement insertNewPassCodeBtn = driver.findElement(By.id("bm0.zero.tier2:id/changePassCodePin"));
		insertNewPassCodeBtn.sendKeys(dataFromCSV.get("2").getValore());	
		utilDriver.takeScreenShotStep(methodName,"Insert Old PassCode" ,"Insert Old PassCode"+dataFromCSV.get("2").getValore(), driver);
		//click New Continue button
		logger.info("Step 6.1) Tap New Continue - ID : bm0.zero.tier2:id/changePassCodeBtn");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/changePassCodeBtn")));
		MobileElement continueNewBtn = driver.findElement(By.id("bm0.zero.tier2:id/changePassCodeBtn"));
		continueNewBtn.click();
		
		//click New Continue button
		logger.info("Step 7) Tap Continua (Easy PassCode Info) - ID : bm0.zero.tier2:id/btn_dialog_ok");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/btn_dialog_ok")));
		MobileElement easyCodeContinueBtn = driver.findElement(By.id("bm0.zero.tier2:id/btn_dialog_ok"));
		easyCodeContinueBtn.click();
		
		
		//Insert Confirm PassCode 
		logger.info("Step 8) Insert New PassCode - ID : bm0.zero.tier2:id/changePassCodePin");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/changePassCodePin")));
		MobileElement insertConfirmPassCodeBtn = driver.findElement(By.id("bm0.zero.tier2:id/changePassCodePin"));
		insertConfirmPassCodeBtn.sendKeys(dataFromCSV.get("3").getValore());	
		utilDriver.takeScreenShotStep(methodName,"Insert Old PassCode" ,"Insert Old PassCode"+dataFromCSV.get("3").getValore(), driver);
		
		//click New Continue button
		logger.info("Step 8.1) Tap Confirm Continue - ID : bm0.zero.tier2:id/changePassCodeBtn");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/changePassCodeBtn")));
		MobileElement continueConfirmBtn = driver.findElement(By.id("bm0.zero.tier2:id/changePassCodeBtn"));
		continueConfirmBtn.click();
		utilDriver.takeScreenShotStep(methodName,"Pagina Cambio Riuscito" ,"Operazione riuscita", driver);
		//verifico pagina di atterraggio "PassCode e security"
		logger.info("Step 9) Verifico pagina di atterraggio \"PassCode e security\" - ID : bm0.zero.tier2:id/passCodeChangeTxt");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/passCodeChangeTxt")));
		utilDriver.takeScreenShotStep(methodName,"Tap Back" ,"Operazione riuscita", driver);
		logger.info("Step 9.1) Tap : Back - ID : bm0.zero.tier2:id/passCodeChangeTxt");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.LinearLayout/android.view.View[1]")));
		MobileElement backBtn = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.LinearLayout/android.view.View[1]"));
		utilDriver.takeScreenShotStep(methodName,"Tap Back" ,"Operazione riuscita", driver);
		backBtn.click();
		
		//Tap Back2 
		logger.info("Step 10) Tap : Back - ID : bm0.zero.tier2:id/passCodeChangeTxt");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView")));
		MobileElement backBtn2 = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView"));
		utilDriver.takeScreenShotStep(methodName,"Tap Back" ,"Operazione riuscita", driver);
		backBtn2.click();
		
		//Tap Back3
		logger.info("Step 8.1) Tap Confirm Continue - ID : bm0.zero.tier2:id/profileBackIcon");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/profileBackIcon")));
		MobileElement back3Btn = driver.findElement(By.id("bm0.zero.tier2:id/profileBackIcon"));
		utilDriver.takeScreenShotStep(methodName,"Tap Back" ,"Operazione riuscita", driver);
		back3Btn.click();
		
		//Verifico se sono nella home ... test riuscito
		logger.info("Step 8.1) Tap Confirm Continue - ID : bm0.zero.tier2:id/homeUserBalance");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/homeUserBalance")));
		utilDriver.takeScreenShotStep(methodName,"Tap Back" ,"Operazione riuscita", driver);
//		MobileElement back3Btn = driver.findElement(By.id("bm0.zero.tier2:id/profileBackIcon"));
//		back3Btn.click();
		

	}

}
