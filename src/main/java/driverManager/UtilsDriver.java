package driverManager;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;

import feature.login.PrimoLogin;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import utils.Utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilsDriver {
	final static String PROJECT_PATH = System.getProperty("user.dir") + "/appiumScreens/";
	private static final Logger logger = LogManager.getLogger(PrimoLogin.class);
	Utils util = new Utils();

	public void screenCapture(AndroidDriver<MobileElement> driver) throws IOException {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String Path = PROJECT_PATH;
		File screenshotName = new File(Path + driver.getTitle() + ".png");
		// Now add screenshot to results by copying the file
		FileUtils.copyFile(scrFile, screenshotName);
		Reporter.log("<br>  <img src='" + screenshotName + "' height='100' width='100' /><br>");
		URL url = new URL(Path);
		Reporter.log("<a href=" + url + ">click to open screenshot</a>");

	}
	
	public void takeScreenShotStep(String methodName, String stepName,String description, AndroidDriver<MobileElement> driver) {
		// get the driver
		// driver=TestBase.getDriver();
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String filePath = PROJECT_PATH + util.getTimeForName() + "-" + methodName + ".png";
		logger.info("*ScreenShoot PATH: " + filePath + " ***");

		// The below method will save the screen shot in d drive with test method name
		try {
			FileUtils.copyFile(scrFile, new File(filePath));
			logger.info("***Placed screen shot in " + PROJECT_PATH + " ***");

//			Reporter.log("<table>");
//			Reporter.log("<br>" + "<tr>" + "<td style='width: 100%;'>" + "<tr><a href=" + filePath
//					+ " target=\"_blank\"><img src='" + filePath + "' height='100' width='100' /></a></tr>"
//					+ "<br><tr><a href=" + filePath + " target=\"_blank\">click to open screenshot</a></tr>" + "</td>"
//					+ "<td style='width: 275.2px;'>" + methodName + "</td>" + "<td style='width: 51.2px;'>"
//					+ "Description" + "</td>" + "</tr>" + "<br><hr>");
//			Reporter.log("</table>");
			
			
			Reporter.log("<table style='width: 100%;'>");
			Reporter.log("<tr><th colspan=\"3\">Step: "+stepName+"</th></tr>");
			Reporter.log("<tr>");
			Reporter.log("<td style='width: 20%'");
			Reporter.log("<div > "
						+"<a href=" + filePath
						+ " target=\"_blank\"><img src='" + filePath + "' height='100' width='100' /></a>"
						+ "</div>");
			Reporter.log("<div>"
					+ "<a href=" + filePath + " target=\"_blank\">click to open screenshot</a>"
					+ "</div>");
			Reporter.log("</td>");
			Reporter.log("<td>"+ methodName);
			Reporter.log("</td>");
			Reporter.log("<td>"+description);
			Reporter.log("</td>");
			Reporter.log("</tr>  ");
			Reporter.log(" </table>");
			Reporter.log("<br>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void takeScreenShot(String methodName, String description, AndroidDriver<MobileElement> driver,ITestResult result) {
		// get the driver
		// driver=TestBase.getDriver();
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String filePath = PROJECT_PATH + util.getTimeForName() + "-" + methodName + ".png";
		logger.info("*ScreenShoot PATH: " + filePath + " ***");

		// The below method will save the screen shot in d drive with test method name
		try {
			FileUtils.copyFile(scrFile, new File(filePath));
			logger.info("***Placed screen shot in " + PROJECT_PATH + " ***");

//			Reporter.log("<table>");
//			Reporter.log("<br>" + "<tr>" + "<td style='width: 100%;'>" + "<tr><a href=" + filePath
//					+ " target=\"_blank\"><img src='" + filePath + "' height='100' width='100' /></a></tr>"
//					+ "<br><tr><a href=" + filePath + " target=\"_blank\">click to open screenshot</a></tr>" + "</td>"
//					+ "<td style='width: 275.2px;'>" + methodName + "</td>" + "<td style='width: 51.2px;'>"
//					+ "Description" + "</td>" + "</tr>" + "<br><hr>");
//			Reporter.log("</table>");
			
			
			Reporter.log("<table style='width: 100%;'>");
			Reporter.log("<tr><th colspan=\"3\">Test: "+result.getTestName()+"</th></tr>");
			Reporter.log("<tr>");
			Reporter.log("<td style='width: 20%'");
			Reporter.log("<div > "
						+"<a href=" + filePath
						+ " target=\"_blank\"><img src='" + filePath + "' height='100' width='100' /></a>"
						+ "</div>");
			Reporter.log("<div>"
					+ "<a href=" + filePath + " target=\"_blank\">click to open screenshot</a>"
					+ "</div>");
			Reporter.log("</td>");
			Reporter.log("<td>"+ methodName);
			Reporter.log("</td>");
			Reporter.log("<td>"+description);
			Reporter.log("</td>");
			Reporter.log("</tr>  ");
			Reporter.log(" </table>");
			Reporter.log("<br>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void failureScreenShot(String methodName, AndroidDriver<MobileElement> driver, ITestResult result) {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String filePath = PROJECT_PATH + util.getTimeForName() + "-" + methodName + ".png";
		logger.info("***failureScreenShot PATH: " + filePath + " ***");

		// The below method will save the screen shot in d drive with test method name
		try {
			FileUtils.copyFile(scrFile, new File(filePath));
			// logger.info("***failureScreenShot Placed screen shot in "+PROJECT_PATH+"
			// ***");
//			Reporter.log("<table>");
//			Reporter.log("<br>" + "<tr>" + "<td style='width: 100%;'>" + "<tr><a href=" + filePath
//					+ " target=\"_blank\"><img src='" + filePath + "' height='100' width='100' /></a></tr>"
//					+ "<br><tr><a href=" + filePath + " target=\"_blank\">click to open screenshot</a></tr>" + "</td>"
//					+ "<td style='width: 275.2px;'>" + methodName + "</td>" + "<td style='width: 51.2px;'>"
//					+ "Description" + "</td>" + "</tr>" + "<br><hr>");
//			Reporter.log("</table>");
			
			Reporter.log("<table style='width: 100%;'>");
			Reporter.log("<tr><th colspan=\"3\">Test: "+result.getTestName()+"</th></tr>");
			Reporter.log("<tr>");
			Reporter.log("<td style='width: 20%'");
			Reporter.log("<div > "
						+"<a href=" + filePath
						+ " target=\"_blank\"><img src='" + filePath + "' height='100' width='100' /></a>"
						+ "</div>");
			Reporter.log("<div>"
					+ "<a href=" + filePath + " target=\"_blank\">click to open screenshot</a>"
					+ "</div>");
			Reporter.log("</td>");
			Reporter.log("<td>"+ methodName);
			Reporter.log("</td>");
			Reporter.log("<td>prova");
			Reporter.log("</td>");
			Reporter.log("</tr>  ");
			Reporter.log(" </table>");
			Reporter.log("<br>");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
