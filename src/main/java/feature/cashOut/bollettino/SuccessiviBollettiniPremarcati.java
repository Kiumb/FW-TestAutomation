package feature.cashOut.bollettino;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

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
import feature.Init;
import feature.actionUtils.ActionUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import utils.ImportCSV;

public class SuccessiviBollettiniPremarcati {
	static AndroidDriver<MobileElement> driver ;
	static HashMap<String, CellsMapper> dataFromCSV;
	private static final Logger logger = LogManager.getLogger(AppiumTest.class);
	
	@Test
	@Parameters("idTest")
	public String successivoBollettinoPremarcato (AndroidDriver<MobileElement> driver , String idTest) throws EncryptedDocumentException, IOException, InterruptedException {
		logger.info("Scenario ) Bollettino Premarcato telefonica ");
		
		driver = Init.getDriver();
		//recupero dati dal csv
		ImportCSV importCsv = new ImportCSV();
		dataFromCSV = importCsv.getCSVRowDataByIdTest("Bollettino" , idTest);
		System.out.println("Ricarica telefonica Started");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		
		//attendi che il saldo sia visibile
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/homeUserBalance")));
		MobileElement saldoDisponibile = driver.findElement(By.id("bm0.zero.tier2:id/userBalanceBox"));
		//saldoDisponibile.findElement(By.id("bbm0.zero.tier2:id/homeUserBalance")).getText();	
		//System.out.println(saldoDisponibile.findElement(By.id("bbm0.zero.tier2:id/homeUserBalance")).getText());
		
		//click Cash Out button
		TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(PointOption.point(800, 1620)).perform();

		//cashOut.click();
			
		//click Operazioni button
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.view.View[2]")));
		MobileElement operazioni = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.view.View[2]"));
		operazioni.click();	
		
		//click bollettino
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/giroCredit")));
		MobileElement btnBollettino = driver.findElement(By.id("bm0.zero.tier2:id/giroCredit"));
		btnBollettino.click();	
		
		//click Premarcato button
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/item_pre_marked")));
		MobileElement btnPremarcato = driver.findElement(By.id("bm0.zero.tier2:id/item_pre_marked"));
		btnPremarcato.click();	
		
		//solo primo bollettino
//		//click consenti foto/fotocamera
//		wait.until(ExpectedConditions.elementToBeClickable(By
//		        .id("com.android.packageinstaller:id/permission_allow_button")));
//		MobileElement btnOKPermissionCamer = driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
//		btnOKPermissionCamer.click();	
		
		//click Premarcato button
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/goToManuallyInsertBtn")));
		MobileElement btnInserisciAMano = driver.findElement(By.id("bm0.zero.tier2:id/goToManuallyInsertBtn"));
		btnInserisciAMano.click();	
		

		
		//FORM BOLLETTINO
		
		//CODICE IDENTIFICATIVO
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.EditText")));
		MobileElement sendCodiceIdentificativo = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.EditText"));
		sendCodiceIdentificativo.sendKeys("420093200623621607");	
		
		//CCN
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.EditText")));
		MobileElement sendCCN = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.EditText"));
		sendCCN.sendKeys("000000002006");	
		
		//TD
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/manual_bulletin_td")));
		MobileElement selectTD = driver.findElement(By.id("bm0.zero.tier2:id/manual_bulletin_td"));
		selectTD.click();	
		
		//TD CHOOSE 674
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]")));
		MobileElement choose674TD = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]"));
		choose674TD.click();	
		//TD CONFIRM 
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/td_select_btn")));
		MobileElement confirmTD = driver.findElement(By.id("bm0.zero.tier2:id/td_select_btn"));
		confirmTD.click();	
		
		//IMPORTO
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/manual_bulletin_amount")));
		MobileElement sendImport = driver.findElement(By.id("bm0.zero.tier2:id/manual_bulletin_amount"));
		sendImport.sendKeys("23");	
		
		//SWIPE DOWN PER GLI ALTRI CAMPI
		//move to down
		WaitOptions.waitOptions(Duration.ofSeconds(1));
		  driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Indirizzo\").instance(0))");

		
		//****campi precompilati
		//**** da svuotare prima di riempire
		/**
		//ESEGUITO DA
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup[3]/android.view.ViewGroup/android.widget.EditText")));
		MobileElement sendEseguitoDa = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup[3]/android.view.ViewGroup/android.widget.EditText"));
		sendEseguitoDa.sendKeys("Antonio Mangia Bot");	
		
		//CLICK INDIRIZZO 
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText")));
		MobileElement sendNewNuber = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText"));
		sendNewNuber.sendKeys("Indirizzo esecutore: Via Re David 114, Bari(BA), 70126, ITA");	
		
		
		//click Auth Posizione OK
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/androidx.cardview.widget.CardView/android.view.ViewGroup/android.widget.LinearLayout/android.widget.Button[1]")));
		MobileElement btnAuthPosizioneOK = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/androidx.cardview.widget.CardView/android.view.ViewGroup/android.widget.LinearLayout/android.widget.Button[1]"));
		btnAuthPosizioneOK.click();	
		
		//click Auth Posizione Android OK
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("com.android.packageinstaller:id/permission_allow_button")));
		MobileElement btnAuthPosizioneAndroidOK = driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
		btnAuthPosizioneAndroidOK.click();	
		
		//click secondo pulsante di ricarica
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/addressBook_search_text_cash_in")));
		MobileElement setPosizione = driver.findElement(By.id("bm0.zero.tier2:id/addressBook_search_text_cash_in"));
		setPosizione.sendKeys("Via Re David 114, Bari(BA), 70126, ITA");
		
		//click selectPosition
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.TextView[1]")));
		MobileElement clickSelectPosition = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.TextView[1]"));
		clickSelectPosition.click();
		
		**/
		
		//click continue 
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/manual_bulletin_continue")));
		MobileElement btnContinue = driver.findElement(By.id("bm0.zero.tier2:id/manual_bulletin_continue"));
		btnContinue.click();
		
		System.out.println("wait 500ms");
		WaitOptions.waitOptions(Duration.ofMillis(500));
		System.out.println("wait 1s");
		//move to down
		WaitOptions.waitOptions(Duration.ofSeconds(1));
		System.out.println("end wait 1s");
		//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Paga\").instance(0))");
		Thread.sleep(7000);
		
		//click Paga 541,1777
