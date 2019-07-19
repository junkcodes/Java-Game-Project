package com.gameproject.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.gameproject.game.MyGdxGame;
import com.gameproject.game.Sprites.GifDecoder;
import com.gameproject.game.Sprites.Player;
import com.gameproject.game.Sprites.Tiles;

public class PlayState extends State {
    private Texture background,db1,db2,life;
    private Vector3[] positionup, positiondown;
    private Vector2[] pointup, pointdown;
    int xx=0,x=0,y=0,zup=0,zupc=-1, zdown=0, zdownc = -1, fallup=0, falldown = 0, score = 0, cu, cd;
    float time = 0, timex = 0;
    int[][] arr = {{-13,102},{-12,103},{-11,104},{-10,105},{-9,106},{-8,107},{-7,108},{-6,109},{-5,110},{-4,111},{-3,112},{-2,113}};
    private Player player;
    BitmapFont font = new BitmapFont(Gdx.files.internal("Fonts/oswald-32.fnt"));
    private Label Score,Jump,Life;
    private Label.LabelStyle labelStyle= new Label.LabelStyle();
    private Tiles tile;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.jpg");
        db1 = new Texture("db22.png");
        db2 = new Texture("db222.png");
        life = new Texture("xxx.png");
      //  animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("life1.gif").read());
        //tile = new Texture("xyz.png");
        player = new Player(210, 315);
        tile = new Tiles();
        labelStyle.font = font;
        labelStyle.fontColor = Color.GRAY;
        Score = new Label("Score : "+String.format("%d",score), labelStyle);
        Score.setFontScale(1.1f,0.8f);
        Score.setPosition(20,485);
        Jump = new Label("Jump : "+String.format("%d",player.getJump()), labelStyle);
        Jump.setFontScale(1.1f,0.8f);
        Jump.setPosition(700,485);
        Life = new Label("Life :", labelStyle);
        Life.setFontScale(1.1f,0.8f);
        Life.setPosition(840/2 - Life.getWidth()/2-(player.getLife()*32+32)/2,485);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            zup = 0;
            zdown = 0;
            if (fallup == 0) {
                if (zupc != -1) tile.setCollisionUp(zupc, 1);
                if(zdownc != -1) tile.setCollisionDown(zdownc,1);
                player.jump((float)1.5);
            }
            else if(falldown == 0){
                if (zupc != -1) tile.setCollisionUp(zupc, 1);
                if(zdownc != -1) tile.setCollisionDown(zdownc,1);
                player.jump((float)-1.5);
            }
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            zup = 0;
            zdown = 0;
            if(fallup == 0){
                if(zupc != -1) tile.setCollisionUp(zupc,1);
                player.jump((float)-1.5);
            }
            else if(falldown == 0){
                if(zdownc != -1) tile.setCollisionDown(zdownc,1);
                player.jump((float)-1.5);
            }
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if(fallup == 0 || falldown == 0) player.move(-tile.getSpeed()-1);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if(fallup == 0 || falldown == 0) player.move(tile.getSpeed()+1);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            gsm.push(new PauseState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        positionup = tile.getPositionUp();
        positiondown = tile.getPositionDown();
        fallup = 0;
        falldown = 0;
        for(int i=0; i<6; i++) if(player.getPosition().x >= positionup[i].x - 20 && player.getPosition().x <= positionup[i].x + 120) if(positionup[i].y - player.getPosition().y <= 27 && positionup[i].y - player.getPosition().y >= 15) fallup = 1;
        for(int i=0; i<4; i++) if(player.getPosition().x >= positiondown[i].x - 20 && player.getPosition().x <= positiondown[i].x + 120) if(positiondown[i].y - player.getPosition().y <= 27 && positiondown[i].y - player.getPosition().y >= 15) falldown = 1;
        cu = tile.crossUp(score,zupc);
        cd = tile.crossDown(score,zdownc);
        player.update(dt,zup,zdown,zupc,zdownc,fallup,falldown,cu,cd,tile.getSpeed());
        tile.update(dt);
        if(player.getLife() <= 0){
            System.out.println("lost");
            Gdx.app.exit();
        }
        tile.setSpeed(score);
       // player.update(dt,zup,zdown,zupc,zdownc,fallup,falldown,cu,cd,tile.getSpeed());
        Score= new Label("Score : "+String.format("%d",score), labelStyle);
        Score.setFontScale(1.1f,0.8f);
        Score.setPosition(20, 485);
        Jump = new Label("Jump : "+String.format("%d",player.getJump()), labelStyle);
        Jump.setFontScale(1.1f,0.8f);
        Jump.setPosition(700,485);
        Life = new Label("Life :", labelStyle);
        Life.setFontScale(1.1f,0.8f);
        Life.setPosition(840/2 - Life.getWidth()/2-(player.getLife()*32+32)/2,485);
        time += dt;
        timex += dt;
        if(x == 7){
            x=0;
            y++;
            if(y == 8) y=0;
        }

        if(timex > .15){
            xx++;
            if(xx == 3) xx = 0;
            timex = 0;
        }

    }
    @Override
    public void render(SpriteBatch sb) {
       // elapsed += Gdx.graphics.getDeltaTime();
        sb.begin();
         // sb.draw(background, 0, 0, MyGdxGame.width, MyGdxGame.height);
        pointup = tile.checkPointUp();
        positionup = tile.getPositionUp();
        pointdown = tile.checkPointDown();
        positiondown = tile.getPositionDown();
        zupc = -1;
        for(int i=0; i<6; i++){
            if(player.getPosition().y - positionup[i].y >= 13 && player.getPosition().y <= tile.getTile().getHeight()/3+positionup[i].y-1 && positionup[i].z == 0){
                for(int j=0; j<11; j++){
                    if(player.getPosition().x >= arr[j][0]+positionup[i].x && player.getPosition().x <= arr[j][1]+positionup[i].x){
                        zup = 1;
                        if(pointup[i].x == 0){
                            tile.setPointUp(i);
                            score++;
                        }
                        zupc = i;
                        break;
                    }
                }
            }
            if(zupc == -1){
                zup = 0;
                tile.setCollisionUp(i, 0);
            }
            sb.draw(tile.getTile(), positionup[i].x, positionup[i].y,tile.getTile().getWidth()/4,tile.getTile().getHeight()/3);
        }
        zdownc = -1;
        for(int i=0; i<4; i++){
            if(player.getPosition().y - positiondown[i].y >= 13 && player.getPosition().y <= tile.getTile().getHeight()/3+positiondown[i].y-1 && positiondown[i].z == 0){
                for(int j=0; j<11; j++){
                    if(player.getPosition().x >= arr[j][0]+positiondown[i].x && player.getPosition().x <= arr[j][1]+positiondown[i].x){
                        zdown = 1;
                        if(pointdown[i].x == 0){
                            tile.setPointDown(i);
                            score++;
                        }
                        zdownc = i;
                        break;
                    }
                }
            }
            if(zdownc == -1){
                zdown = 0;
                tile.setCollisionDown(i, 0);
            }
            sb.draw(tile.getTile(), positiondown[i].x, positiondown[i].y,tile.getTile().getWidth()/4,tile.getTile().getHeight()/3);
        }
        // font.getData().setScale(2, 2);
        if(time <= .5) {
            sb.draw(db1, -8, 0, 856, 12);
        }
        else {
            sb.draw(db2, -8, 0, 856, 12);
            if(time > 1) time = 0;
        }
        Score.draw(sb, 1);
        Jump.draw(sb, 1);
        for(int i=player.getLife(); i > 0; i-- ) sb.draw(new TextureRegion(life,800*xx,0,800,600), Life.getX()+30+i*30,482,45,40);

        sb.draw(new TextureRegion(player.getPlayer(),128*x,128*y,128,128), player.getPosition().x,player.getPosition().y,128/(float)5,128/(float)5);
        x++;
        Life.draw(sb, 1);


        sb.end();
    }

    @Override
    public void dispose(){

    }
}
