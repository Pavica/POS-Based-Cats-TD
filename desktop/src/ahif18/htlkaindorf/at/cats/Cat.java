package ahif18.htlkaindorf.at.cats;

import ahif18.htlkaindorf.at.fishes.Fish;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import lombok.Data;

/**
 * This class represents a Cat
 *
 * @author Clark | Luka
 * @version 1.0
 * Last modified: 16.05.2022
 */

@Data
public abstract class Cat {
    //maybe change at some point to be dynamic

    /** width of every cat */
    public static float CAT_WIDTH = 75;

    /** height of every cat */
    public static float CAT_HEIGHT = 75;

    /** width of hitbox of each cat */
    public static float CAT_BODY_WIDTH = 64;

    /** height of hitbox of each cat */
    public static float CAT_BODY_HEIGHT = 64;

    /** used for range radius (how far a cat can attack) */
    private Rectangle range;

    /** used for the hitbox of the body of a cat */
    private Rectangle body;

    /** cooldown of attacks */
    private long currentInterval = 0;

    /**
     * Specific constructor that is used identify the range and body hitbox of each cat created.
     * @param x : used to identify the width of the range and body hitbox
     * @param y : used to identify the height of the range and body hitbox
     */
    public Cat(float x, float y) {
        this.range = new Rectangle(x - getRangeWidth()/2,y - getRangeHeight()/2, getRangeWidth(), getRangeHeight());
        this.body = new Rectangle(x- CAT_BODY_WIDTH/2,y - CAT_BODY_HEIGHT/2,CAT_BODY_WIDTH,CAT_BODY_HEIGHT);
    }

    //returns the ids of the affected fish

    /**
     * abstract class to return which fish had been attacked by the cat
     *
     * @return which fish had been attacked by the cat
     */
    public abstract Array<Integer> attack(Array<Fish> allFish);

    /**
     * setter method used to set the exact body hitbox
     */
    public void setBodyPosition(){
        body.setX(body.getX() - CAT_BODY_WIDTH/2);
        body.setY(body.getY() - CAT_BODY_HEIGHT/2);
    }

    public abstract int getDamage();

    public abstract float getAttackInterval();

    public abstract int getCost();

    public abstract int getID();

    public abstract int getRangeWidth();

    public abstract int getRangeHeight();
}