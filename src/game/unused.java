//package game;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.GridPane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.stage.Stage;
//
//public class Main extends Application {
//
//    static Rectangle r = null;
//    static Rectangle r2 = null;
//
//    @Override
//    // public void start(Stage primaryStage) throws Exception {
//    public void start(Stage primaryStage) throws Exception {
//
//        primaryStage.setTitle("GridPane Experiment");
//
//        r = new Rectangle(20, 20);
//        r.setFill(Color.RED);
//
//        r2 = new Rectangle(20, 20);
//        r2.setFill(Color.BLUE);
//
//        GridPane gridPane = new GridPane();
//
//        gridPane.add(r, 0, 1, 1, 1);
//        gridPane.add(r2, 2, 1, 1, 1);
//
//        Scene scene = new Scene(gridPane, 240, 100);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//    }
//
//    public static void main(String[] args) {
//        Application.launch(args);
//
//        while (true) {
//
//            r.setFill(Color.ALICEBLUE);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            r.setFill(Color.GREEN);
//
//        }
//    }
//}