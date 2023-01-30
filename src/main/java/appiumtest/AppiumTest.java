package appiumtest;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import data.CellsMapper;
import data.TestBook;
import data.TestObj;
import feature.CambioPassCode;
import feature.cashIn.VerificaEAccettaRichiestaRicarica;
import feature.cashIn.RicaricaContoConCartaDiCred;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import testBookManager.TestBookManager;
import testNG.NGMethod;
import utils.Utils;

public class AppiumTest {

	static Date date = new Date();      
    static String LogDate= new SimpleDateFormat("yyyyMMdd").format(date);
    
	static AndroidDriver<MobileElement> driver;
	//AndroidDriver driverAndroid;
	static HashMap<String, CellsMapper> dataFromCSV;
	private static final Logger logger = LogManager.getLogger(AppiumTest.class);
	static Utils utils = new Utils(); 
	
	
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		System.setProperty("logFilename", LogDate);
		logger.info("System property : " +System.getProperty("logFilename"));
		
		String idTest="";

		
		try {
			
			logger.info("info - Inizio test ore : " + utils.getTime());
			
			TestBookManager tbManager = new TestBookManager();
			List<TestObj> testObjList ;
			TestBook testBook = new TestBook();
			testBook = tbManager.generateTestBook("TestCase1.xlsx");
			logger.info("testBook size  : " + testBook.getNomeTestBook());
			testObjList = testBook.getTestBookList();
			
			
			NGMethod utilsNG = new NGMethod();
			//utilsNG.runNGTest(testBook);
			utilsNG.testRunner(testBook);
			
			
//			for(int i = 0; i<testObjList.size();i++) {
//					TestObj tObj = testObjList.get(i);
//					idTest = tObj.getIdTest();
//					logger.info("testObjList size  : " + testObjList.size());
//					logger.info("idTest  : " + idTest);
//					
//					List<String> stepList = tObj.getStepList();
//					logger.info("stepList  : " + stepList);
//					for(String str:stepList) {
//						logger.info("ID Test  : " + idTest + "Step Trovato : " + str);
//					}
//
//					//DController dController = new DController();
//				  //DRIVER PER LOGIN NUOVO NUMERO *************
//					//driver = dController.startDriverClearCache(idTest);
//				  //DRIVER SOLO LOGIN  *************
//					//driver = dController.startDriverSaveCache();
//				  //DRIVER PER VIRTUAL DEVICE: LOGIN NUOVO NUMERO *************
//					//driver = dController.startEmulatorDriverClearCache();
//					
//					System.out.println(utils.getTime());
//					
//			}
			
			//esegui il login
			logger.info("Carico Lo Scenario - Login");
			//Login login = new Login();
			//login.loginNuovoNumeroFlowe(driver,idTest);
			//login.onlyLogin(driver);
			
			// effettua una ricarica del conto
			RicaricaContoConCartaDiCred ricConto = new RicaricaContoConCartaDiCred();
			logger.info("Carico Lo Scenario - Ricarica Conto : Effettuo la ricarica del conto");
			//ricConto.rechargeFloweTokenImpostato(driver);
			
			//effettua una ricarica del conto
			//Bollettino bollettino = new Bollettino();
			logger.info("Carico Lo Scenario - Bollettino postale - Pagamento bollettino postale");
			//bollettino.primoBollettinoPremarcato(driver);
			//bollettino.successivoBollettinoPremarcato(driver);
			
			//Cambio PassCode
			logger.info("Carico Lo Scenario - Cambio PassCode");
			CambioPassCode cambioPassCode = new CambioPassCode();
			//cambioPassCode.cambioPassCode(driver);
			
			//CashIn
//			logger.info("Carico Lo Scenario - CashIn");
//			VerificaEAccettaRichiestaRicarica cashIn = new VerificaEAccettaRichiestaRicarica();
//			//cashIn.primaRichiestaRicarica(driver);
//			//cashIn.richiestaRicaricaSuccessiva(driver);
//			//Verifica Ricarica CashIn
//			cashIn.verificaRicaricaRicevuta(driver, idTest);
			
			
//			//effettua prima ricarica telefonica
//			WaitOptions.waitOptions(Duration.ofSeconds(1));
//			logger.info("Carico Lo Scenario - Ricarica telefonica : Effettuo una ricarica telefonica");
//			RicaricaTelefonica ricTelefonica = new RicaricaTelefonica();
//			//ricTelefonica.primaRicaricaTelefonica(driver);
//			WaitOptions.waitOptions(Duration.ofSeconds(1));
//			int nRic=10;
////			for(int i=0;i<=16;i++) {
////				int indexForSecurCode = i+2;
////				logger.info("Step) Ricarica telefonica - Effettuo la ricarica num:"+indexForSecurCode);	
////				//System.out.println("Ricarica Num: "+indexForSecurCode);
////				ricTelefonica.successiveRicariccheTelefonicche(driver);
////			}
//			//driver.closeApp();
					
		} catch (Exception exp) {
			System.out.println(exp.getCause());
			System.out.println(exp.getMessage());	
			exp.printStackTrace();
		}
		
	} 

	
