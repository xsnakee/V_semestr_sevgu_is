
public class NegativeValueException extends Exception {
	public NegativeValueException() {
		super("Value is negative.");
	}

	public NegativeValueException(String str) {
		super(str);
	}
}
