package game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import static java.lang.Short.valueOf;

public class Menu extends Application {

    public static int minObjectWidht = 30;   // min rectangles width
    public static int minObjectHeight = 30;  // min rectangles height

    public static int maxObjectWidht = 50;   // max rectangles width
    public static int maxObjectHeight = 50;  // max rectangles height

    public static int freeLcdWidth;
    public static int freeLcdHeight;

    int maxVertical;
    int maxHorizontal;

    int minVertical = 5;
    int minHorizontal = 5;

    public void sizing() {

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        // Detecting LCD heigth & width after excluding taskbar size
        freeLcdWidth = (int) primaryScreenBounds.getWidth();
        freeLcdHeight = (int) primaryScreenBounds.getHeight();

        // Counting max rectangles number in H & V - some 20px for frame borders
        maxHorizontal = (freeLcdWidth - 20) / minObjectWidht;
        maxVertical = (freeLcdHeight - 80 - minObjectHeight) / minObjectHeight;

        if(maxHorizontal < 15) {
            maxHorizontal = 15;
        }
        if(maxVertical < 15) {
            maxVertical = 15;
        }
    }

    @Override
    public void start(Stage stage) {

//        new PlayGame(1,15,15);

        GridPane menuWindow = new GridPane();
        menuWindow.setAlignment(Pos.CENTER);
        menuWindow.setPadding(new Insets(10, 10, 10, 10));
        menuWindow.setVgap(5);
        menuWindow.setHgap(5);
        menuWindow.setId("MainGrid");


        // HELP
        Text help = new Text("Choose help level:");
        help.setId("help");

        final ToggleGroup level = new ToggleGroup();
        RadioButton rb1 = new RadioButton("No help needed");
        rb1.setToggleGroup(level);
        rb1.setSelected(true);
        rb1.setId("radio1");
        rb1.setUserData(0);

        RadioButton rb2 = new RadioButton("Show possible moves");
        rb2.setToggleGroup(level);
        rb2.setId("radio2");
        rb2.setUserData(1);

        RadioButton rb3 = new RadioButton("Show possible & next moves");
        rb3.setToggleGroup(level);
        rb3.setId("radio3");
        rb3.setUserData(2);



        // Puzzle size buttons
        Text size = new Text("Puzzle size:");
        size.setId("size");

        Button btn5 = new Button();
        btn5.setText("5 x 5");
        btn5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int value0 = (int) level.getSelectedToggle().getUserData();
                stage.close();
                new PlayGame(value0,5,5);
            }
        });
        btn5.setId("button5");
        btn5.setPrefWidth(100);

        Button btn10 = new Button();
        btn10.setText("10 x 10");
        btn10.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                int value0 = (int) level.getSelectedToggle().getUserData();
                stage.close();
                new PlayGame(value0,10,10);
            }
        });
        btn10.setId("button10");
        btn10.setPrefWidth(100);

        Button btn15 = new Button();
        btn15.setText("15 x 15");
        btn15.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                int value0 = (int) level.getSelectedToggle().getUserData();
                stage.close();
                new PlayGame(value0,15,15);
            }
        });
        btn15.setId("button15");
        btn15.setPrefWidth(100);



        // Puzzle size buttons
        Text custom = new Text("Custom:");
        custom.setId("custom");

        final TextField txt1 = new TextField();
        txt1.setPromptText("x");
        txt1.setPrefColumnCount(3);
        txt1.setId("txt1");
        txt1.getText();

        Text custX = new Text(" x ");
        custX.setId("custom");

        final TextField txt2 = new TextField();
        txt2.setPromptText("y");
        txt2.setPrefColumnCount(3);
        txt2.setId("txt2");
        txt2.getText();

        Button btnLnch = new Button();
        btnLnch.setText("Launch");
        btnLnch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    sizing();

                    int value0 = (int) level.getSelectedToggle().getUserData();
                    int value1 = valueOf(txt1.getText());
                    int value2 = valueOf(txt2.getText());

                    if(value1 >= minHorizontal && value1 <= maxHorizontal && value2 >= minVertical && value2 <= maxVertical) {
                        stage.close();
                        new PlayGame(value0, value1, value2);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Whoopsie");
                        alert.setHeaderText(null);
                        alert.setContentText("[X] must be between "+minHorizontal+" and "+maxHorizontal+"\n[Y] must be between "+minVertical+" and "+maxVertical);
                        alert.showAndWait();
                    }

                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("No no no!");
                    alert.setHeaderText(null);
                    alert.setContentText("X and Y must be numbers!");
                    alert.showAndWait();
                }
            }
        });
        btnLnch.setId("buttonLaunch");
        btnLnch.setPrefWidth(100);

        Text bttmSpace = new Text();
        Text bttmSpace2 = new Text();
        //Text bttmText = new Text("© 2018 Vitalij Vladimirov :)");
        Text bttmText = new Text("{ about & rules }");
        bttmText.setId("bottom-text");
        bttmText.setFill(Color.GRAY);
        GridPane.setHalignment(bttmText, HPos.RIGHT);


        menuWindow.add(help, 0, 0, 15, 1);
        menuWindow.add(rb1, 0, 1, 15, 1);
        menuWindow.add(rb2, 0, 2, 15, 1);
        menuWindow.add(rb3, 0, 3, 15, 1);
        menuWindow.add(size, 0, 5, 15, 1);
        menuWindow.add(btn5, 0, 6, 6, 1);
        menuWindow.add(btn10, 6, 6, 6, 1);
        menuWindow.add(btn15,11, 6, 6, 1);
        menuWindow.add(custom, 0, 7, 4, 1);
        menuWindow.add(txt1, 5, 7, 2, 1);
        menuWindow.add(custX, 7, 7, 2, 1);
        menuWindow.add(txt2, 9, 7, 2, 1);
        menuWindow.add(btnLnch, 11, 7, 5, 1);
        menuWindow.add(bttmSpace, 0, 8, 15, 1);
        menuWindow.add(bttmSpace2, 0, 9, 15, 1);
        menuWindow.add(bttmText, 0, 10, 15, 1);

        //menuWindow.setGridLinesVisible(true);




        // Creating scene
        Scene scene = new Scene(menuWindow, 500, 450);

        scene.getStylesheets().add("file:src/files/styles.css");

        stage.setTitle("Moving horse game");
        stage.getIcons().add(new Image("file:src/files/icon.png"));
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

}
