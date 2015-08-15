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
import projectDB.model.Vhs;


/*
 * Vhs-View-Controller. For more details see CdOverviewController!!! 
 */
public class VhsOverviewController {

	/*
	 * Instance variables
	 */
	@FXML
	private TableView<Vhs> vhsTable;
	@FXML
	private TableColumn<Vhs, String> titleColumn;
	
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
	
	private Stage vhsStage;
	
	private boolean okClicked = false;
	
	//Reference to the main application
	private MainApp mainApp;
	
	Alert alert = new Alert(AlertType.ERROR);
	private Stage dialogStage;
	
	/*
	 * Constructor
	 */
	public VhsOverviewController() {
	}
	
	/*
	 * Initializing method
	 */
	@FXML
	private void initialize() {

		showVhsDetails(null);
		titleColumn.setCellValueFactory(new PropertyValueFactory<Vhs, String>("title"));
		vhsTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showVhsDetails(vhsTable.getSelectionModel().getSelectedItem()));
	}
	
	/*
	 * Show BluRay details
	 */
	private void showVhsDetails(Vhs vhs) {
		if (vhs != null) {
	
			titleLabel.setText(vhs.getTitle());
			actorLabel.setText(vhs.getActor());
			directorLabel.setText(vhs.getDirector());
			producerLabel.setText(vhs.getProducer());
			genreLabel.setText(vhs.getGenre());
			languageLabel.setText(vhs.getLanguage());
			audienceLabel.setText(vhs.getAudience());
			yearLabel.setText(Integer.toString(vhs.getYear()));
			sizeLabel.setText(Double.toString(vhs.getSize()));
			lengthLabel.setText(Double.toString(vhs.getLength()));
			formatLabel.setText(vhs.getFormat());
			
			if (vhs.getImage() != null) {
				byte[] byteArray = Base64.getDecoder().decode(vhs.getImage());
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
	 * Setting up an extra stage for the VHS class
	 */
	public void setVhsStage(Stage stage) {
		this.vhsStage = stage;
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
		Vhs tempVhs = new Vhs();
		boolean okClicked = mainApp.showVhsEditDialog(tempVhs);
		if (okClicked) {
			mainApp.getVhsData().add(tempVhs);
		}		
	}
	
	@FXML
	private void handleEditTitle() {
		Vhs selectedTitle = vhsTable.getSelectionModel().getSelectedItem();
		if (selectedTitle != null) {
			boolean okClicked = mainApp.showVhsEditDialog(selectedTitle);
			if (okClicked) {
				showVhsDetails(selectedTitle);
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
		int selectedIndex = vhsTable.getSelectionModel().getSelectedIndex();
		
		if (selectedIndex >= 0) {
			vhsTable.getItems().remove(selectedIndex);
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
		this.vhsStage.close();
	}
	
	/*
	 * MainApp reference
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		//Add observable list data to the table
		vhsTable.setItems(mainApp.getVhsData());
	}

}
