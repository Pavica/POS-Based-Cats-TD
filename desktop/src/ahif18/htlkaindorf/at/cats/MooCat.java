package ahif18.htlkaindorf.at.cats;

import com.badlogic.gdx.math.Rectangle;

public class MooCat extends Cat{

    public static int COST = 200;
    public MooCat(Rectangle range, Rectangle body) {
        super(range, body, 200, 500, 3,1);
    }
}
