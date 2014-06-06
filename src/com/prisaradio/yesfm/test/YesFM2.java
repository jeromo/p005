package com.prisaradio.yesfm.test;

// Import the uiautomator libraries
import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class YesFM2 extends YesFM {
	public void test01Novedades() throws UiObjectNotFoundException {
		loginPremium();
		sleep(6000L);

		// Ir a Novedadess
		UiSelector selector = new UiSelector();
		UiScrollable listOfObjects = new UiScrollable(selector);
		UiObject object = listOfObjects.getChildByText(
				selector.className(android.widget.TextView.class.getName()),
				"Novedades", true);
		assertTrue("No encuentra Novedades", object.exists());

		object.clickAndWaitForNewWindow();

		comprobarSeccion("Novedades Álbumes", ">", "Álbumes");
		comprobarSeccion("Novedades Radios", ">", "Radios");
		comprobarSeccion("Novedades Stars", ">", "Stars");

		getUiDevice().pressBack();
		sleep(4000L);
		
		selector = new UiSelector();
		listOfObjects = new UiScrollable(selector);
		while(listOfObjects.scrollBackward());
		sleep(4000L);

		logout();
		sleep(4000L);

	}
}
