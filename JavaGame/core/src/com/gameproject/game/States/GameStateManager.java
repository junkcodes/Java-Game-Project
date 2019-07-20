package com.gameproject.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.IOException;
import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }
    public void push(State state){
        states.push(state);
    }
    public void pop(){
        states.pop();
    }
    public void set(State state){
        states.pop();
        states.push(state);
    }
    public void update(float dt) throws IOException {
        states.peek().update(dt);
    }
    public void render(SpriteBatch sb) throws IOException, InterruptedException {
        states.peek().render(sb);
    }
}
