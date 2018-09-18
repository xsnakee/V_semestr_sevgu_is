import java.util.Scanner;

public class calcCircleProp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("¬ведены аргументы командной строки: ");
		for (String str : args) {
			System.out.println(str);
		}
		
		StreamChanger streamChanger = new StreamChanger(args);
		Scanner dataScanner = new Scanner(System.in);
		
		if (!streamChanger.finIsOpen()) {
			streamChanger.getStdOutputStream().println("Enter circle radius: ");
		}
		double radius = 0.0;
		
		try {
			if (!dataScanner.hasNextDouble()) {
				throw new typeMissmatchException();
			}
			radius = dataScanner.nextDouble();		
		} catch (typeMissmatchException exception) {
			streamChanger.getStdOutputStream().println(exception);
		}
		
		
		CircleFigure circle = new CircleFigure(radius);		
		CirclePropLogger logger = new CirclePropLogger(circle);		
		
		logger.saveProperties();
	}

}

