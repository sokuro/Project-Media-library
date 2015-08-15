package projectDB.view;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

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
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import projectDB.MainApp;
import projectDB.model.Vinyl;


/*
 * Vinyl-View-Controller. For more details see CdOverviewController!!! 
 */	
public class VinylOverviewController {
	/*
	 * Instance variables
	 */
	@FXML
	private TableView<Vinyl> vinylTable;
	@FXML
	private TableColumn<Vinyl, String> titleColumn;
	
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
	private ImageView imageView;
	
	private Stage vinylStage;
	
	private boolean okClicked = false;
	
	//Reference to the main application
	private MainApp mainApp;
	
	Alert alert = new Alert(AlertType.ERROR);
	private Stage dialogStage;
	
	/*
	 * Constructor
	 */
	public VinylOverviewController() {
	}
	
	/*
	 * Initializing method
	 */
	@FXML
	private void initialize() {

		//Clear the CD details (reset).
		showVinylDetails(null);
		titleColumn.setCellValueFactory(new PropertyValueFactory<Vinyl, String>("artist"));
		vinylTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showVinylDetails(vinylTable.getSelectionModel().getSelectedItem()));
	}
	
	/*
	 * Show ConcertVideo details
	 */ 
	private void showVinylDetails(Vinyl vinyl) {
		if (vinyl != null) {

			artistLabel.setText(vinyl.getArtist());
			titleLabel.setText(vinyl.getTitle());
			producerLabel.setText(vinyl.getProducer());
			genreLabel.setText(vinyl.getGenre());
			languageLabel.setText(vinyl.getLanguage());
			yearLabel.setText(Integer.toString(vinyl.getYear()));
			lengthLabel.setText(Double.toString(vinyl.getLength()));
			sizeLabel.setText(Double.toString(vinyl.getSize()));		
			formatLabel.setText(vinyl.getFormat());
			
			if (vinyl.getImage() != null) {
				byte[] byteArray = Base64.getDecoder().decode(vinyl.getImage());
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
	 * Setting up an extra stage for the Vinyl class
	 */
	public void setVinylStage(Stage stage) {
		this.vinylStage = stage;
	}
	
	/*
	 * Write Image to byteArray
	 */
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
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Button Controllers
	 */
	public boolean isOkClicked() {
		return okClicked;
	}
	
	@FXML
	private void handleNewArtist() {
		Vinyl tempVinyl= new Vinyl();
		boolean okClicked = mainApp.showVinylEditDialog(tempVinyl);
		if (okClicked) {
			mainApp.getVinylData().add(tempVinyl);
		}		
	}
	
	@FXML
	private void handleEditArtist() {
		Vinyl selectedArtist = vinylTable.getSelectionModel().getSelectedItem();
		if (selectedArtist != null) {
			boolean okClicked = mainApp.showVinylEditDialog(selectedArtist);
			if (okClicked) {
				showVinylDetails(selectedArtist);
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
	
	@FXML
	private void handleDeleteArtist() {
		int selectedIndex = vinylTable.getSelectionModel().getSelectedIndex();
		
		if (selectedIndex >= 0) {
			vinylTable.getItems().remove(selectedIndex);
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
	
	@FXML
	private void handleCancel() {
		this.vinylStage.close();
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
		vinylTable.setItems(mainApp.getVinylData());
	}

}
