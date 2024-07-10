package com.mycompany.sample.service;

import com.gluonhq.attach.pictures.PicturesService;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.Icon;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class BasicView extends View {

    public BasicView() {
        Label label = new Label("\n"
                + "PicturesService Android :\n"
                + "Can't take photo.\n"
                + "Can't load from gallery more than once.\n"
                + "https://github.com/gluonhq/attach/issues/259");
        label.setStyle("-fx-text-alignment: center");
        setAlignment(label, Pos.CENTER);

        ImageView imageView = new ImageView();
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        Button button = new Button("Press the button to take a photo");
        button.setGraphic(new Icon(MaterialDesignIcon.LANGUAGE));
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnAction(e -> PicturesService.create().ifPresent(service -> {
            service.takePhoto(false).ifPresent(image -> imageView.setImage(image));
        }));

        setStyle("-fx-background-color: antiquewhite;");
        setTop(label);
        setCenter(imageView);
        setBottom(button);
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setTitleText("Test Take Photo Application");
    }
}
