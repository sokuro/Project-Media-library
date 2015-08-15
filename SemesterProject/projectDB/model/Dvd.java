package projectDB.model;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Dvd extends Video {
	
	/*
	 * StringProperty-variables for the GUI 
	 */
	private final StringProperty title;
	private final StringProperty actor;
	private final StringProperty director;
	private final StringProperty producer;
	private final StringProperty genre;
	private final StringProperty language;
	private final StringProperty audience;
	private final IntegerProperty year;
	private final DoubleProperty size;
	private final DoubleProperty length;
	private final StringProperty format;
	private final StringProperty image;
	private final StringProperty video;
	
	/*
	 * Constructor
	 */
	public Dvd() {
		this(null, null, null, null, null, null, null, 0, 0, 0, null, null, null);
	}
	
	//2nd Constructor
	public Dvd(String aTitle, String aActor, String aDirector, String aProducer,
			String aGenre, String aLanguage, String aAudience, int aYear, double aSize,
			double aLength, String aFormat, String aImage, String aVideo) {
		
		//inherited from the Video class
		super(aTitle, aActor, aDirector, aProducer, aGenre, aLanguage, aAudience, 
		aYear, aSize, aLength, aFormat, aImage, aVideo);
		
		this.title = new SimpleStringProperty(super.getTitle());
		this.actor = new SimpleStringProperty(super.getActor());
		this.director = new SimpleStringProperty(super.getDirector());
		this.producer = new SimpleStringProperty(super.getProducer());
		this.genre = new SimpleStringProperty(super.getGenre());
		this.language = new SimpleStringProperty(super.getLanguage());
		this.audience = new SimpleStringProperty(super.getAudience());
		this.year = new SimpleIntegerProperty(super.getYear());
		this.size = new SimpleDoubleProperty(super.getSize());
		this.length = new SimpleDoubleProperty(super.getLength());
		this.format = new SimpleStringProperty(super.getFormat());
		this.image = new SimpleStringProperty(super.getImage());
		this.video = new SimpleStringProperty(super.getMedia());
	}
	
	/*
	 * Methods
	 */
	//variable: title	
	public void setPropertyTitle(String aTitle) {
		this.title.set(aTitle);
	}	
	public StringProperty getPropertyTitle() {
		return this.title;
	}
	
	//variable: actor
	public void setPropertyActor(String aActor) {
		this.actor.set(aActor);
	}
	public StringProperty getPropertyActor() {
		return this.actor;
	}
	
	//variable: director
	public void setPropertyDirector(String aDirector) {
		this.director.set(aDirector);
	}
	public StringProperty getPropertyDirector() {
		return this.director;
	}
	
	//variable: producer
	public void setPropertyProducer(String aProducer) {
		this.producer.set(aProducer);
	}
	public StringProperty getPropertyProducer() {
		return this.producer;
	}
	
	//variable: genre
	public void setPropertyGenre(String aGenre) {
		this.genre.set(aGenre);
	}
	public StringProperty getPropertyGenre() {
		return this.genre;
	}
	
	//variable: language
	public void setPropertyLanguage(String aLanguage) {
		this.language.set(aLanguage);
	}
	public StringProperty getPropertyLanguage() {
		return this.language;
	}
	
	//variable: audience
	public void setPropertyAudience(String aAudience) {
		this.audience.set(aAudience);
	}
	public StringProperty getPropertyAudience() {
		return this.audience;
	}
	
	//variable: year
	public void setPropertyYear(int aYear) {
		this.year.set(aYear);
	}
	public IntegerProperty getPropertyYear() {
		return this.year;
	}
	
	//variable: length
	public void setPropertyLength(double aLength) {
		this.length.set(aLength);
	}
	public DoubleProperty getPropertyLength() {
		return this.length;
	}
	
	//variable size
	public void setPropertySize(double aSize) {
		this.size.set(aSize);
	}
	public DoubleProperty getPropertySize() {
		return this.size;
	}

	//variable: format
	public void setPropertyFormat(String aFormat) {
		this.format.set(aFormat);
	}
	public StringProperty getPropertyFormat() {
		return this.format;
	}
	
	//variable: image
	public void setPropertyImage(String aImage) {
		this.image.set(aImage);
	}
	public StringProperty getPropertyImage() {
		return this.image;
	}
	
	//variable: video
	public void setPropertyMedia(String aVideo) {
		this.image.set(aVideo);
	}
	public StringProperty getPropertyMedia() {
		return this.video;
	}
	
	public void print() {
		super.print();
	}
	
}
