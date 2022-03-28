package ahif18.htlkaindorf.at;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import org.w3c.dom.css.Rect;

public class Drop extends ApplicationAdapter {
    private static int MAX_HEALTH = 100;
    private static int HEALTH_LOSS = 25;

    private int health = MAX_HEALTH;

    private Texture dropImage;
    private Texture dropImageRotated;
    private Texture backgroundImage;
    private Texture backgroundImageBridges;
    private Texture bucketImage;

    private Sprite dropSprite;
    private Sprite backgroundSprite;
    private Sprite backgroundBridgesSprite;

    private SpriteBatch batch;

    private OrthographicCamera camera;
    private Rectangle bucket;
    private Array<Fish> raindrops;
    private long lastDropTime;

    private Rectangle points[] = {
        new Rectangle(515,470,8,8),
        new Rectangle(515, 360, 8,8),
        new Rectangle(70,360, 8,8),
        new Rectangle(70, 280, 8,8),
        new Rectangle(280,280, 8,8),
        new Rectangle(370,220, 8,8),
        new Rectangle(635, 220, 8,8),
        new Rectangle(635, 115, 8,8),
        new Rectangle(100, 115, 8,8),
        new Rectangle(100, -10, 8,8)
    };

    @Override
    public void create() {
        // load the images for the droplet and the bucket, 32x32 pixels each
        backgroundImage = new Texture(Gdx.files.internal("background.png"));
        backgroundImageBridges = new Texture(Gdx.files.internal("background-2.png"));
        dropImage = new Texture(Gdx.files.internal("fish.png"));
        dropImageRotated = new Texture(Gdx.files.internal("fish2.png"));
        bucketImage = new Texture(Gdx.files.internal("bucket.png"));

        dropSprite = new Sprite(dropImage);
        backgroundSprite =new Sprite(backgroundImage);
        backgroundBridgesSprite = new Sprite(backgroundImageBridges);

        // load the drop sound effect and the rain background "music"
        //dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        //rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        // start the playback of the background music immediately
        //rainMusic.setLooping(true);
        //rainMusic.play();

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();

        // create a Rectangle to logically represent the bucket
        bucket = new Rectangle();
        bucket.x = 800 / 2 - 32 / 2; // center the bucket horizontally
        bucket.y = 20; // bottom left corner of the bucket is 20 pixels above the bottom screen edge
        bucket.width = 32;
        bucket.height = 32;

        // create the raindrops array and spawn the first raindrop
        raindrops = new Array<Fish>();
        spawnRaindrop();
    }

    private void spawnRaindrop() {
        Fish raindrop = new Fish(0, new Rectangle(points[0].x,points[0].y,32,32));
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public void render() {
        // clear the screen with a dark blue color. The
        // arguments to clear are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the bucket and
        // all drops
        batch.begin();
        backgroundSprite.draw(batch);
        batch.draw(backgroundImage, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.draw(bucketImage, bucket.x, bucket.y, 32,32);

        for(Fish raindrop: raindrops) {
            if(raindrop.isDirectionLeft()){
                batch.draw(dropImage, raindrop.getRectangle().x, raindrop.getRectangle().y, 32,32);
            }else{
                batch.draw(dropImageRotated, raindrop.getRectangle().x, raindrop.getRectangle().y, 32, 32);
            }
        }

        backgroundBridgesSprite.draw(batch);
        batch.draw(backgroundBridgesSprite, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.end();

        // process user input
        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.x = touchPos.x - 32 / 2;
        }
        if(Gdx.input.isKeyPressed(Keys.LEFT)) bucket.x -= 2000 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) bucket.x += 2000 * Gdx.graphics.getDeltaTime();

        // make sure the bucket stays within the screen bounds
        if(bucket.x < 0) bucket.x = 0;
        if(bucket.x > 800 - 32) bucket.x = 800 - 32;

        // check if we need to create a new raindrop
        if(TimeUtils.nanoTime() - lastDropTime > 500000000) spawnRaindrop();

        // move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the bucket. In the latter case we play back
        // a sound effect as well.
        for (Iterator<Fish> iter = raindrops.iterator(); iter.hasNext(); ) {
            Fish raindrop = iter.next();

            if(raindrop.getRectangle().overlaps(points[raindrop.getCurrentPoint()]) && raindrop.getCurrentPoint() != points.length-1){
                raindrop.setCurrentPoint(raindrop.getCurrentPoint() + 1);
            }

            if(points[raindrop.getCurrentPoint()].x < raindrop.getRectangle().x){
                raindrop.getRectangle().x -= 200 * Gdx.graphics.getDeltaTime();
                raindrop.setDirectionLeft(true);
            }
            if(points[raindrop.getCurrentPoint()].x > raindrop.getRectangle().x){
                raindrop.getRectangle().x += 200 * Gdx.graphics.getDeltaTime();
                raindrop.setDirectionLeft(false);
            }

            if(points[raindrop.getCurrentPoint()].y < raindrop.getRectangle().y) {
                raindrop.getRectangle().y -= 200 * Gdx.graphics.getDeltaTime();
            }
            if(points[raindrop.getCurrentPoint()].y > raindrop.getRectangle().y){
                raindrop.getRectangle().y += 200 * Gdx.graphics.getDeltaTime();
            }

            if(raindrop.getRectangle().y < 0){
                health -= HEALTH_LOSS;
                iter.remove();
            }

            if(health <= 0){
                Gdx.app.exit();
            }

            if(raindrop.getRectangle().overlaps(bucket)) {
                //dropSound.play();
                iter.remove();
            }
        }
    }

    @Override
    public void dispose() {
        // dispose of all the native resources
        dropImage.dispose();
        dropImageRotated.dispose();
        bucketImage.dispose();
        backgroundImage.dispose();
        //dropSound.dispose();
        //rainMusic.dispose();
        batch.dispose();
    }
}