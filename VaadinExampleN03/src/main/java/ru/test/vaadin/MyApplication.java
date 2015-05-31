package ru.test.vaadin;

import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author APronchakov <artem.pronchakov@gmail.com>
 */
public class MyApplication extends Application {
	private Window mainWindow;
	private HorizontalLayout hLayout = new HorizontalLayout();

	@Override
	public void init() {
		setTheme("reindeer");
		setLocale(new Locale("RU"));
		
		mainWindow = new Window("Test Vaadin application");
		
		mainWindow.setWidth("100%");
		mainWindow.setHeight(300, Window.UNITS_PIXELS);
		
		hLayout.setWidth("100%");
		hLayout.setSpacing(true);
		
		TwinColSelect colSelect = new TwinColSelect();
		colSelect.setImmediate(true);
		colSelect.setRows(7);
		colSelect.setLeftColumnCaption("Откуда берем:");
		colSelect.setRightColumnCaption("Куда складываем:");
		colSelect.setWidth("100%");
		colSelect.setMultiSelect(true);
		
		colSelect.addItem("Первое значение");
		colSelect.addItem("Второе значение");
		colSelect.addItem("Третье значение");
		colSelect.addItem("Четвертое значение");
		colSelect.addItem("Пятое значение");



		
		hLayout.addComponent(colSelect);
		
		mainWindow.addComponent(hLayout);
		
		setMainWindow(mainWindow);
	}

}

