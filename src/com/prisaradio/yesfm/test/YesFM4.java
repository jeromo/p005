package com.prisaradio.yesfm.test;

import com.android.uiautomator.core.UiCollection;	
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;

public class YesFM4 extends YesFM {
	/*
	 * Comprobar que tiene imagen, nombre, descripción, creada por, botón de me gusta, Botón de play, Botón de opciones
	 * numero de escuchas, numero de canciones, duración
	 * Artistas:imagen, texto, opciones
	 */
	public void test01RadiosCompleta() throws UiObjectNotFoundException {
		loginPremium();
		sleep(6000L);

		
		/* Coger la primera radio
		 * Primero, encontrar ViewPager, instance 0, FrameLayout -> RelativeLayout. ImageView instance 2
		 */
		
		UiSelector selector = new UiSelector();
		UiObject object = new UiObject(selector.className("android.support.v4.view.ViewPager").instance(0));
		assertTrue("No encuentra ViewPager de la Primera Radio", object.exists());
		
		object = object.getChild(selector.className("android.widget.FrameLayout")); 
		assertTrue("No encuentra FrameLayout de Primera Radio", object.exists());

		object = object.getChild(selector.className("android.widget.RelativeLayout")); 
		assertTrue("No encuentra RelativeLayout de Primera Radio", object.exists());

		object = object.getChild(selector.className("android.widget.ImageView").instance(2)); 
		assertTrue("No encuentra tercera ImageView de Primera Radio", object.exists());

		assertTrue("No pudo posicionarse en la pantalla de la radio", object.clickAndWaitForNewWindow());
		sleep(4000L);
		
		//Ya estoy en la pantalla de la radio
		System.out.println("Estoy en la primera Radio"); System.out.flush();
		selector = new UiSelector();
		object = new UiObject(selector.className("android.view.View").instance(0));
		assertTrue("No encuentra View de Primera Radio", object.exists());

		object = object.getChild(selector.className("android.widget.FrameLayout")); 
		assertTrue("No encuentra FrameLayout de Primera Radio", object.exists());
		object = object.getChild(selector.className("android.widget.FrameLayout")); 
		assertTrue("No encuentra segundo FrameLayout de Primera Radio", object.exists());
		UiObject objectRelativeLayoutParent = object.getChild(selector.className("android.widget.RelativeLayout")); 
		assertTrue("No encuentra RelativeLayout de Primera Radio", objectRelativeLayoutParent.exists());
		UiObject objectRelativeLayoutSon = objectRelativeLayoutParent.getChild(selector.className("android.widget.RelativeLayout").instance(0)); 
		assertTrue("No encuentra RelativeLayout Hijo de Primera Radio", objectRelativeLayoutSon.exists());
		
		//Ahora, el título de la radio
		object = objectRelativeLayoutSon.getChild(selector.className("android.widget.TextView")); 
		assertTrue("No encuentra título de Primera Radio", object.exists());
		System.out.println("El título es " + object.getText()); System.out.flush();
		
		objectRelativeLayoutSon = objectRelativeLayoutParent.getChild(selector.className("android.widget.RelativeLayout").instance(1)); 
		assertTrue("No encuentra RelativeLayout Hijo segundo de Primera Radio", objectRelativeLayoutSon.exists());

		//Ahora, la descripción
		object = objectRelativeLayoutSon.getChild(selector.className("android.widget.TextView").instance(0)); 
		assertTrue("No encuentra descripción de Radio", object.exists());
		System.out.println("La descripción es " + object.getText()); System.out.flush();

		//Creador
		object = objectRelativeLayoutSon.getChild(selector.className("android.widget.TextView").instance(1)); 
		assertTrue("No encuentra creador de Radio", object.exists());
		System.out.println("El creador " + object.getText()); System.out.flush();

		// Botones Botón de Me gusta, Botón de play, Botón de opciones 
		object = objectRelativeLayoutSon.getChild(selector.className("android.widget.ImageButton").description("Botón de Me gusta")); 
		assertTrue("No encuentra Botón de Me gusta", object.exists());
		object = objectRelativeLayoutSon.getChild(selector.className("android.widget.ImageButton").description("Botón de play")); 
		assertTrue("No encuentra Botón de play", object.exists());
		object = objectRelativeLayoutSon.getChild(selector.className("android.widget.ImageButton").description("Botón de opciones")); 
		assertTrue("No encuentra Botón de opciones", object.exists());
		
		
		//Ahora, recorrerse las canciones
		UiScrollable canciones = new UiScrollable(new UiSelector().className("android.support.v4.view.ViewPager"));
		while (canciones.scrollForward())
			;
		getUiDevice().pressBack();
		sleep(4000L);
		logout();
		sleep(4000L);
	}

}
