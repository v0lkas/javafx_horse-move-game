package game;

import javafx.scene.control.Alert;

public class About {

    public void about() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About horse move game");
        alert.setHeaderText("Horse move game");
        alert.setContentText("This is logic game where you have to fill all of the squares.\n\n" +
                "You have to start from any of four corners.\n\n" +
                "You can move only as chess horse figurine moves (\"L\" move):\n" +
                "\tleft/right +1 + top/bottom +2\n" +
                "\tleft/right +2 + top/bottom +1\n\n" +
                "You can choose any level of game from 5x5 to your max resolution allowed size (for example, if your resolution is 1920x1280, then you can generate up to 63x31 squares).\n\n" +
                "You can choose between no help, showing next possible moves and showing next possible moves + next possible moves if you move mouse over square.\n\n" +
                "If you lost game you can push \"Back to game\" button and undo some moves. So you can play same game from some place instead of starting it over from zero.\n\n\n" +
                "Have fun!\n" +
                "Vitalij Vladimirov\n" +
                "horsemovegame@vladimirov.lt");
        alert.showAndWait();

    }

}
