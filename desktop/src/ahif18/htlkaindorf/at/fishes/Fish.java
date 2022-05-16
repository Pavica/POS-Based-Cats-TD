package ahif18.htlkaindorf.at.fishes;
import com.badlogic.gdx.math.Rectangle;
import lombok.Data;

@Data
public abstract class Fish {
    private Rectangle body;

    private int currentPoint = 0;
    private boolean directionLeft = false;
    private int health;

    public Fish(float x, float y) {
        this.body = new Rectangle(x, y, getFishWidth(), getFishHeight());
        health = getBaseHealth();
    }

    public abstract int getBaseHealth();

    public abstract int getGoldDrop();

    public abstract int getID();

    public abstract int getDamage();

    public abstract float getSpeed();

    public abstract float getFishWidth();

    public abstract float getFishHeight();
}


