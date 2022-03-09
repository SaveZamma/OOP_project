package application;
	


import java.util.ArrayList;
import java.util.Iterator;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Asteroids extends Application {
	
	
	private ArrayList<Sprite> laserList = new ArrayList<>();
	private ArrayList<Sprite> asteroidList = new ArrayList<>();
	
	/**
	 *
	 */
	@Override
	public void start(Stage mainStage) {
		
		mainStage.setTitle("Asteroids");
		try {
			BorderPane root = new BorderPane();
			Scene mainScene = new Scene(root);
			mainStage.setScene(mainScene);
			
			Canvas canvas = new Canvas(800,600);
			GraphicsContext context = canvas.getGraphicsContext2D();
			root.setCenter(canvas);
			
			// handle continuous inputs (as long as key is pressed)
			ArrayList<String> keyPressedList = new ArrayList<>();
			// handle descrete inputs (ones per key pressed)
			ArrayList<String> keyJustPressedList = new ArrayList<>();
			
			mainScene.setOnKeyPressed((KeyEvent event) -> {
				String keyName = event.getCode().toString();
				if(!keyPressedList.contains(keyName)) {
					keyPressedList.add(keyName);
					keyJustPressedList.add(keyName);
				}
			});
			
			mainScene.setOnKeyReleased((KeyEvent event) -> {
				String keyName = event.getCode().toString();
				if(keyPressedList.contains(keyName)) {
					keyPressedList.remove(keyName);
				}
			});
			
			Sprite background = new Sprite("src/application/Images/spaceview.png");
			background.position.set(400, 300);
			
			/*
			 * Sprite spaceship = new Sprite("src/application/Images/spaceship.png", 100,
			 * 100); spaceship.position.set(100, 300)
			 */;
			 
			 Spaceship spaceship = new Spaceship();
			 spaceship.start();
			
			int asteroidCount = 89;
			for (int n = 0; n < asteroidCount; n++) {
				Sprite asteroid = new Sprite("src/application/Images/asteroid.png", 50, 50);
				double x = 500 * Math.random() + 300;
				double y = 400 * Math.random() + 100;
				asteroid.position.set(x, y);
				double angle = 360 * Math.random();
				asteroid.velocity.setLength(50);
				asteroid.velocity.setAngle(angle);
				asteroidList.add(asteroid);
			}
		
			AnimationTimer gameloop = new AnimationTimer() {				
				public void handle(long nanotime) {
					
					/*
					 * if (keyPressedList.contains("LEFT")) { spaceship.rotation -= 3.0; } if
					 * (keyPressedList.contains("RIGHT")) { spaceship.rotation += 3.0; } if
					 * (keyPressedList.contains("UP")) { spaceship.velocity.setLength(150);
					 * spaceship.velocity.setAngle(spaceship.rotation); } else {
					 * spaceship.velocity.setLength(0); }
					 */
					if (keyJustPressedList.contains("SPACE")) {
						Sprite laser = new Sprite("src/application/Images/bullet.png", 20, 20);
						laser.position.set(spaceship.getPosition().getX(), spaceship.getPosition().getY());
						laser.velocity.setLength(400);
						laser.velocity.setAngle(spaceship.getRotation());
						laser.setRotation(spaceship.getRotation());
						laserList.add(laser);
					}
					
					// after processing user input, clearing keyJustPressedList
					keyJustPressedList.clear();
					
					spaceship.update(1/60.0);
					
					for(Sprite asteroid: asteroidList) {
						asteroid.update(1/60.0);
					}
					
					// update laser, destroy if more than 2 secs passed
					for (int n=0; n < laserList.size(); n++) {
						Sprite laser = laserList.get(n);
						laser.update(1/60.0);
						if (laser.elapsedTime > 2) {
							laserList.remove(laser);
						}
					}
					
					// when laser overlaps an asteroid, remove both
					for (int laserNum = 0; laserNum < laserList.size(); laserNum++) {
						Sprite laser = laserList.get(laserNum);
						for (int asteroidNum = 0; asteroidNum < asteroidList.size(); asteroidNum++) {
							Sprite asteroid = asteroidList.get(asteroidNum);
							
							if (laser.overlaps(asteroid)) {
								laserList.remove(laser);
								asteroidList.remove(asteroid);   
							}
							
						}
					}
					
					background.render(context);
					spaceship.render(context);
					
					for (Sprite laser: laserList) {
						laser.render(context);
					}
					for (Sprite asteroid: asteroidList) {
						asteroid.render(context);
					}
					
				}
			};
			
			gameloop.start();
						
			mainStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			launch(args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}
}
