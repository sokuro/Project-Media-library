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

import projectDB.model.Vhs;


/*
 * Vhs-Edit-Controller. For more details see CdEditDialogController!!! 
 */
public class VhsEditDialogController {

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
	private Vhs vhs;
	
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
	public void setVhs(Vhs vhs) {
		this.vhs= vhs;
		
		titleField.setText(vhs.getTitle());
		actorField.setText(vhs.getActor());
		directorField.setText(vhs.getDirector());
		producerField.setText(vhs.getProducer());
		genreField.setText(vhs.getGenre());
		languageField.setText(vhs.getLanguage());
		audienceField.setText(vhs.getAudience());
		yearField.setText(Integer.toString(vhs.getYear()));
		sizeField.setText(Double.toString(vhs.getSize()));
		lengthField.setText(Double.toString(vhs.getLength()));
		formatField.setText(vhs.getFormat());
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
			vhs.setPropertyTitle(titleField.getText());
			vhs.setPropertyActor(actorField.getText());
			vhs.setPropertyDirector(directorField.getText());
			vhs.setPropertyProducer(producerField.getText());
			vhs.setPropertyGenre(genreField.getText());
			vhs.setPropertyLanguage(languageField.getText());
			vhs.setPropertyAudience(audienceField.getText());
			vhs.setPropertyYear(Integer.parseInt(yearField.getText()));
			vhs.setPropertySize(Double.parseDouble(sizeField.getText()));
			vhs.setPropertyLength(Double.parseDouble(lengthField.getText()));
			vhs.setPropertyFormat(formatField.getText());
			vhs.setPropertyImage(this.image);
			
			vhs.setTitle(titleField.getText());
			vhs.setActor(actorField.getText());
			vhs.setDirector(directorField.getText());
			vhs.setProducer(producerField.getText());
			vhs.setGenre(genreField.getText());
			vhs.setLanguage(languageField.getText());
			vhs.setAudience(audienceField.getText());
			vhs.setYear(Integer.parseInt(yearField.getText()));
			vhs.setSize(Double.parseDouble(sizeField.getText()));
			vhs.setLength(Double.parseDouble(lengthField.getText()));
			vhs.setFormat(formatField.getText());
			vhs.setImage(image);
			
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

