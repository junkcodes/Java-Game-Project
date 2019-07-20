package  com.gameproject.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gameproject.game.States.GameStateManager;
import com.gameproject.game.States.MenuState;
import com.gameproject.game.States.PauseState;

import java.io.IOException;

public class MyGdxGame extends ApplicationAdapter {
	public static final int width = 840;
	public static final int height = 520;
	public static final String title = "Game Project";
	private GameStateManager gsm;
	SpriteBatch batch;
	Texture img;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 1, 1, 1);
        try {
            gsm.push(new MenuState(gsm));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		try {
			gsm.update(Gdx.graphics.getDeltaTime());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			gsm.render(batch);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
