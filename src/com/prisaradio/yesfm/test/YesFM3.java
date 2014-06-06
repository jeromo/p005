package com.prisaradio.yesfm.test;

// Import the uiautomator libraries
import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class YesFM3 extends YesFM{

	/*
	 * Parece imposible recorrerse un arbol de forma genérica, habrá que
	 * recorrerse el árbol específico de YesFM
	 */
	public void test02Home() throws UiObjectNotFoundException {
		loginPremium();
		sleep(4000L);

		comprobarMenu();
		comprobarSeccion("Radios", ">", "Radios YES");
		comprobarSeccion("Novedades", ">", "Novedades Álbumes");
		comprobarSeccion("Stars", ">", "Stars");
		comprobarSeccion("Tops", ">", "Top Álbumes");
		comprobarSeccion("Playlists", ">", "Playlists");

		logout();
		sleep(4000L);
	}

	public void test03Radios() throws UiObjectNotFoundException {
		loginPremium();
		sleep(4000L);

		// Ir a Radios
		UiSelector selector = new UiSelector();
		UiScrollable listOfObjects = new UiScrollable(selector);
		UiObject object = listOfObjects.getChildByText(
				selector.className(android.widget.TextView.class.getName()),
				"Radios", true);
		assertTrue("No encuentra Radios", object.exists());

		object.clickAndWaitForNewWindow();

		comprobarSeccion("Radios YES", ">", "Radios YES");
		comprobarSeccion("Radios FM", ">", "Radios FM");
		comprobarSeccion("Radios de Stars", ">", "Radios de Stars");
		comprobarSeccion("Radios de Usuarios", ">", "Radios de Usuarios");
		comprobarInformativos();
		getUiDevice().pressBack();
		sleep(4000L);

		logout();
		sleep(4000L);
	}
}
