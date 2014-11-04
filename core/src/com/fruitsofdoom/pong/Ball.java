package com.fruitsofdoom.pong;
import com.badlogic.gdx.math.Vector2;
import com.fruitsofdoom.pong.GameObject;
public class Ball implements GameObject{
	public Vector2 position;
	double direction;
	public Ball() {
		position = new Vector2(0, 0);
		direction = Math.random()-.5;
	}
	@Override
	public void update(float delta) {
		if(direction >0){
			position.x+=10*delta;
			position.y-=10*delta;
		}else{
			position.x-=10*delta;
			position.y-=10*delta;
		}
		if(position.x>220){
			position.x = 220;
		}
		if(position.x<-240){
			position.x = 240;
		}
		if(position.y>140){
			position.y=140;
		}
		if(position.y<-160){
			position.y=-160;
		}
	}
}
