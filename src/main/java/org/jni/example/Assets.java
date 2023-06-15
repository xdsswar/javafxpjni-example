package org.jni.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author XDSSWAR
 * Created on 06/02/2023
 */
public final class Assets {
    /**
     * App Icon
     */
    public static final Image ICON=new Image(Assets.load("/org/jni/example/icon.png").toExternalForm());

    /**
     * Loads and returns the root element of a JavaFX scene graph from the specified FXML file.
     *
     * @param location   The location of the FXML file.
     * @param controller The controller object to associate with the FXML file. Can be null.
     * @return The root element of the loaded JavaFX scene graph.
     */
    public static Parent load(final String location, Object controller){
        FXMLLoader loader = new FXMLLoader(load(location));
        if (controller!=null){
            loader.setController(controller);
        }
        try {
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads a resource file from the specified location.
     *
     * @param location The location of the resource file.
     * @return The URL of the loaded resource.
     */
    public static URL load(final String location){
        return Assets.class.getResource(location);
    }

    /**
     * Loads and returns an input stream for the resource file from the specified location.
     *
     * @param location The location of the resource file.
     * @return The input stream of the loaded resource.
     */
    public static InputStream loadStream(final String location){
        return Assets.class.getResourceAsStream(location);
    }




}
