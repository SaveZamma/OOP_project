package application;

import javafx.scene.canvas.GraphicsContext;

public class Spaceship extends Thread{
	
	private Sprite spaceship;
	
	public Spaceship() {
		this.spaceship = new Sprite("src/application/Images/spaceship.png", 100, 100);
		this.spaceship.position.set(100, 300);
	}
	
	public double getRotation() {
		return this.spaceship.rotation;
	}
	
	public Vector getVelocity() {
		return this.spaceship.velocity;
	}
	
	public Vector getPosition() {
		return this.spaceship.getPosition();
	}
	
	public void update(double deltaTime) {
		this.spaceship.update(deltaTime);
	}
	
	public void render(GraphicsContext context) {
		this.spaceship.render(context);
	}
	
	private void runInCircle() {
		this.spaceship.rotation -= 3.0;
		this.spaceship.velocity.setLength(150);
		this.spaceship.velocity.setAngle(spaceship.rotation);
	}
	
	public void run(){
	    while (true) {
	    	try {
				sleep(50);
				this.runInCircle();
			} catch (Exception e) {
				
			}
	    } 
	}
}
