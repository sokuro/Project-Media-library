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

import projectDB.model.Vinyl;


/*
 * Vinyl-Edit-Controller. For more details see CdEditDialogController!!! 
 */
public class VinylEditDialogController {

	@FXML
	private TextField artistField;
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
	private TextField lengthField;
	@FXML
	private TextField sizeField;
	@FXML
	private TextField formatField;
	@FXML
	private File picture;
	
	private String image;
	private FileChooser pictureChooser = new FileChooser();
	
	//
	private Stage dialogStage;
	private Vinyl vinyl;
	;
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
	public void setVinyl(Vinyl vinyl) {
		this.vinyl = vinyl;
		
		artistField.setText(vinyl.getArtist());
		titleField.setText(vinyl.getTitle());
		producerField.setText(vinyl.getProducer());
		genreField.setText(vinyl.getGenre());
		languageField.setText(vinyl.getLanguage());
		yearField.setText(Integer.toString(vinyl.getYear()));
		lengthField.setText(Double.toString(vinyl.getLength()));
		sizeField.setText(Double.toString(vinyl.getSize()));
		formatField.setText(vinyl.getFormat());
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
			vinyl.setPropertyArtist(artistField.getText());
			vinyl.setPropertyTitle(titleField.getText());
			vinyl.setPropertyProducer(producerField.getText());
			vinyl.setPropertyGenre(genreField.getText());
			vinyl.setPropertyLanguage(languageField.getText());
			vinyl.setPropertyYear(Integer.parseInt(yearField.getText()));
			vinyl.setPropertyLength(Double.parseDouble(lengthField.getText()));
			vinyl.setPropertySize(Double.parseDouble(sizeField.getText()));
			vinyl.setPropertyFormat(formatField.getText());
			vinyl.setPropertyImage(this.image);
			
			vinyl.setArtist(artistField.getText());
			vinyl.setTitle(titleField.getText());
			vinyl.setProducer(producerField.getText());
			vinyl.setGenre(genreField.getText());
			vinyl.setLanguage(languageField.getText());
			vinyl.setFormat(formatField.getText());
			vinyl.setYear(Integer.parseInt(yearField.getText()));
			vinyl.setLength(Double.parseDouble(lengthField.getText()));
			vinyl.setSize(Double.parseDouble(sizeField.getText()));
			vinyl.setImage(image);
			
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
		
		if (artistField.getText() == null || artistField.getText().length() == 0) {
			errorMessage += "No valid artist name!\n";
		}
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
		if (formatField.getText() == null || formatField.getText().length() == 0) {
			errorMessage += "No valid format!\n";
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
			try {
				Double.parseDouble(lengthField.getText());
			} catch (NumberFormatException ex) {
				errorMessage += "No valid Length format (must be a Double)!\n";
			}
		}
		if (errorMessage.length() == 0) {
			return true;
		}
		else {
			//Show the error message
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
