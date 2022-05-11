package ahif18.htlkaindorf.at.cats;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import lombok.Data;

@Data
public class Cat {
    public static float CAT_WIDTH = 75;
    public static float CAT_HEIGHT = 75;

    public static float CAT_BODY_WIDTH = 64;
    public static float CAT_BODY_HEIGHT = 64;

    private Rectangle range;
    private Rectangle body;
    private int damage;
    private float attackInterval;
    private int cost;
    private long currentInterval = 0;
    private int textureID;
    private int aoeAmount;

    public Cat(Rectangle range, Rectangle body, int damage, float attackInterval, int cost, int textureID, int aoeAmount) {
        this.range = range;
        this.body = body;
        this.damage = damage;
        this.attackInterval = attackInterval;
        this.cost = cost;
        this.textureID = textureID;
        this.aoeAmount = aoeAmount;
    }

    public void setBodyPosition(){
        body.setX(body.getX() - CAT_BODY_WIDTH/2);
        body.setY(body.getY() - CAT_BODY_HEIGHT/2);
    }
}