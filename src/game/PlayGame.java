package game;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static game.Menu.maxObjectWidht;
import static game.Menu.maxObjectHeight;

public class PlayGame {

    public static int x;
    public static int y;
    public static int lvl;
    public static int total;
    public static String fontSize;

    public static int selected_actual = 0;
    public static int selected_max = 0;
    public static Map<Integer,String> moves = new HashMap<Integer,String>();
    public static Map<String,Box> boxes = new HashMap<String,Box>();

    public static Stage playStage = new Stage();
    public static String[] colors = {"#FFFFFF","#DDDDDD"};
    public static Label undo = new Label("< undo");;
    public static Label redo = new Label("redo >");;

    private int ClNr;

    int realRecWidth;
    int realRecHeight;
    int buttonSize;
    int closeSize;
    String closeText;

    public PlayGame() {};

    public PlayGame(int level,int columns,int rows) {

        this.x = columns;
        this.y = rows;
        this.lvl = level;
        this.total = x * y;

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

        if (realRecWidth < 33) {
            fontSize = "12px";
        } else if (realRecWidth < 36) {
            fontSize = "13px";
        } else if (realRecWidth < 36) {
            fontSize = "13px";
        } else if (realRecWidth < 39) {
            fontSize = "14px";
        } else if (realRecWidth < 42) {
            fontSize = "15px";
        } else if (realRecWidth < 45) {
            fontSize = "16px";
        } else if (realRecWidth < 48) {
            fontSize = "17px";
        } else {
            fontSize = "18px";
        }

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        for(int row=0;row<x;++row) {
            for(int col=0;col<y;++col) {

                ClNr = boxCalc(row,col);

                Box rec = new Box(colors[ClNr],realRecWidth,realRecHeight,col,row);

                boxes.put(col+"_"+row,rec);

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








        Scene scene = new Scene(grid, fullWidth, fullHeight);

        grid.getStylesheets().add("file:src/files/styles.css");

        playStage.setTitle("Moving horse game ["+columns+" x "+rows+"]");
        playStage.getIcons().add(new Image("file:src/files/icon.png"));
        playStage.setScene(scene);
        playStage.setResizable(false);
        // grid.setGridLinesVisible(true);

        playStage.show();
        
        
        
        
        
        
        
        

        undo.setPrefWidth(realRecWidth * buttonSize);
        undo.setPrefHeight(maxObjectHeight);
        undo.setId("gameButton1");
        undo.setAlignment(Pos.CENTER);
        undo.setCursor(Cursor.HAND);
        undo.setTextFill(Color.WHITE);
        GridPane.setRowIndex(undo,0);
        GridPane.setColumnIndex(undo, 0);
        GridPane.setColumnSpan(undo, buttonSize);
        grid.getChildren().addAll(undo);

        undo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                new Actions().undo();
            }
        });

        redo.setPrefWidth(realRecWidth * buttonSize);
        redo.setPrefHeight(maxObjectHeight);
        redo.setId("gameButton2");
        redo.setAlignment(Pos.CENTER);
        redo.setCursor(Cursor.HAND);
        redo.setTextFill(Color.WHITE);
        GridPane.setRowIndex(redo, 0);
        GridPane.setColumnIndex(redo, buttonSize);
        GridPane.setColumnSpan(redo, buttonSize);
        grid.getChildren().addAll(redo);

        redo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                new Actions().redo();
            }
        });

        Label stop = new Label(closeText);
        stop.setPrefWidth(realRecWidth * closeSize);
        stop.setPrefHeight(maxObjectHeight);
        stop.setId("gameButton3");
        stop.setAlignment(Pos.CENTER);
        stop.setCursor(Cursor.HAND);
        GridPane.setRowIndex(stop, 0);
        GridPane.setColumnIndex(stop, x - closeSize);
        GridPane.setColumnSpan(stop, closeSize);
        grid.getChildren().addAll(stop);

        stop.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                new Actions().closeAlert();
            }
        });



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

        if(lvl > 0) {
            new Actions().angles();
        }
    }

    public int boxCalc(int row, int col) {

        if((row & 1) == (col & 1)) {
            return 0;
        } else {
            return 1;
        }
    }
}
