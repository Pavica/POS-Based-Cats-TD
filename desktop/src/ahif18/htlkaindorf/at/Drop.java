package ahif18.htlkaindorf.at;

import java.awt.*;
import java.util.Iterator;

import ahif18.htlkaindorf.at.cats.BaseCat;
import ahif18.htlkaindorf.at.cats.Cat;
import ahif18.htlkaindorf.at.cats.MoonCat;
import ahif18.htlkaindorf.at.fishes.Fish;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import org.w3c.dom.css.Rect;

public class Drop extends ApplicationAdapter {
    private static int MAX_HEALTH = 100;
    private static int HEALTH_LOSS = 25;

    private int gold = 1000;
    private int speed = 1000000000;
    private int health = MAX_HEALTH;

    private Texture dropImage;
    private Texture dropImageRotated;
    private Texture backgroundImage;
    private Texture backgroundImageBridges;
    private Texture baseCatImage;
    private Texture moonCatImage;
    private Texture catHolderImage;

    private Sprite dropSprite;
    private Sprite backgroundSprite;
    private Sprite backgroundBridgesSprite;
    private Sprite catHolderSprite;

    private SpriteBatch batch;

    private BitmapFont font;

    private OrthographicCamera camera;
    private Rectangle moonCat;
    private Rectangle baseCat;
    private Array<Texture> catTextures;
    private Array<Cat> cats;
    private Array<Fish> raindrops;
    private long lastDropTime;
    private boolean isMoving = false;

    private Rectangle points[] = {
        new Rectangle(515,470,8,8),
        new Rectangle(515, 360, 8,8),
        new Rectangle(70,360, 8,8),
        new Rectangle(70, 280, 8,8),
        new Rectangle(280,280, 8,8),
        new Rectangle(370,220, 8,8),
        new Rectangle(635, 220, 8,8),
        new Rectangle(650, 160, 8,8),
        new Rectangle(600, 115, 8,8),
        new Rectangle(130, 115, 8,8),
        new Rectangle(100, 80, 8,8),
        new Rectangle(100, -10, 8,8)
    };

    //draw these so you can check if they align with the river and then use it as a hitbox for not allowing it to place
    //BEFORE THAT CHECK IF YOU COULD DO IT WITH THE FIRST ONE
    private Rectangle riverHitbox[] = {
            new Rectangle(points[0].x -10,points[0].y - 10,60,50),
            new Rectangle(points[1].x - 10,points[1].y - 10, 60,150),
            new Rectangle(points[2].x - 10,points[2].y - 10, 450,60),
            new Rectangle(points[3].x - 10,points[3].y - 10, 275,60),
            new Rectangle(points[4].x - 10,points[4].y - 60, 100,60),
            new Rectangle(points[5].x - 10,points[5].y - 10, 300,60),
            new Rectangle(points[6].x - 10,points[6].y - 10, 60,60),
            new Rectangle(points[8].x - 10,points[8].y - 10, 100,60),
            new Rectangle(points[9].x - 10,points[9].y - 10, 500,60),
            new Rectangle(points[10].x - 10,points[10].y - 10, 70,60),
            new Rectangle(points[11].x - 10,points[11].y - 10, 60,100)
    };

    @Override
    public void create() {

        // load the images for the droplet and the moonCat, 32x32 pixels each
        backgroundImage = new Texture(Gdx.files.internal("background.png"));
        backgroundImageBridges = new Texture(Gdx.files.internal("background-2.png"));
        dropImage = new Texture(Gdx.files.internal("fish.png"));
        dropImageRotated = new Texture(Gdx.files.internal("fish2.png"));
        moonCatImage = new Texture(Gdx.files.internal("moonCat.png"));
        baseCatImage = new Texture(Gdx.files.internal("BaseCat.png"));
        moonCatImage = new Texture(Gdx.files.internal("moonCat.png"));
        catHolderImage = new Texture(Gdx.files.internal("catHolder.png"));

        catTextures = new Array<>();
        catTextures.add(baseCatImage);
        catTextures.add(moonCatImage);

        dropSprite = new Sprite(dropImage);
        backgroundSprite =new Sprite(backgroundImage);
        backgroundBridgesSprite = new Sprite(backgroundImageBridges);
        catHolderSprite = new Sprite(catHolderImage);

        font = FontGenerator.generateFreetypeFont(16, Color.WHITE);

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

        //USE moonCat CODE FOR MOVING THE CATS WITH MOUSE

        // create a Rectangle to logically represent the moonCat
        moonCat = new Rectangle(645, 345,  Cat.catWidth, Cat.catHeight);
        baseCat = new Rectangle(715, 345, Cat.catWidth, Cat.catHeight);

        // create the raindrops array and spawn the first raindrop
        raindrops = new Array<Fish>();
        spawnRaindrop();

        cats = new Array<Cat>();
    }

