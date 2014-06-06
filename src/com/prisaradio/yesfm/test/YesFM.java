package com.prisaradio.yesfm.test;

// Import the uiautomator libraries
import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class YesFM extends UiAutomatorTestCase {

	protected void logout() throws UiObjectNotFoundException {
		UiObject menuButton = new UiObject(new UiSelector().className(
				"android.widget.ImageButton").descriptionContains(
				"Botón de menú"));
		assertTrue("No encuentra Botón de menú", menuButton.exists());

		menuButton.clickAndWaitForNewWindow();

		UiScrollable appViews = new UiScrollable(
				new UiSelector().scrollable(true));

		UiObject salirButton = appViews.getChildByText(new UiSelector()
				.className(android.widget.TextView.class.getName()), "Salir", true);
		assertTrue("No encuentra Salir en menú", salirButton.exists());

		salirButton.clickAndWaitForNewWindow();

		UiObject okButton = new UiObject(new UiSelector().className(
				"android.widget.Button").textContains("OK"));
		assertTrue("No encuentra campo OK", okButton.exists());

		okButton.click();

		getUiDevice().pressBack();
		sleep(1000L);
		getUiDevice().pressBack();

	}

	protected void loginPremium() throws UiObjectNotFoundException {
		UiScrollable appViews = new UiScrollable(
				new UiSelector().scrollable(true));
		// Set the swiping mode to horizontal (the default is vertical)
		// appViews.setAsHorizontalList();

		// Create a UiSelector to find the Settings app and simulate
		// a user click to launch the app.
		UiObject yesFMApp = appViews.getChildByText(new UiSelector()
				.className(android.widget.TextView.class.getName()), "Yes.fm", true);
		assertTrue("No encontró Yes.fm", yesFMApp.exists());
		yesFMApp.clickAndWaitForNewWindow();
		sleep(4000L);

		UiObject userItem = new UiObject(new UiSelector().className(
				"android.widget.EditText").instance(0));
		assertTrue("No encuentra campo User o Mail", userItem.exists());

		userItem.setText("PDpruebas");

		UiObject userPassword = new UiObject(new UiSelector().className(
				"android.widget.EditText").instance(1));
		assertTrue("No encuentra campo Password", userPassword.exists());

		userPassword.setText("PDpruebas1");

		UiObject entrarButton = new UiObject(new UiSelector().className(
				"android.widget.Button").textContains("entra"));
		assertTrue("No encuentra campo Password", entrarButton.exists());

		entrarButton.clickAndWaitForNewWindow();
		sleep(4000L);

		UiObject radiosTextView = new UiObject(new UiSelector().className(
				"android.widget.TextView").textContains("Radios"));
		assertTrue("No encuentra campo Radios, no debió autenticar",
				radiosTextView.exists());

	}

	protected void getTextView(UiScrollable textViews, String text)
			throws UiObjectNotFoundException {
		UiObject object = textViews.getChildByText(new UiSelector()
				.className(android.widget.TextView.class.getName()), text, true);
		assertTrue("No encuentra " + text, object.exists());
	}

	protected void comprobarMenu() throws UiObjectNotFoundException {
		sleep(4000L);
		UiObject menuButton = new UiObject(new UiSelector().className(
				"android.widget.ImageButton").descriptionContains(
				"Botón de menú"));
		assertTrue("No encuentra Botón de menú", menuButton.exists());

		menuButton.clickAndWaitForNewWindow();

		UiScrollable textViews = new UiScrollable(new UiSelector());

		getTextView(textViews, "Mi Perfil");
		getTextView(textViews, "Home");
		getTextView(textViews, "Radios");
		getTextView(textViews, "Click & Go");
		getTextView(textViews, "Novedades");
		getTextView(textViews, "Stars");
		getTextView(textViews, "Tops");
		getTextView(textViews, "Playlists");
		getTextView(textViews, "Buscador");
		getTextView(textViews, "Sin Conexión");
		getTextView(textViews, "Configuración");
		getTextView(textViews, "Ayuda");
		getTextView(textViews, "Salir");

		getUiDevice().pressBack();
		sleep(4000L);
	}

	protected void comprobarSeccion(String textViewOrigin,
			String textViewAuxiliar, String containsTextView)
			throws UiObjectNotFoundException {

		UiSelector selector = new UiSelector();
		UiScrollable listOfObjectsOrigin = new UiScrollable(selector);
		UiObject objetoOrigin = listOfObjectsOrigin.getChildByText(
				selector.className(android.widget.TextView.class.getName()),
				textViewOrigin, true);
		assertTrue("No encuentra " + textViewOrigin, objetoOrigin.exists());

		objetoOrigin.clickAndWaitForNewWindow();

		selector = new UiSelector();
		UiScrollable listOfObjects = new UiScrollable(selector);

		UiObject objeto = listOfObjects.getChildByText(
				selector.className(android.widget.TextView.class.getName()),
				containsTextView, true);
		assertTrue("No encuentra en ventana hija " + containsTextView,
				objeto.exists());
		getUiDevice().pressBack();
		sleep(2000L);

		selector = new UiSelector();
		objeto = objetoOrigin.getFromParent(selector.text(textViewAuxiliar));
		assertTrue(
				"No encuentra " + textViewAuxiliar + " de " + textViewOrigin,
				objeto.exists());

		// Ahora, el TextView ">"
		/*
		 * selector = new UiSelector(); listOfObjects = new
		 * UiScrollable(selector); objeto =
		 * listOfObjects.getChildByInstance(selector.text(">"), position);
		 * assertTrue("No encuentra > de " + textViewOrigin + " en position " +
		 * String.valueOf(position), objeto.exists());
		 */
		objeto.clickAndWaitForNewWindow();

		selector = new UiSelector();
		listOfObjects = new UiScrollable(selector);

		objeto = listOfObjects.getChildByText(
				selector.className(android.widget.TextView.class.getName()),
				containsTextView, true);
		assertTrue("No encuentra en ventana hija " + containsTextView,
				objeto.exists());

		getUiDevice().pressBack();
		sleep(2000L);
		
		//Volver al principio de la página
		selector = new UiSelector();
		listOfObjects = new UiScrollable(selector);
		while(listOfObjects.scrollBackward());
		sleep(4000L);
	}

	protected void comprobarInformativos() throws UiObjectNotFoundException {
		UiScrollable textViews = new UiScrollable(new UiSelector());
		getTextView(textViews, "Informativos");
		getTextView(textViews, "Deportes");
		getTextView(textViews, "El tiempo");

	}




}
/*
 * // Recorrerse todos los widget, pulsarlos, reconocer que no es 404
 * 
 * 
 * UiSelector selectorVentanaHija; UiCollection coleccionVentanaHija;
 * 
 * UiSelector selector = new UiSelector(); // UiCollection coleccion = new
 * UiCollection(selector.clickable(true)); // int numero_clickables = //
 * coleccion.getChildCount(selector.clickable(true));
 * 
 * sleep(6000L); UiCollection coleccion = new UiCollection(selector);
 * System.out.println(coleccion.getSelector()); System.out.flush();
 * 
 * int numero_objetos = coleccion.getChildCount(selector); int i; String fs; fs
 * = String.format("numero_objetos es %d", numero_objetos);
 * System.out.println(fs); System.out.flush(); for (i = 0; i < numero_objetos;
 * i++) { // new UiSelector cada vez porque cambiamos la página objeto =
 * coleccion.getChildByInstance(selector, i); if (!objeto.exists()) break;
 * 
 * if (objeto.isClickable()) { objeto.clickAndWaitForNewWindow(); sleep(4000L);
 * fs = String.format("i es %d", i); System.out.println(fs); System.out.flush();
 * System.out.println(objeto.getSelector()); System.out.flush();
 * 
 * selectorVentanaHija = new UiSelector(); coleccionVentanaHija = new
 * UiCollection(selectorVentanaHija); if
 * (coleccionVentanaHija.getChildCount(selectorVentanaHija) < 9) { fs = String
 * .format("No ha encontrado suficientes objetos en ventana %d",
 * coleccionVentanaHija .getChildCount(selectorVentanaHija));
 * System.out.println(fs); System.out.flush(); } //
 * assertTrue(coleccionVentanaHija.getChildCount(selectorVentanaHija) // < 9);
 * 
 * getUiDevice().pressBack(); } }
 */


