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
import projectDB.model.Programs;


/*
 * Programs-Edit-Controller. For more details see CdEditDialogController!!! 
 */
public class ProgramsEditDialogController {

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
	private TextField licenseField;
	@FXML
	private File picture;
	
	private String image;
	private FileChooser pictureChooser = new FileChooser();
	
	private Stage dialogStage;
	private Programs progs;
	
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
	public void setPrograms(Programs progs) {
		this.progs= progs;
		
		titleField.setText(progs.getTitle());
		producerField.setText(progs.getProducer());
		genreField.setText(progs.getGenre());
		languageField.setText(progs.getLanguage());		
		yearField.setText(Integer.toString(progs.getYear()));
		sizeField.setText(Double.toString(progs.getSize()));
		serialField.setText(progs.getSerial());
		distributionField.setText(progs.getDistribution());
		formatField.setText(progs.getFormat());
		licenseField.setText(progs.getLicense());
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
			progs.setPropertyTitle(titleField.getText());
			progs.setPropertyProducer(producerField.getText());
			progs.setPropertyGenre(genreField.getText());
			progs.setPropertyLanguage(languageField.getText());
			progs.setPropertyYear(Integer.parseInt(yearField.getText()));
			progs.setPropertySize(Double.parseDouble(sizeField.getText()));
			progs.setPropertySerial(serialField.getText());
			progs.setPropertyDistribution(distributionField.getText());
			progs.setPropertyFormat(formatField.getText());
			progs.setPropertyLicense(licenseField.getText());
			progs.setPropertyImage(this.image);
			
			progs.setTitle(titleField.getText());
			progs.setProducer(producerField.getText());
			progs.setGenre(genreField.getText());
			progs.setLanguage(languageField.getText());
			progs.setYear(Integer.parseInt(yearField.getText()));
			progs.setSize(Double.parseDouble(sizeField.getText()));
			progs.setSerial(serialField.getText());
			progs.setDistribution(distributionField.getText());
			progs.setFormat(formatField.getText());
			progs.setLicense(licenseField.getText());
			progs.setImage(image);
			
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
		if (serialField.getText() == null || serialField.getText().length() == 0) {
			errorMessage += "No valid audience name!\n";
		}
		if (distributionField.getText() == null || distributionField.getText().length() == 0) {
			errorMessage += "No valid audience name!\n";
		}
		if (formatField.getText() == null || formatField.getText().length() == 0) {
			errorMessage += "No valid format!\n";
		}
		if (licenseField.getText() == null || licenseField.getText().length() == 0) {
			errorMessage += "No valid audience name!\n";
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
