package com.gameproject.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.gameproject.game.MyGdxGame;

import java.io.IOException;

public class PauseState extends State {
    private int selection = 1;
    private Texture background, pause, bbt, gbt;
    private BitmapFont font = new BitmapFont(Gdx.files.internal("Fonts/oswald-32.fnt"));
    private Label Resume, MainMenu, Exit;
    private Label.LabelStyle ResumeStyle = new Label.LabelStyle();
    private Label.LabelStyle MainMenuStyle = new Label.LabelStyle();
    private Label.LabelStyle ExitStyle = new Label.LabelStyle();
    private Music PauseMusic;
    public PauseState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("menu.jpg");
        pause = new Texture("pb.png");
        bbt = new Texture("bt1.png");
        gbt = new Texture("gbt.png");
        PauseMusic = Gdx.audio.newMusic(Gdx.files.internal("mm.mp3"));
        ResumeStyle.font = font;
        MainMenuStyle.font = font;
        ExitStyle.font = font;
        if(selection == 1) ResumeStyle.fontColor = Color.BLACK;
        else ResumeStyle.fontColor = Color.LIGHT_GRAY;
        if(selection == 2) MainMenuStyle.fontColor = Color.BLACK;
        else MainMenuStyle.fontColor = Color.LIGHT_GRAY;
        if(selection == 3) ExitStyle.fontColor = Color.BLACK;
        else ExitStyle.fontColor = Color.LIGHT_GRAY;
        Resume = new Label("Resume",ResumeStyle);
        Resume.setPosition(420-Resume.getWidth()/2,300);
        Resume.setFontScale(.8f,.8f);
        MainMenu = new Label("MainMenu",MainMenuStyle);
        MainMenu.setPosition(420-MainMenu.getWidth()/2,225);
        MainMenu.setFontScale(.8f,.8f);
        Exit = new Label("Exit",ExitStyle);
        Exit.setPosition(420-Exit.getWidth()/2,150);
        Exit.setFontScale(.8f,.8f);
    }

    @Override
    public void handleInput() throws IOException {
       /* if(Gdx.input.justTouched()){
            gsm.pop();
            dispose();
        }*/
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            selection++;
            if(selection == 4) selection = 1;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            selection--;
            if(selection == 0) selection = 3;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            if(selection == 1){
                gsm.pop();
                dispose();
            }
            else if(selection == 2){
                gsm.pop();
                gsm.set(new MenuState(gsm));
                dispose();
            }
            else if(selection == 3) Gdx.app.exit();

        }
    }

    @Override
    public void update(float dt) throws IOException {
        handleInput();
        if(selection == 1) ResumeStyle.fontColor = Color.BLACK;
        else ResumeStyle.fontColor = Color.LIGHT_GRAY;
        if(selection == 2) MainMenuStyle.fontColor = Color.BLACK;
        else MainMenuStyle.fontColor = Color.LIGHT_GRAY;
        if(selection == 3) ExitStyle.fontColor = Color.BLACK;
        else ExitStyle.fontColor = Color.LIGHT_GRAY;
        Resume = new Label("Resume",ResumeStyle);
        Resume.setPosition(420-Resume.getWidth()/2,300);
        Resume.setFontScale(.8f,.8f);
        MainMenu = new Label("MainMenu",MainMenuStyle);
        MainMenu.setPosition(420-MainMenu.getWidth()/2,225);
        MainMenu.setFontScale(.8f,.8f);
        Exit = new Label("Exit",ExitStyle);
        Exit.setPosition(420-Exit.getWidth()/2,150);
        Exit.setFontScale(.8f,.8f);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, MyGdxGame.width, MyGdxGame.height);
      //  sb.draw(pause, MyGdxGame.width/2 - pause.getWidth()/2, MyGdxGame.height/2);
        if(selection == 1) sb.draw(gbt,Resume.getX()-22,Resume.getY()-1,Resume.getWidth()+25,Resume.getHeight());
        else sb.draw(bbt,Resume.getX()-22,Resume.getY()-1,Resume.getWidth()+25,Resume.getHeight());
        Resume.draw(sb, 1);
        if(selection == 2) sb.draw(gbt,MainMenu.getX()-20,MainMenu.getY()-3,MainMenu.getWidth()+16,MainMenu.getHeight()+4);
        else sb.draw(bbt,MainMenu.getX()-20,MainMenu.getY()-3,MainMenu.getWidth()+16,MainMenu.getHeight()+4);
        MainMenu.draw(sb, 1);
        if(selection == 3) sb.draw(gbt,Exit.getX()-22,Exit.getY()-1,Exit.getWidth()+33,Exit.getHeight());
        else sb.draw(bbt,Exit.getX()-22,Exit.getY()-1,Exit.getWidth()+33,Exit.getHeight());
        Exit.draw(sb, 1);
        PauseMusic.play();
        sb.end();

    }
    @Override
    public void dispose(){
        background.dispose();
        pause.dispose();
        bbt.dispose();
        gbt.dispose();
        PauseMusic.dispose();
    }
}
