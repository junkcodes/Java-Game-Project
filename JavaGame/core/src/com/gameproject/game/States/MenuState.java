package com.gameproject.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.gameproject.game.MyGdxGame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MenuState extends State {
    FileReader FileRead;
    BufferedReader ScoreRead;
    private int selection = 1, data = 1;
    private String score;
    private Texture background, pause, bbt, gbt, bgm1, bgm2, frame;
    private BitmapFont font = new BitmapFont(Gdx.files.internal("Fonts/oswald-32.fnt"));
    private Label Play, HighScore, Mannual, Exit, Help, Score;
    private Label.LabelStyle PlayStyle = new Label.LabelStyle();
    private Label.LabelStyle HighScoreStyle = new Label.LabelStyle();
    private Label.LabelStyle MannualStyle = new Label.LabelStyle();
    private Label.LabelStyle ExitStyle = new Label.LabelStyle();
    private Label.LabelStyle HelpStyle = new Label.LabelStyle();
    private Label.LabelStyle ScoreStyle = new Label.LabelStyle();
    private Music MenuMusic;

    public MenuState(GameStateManager gsm) throws IOException {
        super(gsm);
        background = new Texture("menu.jpg");
        pause = new Texture("pb.png");
        bbt = new Texture("bt.png");
        gbt = new Texture("gbt.png");
        bgm1 = new Texture("bg1.png");
        bgm2 = new Texture("bg2.png");
        frame = new Texture("frame1.png");
        MenuMusic = Gdx.audio.newMusic(Gdx.files.internal("mm.mp3"));
        PlayStyle.font = font;
        HighScoreStyle.font = font;
        MannualStyle.font = font;
        ExitStyle.font = font;
        HelpStyle.font = font;
        ScoreStyle.font = font;
        FileRead = new FileReader("Score/Score.txt");
        ScoreRead = new BufferedReader(FileRead);
        score = ScoreRead.readLine();
        HelpStyle.fontColor = Color.GRAY;
        ScoreStyle.fontColor = Color.GRAY;
        if(selection == 1) PlayStyle.fontColor = Color.BLACK;
        else PlayStyle.fontColor = Color.LIGHT_GRAY;
        if(selection == 2) HighScoreStyle.fontColor = Color.BLACK;
        else HighScoreStyle.fontColor = Color.LIGHT_GRAY;
        if(selection == 3) MannualStyle.fontColor = Color.BLACK;
        else MannualStyle.fontColor = Color.LIGHT_GRAY;
        if(selection == 4) ExitStyle.fontColor = Color.BLACK;
        else ExitStyle.fontColor = Color.LIGHT_GRAY;
        Play = new Label("Play",PlayStyle);
        Play.setPosition(150-Play.getWidth()/2,375);
        Play.setFontScale(.8f,.8f);
        HighScore = new Label("HighScore",HighScoreStyle);
        HighScore.setPosition(150-HighScore.getWidth()/2,300);
        HighScore.setFontScale(.8f,.8f);
        Mannual = new Label("Help",MannualStyle);
        Mannual.setPosition(150-Mannual.getWidth()/2,225);
        Mannual.setFontScale(.8f,.8f);
        Exit = new Label("Exit",ExitStyle);
        Exit.setPosition(150-Exit.getWidth()/2,150);
        Exit.setFontScale(.8f,.8f);
        Help = new Label("The goal is to survive on the tiles as long as you can. The more\ntile you hit during survival, the more point you gain. To be the\nchampion, you have to beat the previous high score.\nInstructions:\n1. To jump left press and hold down the space bar and then press\nleft key.\n2. To jump right press and hold down the space bar  and then press\nright key.\n3. To move right press right key.\n4. To move left press the left key.\nBe aware of the special tiles.",HelpStyle);
        Help.setPosition(280,-10);
        Help.setFontScale(.6f,.7f);
        Score = new Label("Current HighScore : "+score,ScoreStyle);
        Score.setPosition(280,255);
        Score.setFontScale(.9f,.9f);
    }

    @Override
    public void handleInput() throws IOException {
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            selection++;
            if(selection == 5) selection = 1;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            selection--;
            if(selection == 0) selection = 4;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) data = 1;

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            if(selection == 1){
                gsm.set(new PlayState(gsm));
                dispose();
            }
            else if(selection == 2) data = 2;
            else if(selection == 3) data = 3;
            else if(selection == 4) Gdx.app.exit();
        }
    }

    @Override
    public void update(float dt) throws IOException {
        handleInput();
       // mouseScreenPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        if(selection == 1) PlayStyle.fontColor = Color.BLACK;
        else PlayStyle.fontColor = Color.LIGHT_GRAY;
        if(selection == 2) HighScoreStyle.fontColor = Color.BLACK;
        else HighScoreStyle.fontColor = Color.LIGHT_GRAY;
        if(selection == 3) MannualStyle.fontColor = Color.BLACK;
        else MannualStyle.fontColor = Color.LIGHT_GRAY;
        if(selection == 4) ExitStyle.fontColor = Color.BLACK;
        else ExitStyle.fontColor = Color.LIGHT_GRAY;
        Play = new Label("Play",PlayStyle);
        Play.setPosition(150-Play.getWidth()/2,375);
        Play.setFontScale(.8f,.8f);
        HighScore = new Label("HighScore",HighScoreStyle);
        HighScore.setPosition(150-HighScore.getWidth()/2,300);
        HighScore.setFontScale(.8f,.8f);
        Mannual = new Label("Help",MannualStyle);
        Mannual.setPosition(150-Mannual.getWidth()/2,225);
        Mannual.setFontScale(.8f,.8f);
        Exit = new Label("Exit",ExitStyle);
        Exit.setPosition(150-Exit.getWidth()/2,150);
        Exit.setFontScale(.8f,.8f);
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        if(data == 2){
            sb.draw(bgm1,0,0,MyGdxGame.width,MyGdxGame.height);
            sb.draw(frame,250,255-200,550,400);
            Score.draw(sb,1);

        }
        else if(data == 3){
            sb.draw(bgm2,0,0,MyGdxGame.width,MyGdxGame.height);
            sb.draw(frame,250,255-200,550,400);
            Help.draw(sb,1);
        }
        else {
            sb.draw(background, 0, 0, MyGdxGame.width, MyGdxGame.height);
            if (selection == 1) sb.draw(gbt, Play.getX() - 20, Play.getY() - 1, Play.getWidth() + 27, Play.getHeight());
            else sb.draw(bbt, Play.getX() - 20, Play.getY() - 1, Play.getWidth() + 27, Play.getHeight());
            Play.draw(sb, 1);
            if (selection == 2)
                sb.draw(gbt, HighScore.getX() - 20, HighScore.getY() - 2, HighScore.getWidth() + 16, HighScore.getHeight());
            else
                sb.draw(bbt, HighScore.getX() - 20, HighScore.getY() - 2, HighScore.getWidth() + 16, HighScore.getHeight());
            HighScore.draw(sb, 1);
            if (selection == 3)
                sb.draw(gbt, Mannual.getX() - 22, Mannual.getY() - 1, Mannual.getWidth() + 31, Mannual.getHeight());
            else sb.draw(bbt, Mannual.getX() - 22, Mannual.getY() - 1, Mannual.getWidth() + 31, Mannual.getHeight());
            Mannual.draw(sb, 1);
            if (selection == 4) sb.draw(gbt, Exit.getX() - 21, Exit.getY() - 1, Exit.getWidth() + 31, Exit.getHeight());
            else sb.draw(bbt, Exit.getX() - 21, Exit.getY() - 1, Exit.getWidth() + 31, Exit.getHeight());
            Exit.draw(sb, 1);
        }
        // sb.draw(pause, MyGdxGame.width/2 - pause.getWidth()/2, MyGdxGame.height/2);
        MenuMusic.play();
        sb.end();

    }
    @Override
    public void dispose(){
        background.dispose();
        pause.dispose();
        bbt.dispose();
        gbt.dispose();
        MenuMusic.dispose();
    }
}
