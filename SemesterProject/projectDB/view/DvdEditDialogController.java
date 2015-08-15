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

import projectDB.model.Dvd;


/*
 * Dvd-Edit-Controller. For more details see CdEditDialogController!!! 
 */
public class DvdEditDialogController {

	@FXML
	private TextField titleField;
	@FXML
	private TextField actorField;
	@FXML
	private TextField directorField;
	@FXML 
	private TextField producerField;
	@FXML
	private TextField genreField;
	@FXML
	private TextField languageField;
	@FXML
	private TextField audienceField;
	@FXML
	private TextField yearField;
	@FXML
	private TextField sizeField;
	@FXML
	private TextField lengthField;
	@FXML
	private TextField formatField;
	@FXML
	private File picture;
	
	private String image;
	private FileChooser pictureChooser = new FileChooser();
	
	private Stage dialogStage;
	private Dvd dvd;
	
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
	public void setDvd(Dvd dvd) {
		this.dvd= dvd;
		
		titleField.setText(dvd.getTitle());
		actorField.setText(dvd.getActor());
		directorField.setText(dvd.getDirector());
		producerField.setText(dvd.getProducer());
		genreField.setText(dvd.getGenre());
		languageField.setText(dvd.getLanguage());
		audienceField.setText(dvd.getAudience());
		yearField.setText(Integer.toString(dvd.getYear()));
		sizeField.setText(Double.toString(dvd.getSize()));
		lengthField.setText(Double.toString(dvd.getLength()));
		formatField.setText(dvd.getFormat());
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
			dvd.setPropertyTitle(titleField.getText());
			dvd.setPropertyActor(actorField.getText());
			dvd.setPropertyDirector(directorField.getText());
			dvd.setPropertyProducer(producerField.getText());
			dvd.setPropertyGenre(genreField.getText());
			dvd.setPropertyLanguage(languageField.getText());
			dvd.setPropertyAudience(audienceField.getText());
			dvd.setPropertyYear(Integer.parseInt(yearField.getText()));
			dvd.setPropertySize(Double.parseDouble(sizeField.getText()));
			dvd.setPropertyLength(Double.parseDouble(lengthField.getText()));
			dvd.setPropertyFormat(formatField.getText());
			dvd.setPropertyImage(this.image);
			
			dvd.setTitle(titleField.getText());
			dvd.setActor(actorField.getText());
			dvd.setDirector(directorField.getText());
			dvd.setProducer(producerField.getText());
			dvd.setGenre(genreField.getText());
			dvd.setLanguage(languageField.getText());
			dvd.setAudience(audienceField.getText());
			dvd.setYear(Integer.parseInt(yearField.getText()));
			dvd.setSize(Double.parseDouble(sizeField.getText()));
			dvd.setLength(Double.parseDouble(lengthField.getText()));
			dvd.setFormat(formatField.getText());
			dvd.setImage(image);
			
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
		if (actorField.getText() == null || actorField.getText().length() == 0) {
			errorMessage += "No valid actor name!\n";
		}
		if (directorField.getText() == null || directorField.getText().length() == 0) {
			errorMessage += "No valid director name!\n";
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
		if (audienceField.getText() == null || audienceField.getText().length() == 0) {
			errorMessage += "No valid audience name!\n";
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

