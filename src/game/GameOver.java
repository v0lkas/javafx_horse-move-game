package game;

import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

import static game.PlayGame.*;

public class GameOver {

    public void gameOverAlert() throws Exception {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Gave over");
        alert.setHeaderText("You filled "+selected_actual+" out of "+total+" fields.");
        alert.setContentText("What would you like to do next?");

        ButtonType restartGame = new ButtonType("Restart");
        ButtonType backToGame = new ButtonType("Back to game", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType backToMenu = new ButtonType("Back to menu");
        ButtonType closeGame = new ButtonType("Close game");

        alert.getButtonTypes().setAll(restartGame, backToGame, backToMenu, closeGame);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() != backToGame) {

            playStage.close();

            selected_actual = 0;
            selected_max = 0;

            if (result.get() == backToMenu) {
                new Menu().start(new Stage());
            } else if (result.get() == restartGame) {
                new PlayGame(lvl,x,y,thematics);
            }

        }

    }

    public void gameWonAlert() throws Exception {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("You won the game!");
        alert.setHeaderText("You filled "+selected_actual+" out of "+total+" fields.\nThat is great!!!");
        alert.setContentText("What would you like to do next?");

        ButtonType restartGame = new ButtonType("Restart");
        ButtonType backToMenu = new ButtonType("Back to menu");
        ButtonType closeGame = new ButtonType("Close game");

        alert.getButtonTypes().setAll(restartGame, backToMenu, closeGame);

        Optional<ButtonType> result = alert.showAndWait();

        playStage.close();

        selected_actual = 0;
        selected_max = 0;

        if (result.get() == backToMenu) {
            new Menu().start(new Stage());
        } else if (result.get() == restartGame) {
            new PlayGame(lvl,x,y,thematics);
        }

    }

}
