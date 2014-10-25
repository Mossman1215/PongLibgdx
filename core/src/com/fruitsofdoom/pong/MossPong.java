package com.fruitsofdoom.pong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class MossPong extends Game{

	@Override
	public void create() {
		this.setScreen(new MainMenu(this));
	}
	public void render(){
		super.render();
	}
	public void dispose() {
        this.getScreen().dispose();
    }
}
