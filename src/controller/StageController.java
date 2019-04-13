
package controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Controller class of the Stage.
 */
public class StageController {

    private static final Dimension RESOLUTION = Toolkit.getDefaultToolkit().getScreenSize();
    private final Stage stage;

    /**
     * Build the StageController.
     * @param stage the stage to control
     */
    public StageController(final Stage stage) {
        this.stage = stage;
        this.stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
    }

    public void setHandler(final EventHandler<WindowEvent> exitWindow) {
        this.stage.setOnCloseRequest(exitWindow);
    }
    /**
     * 
     * @return the width of the stage
     */
    public double getWidth() {
        return this.stage.getWidth();
    }

    /**
     * 
     * @return the height of the stage
     */
    public double getHeight() {
        return this.stage.getHeight();
    }

    /**
     * 
     * @return the current scene of the stage
     */
    public Scene getScene() {
        return this.stage.getScene();
    }

    /**
     * 
     * @return the horizontal location of the Stage on the screen
     */
    public double getX() {
        return this.stage.getX();
    }

    /**
     * 
     * @return the vertical location of the Stage on the screen
     */
    public double getY() {
        return this.stage.getY();
    }

    private void autosize(final Dimension2D dimension) {
        this.stage.setMinWidth(dimension.getWidth());
        this.stage.setMinHeight(dimension.getHeight());
    }

    /**
     * 
     * @param stage the stage in which set the owner
     */
    public void setOwner(final Stage stage) {
        stage.initOwner(this.stage);
    }

    /**
     * Set the new Scene.
     * @param scene the scene to set
     */
    public void setScene(final Scene scene) {
        this.stage.setScene(scene);
        this.stage.centerOnScreen();
    }

    /**
     * Set the FullScreen.
     * @param value the value to set
     */
    public void setFullScreen(final boolean value) {
        this.stage.setFullScreen(value);
    }

    /**
     * Set the new Dimension.
     * @param dimension the dimension to set
     */
    public void setDimension(final Dimension2D dimension) {
        if (!this.stage.isFullScreen()) {
            if (dimension.getWidth() == RESOLUTION.getWidth() && dimension.getHeight() == RESOLUTION.getHeight()) {
                this.stage.setMaximized(true);
            } else {
                this.stage.setMaximized(false);
            }
            this.autosize(dimension);
            this.stage.setWidth(dimension.getWidth());
            this.stage.setHeight(dimension.getHeight());
            this.stage.centerOnScreen();
        }
    }
}

