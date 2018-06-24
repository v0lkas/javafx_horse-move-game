package game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import static game.Menu.minObjectWidht;
import static game.Menu.maxObjectWidht;
import static game.Menu.minObjectHeight;
import static game.Menu.maxObjectHeight;

public class PlayGame {

    private int x;
    private int y;
    private int ClNr;

    int realRecWidth;
    int realRecHeight;
    int buttonSize;
    int closeSize;
    int buttonsPosition;
    String closeText;

    public PlayGame(int level,int columns,int rows) {

        this.x = columns;
        this.y = rows;

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        // Counting max rectangles number in H & V - some 20px for frame borders
        realRecWidth = (int) (primaryScreenBounds.getWidth() - 20) / x;
        realRecHeight = (int) (primaryScreenBounds.getHeight() - 30) / (y + 1);

        if(realRecWidth > maxObjectWidht) {
            realRecWidth = maxObjectWidht;
        }
        if(realRecHeight > maxObjectHeight) {
            realRecHeight = maxObjectHeight;
        }
        if(realRecWidth > realRecHeight) {
            realRecWidth = realRecHeight;
        } else if(realRecWidth < realRecHeight) {
            realRecHeight = realRecWidth;
        }

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        Color[] colors = {Color.web("0xFFFFFF"), Color.web("0xCCCCCC")};

        for(int row=0;row<x;++row) {
            for(int col=0;col<y;++col) {

                if((row & 1) == (col & 1)) {
                    ClNr = 0;
                } else {
                    ClNr = 1;
                }

                Rectangle rec = new Rectangle();
                rec.setWidth(realRecWidth);
                rec.setHeight(realRecHeight);
                rec.setFill(colors[ClNr]);
                GridPane.setRowIndex(rec, col);
                GridPane.setColumnIndex(rec, row);
                grid.getChildren().addAll(rec);

            }
        }

        int fullWidth = x * realRecWidth;
        int fullHeight = y * realRecHeight + maxObjectHeight;

        if(realRecWidth < 40) {
            buttonSize = 3;
        } else {
            buttonSize = 2;
        }

        if(x < buttonSize * 3) {
            closeSize = 1;
            closeText = "(X)";
        } else {
            closeSize = buttonSize;
            closeText = "(X) close game";
        }

        buttonsPosition = (x - (buttonSize * 2) - closeSize) / 2;

        Rectangle undo = new Rectangle();
        undo.setWidth(realRecWidth * buttonSize);
        undo.setHeight(maxObjectHeight);
        undo.setFill(Color.web("0x333333"));
        undo.setId("gameButton1");
        undo.setStroke(Color.web("0x666666"));
        GridPane.setRowIndex(undo,y + 1);
        GridPane.setColumnIndex(undo, buttonsPosition);
        GridPane.setColumnSpan(undo, buttonSize);
        grid.getChildren().addAll(undo);

        Rectangle redo = new Rectangle();
        redo.setWidth(realRecWidth * buttonSize);
        redo.setHeight(maxObjectHeight);
        redo.setFill(Color.web("0x333333"));
        undo.setStroke(Color.web("0x666666"));
        redo.setId("gameButton2");
        GridPane.setRowIndex(redo, y + 1);
        GridPane.setColumnIndex(redo, buttonsPosition + buttonSize);
        GridPane.setColumnSpan(redo, buttonSize);
        grid.getChildren().addAll(redo);

        Rectangle stop = new Rectangle();
        stop.setWidth(realRecWidth * closeSize);
        stop.setHeight(maxObjectHeight);
        stop.setFill(Color.web("0x333333"));
        undo.setStroke(Color.web("0x666666"));
        stop.setId("gameButton3");
        GridPane.setRowIndex(stop, y + 1);
        GridPane.setColumnIndex(stop, buttonsPosition + (buttonSize * 2));
        GridPane.setColumnSpan(stop, closeSize);
        grid.getChildren().addAll(stop);

        Scene scene = new Scene(grid, fullWidth, fullHeight);

        grid.getStylesheets().add("file:src/files/styles.css");

        Stage playStage = new Stage();

        playStage.setTitle("Moving horse game ["+columns+" x "+rows+"]");
        playStage.getIcons().add(new Image("file:src/files/icon.png"));
        playStage.setScene(scene);
        //playStage.setResizable(false);

        playStage.show();
    }
}
