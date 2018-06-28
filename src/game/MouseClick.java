package game;

import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

import static game.PlayGame.*;

public class MouseClick {

    public MouseClick(int col, int row) {

        Box box = boxes.get(col+"_"+row);

        if(new Permissions().checkBox(col,row) == true) {

            ++selected_actual;
            selected_max = selected_actual;

            box.setText(""+ selected_actual);

            moves.put(selected_actual,col+"_"+row);

        } else {

            Image notAllowed = new Image("file:src/files/not-allowed.png");
            box.setCursor(new ImageCursor(notAllowed));

        }

    }

}
