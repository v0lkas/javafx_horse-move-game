package game;

import java.util.ArrayList;

import static game.PlayGame.*;

public class Permissions {

    private int tmpX;
    private int tmpY;
    public ArrayList<String> posMoves = new ArrayList();
    public ArrayList<String> tmpMoves = new ArrayList();

    public ArrayList calculateMoves(int col, int row) {

        tmpX = col + 1;
        tmpY = row + 2;
        if(tmpX >= 0 && tmpY >= 0 && tmpX < x && tmpY < y) {
            tmpMoves.add(tmpX+"_"+tmpY);
        }

        tmpX = col + 1;
        tmpY = row - 2;
        if(tmpX >= 0 && tmpY >= 0 && tmpX < x && tmpY < y) {
            tmpMoves.add(tmpX+"_"+tmpY);
        }

        tmpX = col - 1;
        tmpY = row + 2;
        if(tmpX >= 0 && tmpY >= 0 && tmpX < x && tmpY < y) {
            tmpMoves.add(tmpX+"_"+tmpY);
        }

        tmpX = col - 1;
        tmpY = row - 2;
        if(tmpX >= 0 && tmpY >= 0 && tmpX < x && tmpY < y) {
            tmpMoves.add(tmpX+"_"+tmpY);
        }

        tmpX = col + 2;
        tmpY = row + 1;
        if(tmpX >= 0 && tmpY >= 0 && tmpX < x && tmpY < y) {
            tmpMoves.add(tmpX+"_"+tmpY);
        }

        tmpX = col + 2;
        tmpY = row - 1;
        if(tmpX >= 0 && tmpY >= 0 && tmpX < x && tmpY < y) {
            tmpMoves.add(tmpX+"_"+tmpY);
        }

        tmpX = col - 2;
        tmpY = row + 1;
        if(tmpX >= 0 && tmpY >= 0 && tmpX < x && tmpY < y) {
            tmpMoves.add(tmpX+"_"+tmpY);
        }

        tmpX = col - 2;
        tmpY = row - 1;
        if(tmpX >= 0 && tmpY >= 0 && tmpX < x && tmpY < y) {
            tmpMoves.add(tmpX+"_"+tmpY);
        }


        for(String mv : tmpMoves) {
            if (col >= 0 && row >= 0) {
                Box box = boxes.get(mv);
                if (box.getText() == "" || box.getText() == "*") {
                    posMoves.add(mv);
                }
            }
        }

        return posMoves;

    }

    public boolean checkBox(int col,int row) {

        Box box = boxes.get(col+"_"+row);

        String value = box.getText();

        if(value != "" && value != "*") {
            return false;
        }


        if(selected_actual == 0 && (col == 0 || col == x-1) && (row == 0 || row == y-1)) {
            return true;
        } else if (selected_actual > 0) {

            String last = moves.get(selected_actual);
            String[] actual = last.split("_");

            int tmpCol = Integer.parseInt(actual[0]);
            int tmpRow = Integer.parseInt(actual[1]);

            ArrayList posMoves = calculateMoves(tmpCol,tmpRow);

            for(Object tmpMove : posMoves) {

                if(tmpMove.equals(col+"_"+row)) {
                    return true;
                }
            }

            return false;

        } else {
            return false;
        }

    }

}
