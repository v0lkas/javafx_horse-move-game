package game;

import java.util.ArrayList;

import static game.PlayGame.*;

public class MouseMove {

    public MouseMove(String type, int col,int row) {

        if(lvl == 2) {

            Box box = boxes.get(col + "_" + row);

            if (new Permissions().checkBox(col, row) == true) {

                ArrayList nextMoves = new Permissions().calculateMoves(col,row);
                new Levels().level2(type,nextMoves);

            }
        }

    }

}
