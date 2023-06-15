package xss.it.fast.invoice;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import xss.it.conf.JConfig;
import xss.it.conf.JPlatform;
import xss.it.fast.invoice.data.sqlite.SqLite;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author XDSSWAR
 * Created on 06/02/2023
 */
public final class Assets {
    /**
     * The name of the Fast Invoice application.
     */
    public static final String APP_NAME = "Fast Invoice";

    /**
     * The version of the Fast Invoice application.
     */
    public static final String APP_VERSION = "1.0.0";

    private static final String KEY="2F413F4428472B4B";

    /**
     * The application Icon
     */
    public static final Image ICON=new Image(
        load("/xss/it/fast/invoice/assets/images/icon-128.png").toExternalForm()
    );

    /**
     * Splash View location
     */
    public static final String SPLASH_VIEW="/xss/it/fast/invoice/fxml/splash-screen.fxml";

    /**
     * Html view for the Splash Screen
     */
    public static final String SPLASH_HTML=load("/xss/it/fast/invoice/html/index.html")
            .toExternalForm();

    /**
     * Html view for the Login Screen
     */
    public static final String LOGIN_HTML=load("/xss/it/fast/invoice/html/login.html")
            .toExternalForm();

    /**
     * View parent of all views
     */
    public static final String VIEW_PARENT="/xss/it/fast/invoice/fxml/view-parent.fxml";

    /**
     * Login view
     */
    public static final String LOGIN_VIEW="/xss/it/fast/invoice/fxml/login.fxml";

    /**
     * Dashboard view
     */
    public static final String DASHBOARD_VIEW="/xss/it/fast/invoice/fxml/dashboard.fxml";

    /**
     * The configuration object for the Fast Invoice application.
     */
    public static final JConfig CONFIG;

    /**
     * Default db name
     */
    public static final String DB_NAME="database.sqlite";

    /*
     * Static initialization block for configuring Fast Invoice application settings.
     * This block is executed once when the class is loaded.
     * It creates the necessary directory structure and initializes the configuration settings.
     */
    static {
        final String folder=".fast-invoice";
        final String configPath=String.format(
                "%s%s%s%s",
                JPlatform.getUserDir(),
                folder,
                JPlatform.getDirSeparator(),
                "settings.properties"
        );
        JPlatform.createDirs(JPlatform.getUserDir(),folder);
        CONFIG=new JConfig(configPath,KEY);
    }



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


    /**
     * Initializes the application by setting up the necessary directories and configuration.
     * This method should be called at the start of the application.
     *
     * @throws IOException If an I/O error occurs during initialization.
     */
    public static synchronized void initialize() throws Exception {
        final String dataFolder=APP_NAME;
        final String reportsFolder="Reports";
        final String dbFolder="Local Db";

        //Declare and create the root dir
        final String dataFolderPath= JPlatform.getDocumentsDir()+dataFolder+JPlatform.getDirSeparator();
        JPlatform.createDirs(JPlatform.getDocumentsDir(),dataFolder);

        //Declare and create the sub-dirs
        final String reportsDir=dataFolderPath+reportsFolder+JPlatform.getDirSeparator();
        final String dbDir=dataFolderPath+dbFolder+JPlatform.getDirSeparator();
        JPlatform.createDirs(dataFolderPath,reportsFolder,dbFolder);

        if (!CONFIG.isSet("app.main.dir")) {
            CONFIG.set("app.main.dir", dataFolderPath);
        }
        if (!CONFIG.isSet("app.reports.dir")) {
            CONFIG.set("app.reports.dir", reportsDir);
        }
        if (!CONFIG.isSet("app.local.db.dir")) {
            CONFIG.set("app.local.db.dir", dbDir);
        }
        if (!CONFIG.isSet("app.local.db")) {
            CONFIG.set("app.local.db", String.format("%s%s",dbDir,DB_NAME));
        }

        /*
         * Copy the rest from the default properties. Since we already have props at this point, we just copy
         * the key-values that we don't have, by passing false to the refresh param
         */
        CONFIG.copyFromResource(loadStream("/default.properties"),false);

        SqLite.verifyDb();
    }

    /*
     * Load fonts
     */
    static {
        try {
            Font.loadFont(loadStream("/xss/it/fast/invoice/assets/fonts/black-chancery/BLKCHCRY.TTF"),12);
            Font.loadFont(loadStream("/xss/it/fast/invoice/assets/fonts/segoe/segoeui-regular.ttf"),12);
            Font.loadFont(loadStream("/xss/it/fast/invoice/assets/fonts/segoe/segoeui-bold.ttf"),12);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
