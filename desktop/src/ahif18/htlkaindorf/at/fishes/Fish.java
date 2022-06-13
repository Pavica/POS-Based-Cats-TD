package ahif18.htlkaindorf.at.fishes;
import com.badlogic.gdx.math.Rectangle;
import lombok.Data;

/**
 * This class represents a Fish
 *
 * @author Clark | Luka
 * @version 1.0
 * Last modified: 23.05.2022
 */

@Data
public abstract class Fish {
    /** used for the hitbox of the body of a fish */
    private Rectangle body;

    /** used for the note that the fish will travel towards*/
    private int currentPoint = 0;

    /** used for the direction that the fish is facing */
    private boolean directionLeft = false;

    /** health of a fish */
    private float health;


    /**
     * Specific constructor that is used identify the range and body hitbox of each fish created.
     * The health of the fish will also be set in here.
     * @param x : used to identify the width of the range and body hitbox
     * @param y : used to identify the height of the range and body hitbox
     */
    public Fish(float x, float y) {
        this.body = new Rectangle(x, y, getFishWidth(), getFishHeight());
        health = getBaseHealth();
    }

    public abstract float getBaseHealth();

    public abstract int getGoldDrop();

    public abstract int getID();

    public abstract int getDamage();

    public abstract float getSpeed();

    public abstract float getFishWidth();

    public abstract float getFishHeight();
}


