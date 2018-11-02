public class CircleFigure {
	double radius;
	
	public CircleFigure(double _rad){
		try {
			if (_rad < 0) throw new NegativeValueException();
			radius = _rad;
		} catch(NegativeValueException er) {
			System.out.println(er.toString());
		}
	}
	
	public double getSquare() {
		return (Math.PI * radius * radius);
	}
	
	public double getLength() {
		return (2 * Math.PI * radius);
	}
}
