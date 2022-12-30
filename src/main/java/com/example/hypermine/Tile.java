package com.example.hypermine;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane {
    private final int row;
    private final int column;
    private final boolean isMine;
    private final boolean isSupermine;
    // private bombs

    private final Rectangle square;
    private final Image BLANK = new Image("../../../src/grid-icons-pack/blank.png");
    private final Image EXPOSED = new Image("../../../src/grid-icons-pack/exposed.png");
    private final Image FLAG = new Image("../../../src/grid-icons-pack/flag.png");
    private final Image HITMINE = new Image("../../../src/grid-icons-pack/hitmine.png");
    private final Image MINE = new Image("../../../src/grid-icons-pack/mine.png");
    private final Image NO_1 = new Image("../../../src/grid-icons-pack/number1.png");
    private final Image NO_2 = new Image("../../../src/grid-icons-pack/number2.png");
    // Tile constructor

    private final ImageView imv = new ImageView();

    public Tile (int row, int column, boolean isMine, boolean isSupermine, int tile_size) {
        this.row = row;
        this.column = column;
        this.isMine = isMine;
        this.isSupermine = isSupermine;
        square = new Rectangle(tile_size, tile_size);

        imv.setImage(isMine ? MINE : isSupermine ? HITMINE : BLANK);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

}