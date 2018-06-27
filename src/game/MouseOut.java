package game;

import static game.PlayGame.*;

public class MouseOut {

    public MouseOut(int col,int row) {

        Box box = boxes.get(col+"_"+row);

        String value = box.getText();

        if(value == "*" || value == "") {
            box.setText("");
        } else {
            System.out.println(value);
        }

    }

}
