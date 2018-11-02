import java.io.*;

public class FileStreamOpener{
	private BufferedReader _fin = null;
	private PrintWriter _fout = null;
	private String InputFilePath = null;
	private String OutputFilePath = null;
	public FileStreamOpener(String args[]){
		try {		
		for (int i = 0; (i < args.length - 1); ++i) {
					switch (args[i]) {
					case "-i":{
						InputFilePath = args[i + 1];
						_fin = new BufferedReader (new FileReader(InputFilePath));
						break;
					}
					case "-o":{
						OutputFilePath = args[i + 1];
						_fout = new PrintWriter(new FileOutputStream(OutputFilePath));
						break;
					}
					}
			}
		}
		catch (FileNotFoundException exOb) {
			System.out.println(exOb.getMessage());
			System.exit(1);
		}
		
	}
	
	public void ReopenInputStream() {
		try {
			if (_fin != null) {
			_fin.close();
			}
			_fin = new BufferedReader (new FileReader(InputFilePath));
		}
		catch (IOException exOb) {
			System.out.println(exOb.getMessage());
			System.exit(1);
		}
	}
	
	public void ReopenOutputStream() {
		try {
			if (_fout != null) {
				_fout.close();
			}
			_fout = new PrintWriter(new FileOutputStream(OutputFilePath));
		}
		catch (IOException exOb) {
			System.out.println(exOb.getMessage());
			System.exit(1);
		}
	}
	
	public boolean finIsOpen() {
		return (_fin == null ? false : true);
	}
	
	public boolean foutIsOpen() {
		return (_fout == null ? false : true);
	}
	
	public BufferedReader getInputStream() {
		return _fin;
	}
	
	public PrintWriter getOutputStream() {
		return _fout;
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
		super.finalize();
	}
	
}