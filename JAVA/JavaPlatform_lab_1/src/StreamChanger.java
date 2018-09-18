import java.io.*;

public class StreamChanger{
	static private PrintStream stdOutStream = System.out;
	static private InputStream stdInStream = System.in;
	private FileInputStream _fin = null;
	private FileOutputStream _fout = null;
	
	public StreamChanger(String args[]){
		try {		
		for (int i = 0; ((i < args.length) || (i > 3)); i += 2) {
				if (args.length % 2 == 0) {
					switch (args[i]) {
					case "-i":{
						_fin = new FileInputStream (args[i + 1]);
						if (_fin == null) {
							throw new FileNotFoundException();
						} else {
							System.setIn(_fin);
						}
						break;
					}
					case "-o":{
						_fout = new FileOutputStream(args[i + 1]);
					}
					
					if (_fout == null) {
						throw new FileNotFoundException();
					} else {
						System.setOut(new PrintStream(_fout));
					}
					break;
					}
				} else if (args.length > 0){
					throw new Exception("arguments error");
				} 
			}
		}

		catch (FileNotFoundException exOb) {
			stdOutStream.println(exOb.getMessage());
		}
		catch (Exception exOb){
			stdOutStream.println(exOb.getMessage());
		}
		
	}
	
	public boolean finIsOpen() {
		return (_fin == null ? false : true);
	}
	
	public boolean foutIsOpen() {
		return (_fout == null ? false : true);
	}
	
	public static PrintStream getStdOutputStream() {
		return stdOutStream;
	}
	
	public static InputStream getStdInputStream() {
		return stdInStream;
	}
	
	public static void ResetStreams() {
		System.setIn(stdInStream);
		System.setOut(stdOutStream);
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		if (_fin != null) {
			_fin.close();
		}
		if (_fout != null) {
			_fout.close();	
		}
		
		ResetStreams();
		super.finalize();
	}
	
}