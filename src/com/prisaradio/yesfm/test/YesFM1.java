package com.prisaradio.yesfm.test;

// Import the uiautomator libraries
import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class YesFM1 extends YesFM {


	public void test01RadiosYESFM() throws UiObjectNotFoundException {
		loginPremium();
		sleep(6000L);

		UiScrollable objectScrollable;
		// Ir a Radios
		UiSelector selector = new UiSelector();
		UiScrollable listOfObjects = new UiScrollable(selector);
		UiObject object = listOfObjects.getChildByText(
				selector.className(android.widget.TextView.class.getName()),
				"Radios", true);
		assertTrue("No encuentra Radios", object.exists());
		object.clickAndWaitForNewWindow();

		// Ir a Radios YES
		selector = new UiSelector();
		listOfObjects = new UiScrollable(selector);
		object = listOfObjects.getChildByText(
				selector.className(android.widget.TextView.class.getName()),
				"Radios YES", true);
		assertTrue("No encuentra Radios YES", object.exists());
		object.clickAndWaitForNewWindow();
		sleep(4000L);
		
		System.out.println("Estoy en Radios YES"); System.out.flush();
		
		 //Dar un máximo de 4 scrolls
		for (int contador = 0; contador < 4; contador ++)
		{
			selector = new UiSelector();
			UiObject objectListView = new UiObject(selector.className("android.widget.ListView"));
			assertTrue("No encuentra listView", objectListView.exists());

			/*
			 * Estructura:
			 * N LinearLayout
			 * 			-	RelativeLayout
			 * 				- RelativeLayout
			 * 					- ImageView					=> Lleva a la página de la Radio
			 * 					- View						=>No sé
			 * 					- TextView					=> Descripción Lleva a la página de la Radio
			 * 					- TextView					=> Número de escuchas
			 * 					- ButtonImage				=> Player plays la radio elegida
			 * 					- ButtonImage				=> Ventana de Cosas a hacer con la Radio
			 */

			// Conseguir  El número de LinearLayout en el objectListView
			UiCollection collection = new UiCollection(selector.className("android.widget.ListView"));
			int numberLinearLayout = collection.getChildCount(new UiSelector().className("android.widget.LinearLayout"));
			String fs; fs  = String.format("numero_objetos es %d", numberLinearLayout);
			System.out.println(fs); System.out.flush(); 

			UiObject objectRelativeLayout;

			int j;
			UiObject objectLinearLayout;
			//Recorrer cada LinearLayout
			for (int i = 0; i < numberLinearLayout; i++)
			{
				//El primer LinearLayout
				objectLinearLayout = objectListView.getChild(new UiSelector().className("android.widget.LinearLayout").instance(i));

				//Conseguir el primer RelativeLayout
				objectRelativeLayout = objectLinearLayout.getChild(new UiSelector().className("android.widget.RelativeLayout"));

				//Conseguir su RelativeLaout Hijo
				objectRelativeLayout = objectRelativeLayout.getChild(new UiSelector().className("android.widget.RelativeLayout"));
				// Soltar el tipo de cada hijo
				// coger los TextImage de cada uno de ellos
				fs  = String.format("numero_objetos Hijos es %d", objectRelativeLayout.getChildCount());
				System.out.println(fs); System.out.flush();
				object = objectRelativeLayout.getChild(new UiSelector().className("android.widget.ImageView"));
				if (object.exists())
				{
					object.clickAndWaitForNewWindow();
					getUiDevice().pressBack();
				}
				object = objectRelativeLayout.getChild(new UiSelector().className("android.widget.Button").instance(1));
				if (object.exists())
				{
					object.clickAndWaitForNewWindow();
					getUiDevice().pressBack();
				}
			
			}
			objectScrollable = new UiScrollable(new UiSelector());
			if (!objectScrollable.scrollForward())
				break;
			
			sleep(4000L);
		}
		getUiDevice().pressBack();
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
