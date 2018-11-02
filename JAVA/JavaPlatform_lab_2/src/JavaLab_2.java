import java.util.Scanner;
import java.util.InputMismatchException;

public class JavaLab_2 {

	public static void main(String[] args) {
		
			IntBuffer[] data = new IntBuffer[5];
			
			for (int i = 0; i < data.length; ++i) {
				data[i] = new IntBuffer(60);
			}
			
			for (int i = 0; i < data.length; ++i) {
				System.out.println("Buffer data" + data[i].GetBudID() + " Info: ");
				data[i].PrintInfo();
			}
			
			for (int i = 0; i < data.length; ++i) {
				System.out.print("\nBuffer data" + data[i].GetBudID()  + " First 10 elements: ");
				data[i].PrintFirstN(10);
				System.out.println();
			}
			
			for (int i = 0; i < data.length; ++i) {
				System.out.println("Buffer data" + data[i].GetBudID()  + " max element: " + data[i].Max());
			}
			
			for (int i = 0; i < data.length; ++i) {
				data[i].Sort();
			}
			
			for (int i = 0; i < data.length; ++i) {
				System.out.print("\nBuffer data" + data[i].GetBudID()  + " First 10 elements after sort: ");
				data[i].PrintFirstN(10);
				System.out.println();
			}
			
			for (int i = 0; i < data.length; ++i) {
				data[i].SaveSeparateLines("logFile" + data[i].GetBudID()  + ".txt");;
			}
			return;
	}

}
