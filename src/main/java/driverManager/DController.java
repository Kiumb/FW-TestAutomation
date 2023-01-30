package driverManager;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.remote.DesiredCapabilities;

import appiumtest.AppiumTest;
import data.CellsMapper;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import utils.ImportCSV;

public class DController {
	
	static AndroidDriver<MobileElement> driver;
	//AndroidDriver driverAndroid;
	static HashMap<String, CellsMapper> dataFromCSV;
	private static final Logger logger = LogManager.getLogger(DController.class);
	
	public AndroidDriver<MobileElement> startDriverClearCache(String idTest) throws EncryptedDocumentException, IOException {
		

		
		ImportCSV importCsv = new ImportCSV();
		dataFromCSV = importCsv.getCSVRowDataByIdTest("Login",idTest);
		
		DesiredCapabilities cap = new DesiredCapabilities();
		
		//configurazione capabilities 
		//DALL'IMPOSTAZIONI DEL CELL
		//cap.setCapability("deviceName", "SM-A510F");
		cap.setCapability("deviceName", dataFromCSV.get("1").getValore());
		//DA TERMINALE ADB : adb devices
//		cap.setCapability("udid", "33001164d113a25d");
//		cap.setCapability("platformName", "Android");
//		cap.setCapability("platformVersions", "7.0");
		cap.setCapability("udid", dataFromCSV.get("2").getValore());
		cap.setCapability("platformName", dataFromCSV.get("3").getValore());
		cap.setCapability("platformVersions", dataFromCSV.get("4").getValore());
		
		//impostazioni app
		System.out.println("Applicazione da testare :"+dataFromCSV.get("5").getValore());
		cap.setCapability("appPackage", dataFromCSV.get("5").getValore());
		System.out.println("Applicazione da testare : "+dataFromCSV.get("6").getValore());
		cap.setCapability("appActivity", dataFromCSV.get("6").getValore());
		//activity provate e non fuznionanti
		//cap.setCapability("appActivity", "bm0.zero.tier2.app.ZeroMainActivity");
		//funziona con la calcolatrice
//		cap.setCapability("appPackage", "com.sec.android.app.popupcalculator");
//		cap.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");
		
		//URL url = new URL(http://127.0.0.1:4723/wd/hub);
		logger.info("URL da excel : "+dataFromCSV.get("7").getValore());
		URL url = new URL(dataFromCSV.get("7").getValore());	 
		driver = new AndroidDriver<MobileElement>(url , cap);
		
		logger.info("Applicazione agganciata al server appium");	
		
		return driver;
	}
	
	
	
	public AndroidDriver<MobileElement> startDriverSaveCache(String idTest) throws EncryptedDocumentException, IOException {
	
		//recupero dati dal csv
		ImportCSV importCsv = new ImportCSV();
		dataFromCSV = importCsv.getCSVRowDataByIdTest("Login", idTest);
		
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
		driver = new AndroidDriver<MobileElement>(url , cap);
		
		System.out.println("Application Started");
		
		logger.info("Applicazione agganciata al server appium");	
		
		return driver;
	}
	
	public AndroidDriver<MobileElement> startEmulatorDriverClearCache(String idTest) throws EncryptedDocumentException, IOException {
		

		
		ImportCSV importCsv = new ImportCSV();
		dataFromCSV = importCsv.getCSVRowDataByIdTest("Login", idTest);
		
		DesiredCapabilities cap = new DesiredCapabilities();
		
		//configurazione capabilities 
		//DALL'IMPOSTAZIONI DEL CELL
		cap.setCapability("deviceName", "Emu_Pixel_API_30");
		//cap.setCapability("deviceName", dataFromCSV.get("0").getValore());
		//DA TERMINALE ADB : adb devices
		cap.setCapability("udid", "emulator-5554");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersions", "11.0");
//		cap.setCapability("udid", dataFromCSV.get("1").getValore());
//		cap.setCapability("platformName", dataFromCSV.get("2").getValore());
//		cap.setCapability("platformVersions", dataFromCSV.get("3").getValore());
		
		//impostazioni app
		System.out.println("Applicazione da testare :"+dataFromCSV.get("4").getValore());
		cap.setCapability("appPackage", dataFromCSV.get("4").getValore());
		System.out.println("Applicazione da testare : "+dataFromCSV.get("5").getValore());
		cap.setCapability("appActivity", dataFromCSV.get("5").getValore());
		
		
		//activity provate e non fuznionanti
		//cap.setCapability("appActivity", "bm0.zero.tier2.app.ZeroMainActivity");
		//funziona con la calcolatrice
//		cap.setCapability("appPackage", "com.sec.android.app.popupcalculator");
//		cap.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");
		
		//URL url = new URL(http://127.0.0.1:4723/wd/hub);
		URL url = new URL(dataFromCSV.get("6").getValore());	 
		driver = new AndroidDriver<MobileElement>(url , cap);
		
		driver.installApp("C:/FLOWE_relase/A0-0.27.210209.1.apk");
		System.out.println("app installata");
		driver.launchApp();
		
		logger.info("Applicazione agganciata al server appium");	
		
		return driver;
	}

}
