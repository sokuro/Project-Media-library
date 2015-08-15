package projectDB.view;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;

import javax.imageio.ImageIO;

import projectDB.MainApp;
import projectDB.model.Cd;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


public class CdOverviewController {

	/*
	 * Instance variables added. These will give us access to 
	 * the table and the labels inside the view. The fields and 
	 * some methods have a special @FXML annotation. This is ne-
	 * cessary for the fxml-file to have access to private fields
	 * and private methods. After we have everything set up in 
	 * the fxml-file, the application will automatically fill 
	 * the variables when the fxml-file is loaded.
	 */	
	@FXML
	private TableView<Cd> cdTable;
	@FXML
	private TableColumn<Cd, String> titleColumn;
	
	@FXML
	private Label artistLabel;
	@FXML
	private Label titleLabel;
	@FXML
	private Label producerLabel;
	@FXML
	private Label genreLabel;
	@FXML
	private Label languageLabel;
	@FXML
	private Label yearLabel;
	@FXML
	private Label lengthLabel;
	@FXML
	private Label sizeLabel;
	@FXML
	private Label formatLabel;
	@FXML 
	private ImageView play;
	@FXML
	private ImageView imageView;
	
	private Stage cdStage;
	
	private boolean okClicked = false;
	
	/*
	 * Audio variables
	 */
	public boolean mp3Playing = false;
	public String audioString;
	public MediaPlayer mediaPlayer;
	public URL resource;
	public Media media;
	
	//Reference to the main application
	private MainApp mainApp;
	
	/*
	 * For handling errors!
	 */
	Alert alert = new Alert(AlertType.ERROR);
	private Stage dialogStage;
	
	/*
	 * Constructor
	 */
	public CdOverviewController() {
	}
	
	/*
	 * Initializes the controller class. This method is automati-
	 * cally called after the fxml-file has been loaded.
	 */
	@FXML
	private void initialize() {

		//Clear the CD details (reset).
		showCdDetails(null);
		
		/*
		 * Initialize the CD table with the one column.
		 * The cell value factory needs to be set to specify how 
		 * to populate all cells within the single TableColumn 
		 * (to determine which field inside the CD objects
		 * should be used for the particular column.)
		 */
		titleColumn.setCellValueFactory(new PropertyValueFactory<Cd, String>("artist"));
			
		/*
		 * Listen for selection changes and show the CD details when 
		 * changed. Whenever the user selects an artist in the table, 
		 * our lambda expression is executed. We take the newly selected 
		 * artist and pass it to the showCdDetails method.
		 */
		cdTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showCdDetails(cdTable.getSelectionModel().getSelectedItem()));
		
		/*
		 * Handle AudioFile
		 */
		play.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				resource = getClass().getResource(audioString);
				media = new Media(resource.toString());
//				MediaPlayer mediaPlayer = new MediaPlayer(media);
				mediaPlayer = new MediaPlayer(media);
//				if (mp3Playing == false) {
					mp3Playing = (mp3Playing == true) ? false : true;
					
