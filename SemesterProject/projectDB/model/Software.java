package projectDB.model;


public class Software extends Article {
	
	/*
	 * Instance variables
	 */
	private String serial;
	private String distribution;
	
	/*
	 * Constructor
	 */
	protected Software() {		
	}
	
	//2nd Constructor
	public Software(String aTitle, String aProducer, String aGenre, String aLanguage, 
			int aYear, double aSize, String aSerial, String aDistribution, String aFormat, 
			String aImage) {
		
		//inherited from the Article class
		super(aTitle, aProducer, aGenre, aLanguage, aYear, aSize, aFormat, aImage);
		
		//subclass own variables
		this.setSerial(aSerial);
		this.setDistribution(aDistribution);
	}

	/*
	 * Methods
	 */
	//variable: serial
	public void setSerial(String aSerial) {
		this.serial = aSerial;
	}
	public String getSerial() {
		return serial;
	}
	
	//variable: distribution
	public void setDistribution(String aDistribution) {
		this.distribution = aDistribution;
	}
	public String getDistribution() {
		return distribution;
	}
	
	public void print() {
		super.print();
		System.out.println("Software serial is: " + getSerial());
		System.out.println("Software distributor is: " + getDistribution());
	}
	
}
