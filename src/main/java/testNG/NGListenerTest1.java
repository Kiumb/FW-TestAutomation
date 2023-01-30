package testNG;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import driverManager.UtilsDriver;
import feature.Init;
import feature.login.PrimoLogin;

public class NGListenerTest1 implements ISuiteListener, ITestListener {
	private static final Logger logger = LogManager.getLogger(NGListenerTest1.class);
	UtilsDriver utilDriver = new UtilsDriver();

	@Override
	public void onStart(ISuite suite) {
		logger.info("TestNG Listener onStart suite default output directory = " + suite.getOutputDirectory());
	}

	@Override
	public void onFinish(ISuite suite) {
		logger.info("TestNG Listener on finish invoked methods = " + suite.getAllInvokedMethods());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger.info("***** onTestFailure " + result.getName() + " test has failed : see screenshoot *****");
		String methodName = result.getName().toString().trim();
		utilDriver.failureScreenShot(methodName,Init.getDriver(),result);
		Reporter.log("Test "+result.getName()+" fallito : "+result.getStatus());
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("***** onTestSuccess " + result.getName() + " test finish : see screenshoot *****");
		String methodName = result.getName().toString().trim();
		
		utilDriver.failureScreenShot(methodName,Init.getDriver(),result);
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    Date date = new Date(result.getEndMillis());
	    System.out.println(formatter.format(date)); 
	    Reporter.log("onTestSuccess take screen");
		Reporter.log("Test "+result.getInstanceName()+" completato in : "+formatter.format(date));
	}

//	public void takeScreenShot(String methodName) {
//		// get the driver
//		driver = TestBase.getDriver();
//		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		// The below method will save the screen shot in d drive with test method name
//		try {
//			FileUtils.copyFile(scrFile, new File(filePath + methodName + ".png"));
//			System.out.println("***Placed screen shot in " + filePath + " ***");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
