import java.util.Scanner;

public class Auto implements Comparable<Auto>{
	private String Mark;
	private int ProductionYear;
	private double EngineVolume;
	private double MaxSpeed;
	
	final private static int DATA_FIELDS_COUNT = 4;
	
	public Auto(String mark, int productionYear, double engineVolume, double maxSpeed) {
		setMark(mark);
		setProductionYear(productionYear);
		setEngineVolume(engineVolume);
		setMaxSpeed(maxSpeed);		
	}
	
	public Auto(String mark, String productionYear, String engineVolume, String maxSpeed) {
		
			setMark(mark);
			setProductionYear(Integer.parseInt(productionYear));
			setEngineVolume(Double.parseDouble(engineVolume));
			setMaxSpeed(Double.parseDouble(maxSpeed));
			
	}
	
	public static Auto ReadFromScanner(Scanner scanner) {
		String tempMark = null;
		int prodYear = 0;
		double engineVol = 0.0;
		double maxSpeed = 0.0;
		
		if (scanner.hasNextLine()) {
			tempMark = scanner.nextLine();				
		}
		if (scanner.hasNextInt()) {
			prodYear = scanner.nextInt();
		}
		if (scanner.hasNextDouble()) {
			engineVol = scanner.nextDouble();
		}
		if (scanner.hasNextDouble()) {
			maxSpeed = scanner.nextDouble();
		}			
		if (scanner.hasNextLine()) scanner.nextLine();
		
		return new Auto(tempMark, prodYear, engineVol, maxSpeed);
	}
	
	//Sort MaxSpeed Asc LinkedList
	public String getMark() {
		return Mark;
	}
	public void setMark(String mark) {
		Mark = mark;
	}
	
	public int getProductionYear() {
		return ProductionYear;
	}
	public void setProductionYear(int productionYear) {
		ProductionYear = productionYear;
	}
	
	public double getEngineVolume() {
		return EngineVolume;
	}
	public void setEngineVolume(double engineVolume) {
		EngineVolume = engineVolume;
	}
	
	public double getMaxSpeed() {
		return MaxSpeed;
	}
	public void setMaxSpeed(double maxSpeed) {
		MaxSpeed = maxSpeed;
	}

	@Override
	public int compareTo(Auto auto) {
		// TODO Auto-generated method stub
		return Double.compare(this.getMaxSpeed(), auto.getMaxSpeed());
	}
	
	static public int getDataFieldsCount() {
		return DATA_FIELDS_COUNT;
	}
	
	static public String getFieldName(int fieldNum) {
		
		switch (fieldNum) {
			case 0:{
				return "Mark";
			}
			case 1:{
				return "Produced Year";
			}
			case 2:{
				return "Engine Volume";
			}
			case 3:{
				return "Max speed";
			}
		}	
		return  null;
		
	}
	
	public Object getValueAtField(int fieldNum) {
		
		switch (fieldNum) {
			case 0:{
				return this.getMark();
			}
			case 1:{
				return this.getProductionYear();
			}
			case 2:{
				return this.getEngineVolume();
			}
			case 3:{
				return this.getMaxSpeed();
			}
		}
		
		return null;
	}
	
	
	public void setValueAtField(Object value, int filedNum) {
		
		switch (filedNum) {
		case 0:{
			this.setMark((String)value);
			return;
		}
		case 1:{
			this.setProductionYear((int)value);
			return;
		}
		case 2:{
			this.setEngineVolume((double)value);
			return;
		}
		case 3:{
			this.setMaxSpeed((double)value);
			return;
		}
		}
	}
	
}
