package com.example.hypermine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {
    State state = State.RUNNING;
    private int time;
    private int mineCount;
    private int markedCount = 0;
    private int level;
    private boolean hasSupermine;
    public static File Scenario;
    public static int[] ScenarioCheck(File file) throws InvalidDescriptionException, InvalidValueException {
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
            Scenario = file;
        }
        return line;
    }

    // Game constructor, called each time the start button is pressed.
    public Game(File Scenario) throws InvalidValueException, InvalidDescriptionException {
        int[] line = ScenarioCheck(Scenario);
        mineCount = line[1];
        time = line[2];
        this.hasSupermine = (line[3] == 0) ? false : true;
    }
    public void lose(Game game) {
        game.state = State.LOST;
        // TODO: export to game history & get a popup? to notify
    }

    public void win(Game game) {
        game.state = State.WON;
        // TODO: export to game history & get a popup? to notify
    }
}
