import java.util.Random;
public class IntCBuffer extends CBuffer {

	public int[] array;
	
	public IntCBuffer(int count) {
		super(count);
		array = new int[count];
		Generate();
	}

	@Override
	protected void Generate() {
		Random randomGenerator = new Random();
		try {
			for(int i = 0; i < bufSize; ++i) {
				array[i] = randomGenerator.nextInt(100);
			}
		} catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}

}
