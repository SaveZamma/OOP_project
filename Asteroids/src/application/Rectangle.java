package application;

public class Rectangle {
	//(x, y) represent top-left corner of Rectangle
	private double x;
	private double y;
	private double width;
	private double height;
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public Rectangle() {
		this.setPosition(0, 0);
		this.setSize(1, 1);
	}
	
	public Rectangle(double x, double y, double w, double h) {
		this.setPosition(x, y);
		this.setSize(w, h);
	}
	
	public void setPosition(double x, double y) {
		this.x = x; 
		this.y = y;
	}
	
	public void setSize(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	public boolean overlaps(Rectangle other) {
		// 4 cases wher there is no overlap:
		// this to the left of other
		// this to the right of other
		// this is above other
		// this is under other
		boolean noOverlap = 
				this.x + this.width < other.x ||
				other.x + other.width < this.x ||
				this.y + this.height < other.y ||
				other.y + other.height < this.y;
		return !noOverlap;
	}
}
