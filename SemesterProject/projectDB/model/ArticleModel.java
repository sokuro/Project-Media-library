package projectDB.model;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;
import projectDB.XmlHelper;
import projectDB.model.Article;
import projectDB.model.BluRay;
import projectDB.model.Cd;


public class ArticleModel implements ArticleInterface {
	
	private File xmlFile = new File("DB.xml");
	private Writer writerXml = null;
	
	/*
	 * Handling the ArrayLists. Making a copy of the Observable.
	 */
	ArrayList<Cd> aAllCds = new ArrayList<Cd>();
	ArrayList<BluRay> aAllBluRays = new ArrayList<BluRay>();
	ArrayList<ConcertVideo> aAllConcerts = new ArrayList<ConcertVideo>();
	ArrayList<Vinyl> aAllVinyls = new ArrayList<Vinyl>();
	ArrayList<Dvd> aAllDvds = new ArrayList<Dvd>();
	ArrayList<Vhs> aAllVhss = new ArrayList<Vhs>();
	ArrayList<Programs> aAllProgs = new ArrayList<Programs>();
	ArrayList<Games> aAllGames = new ArrayList<Games>();

	public ArrayList<Cd> getACd() {
		return this.aAllCds;
	}
	public ArrayList<BluRay> getABluRay() {
		return this.aAllBluRays;
	}
	public ArrayList<ConcertVideo> getAConcert() {
		return this.aAllConcerts;
	}
	public ArrayList<Vinyl> getAVinyl() {
		return this.aAllVinyls;
	}
	public ArrayList<Dvd> getADvd() {
		return this.aAllDvds;
	}
	public ArrayList<Vhs> getAVhs() {
		return this.aAllVhss;
	}
	public ArrayList<Programs> getAProgs() {
		return this.aAllProgs;
	}
	public ArrayList<Games> getAGames() {
		return this.aAllGames;
	}

	//Method save to XML
	@Override
	public void save() {
		
		DBObject dbo = new DBObject(aAllCds, aAllBluRays, aAllConcerts, aAllVinyls, aAllDvds,
				aAllVhss, aAllProgs, aAllGames);
		
		try {
			writerXml = new FileWriter(xmlFile);
			try {
				XmlHelper.saveInstance(writerXml, dbo);					
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		} catch (IOException expt) {
			expt.printStackTrace();
		}	
	}
	
	//Method add
	@Override
	public void add(Article object) {
		if (object instanceof projectDB.model.Cd) {
			this.aAllCds.add((Cd) object);
		}
		else if (object instanceof projectDB.model.BluRay) {
			this.aAllBluRays.add((BluRay) object);
		}
		else if (object instanceof projectDB.model.ConcertVideo) {
			this.aAllConcerts.add((ConcertVideo) object);
		}
		else if (object instanceof projectDB.model.Vinyl) {
			this.aAllVinyls.add((Vinyl) object);
		}
		else if (object instanceof projectDB.model.Dvd) {
			this.aAllDvds.add((Dvd) object);
		}
		else if (object instanceof projectDB.model.Vhs) {
			this.aAllVhss.add((Vhs) object);
		}
		else if (object instanceof projectDB.model.Programs) {
			this.aAllProgs.add((Programs) object);
		}
		else if (object instanceof projectDB.model.Games) {
			this.aAllGames.add((Games) object);
		}
	}

	//Method load from the XML-File
	@Override
	public void loadXml() {
		
		try {
			DBObject dbo = (DBObject) XmlHelper.loadInstance(
					new FileReader(this.xmlFile), DBObject.class);
			
			aAllCds = dbo.getAllCd();
			aAllBluRays = dbo.getAllBluray();
			aAllConcerts = dbo.getAllConcert();
			aAllVinyls = dbo.getAllVinyl();
			aAllDvds = dbo.getAllDvd();
			aAllVhss = dbo.getAllVhs();
			aAllProgs = dbo.getAllProgs();
			aAllGames = dbo.getAllGames();
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JAXBException ex) {
			ex.printStackTrace();
		}	
	}
}
