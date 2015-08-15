package projectDB.model;


public class Video extends Article {
	
	/*
	 * Instance variables
	 */
	private String actor;
	private String director;
	private String audience;
	private String video;
	private double length;
	
	/*
	 * Constructor
	 */
	protected Video() {
	}
	
	//2nd Constructor
	public Video(String aTitle, String aActor, String aDirector, String aProducer,
			String aGenre, String aLanguage, String aAudience, int aYear, double aSize,
			double aLength, String aFormat, String aImage, String aVideo) {
		
		//inherited from the Article class
		super(aTitle, aProducer, aGenre, aLanguage, aYear, aSize, aFormat, aImage);
		
		//subclass own variables
		this.setActor(aActor);
		this.setDirector(aDirector);
		this.setAudience(aAudience);
		this.setLength(aLength);
	}
	
	/*
	 * Methods
	 */
	//variable: actor
	public void setActor(String aActor) {
		this.actor = aActor;
	}
	public String getActor() {
		return actor;
	}
	
	//variable: director
	public void setDirector(String aDirector) {
		this.director = aDirector;
	}
	public String getDirector() {
		return director;
	}
	
	//variable: audience
	public void setAudience(String aAudience) {
		this.audience = aAudience;
	}
	public String getAudience() {
		return audience;
	}
	
	//variable: length
	public void setLength(double aLength) {
		this.length = aLength;
	}
	public double getLength() {
		return length;
	}
	
	//variable: media
	public void setMedia(String aVideo) {
		this.video = aVideo;
	}
	public String getMedia() {
		return video;
	}
	
	public void print() {
		super.print();
		System.out.println("Video actor is: " + getActor());
		System.out.println("Video director is: " + getDirector());
		System.out.println("Video audience is: " + getAudience());
		System.out.println("Video length is: " + getLength());
	}
	
}
