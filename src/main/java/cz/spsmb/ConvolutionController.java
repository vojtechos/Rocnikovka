package cz.spsmb;

import cz.spsmb.convolution.ConvertImage;
import cz.spsmb.convolution.ConvolutionService;
import cz.spsmb.convolution.SimpleConvolutionService;
import cz.spsmb.model.Filter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ConvolutionController implements Initializable {

    @FXML
    public Button SelectImageButton;
    @FXML
    public Button DoConvolutionButton;
    @FXML
    public ImageView beforeImageView;
    @FXML
    public ImageView AfterImageView;
    @FXML
    public ComboBox<String> FilterComboBox;
    @FXML
    public TextArea Value1;
    @FXML
    public TextArea Value2;
    @FXML
    public TextArea Value3;
    @FXML
    public TextArea Value4;
    @FXML
    public TextArea Value5;
    @FXML
    public TextArea Value6;
    @FXML
    public TextArea Value7;
    @FXML
    public TextArea Value8;
    @FXML
    public TextArea Value9;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.FilterComboBox.getItems().addAll(new String[]{"Edge Detection"});

    }

    public void onOpenFileClick(MouseEvent mouseEvent) {

        Window window = ((Node) mouseEvent.getTarget()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chose Image");
        File chosenFile = fileChooser.showOpenDialog(window);


        renderImage(chosenFile);
        System.out.println(chosenFile);

    }

    public void renderImage(File chosenFile) {

        Image image = new Image(chosenFile.toURI().toString());
        beforeImageView.setImage(image);

    }

    public void onDoConvolutionClick(MouseEvent mouseEvent) {

        Image image = beforeImageView.getImage();

        AfterImageView.setImage(image);
        ConvolutionService convolutionService = new SimpleConvolutionService();
        // ziskat filter
        Filter filter = new Filter() {
            @Override
            public int[][] getArray() {
                return new int[0][];
            }
        };

        //Ziskej obrazek
        cz.spsmb.model.Image image1 = new cz.spsmb.model.Image() {
            @Override
            public int[][][] getArray() {
                return ConvertImage.convertImageToIntArray(image);
            }
        };

        int[][][] img  = convolutionService.convolution(image1, filter);
        Image convertedImg = S.convertArrayToImg(img);

    }

    public void onInstateValues(MouseEvent mouseEvent) {

        String operation = FilterComboBox.getSelectionModel().getSelectedItem();
        switch (operation) {
            case "Edge Detection" -> instateValues();
        }

    }

    private void instateValues() {

        Value1.setText("0");
        Value2.setText("0");
        Value3.setText("0");
        Value4.setText("0");
        Value5.setText("0");
        Value6.setText("0");
        Value7.setText("0");
        Value8.setText("0");
        Value9.setText("0");

    }
}