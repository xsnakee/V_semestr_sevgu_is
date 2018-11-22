import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

@SuppressWarnings("serial")
public class AutoCollection extends LinkedList<Auto>{
	
	public AutoCollection() {
		super();
	}
	
	public AutoCollection(LinkedList<Auto> data) {
		super(data);
	}

	public Iterator<Auto> find(String markOfAuto) {
		Iterator<Auto> iter = this.iterator();
		
		while(iter.hasNext()) {
			if (iter.next().getMark().equals(markOfAuto)) {
				return iter;
			}
		}
		
		return null;
	}
	
	
	public void LoadFromFile(File file) {
		File fin = null;
		Scanner scanner = null;
		try {
			fin = file;
			scanner = new Scanner(fin);

			scanner.useLocale(Locale.ENGLISH);
				
			while(scanner.hasNextLine()) {
				Auto tempAutoData = Auto.ReadFromScanner(scanner);
				this.add(tempAutoData);			
			}
		} catch(FileNotFoundException exception) {
			System.out.println("exception");
		}	catch(Exception exception) {
			System.out.println("exception");			
		}
		finally {
			if (scanner != null) {
				scanner.close();
			}
		}
		
	}

	public void SaveToFile(File file) {
		PrintWriter fout = null;
		try {
			fout = new PrintWriter(file);
			
			Iterator<Auto> autoIter = this.iterator();
			while(autoIter.hasNext()) {
				Auto auto = autoIter.next();
				fout.println(auto.getMark());
				fout.println(auto.getProductionYear());
				fout.println(auto.getEngineVolume());
				fout.println(auto.getMaxSpeed());
			}
			
		} catch(FileNotFoundException exception) {
			System.out.println("exception");
		}	catch(Exception exception) {
			System.out.println("exception");			
		}
		finally {
			if (fout != null) {
				fout.close();
			}
		}
	}
}
