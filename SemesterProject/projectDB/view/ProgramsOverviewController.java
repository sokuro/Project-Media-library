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
import projectDB.model.Programs;


/*
 * Programs-View-Controller. For more details see CdOverviewController!!! 
 */
public class ProgramsOverviewController {

	/*
	 * Instance variables
	 */
	@FXML
	private TableView<Programs> progsTable;
	@FXML
	private TableColumn<Programs, String> titleColumn;
	
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
	private Label sizeLabel;
	@FXML
	private Label serialLabel;
	@FXML
	private Label distributionLabel;
	@FXML
	private Label formatLabel;
	@FXML
	private Label licenseLabel;
	@FXML
	private ImageView imageView;
	
	private Stage progsStage;
	
	private boolean okClicked = false;
	
	//Reference to the main application
	private MainApp mainApp;
	
	Alert alert = new Alert(AlertType.ERROR);
	private Stage dialogStage;
	
	/*
	 * Constructor
	 */
	public ProgramsOverviewController() {
	}
	
	/*
	 * Initializing method
	 */
	@FXML
	private void initialize() {

		showProgramsDetails(null);
		titleColumn.setCellValueFactory(new PropertyValueFactory<Programs, String>("title"));
		progsTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showProgramsDetails(progsTable.getSelectionModel().getSelectedItem()));
	}
	
	/*
	 * Show BluRay details
	 */
	private void showProgramsDetails(Programs progs) {
		if (progs != null) {
	
			titleLabel.setText(progs.getTitle());
			producerLabel.setText(progs.getProducer());
			genreLabel.setText(progs.getGenre());
			languageLabel.setText(progs.getLanguage());
			yearLabel.setText(Integer.toString(progs.getYear()));
			sizeLabel.setText(Double.toString(progs.getSize()));
			serialLabel.setText(progs.getSerial());
			distributionLabel.setText(progs.getDistribution());
			formatLabel.setText(progs.getFormat());
			licenseLabel.setText(progs.getLicense());
			
			if (progs.getImage() != null) {
				byte[] byteArray = Base64.getDecoder().decode(progs.getImage());
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
			producerLabel.setText("");
			genreLabel.setText("");
			languageLabel.setText("");
			yearLabel.setText("");
			sizeLabel.setText("");
			serialLabel.setText("");
			distributionLabel.setText("");
			formatLabel.setText("");
			licenseLabel.setText("");
		}
	}
	
	/*
	 * Setting up an extra stage for the Program class
	 */
	public void setProgsStage(Stage stage) {
		this.progsStage = stage;
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
		Programs tempProgs = new Programs();
		boolean okClicked = mainApp.showProgramsEditDialog(tempProgs);
		if (okClicked) {
			mainApp.getProgramsData().add(tempProgs);
		}		
	}
	
	@FXML
	private void handleEditTitle() {
		Programs selectedTitle = progsTable.getSelectionModel().getSelectedItem();
		if (selectedTitle != null) {
			boolean okClicked = mainApp.showProgramsEditDialog(selectedTitle);
			if (okClicked) {
				showProgramsDetails(selectedTitle);
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
		int selectedIndex = progsTable.getSelectionModel().getSelectedIndex();
		
		if (selectedIndex >= 0) {
			progsTable.getItems().remove(selectedIndex);
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
		this.progsStage.close();
	}
	
	/*
	 * MainApp reference
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		//Add observable list data to the table
		progsTable.setItems(mainApp.getProgramsData());
	}

}
