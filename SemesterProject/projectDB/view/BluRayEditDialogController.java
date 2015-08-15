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

import projectDB.model.BluRay;


/*
 * BluRay-Edit-Controller. For more details see CdEditDialogController!!! 
 */
public class BluRayEditDialogController {

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
	private TextField threeDField;
	@FXML
	private File picture;
	@FXML
	private File mediaFile;
	
	private String image;
	private String video;
	private FileChooser pictureChooser = new FileChooser();
	private FileChooser mediaFileChooser = new FileChooser();
	
	private Stage dialogStage;
	private BluRay br;
	
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
	public void setBluRay(BluRay br) {
		this.br = br;
		
		titleField.setText(br.getTitle());
		actorField.setText(br.getActor());
		directorField.setText(br.getDirector());
		producerField.setText(br.getProducer());
		genreField.setText(br.getGenre());
		languageField.setText(br.getLanguage());
		audienceField.setText(br.getAudience());
		yearField.setText(Integer.toString(br.getYear()));
		sizeField.setText(Double.toString(br.getSize()));
		lengthField.setText(Double.toString(br.getLength()));
		formatField.setText(br.getFormat());
		threeDField.setText(br.getThreeD());
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
	 * Handle the MediaFile
	 */
	@FXML
	private void handleMediaFile() {
		this.mediaFile = mediaFileChooser.showOpenDialog(dialogStage);
		try {
			String tempVideo = mediaFile.toString();
			this.video = tempVideo.substring(tempVideo.length()-8);
			
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Media not found!");
			alert.setHeaderText("Please choose the right media!");
			alert.showAndWait();
		}
	}
	
	/*
	 * Button Controllers
	 */
	@FXML
	private void handleOk() {

		if (isInputValid()) {
			br.setPropertyTitle(titleField.getText());
			br.setPropertyActor(actorField.getText());
			br.setPropertyDirector(directorField.getText());
			br.setPropertyProducer(producerField.getText());
			br.setPropertyGenre(genreField.getText());
			br.setPropertyLanguage(languageField.getText());
			br.setPropertyAudience(audienceField.getText());
			br.setPropertyYear(Integer.parseInt(yearField.getText()));
			br.setPropertySize(Double.parseDouble(sizeField.getText()));
			br.setPropertyLength(Double.parseDouble(lengthField.getText()));
			br.setPropertyFormat(formatField.getText());
			br.setPropertyThreeD(threeDField.getText());
			br.setPropertyImage(this.image);
			br.setPropertyMedia(this.video);
			
			br.setTitle(titleField.getText());
			br.setActor(actorField.getText());
			br.setDirector(directorField.getText());
			br.setProducer(producerField.getText());
			br.setGenre(genreField.getText());
			br.setLanguage(languageField.getText());
			br.setAudience(audienceField.getText());
			br.setYear(Integer.parseInt(yearField.getText()));
			br.setSize(Double.parseDouble(sizeField.getText()));
			br.setLength(Double.parseDouble(lengthField.getText()));
			br.setFormat(formatField.getText());
			br.setThreeD(threeDField.getText());
			br.setImage(image);
			br.setMedia(video);
			
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
		if (threeDField.getText() == null || threeDField.getText().length() == 0) {
			errorMessage += "No valid 3D name!\n";
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
