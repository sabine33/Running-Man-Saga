package com.sabinApps.mylibgdx;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by sabin on 2/8/2015.
 */
public  class MyLibgdxGame extends Game implements ApplicationListener,Screen {
    final MenuScreen game;
    public Texture sky, gd, br, bomb, bg, heart, eg;
    public Sprite sky_s, bomb_s, bg_s, heart_s, eg_s;
    public Texture[] ground, bar, egg, trees;
    public Sprite[] ground_s,bar_s, egg_s;
    public OrthographicCamera camera;
    public BitmapFont font;
    public float[] ground_x, bar_x, egg_x;
    public float width, height, heart_x, bg_x, bg2_x;
    public float speed = 5, score, level, life = 3, time;
    public int highScore;
    private static final int FRAME_COLS = 6;
    private static final int FRAME_ROWS = 5;
    Animation walkAnimation;
    Texture walkSheet;
    FpsLimiter f;
    TextureRegion[] walkFrames;
    TextureRegion currentFrame;
    public float manx = 300, many = 20;
    float stateTime;
    public Music mp3Music;
    public Sound manGrunt,jump,coinEat,levelUp,bomb_blast;
private long diff,start=System.currentTimeMillis();
    public MyLibgdxGame(final MenuScreen gam) {
Texture.setEnforcePotImages(false);
gam.batch=new SpriteBatch();
        this.game = gam;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        camera=new OrthographicCamera();
       camera.setToOrtho(false, width,height);
        loadAssets();
        makeScreen();
        walkingMan();
        loadMusic();
        font.setScale(1.5f);
        bg_x = 0;
        bg2_x = width - 20;
        int s = (int) rnd(1, 8);
        bg = new Texture(Gdx.files.internal("bgs/bg"+s+".png"));
        bg_s = new Sprite(bg);
        bg=null;
    }
public void sleep(int fps)
{
    if(fps>0)
    {
        diff=System.currentTimeMillis()-start;
        long TargetDelay=1000/fps;
        if(diff<TargetDelay)
        {
            try
            {
                Thread.sleep(TargetDelay-diff);
            }
            catch (InterruptedException e)
            {

            }
            start=System.currentTimeMillis();
        }
    }
}
    public void loadAssets() {
       game.batch = new SpriteBatch();
        sky = new Texture(Gdx.files.internal("bgs/sky1.png"));
        bg = new Texture(Gdx.files.internal("img/sky.jpg"));
        gd = new Texture(Gdx.files.internal("img/grass.png"));
        br = new Texture(Gdx.files.internal("img/bar.png"));
        bomb = new Texture(Gdx.files.internal("img/bomb.png"));
        heart = new Texture(Gdx.files.internal("img/life.png"));
        eg = new Texture(Gdx.files.internal("img/egg.png"));
        font = new BitmapFont();
        sky_s = new Sprite(sky);
        bg_s = new Sprite(bg);
        bomb_s = new Sprite(bomb);
        heart_s = new Sprite(heart);
        eg_s = new Sprite(eg);
        Preferences prefs = Gdx.app.getPreferences("highScore");
        highScore = prefs.getInteger("highScore");

    }

    public void makeScreen() {
        ground = new Texture[3];
        bar = new Texture[4];
        egg = new Texture[4];
        ground_s = new Sprite[3];
        bar_s = new Sprite[4];
        egg_s = new Sprite[4];
        ground_x = new float[4];
        bar_x = new float[4];
        egg_x = new float[4];
        heart_x = 5000;
        bg2_x = width;
for(int i=0;i<3;i++) {
    ground[i] = gd;
    ground_x[0] = 0;
    ground_s[i] = new Sprite(ground[i]);
    if (i > 0) {
        ground_x[i] = ground_x[i - 1] + ground_s[0].getWidth();
    }
}
        for (int i = 0; i < 4; i++) {
            bar[i] = br;
            egg[i] = eg;
            bar_x[i] = (i + 1) * bar_x[1] + rnd(width, width + 300);
            egg_x[i] = width + rnd(100, 300);
            bar_s[i] = new Sprite(bar[i]);
            egg_s[i] = new Sprite(egg[i]);

        }
        sky=null;
        bg=null;
        ground=null;
        bar=null;
        eg=null;
        br=null;
        eg=null;
        gd=null;
        egg=null;
    }

    public float rnd(float a, float b) {
        return MathUtils.random(a, b);
    }

    public void loadMusic() {
        mp3Music = Gdx.audio.newMusic(Gdx.files.internal("music/bouncing.mp3"));
        bomb_blast = Gdx.audio.newSound(Gdx.files.internal("music/bomb.wav"));
        manGrunt = Gdx.audio.newSound(Gdx.files.internal("music/mangrunt.mp3"));
       levelUp = Gdx.audio.newSound(Gdx.files.internal("music/levelUp.wav"));
        coinEat = Gdx.audio.newSound(Gdx.files.internal("music/coin.mp3"));
        mp3Music.setLooping(true);
        mp3Music.setVolume(0.5f);

mp3Music.play();
    }

    @Override
    public void create() {

    }

