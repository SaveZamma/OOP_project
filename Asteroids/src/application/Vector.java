package application;

public class Vector {
	
	private double x;
	private double y;
	
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
		
	public Vector() {
		this.set(0, 0);
	}
	
	public Vector(double x, double y) {
		this.set(x, y);
	}
	
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void add(double dx, double dy) {
		this.x += dx;
		this.y += dy;
	}
	
	public void multiply(double m) {
		this.x *= m;
		this.y *= m;
	}
	
	public double getLength() {
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}
	
	public void setLength(double L) {
		
		double currentLength = this.getLength();
		// guard against division by 0 later
		if (currentLength == 0) {
			return;
		} else {
			// scale vector to have length 1
			this.multiply(1/currentLength);
			// scale the vector to have length L
			this.multiply(L);
		}
	}	

	public double getAngle() {
		return Math.toDegrees(Math.atan2(this.x, this.y));
	}
	
	public void setAngle(double angleDegrees) {
		double L = this.getLength();
		double angleRadians = Math.toRadians(angleDegrees);
		this.x = L * Math.cos(angleRadians);
		this.y = L *  Math.sin(angleRadians);
	}
}
