import java.util.Scanner;

public class Lab3Java {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileStreamOpener fileStreams= new FileStreamOpener(args);
			TreeSetCollection CDSet = new TreeSetCollection();
			CDSet.FillDataFromInputStream(fileStreams.getInputStream());
			System.out.println("\nTreeSet Data:");
			CDSet.Print();
			
			System.out.println("\nEnter Album Name to search: ");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
		
			String foundingData = scanner.next();
			String result = CDSet.Contain(foundingData) ? "contain" : "not contain";
			System.out.println("CD set " + result + " " + foundingData);		
			
			LinkedListCollection CdLList = new LinkedListCollection();			
			fileStreams.ReopenInputStream();
			CdLList.FillDataFromInputStream(fileStreams.getInputStream());
			System.out.println("\nLinked List Data:");
			CdLList.Print();
			CdLList.sort(new MyComp());
			System.out.println("\nLinked List Data after sort: ");		
			CdLList.Print();
			CdLList.SaveToFile(fileStreams.getOutputStream());
			
			TreeMapCollection CDMap = new TreeMapCollection();
			fileStreams.ReopenInputStream();
			CDMap.FillDataFromInputStream(fileStreams.getInputStream());
			System.out.println("\nTreeMap Data:");
			CDMap.Print();
			System.out.println("\nEnter Album Name to search: ");
			foundingData = scanner.next();
			CDMap.SearchAndPrint(foundingData);
			
		} catch(Exception ex) {
			System.out.println(ex.toString());
		}
		
	}

}
