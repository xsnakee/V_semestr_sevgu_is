import java.util.Comparator;

public class MyComp implements Comparator<Cd> {

	@Override
	public int compare(Cd o1, Cd o2) {
		return Float.compare(o1.getTime(), o2.getTime());
	}

}
