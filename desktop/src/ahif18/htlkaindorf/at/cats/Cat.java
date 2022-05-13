package ahif18.htlkaindorf.at.cats;

import ahif18.htlkaindorf.at.fishes.Fish;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import lombok.Data;

@Data
public abstract class Cat {
    //maybe change at some point to be dynamic
    public static float CAT_WIDTH = 75;
    public static float CAT_HEIGHT = 75;
    public static float CAT_BODY_WIDTH = 64;
    public static float CAT_BODY_HEIGHT = 64;

    private Rectangle range;
    private Rectangle body;

    private long currentInterval = 0;

    public Cat(float x, float y) {
        this.range = new Rectangle(x - getRangeWidth()/2,y - getRangeHeight()/2, getRangeWidth(), getRangeHeight());
        this.body = new Rectangle(x- CAT_BODY_WIDTH/2,y - CAT_BODY_HEIGHT/2,CAT_BODY_WIDTH,CAT_BODY_HEIGHT);
    }

    //returns the ids of the affected fish
    public abstract Array<Integer> attack(Array<Fish> allFish);

    public void setBodyPosition(){
        body.setX(body.getX() - CAT_BODY_WIDTH/2);
        body.setY(body.getY() - CAT_BODY_HEIGHT/2);
    }

    public abstract int getDamage();

    public abstract float getAttackInterval();

    public abstract int getCost();

    public abstract int getTextureID();

    public abstract int getRangeWidth();

    public abstract int getRangeHeight();
}