					if(mp3Playing == true){
						mediaPlayer.play();
					}else{
						mediaPlayer.pause();
					}
//				}
//				if (mp3Playing ==  true) {
//					mediaPlayer.stop();
//				}
			}
		});
	}
	
	/*
	 * Fills all text fields to show details about the CD.
	 * If the specified CD is null, all text fields are clea-
	 * red.
	 */
	private void showCdDetails(Cd cd) {
		if (cd != null) {
			//Fill the labels with info from the CD-object
			artistLabel.setText(cd.getArtist());
			titleLabel.setText(cd.getTitle());
			producerLabel.setText(cd.getProducer());
			genreLabel.setText(cd.getGenre());
			languageLabel.setText(cd.getLanguage());
			//parsing Integer to String
			yearLabel.setText(Integer.toString(cd.getYear()));
			lengthLabel.setText(Double.toString(cd.getLength()));
			sizeLabel.setText(Double.toString(cd.getSize()));			
			formatLabel.setText(cd.getFormat());
			
			//get the audio data
			audioString = cd.getMedia();
			
			//for showing the image
			if (cd.getImage() != null) {
				byte[] byteArray = Base64.getDecoder().decode(cd.getImage());
				if (byteArray.length > 0 && this.bufferedToWriteable(byteArray) != null) {
					imageView.setImage(this.bufferedToWriteable(byteArray));
				}
			} 
			else {
				imageView.setImage(null);
				alert.initOwner(dialogStage);
				alert.setTitle("Image not found!");
				alert.setHeaderText("Please choose the right image!");
			}
		}
		else {
			//otherwise the Cd is Null, so remove all the text
			artistLabel.setText("");
			titleLabel.setText("");
			producerLabel.setText("");
			genreLabel.setText("");
			languageLabel.setText("");
			yearLabel.setText("");
			lengthLabel.setText("");
			sizeLabel.setText("");
			formatLabel.setText("");
		}
	}
	
	
	/*
	 * Setting up an extra stage for the CD class
	 */
	public void setCdStage(Stage stage) {
		this.cdStage = stage;
	}
	
	private WritableImage bufferedToWriteable(byte[] byteArray) {
		BufferedImage bf;
		
		try {
			bf = ImageIO.read(new ByteArrayInputStream(byteArray));
			WritableImage wr = null;
			if (bf != null) {
				wr = new WritableImage(bf.getWidth(), bf.getHeight());
				PixelWriter pw = wr.getPixelWriter();
				for (int x = 0; x < bf.getWidth(); x++) {
					for (int y = 0; y < bf.getHeight(); y++) {
						pw.setArgb(x, y, bf.getRGB(x, y));
					}
				}
			}
			return wr;
		} catch (IOException e) {
			alert.initOwner(dialogStage);
			alert.setTitle("Input/Output Error");
			alert.setHeaderText("Please correct the I/O error!");
			alert.setContentText("Exception while reading the image" + e);
//			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Returns true if the user clicks OK
	 */
	public boolean isOkClicked() {
		return okClicked;
	}
	
	/*
	 * Called when the user clicks the "New" button. Opens a dialog to
	 * edit details for the new artist.
	 */
	@FXML
	private void handleNewArtist() {
		Cd tempCd = new Cd();
		boolean okClicked = mainApp.showCdEditDialog(tempCd);
		if (okClicked) {
			mainApp.getCdData().add(tempCd);
		}		
	}
	
	/*
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected artist.
	 */
	@FXML
	private void handleEditArtist() {
		Cd selectedArtist = cdTable.getSelectionModel().getSelectedItem();
		if (selectedArtist != null) {
			boolean okClicked = mainApp.showCdEditDialog(selectedArtist);
			if (okClicked) {
				showCdDetails(selectedArtist);
			}
		}
		else {
			//Nothing selected.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Artist Selected!");
			alert.setContentText("Please select an Artist in the table!");
			
			alert.showAndWait();
		}
	}
	
	/*
	 * this method is called when the user clicks on the delete button
	 */
	@FXML
	private void handleDeleteArtist() {
		int selectedIndex = cdTable.getSelectionModel().getSelectedIndex();
		
		/*
		 * handling the error if the user hits the delete button while
		 * no artist is selected.
		 */
		if (selectedIndex >= 0) {
			cdTable.getItems().remove(selectedIndex);
		}
		else {
			//nothing selected
			alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Artist Selected");
			alert.setContentText("Please select an Artist in the table");
			
			alert.showAndWait();
		}
	}
	
	/*
	 * returns to the previous Stage
	 */
	@FXML
	private void handleCancel() {
//		mediaPlayer.stop();
		this.cdStage.close();
	}
	
	/*
	 * Is called by the main application to give a reference
	 * back to itself. 
	 * The setMainApp() method must be called by the MainApp class!
	 * This gives us a way to access the MainApp object and get
	 * the list of Artists.
	 * 
	 * @param MainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		//Add observable list data to the table
		cdTable.setItems(mainApp.getCdData());
	}

}
