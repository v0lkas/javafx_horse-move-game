package game;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import static game.Menu.maxObjectWidht;
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

        //Color[] colors = {Color.web("0xFFFFFF"), Color.web("0xDDDDDD")};
        String[] colors = {"#FFFFFF","#DDDDDD"};

        for(int row=0;row<x;++row) {
            for(int col=0;col<y;++col) {

                if((row & 1) == (col & 1)) {
                    ClNr = 0;
                } else {
                    ClNr = 1;
                }

                Label rec = new Label(String.valueOf(" "));
                rec.setStyle("-fx-background-color:"+colors[ClNr]+"; -fx-font:12px 'Courier-New';");
                rec.setPrefWidth(realRecWidth);
                rec.setPrefHeight(realRecHeight);
                rec.setAlignment(Pos.CENTER);
                rec.setCursor(Cursor.HAND);
                GridPane.setRowIndex(rec, col+1);
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
            closeText = "Close";
        }

        Label undo = new Label(String.valueOf("< undo"));
        undo.setPrefWidth(realRecWidth * buttonSize);
        undo.setPrefHeight(maxObjectHeight);
        undo.setId("gameButton1");
        undo.setAlignment(Pos.CENTER);
        undo.setCursor(Cursor.HAND);
        GridPane.setRowIndex(undo,0);
        GridPane.setColumnIndex(undo, 0);
        GridPane.setColumnSpan(undo, buttonSize);
        grid.getChildren().addAll(undo);

        Label redo = new Label(String.valueOf(" redo >"));
        redo.setPrefWidth(realRecWidth * buttonSize);
        redo.setPrefHeight(maxObjectHeight);
        redo.setId("gameButton2");
        redo.setAlignment(Pos.CENTER);
        redo.setCursor(Cursor.HAND);
        GridPane.setRowIndex(redo, 0);
        GridPane.setColumnIndex(redo, buttonSize);
        GridPane.setColumnSpan(redo, buttonSize);
        grid.getChildren().addAll(redo);

        Label stop = new Label(String.valueOf(closeText));
        stop.setPrefWidth(realRecWidth * closeSize);
        stop.setPrefHeight(maxObjectHeight);
        stop.setId("gameButton3");
        stop.setAlignment(Pos.CENTER);
        stop.setCursor(Cursor.HAND);
        GridPane.setRowIndex(stop, 0);
        GridPane.setColumnIndex(stop, x - closeSize);
        GridPane.setColumnSpan(stop, closeSize);
        grid.getChildren().addAll(stop);

        if(x > (buttonSize * 2 + closeSize)) {
            int spaceSize = x - buttonSize * 2 - closeSize;

            Label space = new Label("");
            space.setPrefWidth(realRecWidth * spaceSize);
            space.setPrefHeight(maxObjectHeight);
            space.setStyle("-fx-background-color:#FFFFFF; -fx-border-color: #999999;");
            GridPane.setRowIndex(space, 0);
            GridPane.setColumnIndex(space, buttonSize * 2);
            GridPane.setColumnSpan(space, spaceSize);
            grid.getChildren().addAll(space);
        }

        Scene scene = new Scene(grid, fullWidth, fullHeight);

        grid.getStylesheets().add("file:src/files/styles.css");

        Stage playStage = new Stage();

        playStage.setTitle("Moving horse game ["+columns+" x "+rows+"]");
        playStage.getIcons().add(new Image("file:src/files/icon.png"));
        playStage.setScene(scene);
        playStage.setResizable(false);
        // grid.setGridLinesVisible(true);

        playStage.show();
    }
}
