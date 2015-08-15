package projectDB;


import java.io.IOException;
import java.util.ArrayList;

import projectDB.model.ArticleModel;
import projectDB.model.Cd;
import projectDB.model.BluRay;
import projectDB.model.ConcertVideo;
import projectDB.model.Dvd;
import projectDB.model.Games;
import projectDB.model.Programs;
import projectDB.model.Vhs;
import projectDB.model.Vinyl;
import projectDB.view.BluRayEditDialogController;
import projectDB.view.BluRayOverviewController;
import projectDB.view.CdEditDialogController;
import projectDB.view.CdOverviewController;
import projectDB.view.ConcertVideoEditDialogController;
import projectDB.view.ConcertVideoOverviewController;
import projectDB.view.DbOverviewController;
import projectDB.view.DvdEditDialogController;
import projectDB.view.DvdOverviewController;
import projectDB.view.GamesEditDialogController;
import projectDB.view.GamesOverviewController;
import projectDB.view.ProgramsEditDialogController;
import projectDB.view.ProgramsOverviewController;
import projectDB.view.RootLayoutController;
import projectDB.view.VhsEditDialogController;
import projectDB.view.VhsOverviewController;
import projectDB.view.VinylEditDialogController;
import projectDB.view.VinylOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class MainApp extends Application {
	
	/*
	 * Stage is the main container. Inside of it a Scene 
	 * is added. This can be switched by an another Scene.
	 * 
	 */
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	/*
	 * We're working with JavaFX view classes that need to be
	 * informed about any changes made to the list of Artists.
	 * Otherwise the view would not be in sync with the data. 
	 * For this purpose, JavaFX introduces some new Collection
	 * classes. From these collections, we need the Observable-
	 * List. 
	 */ 
	private ObservableList<Cd> cdData = FXCollections.observableArrayList();
	private ObservableList<BluRay> brData = FXCollections.observableArrayList();	
	private ObservableList<ConcertVideo> cvData = FXCollections.observableArrayList();
	private ObservableList<Vinyl> vinylData = FXCollections.observableArrayList();
	private ObservableList<Dvd> dvdData = FXCollections.observableArrayList();
	private ObservableList<Vhs> vhsData = FXCollections.observableArrayList();
	private ObservableList<Programs> progsData = FXCollections.observableArrayList();
	private ObservableList<Games> gamesData = FXCollections.observableArrayList();

	/*
	 * Setter methods
	 */
	public void setCdData(ArrayList<Cd> cd) {
		cdData = FXCollections.observableArrayList(cd);
	}
	public void setBluRayData(ArrayList<BluRay> br) {
		brData = FXCollections.observableArrayList(br);
	}
	public void setConcertVideoData(ArrayList<ConcertVideo> cv) {
		cvData = FXCollections.observableArrayList(cv);
	}
	public void setVinylData(ArrayList<Vinyl> vinyl) {
		vinylData = FXCollections.observableArrayList(vinyl);
	}
	public void setDvdData(ArrayList<Dvd> dvd) {
		dvdData = FXCollections.observableArrayList(dvd);
	}
	public void setVhsData(ArrayList<Vhs> vhs) {
		vhsData = FXCollections.observableArrayList(vhs);
	}
	public void setProgramsData(ArrayList<Programs> progs) {
		progsData = FXCollections.observableArrayList(progs);
	}
	public void setGamesData(ArrayList<Games> games) {
		gamesData = FXCollections.observableArrayList(games);
	}
	
	/*
	 * Getter methods. Return the data as an observable list
	 */
	public ObservableList<Cd> getCdData() {
		return cdData;
	}
	public ObservableList<BluRay> getBluRayData() {
		return brData;
	}
	public ObservableList<ConcertVideo> getConcertVideoData() {
		return cvData;
	}
	public ObservableList<Vinyl> getVinylData() {
		return vinylData;
	}
	public ObservableList<Dvd> getDvdData() {
		return dvdData;
	}
	public ObservableList<Vhs> getVhsData() {
		return vhsData;
	}
	public ObservableList<Programs> getProgramsData() {
		return progsData;
	}
	public ObservableList<Games> getGamesData() {
		return gamesData;
	}
	
	/*
	 * Constructor (empty)
	 */
	public MainApp() {
	}		
		
	/*
	 * method start is automatically called when the 
	 * application is launched from within the main
	 * method.
	 * the start method receives a Stage as a parameter
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Media Database");
		
		initRootLayout();
	}
	
	/*
	 * Initializes the root layout
	 */
	private void initRootLayout() {
		try {
			//Load root layout from the fxml file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			/*
			 * Show the scene containing the root layout
			 */
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			RootLayoutController control = new RootLayoutController();
			control.setMainApp(this);
			
			/*
			 * Load from the XML
			 */
			ArticleModel entryModel = new ArticleModel();
			entryModel.loadXml();
			this.setCdData(entryModel.getACd());
			this.setBluRayData(entryModel.getABluRay());
			this.setConcertVideoData(entryModel.getAConcert());
			this.setVinylData(entryModel.getAVinyl());
			this.setDvdData(entryModel.getADvd());
			this.setVhsData(entryModel.getAVhs());
			this.setProgramsData(entryModel.getAProgs());
			this.setGamesData(entryModel.getAGames());
				
			/*
			 * Save files to XML after closing
			 */
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent event) {
					System.out.println("..closing window");
					
					ArticleModel model;
					try {
						model = new ArticleModel();
					
						for (Cd cd : getCdData()) {
							model.add(cd);
						}
						for (BluRay br : getBluRayData()) {
							model.add(br);
						}
						for (ConcertVideo cv : getConcertVideoData()) {
							model.add(cv);
						}
						for (Vinyl vinyl : getVinylData()) {
							model.add(vinyl);
						}
						for (Dvd dvd : getDvdData()) {
							model.add(dvd);
						}
						for (Vhs vhs : getVhsData()) {
							model.add(vhs);
						}
						for (Programs progs : getProgramsData()) {
							model.add(progs);
						}
						for (Games games : getGamesData()) {
							model.add(games);
						}
						model.save();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
			
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/*
	 * Shows the Database entry layout
	 */
	public void showDatabaseEntryOverview() {
		try {
			//Load database layout from the fxml file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/DbOverview.fxml"));
			AnchorPane databaseOverview = (AnchorPane) loader.load();
			
			//Set database overview into the center of root layout
			rootLayout.setCenter(databaseOverview);
			
			/*
			 * The part of the method must be added:
			 * Give the controller access to the main app!
			 */
			DbOverviewController controller = loader.getController();
			controller.setMainApp(this);
						
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Opens a new table with the CdOverview 
	 */
	public void showCdOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CdOverview.fxml"));
			AnchorPane cdOverview = (AnchorPane) loader.load();
			
			//Create a new Stage
			Stage cdStage = new Stage();
			cdStage.setTitle("CD");
			/*
			 * Specifies the modality for this stage. This must be done 
			 * prior to making the stage visible.
			 */
			cdStage.initModality(Modality.WINDOW_MODAL);
			cdStage.initOwner(primaryStage);
			Scene scene = new Scene(cdOverview);
			cdStage.setScene(scene);
			
			//controller -> audioStage
			CdOverviewController controller = loader.getController();
			controller.setMainApp(this);
			
			//setting up a stage
			controller.setCdStage(cdStage);
			
			//Show the dialog and wait until the user closes it.
			cdStage.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Opens a new table with the ConcertVideoOverview 
	 */
	public void showConcertVideoOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ConcertVideoOverview.fxml"));
			AnchorPane cvOverview = (AnchorPane) loader.load();
			
			Stage cvStage = new Stage();
			cvStage.setTitle("Concert Video");
			cvStage.initModality(Modality.WINDOW_MODAL);
			cvStage.initOwner(primaryStage);
			Scene scene = new Scene(cvOverview);
			cvStage.setScene(scene);
			
			ConcertVideoOverviewController controller = loader.getController();
			controller.setMainApp(this);
			controller.setCvStage(cvStage);
			cvStage.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Opens a new table with the VinylOverview 
	 */
	public void showVinylOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/VinylOverview.fxml"));
			AnchorPane vinylOverview = (AnchorPane) loader.load();
			
			Stage vinylStage = new Stage();
			vinylStage.setTitle("Vinyl");
			vinylStage.initModality(Modality.WINDOW_MODAL);
			vinylStage.initOwner(primaryStage);
			Scene scene = new Scene(vinylOverview);
			vinylStage.setScene(scene);
			
			VinylOverviewController controller = loader.getController();
			controller.setMainApp(this);
			controller.setVinylStage(vinylStage);
			vinylStage.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Opens a new table with the DvdOverview 
	 */
	public void showDvdOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/DvdOverview.fxml"));
			AnchorPane dvdOverview = (AnchorPane) loader.load();
			
			Stage dvdStage = new Stage();
			dvdStage.setTitle("DVD");
			dvdStage.initModality(Modality.WINDOW_MODAL);
			dvdStage.initOwner(primaryStage);
			Scene scene = new Scene(dvdOverview);
			dvdStage.setScene(scene);
			
			DvdOverviewController controller = loader.getController();
			controller.setMainApp(this);
			controller.setDvdStage(dvdStage);
			dvdStage.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Opens a new table with the VhsOverview 
	 */
	public void showVhsOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/VhsOverview.fxml"));
			AnchorPane vhsOverview = (AnchorPane) loader.load();
			
			Stage vhsStage = new Stage();
			vhsStage.setTitle("VHS");
			vhsStage.initModality(Modality.WINDOW_MODAL);
			vhsStage.initOwner(primaryStage);
			Scene scene = new Scene(vhsOverview);
			vhsStage.setScene(scene);
			
			VhsOverviewController controller = loader.getController();
			controller.setMainApp(this);
			controller.setVhsStage(vhsStage);
			vhsStage.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Opens a new table with the BluRayOverview 
	 */
	public void showBluRayOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/BluRayOverview.fxml"));
			AnchorPane brOverview = (AnchorPane) loader.load();
			
			Stage brStage = new Stage();
			brStage.setTitle("BluRay");
			brStage.initModality(Modality.WINDOW_MODAL);
			brStage.initOwner(primaryStage);
			Scene scene = new Scene(brOverview);
			brStage.setScene(scene);
			
			BluRayOverviewController controller = loader.getController();
			controller.setMainApp(this);
			controller.setBluRayStage(brStage);
			brStage.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Opens a new table with the ProgramsOverview 
	 */
	public void showProgramsOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ProgramsOverview.fxml"));
			AnchorPane progsOverview = (AnchorPane) loader.load();
			
			Stage progsStage = new Stage();
			progsStage.setTitle("Programs");
			progsStage.initModality(Modality.WINDOW_MODAL);
			progsStage.initOwner(primaryStage);
			Scene scene = new Scene(progsOverview);
			progsStage.setScene(scene);
			
			ProgramsOverviewController controller = loader.getController();
			controller.setMainApp(this);
			controller.setProgsStage(progsStage);
			progsStage.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Opens a new table with the GamesOverview 
	 */
	public void showGamesOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/GamesOverview.fxml"));
			AnchorPane gamesOverview = (AnchorPane) loader.load();
			
			Stage gamesStage = new Stage();
			gamesStage.setTitle("Games");
			gamesStage.initModality(Modality.WINDOW_MODAL);
			gamesStage.initOwner(primaryStage);
			Scene scene = new Scene(gamesOverview);
			gamesStage.setScene(scene);
			
			GamesOverviewController controller = loader.getController();
			controller.setMainApp(this);
			controller.setGamesStage(gamesStage);
			gamesStage.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Opens a dialog to edit details for the specified artist. If the user
	 * clicks OK, the changes are saved into the provided CD object and
	 * true is returned.
	 * 
	 * @param artist the CD object to be edited.
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showCdEditDialog(Cd cd) {
		try {
			//load fxml file and create a new stage for the popup dialog
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CdEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			//Create a new Dialog Stage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Artist");
			/*
			 * Specifies the modality for this stage. This must be done 
			 * prior to making the stage visible.
			 */
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			//Set the artist into the controller
			CdEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setCd(cd);
			
			//Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/*
	 * Opens a dialog to edit details for the specified ConcertVideo title. 
	 */
	public boolean showConcertVideoEditDialog(ConcertVideo cv) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ConcertVideoEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Artist");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			ConcertVideoEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setConcertVideo(cv);
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * Opens a dialog to edit details for the specified Vinyl title. 
	 */
	public boolean showVinylEditDialog(Vinyl vinyl) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/VinylEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Artist");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			VinylEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setVinyl(vinyl);
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * Opens a dialog to edit details for the specified DVD title. 
	 */
	public boolean showDvdEditDialog(Dvd dvd) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/DvdEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Title");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			DvdEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setDvd(dvd);
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * Opens a dialog to edit details for the specified VHS title. 
	 */
	public boolean showVhsEditDialog(Vhs vhs) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/VhsEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Title");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			VhsEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setVhs(vhs);
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * Opens a dialog to edit details for the specified BluRay title. 
	 */
	public boolean showBluRayEditDialog(BluRay br) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/BluRayEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Title");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			BluRayEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setBluRay(br);			
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * Opens a dialog to edit details for the specified Program title. 
	 */
	public boolean showProgramsEditDialog(Programs progs) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ProgramsEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Title");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			ProgramsEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPrograms(progs);			
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * Opens a dialog to edit details for the specified Game title. 
	 */
	public boolean showGamesEditDialog(Games games) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/GamesEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Title");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			GamesEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setGames(games);			
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * Returns the Main Stage
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

