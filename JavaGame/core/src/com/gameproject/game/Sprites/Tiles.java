package com.gameproject.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;
import java.util.Vector;

public class Tiles {
    private Texture tile;
    private int speed = 1, c = 1, cu = 25, cd = 50, cdr, rcu, rcd;
    private Vector3[] positionup = new Vector3[6];
    private Vector2[] pointup = new Vector2[6];
    private Vector2[] crossup = new Vector2[6];
    private Vector2[] pointdown = new Vector2[4];
    private Vector3[] positiondown = new Vector3[4];
    private Vector2[] crossdown = new Vector2[4];
    Random rand = new Random();
    public Tiles(){
        tile = new Texture("base.png");
        crossup[0] = new Vector2(0,0);
        pointup[0] = new Vector2(0,0);
        positionup[0] = new Vector3(200,300,0);
        crossdown[0] = new Vector2(0,0);
        pointdown[0] = new Vector2(0,0);
        positiondown[0] = new Vector3(200,70,0);
        for(int i=1; i<6; i++){
            crossup[i] = new Vector2(0,0);
            pointup[i] = new Vector2(0,0);
            positionup[i] = new Vector3(positionup[i-1].x+200+rand.nextInt(100),230+rand.nextInt(150),0);
        }
        for(int i=1; i<4; i++){
            crossdown[i] = new Vector2(0,0);
            pointdown[i] = new Vector2(0,0);
            positiondown[i] = new Vector3(positiondown[i-1].x+250+rand.nextInt(70),100+rand.nextInt(115),0);
        }
    }
    public void update(float dt){
        for(int i=0; i<6; i++){
            if(crossup[i].y == 1 && positionup[i].y <= 440) positionup[i].add(-speed,speed,0);
            else{
                crossup[i].y = 0;
                positionup[i].add(-speed,0,0);
            }
            if(positionup[i].x <= -140){
                pointup[i].x=0;
                if(i == 0){
                    positionup[i].x = positionup[5].x+200+rand.nextInt(50);
                    positionup[i].y = 220+rand.nextInt(190);
                }
                else{
                    positionup[i].x = positionup[i-1].x+200+rand.nextInt(50);
                    positionup[i].y = 220+rand.nextInt(190);
                }
            }
        }
        for(int i=0; i<4; i++){
            if(crossdown[i].y == 1 && positiondown[i].y >= -20) positiondown[i].add(-speed,-speed,0);
            else {
                crossdown[i].y = 0;
                positiondown[i].add(-speed, 0, 0);
            }
            if(positiondown[i].x <= -140){
                pointdown[i].x=0;
                if(i == 0){
                    positiondown[i].x = positiondown[3].x+250+rand.nextInt(70);
                    positiondown[i].y = 70+rand.nextInt(115);
                }
                else{
                    positiondown[i].x = positiondown[i-1].x+250+rand.nextInt(70);
                    positiondown[i].y = 70+rand.nextInt(115);
                }
            }
        }
    }
    public int crossUp(int score, int zupc){
        if(score == cu){
            cdr = 1 + rand.nextInt(6);
            cu += cdr;
            if(zupc != -1){
                crossup[zupc].y = 1;
                rcu = -1;
                return zupc;
            }
            else{
                rcu = rand.nextInt(5);
                crossup[rcu].y = 1;
                return rcu;
            }
        }
        else if (zupc != -1 && crossup[zupc].y == 1) return zupc;
        else if (rcu != -1 && crossup[rcu].y == 1) return rcu;
        else return -1;
    }
    public int crossDown(int score, int zdownc){
        if(score == cd ){
            cd += cdr;
            if(zdownc != -1){
                crossdown[zdownc].y = 1;
                rcd = -1;
                return zdownc;
            }
            else {
                rcd = rand.nextInt(4);
                crossdown[rcd].y = 1;
                return rcd;
            }
        }
        else if(zdownc != -1 && crossdown[zdownc].y == 1) return zdownc;
        else if(rcd != -1 && crossdown[rcd].y == 1) return rcd;
        else return -1;
    }
    public Texture getTile(){
        return tile;
    }
    public Vector3[] getPositionUp(){
        return positionup;
    }
    public Vector3[] getPositionDown(){
        return positiondown;
    }
    public void setCollisionUp(int i, int value){
        positionup[i].z = value;
    }
    public void setCollisionDown(int i, int value){
        positiondown[i].z = value;
    }
    public void setPointUp(int i){
        pointup[i].x = 1;
    }
    public Vector2[] checkPointUp(){
        return pointup;
    }
    public void setPointDown(int i){
        pointdown[i].x = 1;
    }
    public Vector2[] checkPointDown(){
        return pointdown;
    }
    public void setSpeed(int score){
        if(score % 20 == 0){
            if(c  == 1){
                speed++;
                c = 0;
            }
        }
        else c = 1;
    }
    public int getSpeed(){
        return speed;
    }
}

