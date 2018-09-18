public class CirclePropLogger implements PropLogger{
	private CircleFigure _ob;
	public CirclePropLogger(CircleFigure ob) {
		_ob = ob;
	}
	
	public void saveProperties() {		
		System.out.println(_ob.getLength());
		System.out.println(_ob.getSquare());
	}
}
