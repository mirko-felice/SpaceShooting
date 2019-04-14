package utilities;

import java.util.Arrays;
import java.util.List;
import javafx.scene.image.Image;

/**
 * 
 * This class has the scope to return the image related to the entity level.
 */
public final class EntitiesImageUtils {

    private static final List<Image> BULLET_IMAGES = Arrays.asList(
            new Image("bulletGreen.png"),
            new Image("bulletYellow.png"),
            new Image("bulletRed.png"),
            new Image("bulletPower.png"));
    private static final List<Image> METEOR_IMAGES = Arrays.asList(
            new Image("meteorGreen.png"),
            new Image("meteorYellow.png"),
            new Image("meteorRed.png"));
    private static final List<Image> ENEMYSHIP_IMAGES = Arrays.asList(
            new Image("enemyShip1.png"),
            new Image("enemyShip2.png"),
            new Image("enemyShip3.png"));

    private EntitiesImageUtils() { }

    /**
     * 
     * @param level the level of the calling Bullet.
     * @return the image to print.
     */
    public static Image getBulletImage(final int level) {
        return BULLET_IMAGES.get((level - 1) % BULLET_IMAGES.size());
    }

    /**
     * 
     * @param level the level of the calling Meteor.
     * @return the image to print.
     */
    public static Image getMeteorImage(final int level) {
        return METEOR_IMAGES.get((level - 1) % METEOR_IMAGES.size());
    }

    /**
     * 
     * @param level the level of the calling EnemyShip.
     * @return the image to print.
     */
    public static Image getEnemyShipImage(final int level) {
        return ENEMYSHIP_IMAGES.get((level - 1) % ENEMYSHIP_IMAGES.size());
    }
}
