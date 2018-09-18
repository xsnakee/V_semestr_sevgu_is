public class CircleFigure {
	double radius;
	
	public CircleFigure(double _rad){
		radius = _rad;
	}
	
	public double getSquare() {
		return (Math.PI * radius * radius);
	}
	
	public double getLength() {
		return (2 * Math.PI * radius);
	}
}
