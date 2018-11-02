public class CirclePropLogger implements PropLogger{
	private CircleFigure _ob;
	public CirclePropLogger(CircleFigure ob) {
		_ob = ob;
	}
	
	public void saveProperties() {	
		System.out.println("Circle radius: " + _ob.radius);;
		System.out.println("Circle length: " + _ob.getLength());
		System.out.println("Circle square: " + _ob.getSquare());
	}
}
