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
	public double elapsedTime; //secs
	
	
	
	public Vector getPosition() {
		return position;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
	
	public Sprite() {
		this.position = new Vector();
		this.velocity = new Vector();
		this.rotation = 0;
		this.boundary = new Rectangle();
		elapsedTime = 0;
	}
	
	public Sprite(String imageFileName) {
		this();
		setImage(imageFileName);
	}
	
	public Sprite(String imageFileName, int width, int height) {
		this();
		setImage(imageFileName, width, height);
	}
	
	public void setImage(String imageFilename) {

		this.image = new Image("file:" + imageFilename);

		this.boundary.setSize(this.image.getWidth(), this.image.getHeight());
	}
	public void setImage(String imageFilename, int width, int height) {

		this.image = new Image("file:" + imageFilename, width, height, false, false);

		this.boundary.setSize(this.image.getWidth(), this.image.getHeight());
	}
	
	public Rectangle getBoundary() {
		this.boundary.setPosition(this.position.getX(), this.position.getY());
		return this.boundary;
	}
	
	public boolean overlaps(Sprite other) {
		return this.getBoundary().overlaps(other.getBoundary());
	}
	public void wrap(double screenWidth, double screenHeight) {
		if (this.position.getX() + this.image.getWidth() < 0) {
			this.position.setX(screenWidth);
		}
		if (this.position.getX() > screenWidth) {
			this.position.setX(-this.image.getWidth());
		}
		if (this.position.getY() + this.image.getHeight() < 0) {
			this.position.setY(screenHeight);
		}
		if (this.position.getY() > screenHeight) {
			this.position.setY(-this.image.getHeight());
		}
	}
	public void update(double deltaTime) {
		// increase elapsed time for this sprite
		this.elapsedTime += deltaTime;
		//update position according to velocity
		this.position.add(this.velocity.getX() * deltaTime, this.velocity.getY() * deltaTime);
		// wrap around the screen
		this.wrap(800, 600);
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