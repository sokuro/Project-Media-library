package projectDB.view;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Base64;

import javax.imageio.ImageIO;

import projectDB.model.Cd;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/*
 * Dialog class to edit details of an artist
 */
public class CdEditDialogController {

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
	
	/*
	 * Image
	 */
	@FXML
	private File picture;
	private String image;
	private FileChooser pictureChooser = new FileChooser();
	
	/*
	 * File
	 */
	@FXML
	private File mediaFile;
	private String audio;
	private FileChooser mediaFileChooser = new FileChooser();
	
	//
	private Stage dialogStage;
	private Cd cd;
	private boolean okClicked = false;
	
	/*
	 * Initializes the controller class. This method
	 * is automatically called after the fxml file has
	 * been loaded.
	 */
	@FXML
	private void initialize() {		
	}
	
	/*
	 * Sets the stage of the dialog
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	/*
	 * Sets an artist to be edited in the dialog.
	 * The method can be called from another class to set
	 * the artist that is to be edited.
	 * 
	 * @param artist
	 */
	public void setCd(Cd cd) {
		this.cd = cd;
		
		artistField.setText(cd.getArtist());
		titleField.setText(cd.getTitle());
		producerField.setText(cd.getProducer());
		genreField.setText(cd.getGenre());
		languageField.setText(cd.getLanguage());
		yearField.setText(Integer.toString(cd.getYear()));
		lengthField.setText(Double.toString(cd.getLength()));
		sizeField.setText(Double.toString(cd.getSize()));
		formatField.setText(cd.getFormat());
	}
	
	/*
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
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
	 * Handle the MediaFile
	 */
	@FXML
	private void handleMediaFile() {
		this.mediaFile = mediaFileChooser.showOpenDialog(dialogStage);
		try {
			String tempAudio = mediaFile.toString();
			this.audio = tempAudio.substring(tempAudio.length()-8);
			
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Media not found!");
			alert.setHeaderText("Please choose the right media!");
			alert.showAndWait();
		}
	}
	
	/*
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		/*
		 * First the validation is done by calling isInputValid() method.
		 * Only if validation was successful, the CD object is filled 
		 * with the data the user entered. Those changes will directly 
		 * be applied to the CD object that was passed to setArtist()!
		 */
		if (isInputValid()) {
			cd.setPropertyArtist(artistField.getText());
			cd.setPropertyTitle(titleField.getText());
			cd.setPropertyProducer(producerField.getText());
			cd.setPropertyGenre(genreField.getText());
			cd.setPropertyLanguage(languageField.getText());
			cd.setPropertyYear(Integer.parseInt(yearField.getText()));
			cd.setPropertyLength(Double.parseDouble(lengthField.getText()));
			cd.setPropertySize(Double.parseDouble(sizeField.getText()));
			cd.setPropertyFormat(formatField.getText());
			
			//necessary for XML-file
			cd.setArtist(artistField.getText());
			cd.setTitle(titleField.getText());
			cd.setProducer(producerField.getText());
			cd.setGenre(genreField.getText());
			cd.setLanguage(languageField.getText());
			cd.setFormat(formatField.getText());
			cd.setYear(Integer.parseInt(yearField.getText()));
			cd.setLength(Double.parseDouble(lengthField.getText()));
			cd.setSize(Double.parseDouble(sizeField.getText()));
			
			//Handle image
			cd.setImage(image);
			cd.setPropertyImage(this.image);
			
			//Handle media
			cd.setMedia(audio);
			cd.setPropertyMedia(this.audio);
			
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
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
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
			//check if the year is the right integer format
			try {
				Integer.parseInt(yearField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid Year format (must be an Integer)!\n";
			}
			//check if the size is the right double format
			try {
				Double.parseDouble(sizeField.getText());
			} catch (NumberFormatException ex) {
				errorMessage += "No valid Size format (must be a Double)!\n";
			}
			//check if the length is the right double format
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
