package game;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FieldsGenerator {

    private int x;
    private int y;
    private int ClNr;

    public FieldsGenerator(int x, int y) {

        this.x = x;
        this.y = y;

        GridPane grid = new GridPane();
        Color[] colors = {Color.web("0xFFFFFF"), Color.web("0xCCCCCC")};

        for(int row=0;row<x;++row) {
            for(int col=0;col<y;++col) {

                if((row & 1) == (col & 1)) {
                    ClNr = 0;
                } else {
                    ClNr = 1;
                }

                Rectangle rec = new Rectangle();
                rec.setWidth(30);
                rec.setHeight(30);
                rec.setFill(colors[ClNr]);
                GridPane.setRowIndex(rec, row);
                GridPane.setColumnIndex(rec, col);
                grid.getChildren().addAll(rec);

            }
        }
    }

}
