import java.io.BufferedReader;
import java.util.*;


@SuppressWarnings("serial")
public class TreeMapCollection extends TreeMap<String, Cd>{
		
	public void FillDataFromInputStream(BufferedReader InputStream) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(InputStream);

			while(scanner.hasNext()) {
				Cd CdInformation = Cd.ReadFromScanner(scanner);
				this.put(CdInformation.getAlbumName(),CdInformation);
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
		
		Set<Map.Entry<String,Cd>> tempSet = this.entrySet();
		
		for(Map.Entry<String,Cd> temp : tempSet) {
			System.out.printf("%10s | %20s | %6d | %8.2f\n",
					temp.getValue().getArtist(), temp.getKey(), 
					temp.getValue().getTrackCount(), temp.getValue().getTime());
		}
	}
	
	public boolean Contain(String AlbumName) {		
		return this.containsKey(AlbumName);
	}
	
	public void SearchAndPrint(String AlbumName) {
				if (containsKey(AlbumName)) {
				Cd temp = this.get(AlbumName);

				System.out.printf("%10s | %20s | %6s | %8s\n","Artist","Album", "Tracks", "Time");
				System.out.printf("%10s | %20s | %6d | %8.2f \n", temp.getArtist(), temp.getAlbumName(), temp.getTrackCount(), temp.getTime());
			} else {
				System.out.println("Entity not found");
			}
	}
}