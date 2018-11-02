import java.io.IOException;

public class Lab2Java {
	public static void main(String[] args) throws IOException {
		MyClass arrays[] = new MyClass[3];
		
		for (int i=0; i<3; i++) {
			arrays[i] = new MyClass(50);
			arrays[i].PrintInfo();
			arrays[i].PrintFirstN(10);
			System.out.println("Buffer min elem: "+arrays[i].Min());
			arrays[i].Sort();
			arrays[i].SaveOneLine("result"+(i + 1)+".txt");
			arrays[i].PrintLastN(10);
			arrays[i].o("output"+(i + 1)+".txt");
		}
	}
}
