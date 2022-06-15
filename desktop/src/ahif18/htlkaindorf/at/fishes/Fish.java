package ahif18.htlkaindorf.at.fishes;
import ahif18.htlkaindorf.at.Drop;
import com.badlogic.gdx.math.Rectangle;
import lombok.Data;

/**
 * This class represents a Fish
 *
 * @author Clark | Luka
 * @version 1.0
 * Last modified: 15.06.2022
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
     */
    public Fish() {
        this.body = new Rectangle(Drop.points[0].x,Drop.points[0].y, getFishWidth(), getFishHeight());   
        health = getBaseHealth();
    }

    /** returns the base health value of a fish*/
    public abstract float getBaseHealth();

    /** returns the gold value that a fish drops when it dies*/
    public abstract int getGoldDrop();

    /** returns the id of a fish, used to identifying its type*/
    public abstract int getID();

    /** returns the damage the fish does to a map*/
    public abstract int getDamage();

    /** returns the speed at which a fish travels*/
    public abstract float getSpeed();

    /** returns the width of a fish*/
    public abstract float getFishWidth();

    /** returns the height of a fish*/
    public abstract float getFishHeight();
}