/*
for (j = 0; j < objectRelativeLayout.getChildCount(); j++)
{
	object = objectRelativeLayout.getChild(new UiSelector().instance(j));
	if (object.exists())
			System.out.println("Estoy recorriendo objeto "+String.format("%d", j) +" " + object.getContentDescription()); System.out.flush();
			
	if (object.isClickable())
	{
		object.clickAndWaitForNewWindow();
		getUiDevice().pressBack();
	}
}

UiObject object = textViews.getChildByText(new UiSelector()
.className(android.widget.TextView.class.getName()), text);
listOfObjects = new UiScrollable(objectListView.;
object = objectListView.getChild(new UiSelector().className("android.widget.ImageView"));
assertTrue("No encuentra Imagen de Primera Radio", object.exists());
//
//AHora, averiguar qué tipo de objetos queremos seleccionar
UiScrollable objectList = objectListView.getChild(
	selector.className(android.widget.ListView.class.getName()),
	"Novedades");
assertTrue("No encuentra Novedades", object.exists());
objeto = objetoOrigin.getFromParent(selector.text(textViewAuxiliar));
assertTrue(
	"No encuentra " + textViewAuxiliar + " de " + textViewOrigin,
	objeto.exists());

object.clickAndWaitForNewWindow();
*/
//System.out.println("Ahora, logout"); System.out.flush();
//logout();
