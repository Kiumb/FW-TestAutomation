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
import io.appium.java_client.MobileElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;

import utils.ImportCSV;

public class VerificaEAccettaRichiestaRicarica {

	static AndroidDriver<MobileElement> driver;
	static HashMap<String, CellsMapper> dataFromCSV;
	private static final Logger logger = LogManager.getLogger(VerificaEAccettaRichiestaRicarica.class);
	UtilsDriver utilDriver = new UtilsDriver();

	@Test
	@Parameters("idTest")
	public void verificaEAccettaRichiestaRicarica(String idTest) throws IOException, InterruptedException {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("Scenario ) Richiedi una ricarica ad un utente Flowe ");
		driver = Init.getDriver();

		// recupero dati dal csv
		ImportCSV importCsv = new ImportCSV();
		dataFromCSV = importCsv.getCSVRowDataByIdTest("RicaricaDaFlowe", idTest);
		WebDriverWait wait = new WebDriverWait(driver, 40);

		// attendi che il saldo sia visibile
		wait.until(ExpectedConditions.elementToBeClickable(By.id("bm0.zero.tier2:id/homeUserBalance")));
		utilDriver.takeScreenShotStep(methodName, "Notifica Di Ricarica","Mi aspetto di trovare la notifica di richiesta ricarica", driver);
		// Read Notifica
		logger.info("Verifica Richiesta Ricarica - Step: 1 - Action: Read < Notifica > ");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("bm0.zero.tier2:id/itemtitle")));
		MobileElement titoloNotificaRichiestaDiRicarica = driver.findElement(By.id("bm0.zero.tier2:id/itemtitle"));
		String titoloNotifica = titoloNotificaRichiestaDiRicarica.getAttribute("text");
		logger.info("Verifica Richiesta Ricarica - Result: Titolo = " + titoloNotifica);
		MobileElement messaggioNotificaRichiestaDiRicarica = driver
				.findElement(By.id("bm0.zero.tier2:id/itemdescription"));
		String testoNotifica = messaggioNotificaRichiestaDiRicarica.getAttribute("text");
		logger.info("Verifica Richiesta Ricarica - Result: Testo = " + testoNotifica);
		WaitOptions.waitOptions(Duration.ofSeconds(1));
		utilDriver.takeScreenShotStep(methodName, "Notifica Trovata","Titolo Notifica: " +
													titoloNotifica + " Contenuto: " +
													testoNotifica, driver);

		// Tap Notifica
		logger.info("Verifica Richiesta Ricarica - Step: 2 - Action: Tap < Notifica > ");
		titoloNotificaRichiestaDiRicarica.click();

		// Tap Notifica From list
		logger.info("Accetta Ricarica - Step: - Action: Tap < notifica from list > ");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.LinearLayout/android.widget.TextView[3]")));
		MobileElement notiFromListBtn = driver.findElement(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.LinearLayout/android.widget.TextView[3]"));
		notiFromListBtn.click();
		WaitOptions.waitOptions(Duration.ofSeconds(1));
		utilDriver.takeScreenShotStep(methodName, "Pagina ricarica","Visualizzo la notifica", driver);
		// Tap read mex
		logger.info("Accetta Ricarica - Step: - Wait: < schermata > ");
		// wait data operazione
		wait.until(ExpectedConditions.elementToBeClickable(By.id("bm0.zero.tier2:id/cashout_payment_request_date")));
		logger.info("Accetta Ricarica - read Message ");
		MobileElement messaggioNotifica = driver.findElement(By.id("bm0.zero.tier2:id/messageBox_fromMessage"));
		String mexNot = messaggioNotifica.getAttribute("text");
		logger.info("Accetta Ricarica - contenuto messaggio: " + mexNot);
		WaitOptions.waitOptions(Duration.ofSeconds(1));

		// Tap Accetta
		logger.info("Accetta Ricarica - Step: - Action: Tap < Accetta > ");
		wait.until(
				ExpectedConditions.elementToBeClickable(By.id("bm0.zero.tier2:id/cashout_payment_request_accept_btn")));
		MobileElement inviaBtn = driver.findElement(By.id("bm0.zero.tier2:id/cashout_payment_request_accept_btn"));
		utilDriver.takeScreenShotStep(methodName, "Click Accetta","accetto la richiesta", driver);
		inviaBtn.click();
		WaitOptions.waitOptions(Duration.ofSeconds(1));

		// Send messaggio risposta
		logger.info("Accetta Ricarica - Step: - Action: sendKeys < messaggio > ");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.id("bm0.zero.tier2:id/cashout_payment_request_response_message_edt")));
		MobileElement messaggioRichiesta = driver
				.findElement(By.id("bm0.zero.tier2:id/cashout_payment_request_response_message_edt"));
		messaggioRichiesta.sendKeys("Richiesta Accettata Automaticamente");
		WaitOptions.waitOptions(Duration.ofSeconds(1));

		// Tap emoticon
		logger.info("Accetta Ricarica - Step: - Action: Tap < emoticon > ");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.LinearLayout[3]/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[4]/android.widget.ImageView")));
		MobileElement emoticonBtn = driver.findElement(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.LinearLayout[3]/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[4]/android.widget.ImageView"));
		emoticonBtn.click();
		WaitOptions.waitOptions(Duration.ofSeconds(1));

		// Tap invia
		logger.info("Accetta Ricarica - Step: - Action: Tap < Invia > ");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.id("bm0.zero.tier2:id/cashout_payment_request_send_response_btn")));
		MobileElement inviaRicaricaBtn = driver
				.findElement(By.id("bm0.zero.tier2:id/cashout_payment_request_send_response_btn"));
		utilDriver.takeScreenShotStep(methodName, "Compilo La risposta","Compilo la risposta e confermo", driver);
		inviaRicaricaBtn.click();
		WaitOptions.waitOptions(Duration.ofSeconds(1));

		Thread.sleep(3000);
		List<MobileElement> elementList = driver.findElements(By.id("bm0.zero.tier2:id/security_token_title"));
		if (elementList.size() > 0) {
			utilDriver.takeScreenShotStep(methodName, "Token Richiesto","Schermata token intercettata", driver);
			System.out.println("**token richiesto");
			ActionUtils.insertSecurityToken(driver);
		} else {
			System.out.println("token non richiesto");
		}

		// Tap read mex
		logger.info("Accetta Ricarica - Step: - Wait: < schermata > ");
		// wait data operazione
		wait.until(ExpectedConditions.elementToBeClickable(By.id("bm0.zero.tier2:id/cashInConfirmConfirmMex")));
		logger.info("Accetta Ricarica - read Message result operation ");
		MobileElement messaggioDiRIsposta = driver.findElement(By.id("bm0.zero.tier2:id/cashInConfirmConfirmMex"));
		String mexConfirmNot = messaggioNotifica.getAttribute("text");
		utilDriver.takeScreenShotStep(methodName, "Ricarica Accettata","Ricarica accettata correttamente ResponseMex: " +mexConfirmNot, driver);
		logger.info("Accetta Ricarica - contenuto messaggio di conferma: " + mexConfirmNot);
		WaitOptions.waitOptions(Duration.ofSeconds(1));

		// Tap Torna Alla Home
		logger.info("Accetta Ricarica - Step: - Action: Tap < tornaAllaHomeBtn > ");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("bm0.zero.tier2:id/cashInConfirmHomeBtn")));
		MobileElement tornaAllaHomeBtn = driver.findElement(By.id("bm0.zero.tier2:id/cashInConfirmHomeBtn"));
		utilDriver.takeScreenShotStep(methodName, "Schermata Conferma ","Tap Confirm per tornare alla home", driver);
		logger.info("Accetta Ricarica: test terminato correttamente ***** ");
		tornaAllaHomeBtn.click();
		WaitOptions.waitOptions(Duration.ofSeconds(1));

		// attendi che il saldo sia visibile
		wait.until(ExpectedConditions.elementToBeClickable(By.id("bm0.zero.tier2:id/homeUserBalance")));
	}

}
