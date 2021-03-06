package com.deep.spaceglad;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class Core extends ApplicationAdapter {

	public static final float VIRTUAL_WIDTH = 960;
	public static final float VIRTUAL_HEIGHT = 540;
	Screen screen;

	@Override
	public void create () {
		setScreen(new GameScreen(this));
	}

	@Override
	public void resize(int width, int height) {
		screen.resize(width, height);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		screen.render(Gdx.graphics.getDeltaTime());
	}

	public void setScreen(Screen screen){
		if (this.screen != null){
			this.screen.hide();
			this.screen.dispose();
		}

		this.screen = screen;
		if (this.screen != null){
			this.screen.show();
			this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
	}
	
	@Override
	public void dispose () {

	}

}
