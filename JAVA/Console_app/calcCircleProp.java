import java.util.Scanner;

public class calcCircleProp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		StreamChanger streamChanger = new StreamChanger(args);
		Scanner dataScanner = new Scanner(System.in);
		
		if (!streamChanger.finIsOpen()) {
			streamChanger.getStdOutputStream().println("Enter circle radius: ");
		}
		double radius = 0.0;
		
		try {
			if (!dataScanner.hasNextDouble()) {
				throw new TypeMissmatchException();
			}
			radius = dataScanner.nextDouble();		
		} catch (TypeMissmatchException exception) {
			streamChanger.getStdOutputStream().println(exception);
		}
		
		
		CircleFigure circle = new CircleFigure(radius);		
		CirclePropLogger logger = new CirclePropLogger(circle);		
		
		logger.saveProperties();
	}

}

