package feature;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class Init {
	private static AndroidDriver<MobileElement> driver;
	private static IOSDriver<MobileElement> driverios;

	public static IOSDriver<MobileElement> getDriverios() {
		return driverios;
	}

	public static void setDriverios(IOSDriver<MobileElement> driverios) {
		Init.driverios = driverios;
	}

	public static AndroidDriver<MobileElement> getDriver() {
		return driver;
	}

	public static void setDriver(AndroidDriver<MobileElement> driver) {
		Init.driver = driver;
	}
	
	

}
