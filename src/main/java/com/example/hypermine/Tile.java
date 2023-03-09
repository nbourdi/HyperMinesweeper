package com.example.hypermine;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static com.example.hypermine.Main.Handler;


public class Tile extends StackPane {
    private final int row;
    private final int column;
    private final boolean isMine;
    private final boolean isSupermine;

    private boolean isMarked = false;

    private boolean isRevealed = false;

    private int neighMines = 0; // number of neighboring mines -> will determine number if not mine.
    private final Image BLANK = new Image("file:src/grid-icons-pack/blank.png");
    private final Image EXPOSED = new Image("file:src/grid-icons-pack/exposed.png");
    private final Image FLAG = new Image("file:src/grid-icons-pack/flag.png");
    private final Image HITMINE = new Image("file:src/grid-icons-pack/hitmine.png");
    private final Image MINE = new Image("file:src/grid-icons-pack/mine.png");
    private final Image SUPERMINE = new Image("file:src/grid-icons-pack/supermine.png");
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
    private List<Tile> neighbors = new ArrayList<>();

    public Tile (int row, int column, boolean isMine, boolean isSupermine) {
        this.row = row;
        this.column = column;
        this.isMine = isMine;
        this.isSupermine = isSupermine;
        int tile_size = 24;
        Rectangle square = new Rectangle(tile_size, tile_size);
        square.setStroke(Color.GREY);
        this.getChildren().addAll(imv);
        setTranslateX(row * tile_size);
        setTranslateY(column * tile_size);
        setOnMouseClicked(event ->
        {
            if (event.getButton() == MouseButton.PRIMARY)
            {
                Game.setMoveCount(Game.getMoveCount() + 1);
                this.reveal(this);
            } else if (event.getButton() == MouseButton.SECONDARY)
            {
                this.mark(this);
            }
        });
    }

    public void reveal(@NotNull Tile tile) {
        Predicate<Tile> rev = t -> (!t.isRevealed && !t.isMine && !t.isMarked);
        // reveal or open the tile, this should be called from the controller on left click.
        if (tile.isMine) {
            tile.imv.setImage(HITMINE);
            Game.lose();
        } else if (tile.isSupermine) {
            tile.imv.setImage(SUPERMINE);
            Game.lose();
        } else {
            Game.winCheck();
            tile.isRevealed = true;
            if (tile.isMarked) tile.mark(tile);
            tile.imv.setImage(num[tile.neighMines]);
            if(tile.neighMines == 0)
                tile.neighbors.stream().filter(rev).forEach(this::reveal);
        }
    }


    // Called on right click on tile to either mark or unmark.
    public void mark(@NotNull Tile tile) {
        if (!isMarked && !isRevealed && Game.getMarkedCount() < Game.getMineCount()) {
            tile.imv.setImage(FLAG);
            Game.setMarkedCount(Game.getMarkedCount() + 1);
            Game.setMoveCount(Game.getMoveCount() + 1);
            Handler.setMarkedCount(Game.getMarkedCount());
            tile.isMarked = !tile.isMarked;
            if(tile.isSupermine && Game.getMoveCount() <= 4) {
                cross_reveal(tile);
            }
        }
        else if (isMarked){
            tile.imv.setImage(BLANK);
            Game.setMarkedCount(Game.getMarkedCount() - 1);
            Handler.setMarkedCount(Game.getMarkedCount());
            tile.isMarked = !tile.isMarked;
        }
    }

    private void cross_reveal(Tile tile) {
        int col = tile.getColumn();
        int row = tile.getRow();
        for (int i = 0; i < Game.MineField.length; i++) {
            Tile itile = Game.MineField[i][col];
            itile.simple_reveal(itile);
            itile.isRevealed = true;
            if (itile.isMarked) itile.mark(itile);
        }
        for (int i = 0; i < Game.MineField.length; i++) {
            Tile itile = Game.MineField[row][i];
            itile.simple_reveal(itile);
            itile.isRevealed = true;
            if (itile.isMarked) itile.mark(itile);
        }
    }

    public void simple_reveal(Tile tile) {
        if (tile.isSupermine) tile.imv.setImage(SUPERMINE);
        else if (tile.isMine) tile.imv.setImage(HITMINE);
        else tile.imv.setImage(num[tile.neighMines]);
    }
    public void calcNeighbors (Tile tile, int dimension) {
        // calculate the number of nearby mines
        int res = 0;
        int x = tile.getRow();
        int y = tile.getColumn();

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

            if (x+dx >= 0 && x + dx < dimension &&
                y + dy >= 0 && y+ dy < dimension) {
                if (Game.MineField[x + dx][y + dy].isMine) tile.neighMines++;
                tile.neighbors.add(Game.MineField[x+dx][y+dy]);
            }

        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}