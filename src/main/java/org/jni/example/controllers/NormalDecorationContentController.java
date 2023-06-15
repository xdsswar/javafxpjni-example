package org.jni.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.jni.example.decorations.NormalDecoration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author XDSSWAR
 * Created on 06/15/2023
 */
public class ContentController implements Initializable {
    @FXML
    private Button btn;

    //Normal decoration
    private final NormalDecoration decoration;

    /**
     * Constructor
     * @param decoration NormalDecoration
     */
    public ContentController(NormalDecoration decoration) {
        this.decoration = decoration;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
         * We attach an event to the button in the fxml file
         */
        btn.setOnAction(event -> decoration.centerTitle(!decoration.isTitleCentered()));
    }
}
