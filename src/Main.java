import game.Menu;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class Main {

    public void sizing() {

        int objectWidht = 30;   // rectangles width
        int objectHeight = 30;  // rectangles height

        int freeLcdWidth;
        int freeLcdHeight;

        int maxVertical;
        int maxHorizontal;

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        // Detecting LCD heigth & width after excluding taskbar size
        freeLcdWidth = (int) primaryScreenBounds.getWidth();
        freeLcdHeight = (int) primaryScreenBounds.getHeight();

        // Counting max rectangles number in H & V - some 20px for frame borders
        maxHorizontal = (freeLcdWidth - 20) / objectWidht;
        maxVertical = (freeLcdHeight - 20) / objectHeight;
    }

    public static void main(String[] args) {

        Menu.launch(Menu.class, args);

    }

}
