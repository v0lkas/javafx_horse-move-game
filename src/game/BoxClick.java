package game;

import java.util.ArrayList;

import static game.PlayGame.*;

public class BoxClick {

    public BoxClick(int col,int row) {

        if(new Permissions().checkBox(col,row) == true) {

            ++selected_actual;
            selected_max = selected_actual;

            Box box = boxes.get(col+"_"+row);
            box.setText(""+ selected_actual);


            moves.put(selected_actual,col+"_"+row);
        }

    }

}
