package ahif18.htlkaindorf.at.cats;

import com.badlogic.gdx.math.Rectangle;

public class MoonCat extends Cat{

    public static int COST = 250;
    public static int RANGE_WIDTH = 125;
    public static int RANGE_HEIGHT = 125;
    public MoonCat(float x, float y) {
        super(new Rectangle(x - RANGE_WIDTH/2,y - RANGE_HEIGHT/2,RANGE_WIDTH,RANGE_HEIGHT),
                new Rectangle(x- CAT_BODY_WIDTH/2,y - CAT_BODY_HEIGHT/2,CAT_BODY_WIDTH,CAT_BODY_HEIGHT),
                100,
                1000,
                250,
                1,
                3);
    }
}
