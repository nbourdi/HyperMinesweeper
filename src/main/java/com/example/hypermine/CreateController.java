package com.example.hypermine;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
    void onSubmitButtonClick(ActionEvent event) throws IOException {

        /*
         * Upon clicking submit, a file is created
         * using the text fields' input and then
         * sent to Main to check its validity.
         * In case it's invalid, an alert widow
         * pops up and informs the user.
         */

        String ScenarioID = String.format(
                "./src/main/java/com/example/hypermine/medialab/%s.txt", FileNameField.getText());

        File file = new File(ScenarioID);
        if (file.createNewFile()) System.out.println("File created...");
        try {
            FileWriter myWriter = new FileWriter(ScenarioID);
            myWriter.write(
                    String.format("%s\n%s\n%s\n%s",
                            LevelField.getText(), MinesField.getText(),
                            TimeField.getText(), SupermineField.getText()
                    )
            );
            myWriter.close();
        } catch (IOException e) {
            System.out.println("FileWriter caused an exception...");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        try {
            Game.ScenarioCheck(file);
        } catch(InvalidValueException e1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Values");
            alert.setContentText("Invalid description values...\nTry again.");
            alert.showAndWait();

        } catch (InvalidDescriptionException e2) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Description");
            alert.setContentText("Fill all fields and try again...\n");
            alert.showAndWait();
        }

        // Close the pop-up window.
        Stage stage = (Stage) SubmitButton.getScene().getWindow();
        stage.close();
    }

}
