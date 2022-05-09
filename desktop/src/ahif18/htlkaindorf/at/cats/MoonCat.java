package ahif18.htlkaindorf.at.cats;

import com.badlogic.gdx.math.Rectangle;

public class MoonCat extends Cat{

    public static int COST = 250;
    public MoonCat(Rectangle range, Rectangle body) {
        super(range, body, 100, 1000, 1, 3);
    }
}
