package projectDB.model;


public class Audio extends Article {
	
	/*
	 * Instance variables
	 */
	private String artist;
	private double length;
	private String audio;
	
	/*
	 * Constructor 
	 */
	protected Audio() {
	}
	
	//2nd Constructor
	public Audio(String aTitle, String aArtist, String aProducer, String aGenre, String aLanguage, 
			int aYear, double aLength, double aSize, String aFormat, String aImage, String aAudio) {
		
		//inherited from the Article class
		super(aTitle, aProducer, aGenre, aLanguage, aYear, aSize, aFormat, aImage);
		
		//subclass own variables
		this.setArtist(aArtist);
		this.setLength(aLength);
	}
	
	/*
	 * Methods
	 */
	//variable: artist
	public void setArtist(String aArtist) {
		this.artist = aArtist;
	}
	public String getArtist() {
		return artist;
	}	
	
	//variable: length
	public void setLength(double aLength) {
		this.length = aLength;
	}
	public double getLength() {
		return length;
	}
	
	//variable: media
	public void setMedia(String aAudio) {
		this.audio = aAudio;
	}
	public String getMedia() {
		return audio;
	}
	
	public void print() {
		super.print();
		System.out.println("Audio artist is: " + getArtist());
		System.out.println("Audio length is: " + getLength());
		System.out.println("Audio's audio is: " + getMedia());
	}
	
}

