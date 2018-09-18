import javax.xml.ws.handler.MessageContext;

public class typeMissmatchException extends Exception {
	public typeMissmatchException() {
		super("Type missmatch exception. Variable waiting another type.");
	}

	public typeMissmatchException(String str) {
		super(str);
	}
}
