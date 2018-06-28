package game;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static game.Menu.maxObjectWidht;
import static game.Menu.maxObjectHeight;

public class PlayGame {

    public static int x;
    public static int y;
    private int ClNr;

    public int total = x * y;
    public static int selected_actual = 0;
    public static int selected_max = 0;
    public static Map<Integer,String> moves = new HashMap<Integer,String>();
    public static Map<String,Box> boxes = new HashMap<String,Box>();

    int realRecWidth;
    int realRecHeight;
    int buttonSize;
    int closeSize;
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

        Stage playStage = new Stage();

        playStage.setTitle("Moving horse game ["+columns+" x "+rows+"]");
        playStage.getIcons().add(new Image("file:src/files/icon.png"));
        playStage.setScene(scene);
        playStage.setResizable(false);
        // grid.setGridLinesVisible(true);

        playStage.show();
        
        
        
        
        
        
        
        
        Label undo = new Label("< undo");
        undo.setPrefWidth(realRecWidth * buttonSize);
        undo.setPrefHeight(maxObjectHeight);
        undo.setId("gameButton1");
        undo.setAlignment(Pos.CENTER);
        undo.setCursor(Cursor.HAND);
        GridPane.setRowIndex(undo,0);
        GridPane.setColumnIndex(undo, 0);
        GridPane.setColumnSpan(undo, buttonSize);
        grid.getChildren().addAll(undo);

        Label redo = new Label(" redo >");
        redo.setPrefWidth(realRecWidth * buttonSize);
        redo.setPrefHeight(maxObjectHeight);
        redo.setId("gameButton2");
        redo.setAlignment(Pos.CENTER);
        redo.setCursor(Cursor.HAND);
        GridPane.setRowIndex(redo, 0);
        GridPane.setColumnIndex(redo, buttonSize);
        GridPane.setColumnSpan(redo, buttonSize);
        grid.getChildren().addAll(redo);

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
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Choose closing option");
                alert.setHeaderText("What do yuo want to do?");
                alert.setContentText(null);

                ButtonType restart = new ButtonType("Restart");
                ButtonType back = new ButtonType("Back to menu");
                ButtonType close = new ButtonType("Close game");
                ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(restart, back, close, cancel);

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() != cancel) {

                    playStage.close();

                    selected_actual = 0;
                    selected_max = 0;

                    if (result.get() == back) {
                        new Menu().start(new Stage());
                    } else if (result.get() == restart) {
                        new PlayGame(level,columns,rows);
                    }

                }
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
    }
}
