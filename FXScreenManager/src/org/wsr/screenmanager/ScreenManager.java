package org.wsr.screenmanager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import org.wsr.screenmanager.interfaces.ScreenController;

/**
 * Manager to perform screen actions/transitions.
 * 
 * @author willes.reis
 */
public class ScreenManager extends StackPane {
	
	/**
	 * Stack of screens to be managed.
	 */
	private Map<String, Node> screens = new HashMap<String, Node>();
	
	/** 
	 * Add screen to be managed.
	 * 
	 * @param name Screen name.
	 * @param node Node to be putted inside pane.
	 */
	public void addScreen(String name, Node node) {
		screens.put(name, node);
	}
	
	/**
	 * Define actual screen with fade transition.
	 * 
	 * @param name Screen name.
	 * @return true to success defined, or false otherwise.
	 */
	public boolean setScreen(final String name) {
		if (screens.get(name) != null) {
			final DoubleProperty opacity = opacityProperty();
			
			final Timeline fadeIn = new Timeline(
					new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
					new KeyFrame(new Duration(2000), new KeyValue(opacity, 0.0)));
			if (!getChildren().isEmpty()) {
				Timeline fade = new Timeline(
						new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
						new KeyFrame(new Duration(1000), new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								getChildren().remove(0);
								getChildren().add(0, screens.get(name));
								fadeIn.play();
							}
						}, new KeyValue(opacity, 0.0)));
				fade.play();
			} else {
				setOpacity(0.0);
				getChildren().add(screens.get(name));
				fadeIn.play();
			}
			return true;
		} else {
			System.out.println("Screen hasn't been loaded!");
			return false;
		}
	}
	
	/**
	 * Load screen with your template FXML.
	 * 
	 * @param name Screen name.
	 * @param clazz Class to get resource.
	 * @param resource Template name of FXML.
	 * @return true to success load, or false otherwise.
	 */
	public boolean loadScreen(String name, Class<? extends Application> clazz, String resource) {
		try {
			FXMLLoader loader = new FXMLLoader(clazz.getResource(resource));
			Parent screen = (Parent) loader.load();
			ScreenController controller = loader.getController();
			controller.setScreenParent(this);
			addScreen(name, screen);
			return true;
		} catch (IOException ioe) {
			Logger.getLogger(ScreenManager.class.getName()).log(Level.ALL, ioe.getMessage());
			return false;
		}
	}

	/**
	 * Remove screen of the stack.
	 * 
	 * @param name Screen name.
	 * @return true to success remove, or false otherwise.
	 */
	public boolean unloadScreen(String name) {
		if (screens.remove(name) == null) {
			System.out.println("Screen didn't exist!");
			return false;
		} else {
			return true;
		}
	}
}
