package projectDB.view;


//import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import projectDB.MainApp;


public class RootLayoutController {

	@FXML
	private PasswordField password;
	@FXML
	private Label message;
	
	public boolean okPassword = false;
	
	//Reference to the main application
	private MainApp mainApp;
	
	 /**
     * Is called by the main application to give a reference back to itself.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    /*
     * Handle password
     */
    @FXML
    private void handlePassword() {
    	String psw = "uuu";
    	password.setPromptText("Enter Password");
    	if (password.getText().equalsIgnoreCase(psw)) {
    		//okPassword = true;
    		mainApp.showDatabaseEntryOverview();
    	}
//    	password.setOnAction(new EventHandler<ActionEvent> {
//    			mainApp.launch();
    	else {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Wrong password!");
    		alert.setHeaderText("Wrong password typed!");
    		alert.showAndWait();
    	}    	
    }
   
	public boolean isPasswordOk() {
		System.out.println("Hallo");
		return okPassword;
	}

	/*
	 * Initializing method
	 */
	@FXML
	private void initialize() {		
	}
	
	public RootLayoutController(){
		
	}
	
    /*
     * Opens an about dialog
     */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Media Database");
        alert.setHeaderText("About");
        alert.setContentText("Author: Karol Ugorcak\nWebsite: http://ugorcak.ch");
        alert.showAndWait();
    }
    
    /*
     * Closes the application
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
    
    
}
