package com.fruitsofdoom.pong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MainMenu implements Screen {
	final Game mossPong;
	OrthographicCamera camera;
	ShapeRenderer shapeRenderer;
	SpriteBatch batch = new SpriteBatch();
	Rectangle menu1 = new Rectangle(x, y, width, height);
	Rectangle menu2 = new Rectangle(x,y,width,height);
	BitmapFont font = new BitmapFont();
	Vector2 touchpt = new Vector2();
	public MainMenu(final Game mossPong){
		this.mossPong = mossPong;
		camera = new OrthographicCamera(480, 320);
		shapeRenderer = new ShapeRenderer(); 
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(1, 1, 1, 1);
		shapeRenderer.rect(menu1.x,menu1.y,menu1.width,menu1.height);
		shapeRenderer.rect(menu2.x, menu2.y, menu2.width, menu2.height);
		shapeRenderer.end();
		batch.begin();
		font.draw(batch, "1 Player", menu1.x+10, menu1.y-10);
		font.draw(batch, "2 Player", menu2.x+10, menu2.y-10);
		batch.end();
		if(Gdx.input.isTouched()){
			int x = Gdx.input.getX();
			int y = Gdx.input.getY();
			touchpt.x = x;
			touchpt.y = y;
			if(menu1.contains(touchpt)){
				load1P();
			}
			if(menu2.contains(touchpt)){
				load2P();
			}
		}
	}
	public void load1P(){
		this.dispose();
	}
	public void load2P(){
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
	}

}
