package org.jni.example;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jni.example.controllers.NormalDecorationContentController;
import org.jni.example.decorations.NormalDecoration;
import xss.it.javafx.JavaFxJni;
import xss.it.javafx.decorator.StageDecorator;

import static org.jni.example.Assets.ICON;

/**
 * @author XDSSWAR
 * Created on 06/15/2023
 */
public class Demo extends Application {

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage stage) {
        stage.setTitle("Custom Stage Decoration");
        stage.getIcons().add(ICON);
        StageDecorator decorator=new StageDecorator(stage,30);


        NormalDecoration normalDec=new NormalDecoration(stage);

        /*
         * The HitTestSpots are very important, if you won't set them  to the decorator you won't be able to click
         * any control located in the top zone above the titleBar height
         */
        decorator.setHitSpots(normalDec.getHistSpots());

        //Center title if you want
        normalDec.centerTitle(true);

        //Load Fxml content. Note we pass the normalDec as parameter to the controller in case we need it
        Parent content=Assets.load("/org/jni/example/fxml/normal-content.fxml",new NormalDecorationContentController(normalDec));

        /*
         * With this method you can change teh content of the decoration
         */
        normalDec.setContent(content);


        Scene scene=new Scene(normalDec,1000,600);


        stage.setScene(scene);

        /*
         * If the stage flicker on shown , this hack hides it
         */
        stage.setOpacity(0);
        PauseTransition pt=new PauseTransition(Duration.millis(100));
        pt.setOnFinished(event -> stage.setOpacity(1d));
        stage.show();
        pt.play();
    }


    @Override
    public void init() throws Exception {
        JavaFxJni.initialize();
    }
}
