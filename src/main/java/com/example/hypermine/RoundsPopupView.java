package com.example.hypermine;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class RoundsPopupView {
    @FXML
    private GridPane roundsGrid;

    public void displayRounds() {

        /*
         * iterates through the History list backwards
         * to place the last 5 games in the grid cells
         */

        int itr = Main.History.size();
        int rows = Math.min(itr, 5);

        for(int row = 0; row < rows; row++) {
            int[] round = Main.History.get(itr - row - 1);
            for (int col = 0; col < 4; col++) {
                String s = col != 3
                        ? String.valueOf(round[col])
                        : (round[col] == 0 ? "PC" : "PLAYER");
                roundsGrid.add(new Label(s), col + 1, row + 1);
            }
        }
    }
}