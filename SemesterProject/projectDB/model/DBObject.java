package projectDB.model;


import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import projectDB.model.BluRay;
import projectDB.model.Cd;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="allEntries")
public class DBObject {

	private ArrayList<Cd> aAllCdObj = new ArrayList<Cd>();
	private ArrayList<BluRay> aAllBluRayObj = new ArrayList<BluRay>();
	private ArrayList<ConcertVideo> aAllConcertVideoObj = new ArrayList<ConcertVideo>();
	private ArrayList<Vinyl> aAllVinylObj = new ArrayList<Vinyl>();
	private ArrayList<Dvd> aAllDvdObj = new ArrayList<Dvd>(); 
	private ArrayList<Vhs> aAllVhsObj = new ArrayList<Vhs>(); 
	private ArrayList<Programs> aAllProgsObj = new ArrayList<Programs>();
	private ArrayList<Games> aAllGamesObj = new ArrayList<Games>();
	
	/*
	 * Constructor
	 */
	protected DBObject() {		
	}
	
	//2nd Constructor
	public DBObject(ArrayList<Cd> allCd, ArrayList<BluRay> allBluRay, ArrayList<ConcertVideo> allConcert,
			ArrayList<Vinyl> allVinyl, ArrayList<Dvd> allDvd, ArrayList<Vhs> allVhs, ArrayList<Programs> 
			allProgs, ArrayList<Games> allGames) {
		this.aAllCdObj = allCd;
		this.aAllBluRayObj = allBluRay;
		this.aAllConcertVideoObj = allConcert;
		this.aAllVinylObj = allVinyl;
		this.aAllDvdObj = allDvd;
		this.aAllVhsObj = allVhs;
		this.aAllProgsObj = allProgs;
		this.aAllGamesObj = allGames;
	}	

	public ArrayList<Cd> getAllCd() {
		return this.aAllCdObj;
	}
	
	public ArrayList<BluRay> getAllBluray() {
		return this.aAllBluRayObj;
	}
	
	public ArrayList<ConcertVideo> getAllConcert() {
		return this.aAllConcertVideoObj;
	}
	
	public ArrayList<Vinyl> getAllVinyl() {
		return this.aAllVinylObj;
	}
	
	public ArrayList<Dvd> getAllDvd() {
		return this.aAllDvdObj;
	}
	
	public ArrayList<Vhs> getAllVhs() {
		return this.aAllVhsObj;
	}
	
	public ArrayList<Programs> getAllProgs() {
		return this.aAllProgsObj;
	}
	
	public ArrayList<Games> getAllGames() {
		return this.aAllGamesObj;
	}
	
}
