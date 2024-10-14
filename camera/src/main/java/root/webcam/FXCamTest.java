package root.webcam;

import com.github.sarxos.webcam.Webcam;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class FXCamTest extends Application {
    private WebCamService service;

    @Override
    public void init() {
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Webcam cam = Webcam.getWebcams().get(0);
            service = new WebCamService(cam);
        } catch (Exception e) {
            showError(primaryStage, "No Webcam Detected");
            return;
        }

        try {
//            Webcam cam = service.getCam();
//            cam.open();
//            Thread.sleep(1000);
//
//            String camName = cam.getName();
//            String deviceName = cam.getDevice().getName();
//            BufferedImage camImage = cam.getImage();

                Button startStop = new Button();
                startStop.textProperty().bind(Bindings.
                        when(service.runningProperty()).
                        then("Stop").
                        otherwise("Start"));

                startStop.setOnAction(e -> {
                    if (service.isRunning()) {
                        service.cancel();
                    } else {
                        service.restart();
                    }
                });

                WebCamView view = new WebCamView(service);

                BorderPane root = new BorderPane(view.getView());
                BorderPane.setAlignment(startStop, Pos.CENTER);
                BorderPane.setMargin(startStop, new Insets(5));
                root.setBottom(startStop);

//            Image image = SwingFXUtils.toFXImage(camImage, null);
//            Label label = new Label("Cam Name : " + camName + "\nDevice Name : " + deviceName);
//            label.setAlignment(Pos.CENTER);
//            label.setGraphic(new ImageView(image));
//            Scene scene = new Scene(label);
//            primaryStage.setScene(scene);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            showError(primaryStage, "Error starting Webcam : " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }

    }


    public static void main(String[] args) {
        launch(args);
    }

    private void showError(Stage primaryStage, String message) {
        primaryStage.setTitle("Error");
        primaryStage.setWidth(300);
        primaryStage.setHeight(200);
        Label label = new Label(message);
        label.setAlignment(Pos.CENTER);
        primaryStage.setScene(new Scene(label));
        primaryStage.show();
    }
}
