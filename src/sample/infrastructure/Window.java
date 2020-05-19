package sample.infrastructure;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;


public class Window extends Group {

    private Rectangle rectangle;
    private Translate position;

    public Window() {
        rectangle = new Rectangle();
        position = new Translate();
        this.rectangle.setFill(Color.WHITE);
        this.rectangle.setStroke(Color.BLACK);
        this.rectangle.setStrokeWidth(1);
        setManaged(false);
        getChildren().add(rectangle);
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
        rectangle.setHeight(height);
        rectangle.setWidth(width);
    }

    @Override
    public void relocate(double x, double y) {
        super.relocate(x, y);
        position.setX(x);
        position.setY(y);
    }

    public void setALiveCell() {
        this.rectangle.setFill(Color.RED);
    }

    public void setDeathCell() {
        this.rectangle.setFill(Color.WHITE);
    }

}
