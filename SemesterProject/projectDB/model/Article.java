package projectDB.model;

/*
 * The superclass Article
 */
public class Article {
	
	/*
	 * Instance variables
	 */
	private String title;
	private String producer;
	private String genre;
	private String format;
	private String language;
	private int year;
	private double size;
	
	//handle image
	private String image;
	
	/*
	 * Constructor (for XML)
	 */
	protected Article()	{		
	}
	
	//2nd Constructor
	public Article(String aTitle, String aProducer, String aGenre, String aLanguage, 
			int aYear, double aSize, String aFormat, String aImage) {

		this.setTitle(aTitle);
		this.setProducer(aProducer);
		this.setGenre(aGenre);
		this.setLanguage(aLanguage);
		this.setYear(aYear);
		this.setSize(aSize);
		this.setFormat(aFormat);
		this.setImage(aImage);
	}
	
	/*
	 * Methods
	 */
	//variable: title
	public void setTitle(String aTitle) {
		this.title = aTitle;
	}
	public String getTitle() {
		return title;
	}
	
	//variable: producer
	public void setProducer(String aProducer) {
		this.producer = aProducer;
	}
	public String getProducer() {
		return producer;
	}

	//variable: genre
	public void setGenre(String aGenre) {
		this.genre = aGenre;
	}
	public String getGenre() {
		return genre;
	}
	
	//variable: language
	public void setLanguage(String aLanguage) {
		this.language = aLanguage;
	}
	public String getLanguage() {
		return language;
	}
	
	//variable: year
	public void setYear(int aYear) {
		this.year = aYear;
	}
	public int getYear() {
		return year;
	}
	
	//variable size
	public void setSize(double aSize) {
		this.size = aSize;
	}
	public double getSize() {
		return size;
	}

	//variable: format
	public void setFormat(String aFormat) {
		this.format = aFormat;
	}
	public String getFormat() {
		return format;
	}
	
	//variable: image
	public void setImage(String aImage) {
		this.image = aImage;
	}
	public String getImage() {
		return this.image;
	}

	public void print() {
		System.out.println("Article title is: " + getTitle());
		System.out.println("Article producer is: " + getProducer());
		System.out.println("Article genre is: " + getGenre());
		System.out.println("Article language is: " + getLanguage());
		System.out.println("Article year is: " + getYear());
		System.out.println("The size of the Article is: " + getSize()+ "MB");
		System.out.println("Article format is: " + getFormat());	
	}
	
}

