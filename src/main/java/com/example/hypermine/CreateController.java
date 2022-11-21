package com.example.hypermine;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;

public class CreateController {

    @FXML
    private TextField FileNameField;

    @FXML
    private TextField LevelField;

    @FXML
    private TextField MinesField;

    @FXML
    private TextField SupermineField;

    @FXML
    private TextField TimeField;

    @FXML
    private Button SubmitButton;

    @FXML
    void onSubmitButtonClick(ActionEvent event) {


        File file = new File(".\\");
        try {
            Main.ScenarioCheck(file);
        } catch(InvalidValueException e1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Values");
            alert.setContentText(String.format("Invalid description values...\nTry again.", FileNameField.getText()));
            alert.showAndWait();

        } catch (InvalidDescriptionException e2) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Description");
            alert.setContentText(String.format("Fill all fields and try again...\n", FileNameField.getText()));
            alert.showAndWait();
        }
        // Close the window.
        Stage stage = (Stage) SubmitButton.getScene().getWindow();
        stage.close();
    }

}
