package game;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import static game.PlayGame.*;

public class Box extends Label {

    public Box (String color, int width, int height, int col, int row) {

        setStyle("-fx-background-color:"+color+"; -fx-font:"+fontSize+" 'Courier-New';");
//        setStyle("-fx-font:"+fontSize+" 'Courier-New';");

        setPrefWidth(width);
        setPrefHeight(height);
        setAlignment(Pos.CENTER);
        setCursor(Cursor.HAND);
        GridPane.setRowIndex(this, col+1);
        GridPane.setColumnIndex(this, row);

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                new MouseMove("on",col,row);
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                new MouseMove("off",col,row);
            }
        });

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try {
                    new MouseClick(col,row);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

}