    public void draw()
    {
        if (Gdx.input.justTouched()  && Gdx.input.isTouched() && many <= 40) {
            many = 160;
        } else {
            stateTime += 1.5 * Gdx.graphics.getDeltaTime();
        }
        speed=(level/2)+500*Gdx.graphics.getDeltaTime();
        if(speed>=17)
            speed=17;

        if ((score % 2000) >= 0 && (score % 2000) <= 50 && score>100) {
            levelUp.play(0.1f);
        }
        if (bg_x < (-width + 15))
            bg_x = width - 10;
        if (bg2_x <= (-width + 15))
            bg2_x = width - 10;
        bg_x -= 0.4;
        bg2_x -= 0.4;
        if (many > 200) {
            many = 200;
        }
        if (many >= 20) {
            stateTime = 2;
            many -= 6;
        }
        egg_x[2] = -300;
        egg_x[3] = -300;
        if (manx >= heart_x && (manx <= heart_x + 32)) {
            life = life + 1;
            heart_x = 8000;
        }
        if (heart_x < -400)
            heart_x = 6000;
        for(int i=0;i<3;i++)
        {
            if (ground_x[i] < (-width - 100)) {
                ground_x[i] = width;
            }
            if (egg_x[i] < -400) {
                egg_x[i] = width+200;
            }
            if (bar_x[i] < -400) {
                bar_x[i] = i * 1000 + width+100;
            }

            if ((manx) >= (bar_x[i] - 10) && manx <= (bar_x[i] + 30) && many <= 30) {
                bar_x[i] = -100;
                bomb_blast.play(0.5f);
                life = life - 1;
                manGrunt.play();

            }

            if ((manx) >= (egg_x[i] - 10) && manx <= (egg_x[i] + 40) && many <= 60) {
                coinEat.play(1.5f);
                egg_x[i] = -100;
                score = score + 100;

            }

            ground_x[i] -= (speed);
            bar_x[i] -= (speed );
            egg_x[i] -= (speed );

        }
        score += 1;
        level = score / 1000;
        heart_x -= (speed );
    }
    public void highScore()
    {
        highScore = (int) score;
        levelUp.play(0.5f);
        Preferences prefs = Gdx.app.getPreferences("highScore");
        prefs.putInteger("highScore", (int) score);
        prefs.flush();
    }
public void gameOver()
{
    font.setColor(Color.BLACK);
    game.batch.draw(sky_s, 0, 0, width, height);
    font.draw(game.batch, "Score : " + score + " | GAME OVER ", 40, 200);
    font.draw( game.batch, "Tap to Play Again ", 40, 300);
    mp3Music.stop();
    if (Gdx.input.justTouched() && Gdx.input.isTouched()) {
        life = 3;
        score = 0;
        game.setScreen(new MainMenuGame(game));

    }
}
    @Override
    public void render(float v) {

  // Gdx.gl.glClearColor(0, 0, 0, 0);
   //   Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      game.batch.begin();
    //  camera.update();
        int fps=30;
       game.batch.setProjectionMatrix(camera.combined);
        if (score > highScore) {
         highScore();
        }
       if(life>0)
       {
               game.batch.draw(sky_s, 0, 0, width + 10, height);

draw();


           game.batch.draw(bg_s, bg_x, 0, width + 10, height);
          game.batch.draw(bg_s, bg2_x, 0, width + 10, height);
        time = Gdx.graphics.getDeltaTime() * 60;
        for (int i = 0; i < 3; i++) {
            game.batch.draw(ground_s[i], ground_x[i], 0, width,40);
            game.batch.draw(egg_s[i], egg_x[i], 60, 40, 40);
            game.batch.draw(bar_s[i], bar_x[i], 0, 30, 64);
            game.batch.draw(bomb_s, bar_x[i], 0, 50, 50);
        }
           scoreShow();
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);
       game.batch.draw(currentFrame, manx, many);
    }
    else
    {
        gameOver();
    }
        game.batch.end();

    }
public void scoreShow()
{
    game.batch.draw(heart_s, heart_x, 40, width / 25, (height * 8 / 100));
    game.batch.draw(eg_s, 0, height - 40, 32, 32);
    font.draw(game.batch, " X " + (int) score + " / " + highScore, 30, height - 10);
    font.draw(game.batch, "LEVEL :" + (int) level, width / 2 - width / 8, height - 10);
    game.batch.draw(heart_s, width - (width / 6), height - 40, 32, 32);
    font.draw(game.batch, " x " + (int) life, width - (width / 8), height - 10);
    font.draw(game.batch, "" + Gdx.graphics.getFramesPerSecond(), width - (width / 4), height - 10);
}
    @Override
    public void resize(int i, int i1) {
      camera.update();
        camera.setToOrtho(false,width,height);
    }


    @Override
    public void render() {

    }

    @Override
    public void pause() {

    }

    public void walkingMan() {
        walkSheet = new Texture(Gdx.files.internal("img/anim.png"));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);              // #10
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];

        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation(0.025f, walkFrames);
        stateTime = 0f;
        walkSheet=null;
        walkFrames=null;
        tmp=null;

    }




    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        font.dispose();
        bomb.dispose();
        game.batch.dispose();
        //  camera.dispose();
        mp3Music.dispose();
        bg.dispose();
        for (int i = 0; i < 4; i++) {
            ground[i].dispose(); bar[i].dispose(); egg[i].dispose(); trees[i].dispose();

        }


    }
    /*
    public void loadTrees() {
        trees = new Texture[4];
        trees_s = new Sprite[4];
        tree_x = new float[5];
        for (int i = 0; i < 4; i++) {
            trees[i] = new Texture(Gdx.files.internal("img/tree" + i + ".png"));
            trees_s[i] = new Sprite(trees[i]);
            tree_x[i] = rnd(width,width+100);
        }

    }
    */


}