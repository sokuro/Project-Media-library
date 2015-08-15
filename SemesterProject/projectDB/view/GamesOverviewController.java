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
import projectDB.model.Games;


/*
 * Games-View-Controller. For more details see CdOverviewController!!! 
 */
public class GamesOverviewController {

	@FXML
	private TableView<Games> gamesTable;
	@FXML
	private TableColumn<Games, String> titleColumn;
	
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
	private Label playerLabel;
	@FXML
	private Label netLabel;
	@FXML
	private Label netAccountLabel;
	@FXML
	private Label controlLabel;
	@FXML
	private ImageView imageView;
	
	private Stage gamesStage;
	
	private boolean okClicked = false;
	
	//Reference to the main application
	private MainApp mainApp;
	
	Alert alert = new Alert(AlertType.ERROR);
	private Stage dialogStage;
	
	/*
	 * Constructor
	 */
	public GamesOverviewController() {
	}
	
	/*
	 * Initializing method
	 */
	@FXML
	private void initialize() {

		showGamesDetails(null);
		titleColumn.setCellValueFactory(new PropertyValueFactory<Games, String>("title"));
		gamesTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showGamesDetails(gamesTable.getSelectionModel().getSelectedItem()));
	}
	
	/*
	 * Show BluRay details
	 */
	private void showGamesDetails(Games games) {
		if (games != null) {
	
			titleLabel.setText(games.getTitle());
			producerLabel.setText(games.getProducer());
			genreLabel.setText(games.getGenre());
			languageLabel.setText(games.getLanguage());
			yearLabel.setText(Integer.toString(games.getYear()));
			sizeLabel.setText(Double.toString(games.getSize()));
			serialLabel.setText(games.getSerial());
			distributionLabel.setText(games.getDistribution());
			formatLabel.setText(games.getFormat());
			playerLabel.setText(games.getPlayer());
			netLabel.setText(games.getNet());
			netAccountLabel.setText(games.getNetAccount());
			controlLabel.setText(games.getControl());
			
			if (games.getImage() != null) {
				byte[] byteArray = Base64.getDecoder().decode(games.getImage());
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
			playerLabel.setText("");
			netLabel.setText("");
			netAccountLabel.setText("");
			controlLabel.setText("");
		}
	}
	
	/*
	 * Setting up an extra stage for the Games class
	 */
	public void setGamesStage(Stage stage) {
		this.gamesStage = stage;
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
		Games tempGames = new Games();
		boolean okClicked = mainApp.showGamesEditDialog(tempGames);
		if (okClicked) {
			mainApp.getGamesData().add(tempGames);
		}		
	}
	
	@FXML
	private void handleEditTitle() {
		Games selectedTitle = gamesTable.getSelectionModel().getSelectedItem();
		if (selectedTitle != null) {
			boolean okClicked = mainApp.showGamesEditDialog(selectedTitle);
			if (okClicked) {
				showGamesDetails(selectedTitle);
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
		int selectedIndex = gamesTable.getSelectionModel().getSelectedIndex();
		
		if (selectedIndex >= 0) {
			gamesTable.getItems().remove(selectedIndex);
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
		this.gamesStage.close();
	}
	
	/*
	 * MainApp reference
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		//Add observable list data to the table
		gamesTable.setItems(mainApp.getGamesData());
	}

}
