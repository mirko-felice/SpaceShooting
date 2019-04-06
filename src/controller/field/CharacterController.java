package controller.field;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Toolkit;

import controller.StageController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import model.Entity;
import model.ship.CharacterShip;
import model.ship.CharacterShipImpl;
import view.field.FieldView;

/**
 * Class that controls the character ship moves.
 *
 */
public class CharacterController implements EntityController {

    private static final Dimension RESOLUTION = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Image SHIP_IMAGE = new Image("spaceship.png");
    private final CharacterShip ship;
    private final FieldView view;
    private final CameraController camController;
    private double angle;
    private boolean camMoving;
    private boolean immunity;

    /**
     * Constructor of the CharacterController.
     * 
     * @param view  the view in which the ship is moving
     * @param camController  the camera controller of the view
     * @param stageController the controller of the stage
     */
    public CharacterController(final FieldView view, final CameraController camController, final StageController stageController) {
        this.ship = new CharacterShipImpl(new Point2D(RESOLUTION.getWidth() / 2, RESOLUTION.getHeight() / 2));
        this.view = view;
        this.camController = camController;
        this.immunity = false;
        final EventHandler<Event> eh = new EventHandler<Event>() {
            @Override
            public void handle(final Event event) {
                ship.changeMoving();
                camMoving = !camMoving;
            }
        };
        stageController.getScene().setOnMouseEntered(eh);
        stageController.getScene().setOnMouseExited(eh);
    }

    private Point2D getUpdatedPosition() {
        final Point2D mouseOnScreen = new Point2D(MouseInfo.getPointerInfo().getLocation().getX(), MouseInfo.getPointerInfo().getLocation().getY());
        final Point2D mousePosition = this.view.getCanvas().screenToLocal(mouseOnScreen);
        final Point2D vector = mousePosition.subtract(RESOLUTION.getWidth() / 2, RESOLUTION.getHeight() / 2);
        final double rad = Math.atan2(vector.getY(), vector.getX());
        this.angle = Math.toDegrees(rad);
        double shipUpdateX = this.ship.getBoundary().getMinX() + (this.ship.getSpeed() * Math.cos(rad));
        double shipUpdateY = this.ship.getBoundary().getMinY() + (this.ship.getSpeed() * Math.sin(rad));
        if (shipUpdateX < 0) {
            shipUpdateX = 0;
        } else if (shipUpdateX > (this.view.getCanvas().getWidth() - this.ship.getBoundary().getWidth())) {
            shipUpdateX = this.view.getCanvas().getWidth() - this.ship.getBoundary().getWidth();
        }
        if (shipUpdateY < 0) {
            shipUpdateY = 0;
        } else if (shipUpdateY > (this.view.getCanvas().getHeight() - this.ship.getBoundary().getHeight())) {
            shipUpdateY = this.view.getCanvas().getHeight() - this.ship.getBoundary().getHeight();
        }
        return new Point2D(shipUpdateX, shipUpdateY);
    }

    /**
     * Gets the angle, in degrees, by which the ship is rotated.
     * 
     * @return the angle, in degrees, by which the ship is rotated
     */
    public double getAngle() {
        return this.angle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw() {
        this.view.drawEntity(SHIP_IMAGE, this.angle, this.ship.getBoundary());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        final Point2D updatedPosition = getUpdatedPosition();
        this.camController.setCamUpdate(new Point2D(updatedPosition.getX() - this.ship.getBoundary().getMinX(), updatedPosition.getY() - this.ship.getBoundary().getMinY()));
        this.ship.update(updatedPosition.getX(), updatedPosition.getY());
    }

    /**
     * The method that observes if the camera must move or not.
     * 
     * @return isCamMoving value
     */
    public boolean isCamMoving() {
        return this.camMoving;
    }

    /**
     * Method that changes the value of immunity. 
     */
    public void changeImmunity() {
        this.immunity = !this.immunity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean intersects(final EntityController entityController) {
        return this.ship.intersects(entityController.getEntity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getEntity() {
        return this.ship;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive() {
        return this.ship.isAlive();
    }
}
