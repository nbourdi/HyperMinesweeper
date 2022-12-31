package com.example.hypermine;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;

public class LoadPopupView {

    @FXML
    private TextField FilenameField;

    @FXML
    private Button OKButton;

    @FXML
    public void onOKButtonClick(ActionEvent event) {

        // TODO: look for the SCENARIO-ID.txt file ....

        String ScenarioID = String.format("./src/main/java/com/example/hypermine/medialab/%s.txt", FilenameField.getText());
        File file = new File(ScenarioID);
        if(file.exists()) {
            try {
                Game.ScenarioCheck(file);
            } catch(InvalidValueException e1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Values");
                alert.setContentText(String.format("%s.txt contains invalid description values...\nEdit the file to comply with the required format and try again!", FilenameField.getText()));
                alert.showAndWait();

            } catch (InvalidDescriptionException e2) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Description");
                alert.setContentText(String.format("%s.txt doesn't contain the 4 lines expected... \nEdit the file to comply with the required format and try again!", FilenameField.getText()));
                alert.showAndWait();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File not Found");
            alert.setHeaderText("");
            alert.setContentText(String.format("%s.txt doesn't exist in the default 'medialab' directory...", FilenameField.getText()));
            alert.showAndWait();
        }

        // Close the window.
        Stage stage = (Stage) OKButton.getScene().getWindow();
        stage.close();
    }

}
