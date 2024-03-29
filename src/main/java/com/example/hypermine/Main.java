package com.example.hypermine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main extends Application {

    static MainController Handler = new MainController();
    static RoundsPopupView RoundsHandler = new RoundsPopupView();
    public static List<int[]> History = new ArrayList<int[]>();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Medialab Minesweeper");
        Image icon = new Image("https://apprecs.org/ios/images/app-icons/256/e7/451931111.jpg");
        stage.getIcons().add(icon);
        stage.setResizable(true);
        stage.setWidth(320);
        stage.setHeight(400);
        stage.setScene(scene);
        stage.show();
        Handler = fxmlLoader.getController();
    }

    public static void main(String[] args) {
        launch(args);
    }
}