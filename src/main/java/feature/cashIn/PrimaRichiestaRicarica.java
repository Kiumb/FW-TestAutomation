package feature.cashIn;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import appiumtest.AppiumTest;
import data.CellsMapper;
import driverManager.UtilsDriver;
import feature.Init;
import feature.actionUtils.ActionUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import utils.ImportCSV;

public class PrimaRichiestaRicarica {
	static AndroidDriver<MobileElement> driver;
	static HashMap<String, CellsMapper> dataFromCSV;
	private static final Logger logger = LogManager.getLogger(AppiumTest.class);
	UtilsDriver utilDriver = new UtilsDriver();
	
	@Test
	@Parameters("idTest")
	public void primaRichiestaRicarica (String idTest) throws  IOException {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		
		logger.info("Scenario ) Richiedi una ricarica ad un utente Flowe ");
		driver = Init.getDriver();
			
		//recupero dati dal csv
		ImportCSV importCsv = new ImportCSV();
		dataFromCSV = importCsv.getCSVRowDataByIdTest("RicaricaDaFlowe" , idTest);
		//System.out.println("Ricarica telefonica Started");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		
		//attendi che il saldo sia visibile
		logger.info("Richiedi Ricarica: Wait - HomeUserBalance ");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/homeUserBalance")));
		
		//click money button
		logger.info("Richiedi Ricarica: Tap - Money ");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/moneyFragment")));
		MobileElement moneyButton = driver.findElement(By.id("bm0.zero.tier2:id/moneyFragment"));
		moneyButton.click();
		utilDriver.takeScreenShotStep(methodName,"GoTo Money" ,"Jump to Money", driver);
		
		//click home button
		logger.info("Richiedi Ricarica: Tap - Home ");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/homeFragment")));
		MobileElement homeButton = driver.findElement(By.id("bm0.zero.tier2:id/homeFragment"));
		homeButton.click();	
		WaitOptions.waitOptions(Duration.ofSeconds(1));
		utilDriver.takeScreenShotStep(methodName,"GoTo Home" ,"Jump to Home", driver);
			
		//Tap Cash In 
		logger.info("Richiedi Ricarica- Step: - Action: Tap < CashIn > ");
		//wait.until(ExpectedConditions.elementToBeClickable(By
			//	.id("bm0.zero.tier2:id/adapterProfileBannerDescription")));
		MobileElement cashIn = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Cash In\").instance(0))");
				//driver.findElement(By.id("bm0.zero.tier2:id/adapterProfileBannerDescription"));
		cashIn.click();
		utilDriver.takeScreenShotStep(methodName,"GoTo CashIn" ,"CashIn", driver);
		
		
		//Tap Richiedi
		logger.info("Richiedi Ricarica - Step: - Action: Tap < Richiedi > ");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.view.View[2]")));
		MobileElement richiediButton = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.view.View[2]"));
		richiediButton.click();	
		WaitOptions.waitOptions(Duration.ofSeconds(1));
		utilDriver.takeScreenShotStep(methodName,"Page CashIn" ,"Tap Richiedi", driver);
		
		
		//Tap Sincronizza
		logger.info("Richiedi Ricarica - Step: - Action: Tap < Sincronizza > ");
		TouchAction touchActionSincronizza = new TouchAction(driver);
		touchActionSincronizza.press(PointOption.point(531, 1668));
		
		touchActionSincronizza.press(PointOption.point(531, 1668)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).release().perform();
		utilDriver.takeScreenShotStep(methodName,"Permessi Rubrica" ,"Tap Sincronizza Rubrica", driver);
		//tap(PointOption.point(531, 1668)).perform();
		WaitOptions.waitOptions(Duration.ofSeconds(1));
		
		//SOLO PER LA PRIMA RICHIESTA DI RICARICA
		//Tap OK modale permessi 
		logger.info("Richiedi Ricarica - Step: - Action: Tap < OK > ");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/androidx.cardview.widget.CardView/android.view.ViewGroup/android.widget.LinearLayout/android.widget.Button[1]")));
		MobileElement okPermissionButton = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/androidx.cardview.widget.CardView/android.view.ViewGroup/android.widget.LinearLayout/android.widget.Button[1]"));
		okPermissionButton.click();	
		utilDriver.takeScreenShotStep(methodName,"Permission App" ,"Tap OK", driver);
		WaitOptions.waitOptions(Duration.ofSeconds(1));
		
