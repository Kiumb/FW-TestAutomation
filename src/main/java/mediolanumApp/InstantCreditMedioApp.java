package mediolanumApp;

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

import data.CellsMapper;
import driverManager.UtilsDriver;
import feature.Init;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import utils.ImportCSV;

public class InstantCreditMedioApp {
			
		//static AndroidDriver<MobileElement> driver;
		//AndroidDriver driverAndroid;
		static HashMap<String, CellsMapper> dataFromCSV;
		private static final Logger logger = LogManager.getLogger(InstantCreditMedioApp.class);
		
		private static AndroidDriver<MobileElement> driver;
		

//	    public static AndroidDriver<MobileElement> getDriver() {
//		        return driver;
//	    }
		String idTestAfter="";
		UtilsDriver utilDriver = new UtilsDriver();
		
		@Test
	    @Parameters("idTest")
		public void instantCredit(String idTest) throws EncryptedDocumentException, IOException, InterruptedException {
			logger.info("Scenario) InstantCredit");	
			logger.info("log time before delay - InstantCredit");
			//WaitOptions.waitOptions(Duration.ofSeconds(20));
			
				Thread.sleep(5000);
	
			logger.info("log time after delay - InstantCredit");
			String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
			logger.info("Scenario) InstantCredit 2");	
			//utilDriver.screenCapture(driver);
	    	//DRIVER PER LOGIN NUOVO NUMERO *************
			//AndroidDriver<MobileElement> driver = dController.startDriverClearCache(idTest);
			driver = Init.getDriver();
			utilDriver.takeScreenShotStep(methodName,"InstantCredit" ,"Tap Top Menu", driver);
			ImportCSV importCsv = new ImportCSV();
			dataFromCSV = importCsv.getCSVRowDataByIdTest("InstantCredit MB",idTest);
			
			//recupero dati dal csv
			
			WebDriverWait wait = new WebDriverWait(driver, 40);
			
			//step 1 click TopMenu
			//wait.until(ExpectedConditions.elementToBeClickable(By.id("Navigate up")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]")));
			logger.info("Scenario) InstantCredit - Step 1) tap top menu");
			MobileElement topMenu = driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"));
			topMenu.click();
			utilDriver.takeScreenShotStep(methodName,"Top Menu" ,"open menu", driver);
			
			
			//step 2 scroll and Click instantCredit
			//wait.until(ExpectedConditions.elementToBeClickable(By
				//	.id("bm0.zero.tier2:id/adapterProfileBannerDescription")));
			logger.info("Scenario) InstantCredit - Step 2) scroll and tap instant Credit");
			MobileElement instantCredit = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"SelfyCredit\").instance(0))");
					//driver.findElement(By.id("bm0.zero.tier2:id/adapterProfileBannerDescription"));
			instantCredit.click();
			utilDriver.takeScreenShotStep(methodName,"Instant Credit View" ,"open instant credit view", driver);
			
			//delay
			Thread.sleep(3000);
			//step 3 selezione importo
			logger.info("Scenario) InstantCredit - Step 3) sseleziona importo");
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.SeekBar")));
			logger.info("Scenario) InstantCredit - Step 2) by id");
			MobileElement seekBar = driver.findElement(By.id("com.mediolanum.android.fullbanca:id/seek_bar"));
			logger.info("Scenario) InstantCredit - Importo letto dall'excel: " +dataFromCSV.get("1").getValore());
			seekBar.sendKeys(calculateValueToSet(dataFromCSV.get("1").getValore()));
			//wait.until(ExpectedConditions.element To BeClickable(By.id("com.mediolanum.android.fullbanca:id/importo")));
			try {
			MobileElement boxImporto = driver.findElement(By.id("com.mediolanum.android.fullbanca:id/importo"));
			String valueBoxImporto = boxImporto.getText();
			utilDriver.takeScreenShotStep(methodName,"Instant Credit - Select Import" ,"valore da excel:"+
					dataFromCSV.get("1").getValore()+" Valore nel box dopo la selezione dell'importo è : "+valueBoxImporto, driver);
			}catch(org.openqa.selenium.TimeoutException r) {
				logger.info("exception",r);
				org.testng.Assert.fail("element not found");
			}
			
			//scrollo piu sotto \"Imposta di bollo\"
			//MobileElement scrollToImpostaDiBollo = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Imposta di bollo\").instance(0))");
			
