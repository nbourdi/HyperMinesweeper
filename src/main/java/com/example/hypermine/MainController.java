package com.example.hypermine;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class MainController {

    public Pane GridArea;
    public Tile[][] Grid = new Tile[9][9];
    public Label StateLabel;
    @FXML
    private Label BombCount;
    @FXML
    private Label Countdown;
    @FXML
    private Label MarkedCount;

    public void setMarkedCount(int markedCount) {
       MarkedCount.setText(Integer.toString(markedCount));
    }
    public void setStateLabel(String s) {
        StateLabel.setText(s);
    }
    @FXML
    protected void onExitButtonClick() {
        System.exit(0);
    }

    @FXML
    protected void onStartButtonClick () {
        try {
        Game new_Game;
        try {
            new_Game = new Game();
        } catch (InvalidValueException | InvalidDescriptionException e) {
            throw new RuntimeException(e);
        }
        BombCount.setText(Integer.toString(Game.getMineCount()));
        MarkedCount.setText("0");
        Countdown.setText("initial"); // put the ticking timer here

        Grid = new_Game.createGrid();

        for (int y = 0; y < Grid.length; y++) {
            for (Tile[] tiles : Grid) {
                GridArea.getChildren().add(tiles[y]);
            }
        }
            StateLabel.setText("Game Running!");
        }catch (NullPointerException pointerException) { StateLabel.setText("You haven't selected a game description...");}
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onRoundsButtonClick () {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("rounds-popup-view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Rounds");
            Image icon = new Image("https://static.thenounproject.com/png/1915404-200.png");
            stage.getIcons().add(icon);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            System.out.println("Could not open Rounds pop-up window...");
        }
    }
    @FXML
    protected void onSolutionButtonClick () {
        Game.lose();
        Game.solution();
    }

    @FXML
    protected void onLoadButtonClick () {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("load-popup-view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Load Game Description");
            Image icon = new Image("https://www.freeiconspng.com/uploads/spinner-icon-29.jpg");
            stage.getIcons().add(icon);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            System.out.println("Could not open Load pop-up window...");
            e.printStackTrace();
        }

    }
    @FXML
    protected void onCreateButtonClick () {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("create-popup-view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Create Game Description");
            Image icon = new Image("https://www.clipartmax.com/png/full/14-149858_add-clipart-to-photo.png");
            stage.getIcons().add(icon);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            System.out.println("Could not open Create pop-up window...");
        }
    }
}