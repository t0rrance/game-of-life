package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.controller.CustomController;
import sample.logic.GameOfLife;

public class Main extends Application {

    private StackPane mainLayout;
    private CustomController customController;
    private GameOfLife gameOfLife;
    private EventHandler<MouseEvent> mouseHandler;

    @Override
    public void init() throws Exception {
        mainLayout = new StackPane();
        gameOfLife = new GameOfLife();
        customController = new CustomController(gameOfLife);
        mainLayout.getChildren().add(customController);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Game of life - John Conway");
        primaryStage.setScene(new Scene(mainLayout,1000, 1000));
        primaryStage.setMinWidth(1000);
        primaryStage.setMaxWidth(1000);
        primaryStage.setMaxHeight(1000);
        primaryStage.setMinHeight(1000);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    gameOfLife.startSimulation();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        this.mouseHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                animationTimer.start();
            }
        };
        this.gameOfLife.setOnMouseClicked(this.mouseHandler);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
