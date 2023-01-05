package com.example.hypermine;

import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.example.hypermine.Main.Handler;

public class Game {
    static State state = State.RUNNING;
    private int time;
    private static int mineCount;
    private static int markedCount = 0;
    private static int moveCount = 0;
    private final boolean hasSupermine;
    public static File Scenario;
    public static Tile[][] MineField;
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
    public Game() throws InvalidValueException, InvalidDescriptionException {
        int[] line = ScenarioCheck(Scenario);
        mineCount = line[1];
        time = line[2];
        this.hasSupermine = (line[3] == 0) ? false : true;
        moveCount = 0;
        markedCount = 0;
    }
    public static void lose() {
        state = State.LOST;
        solution();
        Handler.setStateLabel("You lost =( ... Click Start to play again.");
        // TODO: export to game history
    }

    public static void win() {
        state = State.WON;
        solution();
        Handler.setStateLabel("You won! Click Start to play again.");
        // TODO: export to game history
    }

    public static void solution() {
        for (Tile[] tiles : MineField) {
            for (int j = 0; j < MineField.length; j++) tiles[j].simple_reveal(tiles[j]);
        }

    }

    public Tile[][] createGrid() throws IOException {
        int dimension = hasSupermine ? 16 : 9;
        MineField = new Tile[dimension][dimension];


        // create the random mine positions and
        List<Pair<Integer, Integer>> minePos = new ArrayList<>();
        String minesTXT = "./src/main/java/com/example/hypermine/medialab/mines.txt";

        File file = new File(minesTXT);
        if (file.createNewFile()) System.out.println("File created...");
        FileWriter myWriter = new FileWriter(minesTXT);

        int m = hasSupermine ? mineCount - 1 : mineCount;
        int r1, r2;
        for (int i = 0; i < m; i++) {
            r1 = (int)(Math.random() * dimension);
            r2 = (int)(Math.random() * dimension);
            Pair<Integer, Integer> pair = new Pair<>(r1, r2);
            if (!minePos.contains(pair)) {
                minePos.add(pair);

                myWriter.write(String.format("%d, %d, %d\n",r1, r2, 0));
                Tile tile = new Tile(r1, r2, true, false);
                MineField[r1][r2] = tile;
            }
            else i--;
        }
        if (hasSupermine) {
            r1 = (int)(Math.random() * dimension);
            r2 = (int)(Math.random() * dimension);
            Pair<Integer, Integer> pair = new Pair<>(r1, r2);
            if (!minePos.contains(pair)) {
                minePos.add(pair);
                myWriter.write(String.format("%d, %d, %d",r1, r2, 1));
                Tile tile = new Tile(r1, r2, true, true);
                MineField[r1][r2] = tile;
            }
        }
        myWriter.close();

        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                if(!minePos.contains(new Pair<>(x, y))) {
                    Tile tile = new Tile(x, y, false, false);
                    MineField[x][y] = tile;
                }
            }
        }
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                MineField[x][y].calcNeighbors(MineField[x][y], dimension);
            }
        }
        return MineField;
    }

    public static int getMineCount() {
        return mineCount;
    }

    public static int getMarkedCount() {
        return markedCount;
    }

    public static void setMarkedCount(int i) {
        markedCount = i;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public static int getMoveCount() {
        return moveCount;
    }

    public static void setMoveCount(int i) {
        moveCount = i;
    }
}
