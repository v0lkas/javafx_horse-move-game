package game;

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

import java.util.ArrayList;

import static game.PlayGame.*;

public class MouseClick {

    public MouseClick(int col, int row) throws Exception {

        Box box = boxes.get(col+"_"+row);

        if (new Permissions().checkBox(col,row) == true) {

            ++selected_actual;                      // maximize selection
            selected_max = selected_actual;         // set max selection

            box.setText(" ");                       // set blank text inside the box
            box.setStyle("-fx-background:transparent;");     // set box to transparent

            moves.put(selected_actual,col+"_"+row); // set moves counter

            ArrayList nextMoves = new Permissions().calculateMoves(col,row);

            undo.setStyle("-fx-background-image:url('file:src/files/undo.png');");
            redo.setStyle("-fx-background-image:url('file:src/files/redo-off.png');");

            if(space.getText() != "") {
                space.setText(selected_actual+" / "+total);
            }

            if (selected_actual == total) {

                new GameOver().gameWonAlert();

            } else if (nextMoves.size() == 0) {

                new GameOver().gameOverAlert();

            } else if (lvl > 0) {

                new Levels().level1(nextMoves);

            }

        } else {

            box.setStyle("-fx-background-color:#ff0000; -fx-font:"+fontSize+" 'Courier-New';");

            if(box.getText() == " ") {
                setTimeout(() -> box.setStyle("-fx-background-color:transparent; -fx-font:"+fontSize+" 'Courier-New';"), 100);
            } else {

                int ClNr = new PlayGame().boxCalc(row,col);

                setTimeout(() -> box.setStyle("-fx-background-color:"+colors[ClNr]+"; -fx-font:"+fontSize+" 'Courier-New';"), 100);
            }

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
