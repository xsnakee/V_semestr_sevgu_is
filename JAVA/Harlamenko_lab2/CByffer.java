import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;


public abstract class CByffer {
	protected int bufID;
	protected int bufSize;
	static int BufCount = 0;
	
	static void inc() {
		CByffer.BufCount++;
	}
	
	public CByffer(int count) {
		CByffer.inc();
		this.bufSize = count;
		this.bufID = CByffer.BufCount;
	}
	
	abstract void Generate();
	
	protected int GetBufCount() {
		return CByffer.BufCount;
	}
	
	protected int GetBufID() {
		return this.bufID;
	}

}

class BufferCreate extends CByffer {
	public int arr[];

	@Override
	void Generate() {
		Random random = new Random();
		for (int i=0; i<this.arr.length; i++) {
			this.arr[i]= random.nextInt();
		}
	}
	
	public BufferCreate(int count) {
		super(count);
		this.arr = new int[count];
		Generate();
	}
	
}

interface IBufferPrintable {
	public void PrintInfo();
	public void Print();
	public void PrintFirstN(int n);
	public void PrintLastN(int n);
}


interface IBufferSortable {
	public void Sort();
}

interface IBufferComputable {
	public int Min();
}

interface IBufferStorable {
	public void SaveOneLine(String filename) throws IOException;
}

class MyClass extends BufferCreate implements IBufferPrintable,
IBufferSortable, IBufferComputable, IBufferStorable {

	public MyClass(int count) {
		super(count);
	}

	@Override
	public void SaveOneLine(String filename) throws IOException {
		PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
		for (int i = 0; i < this.bufSize; i++)
			output.print(+this.arr[i] + ' ');
		output.close();

		
	}

	@Override
	public int Min() {
		int min = this.arr[0];
		for (int i=1; i<this.arr.length; i++) {
			if (this.arr[i] < min) {
				min = this.arr[i];
			}
		}
		return min;
	}

	@Override
	public void Sort() {
		boolean isSorted = false;
        int buf;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < this.arr.length-1; i++) {
                if(this.arr[i] < this.arr[i+1]){
                    isSorted = false;
 
                    buf = this.arr[i];
                    this.arr[i] = this.arr[i+1];
                    this.arr[i+1] = buf;
                }
            }
        }
        System.out.println(Arrays.toString(this.arr));
		
	}

	@Override
	public void PrintInfo() {
		System.out.println("bufID = "+this.bufID+" bufCount = "+CByffer.BufCount+" bufSize = "+this.bufSize);
	}

	@Override
	public void Print() {
		for (int i = 0; i < this.bufSize; i++)
			System.out.print(+this.arr[i]+" ");
		System.out.println();

	}

	@Override
	public void PrintFirstN(int n) {
		for (int i = 0; i < n; i++)
			System.out.print(+this.arr[i]+" ");
		System.out.println();
	}

	@Override
	public void PrintLastN(int n) {
		for (int i = this.bufSize - n; i < this.bufSize; i++)
			System.out.print(+this.arr[i]+" ");
		System.out.println();
	}
	
	public void o (String filename) throws IOException {
		PrintWriter output = 
				new PrintWriter(new BufferedWriter(new FileWriter(filename)));
		output.println("#"+this.GetBufID());
		for (int i = 0; i < this.bufSize; i++)
			output.print(+this.arr[i]+" ");
		output.println();
		output.close();
	}

}


