package application;

import java.net.URISyntaxException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
	public Vector position;
	public Vector velocity;
	public double rotation;
	public Rectangle boundary;
	public Image image;
	
	public Sprite() {
		this.position = new Vector();
		this.velocity = new Vector();
		this.rotation = 0;
		this.boundary = new Rectangle();
	}
	
	public Sprite(String imageFileName) {
		this();
		setImage(imageFileName);
	}
	public void setImage(String imageFilename) {

		this.image = new Image("file:" + imageFilename);

		this.boundary.setSize(this.image.getWidth(), this.image.getHeight());
	}
	
	public Rectangle getBoundary() {
		this.boundary.setPosition(this.position.getX(), this.position.getY());
		return this.boundary;
	}
	
	public boolean overlaps(Sprite other) {
		return this.getBoundary().overlaps(other.getBoundary());
	}
	
	public void update(double deltaTime) {
		//update position according to velocity
		this.position.add(this.velocity.getX() * deltaTime, this.velocity.getY() * deltaTime);		
	}
	
	public void render(GraphicsContext context) {
		context.save();
		context.translate(this.position.getX(), this.position.getY());
		context.rotate(this.rotation);
		context.translate(-this.image.getWidth()/2, -this.image.getHeight()/2);
		context.drawImage(this.image, 0, 0);
		
		context.restore();
	}
	
}