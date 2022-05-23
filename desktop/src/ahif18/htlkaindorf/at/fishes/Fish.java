package ahif18.htlkaindorf.at.fishes;
import ahif18.htlkaindorf.at.Drop;
import com.badlogic.gdx.math.Rectangle;
import lombok.Data;

@Data
public abstract class Fish {
    private Rectangle body;

    private int currentPoint = 0;
    private boolean directionLeft = false;
    private float health;

    public Fish() {
        this.body = new Rectangle(Drop.points[0].x,Drop.points[0].y, getFishWidth(), getFishHeight());
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


