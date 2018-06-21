package game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.scene.image.Image;

public class Menu extends Application {

    @Override
    public void start(Stage stage) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setId("MainGrid");


        // HELP
        Text help = new Text("Choose help level:");
        help.setId("help");

        final ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton("No help needed");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);
        rb1.setId("radio1");

        RadioButton rb2 = new RadioButton("Show possible moves");
        rb2.setToggleGroup(group);
        rb2.setId("radio2");

        RadioButton rb3 = new RadioButton("Show possible & next moves");
        rb3.setToggleGroup(group);
        rb3.setId("radio3");



        // Puzzle size buttons
        Text size = new Text("Puzzle size:");
        size.setId("size");

        Button btn5 = new Button();
        btn5.setText("5 x 5");
        btn5.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("5 x 5");
            }
        });
        btn5.setId("button5");
        btn5.setPrefWidth(100);

        Button btn10 = new Button();
        btn10.setText("10 x 10");
        btn10.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("10 x 10");
            }
        });
        btn10.setId("button10");
        btn10.setPrefWidth(100);

        Button btn15 = new Button();
        btn15.setText("15 x 15");
        btn15.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("15 x 15");
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
                System.out.println("Launch");
            }
        });
        btnLnch.setId("buttonLaunch");
        btnLnch.setPrefWidth(100);

        Text bttmSpace = new Text();
        Text bttmSpace2 = new Text();
        Text bttmText = new Text("© 2018 Vitalij Vladimirov :)");
        bttmText.setId("bottom-text");
        bttmText.setFill(Color.GRAY);
        GridPane.setHalignment(bttmText, HPos.RIGHT);


        grid.add(help, 0, 0, 15, 1);
        grid.add(rb1, 0, 1, 15, 1);
        grid.add(rb2, 0, 2, 15, 1);
        grid.add(rb3, 0, 3, 15, 1);
        grid.add(size, 0, 5, 15, 1);
        grid.add(btn5, 0, 6, 6, 1);
        grid.add(btn10, 6, 6, 6, 1);
        grid.add(btn15,11, 6, 6, 1);
        grid.add(custom, 0, 7, 4, 1);
        grid.add(txt1, 5, 7, 2, 1);
        grid.add(custX, 7, 7, 2, 1);
        grid.add(txt2, 9, 7, 2, 1);
        grid.add(btnLnch, 11, 7, 5, 1);
        grid.add(bttmSpace, 0, 8, 15, 1);
        grid.add(bttmSpace2, 0, 9, 15, 1);
        grid.add(bttmText, 0, 10, 15, 1);




        // Creating scene
        Scene scene = new Scene(grid, 500, 450);

        scene.getStylesheets().add("file:src/files/styles.css");

        stage.setTitle("Moving horse game");
        stage.getIcons().add(new Image("file:src/files/icon.png"));
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

}
