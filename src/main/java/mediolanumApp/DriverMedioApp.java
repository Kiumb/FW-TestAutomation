package mediolanumApp;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.remote.DesiredCapabilities;


import data.CellsMapper;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import utils.ImportCSV;

import com.experitest.appium.SeeTestCapabilityType;
import com.experitest.appium.SeeTestClient;
import com.experitest.client.*;

public class DriverMedioApp {
	static AndroidDriver<MobileElement> driver;
	static IOSDriver<MobileElement> driverOs;
	//AndroidDriver driverAndroid;
	static HashMap<String, CellsMapper> dataFromCSV;
	private static final Logger logger = LogManager.getLogger(DriverMedioApp.class);
	
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
	
	
	public IOSDriver<MobileElement> startDriverIosRemote(String idTest) throws EncryptedDocumentException, IOException {
		
		logger.info("startDriverIosRemote : ");
		SeeTestClient seetest;
		
		ImportCSV importCsv = new ImportCSV();
		dataFromCSV = importCsv.getCSVRowDataByIdTest("Login",idTest);
		
		DesiredCapabilities dc = new DesiredCapabilities();

		
		logger.info("Setting up Desired Capabilities");
		String testName = "Testing Android App with Java";
	    String accessKey = "eyJhbGciOiJIUzI1NiJ9.eyJ4cC51IjoxMDk3MDQ1NCwieHAucCI6MTA5NzA0NTMsInhwLm0iOjE2MTU5ODQxNDgzNDEsImV4cCI6MTkzMTM0NDIwMCwiaXNzIjoiY29tLmV4cGVyaXRlc3QifQ.jlvcHKhDk-Ue0kNTuQTFADdPzAT3Jg3O_r-4YTENAHw";
//	    //System.getenv("SEETEST_IO_ACCESS_KEY");
	    logger.info("DriverExperiTest SeeTestCapabilityType.ACCESS_KEY");
	    dc.setCapability(SeeTestCapabilityType.ACCESS_KEY, accessKey);
	    logger.info("DriverExperiTest platfor Name");
	    dc.setCapability(MobileCapabilityType.PLATFORM_NAME,"iOS");
	    String deviceQuery = String.format("@os=iOS", "iOS");
	    logger.info("DriverExperiTest Set Qyery");
	    dc.setCapability(SeeTestCapabilityType.DEVICE_QUERY, deviceQuery);
////	    AppiumDriver driver = new AndroidDriver(SeeTestProperties.SEETEST_IO_APPIUM_URL, dc)
//	   // dc.setCapability("testName", testName);
//        //dc.setCapability("accessKey", accessKey);
//        //install the app on the device
//	    logger.info("DriverExperiTest","install App");
        //dc.setCapability(MobileCapabilityType.APP, "http://d242m5chux1g9j.cloudfront.net/EriBank.ipa");
        dc.setCapability(MobileCapabilityType.AUTO_WEBVIEW, true);
//        //dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.EriBank");
//        //get an iOS device
         dc.setCapability("platformName", "iOS");
//        //dc.setCapability("uiud", "00008030-000D7589020A802E");//@serialnumber='00008030-001018E1019A802E'
//        dc.setCapability("autoDismissAlerts", true);
        //launch the app
//        dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.ExperiBank");
//  
//        logger.info("Setting up Desired Capabilities");
        //driverOs = new IOSDriver(new URL("https://cloud.seetest.io/wd/hub/"), dc);
//		
//		
//		
//		//URL url = new URL(http://127.0.0.1:4723/wd/hub);
//		logger.info("URL da excel : "+dataFromCSV.get("7").getValore());
//		URL url = new URL(dataFromCSV.get("7").getValore());	 
//		//driverOs = new IOSDriver<MobileElement>(url , cap);
//		
//		logger.info("Applicazione agganciata al server appium");	
        
        driverOs = new IOSDriver(new URL("https://cloud.seetest.io/wd/hub/"), dc);
		seetest = new SeeTestClient(driverOs);
		//dc.setCapability(MobileCapabilityType.UDID, "00008030-001018E1019A802E");
		seetest.launch("com.apple.calculator", false, false);
//		
		return driverOs;
	}
	
	

	
//	public IOSDriver<MobileElement> startDriverIosRemote(String idTest) throws EncryptedDocumentException, IOException {
//		
//		logger.info("startDriverIosRemote : ");
//		
//		ImportCSV importCsv = new ImportCSV();
//		dataFromCSV = importCsv.getCSVRowDataByIdTest("Login",idTest);
//		
//		DesiredCapabilities dc = new DesiredCapabilities();
//		
//		
//		logger.info("Setting up Desired Capabilities");
//		String testName = "Testing Android App with Java";
//	    String accessKey = "eyJhbGciOiJIUzI1NiJ9.eyJ4cC51IjoxMDk3MDQ1NCwieHAucCI6MTA5NzA0NTMsInhwLm0iOjE2MTU5ODQxNDgzNDEsImV4cCI6MTkzMTM0NDIwMCwiaXNzIjoiY29tLmV4cGVyaXRlc3QifQ.jlvcHKhDk-Ue0kNTuQTFADdPzAT3Jg3O_r-4YTENAHw";
//	    //System.getenv("SEETEST_IO_ACCESS_KEY");
//	    logger.info("DriverExperiTest SeeTestCapabilityType.ACCESS_KEY");
//	    dc.setCapability(SeeTestCapabilityType.ACCESS_KEY, accessKey);
//	    logger.info("DriverExperiTest platfor Name");
//	    dc.setCapability(MobileCapabilityType.PLATFORM_NAME,"iOS");
//	    String deviceQuery = String.format("@os=iOS and @dhmlocation='us-1'", "iOS");
//	    logger.info("DriverExperiTest Set Qyery");
//	    dc.setCapability(SeeTestCapabilityType.DEVICE_QUERY, deviceQuery);
////	    AppiumDriver driver = new AndroidDriver(SeeTestProperties.SEETEST_IO_APPIUM_URL, dc)
//	   // dc.setCapability("testName", testName);
//        //dc.setCapability("accessKey", accessKey);
//        //install the app on the device
//	    logger.info("DriverExperiTest","install App");
//        //dc.setCapability(MobileCapabilityType.APP, "http://d242m5chux1g9j.cloudfront.net/EriBank.ipa");
//        dc.setCapability(MobileCapabilityType.AUTO_WEBVIEW, true);
//        //dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.EriBank");
//        //get an iOS device
//        //dc.setCapability("platformName", "iOS");
//        //dc.setCapability("uiud", "00008030-000D7589020A802E");//@serialnumber='00008030-001018E1019A802E'
//        dc.setCapability("autoDismissAlerts", true);
//        //launch the app
//        dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.ExperiBank");
//  
//        logger.info("Setting up Desired Capabilities");
//        driverOs = new IOSDriver(new URL("https://cloud.seetest.io/wd/hub/"), dc);
//		
//		
//		
//		//URL url = new URL(http://127.0.0.1:4723/wd/hub);
//		logger.info("URL da excel : "+dataFromCSV.get("7").getValore());
//		URL url = new URL(dataFromCSV.get("7").getValore());	 
//		//driverOs = new IOSDriver<MobileElement>(url , cap);
//		
//		logger.info("Applicazione agganciata al server appium");	
//		
//		return driverOs;
//	}

}
