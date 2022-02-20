package application;
	


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Asteroids extends Application {
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
			
			Sprite background = new Sprite("/Asteroids/src/application/Images/spaceview.png");
			background.position.set(400, 300);
			background.render(context);
						
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
