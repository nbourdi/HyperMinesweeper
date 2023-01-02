package com.example.hypermine;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class Tile extends StackPane {
    private final int row;
    private final int column;
    private final boolean isMine;
    private final boolean isSupermine;

    private boolean isMarked = false;

    private int neighMines = 0; // number of neighboring mines -> will determine number if not mine.
    private final Rectangle square;
    private final Image BLANK = new Image("file:src/grid-icons-pack/blank.png");
    private final Image EXPOSED = new Image("file:src/grid-icons-pack/exposed.png");
    private final Image FLAG = new Image("file:src/grid-icons-pack/flag.png");
    private final Image HITMINE = new Image("file:src/grid-icons-pack/hitmine.png");
    private final Image MINE = new Image("file:src/grid-icons-pack/mine.png");

    // TODO: find a supermine.png
    private final Image SUPERMINE = new Image("file:src/grid-icons-pack/hitmine.png");
    private final Image NO_1 = new Image("file:src/grid-icons-pack/number1.png");
    private final Image NO_2 = new Image("file:src/grid-icons-pack/number2.png");
    private final Image NO_3 = new Image("file:src/grid-icons-pack/number3.png");
    private final Image NO_4 = new Image("file:src/grid-icons-pack/number4.png");
    private final Image NO_5 = new Image("file:src/grid-icons-pack/number5.png");
    private final Image NO_6 = new Image("file:src/grid-icons-pack/number6.png");
    private final Image NO_7 = new Image("file:src/grid-icons-pack/number7.png");
    private final Image NO_8 = new Image("file:src/grid-icons-pack/number8.png");

    Image[] num = {EXPOSED, NO_1, NO_2, NO_3, NO_4, NO_5, NO_6, NO_7, NO_8};

    private final ImageView imv = new ImageView(BLANK);
    private List<Tile> neighbors;

    public Tile (int row, int column, boolean isMine, boolean isSupermine) {
        this.row = row;
        this.column = column;
        this.isMine = isMine;
        this.isSupermine = isSupermine;
        int tile_size = 24;
        square = new Rectangle(tile_size, tile_size);
        square.setStroke(Color.GREY);
        this.getChildren().addAll(imv);
        setTranslateX(row * tile_size);
        setTranslateY(column * tile_size);
        setOnMouseClicked(event ->
        {
            if (event.getButton() == MouseButton.PRIMARY)
            {
                this.reveal(this);
                // TODO: add move counter
            } else if (event.getButton() == MouseButton.SECONDARY)
            {
                this.mark(this);
            }
        });
    }

    public void reveal(@NotNull Tile tile) {
        // reveal or open the tile, this should be called from the controller on left click.
        if (tile.isMine) {
            tile.imv.setImage(HITMINE);
            // TODO: reveal all of the grid
            // TODO: end the game

        } else if (tile.isSupermine) {
            tile.imv.setImage(SUPERMINE);
            // TODO: reveal all of the grid
            // TODO: end the game
        } else {
            tile.imv.setImage(num[tile.neighMines]);
            // TODO: reveal neighboring
            for (neighbor: tile.neighbors  // foreach statement in java or filter like that one dude?
                 ) {

            }
            reveal(neighbor);
        }
    }

    public void mark(@NotNull Tile tile) {
        // on right click called.
        tile.isMarked = !tile.isMarked;
        if (isMarked) {
            tile.imv.setImage(FLAG);
            // TODO: if ismine or issupermine... increase the game mark counter.
        }
        else tile.imv.setImage(BLANK);
    }
    public void calcNeighbors (Tile tile) {
        // calculate the number of nearby mines
        int res = 0;
        int x = tile.getRow();
        int y = tile.getColumn();

        // for loop...
        int[] points = new int[] {
                -1, -1,
                -1, 0,
                -1, 1,
                0, -1,
                0, 1,
                1, -1,
                1, 0,
                1, 1
        };

        for (int i = 0; i < points.length; i++) {
            int dx = points[i];
            int dy = points[++i];

            if (x+dx >= 0 && x + dx <= 8 &&
                y + dy >= 0 && y+ dy <= 8) {
                if (Game.MineField[x + dx][y + dy].isMine) tile.neighMines++;}
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setNeighmines (int neighMines) {
        this.neighMines = neighMines;
    }

}