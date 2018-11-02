
public abstract class CBuffer {
	protected int bufID = 0;
	protected int bufSize = 0;
	protected static int bufCount = 0;
	
	public CBuffer(int count) {
		bufID = ++bufCount;
		bufSize = count;
	}
	
	protected abstract void Generate();
	
	public int GetBufCount() {
		return bufCount;
	}
	
	public int GetBudID() {
		return bufID;
	}
	
	private void finallize() {
		// TODO Auto-generated method stub
		--bufCount;
	}
}