//	public static void rechargeFlowe() throws EncryptedDocumentException, IOException {
//	
//		//recupero dati dal csv
//		ImportCSV importCsv = new ImportCSV();
//		dataFromCSV = importCsv.getCSVData("Login");
//		
//		WebDriverWait wait = new WebDriverWait(driver, 30);
//	
//		//step 1 click ho gia un account (login)
//		wait.until(ExpectedConditions.elementToBeClickable(By
//		        .id("bm0.zero.tier2:id/login_text_entry")));
//		MobileElement passCode = driver.findElement(By.id("bm0.zero.tier2:id/login_text_entry"));
//		passCode.sendKeys("00000");
//		
//		//step 2 Security token  
//		//if (driver.findElements( By.id("bm0.zero.tier2:id/gemaltoShortTutorialEnableBtn") ).size() != 0) {
//			wait.until(ExpectedConditions.elementToBeClickable(By
//		        .id("bm0.zero.tier2:id/gemaltoShortTutorialEnableBtn")));
//			MobileElement abilitaSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/gemaltoShortTutorialEnableBtn"));
//			abilitaSecurityToken.click();
//			wait.until(ExpectedConditions.elementToBeClickable(By
//			        .id("bm0.zero.tier2:id/insert_pin_entry")));
//			MobileElement insertPassCode = driver.findElement(By.id("bm0.zero.tier2:id/insert_pin_entry"));
//			insertPassCode.sendKeys("00000");
//			MobileElement sendPassCode = driver.findElement(By.id("bm0.zero.tier2:id/insert_pin_continue"));
//			sendPassCode.click();
//			//sett security token
//			wait.until(ExpectedConditions.elementToBeClickable(By
//			        .id("bm0.zero.tier2:id/security_token_login_text_entry")));
//			MobileElement insertSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/security_token_login_text_entry"));
//			insertSecurityToken.sendKeys("00000");
//			MobileElement sendSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/security_token_continue_btn"));
//			sendSecurityToken.click();
//			//codice troppo facile continuare
//			wait.until(ExpectedConditions.elementToBeClickable(By
//			        .id("bm0.zero.tier2:id/btn_dialog_ko")));
//			MobileElement tokenFacile = driver.findElement(By.id("bm0.zero.tier2:id/btn_dialog_ko"));
//			tokenFacile.click();
//			//sett security token
//			wait.until(ExpectedConditions.elementToBeClickable(By
//			        .id("bm0.zero.tier2:id/security_token_login_text_entry")));
//			insertSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/security_token_login_text_entry"));
//			insertSecurityToken.sendKeys("00000");
//			sendSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/security_token_continue_btn"));
//			sendSecurityToken.click();
//			
//			//step 7 skip finger print
//			wait.until(ExpectedConditions.elementToBeClickable(By
//					.id("bm0.zero.tier2:id/sca_use_biometric_not_now_btn")));
//			MobileElement skipFingerPrint = driver.findElement(By.id("bm0.zero.tier2:id/sca_use_biometric_not_now_btn"));
//			skipFingerPrint.click();
//			// security created
//			//step 7 skip finger print
//			wait.until(ExpectedConditions.elementToBeClickable(By
//					.id("bm0.zero.tier2:id/security_token_created_btn")));
//			MobileElement securityContinua = driver.findElement(By.id("bm0.zero.tier2:id/security_token_created_btn"));
//			securityContinua.click();
//		//}
//		
//		
//			
//			
//		//inizio ricarica
//		wait.until(ExpectedConditions.elementToBeClickable(By
//				.id("bm0.zero.tier2:id/adapterProfileBannerDescription")));
//		MobileElement cashIn = driver.findElement(By.id("bm0.zero.tier2:id/adapterProfileBannerDescription"));
//		cashIn.click();
//		
//
//		//clicca carta per ricaricare
//		wait.until(ExpectedConditions.elementToBeClickable(By
//				.id("bm0.zero.tier2:id/img_card_with_circle")));
//		MobileElement cartaPerRicarica = driver.findElement(By.id("bm0.zero.tier2:id/img_card_with_circle"));
//		cartaPerRicarica.click();
//		
//		//inserire importo da ricaricare
//		wait.until(ExpectedConditions.elementToBeClickable(By
//				.id("bm0.zero.tier2:id/inserted_value")));
//		MobileElement importoDaRicaricare = driver.findElement(By.id("bm0.zero.tier2:id/inserted_value"));
//		importoDaRicaricare.sendKeys("134");
//		MobileElement importoDaRicaricareNext = driver.findElement(By.id("bm0.zero.tier2:id/flowContinueBtn"));
//		importoDaRicaricareNext.click();
//		//popUp dati carte per ricarica
//		
//		wait.until(ExpectedConditions.elementToBeClickable(By
//		        .id("cards")));
//		MobileElement dropDownTipoCarta = driver.findElement(By.id("cards"));
//		dropDownTipoCarta.click();
//		wait.until(ExpectedConditions.elementToBeClickable(By
//		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]")));
//		MobileElement selectMatercard = driver.findElement(By.id("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]\""));
//		selectMatercard.click();
//		//nome Intestatario
//		wait.until(ExpectedConditions.elementToBeClickable(By
//		        .id("nameInputContainer2")));
//		MobileElement nomeIntestatario = driver.findElement(By.id("nameInputContainer2"));
//		nomeIntestatario.sendKeys("nome test");
//		
//		//numero carta
//		wait.until(ExpectedConditions.elementToBeClickable(By
//		        .id("cardNumber")));
//		MobileElement numeroCarta = driver.findElement(By.id("cardNumber"));
//		numeroCarta.sendKeys("5255000260000014");
//		
//		//scadenzamese
//		wait.until(ExpectedConditions.elementToBeClickable(By
//		        .id("monthList")));
//		MobileElement monthList = driver.findElement(By.id("monthList"));
//		monthList.click();
//		wait.until(ExpectedConditions.elementToBeClickable(By
//		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[7]")));
//		MobileElement monthListDropDown = driver.findElement(By.id("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[7]"));
//		monthListDropDown.click();
//		//scadenza anno
//		wait.until(ExpectedConditions.elementToBeClickable(By
//		        .id("yearList")));
//		MobileElement yearList = driver.findElement(By.id("yearList"));
//		yearList.click();
//		wait.until(ExpectedConditions.elementToBeClickable(By
//		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[5]")));
//		MobileElement yearListDropDown = driver.findElement(By.id("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[5]"));
//		yearListDropDown.click();
//		
//		//email
//		wait.until(ExpectedConditions.elementToBeClickable(By
//		        .id("email")));
//		MobileElement email = driver.findElement(By.id("email"));
//		email.sendKeys("eamila345x@gmails.net");
//		
//		//cvc
//		wait.until(ExpectedConditions.elementToBeClickable(By
//		        .id("cvc")));
//		MobileElement cvc = driver.findElement(By.id("cvc"));
//		cvc.sendKeys("111");
//		//acceptPrivacyInput
//		wait.until(ExpectedConditions.elementToBeClickable(By
//		        .id("acceptPrivacyInput")));
//		MobileElement acceptPrivacyInput = driver.findElement(By.id("acceptPrivacyInput"));
//		acceptPrivacyInput.click();
//		//confirm recharge
//		wait.until(ExpectedConditions.elementToBeClickable(By
//		        .id("confirm")));
//		MobileElement confirmRecharge = driver.findElement(By.id("confirm"));
//		confirmRecharge.click();
//		
//	
//	}
	

	

}
