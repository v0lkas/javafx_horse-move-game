package game;

import javafx.scene.Cursor;

import static game.PlayGame.*;

public class MouseOver {

    public MouseOver(int col,int row) {

        Box box = boxes.get(col+"_"+row);

        if(new Permissions().checkBox(col,row) == true) {

        } else {

        }

    }

}
