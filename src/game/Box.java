package game;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Box extends Label {

    public Box box;

    public Box (String color, int width, int height, int col, int row) {

        setStyle("-fx-background-color:"+color+"; -fx-font:12px 'Courier-New';");

        setPrefWidth(width);
        setPrefHeight(height);
        setAlignment(Pos.CENTER);
        setCursor(Cursor.HAND);
        setId("box"+col+"x"+row);
        GridPane.setRowIndex(this, col+1);
        GridPane.setColumnIndex(this, row);

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                new MouseOver(col,row, box);
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                // mouse out
            }
        });

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                setText("AHOJ");
            }
        });

        this.box = this;
    }

}

class maclass implements  EventHandler {

    @Override
    public void handle(Event event) {

    }
}
