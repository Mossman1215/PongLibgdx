package com.fruitsofdoom.pong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class TwoPlayerScreen implements Screen, InputProcessor {
	ShapeRenderer shapeRenderer;
	Camera camera;
	Game mossPong;
	TouchPos[] points = new TouchPos[2];
	Rectangle[] player = new Rectangle[2];
	Vector3[] temp = new Vector3[2];
	Ball ball = new Ball();
	BitmapFont font = new BitmapFont();
	SpriteBatch batch = new SpriteBatch(256);
	public int player0Score,player1Score;
	public Rectangle returnbox = new Rectangle(-70, -160, 120, 40);
	public TwoPlayerScreen(Game game) {
		player0Score = 0;
		player1Score = 0;
		shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera(480, 320);
		mossPong = game;
		Rectangle p1 = new Rectangle(200, -60, 20, 60); // left player
		Rectangle p2 = new Rectangle(-220, -60, 20, 60); // right player
		temp[0] = new Vector3(0, 0, 0);
		temp[1] = new Vector3(0, 0, 0);
		player[0] = p1;
		player[1] = p2;
		Gdx.input.setInputProcessor(this);
		for (int i = 0; i < points.length; i++) {
			points[i] = new TouchPos(0, 0, false);
		}
	}

	/*
	 * make sure that -240 to 0 is left player 0 to 240 is right player
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.projection);
		batch.begin();
		font.setScale(3);
		font.draw(batch, "return",-70, -120);
		font.draw(batch, ""+player0Score, -200, 140);
		font.draw(batch, ""+player1Score, 180, 140);
		batch.end();
		for (int i = 0; i < points.length; i++) {
			if (points[i].touched) {
				temp[i].x = points[i].x;
				temp[i].y = points[i].y;
				temp[i] = camera.unproject(temp[i]);
			}
		}
		if(returnbox.contains(temp[0].x,temp[0].y)){
			loadMainMenu(mossPong);
		}
		for(int i=0;i<player.length;i++){
			if(player[i].y>100){
				player[i].y=100;
			}
			if(player[i].y<-160){
				player[i].y=-160;
			}
			if(temp[i].x<0 && player[i].x<0){
				if(temp[i].y>player[i].y){
					player[i].y+=200*delta;
				}
				if(temp[i].y<player[i].y){
					player[i].y-=200*delta;
				}
			}
			if(temp[i].x>0 && player[i].x>0){
				if(temp[i].y>player[i].y){
					player[i].y+=200*delta;
				}
				if(temp[i].y<player[i].y){
					player[i].y-=200*delta;
				}
			}
		}
		ball.update(delta);
		if(ball.bounding.overlaps(player[0])){
			ball.bounceX();
		}
		if(ball.bounding.overlaps(player[1])){
			ball.bounceX();
		}
		if(ball.position.x==220){
			score(0);//score player 1
			newRound();
		}
		if(ball.position.x==-240){
			score(1);//score player 2
			newRound();
		}
		camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(1, 1, 1, 1);
		shapeRenderer.rect(returnbox.x,returnbox.y,returnbox.width,returnbox.height);
		shapeRenderer.end();
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(1, 1, 1, 1);
		shapeRenderer.rect(player[0].x, player[0].y, player[0].width,
				player[0].height);
		shapeRenderer.rect(player[1].x, player[1].y, player[1].width,
				player[1].height);
		shapeRenderer.rect(ball.position.x,ball.position.y,ball.bounding.width,ball.bounding.height);
		shapeRenderer.end();
	}
	public void loadMainMenu(Game game){
		game.setScreen(new MainMenu(game));
	}
	public void score(int player){
		if(player == 0){
			player0Score++;
		}else{
			player1Score++;
		}
	}
	public void newRound(){
		ball = new Ball();
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
		font.dispose();
		batch.dispose();
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
		if (pointer < 2) {
			points[pointer].x = screenX;
			points[pointer].y = screenY;
			points[pointer].touched = true;
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (pointer < 2) {
			points[pointer].x = screenX;
			points[pointer].y = screenY;
			points[pointer].touched = false;
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (pointer < 2) {
			points[pointer].x = screenX;
			points[pointer].y = screenY;
			points[pointer].touched = true;
		}
		return true;
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
