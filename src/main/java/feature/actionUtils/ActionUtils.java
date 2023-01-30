package feature.actionUtils;



import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.touch.WaitOptions;


public class ActionUtils {
	private static final Logger logger = LogManager.getLogger(ActionUtils.class);
	
	public static void insertSecurityToken(AppiumDriver<MobileElement> driver) throws InterruptedException {
		logger.info("Inserisci Security Token - Step: 1 - Action: Send < 00000 > ");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("bm0.zero.tier2:id/security_token_login_text_entry")));
		MobileElement insertSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/security_token_login_text_entry"));
		insertSecurityToken.sendKeys("00000");
		Thread.sleep(500);
		logger.info("Inserisci Security Token - Step: 2 - Action: Tap < Continue > ");
		MobileElement sendSecurityToken = driver.findElement(By.id("bm0.zero.tier2:id/security_token_continue_btn"));
		WaitOptions.waitOptions(Duration.ofSeconds(1));
		sendSecurityToken.click();
	}

}
