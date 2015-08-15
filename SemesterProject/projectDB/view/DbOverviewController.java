package projectDB.view;


import projectDB.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


/*
 * Entry-panel DBOverviewController
 */
public class DbOverviewController {
	
	@FXML
	private VBox videoButtons;
	@FXML
	private VBox audioButtons;
	@FXML
	private VBox programButtons;
	
	//Reference to the main application
	private MainApp mainApp;

	/*
	 * Initializes the controller class. This method
	 * is automatically called after the fxml file has
	 * been loaded.
	 */
	@FXML
	private void initialize() {
		this.hideAudioButtons();
		this.hideVideoButtons();
		this.hideProgramsButtons();
	}
	
	@FXML
	private void handleAudio() {
		this.showAudioButtons();
		this.hideVideoButtons();
		this.hideProgramsButtons();
	}
	
	@FXML
	private void handleCd() {
//		if(access){
			mainApp.showCdOverview();			
//		}else{
//			Alert alert 
//		}
	}
	
	@FXML
	private void handleConcertVideo() {
		mainApp.showConcertVideoOverview();
	}
	
	@FXML 
	private void handleVinyl() {
		mainApp.showVinylOverview();
	}
	
	@FXML
	private void handleVideo() {
		this.showVideoButtons();
		this.hideAudioButtons();
		this.hideProgramsButtons();
	}
	
	@FXML
	private void handleDvd() {
		mainApp.showDvdOverview();
	}
	
	@FXML
	private void handleVhs() {
		mainApp.showVhsOverview();
	}
	
	@FXML
	private void handleBluray() {
		mainApp.showBluRayOverview();
	}
	
	@FXML
	private void handleSoftware() {
		this.showProgramsButtons();
		this.hideAudioButtons();
		this.hideVideoButtons();
	}

	@FXML
	private void handlePrograms() {
		mainApp.showProgramsOverview();
	}
	
	@FXML
	private void handleGames() {
		mainApp.showGamesOverview();
	}
	
	private void showAudioButtons(){
		ObservableList<Node> buttons = FXCollections.observableArrayList(audioButtons.getChildren());
		
		for(Node elem : buttons){
			if(elem instanceof Button){
				elem.setVisible(true);
			}
		}
	}
	
	private void hideAudioButtons(){
		ObservableList<Node> buttons = FXCollections.observableArrayList(audioButtons.getChildren());
		
		for(Node elem : buttons){
			if(elem instanceof Button){
				elem.setVisible(false);
			}
		}
	}
	
	private void showVideoButtons(){
		ObservableList<Node> buttons = FXCollections.observableArrayList(videoButtons.getChildren());
		
		for(Node elem : buttons){
			if(elem instanceof Button){
				elem.setVisible(true);
			}
		}
	}
	
	private void hideVideoButtons(){
		ObservableList<Node> buttons = FXCollections.observableArrayList(videoButtons.getChildren());
		
		for(Node elem : buttons){
			if(elem instanceof Button){
				elem.setVisible(false);
			}
		}
//		
//		for(int i = 0; i <= buttons.size()-1; i++)
//		{
//			buttons.get(i).setVisible(false);
//		}
	}
	
	private void showProgramsButtons(){
		ObservableList<Node> buttons = FXCollections.observableArrayList(programButtons.getChildren());
		
		for(Node elem : buttons){
			if(elem instanceof Button){
				elem.setVisible(true);
			}
		}
	}
	
	private void hideProgramsButtons(){
		ObservableList<Node> buttons = FXCollections.observableArrayList(programButtons.getChildren());
		
		for(Node elem : buttons){
			if(elem instanceof Button){
				elem.setVisible(false);
			}
		}
	}
	
//	private void isPassOk(){
//		if (password == "Karol")
//			access = true;
//		
//	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
	}
}