//		wait.until(ExpectedConditions.elementToBeClickable(By
//		        //.xpath("//android.widget.EditText[@text=\"Paga\"]")));
//				.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[7]/android.view.ViewGroup/android.widget.EditText")));
//		MobileElement btnpaga = driver.findElement(By
//				//.xpath("//android.widget.EditText[@text=\"Paga\"]"));
//				.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[7]/android.view.ViewGroup/android.widget.EditText"));
//		btnpaga.click();
		TouchAction touchPaga = new TouchAction(driver);
		touchPaga.tap(PointOption.point(541, 1777)).perform();
		
		//click Pagamento effettuato
//		wait.until(ExpectedConditions.elementToBeClickable(By
//		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[7]/android.view.ViewGroup/android.widget.EditText")));
//		MobileElement pagamentoEffettuato = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[7]/android.view.ViewGroup/android.widget.EditText"));
//		pagamentoEffettuato.click();
		
		Thread.sleep(3000);
		List<MobileElement> elementList = driver.findElements(By.id("bm0.zero.tier2:id/security_token_title"));
		if(elementList.size()>0){
			System.out.println("**token richiesto");
			ActionUtils.insertSecurityToken(driver);
		}
		else{
			System.out.println("token non richiesto");
		}
		
		
		
		//torna alla home 
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/bulletin_completed_secondary")));
		MobileElement btnTornaAllaHome = driver.findElement(By.id("bm0.zero.tier2:id/bulletin_completed_secondary"));
		btnTornaAllaHome.click();
		
		return null;
		
	}
}
