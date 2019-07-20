package com.gameproject.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

import static java.lang.Math.abs;

public class Player{
    private static final float g = (float)-9.8;
    private int dropping = 0,jumpcount = 3, Life = 5 , hit = 1;
    private float move, droptime = 0;
    private Vector2 position;
    private Vector2 velocity;
    private Texture player;
    private Music life;

    public Player(int x, int y){
        position = new Vector2(x,y);
        velocity = new Vector2(0,0);
        player = new Texture("b.png");
        life = Gdx.audio.newMusic(Gdx.files.internal("life.mp3"));
    }
    public void update(float dt, int zup, int zdown, int zupc, int zdownc, int fallup, int falldown, int crossup, int crossdown, int speed){
        if(zup == 0 && zdown == 0) {
            if (position.y > 11) velocity.add(0, g);
            if (position.y >= 494){
                position.y = 494;
                velocity.y = g;
            }
            velocity.scl(dt);
            position.add(0, velocity.y);
            if (position.y <= 11){
                if(hit == 1){
                    Life--;
                    life.play();
                    hit = 0;
                }
                droptime += dt;
                position.y = 11;
                if(droptime >= 1 && Life > 0){
                    Life--;
                    life.play();
                    droptime = 0;
                }
            }
            else{
                hit = 1;
                droptime = 0;
            }
            if(dropping == 1 && position.y > 11) position.add( move, 0);
            velocity.scl(1 / dt);
            if (position.x <= 0) position.x = 0;
            if (position.x >= 815) position.x = 815;
        }
        else{
            velocity.y=0;
            dropping = 0;
            jumpcount = 3;
           /* if(crossup != -1 && zup == 1 && crossup == zupc && zupc != -1){
                position.add(-speed,speed);

            }*/
            if(crossdown != -1 && zdown == 1 && crossdown == zdownc && zdownc != -1 && position.y >= 11){
                position.add(-speed,-speed);
               // System.out.println(crossdown+" "+zdown+" "+zdownc);
            }
            //if(crossup == 0 && crossdown == 0) position.add( -speed, 0);
            else position.add( -speed, 0);
            if (position.x <= 0) position.x = 0;


        }
        if(falldown == 1 || fallup == 1) velocity.y = -25;
    }
    public Texture getPlayer(){
        return player;
    }
    public Vector2 getPosition(){
        return position;
    }
    public void jump(float x){
        if(jumpcount > 0){
            velocity.y = 350;
            dropping = 1;
            jumpcount--;
        }
        move = x;
    }
    public int getLife(){
        return Life;
    }
    public int getJump(){
        return jumpcount;
    }
    public void move(int x){
        position.x += x;
    }

}
