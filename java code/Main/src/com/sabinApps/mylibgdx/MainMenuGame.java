package com.sabinApps.mylibgdx;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;


public class MainMenuGame implements Screen {

    final MenuScreen game;
    String selected;
    Texture playbuttontexture,bg,play,pause,help,about,score,title;
    TextureRegion playbuttonregion;
    Sprite sprite,play_s,pause_s,help_s,about_s,score_s,title_s;
    Image playbutton,pauseButton,helpButton,aboutButton,scoreButton;
    SpriteBatch batch;
    Label label;
    Stage stage;
    BitmapFont font;
    Skin skin;
    float width,height;
    int highScore;
    OrthographicCamera camera;

    public MainMenuGame(final MenuScreen gam) {
        Texture.setEnforcePotImages(false);
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        this.game = gam;
batch=new SpriteBatch();
        Preferences prefs = Gdx.app.getPreferences("highScore");
        highScore = prefs.getInteger("highScore");
       camera = new OrthographicCamera();
        camera.setToOrtho(false, width,height);
        Gdx.input.setInputProcessor(stage);
  stage=new Stage();
        skin = new Skin();
        selected="";
     //   title=new Texture(Gdx.files.internal("menu/title.png"));
        bg=new Texture(Gdx.files.internal("bgs/bg2.png"));
        play=new Texture(Gdx.files.internal("menu/play.png"));
        pause=new Texture(Gdx.files.internal("menu/pause.png"));
        help=new Texture(Gdx.files.internal("menu/help.png"));
        about=new Texture(Gdx.files.internal("menu/about.png"));
        score=new Texture(Gdx.files.internal("menu/score.png"));
sprite=new Sprite(bg);
    // title_s=new Sprite(title);
        play_s=new Sprite(play);
        pause_s=new Sprite(pause);
        score_s=new Sprite(score);
        help_s=new Sprite(help);
        about_s=new Sprite(about);
        bg=null;
        play=null;
        pause=null;
        help=null;
        about=null;
        score=null;
        playbutton = new Image();
        pauseButton=new Image();
        scoreButton=new Image();
        helpButton=new Image();
        aboutButton=new Image();
        playbutton.setDrawable(new SpriteDrawable(play_s));
        playbutton.setPosition(width-200,height-100);
        playbutton.setSize(play_s.getWidth(),play_s.getHeight());
      scoreButton.setDrawable(new SpriteDrawable(score_s));
        scoreButton.setPosition(width-300,playbutton.getY()-100);
        scoreButton.setSize(score_s.getWidth(),score_s.getHeight());
        helpButton.setDrawable(new SpriteDrawable(help_s));
        helpButton.setPosition(width-200,scoreButton.getY()-100);
        helpButton.setSize(help_s.getWidth(),help_s.getHeight());
        aboutButton.setDrawable(new SpriteDrawable(about_s));
        aboutButton.setPosition(width-230,helpButton.getY()-90);
        aboutButton.setSize(about_s.getWidth(), about_s.getHeight());

        playbutton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.clear();
                game.setScreen(new MyLibgdxGame(game));
            }
        });
        scoreButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.clear();
                selected="score";
            }
        });
       helpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.clear();
                selected="help";

            }
        });
        aboutButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.clear();
                selected="about";

            }
        });
        font=new BitmapFont();
        stage.addActor(playbutton);
        stage.addActor(pauseButton);
        stage.addActor(helpButton);
        stage.addActor(aboutButton);
        stage.addActor(scoreButton);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT );
        batch.begin();
    //    camera.update();
   //     batch.setProjectionMatrix(camera.combined);

        batch.draw(sprite, 0, 0, width, height);
        font.draw(batch, ":" + Gdx.graphics.getFramesPerSecond(), 100, 100);
        font.draw(batch, "Game Developed and Maintained by indie Apps", 10, 60);
        if(selected=="")
        {
          //  batch.draw(title_s,10,height-100);
        }
        if (selected == "about") {
            font.setScale(2f);
            font.draw(batch, "ABOUT RUNNING MAN", 100, height - 10);
            font.setScale(1f);
            font.draw(batch, "Game Developed and Maintained by indie Apps", 100, height - 60);
            font.draw(batch, "Email : sabin.khanal.33@gmail.com (sabin Khanal) , Send Bug Reports @ sabinkhanal.com.np/apps", 100, height - 90);
            font.setScale(2f);
            font.draw(batch, "CREDITS", 100, height - 120);
            font.setScale(1f);
            font.draw(batch, "Suresh Kharel and Prashanta Bogati for Support", 100, height - 160);
            font.draw(batch, "Background Assets : ", 100, height - 180);
            font.draw(batch, "Sound Assets : ", 100, height - 220);
            font.draw(batch, "Actor Assets : ", 100, height - 260);
            if (Gdx.input.isTouched()) {
                game.setScreen(new MainMenuGame(game));
            }
        }
        if (selected == "help") {
            font.setScale(2f);
            font.setColor(Color.WHITE);
            font.draw(batch, "Touch the screen or swipe up to Jump", 100, height - 10);
            font.draw(batch, "Collect the Coins and Stay Away from Bombs", 100, height - 50);
            if (Gdx.input.isTouched()) {
                game.setScreen(new MainMenuGame(game));
            }
        }
        if (selected == "score") {
            font.setScale(2f);
            font.setColor(Color.WHITE);
            font.draw(batch, "HIGH SCORE  : " + highScore, 100, height - 10);
            if (Gdx.input.isTouched()) {
                game.setScreen(new MainMenuGame(game));
            }
        }



        batch.end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
//camera.update();
     //  camera.setToOrtho(false,width,height);
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
font.dispose();
        batch.dispose();
        title.dispose();
        bg.dispose();
        play.dispose();
        pause.dispose();
        score.dispose();
        help.dispose();
        about.dispose();
    }

}