import java.util.Locale;
import java.util.Scanner;

public class Cd implements Comparable<Cd>{
	static final String DefaultName = "None";

	private String _AlbumName;
	private String _Artist;
	private int _trackCount;
	private float _time;
	
	public Cd() {
		_AlbumName = DefaultName;
		_Artist = DefaultName;
		_trackCount = 0;
		_time = 0.0f;
	}
	
	public Cd(String AlbumName, String Artist, int trackCount, float time) {
		this.setAlbumName(AlbumName);
		this.setArtist(Artist);
		this.setTrackCount(trackCount);
		this.setTime(time);
	}
	
	@Override
	public int compareTo(Cd _Cd) {
		return this.getAlbumName().compareTo(_Cd.getAlbumName());
	}		
	
	public static Cd ReadFromScanner(Scanner scanner) {
			scanner.useLocale(Locale.ENGLISH);
			String AuthorName = null;
			String AlbumName = null;
			int TrackCount = 0;
			float Time = 0.0f;

			if (scanner.hasNext()) {
				AuthorName = scanner.nextLine();
			}
			if (scanner.hasNext()) {
				AlbumName = scanner.nextLine();
			}
			if (scanner.hasNextInt()) {
				TrackCount = scanner.nextInt();
			}
			if (scanner.hasNextFloat()) {
				Time = scanner.nextFloat();
			}
			if (scanner.hasNext()) scanner.nextLine(); //skip '\n'
			return new Cd(AlbumName, AuthorName, TrackCount, Time);
		
	}

	public String getAlbumName() {
		return _AlbumName;
	}
	public void setAlbumName(String _AlbumName) {
		this._AlbumName = _AlbumName;
	}	
	public String getArtist() {
		return _Artist;
	}
	public void setArtist(String _Artist) {
		this._Artist = _Artist;
	}
	public float getTime() {
		return _time;
	}
	public void setTime(float _time) {
		this._time = _time;
	}
	public int getTrackCount() {
		return _trackCount;
	}
	public void setTrackCount(int _trackCount) {
		this._trackCount = _trackCount;
	}
}