    private void spawnRaindrop() {
        Fish raindrop = new Fish(0, new Rectangle(points[0].x,points[0].y,32,32));
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    //add x, y coordinates to spawn the cat where you drag it
    private void spawnCat(float screenX, float screenY, int catID){
        //for some reason the cat is a bit further to the right than it should be, you cna compensate for this by just making
        // the values uneven but i think that will break some automation down the line, but it probably wont be an issue considering
        // all the cats are the same so you would just have to decrease or increase it by a certain amount

        switch (catID) {
            case 0:
                Cat basecat = new BaseCat(new Rectangle(screenX - 64, screenY - 64, 128, 128),
                        new Rectangle(screenX - 32, screenY - 32, 64, 64));
                cats.add(basecat);
                break;
            case 1:
                Cat moonCat = new MoonCat(new Rectangle(screenX - 64, screenY - 64, 128, 128),
                        new Rectangle(screenX - 32, screenY - 32, 64, 64));
                cats.add(moonCat);
                break;
            default:
                System.out.println("No cats found");
        }
    }

    private int checkWhichCat(float screenX, float screenY, Rectangle rectangle){
        if(rectangle.overlaps(new Rectangle(645,345,75,75))){
            return 1;
        }else if(rectangle.overlaps(new Rectangle(715,345,75,75))){
            return 0;
        }
        return -1;
    }

    private int getCatCost(int catID){
        switch (catID) {
            case 0:
                return 100;
            case 1:
                return 250;
            default:
                System.out.println("No cats found");
        }
        return -1;
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

        // begin a new batch and draw the moonCat and
        // all drops
        batch.begin();
        backgroundSprite.draw(batch);

        //Background
        batch.draw(backgroundImage, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //Fish
        for(Fish raindrop: raindrops) {
            if(raindrop.isDirectionLeft()){
                batch.draw(dropImage, raindrop.getRectangle().x, raindrop.getRectangle().y, 32,32);
            }else{
                batch.draw(dropImageRotated, raindrop.getRectangle().x, raindrop.getRectangle().y, 32, 32);
            }
        }

        //Bridges
        backgroundBridgesSprite.draw(batch);
        batch.draw(backgroundBridgesSprite, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //Cats
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){

        }
        for(int i=0; i<cats.size; i++){
            batch.draw(catTextures.get(cats.get(i).getTextureID()), cats.get(i).getBody().x, cats.get(i).getBody().y, Cat.catWidth, Cat.catHeight);
        }

        //Cat Holder
        catHolderSprite.draw(batch);

        //Gold
        GlyphLayout glyphLayout = new GlyphLayout();
        String item = gold + "";
        glyphLayout.setText(font,item);
        float m = glyphLayout.width;
        font.draw(batch, item, 755 - m, 460);

        //Cats inside holder (dummys)
        batch.draw(moonCatImage, moonCat.x, moonCat.y, Cat.catWidth, Cat.catHeight);
        batch.draw(baseCatImage, baseCat.x, baseCat.y, Cat.catWidth ,Cat.catHeight);
        batch.end();
        /*
        //RENDER THE HITBOX OF THE RIVER WHICH IS USED FOR DISALLOWING THE PLACEMENT OF CATS ON IT
        ShapeRenderer shapeRenderer;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for(int i=0; i<= riverHitbox.length -1; i++){
            shapeRenderer.rect(riverHitbox[i].x, riverHitbox[i].y, riverHitbox[i].getWidth(), riverHitbox[i].getHeight(), com.badlogic.gdx.graphics.Color.GRAY, com.badlogic.gdx.graphics.Color.GRAY, com.badlogic.gdx.graphics.Color.GRAY, com.badlogic.gdx.graphics.Color.GRAY);
        }
        if(!cats.isEmpty()){
            for(int i= 0; i< cats.size; i++){
                shapeRenderer.rect(cats.get(i).getRange().x, cats.get(i).getRange().y, cats.get(i).getRange().getWidth(), cats.get(i).getRange().getHeight(), com.badlogic.gdx.graphics.Color.GRAY, com.badlogic.gdx.graphics.Color.GRAY, com.badlogic.gdx.graphics.Color.GRAY, com.badlogic.gdx.graphics.Color.GRAY);
                shapeRenderer.rect(cats.get(i).getBody().x, cats.get(i).getBody().y, cats.get(i).getBody().getWidth(), cats.get(i).getBody().getHeight(), com.badlogic.gdx.graphics.Color.GRAY, com.badlogic.gdx.graphics.Color.GRAY, com.badlogic.gdx.graphics.Color.GRAY, com.badlogic.gdx.graphics.Color.GRAY);
            }
        }

        shapeRenderer.end();
        */
        // process user input
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            Vector3 touchPos = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touchPos);
            Rectangle rectangle = new Rectangle(touchPos.x, touchPos.y, 0,0);

            int checkWhichCat = checkWhichCat(touchPos.x,touchPos.y,rectangle);

            if(checkWhichCat != -1 && !isMoving) {
                Gdx.input.setInputProcessor(new InputProcessor() {
                    @Override
                    public boolean keyDown(int keycode) {
                        return false;
                    }

                    @Override
                    public boolean keyUp(int keycode) {
                        return false;
                    }

                    @Override
                    public boolean keyTyped(char character) {
                        return false;
                    }

                    @Override
                    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                        return true;
                    }

                    @Override
                    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                        isMoving = false;
                        Vector3 touchPos = new Vector3(screenX, screenY, 0);
                        camera.unproject(touchPos);
                        Rectangle catPosition = new Rectangle(touchPos.x, touchPos.y, 0, 0);
                        boolean helpOverlaps = true;
                        for (Rectangle riverPoint : riverHitbox) {
                            if (catPosition.overlaps(riverPoint)) {
                                helpOverlaps = false;
                                break;
                            }
                        }
                        for (Cat cat : cats) {
                            if (catPosition.overlaps(cat.getBody())) {
                                helpOverlaps = false;
                                break;
                            }
                        }
                        //If the cats DONT overlap with the river OR any other cats AND the gold amount is above CAT_COST you can spawn the cat
                        if (helpOverlaps && gold >= getCatCost(checkWhichCat)) {
                            gold -= getCatCost(checkWhichCat);
                            spawnCat(touchPos.x, touchPos.y, checkWhichCat);
                        }

                        switch (checkWhichCat) {
                            case 0:
                                baseCat.x = 715;
                                baseCat.y = 345;
                                break;
                            case 1:
                                moonCat.x = 645;
                                moonCat.y = 345;
                                break;
                            default:
                                System.out.println("No cats found");
                        }
                        return false;
                    }

                    @Override
                    public boolean touchDragged(int screenX, int screenY, int pointer) {
                        isMoving = true;
                        Vector3 touchPos = new Vector3(screenX, screenX, 0);
                        camera.unproject(touchPos);
                        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                        camera.unproject(touchPos);

                        switch (checkWhichCat) {
                            case 0:
                                baseCat.x = touchPos.x - Cat.catWidth  / 2;
                                baseCat.y = touchPos.y - Cat.catHeight / 2;
                                break;
                            case 1:
                                moonCat.x = touchPos.x - Cat.catWidth  / 2;
                                moonCat.y = touchPos.y - Cat.catHeight / 2;
                                break;
                            default:
                                System.out.println("No cats found");
                        }
                        return false;
                    }

                    @Override
                    public boolean mouseMoved(int screenX, int screenY) {
                        return false;
                    }

                    @Override
                    public boolean scrolled(float amountX, float amountY) {
                        return false;
                    }
                });
            }
        }else{
            Gdx.input.setInputProcessor(null);
        }

        // make sure the moonCat stays within the screen bounds
        if(moonCat.x < 0) moonCat.x = 0;
        if(moonCat.x > 800 - 32) moonCat.x = 800 - 32;

        // check if we need to create a new raindrop
        if(TimeUtils.nanoTime() - lastDropTime > speed) spawnRaindrop();
        speed *= 0.9995;
        // move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the moonCat. In the latter case we play back
        // a sound effect as well.
        for (Iterator<Fish> iter = raindrops.iterator(); iter.hasNext(); ) {
            Fish raindrop = iter.next();

            if(raindrop.getRectangle().overlaps(points[raindrop.getCurrentPoint()]) && raindrop.getCurrentPoint() != points.length-1){
                raindrop.setCurrentPoint(raindrop.getCurrentPoint() + 1);
            }

            if(points[raindrop.getCurrentPoint()].x < raindrop.getRectangle().x){
                raindrop.getRectangle().x -= 100 * Gdx.graphics.getDeltaTime();
                raindrop.setDirectionLeft(true);
            }
            if(points[raindrop.getCurrentPoint()].x > raindrop.getRectangle().x){
                raindrop.getRectangle().x += 100 * Gdx.graphics.getDeltaTime();
                raindrop.setDirectionLeft(false);
            }

            if(points[raindrop.getCurrentPoint()].y < raindrop.getRectangle().y) {
                raindrop.getRectangle().y -= 100 * Gdx.graphics.getDeltaTime();
            }
            if(points[raindrop.getCurrentPoint()].y > raindrop.getRectangle().y){
                raindrop.getRectangle().y += 100 * Gdx.graphics.getDeltaTime();
            }

            if(raindrop.getRectangle().y < 0){
                health -= HEALTH_LOSS;
                iter.remove();
            }

            for(int i=0; i< cats.size; i++){
            if(TimeUtils.timeSinceMillis(cats.get(i).getCurrentInterval()) >= cats.get(i).getAttackInterval()) {
                    if (cats.get(i).getRange().overlaps(raindrop.getRectangle())) {
                        raindrop.setHealth(raindrop.getHealth() - cats.get(i).getDamage());
                        cats.get(i).setCurrentInterval(TimeUtils.millis());
                    }
                }
            }

            //use libgdx Timer and Task for this
            if(raindrop.getHealth() <= 0){
                iter.remove();
                gold += raindrop.getGoldDrop();
                System.out.println(gold);
            }

            if(health <= 0){
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void dispose() {
        // dispose of all the native resources
        dropImage.dispose();
        dropImageRotated.dispose();
        //moonCatImage.dispose();
        backgroundImage.dispose();
        //dropSound.dispose();
        //rainMusic.dispose();
        batch.dispose();
    }
}