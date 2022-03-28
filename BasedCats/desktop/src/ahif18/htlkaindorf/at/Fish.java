package ahif18.htlkaindorf.at;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import org.lwjgl.system.CallbackI;

public class Fish {
    private int health = 100;
    private int currentPoint;
    private Rectangle rectangle;
    private boolean directionLeft = true;


    public boolean isDirectionLeft() {
        return directionLeft;
    }

    public void setDirectionLeft(boolean directionLeft) {
        this.directionLeft = directionLeft;
    }

    public Fish(int currentPoint, Rectangle rectangle) {
        this.currentPoint = currentPoint;
        this.rectangle = rectangle;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public int getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(int currentPoint) {
        this.currentPoint = currentPoint;
    }
}


