package game;

import java.util.ArrayList;

import static game.PlayGame.*;

public class Levels {

    int ClNr;
    String thisColor;

    public void level1(ArrayList posMoves) {

        for(int row=0;row<x;++row) {
            for(int col=0;col<y;++col) {

                Box box = boxes.get(col+"_"+row);

                if(box.getText() == "") {

                    ClNr = new PlayGame().boxCalc(row, col);

                    box.setStyle("-fx-background-color:" + colors[ClNr] + "; -fx-font:" + fontSize + " 'Courier-New';");
                }
            }
        }

        for(Object tmpMove : posMoves) {

            Box box = boxes.get(tmpMove);
            box.setStyle("-fx-background-color:#d6ff00; -fx-font:"+fontSize+" 'Courier-New';");

        }

    }

    public void level2(String type, ArrayList posMoves) {

        for(Object tmpMove : posMoves) {

            Box box = boxes.get(tmpMove);

            if(type.equals("on")) {
                thisColor = "#9999ff";
            } else {

                String[] token = String.valueOf(tmpMove).split("_");
                int row = Integer.parseInt(token[0]);
                int col = Integer.parseInt(token[1]);

                ClNr = new PlayGame().boxCalc(row,col);

                thisColor = colors[ClNr];

            }

            box.setStyle("-fx-background-color:"+thisColor+"; -fx-font:"+fontSize+" 'Courier-New';");

        }

    }

}
