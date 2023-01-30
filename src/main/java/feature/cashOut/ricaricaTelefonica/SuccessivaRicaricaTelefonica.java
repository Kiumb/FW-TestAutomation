package feature.cashOut.ricaricaTelefonica;

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
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import utils.ImportCSV;

public class SuccessivaRicaricaTelefonica {
	
	static AndroidDriver<MobileElement> driver;
	static HashMap<String, CellsMapper> dataFromCSV;
	private static final Logger logger = LogManager.getLogger(AppiumTest.class);
	
	
	@Test
	@Parameters("idTest")
	public void successiveRicariccheTelefonicche (String idTest) throws EncryptedDocumentException, IOException, InterruptedException {
		logger.info("Start Scenario - ricarica telefonica successiva");
		driver = Init.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, 40);
		//recupero dati dal csv
		ImportCSV importCsv = new ImportCSV();
		dataFromCSV = importCsv.getCSVRowDataByIdTest("RicaricaTelefonica" , idTest);
		//attendi che il saldo sia visibile
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/homeUserBalance")));
		MobileElement saldoDisponibile = driver.findElement(By.id("bm0.zero.tier2:id/userBalanceBox"));
		//saldoDisponibile.findElement(By.id("bbm0.zero.tier2:id/homeUserBalance")).getText();	
		//System.out.println(saldoDisponibile.findElement(By.id("bbm0.zero.tier2:id/homeUserBalance")).getText());
		
		//click Cash Out button
		//*[@id="screenshotContainer"]/div/div/div/div/div/div[85]
		//wait.until(ExpectedConditions.elementToBeClickable(By
		 //       .xpath("/html/body/div[1]/div/div/div/div[2]/div[1]/div/div/div/div/div/div[85]")));
		//MobileElement cashOut = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div[1]/div/div/div/div/div/div[85]"));
		TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(PointOption.point(800, 1620)).perform();

		//cashOut.click();
			
		//click Operazioni button
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.view.View[2]")));
		MobileElement operazioni = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.view.View[2]"));
		operazioni.click();	
		
		//click ricarica telefonica button
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/cashoutPhoneTopUpLayout")));
		MobileElement ricaricaTelefonica = driver.findElement(By.id("bm0.zero.tier2:id/cashoutPhoneTopUpLayout"));
		ricaricaTelefonica.click();	
		
		//click ricarica telefonica button
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/carrierVodafoneLayout")));
		MobileElement vodafoneButton = driver.findElement(By.id("bm0.zero.tier2:id/carrierVodafoneLayout"));
		vodafoneButton.click();	
		
		//click ricarica telefonica button
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/carrierContinueBtn")));
		MobileElement continueButton = driver.findElement(By.id("bm0.zero.tier2:id/carrierContinueBtn"));
		continueButton.click();	
		
		//**** Solo prima ricarica
		//permesso lettura contatti
//		wait.until(ExpectedConditions.elementToBeClickable(By
//		        .id("bm0.zero.tier2:id/btn_dialog_ok")));
//		MobileElement permessoLetturaContatti = driver.findElement(By.id("bm0.zero.tier2:id/btn_dialog_ok"));
//		permessoLetturaContatti.click();
//		
//		//permesso lettura contatti
//		wait.until(ExpectedConditions.elementToBeClickable(By
//		        .id("com.android.packageinstaller:id/permission_allow_button")));
//		MobileElement permessoAndroidLetturaContatti = driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
//		permessoAndroidLetturaContatti.click();	
//		
		
		//inserimento nuovo numero
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/phoneTopUpNewContact")));
		MobileElement insertNewNuber = driver.findElement(By.id("bm0.zero.tier2:id/phoneTopUpNewContact"));
		insertNewNuber.click();	
		
		//send nuovo numero
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/view_phone_number")));
		MobileElement sendNewNuber = driver.findElement(By.id("bm0.zero.tier2:id/view_phone_number"));
		sendNewNuber.sendKeys("3899918719");	
		
		//click continue
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/phoneTopUpNewNumberContinueBtn")));
		MobileElement continueBtn = driver.findElement(By.id("bm0.zero.tier2:id/phoneTopUpNewNumberContinueBtn"));
		continueBtn.click();	
		
		//click Importo ricarica
		System.out.println("** Ricarica telefonica - Scegli importo");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]")));
		MobileElement sceltaRicarica = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]"));
		sceltaRicarica.click();
		
		//click ricarica
		System.out.println("** Ricarica telefonica - Click ricarica");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/phoneTopUpSummaryCompleteBtn")));
		MobileElement clickRicarica = driver.findElement(By.id("bm0.zero.tier2:id/phoneTopUpSummaryCompleteBtn"));
		clickRicarica.click();
		
		Thread.sleep(3000);
		List<MobileElement> elementList = driver.findElements(By.id("bm0.zero.tier2:id/security_token_title"));
		if(elementList.size()>0){
			System.out.println("**token richiesto");
			ActionUtils.insertSecurityToken(driver);
		}
		else{
			System.out.println("token non richiesto");
		}
		
		WaitOptions.waitOptions(Duration.ofSeconds(3));
		
//		Thread.sleep(3000);
		
//		MobileElement elementx = driver.findElement(By.id("bm0.zero.tier2:id/security_token_login_text_entry"));
//
//	      if(elementx.isDisplayed())
//	      {
//	    	  System.out.println("**token richiesto");
//				ActionUtils.insertSecurityToken(driver);
//	      }
//	      else
//	      {
//	    	  System.out.println("token non richiesto");
//	      }
		
		
		
		
		
		
		
		//click ricarica
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/phoneTopUpCompletedHomeBtn")));
		MobileElement tornaAllaHomeClick = driver.findElement(By.id("bm0.zero.tier2:id/phoneTopUpCompletedHomeBtn"));
		tornaAllaHomeClick.click();
		
	}
	

}
