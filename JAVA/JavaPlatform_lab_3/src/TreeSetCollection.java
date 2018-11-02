import java.io.BufferedReader;
import java.util.*;

@SuppressWarnings("serial")
public class TreeSetCollection extends TreeSet<Cd>{
	
	public void FillDataFromInputStream(BufferedReader InputStream) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(InputStream);
			while(scanner.hasNext()) {
				Cd CdInformation = Cd.ReadFromScanner(scanner);
				this.add(CdInformation);
			}
		} catch(InputMismatchException ex){
			System.out.println(ex);
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}
	
	public void Print() {
		
		System.out.printf("%10s | %20s | %6s | %8s\n","Artist","Album", "Tracks", "Time");
		
		for(Cd temp : this) {
			System.out.printf("%10s | %20s | %6d | %8.2f\n", temp.getArtist(), temp.getAlbumName(), temp.getTrackCount(), temp.getTime());
		}
	}

	public boolean Contain(String AlbumName) {		
		Iterator<Cd> CDiter = this.iterator();
		
		while(CDiter.hasNext()) {
			Cd temp = CDiter.next();
			if (temp.getAlbumName().equals(AlbumName)) {
				return true;
			}
		}
		return false;
	}

}
