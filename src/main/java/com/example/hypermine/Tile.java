package com.example.hypermine;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.File;

public class Tile extends StackPane {
    private final int row;
    private final int column;
    private final boolean isMine;
    private final boolean isSupermine;

    private final int neighMines = 0; // number of neighboring mines -> will determine number if not mine.
    private final Rectangle square;
    private final Image BLANK = new Image("file:src/grid-icons-pack/blank.png");
    private final Image EXPOSED = new Image("file:src/grid-icons-pack/exposed.png");
    private final Image FLAG = new Image("file:src/grid-icons-pack/flag.png");
    private final Image HITMINE = new Image("file:src/grid-icons-pack/hitmine.png");
    private final Image MINE = new Image("file:src/grid-icons-pack/mine.png");
    private final Image NO_1 = new Image("file:src/grid-icons-pack/number1.png");
    private final Image NO_2 = new Image("file:src/grid-icons-pack/number2.png");

    private final Text text = new Text();
    // Tile constructor
    private final Image image = new Image(new File("mine.png").toURI().toString());
    private final ImageView imv = new ImageView(BLANK);
    public Tile (int row, int column, boolean isMine, boolean isSupermine, int tile_size) {
        this.row = row;
        this.column = column;
        this.isMine = isMine;
        this.isSupermine = isSupermine;
        square = new Rectangle(tile_size, tile_size);
        square.setStroke(Color.GREY);
       // square.setFill(Color.LIGHTGREY);
        text.setStroke(Color.BLACK);
        imv.setImage(isMine ? MINE : isSupermine ? HITMINE : BLANK);
        text.setText(isMine ? "x" : "n");
        this.getChildren().addAll(imv);
        setTranslateX(row * tile_size);
        setTranslateY(column * tile_size);
    }

    public void reveal(Tile tile) {
        // reveal or open the tile, this should be called from the controller on left click.

    }
    private int calcNeighbors (Tile tile) {
        // calculate the number of nearby mines
        int res = 0;
        // for loop...
        return res;
    }
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

}