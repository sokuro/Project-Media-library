package projectDB.model;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Vinyl extends Audio {

	/*
	 * Instance variables. Property-variables
	 * for the GUI.
	 */
	private final StringProperty title;
	private final StringProperty artist;
	private final StringProperty producer;
	private final StringProperty genre;
	private final StringProperty language;
	private final IntegerProperty year;
	private final DoubleProperty length;
	private final DoubleProperty size;
	private final StringProperty format;
	private final StringProperty image;
	
	/*
	 * Constructor
	 */
	public Vinyl() {
		this(null, null, null, null, null, 0, 0, 0, null, null, null);
	}
	
	//2nd Constructor
	public Vinyl(String aTitle, String aArtist, String aProducer, String aGenre, String aLanguage, 
			int aYear, double aLength, double aSize, String aFormat, String aImage, String aVinylAudio) {
		
		/*
		 * Methods setter inherited from the super-class. The variables of the superclass
		 * are inherited by the subclass. The variables for the Property methods (for GUI) 
		 * are assigned to these variables.
		 */	
		super(aTitle, aArtist, aProducer, aGenre, aLanguage, aYear, aLength, aSize, aFormat, 
				aImage, aVinylAudio);
		
		this.title = new SimpleStringProperty(super.getTitle());
		this.artist = new SimpleStringProperty(super.getArtist());
		this.producer = new SimpleStringProperty(super.getProducer());
		this.genre = new SimpleStringProperty(super.getGenre());
		this.language = new SimpleStringProperty(super.getLanguage());
		this.year = new SimpleIntegerProperty(super.getYear());
		this.length = new SimpleDoubleProperty(super.getYear());
		this.size = new SimpleDoubleProperty(super.getSize());
		this.format = new SimpleStringProperty(super.getFormat());
		this.image = new SimpleStringProperty(super.getImage());		
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
	
	//variable: artist
	public void setPropertyArtist(String aArtist) {
		this.artist.set(aArtist);
	}
	public StringProperty getPropertyArtist() {
		return this.artist;
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
	
	public void print() {		
		super.print();
	}
	
}
