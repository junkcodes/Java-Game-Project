package com.gameproject.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.io.IOException;

public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    protected State(GameStateManager gsm){
        this.gsm=gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }
    public abstract void handleInput() throws IOException;
    public abstract void update(float dt) throws IOException;
    public abstract void render(SpriteBatch sb) throws IOException, InterruptedException;
    public abstract void dispose();
}
