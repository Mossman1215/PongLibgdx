package com.fruitsofdoom.pong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class TwoPlayerScreen implements Screen, InputProcessor{
	ShapeRenderer shapeRenderer;
	Camera camera;
	Game mossPong;
	TouchPos[] points = new TouchPos[2];
	Rectangle[] player = new Rectangle[2];
	Vector3 temp = new Vector3(0,0,0);
	public TwoPlayerScreen(Game game) {
		shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera(480,320);
		mossPong = game;
		Rectangle p1 = new Rectangle(200, -60, 20, 60); //right player
		Rectangle p2 = new Rectangle(-220, -60, 20, 60); //left player
		player[0]=p1;
		player[1]=p2;
		Gdx.input.setInputProcessor(this);
		for(int i=0;i<points.length;i++){
			points[i]= new TouchPos(0, 0, false);
		}
	}
/*
 * make sure that -240 to 0 is left player 0 to 240 is right player 
 * */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		for(int i=0;i<points.length;i++){
			if(points[i].touched){
				temp.x=points[i].x;
				temp.y=points[i].y;
				temp = camera.unproject(temp);
				Gdx.app.log("ERRORS","something");
			}
		}
		//left player
		if(temp.x<0){
			player[1].y = temp.y;
		}
		//right player
		if(temp.x>0){
			player[0].y = temp.y;
		}
		camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);
		 shapeRenderer.begin(ShapeType.Filled);
		 shapeRenderer.setColor(1, 1, 1, 1);
		 shapeRenderer.rect(player[0].x, player[0].y, player[0].width, player[0].height);
		 shapeRenderer.rect(player[1].x, player[1].y, player[1].width, player[1].height);
		 shapeRenderer.end();
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
		this.shapeRenderer.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(pointer<2){
			points[pointer].x = screenX;
			points[pointer].y = screenY;
			points[pointer].touched = true;
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(pointer<2){
			points[pointer].x = screenX;
			points[pointer].y = screenY;
			points[pointer].touched = false;
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
