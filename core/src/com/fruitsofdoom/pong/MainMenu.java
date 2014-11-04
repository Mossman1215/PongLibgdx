package com.fruitsofdoom.pong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenu implements Screen {
	final Game mossPong;
	OrthographicCamera camera;
	public MainMenu(final Game mossPong){
		this.mossPong = mossPong;
		camera = new OrthographicCamera(480, 320);
		
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		mossPong.setScreen(new TwoPlayerScreen(mossPong));
		this.dispose();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		this.dispose();
	}

}
