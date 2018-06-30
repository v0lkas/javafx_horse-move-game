package game;

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static game.PlayGame.*;

public class MouseClick {

    public MouseClick(int col, int row) {

        Box box = boxes.get(col+"_"+row);

        if (new Permissions().checkBox(col,row) == true) {

            ++selected_actual;                      // maximize selection
            selected_max = selected_actual;         // set max selection

            box.setText(""+ selected_actual);       // set number inside the box

            moves.put(selected_actual,col+"_"+row); // set moves counter

            ArrayList nextMoves = new Permissions().calculateMoves(col,row);

            if (selected_actual == total) {

                new GameOver().gameWonAlert();

            } else if (nextMoves.size() == 0) {

                new GameOver().gameOverAlert();

            } else if (lvl > 0) {

                new Levels().level1(nextMoves);

            }

            undo.setStyle("-fx-background-color:#CCCCCC;");
            undo.setTextFill(Color.BLACK);
            redo.setStyle("-fx-background-color:#EEEEEE;");
            redo.setTextFill(Color.WHITE);

        } else {

            Image notAllowed = new Image("file:src/files/not-allowed.png");
            box.setCursor(new ImageCursor(notAllowed));

            setTimeout(() -> box.setCursor(Cursor.HAND), 100);  // set cursor "not-allowed" for 0.1 second

        }

    }

    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }

}
