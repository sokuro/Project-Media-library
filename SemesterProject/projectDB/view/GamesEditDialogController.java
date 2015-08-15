package projectDB.view;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Base64;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import projectDB.model.Games;


/*
 * Games-Edit-Controller. For more details see CdEditDialogController!!! 
 */
public class GamesEditDialogController {

	@FXML
	private TextField titleField;
	@FXML 
	private TextField producerField;
	@FXML
	private TextField genreField;
	@FXML
	private TextField languageField;
	@FXML
	private TextField yearField;
	@FXML
	private TextField sizeField;
	@FXML
	private TextField serialField;
	@FXML
	private TextField distributionField;
	@FXML
	private TextField formatField;
	@FXML
	private TextField playerField;
	@FXML
	private TextField netField;
	@FXML
	private TextField netAccountField;
	@FXML
	private TextField controlField;
	@FXML
	private File picture;
	
	private String image;
	private FileChooser pictureChooser = new FileChooser();
	
	private Stage dialogStage;
	private Games games;
	
	private boolean okClicked = false;
	
	/*
	 * Initializing method
	 */
	@FXML
	private void initialize() {		
	}
	
	/*
	 * Set the stage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	/*
	 * Edit the Title
	 */
	public void setGames(Games games) {
		this.games = games;
		
		titleField.setText(games.getTitle());
		producerField.setText(games.getProducer());
		genreField.setText(games.getGenre());
		languageField.setText(games.getLanguage());
		yearField.setText(Integer.toString(games.getYear()));
		sizeField.setText(Double.toString(games.getSize()));
		serialField.setText(games.getSerial());
		distributionField.setText(games.getDistribution());
		formatField.setText(games.getFormat());
		playerField.setText(games.getPlayer());
		netField.setText(games.getNet());
		netAccountField.setText(games.getNetAccount());
		controlField.setText(games.getControl());
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	/*
	 * Handle the image
	 */
	@FXML 
	private void handleImage() {
		this.picture = pictureChooser.showOpenDialog(dialogStage);
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
			BufferedImage img = ImageIO.read(new File(this.picture.toString()));
			ImageIO.write(img, "jpg", baos);
			baos.flush();
			
			String base64String = Base64.getEncoder().encodeToString(baos.toByteArray());
			baos.close();
			
			this.image = base64String;
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Wrong Image format!");
			alert.setHeaderText("Please choose the right Image format!");
			alert.showAndWait();
		}
	} 
	
	/*
	 * Button Controllers
	 */
	@FXML
	private void handleOk() {

		if (isInputValid()) {
			games.setPropertyTitle(titleField.getText());
			games.setPropertyProducer(producerField.getText());
			games.setPropertyGenre(genreField.getText());
			games.setPropertyLanguage(languageField.getText());
			games.setPropertyYear(Integer.parseInt(yearField.getText()));
			games.setPropertySize(Double.parseDouble(sizeField.getText()));
			games.setPropertySerial(serialField.getText());
			games.setPropertyDistribution(distributionField.getText());
			games.setPropertyFormat(formatField.getText());
			games.setPropertyPlayer(playerField.getText());
			games.setPropertyNet(netField.getText());
			games.setPropertyNetAccount(netAccountField.getText());
			games.setPropertyControl(controlField.getText());
			games.setPropertyImage(this.image);
			
			games.setTitle(titleField.getText());
			games.setProducer(producerField.getText());
			games.setGenre(genreField.getText());
			games.setLanguage(languageField.getText());
			games.setYear(Integer.parseInt(yearField.getText()));
			games.setSize(Double.parseDouble(sizeField.getText()));
			games.setSerial(serialField.getText());
			games.setDistribution(distributionField.getText());
			games.setFormat(formatField.getText());
			games.setPlayer(playerField.getText());
			games.setNet(netField.getText());
			games.setNetAccount(netAccountField.getText());
			games.setControl(controlField.getText());
			games.setImage(image);
			
			okClicked = true;
			dialogStage.close();
		}
	}
	
	/*
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	
	/*
	 * Input validation
	 */
	private boolean isInputValid() {
		String errorMessage = "";
		
		if (titleField.getText() == null || titleField.getText().length() == 0) {
			errorMessage += "No valid title name!\n";
		}
		if (producerField.getText() == null || producerField.getText().length() == 0) {
			errorMessage += "No valid producer name!\n";
		}
		if (genreField.getText() == null || genreField.getText().length() == 0) {
			errorMessage += "No valid genre!\n";
		}
		if (languageField.getText() == null || languageField.getText().length() == 0) {
			errorMessage += "No valid language!\n";
		}
		if (distributionField.getText() == null || distributionField.getText().length() == 0) {
			errorMessage += "No valid distribution!\n";
		}
		if (formatField.getText() == null || formatField.getText().length() == 0) {
			errorMessage += "No valid format!\n";
		}
		if (playerField.getText() == null || playerField.getText().length() == 0) {
			errorMessage += "No valid player!\n";
		}
		if (netField.getText() == null || netField.getText().length() == 0) {
			errorMessage += "No valid net!\n";
		}
		if (netAccountField.getText() == null || netAccountField.getText().length() == 0) {
			errorMessage += "No valid netAccount!\n";
		}
		if (controlField.getText() == null || controlField.getText().length() == 0) {
			errorMessage += "No valid control!\n";
		}
		else {
			try {
				Integer.parseInt(yearField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid Year format (must be an Integer)!\n";
			}
			try {
				Double.parseDouble(sizeField.getText());
			} catch (NumberFormatException ex) {
				errorMessage += "No valid Size format (must be a Double)!\n";
			}
		}
		if (errorMessage.length() == 0) {
			return true;
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct the invalid fields!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			
			return false;
		}
	}
	
}
