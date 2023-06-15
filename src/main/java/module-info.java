/**
 * @author XDSSWAR
 * Created on 06/15/2023
 */
module javafxpjni.example {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.jni;

    exports org.jni.example.controllers;
    opens org.jni.example.controllers;

    exports org.jni.example.decorations;
    opens org.jni.example.decorations;

    exports org.jni.example;
    opens org.jni.example;
}