			//step 4 select Rate
			//wait.until(ExpectedConditions.elementToBeClickable(By
				//	.id("bm0.zero.tier2:id/adapterProfileBannerDescription")));
			logger.info("Scenario) InstantCredit - Step 4) seleziona la rata");
			try {
			MobileElement selectRate = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"in 42 rate\").instance(0))");
					//driver.findElement(By.id("bm0.zero.tier2:id/adapterProfileBannerDescription"));
			selectRate.click();
			}catch(Exception e ) {
				
			}
			utilDriver.takeScreenShotStep(methodName,"Select Rate" ,"try to scroll and tap rate 24", driver);
			
			//step 5 open Motivation Dropdown 
			MobileElement scrollToProcedi = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"PROCEDI\").instance(0))");
			logger.info("Scenario) InstantCredit - Step 5) open DropDown Motivation");
			wait.until(ExpectedConditions.elementToBeClickable(By.id("com.mediolanum.android.fullbanca:id/click_panel")));
			MobileElement dropdownMotivation = driver.findElement(By.id("com.mediolanum.android.fullbanca:id/click_panel"));
			dropdownMotivation.click();
			
			//step 6 Scroll and Tap to Liquidita
			logger.info("Scenario) InstantCredit - Step 6) select motivation");
			MobileElement motivoScelto = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Liquidita\").instance(0))");
					//driver.findElement(By.id("bm0.zero.tier2:id/adapterProfileBannerDescription"));
			motivoScelto.click();
			utilDriver.takeScreenShotStep(methodName,"Select Motivation" ,"select Liquidità", driver);
			
			//step 6 Procedi
			logger.info("Scenario) InstantCredit - Step 7) Tap Procedi");
			wait.until(ExpectedConditions.elementToBeClickable(By.id("com.mediolanum.android.fullbanca:id/continua")));
			MobileElement tapProcedi = driver.findElement(By.id("com.mediolanum.android.fullbanca:id/continua"));
			tapProcedi.click();
			utilDriver.takeScreenShotStep(methodName,"Tap Procedi" ,"wait for riepilogo", driver);
			
			//TODO
			//step 7 implementa lettura allegati (pdf)
			
			//step 8 scroll to Procedi
			MobileElement scrollToProcediFinale = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"PROCEDI\").instance(0))");
			utilDriver.takeScreenShotStep(methodName,"Riepilogo" ,"Scroll To Procedi", driver);
			
			//step9 accetta contratto
			logger.info("Scenario) InstantCredit - Step 9) Check presa visione");
			wait.until(ExpectedConditions.elementToBeClickable(By.id("com.mediolanum.android.fullbanca:id/dichiarazione_presa_visione_checkbox")));
			MobileElement tapPresaVisione = driver.findElement(By.id("com.mediolanum.android.fullbanca:id/dichiarazione_presa_visione_checkbox"));
			tapPresaVisione.click();
			utilDriver.takeScreenShotStep(methodName,"Tap PresaVisione" ,"check presa visione", driver);
			
			//Step 11 permessi android conssenti
			logger.info("Scenario) InstantCredit - Step 9) Check presa visione");
			wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.packageinstaller:id/permission_allow_button")));
			MobileElement allowPermission = driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
			allowPermission.click();
			utilDriver.takeScreenShotStep(methodName,"Visualizza Contratto" ,"check presa visione", driver);
			
			MobileElement backToDocument = driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"));
			backToDocument.click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]")));
			utilDriver.takeScreenShotStep(methodName,"Tap Continua" ,"tap continua e conferma l'operazione", driver);
			//android.widget.ImageButton[@content-desc="Navigate up"]
			Thread.sleep(1000);
			
			//Step 10 conferma instantCredit...
			logger.info("Scenario) InstantCredit - Step 9) Check presa visione");
			wait.until(ExpectedConditions.elementToBeClickable(By.id("com.mediolanum.android.fullbanca:id/continua")));
			MobileElement confermaOperazione = driver.findElement(By.id("com.mediolanum.android.fullbanca:id/continua"));
			confermaOperazione.click();
			utilDriver.takeScreenShotStep(methodName,"Tap Continua" ,"tap continua e conferma l'operazione", driver);
			Thread.sleep(1000);
			//backToDocument.click();
			
			
			
			
		}
		
		
		
		
		public String calculateValueToSet(String importoDaExcel) {
			logger.info("Scenario) InstantCredit - calculateValueToSet: " +importoDaExcel);
			float importo = Float.parseFloat(importoDaExcel);
			int value;
			if(importo < 2000)
				return "0";
			else if(importo > 15000)
				return "26";
			else {
				
				value = Math.round((importo/500)-4);
				logger.info("Scenario) InstantCredit - importo da excel: " +importoDaExcel+" indexToSend: " +value);
			}
			return Integer.toString(value);
		}

}
