import java.io.*;

public class IntBuffer extends IntCBuffer
		implements IBufferComputable, IBufferPrintable, IBufferSortable, IBufferStorable {

	public IntBuffer(int count) {
		super(count);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void SaveOneLine(String filename) {
		// TODO Auto-generated method stub

	}

	@Override
	public void SaveSeparateLines(String filename) {
		// TODO Auto-generated method stub
		PrintWriter outFile = null;
		try {
			outFile = new PrintWriter(filename);
			for (int i = 0; i < array.length; ++i) {
				outFile.println(array[i]);
			}
		} catch(FileNotFoundException ex) {
			System.out.println(ex);
			System.exit(0);
		}
		finally{
			if (outFile != null) {
				outFile.close();
			}
		}

	}

	@Override
	public void Sort() {
		QuickSort(array, 0, array.length-1);
	}

	public static void QuickSort(int[] _array, int start, int end) {
		
		if (start >= end){
			return;
		}
		
			int i = start;
			int j = end;
			
			int midElIndex = (start + end) / 2;

			
			while (i < j) {
				
				while (_array[i] <= _array[midElIndex] && i < midElIndex){
					++i;
				}
				
				while (_array[j] >= _array[midElIndex] && midElIndex < j) {
					--j;
				}
				
				if (i <j) {
					int temp = _array[i];
					_array[i] = _array[j];
					_array[j] = temp;
					
					if (i == midElIndex)
						midElIndex = j;
	                else if (j == midElIndex)
	                	midElIndex = i;
				}
			}	
			QuickSort(_array, start, midElIndex);
			QuickSort(_array, midElIndex + 1, end);			
		return;		
	}
	
	@Override
	public void PrintInfo() {
		// TODO Auto-generated method stub
		System.out.println("Buffer ID: " + this.bufID);
		System.out.println("Buffer size: " + this.bufSize);
		System.out.println("Buffer count: " + this.bufCount);
	}

	@Override
	public void Print() {
		// TODO Auto-generated method stub
		for(int i = 0; i < array.length; ++i) {
			if (i % 10 == 0) {
				System.out.println();
			}
			System.out.println(array[i] + " ");
		}

	}

	@Override
	public void PrintFirstN(int n) {
		// TODO Auto-generated method stub
		for(int i = 0; (i < array.length) && (i < n); ++i) {
			if (i % 10 == 0) {
				System.out.println();
			}
			System.out.print(array[i] + " ");
		}
	}

	@Override
	public void PrintLastN(int n) {
		// TODO Auto-generated method stub
		for(int i = array.length - 1; (i >= 0)  && (array.length - i - 1 < n); --i) {
			if (i % 10 == 0) {
				System.out.println();
			}
			System.out.print(array[i] + " ");
		}
	}

	@Override
	public int Max() {
		// TODO Auto-generated method stub
		int indexOfMax = 0;
		for (int i = 0; i < array.length; ++i) {
			if (array[i] >= array[indexOfMax]) {
				indexOfMax = i;
			}
		}
		return array[indexOfMax];
	}

	@Override
	public int Min() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Sum() {
		// TODO Auto-generated method stub
		return 0;
	}


}
