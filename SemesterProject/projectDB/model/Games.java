package projectDB.model;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Games extends Software {
	
	/*
	 * Instance variables
	 */
	private String player;
	private String net;
	private String netAccount;
	private String control;
	
	/*
	 * Property-variables for the GUI 
	 */
	private final StringProperty title;
	private final StringProperty producer;
	private final StringProperty genre;
	private final StringProperty language;
	private final IntegerProperty year;
	private final DoubleProperty size;
	private final StringProperty serial;
	private final StringProperty distribution;
	private final StringProperty format;
	private final StringProperty image;
	
	private final StringProperty playerProperty;
	private final StringProperty netProperty;
	private final StringProperty netAccountProperty;
	private final StringProperty controlProperty;
	
	/*
	 * Constructor
	 */
	public Games() {
		this(null,null, null, null, 0, 0, null, null, null, null, null, null, null, null);
	}
	
	//2nd Constructor
	public Games(String aTitle, String aProducer, String aGenre, String aLanguage, 
			int aYear, double aSize, String aSerial, String aDistribution, String aFormat,
			String aImage, String aPlayer, String aNet, String aNetAccount, String aControl) {
		
		/*
		 * Methods setter inherited from the super-class. The variables of the superclass
		 * are inherited by the subclass. The variables for the Property methods (for GUI) 
		 * are assigned to these variables.
		 */	
		super(aTitle, aProducer, aGenre, aLanguage, aYear, aSize, aSerial,  
				aDistribution, aFormat, aImage);
		
		//subclass own variables
		this.setPlayer(aPlayer);
		this.setNet(aNet);	
		this.setNetAccount(aNetAccount);
		this.setControl(aControl);
		
		this.title = new SimpleStringProperty(super.getTitle());
		this.producer = new SimpleStringProperty(super.getProducer());
		this.genre = new SimpleStringProperty(super.getGenre());
		this.language = new SimpleStringProperty(super.getLanguage());
		this.year = new SimpleIntegerProperty(super.getYear());
		this.size = new SimpleDoubleProperty(super.getSize());
		this.serial = new SimpleStringProperty(super.getSerial());
		this.distribution = new SimpleStringProperty(super.getDistribution());
		this.format = new SimpleStringProperty(super.getFormat());
		this.image = new SimpleStringProperty(super.getImage());
		
		this.playerProperty = new SimpleStringProperty(this.getPlayer());
		this.netProperty = new SimpleStringProperty(this.getNet());
		this.netAccountProperty = new SimpleStringProperty(this.getNetAccount());
		this.controlProperty = new SimpleStringProperty(this.getControl());
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
	public StringProperty getPropertylanguage() {
		return this.language;
	}
	
	//variable: year
	public void setPropertyYear(int aYear) {
		this.year.set(aYear);
	}
	public IntegerProperty getPropertyYear() {
		return this.year;
	}
	
	//variable: size
	public void setPropertySize(double aSize) {
		this.size.set(aSize);
	}
	public DoubleProperty getPropertySize() {
		return this.size;
	}
	
	//variable: serial
	public void setPropertySerial(String aSerial) {
		this.serial.set(aSerial);
	}
	public StringProperty getPropertySerial() {
		return this.serial;
	}
	
	//variable: distribution
	public void setPropertyDistribution(String aDistribution) {
		this.distribution.set(aDistribution);
	}
	public StringProperty getPropertyDistribution() {
		return this.distribution;
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
	
	//variable: player
	public void setPlayer(String aPlayer) {
		this.player = aPlayer;
	}
	public String getPlayer() {
		return this.player;
	}
	public void setPropertyPlayer(String playerProperty) {
		this.playerProperty.set(playerProperty);
	}
	public StringProperty getPropertyPlayer() {
		return this.playerProperty;
	}
	
	//variable net
	public void setNet(String aNet) {
		this.net = aNet;
	}
	public String getNet() {
		return this.net;
	}
	public void setPropertyNet(String netProperty) {
		this.netProperty.set(netProperty);
	}
	public StringProperty getPropertyNet() {
		return this.netProperty;
	}
	
	//variable: netAccount
	public void setNetAccount(String aNetAccount) {
		this.netAccount = aNetAccount;
	}
	public String getNetAccount() {
		return this.netAccount;
	}
	public void setPropertyNetAccount(String netAccountProperty) {
		this.netAccountProperty.set(netAccountProperty);
	}
	public StringProperty getPropertyNetAccount() {
		return this.netAccountProperty;
	}
	
	//variable control
	public void setControl(String aControl) {
		this.control = aControl;
	}
	public String getControl() {
		return this.control;
	}
	public void setPropertyControl(String controlProperty) {
		this.controlProperty.set(controlProperty);
	}
	public StringProperty getPropertyControl() {
		return this.controlProperty;
	}
	
	public void print() {
		super.print();
		System.out.println("Game's player: " + getPlayer());
		System.out.println("Game's net: " + getNet());
		System.out.println("Game's net account: " + getNetAccount());
		System.out.println("Game's control: " + getControl());
	}
	
}

