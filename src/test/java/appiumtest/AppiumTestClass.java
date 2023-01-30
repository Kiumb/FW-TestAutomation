package appiumtest;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumTestClass {

	
	static AppiumDriver<MobileElement> driver;
	//AndroidDriver driverAndroid;
	
	public static void main(String[] args) {
		
		try {
			//esegui il login
			loginFlowe();
			//effettua una ricarica
			//rechargeFlowe();
			//rechargeFloweTokenImpostato();
			
		} catch (Exception exp) {
			System.out.println(exp.getCause());
			System.out.println(exp.getMessage());	
			exp.printStackTrace();
		}
		
	} 
	public static void loginFlowe() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		
		//configurazione capabilities 
		//DALL'IMPOSTAZIONI DEL CELL
		cap.setCapability("deviceName", "SM-A510F");
		//cap.setCapability("deviceName", "GALAXY a5(2016)");
		//DA TERMINALE ADB : adb devices
		cap.setCapability("udid", "33001164d113a25d");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersions", "7.0");
		
		//impostazioni app
		System.out.println("Applicazione da testare : bm0.zero.tier2");
		cap.setCapability("appPackage", "bm0.zero.tier2");
		System.out.println("Applicazione da testare : bm0.zero.tier2.app.ZeroSplashActivity");
		cap.setCapability("appActivity", "bm0.zero.tier2.app.ZeroSplashActivity");
		//activity provate e non fuznionanti
		//cap.setCapability("appActivity", "bm0.zero.tier2.app.ZeroMainActivity");
		//funziona con la calcolatrice
//		cap.setCapability("appPackage", "com.sec.android.app.popupcalculator");
//		cap.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");	 
		driver = new AppiumDriver<MobileElement>(url , cap);
		
		System.out.println("Application Started");
		
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//	    wait.until(ExpectedConditions.textToBePresentInElement((WebElement) By.xpath("//*[@text='Ho già']"), "Ho già"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
	
		//step 1 click ho gia un account (login)
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/btn_welcome_login")));
		System.out.println("step 1) click ho gia un account");
		MobileElement loginIn = driver.findElement(By.id("bm0.zero.tier2:id/btn_welcome_login"));
		loginIn.click();
		
//		wait.until(ExpectedConditions.elementToBeClickable(By
//		        .xpath("//android.widget.Button[contains(@text, 'OK')]")));
		
		//step 2 click ok sulla modale OTP
		wait.until(ExpectedConditions.elementToBeClickable(By.id("bm0.zero.tier2:id/btn_dialog_ok")));
		System.out.println("step 2) click ok sulla modale OTP");
		MobileElement otpPermission = driver.findElement(By.id("bm0.zero.tier2:id/btn_dialog_ok"));
		otpPermission.click();

		//step 3 click ok sulla modale permesso android SMS
		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("com.android.packageinstaller:id/permission_allow_button")));
		System.out.println("step 3) click ok sulla modale permesso android SMS");
		MobileElement smsPermission = driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
		smsPermission.click();
		
		//step 4 inserisci il numero di telefono 
		wait.until(ExpectedConditions.elementToBeClickable(By
						.id("bm0.zero.tier2:id/view_phone_number")));
		System.out.println("step 4) inserisci il numero di telefono ");
		MobileElement phoneNumber = driver.findElement(By.id("bm0.zero.tier2:id/view_phone_number"));
		phoneNumber.sendKeys("1111110047");
		//step 4.1 send il numero di telefono 		
		System.out.println("step 4.1) send il numero di telefono ");
		MobileElement sendPhoneNumber = driver.findElement(By.id("bm0.zero.tier2:id/btn_phone_number_continue"));
		sendPhoneNumber.click();
		
		//step 5 inserisci il passcode
		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("bm0.zero.tier2:id/login_text_entry")));
		System.out.println("step 5) inserisci il passcode");
		MobileElement passCode = driver.findElement(By.id("bm0.zero.tier2:id/login_text_entry"));
		passCode.sendKeys("00000");
		
		//step 6 il codice OTP ricevuto via SMS
		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("bm0.zero.tier2:id/otpPinEntry")));
		System.out.println("step 6) il codice OTP ricevuto via SMS");
		MobileElement smsCode = driver.findElement(By.id("bm0.zero.tier2:id/otpPinEntry"));
		smsCode.sendKeys("123456");
		//step 6.1 send il codice OTP ricevuto via SMS
		System.out.println("step 6.1) send OTP ricevuto via SMS");
		MobileElement sendSmsCode = driver.findElement(By.id("bm0.zero.tier2:id/btn_next"));
		sendSmsCode.click();
		//step 7 skip finger print
		wait.until(ExpectedConditions.elementToBeClickable(By
						.id("bm0.zero.tier2:id/btn_biometric_notnow")));
		System.out.println("step 7) skip finger print");
		MobileElement skipFingerPrint = driver.findElement(By.id("bm0.zero.tier2:id/btn_biometric_notnow"));
		skipFingerPrint.click();
		
		//WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("//android.widget.TextView[contains(@text, 'Security token')]")));
		System.out.println("step 8) skip banner");
		MobileElement nextSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/dialog_gemalto_tutorial_right_btn"));
		nextSecurityToken.click();
		nextSecurityToken.click();
		nextSecurityToken.click();
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/gemaltoShortTutorialEnableBtn")));
			MobileElement abilitaSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/gemaltoShortTutorialEnableBtn"));
			abilitaSecurityToken.click();
			wait.until(ExpectedConditions.elementToBeClickable(By
			        .id("bm0.zero.tier2:id/insert_pin_entry")));
			MobileElement insertPassCode = driver.findElement(By.id("bm0.zero.tier2:id/insert_pin_entry"));
			insertPassCode.sendKeys("00000");
			MobileElement sendPassCode = driver.findElement(By.id("bm0.zero.tier2:id/insert_pin_continue"));
			sendPassCode.click();
			//sett security token
			wait.until(ExpectedConditions.elementToBeClickable(By
			        .id("bm0.zero.tier2:id/security_token_login_text_entry")));
			MobileElement insertSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/security_token_login_text_entry"));
			insertSecurityToken.sendKeys("00000");
			MobileElement sendSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/security_token_continue_btn"));
			sendSecurityToken.click();
			//codice troppo facile continuare
			wait.until(ExpectedConditions.elementToBeClickable(By
			        .id("bm0.zero.tier2:id/btn_dialog_ko")));
			MobileElement tokenFacile = driver.findElement(By.id("bm0.zero.tier2:id/btn_dialog_ko"));
			tokenFacile.click();
			//sett security token
			wait.until(ExpectedConditions.elementToBeClickable(By
			        .id("bm0.zero.tier2:id/security_token_login_text_entry")));
			insertSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/security_token_login_text_entry"));
			insertSecurityToken.sendKeys("00000");
			sendSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/security_token_continue_btn"));
			sendSecurityToken.click();
			
			//step 7 skip finger print
			wait.until(ExpectedConditions.elementToBeClickable(By
					.id("bm0.zero.tier2:id/sca_use_biometric_not_now_btn")));
			skipFingerPrint = driver.findElement(By.id("bm0.zero.tier2:id/sca_use_biometric_not_now_btn"));
			skipFingerPrint.click();
			// security created
			//step 7 skip finger print
			wait.until(ExpectedConditions.elementToBeClickable(By
					.id("bm0.zero.tier2:id/security_token_created_btn")));
			MobileElement securityContinua = driver.findElement(By.id("bm0.zero.tier2:id/security_token_created_btn"));
			securityContinua.click();
	}
	
	public static void rechargeFlowe() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		
		//configurazione capabilities 
		//DALL'IMPOSTAZIONI DEL CELL
		cap.setCapability("deviceName", "SM-A510F");

		//DA TERMINALE ADB : adb devices
		cap.setCapability("udid", "33001164d113a25d");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersions", "7.0");
		//not clear cache 
		cap.setCapability("noReset", "true");
		cap.setCapability("fullReset", "false");
		
		//impostazioni app
		cap.setCapability("appPackage", "bm0.zero.tier2");
		cap.setCapability("appActivity", "bm0.zero.tier2.app.ZeroSplashActivity");
	
		URL url = new URL("http://127.0.0.1:4723/wd/hub");	 
		driver = new AppiumDriver<MobileElement>(url , cap);
		
		System.out.println("Application Started");

		WebDriverWait wait = new WebDriverWait(driver, 30);
	
		//step 1 click ho gia un account (login)
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/login_text_entry")));
		MobileElement passCode = driver.findElement(By.id("bm0.zero.tier2:id/login_text_entry"));
		passCode.sendKeys("00000");
		
		//step 2 Security token  
		//if (driver.findElements( By.id("bm0.zero.tier2:id/gemaltoShortTutorialEnableBtn") ).size() != 0) {
			wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/gemaltoShortTutorialEnableBtn")));
			MobileElement abilitaSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/gemaltoShortTutorialEnableBtn"));
			abilitaSecurityToken.click();
			wait.until(ExpectedConditions.elementToBeClickable(By
			        .id("bm0.zero.tier2:id/insert_pin_entry")));
			MobileElement insertPassCode = driver.findElement(By.id("bm0.zero.tier2:id/insert_pin_entry"));
			insertPassCode.sendKeys("00000");
			MobileElement sendPassCode = driver.findElement(By.id("bm0.zero.tier2:id/insert_pin_continue"));
			sendPassCode.click();
			//sett security token
			wait.until(ExpectedConditions.elementToBeClickable(By
			        .id("bm0.zero.tier2:id/security_token_login_text_entry")));
			MobileElement insertSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/security_token_login_text_entry"));
			insertSecurityToken.sendKeys("00000");
			MobileElement sendSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/security_token_continue_btn"));
			sendSecurityToken.click();
			//codice troppo facile continuare
			wait.until(ExpectedConditions.elementToBeClickable(By
			        .id("bm0.zero.tier2:id/btn_dialog_ko")));
			MobileElement tokenFacile = driver.findElement(By.id("bm0.zero.tier2:id/btn_dialog_ko"));
			tokenFacile.click();
			//sett security token
			wait.until(ExpectedConditions.elementToBeClickable(By
			        .id("bm0.zero.tier2:id/security_token_login_text_entry")));
			insertSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/security_token_login_text_entry"));
			insertSecurityToken.sendKeys("00000");
			sendSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/security_token_continue_btn"));
			sendSecurityToken.click();
			
			//step 7 skip finger print
			wait.until(ExpectedConditions.elementToBeClickable(By
					.id("bm0.zero.tier2:id/sca_use_biometric_not_now_btn")));
			MobileElement skipFingerPrint = driver.findElement(By.id("bm0.zero.tier2:id/sca_use_biometric_not_now_btn"));
			skipFingerPrint.click();
			// security created
			//step 7 skip finger print
			wait.until(ExpectedConditions.elementToBeClickable(By
					.id("bm0.zero.tier2:id/security_token_created_btn")));
			MobileElement securityContinua = driver.findElement(By.id("bm0.zero.tier2:id/security_token_created_btn"));
			securityContinua.click();
		//}
		
		
			
			
		//inizio ricarica
		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("bm0.zero.tier2:id/adapterProfileBannerDescription")));
		MobileElement cashIn = driver.findElement(By.id("bm0.zero.tier2:id/adapterProfileBannerDescription"));
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
		importoDaRicaricare.sendKeys("134");
		MobileElement importoDaRicaricareNext = driver.findElement(By.id("bm0.zero.tier2:id/flowContinueBtn"));
		importoDaRicaricareNext.click();
		//popUp dati carte per ricarica
		
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("cards")));
		MobileElement dropDownTipoCarta = driver.findElement(By.id("cards"));
		dropDownTipoCarta.click();
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]")));
		MobileElement selectMatercard = driver.findElement(By.id("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]\""));
		selectMatercard.click();
		//nome Intestatario
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("nameInputContainer2")));
		MobileElement nomeIntestatario = driver.findElement(By.id("nameInputContainer2"));
		nomeIntestatario.sendKeys("nome test");
		
		//numero carta
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("cardNumber")));
		MobileElement numeroCarta = driver.findElement(By.id("cardNumber"));
		numeroCarta.sendKeys("5255000260000014");
		
		//scadenzamese
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("monthList")));
		MobileElement monthList = driver.findElement(By.id("monthList"));
		monthList.click();
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[7]")));
		MobileElement monthListDropDown = driver.findElement(By.id("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[7]"));
		monthListDropDown.click();
		//scadenza anno
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("yearList")));
		MobileElement yearList = driver.findElement(By.id("yearList"));
		yearList.click();
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[5]")));
		MobileElement yearListDropDown = driver.findElement(By.id("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[5]"));
		yearListDropDown.click();
		
		//email
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("email")));
		MobileElement email = driver.findElement(By.id("email"));
		email.sendKeys("eamila345x@gmails.net");
		
		//cvc
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("cvc")));
		MobileElement cvc = driver.findElement(By.id("cvc"));
		cvc.sendKeys("111");
		//acceptPrivacyInput
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("acceptPrivacyInput")));
		MobileElement acceptPrivacyInput = driver.findElement(By.id("acceptPrivacyInput"));
		acceptPrivacyInput.click();
		//confirm recharge
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("confirm")));
		MobileElement confirmRecharge = driver.findElement(By.id("confirm"));
		confirmRecharge.click();
		
		
	
		
		
	
	}
	
	public static void rechargeFloweTokenImpostato() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		
		//configurazione capabilities 
		//DALL'IMPOSTAZIONI DEL CELL
		cap.setCapability("deviceName", "SM-A510F");

		//DA TERMINALE ADB : adb devices
		cap.setCapability("udid", "33001164d113a25d");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersions", "7.0");
		//not clear cache 
		cap.setCapability("noReset", "true");
		cap.setCapability("fullReset", "false");
		
		//impostazioni app
		cap.setCapability("appPackage", "bm0.zero.tier2");
		cap.setCapability("appActivity", "bm0.zero.tier2.app.ZeroSplashActivity");
	
		URL url = new URL("http://127.0.0.1:4723/wd/hub");	 
		driver = new AppiumDriver<MobileElement>(url , cap);
		
		System.out.println("Application Started");

		WebDriverWait wait = new WebDriverWait(driver, 30);
	
		//step 1 click ho gia un account (login)
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/login_text_entry")));
		MobileElement passCode = driver.findElement(By.id("bm0.zero.tier2:id/login_text_entry"));
		passCode.sendKeys("00000");
		
		//step 2 Security token  
			
			
		//inizio ricarica
		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("bm0.zero.tier2:id/adapterProfileBannerDescription")));
		MobileElement cashIn = driver.findElement(By.id("bm0.zero.tier2:id/adapterProfileBannerDescription"));
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
		importoDaRicaricare.sendKeys("134");
		MobileElement importoDaRicaricareNext = driver.findElement(By.id("bm0.zero.tier2:id/flowContinueBtn"));
		importoDaRicaricareNext.click();
		//popUp dati carte per ricarica
		
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("cards")));
		MobileElement dropDownTipoCarta = driver.findElement(By.id("cards"));
		dropDownTipoCarta.click();
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]")));
		MobileElement selectMatercard = driver.findElement(By.id("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]\""));
		selectMatercard.click();
		//nome Intestatario
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("nameInputContainer2")));
		MobileElement nomeIntestatario = driver.findElement(By.id("nameInputContainer2"));
		nomeIntestatario.sendKeys("nome test");
		
		//numero carta
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("cardNumber")));
		MobileElement numeroCarta = driver.findElement(By.id("cardNumber"));
		numeroCarta.sendKeys("5255000260000014");
		
		//scadenzamese
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("monthList")));
		MobileElement monthList = driver.findElement(By.id("monthList"));
		monthList.click();
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[7]")));
		MobileElement monthListDropDown = driver.findElement(By.id("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[7]"));
		monthListDropDown.click();
		//scadenza anno
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("yearList")));
		MobileElement yearList = driver.findElement(By.id("yearList"));
		yearList.click();
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[5]")));
		MobileElement yearListDropDown = driver.findElement(By.id("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[5]"));
		yearListDropDown.click();
		
		//email
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("email")));
		MobileElement email = driver.findElement(By.id("email"));
		email.sendKeys("eamila345x@gmails.net");
		
		//cvc
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("cvc")));
		MobileElement cvc = driver.findElement(By.id("cvc"));
		cvc.sendKeys("111");
		//acceptPrivacyInput
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("acceptPrivacyInput")));
		MobileElement acceptPrivacyInput = driver.findElement(By.id("acceptPrivacyInput"));
		acceptPrivacyInput.click();
		//confirm recharge
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("confirm")));
		MobileElement confirmRecharge = driver.findElement(By.id("confirm"));
		confirmRecharge.click();
	}
}
