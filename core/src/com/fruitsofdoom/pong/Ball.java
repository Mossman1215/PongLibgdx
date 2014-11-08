package com.fruitsofdoom.pong;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.fruitsofdoom.pong.GameObject;
public class Ball implements GameObject{
	public Vector2 position;
	double directionX;
	double directionY;
	public Rectangle bounding;
	double speedScale = .4;
	public Ball() {
		position = new Vector2(0, 0);
		bounding = new Rectangle(position.x, position.y, 20, 20);
		directionX = Math.random()-.5;
		directionY = Math.random()-.5;
	}
	@Override
	public void update(float delta) {
		if(directionX >0){
			position.x+=200*delta*speedScale;
		}else{
			position.x-=200*delta*speedScale;
		}
		if(directionY >0){
			position.y+=200*delta*speedScale;
		}else{
			position.y-=200*delta*speedScale;
		}
		if(position.x>220){
			position.x = 220;
		}
		if(position.x<-240){
			position.x = -240;
		}
		if(position.y>140){
			position.y=140;
			bounceY();
		}
		if(position.y<-160){
			position.y=-160;
			bounceY();
		}
		bounding.x= position.x;
		bounding.y= position.y;
	}
	public void bounceX(){
		speedScale = 1;
		if(directionX<0){
			directionX = 1;
		}else{
			directionX = -1;
		}
	}
	public void bounceY(){
		if(directionY<0){
			directionY = 1;
		}else{
			directionY = -1;
		}
	}
}
