package root.pictureservice;

import com.gluonhq.attach.pictures.PicturesService;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import nu.pattern.OpenCV;

import java.util.Optional;

public class Main extends Application {

    public void start(Stage primaryStage) throws InterruptedException {
        primaryStage.setTitle("Camera Image");
        primaryStage.setWidth(300);
        primaryStage.setHeight(200);
//        InputStream iconStream = getClass().getResourceAsStream("openduke.png");
//        Image image = new Image(iconStream);
//        primaryStage.getIcons().add(image);

        Scene primaryScene;
//        primaryScene = new Scene(new BasicView());

        Optional<PicturesService> picturesService = PicturesService.create();
        if (picturesService.isPresent()) {
            PicturesService service = picturesService.get();
            Optional<Image> image = service.takePhoto(false);
            if (image.isPresent()) {
                ImageView imageView = new ImageView(image.get());
                primaryScene = new Scene(imageView.getParent());
            } else {
                Label helloWorldLabel = new Label("Can't take photo - No Image");
                helloWorldLabel.setAlignment(Pos.CENTER);
                primaryScene = new Scene(helloWorldLabel);
            }
        } else {
            Label helloWorldLabel = new Label("Can't take photo - No PicturesService");
            helloWorldLabel.setAlignment(Pos.CENTER);
            primaryScene = new Scene(helloWorldLabel);
        }

        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}