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
import projectDB.model.Dvd;


/*
 * Dvd-View-Controller. For more details see CdOverviewController!!! 
 */
public class DvdOverviewController {

	/*
	 * Instance variables
	 */
	@FXML
	private TableView<Dvd> dvdTable;
	@FXML
	private TableColumn<Dvd, String> titleColumn;
	
	@FXML
	private Label titleLabel;
	@FXML
	private Label actorLabel;
	@FXML
	private Label directorLabel;
	@FXML
	private Label producerLabel;
	@FXML
	private Label genreLabel;
	@FXML
	private Label languageLabel;
	@FXML
	private Label audienceLabel;
	@FXML
	private Label yearLabel;
	@FXML
	private Label sizeLabel;
	@FXML
	private Label lengthLabel;
	@FXML
	private Label formatLabel;
	@FXML
	private ImageView imageView;
	
	private Stage dvdStage;
	
	private boolean okClicked = false;
	
	//Reference to the main application
	private MainApp mainApp;
	
	Alert alert = new Alert(AlertType.ERROR);
	private Stage dialogStage;
	
	/*
	 * Constructor
	 */
	public DvdOverviewController() {
	}
	
	/*
	 * Initializing method
	 */
	@FXML
	private void initialize() {

		showDvdDetails(null);
		titleColumn.setCellValueFactory(new PropertyValueFactory<Dvd, String>("title"));
		dvdTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showDvdDetails(dvdTable.getSelectionModel().getSelectedItem()));
	}
	
	/*
	 * Show BluRay details
	 */
	private void showDvdDetails(Dvd dvd) {
		if (dvd != null) {
	
			titleLabel.setText(dvd.getTitle());
			actorLabel.setText(dvd.getActor());
			directorLabel.setText(dvd.getDirector());
			producerLabel.setText(dvd.getProducer());
			genreLabel.setText(dvd.getGenre());
			languageLabel.setText(dvd.getLanguage());
			audienceLabel.setText(dvd.getAudience());
			yearLabel.setText(Integer.toString(dvd.getYear()));
			sizeLabel.setText(Double.toString(dvd.getSize()));
			lengthLabel.setText(Double.toString(dvd.getLength()));
			formatLabel.setText(dvd.getFormat());
			
			if (dvd.getImage() != null) {
				byte[] byteArray = Base64.getDecoder().decode(dvd.getImage());
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
			titleLabel.setText("");
			actorLabel.setText("");
			directorLabel.setText("");
			producerLabel.setText("");
			genreLabel.setText("");
			languageLabel.setText("");
			audienceLabel.setText("");
			yearLabel.setText("");
			sizeLabel.setText("");
			lengthLabel.setText("");
			formatLabel.setText("");
		}
	}
	
	/*
	 * Setting up an extra stage for the DVD class
	 */
	public void setDvdStage(Stage stage) {
		this.dvdStage = stage;
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
	private void handleNewTitle() {
		Dvd tempDvd = new Dvd();
		boolean okClicked = mainApp.showDvdEditDialog(tempDvd);
		if (okClicked) {
			mainApp.getDvdData().add(tempDvd);
		}		
	}
	
	@FXML
	private void handleEditTitle() {
		Dvd selectedTitle = dvdTable.getSelectionModel().getSelectedItem();
		if (selectedTitle != null) {
			boolean okClicked = mainApp.showDvdEditDialog(selectedTitle);
			if (okClicked) {
				showDvdDetails(selectedTitle);
			}
		}
		else {
			//Nothing selected.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Title Selected!");
			alert.setContentText("Please select a Title in the table!");			
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleDeleteTitle() {
		int selectedIndex = dvdTable.getSelectionModel().getSelectedIndex();
		
		if (selectedIndex >= 0) {
			dvdTable.getItems().remove(selectedIndex);
		}
		else {
			//nothing selected
			alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Title Selected");
			alert.setContentText("Please select a Title in the table");			
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleCancel() {
		this.dvdStage.close();
	}
	
	/*
	 * MainApp reference
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		//Add observable list data to the table
		dvdTable.setItems(mainApp.getDvdData());
	}

}
