package sample.controller;

import javafx.scene.control.Control;
import sample.logic.GameOfLife;

public class CustomController extends Control {

    private GameOfLife gameOfLife;

    public CustomController(GameOfLife gameOfLife) throws InterruptedException {
        setSkin(new CustomControllerSkin(this));
        this.gameOfLife = gameOfLife;
        getChildren().add(this.gameOfLife);
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
        this.gameOfLife.resize(width, height);
    }

}
