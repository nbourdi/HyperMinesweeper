package com.example.hypermine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {

    public static File Scenario;
    public static void ScenarioCheck(File file) throws InvalidDescriptionException, InvalidValueException {
        // TODO: MAYBE I NEED TO MOVE THE SCENARIO VARIABLE TO A GAME CLASS? I DONT LIKE IT IN MAIN...
        /*
         *  Checks for the validity of the game description .txt.
         *  If it is valid, sets the current game Scenario to that,
         *  otherwise it throws appropriate exceptions.
         */

        int lines = 0;
        int[] line = new int[4];

        try {
            Scanner sc = new Scanner(file);
            int i = 0;
            while (sc.hasNextLine()) {
                if (lines < 4) line[lines] = Integer.parseInt(sc.nextLine());
                lines++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (lines != 4) {
            System.out.println(lines);
            System.out.println("File size in bytes " + file.length());
            throw new InvalidDescriptionException();
        }
        // TODO: tidy up this mess of a statement...
        else if (!(((line[0] == 1) && (line[1] <= 11 && line[1] >= 9) &&
                (line[2] <= 180 && line[2] >= 120) && line[3] == 0)
                || ((line[0] == 2) && (line[1] <= 45 && line[1] >= 35) &&
                (line[2] <= 360 && line[2] >= 240) && line[3] == 1))) {
            throw new InvalidValueException();
        }
        else {
            // TODO: also save/update Mode (line[0], etc... probably in Game? Otherwise here.
            Scenario = file;
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Medialab Minesweeper");
        Image icon = new Image("https://apprecs.org/ios/images/app-icons/256/e7/451931111.jpg");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setWidth(500);
        stage.setHeight(500);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}