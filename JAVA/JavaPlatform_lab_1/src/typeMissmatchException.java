import javax.xml.ws.handler.MessageContext;

public class TypeMissmatchException extends Exception {
	public TypeMissmatchException() {
		super("Type missmatch exception. Variable waiting another type.");
	}

	public TypeMissmatchException(String str) {
		super(str);
	}
}
