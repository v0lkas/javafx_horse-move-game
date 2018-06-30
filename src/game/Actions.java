package game;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

import static game.PlayGame.*;

public class Actions {

    public void closeAlert() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close alert!");
        alert.setHeaderText("Are you sure you want to close the game?");
        alert.setContentText("What do you want to do?");

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
                new PlayGame(lvl, x, y);
            }
        }
    }

    public void undo() {

        if(selected_actual > 0) {

            String last = moves.get(selected_actual);

            Box box = boxes.get(last);
            box.setText("");

            --selected_actual;

            if (lvl > 0) {

                if(selected_actual > 0) {
                    String beforeLast = moves.get(selected_actual);

                    String[] token = String.valueOf(beforeLast).split("_");
                    int row = Integer.parseInt(token[1]);
                    int col = Integer.parseInt(token[0]);

                    ArrayList nextMoves = new Permissions().calculateMoves(col, row);
                    new Levels().level1(nextMoves);
                } else {
                    angles();
                }
            }
        }

        if(selected_actual == 0) {
            undo.setStyle("-fx-background-color:#EEEEEE;");
            undo.setTextFill(Color.WHITE);
        } else {
            undo.setStyle("-fx-background-color:#CCCCCC;");
            undo.setTextFill(Color.BLACK);
        }
        redo.setStyle("-fx-background-color:#CCCCCC;");
        redo.setTextFill(Color.BLACK);
    }

    public void redo() {

        if(selected_actual < selected_max) {

            ++selected_actual;

            String last = moves.get(selected_actual);

            Box box = boxes.get(last);
            box.setText(String.valueOf(selected_actual));

            if (lvl > 0) {

                String beforeLast = moves.get(selected_actual);

                String[] token = String.valueOf(beforeLast).split("_");
                int row = Integer.parseInt(token[1]);
                int col = Integer.parseInt(token[0]);

                ArrayList nextMoves = new Permissions().calculateMoves(col,row);
                new Levels().level1(nextMoves);
            }

            if(selected_actual == selected_max) {
                redo.setStyle("-fx-background-color:#EEEEEE;");
                redo.setTextFill(Color.WHITE);
            } else {
                redo.setStyle("-fx-background-color:#CCCCCC;");
                redo.setTextFill(Color.BLACK);
            }
            undo.setStyle("-fx-background-color:#CCCCCC;");
            undo.setTextFill(Color.BLACK);
        }
    }

    public void angles() {
        String right = String.valueOf(x-1);
        String bottom = String.valueOf(y-1);

        ArrayList<String> list = new ArrayList();
        list.add("0_0");
        list.add("0_"+right);
        list.add(bottom+"_0");
        list.add(bottom+"_"+right);

        new Levels().level1(list);
    }

}
