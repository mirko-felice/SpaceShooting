package model.meteor;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import model.Entity;
/**
 * Implementation of Meteor interface.
 */
public class MeteorImpl implements Meteor {

    private static final int DAMAGE_UNIT = 300;
    private static final double WIDTH = 100;
    private static final double HEIGHT = 100;
    private final int damage;
    private Point2D position;
    private final double movX;
    private final double movY;
    private Point2D target;
    private final Dimension2D fieldSize;
    private boolean enteredInField;
    private final double angle;
    private boolean isCollided;

    /**
     * Build a new Meteor.
     * @param level defines the speed and power.
     * @param src the starting position of the Meteor.
     * @param target the target position.
     * @param fieldSize the field width and height.
     */
    public MeteorImpl(final int level, final Point2D src, final Point2D target, final Dimension2D fieldSize) {
        final int speed = level * 3;
        this.fieldSize = fieldSize;
        this.damage = level * MeteorImpl.DAMAGE_UNIT;
        this.position = src;
        final Point2D centralPosition = new Point2D(src.getX() + WIDTH / 2, src.getY() + HEIGHT / 2);
        this.angle = Math.atan2(centralPosition.getY() - target.getY(), 
                centralPosition.getX() - target.getX());

        this.movY = -speed * Math.sin(angle);
        this.movX = -speed * Math.cos(angle);
        this.target = new Point2D(src.getX() + movX, src.getY() + movY);
        this.enteredInField = false;
        this.isCollided = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getAngle() {
        return this.angle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDamage() {
        return this.damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized Rectangle2D getBoundary() {
        return new Rectangle2D(position.getX(), position.getY(), MeteorImpl.WIDTH, MeteorImpl.HEIGHT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean intersects(final Entity entity) {
        final boolean isIntersected = entity.getBoundary().intersects(this.getBoundary());
        if (isIntersected) {
            this.destroy();
            entity.destroy();
        }
        return isIntersected;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void update() {
        this.position = new Point2D(position.getX() + movX, position.getY() + movY);
        this.target = new Point2D(target.getX() + movX, target.getY() + movY);
        if (position.getX() <= fieldSize.getWidth() && position.getX() > 0 && position.getY() <= fieldSize.getHeight() && position.getY() > 0) {
            this.enteredInField = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized boolean isAlive() {
        return !((position.getX() > this.fieldSize.getWidth() || position.getY() > this.fieldSize.getHeight()
                || position.getX() < 0 || position.getY() < 0) && enteredInField) && !this.isCollided;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void takeDamage(final int damage) { }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void destroy() {
        this.isCollided = true;
    }
}
