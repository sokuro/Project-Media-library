package projectDB.view;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;

import javax.imageio.ImageIO;

import projectDB.MainApp;
import projectDB.model.BluRay;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/*
 * BluRay-View-Controller. For more details see CdOverviewController!!! 
 */
public class BluRayOverviewController {
	
	/*
	 * Instance variables
	 */
	@FXML
	private TableView<BluRay> brTable;
	@FXML
	private TableColumn<BluRay, String> titleColumn;
	
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
	private Label threeDLabel;
	@FXML 
	private ImageView play;
	@FXML
	private ImageView imageView;
	
	private Stage brStage;
	
	private boolean okClicked = false;
	
	/*
	 * Video variables
	 */
	public boolean videoPlaying = false;
	public String videoString;
	public MediaPlayer mediaPlayer;
	
	//Reference to the main application
	private MainApp mainApp;
	
	Alert alert = new Alert(AlertType.ERROR);
	private Stage dialogStage;
	
	/*
	 * Constructor
	 */
	public BluRayOverviewController() {
	}
	
	/*
	 * Initializing method
	 */
	@FXML
	private void initialize() {

		showBluRayDetails(null);
		titleColumn.setCellValueFactory(new PropertyValueFactory<BluRay, String>("title"));
		brTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showBluRayDetails(brTable.getSelectionModel().getSelectedItem()));
		/*
		 * Handle VideoFile
		 */
		play.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				URL resource = getClass().getResource(videoString);
				Media media = new Media(resource.toString());
				MediaPlayer mediaPlayer = new MediaPlayer(media);
				MediaView mediaView = new MediaView(mediaPlayer);
				
//				DoubleProperty width = mediaView.fitWidthProperty();
//				DoubleProperty height = mediaView.fitHeightProperty();
				mediaView.setPreserveRatio(true);
				StackPane root = new StackPane();
				root.getChildren().add(mediaView);
				
				Scene scene = new Scene(root, 960, 540);
				scene.setFill(Color.BLACK);
				    
				    Stage primaryStage = null;
					primaryStage.setScene(scene);
				    primaryStage.setTitle("Full Screen Video Player");
				    primaryStage.setFullScreen(true);
				    primaryStage.show();

				mediaPlayer.play();
			}
		});
	}
	
	/*
	 * Show BluRay details
	 */
	private void showBluRayDetails(BluRay br) {
		if (br != null) {
	
			titleLabel.setText(br.getTitle());
			actorLabel.setText(br.getActor());
			directorLabel.setText(br.getDirector());
			producerLabel.setText(br.getProducer());
			genreLabel.setText(br.getGenre());
			languageLabel.setText(br.getLanguage());
			audienceLabel.setText(br.getAudience());
			yearLabel.setText(Integer.toString(br.getYear()));
			sizeLabel.setText(Double.toString(br.getSize()));
			lengthLabel.setText(Double.toString(br.getLength()));
			formatLabel.setText(br.getFormat());
			threeDLabel.setText(br.getThreeD());
			
			videoString = br.getMedia();
			
			if (br.getImage() != null) {
				byte[] byteArray = Base64.getDecoder().decode(br.getImage());
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
			threeDLabel.setText("");
		}
	}
	
	/*
	 * Setting up an extra stage for the BluRay class
	 */
	public void setBluRayStage(Stage stage) {
		this.brStage = stage;
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
		BluRay tempBr = new BluRay();
		boolean okClicked = mainApp.showBluRayEditDialog(tempBr);
		if (okClicked) {
			mainApp.getBluRayData().add(tempBr);
		}		
	}
	
	@FXML
	private void handleEditTitle() {
		BluRay selectedTitle = brTable.getSelectionModel().getSelectedItem();
		if (selectedTitle != null) {
			boolean okClicked = mainApp.showBluRayEditDialog(selectedTitle);
			if (okClicked) {
				showBluRayDetails(selectedTitle);
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
		int selectedIndex = brTable.getSelectionModel().getSelectedIndex();
		
		if (selectedIndex >= 0) {
			brTable.getItems().remove(selectedIndex);
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
		this.brStage.close();
	}
	
	/*
	 * MainApp reference
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		//Add observable list data to the table
		brTable.setItems(mainApp.getBluRayData());
	}

}
