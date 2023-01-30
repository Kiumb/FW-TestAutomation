package feature.cashIn;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import appiumtest.AppiumTest;
import data.CellsMapper;
import feature.Init;
import io.appium.java_client.MobileElement;
import io.appium.java_client.touch.WaitOptions;
import utils.ImportCSV;
import io.appium.java_client.android.AndroidDriver;


public class RicaricaContoConCartaDiCred {
	
	static HashMap<String, CellsMapper> dataFromCSV;
	private static final Logger logger = LogManager.getLogger(AppiumTest.class);
	private static AndroidDriver<MobileElement> driver;
	
	@Test
	@Parameters("idTest")
	public static void rechargeFloweTokenImpostato(String idTest) throws EncryptedDocumentException, IOException {
		logger.info("Scenario ) Ricarica Conto ");
		driver = Init.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, 40);
		//recupero dati dal csv
		ImportCSV importCsv = new ImportCSV();
		dataFromCSV = importCsv.getCSVRowDataByIdTest("RicaricaConto" , idTest);

		
		System.out.println("Ricarica conto Started");
		
		//step 2 Security token  
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
		WaitOptions.waitOptions(Duration.ofSeconds(1));
			
		//inizio ricarica
		//wait.until(ExpectedConditions.elementToBeClickable(By
			//	.id("bm0.zero.tier2:id/adapterProfileBannerDescription")));
		MobileElement cashIn = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Cash In\").instance(0))");
				//driver.findElement(By.id("bm0.zero.tier2:id/adapterProfileBannerDescription"));
		cashIn.click();
		

		//clicca carta per ricaricare
		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("bm0.zero.tier2:id/img_card_with_circle")));
		MobileElement cartaPerRicarica = driver.findElement(By.id("bm0.zero.tier2:id/img_card_with_circle"));
		cartaPerRicarica.click();
		
		//inserire importo da ricaricare
		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("bm0.zero.tier2:id/inserted_value")));
		MobileElement importoDaRicaricare = driver.findElement(By.id("bm0.zero.tier2:id/inserted_value"));
		importoDaRicaricare.sendKeys(dataFromCSV.get("0").getValore());
		MobileElement importoDaRicaricareNext = driver.findElement(By.id("bm0.zero.tier2:id/flowContinueBtn"));
		importoDaRicaricareNext.click();
		//popUp dati carte per ricarica
		
		//Swipe bottom
			Dimension size;
		  //Get the size of screen.
		  size = driver.manage().window().getSize();
		  System.out.println(size);

		  //Swipe from Bottom to Top.
//		  TouchAction swipe = new TouchAction(driver);
//		  swipe.press(PointOption.point(624,1227));
//		  swipe.moveTo(PointOption.point(632,491));
//		  swipe.release();
//		  swipe.perform();
//		  driver.performTouchAction(swipe);
		 // wait.until(ExpectedConditions.elementToBeClickable(By.id("cardButton")));
		  WaitOptions.waitOptions(Duration.ofSeconds(1));
		  driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"EMAIL\").instance(0))");
 
	
		  WaitOptions.waitOptions(Duration.ofSeconds(1));
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]")));
		MobileElement dropDownTipoCarta = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]"));
		dropDownTipoCarta.click();
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]")));
		MobileElement selectMatercard = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]"));
		selectMatercard.click();
		//nome Intestatario
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[4]/android.widget.EditText")));
		MobileElement nomeIntestatario = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[4]/android.widget.EditText"));
		nomeIntestatario.sendKeys(dataFromCSV.get("3").getValore());
		
		//numero carta
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[6]/android.widget.EditText")));
		MobileElement numeroCarta = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[6]/android.widget.EditText"));
		numeroCarta.sendKeys(dataFromCSV.get("1").getValore());
		
		//scadenzamese
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[8]/android.view.View[1]")));
		MobileElement monthList = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[8]/android.view.View[1]"));
		monthList.click();
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[7]")));
		MobileElement monthListDropDown = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[7]"));
		monthListDropDown.click();
		//scadenza anno
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[8]/android.view.View[2]")));
		MobileElement yearList = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[8]/android.view.View[2]"));
		yearList.click();
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[5]")));
		MobileElement yearListDropDown = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[5]"));
		yearListDropDown.click();
		
		//move to down
		WaitOptions.waitOptions(Duration.ofSeconds(1));
		  driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Conferma\").instance(0))");

		
		
		//email
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[2]/android.widget.EditText")));
		MobileElement email = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[2]/android.widget.EditText"));
		email.sendKeys(dataFromCSV.get("4").getValore());
		
		//cvc
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[4]/android.widget.EditText")));
		MobileElement cvc = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[4]/android.widget.EditText"));
		cvc.sendKeys(dataFromCSV.get("2").getValore());
		//acceptPrivacyInput
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.widget.CheckBox")));
		MobileElement acceptPrivacyInput = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.widget.CheckBox"));
		acceptPrivacyInput.click();
		//confirm recharge
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.widget.Button[2]")));
		MobileElement confirmRecharge = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.widget.Button[2]"));
		confirmRecharge.click();
		
		//schermata di successo della ricarica 
		//confirm recharge
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.Button")));
		MobileElement okBackToHome = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.Button"));
		okBackToHome.click();
		
		System.out.println("Fine RicaricaConto");
		
	}

}
