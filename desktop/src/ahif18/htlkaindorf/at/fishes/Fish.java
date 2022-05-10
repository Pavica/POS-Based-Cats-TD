package ahif18.htlkaindorf.at.fishes;
import com.badlogic.gdx.math.Rectangle;
import lombok.Data;

@Data
public class Fish {
    private int health;
    private int currentPoint;
    private Rectangle rectangle;
    private boolean directionLeft = true;
    private int goldDrop;
    private int textureID;
    private int damage;
    private float speed;

    //maybe different speed
    private float fishWidth;
    private float fishHeight;

    public Fish(int currentPoint, Rectangle rectangle, int health, int goldDrop, int textureID, int damage, float speed, float fishWidth, float fishHeight) {
        this.currentPoint = currentPoint;
        this.rectangle = rectangle;
        this.health = health;
        this.goldDrop = goldDrop;
        this.textureID = textureID;
        this.damage = damage;
        this.speed = speed;
        this.fishWidth = fishWidth;
        this.fishHeight = fishHeight;
    }
}