		//Tap OK modale permessi android
		logger.info("Richiedi Ricarica - Step: - Action: Tap < OK > ");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("com.android.packageinstaller:id/permission_allow_button")));
		MobileElement okPermAndroidButton = driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
		okPermAndroidButton.click();	
		utilDriver.takeScreenShotStep(methodName,"Permission Android" ,"Tap OK", driver);
		WaitOptions.waitOptions(Duration.ofSeconds(1));
		
		//Tap Numero a cui chiedere la ricarica 
		logger.info("Richiedi Ricarica - Step: - Action: Tap < NumeroDaLista[2] > ");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout")));
		MobileElement utenteDaCliccare = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout"));
		utenteDaCliccare.click();	
		utilDriver.takeScreenShotStep(methodName,"Seleziono il numero" ,"Seleziono il secondo numero in rubrica", driver);
		WaitOptions.waitOptions(Duration.ofSeconds(1));
		
		//Send Importo da ricaricare
		logger.info("Richiedi Ricarica - Step: - Action: sendKeys < 123 > ");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/inserted_value")));
		MobileElement importoDaRicaricare = driver.findElement(By.id("bm0.zero.tier2:id/inserted_value"));
		importoDaRicaricare.sendKeys("26");	
		
		WaitOptions.waitOptions(Duration.ofSeconds(1));
		
		//Tap Continue
		logger.info("Richiedi Ricarica - Step: - Action: Tap < NumeroDaLista[2] > ");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/flowContinueBtn")));
		MobileElement continueBtn = driver.findElement(By.id("bm0.zero.tier2:id/flowContinueBtn"));
		continueBtn.click();	
		utilDriver.takeScreenShotStep(methodName,"Importo da ricaricare" ,"inserisco 26 come importo", driver);
		WaitOptions.waitOptions(Duration.ofSeconds(1));
		
		
		//Send messaggio richiesta
		logger.info("Richiedi Ricarica - Step: - Action: sendKeys < messaggio > ");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/view_message_edt")));
		MobileElement messaggioRichiesta = driver.findElement(By.id("bm0.zero.tier2:id/view_message_edt"));
		messaggioRichiesta.sendKeys("Ciao test automatizzato riuscito");	
		WaitOptions.waitOptions(Duration.ofSeconds(1));
		
		//Tap emoticon
		logger.info("Richiedi Ricarica - Step: - Action: Tap < emoticon > ");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[4]/android.widget.ImageView")));
		MobileElement emoticonBtn = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[4]/android.widget.ImageView"));
		emoticonBtn.click();	
		WaitOptions.waitOptions(Duration.ofSeconds(1));
		
		//Tap invia
		logger.info("Richiedi Ricarica - Step: - Action: Tap < Invia > ");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/cashInRequestBtn")));
		MobileElement inviaBtn = driver.findElement(By.id("bm0.zero.tier2:id/cashInRequestBtn"));
		utilDriver.takeScreenShotStep(methodName,"Schermata di ricarica" ,"inserisco il messaggio e l'icona , tap continua", driver);
		inviaBtn.click();	
		WaitOptions.waitOptions(Duration.ofSeconds(1));
		
		
		//Tap Torna Alla Home
		logger.info("Richiedi Ricarica - Step: - Action: Tap < tornaAllaHomeBtn > ");
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/cashInConfirmHomeBtn")));
		MobileElement tornaAllaHomeBtn = driver.findElement(By.id("bm0.zero.tier2:id/cashInConfirmHomeBtn"));
		logger.info("Richiedi Ricarica: test terminato correttamente ***** ");
		utilDriver.takeScreenShotStep(methodName,"Messaggio di conferma" ,"Tap Torna Alla Home", driver);
		tornaAllaHomeBtn.click();	
		WaitOptions.waitOptions(Duration.ofSeconds(1));
		
		//attendi che il saldo sia visibile
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/homeUserBalance")));

		
	}
	
	
}
