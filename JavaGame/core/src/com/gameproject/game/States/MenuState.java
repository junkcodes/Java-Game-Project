package com.gameproject.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.gameproject.game.MyGdxGame;

public class MenuState extends State {
    private int selection = 1, data = 1;
    private Texture background, pause, bbt, gbt, bgm;
    private BitmapFont font = new BitmapFont(Gdx.files.internal("Fonts/oswald-32.fnt"));
    private Label Play, HighScore, Mannual, Exit;
    private Label.LabelStyle PlayStyle = new Label.LabelStyle();
    private Label.LabelStyle HighScoreStyle = new Label.LabelStyle();
    private Label.LabelStyle MannualStyle = new Label.LabelStyle();
    private Label.LabelStyle ExitStyle = new Label.LabelStyle();

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("menu.jpg");
        pause = new Texture("pb.png");
        bbt = new Texture("bt.png");
        gbt = new Texture("gbt.png");
        PlayStyle.font = font;
        HighScoreStyle.font = font;
        MannualStyle.font = font;
        ExitStyle.font = font;
        if(selection == 1) PlayStyle.fontColor = Color.BLACK;
        else PlayStyle.fontColor = Color.GRAY;
        if(selection == 2) HighScoreStyle.fontColor = Color.BLACK;
        else HighScoreStyle.fontColor = Color.GRAY;
        if(selection == 3) MannualStyle.fontColor = Color.BLACK;
        else MannualStyle.fontColor = Color.GRAY;
        if(selection == 4) ExitStyle.fontColor = Color.BLACK;
        else ExitStyle.fontColor = Color.GRAY;
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
    public void handleInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            selection++;
            if(selection == 5) selection = 1;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            selection--;
            if(selection == 0) selection = 4;
        }
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
    public void update(float dt) {
        handleInput();
       // mouseScreenPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        if(data == 2){

        }
        else if(data == 3){

        }
        if(selection == 1) PlayStyle.fontColor = Color.BLACK;
        else PlayStyle.fontColor = Color.GRAY;
        if(selection == 2) HighScoreStyle.fontColor = Color.BLACK;
        else HighScoreStyle.fontColor = Color.GRAY;
        if(selection == 3) MannualStyle.fontColor = Color.BLACK;
        else MannualStyle.fontColor = Color.GRAY;
        if(selection == 4) ExitStyle.fontColor = Color.BLACK;
        else ExitStyle.fontColor = Color.GRAY;
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
        sb.draw(background, 0, 0, MyGdxGame.width, MyGdxGame.height);
        if(selection == 1) sb.draw(gbt,Play.getX()-20,Play.getY()-1,Play.getWidth()+27,Play.getHeight());
        else sb.draw(bbt,Play.getX()-20,Play.getY()-1,Play.getWidth()+27,Play.getHeight());
        Play.draw(sb, 1);
        if(selection == 2) sb.draw(gbt,HighScore.getX()-20,HighScore.getY()-2,HighScore.getWidth()+16,HighScore.getHeight());
        else sb.draw(bbt,HighScore.getX()-20,HighScore.getY()-2,HighScore.getWidth()+16,HighScore.getHeight());
        HighScore.draw(sb, 1);
        if(selection == 3) sb.draw(gbt,Mannual.getX()-22,Mannual.getY()-1,Mannual.getWidth()+31,Mannual.getHeight());
        else sb.draw(bbt,Mannual.getX()-22,Mannual.getY()-1,Mannual.getWidth()+31,Mannual.getHeight());
        Mannual.draw(sb, 1);
        if(selection == 4) sb.draw(gbt,Exit.getX()-21,Exit.getY()-1,Exit.getWidth()+31,Exit.getHeight());
        else sb.draw(bbt,Exit.getX()-21,Exit.getY()-1,Exit.getWidth()+31,Exit.getHeight());
        Exit.draw(sb, 1);
        // sb.draw(pause, MyGdxGame.width/2 - pause.getWidth()/2, MyGdxGame.height/2);
        sb.end();

    }
    @Override
    public void dispose(){
        background.dispose();
        pause.dispose();
        bbt.dispose();
        gbt.dispose();
    }
}
