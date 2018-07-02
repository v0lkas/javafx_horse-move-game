package game;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

import static game.PlayGame.*;

public class Actions {

    public void closeAlert() throws Exception {

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

            undo.setStyle("-fx-background-image:url('file:src/files/undo-off.png');");
            redo.setStyle("-fx-background-image:url('file:src/files/redo-off.png');");

            if (result.get() == back) {
                new Menu().start(new Stage());
            } else if (result.get() == restart) {
                new PlayGame(lvl, x, y,thematics);
            }
        }
    }

    public void undo() {

        if(selected_actual > 0) {

            String last = moves.get(selected_actual);

            Box box = boxes.get(last);
            box.setText("");

            String[] token0 = String.valueOf(last).split("_");
            int row0 = Integer.parseInt(token0[1]);
            int col0 = Integer.parseInt(token0[0]);

            int ClNr = new PlayGame().boxCalc(row0,col0);
            box.setStyle("-fx-background-color:"+colors[ClNr]+"; -fx-font:"+fontSize+" 'Courier-New';");



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
            undo.setStyle("-fx-background-image:url('file:src/files/undo-off.png');");
        } else {
            undo.setStyle("-fx-background-image:url('file:src/files/undo.png');");
        }

        if(selected_max == 0) {
            redo.setStyle("-fx-background-image:url('file:src/files/redo-off.png');");
        } else {
            redo.setStyle("-fx-background-image:url('file:src/files/redo.png');");
        }

        if(space.getText() != "") {
            space.setText(selected_actual+" / "+total);
        }
    }

    public void redo() {

        if(selected_actual < selected_max) {

            ++selected_actual;

            String last = moves.get(selected_actual);

            Box box = boxes.get(last);
            // box.setText(String.valueOf(selected_actual));
            box.setText(" ");
            box.setStyle("-fx-background:transparent;");

            if (lvl > 0) {

                String beforeLast = moves.get(selected_actual);

                String[] token = String.valueOf(beforeLast).split("_");
                int row = Integer.parseInt(token[1]);
                int col = Integer.parseInt(token[0]);

                ArrayList nextMoves = new Permissions().calculateMoves(col,row);
                new Levels().level1(nextMoves);
            }

            if(selected_actual == selected_max) {
                redo.setStyle("-fx-background-image:url('file:src/files/redo-off.png');");
            } else {
                redo.setStyle("-fx-background-image:url('file:src/files/redo.png');");
            }
            undo.setStyle("-fx-background-image:url('file:src/files/undo.png');");

            if(space.getText() != "") {
                space.setText(selected_actual+" / "+total);
            }
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
