import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

@SuppressWarnings("serial")
public class LinkedListCollection extends LinkedList<Cd>{
		
		public void FillDataFromInputStream(BufferedReader InputStream) {
			Scanner scanner = null;
			try {
				scanner = new Scanner(InputStream);
				while(scanner.hasNext()) {
					Cd CdInformation = Cd.ReadFromScanner(scanner);
					this.addLast(CdInformation);
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
		
		public void SaveToFile(PrintWriter out_stream) {
			for(Cd temp : this) {
				out_stream.println(temp.getArtist());
				out_stream.println(temp.getAlbumName());
				out_stream.println(temp.getTrackCount());
				out_stream.println(temp.getTime());
			}
			out_stream.close();
	}
}
