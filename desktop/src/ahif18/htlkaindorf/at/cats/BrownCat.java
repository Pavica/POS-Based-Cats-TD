package ahif18.htlkaindorf.at.cats;

import com.badlogic.gdx.math.Rectangle;

public class BrownCat extends Cat{

    public static int COST = 300;
    public static int RANGE_WIDTH = 100;
    public static int RANGE_HEIGHT = 100;
    public BrownCat(float x, float y) {
        super(new Rectangle(x - RANGE_WIDTH/2,y - RANGE_HEIGHT/2,RANGE_WIDTH,RANGE_HEIGHT),
                new Rectangle(x- CAT_BODY_WIDTH/2,y - CAT_BODY_HEIGHT/2,CAT_BODY_WIDTH,CAT_BODY_HEIGHT),
                500,
                2500,
                2,
                5);
    }

}
