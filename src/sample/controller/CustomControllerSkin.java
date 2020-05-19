package sample.controller;

import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;

public class CustomControllerSkin extends SkinBase<CustomController> implements Skin<CustomController> {
    protected CustomControllerSkin(CustomController control) {
        super(control);
    }
}
