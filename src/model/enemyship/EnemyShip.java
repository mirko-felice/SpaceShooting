package model.enemyship;

import javafx.geometry.Point2D;
import model.ship.Ship;

/**
 * 
 *
 */
public interface EnemyShip extends Ship {

    /**
     * Get the speed.
     * @return the speed.
     */
    double getSpeed();

    /**
     * Update the Enemy position.
     * @param addPosition the position to add at the current position.
     */
    void update(Point2D addPosition);
}
