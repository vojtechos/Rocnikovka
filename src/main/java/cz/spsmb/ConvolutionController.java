package cz.spsmb;

import cz.spsmb.convolution.ConvertImage;
import cz.spsmb.convolution.ConvolutionService;
import cz.spsmb.convolution.    SimpleConvolutionService;
import cz.spsmb.filters.EdgeDetectionFilter;
import cz.spsmb.filters.EdgeDetectionFilter2;
import cz.spsmb.filters.EdgeDetectionFilter3;
import cz.spsmb.filters.SharpenFilter;
import cz.spsmb.model.Filter;
import javafx.embed.swing.SwingFXUtils;
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

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.nio.file.Files.write;

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

    Filter activeFilter;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.FilterComboBox.getItems().addAll(new String[]{"Edge Detection", "Edge Detection2", "Edge Detection3", "Sharpen"});

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

        ConvolutionService convolutionService = new SimpleConvolutionService();


        cz.spsmb.model.Image image1 = new cz.spsmb.model.Image() {
            @Override
            public int[][][] getArray() {
                return ConvertImage.convertImageToIntArray(image);
            }
        };

        int[][][] img  = convolutionService.convolution(image1, activeFilter, 1./9);
        Image convertedImg = ConvertImage.convertIntArrayToImg(image, img);
        AfterImageView.setImage(convertedImg);

    }

    public void onInstateValues(MouseEvent mouseEvent) {

        String operation = FilterComboBox.getSelectionModel().getSelectedItem();
        switch (operation) {
            case "Edge Detection" -> instateValues(new EdgeDetectionFilter());
            case "Edge Detection2" -> instateValues(new EdgeDetectionFilter2());
            case "Edge Detection3" -> instateValues(new EdgeDetectionFilter3());
            case "Sharpen" -> instateValues(new SharpenFilter());
        }

    }

    public void instateValues(Filter filter) {

        activeFilter = filter;

        Value1.setText(String.valueOf(filter.getArray()[0][0]));
        Value2.setText(String.valueOf(filter.getArray()[0][1]));
        Value3.setText(String.valueOf(filter.getArray()[0][2]));
        Value4.setText(String.valueOf(filter.getArray()[1][0]));
        Value5.setText(String.valueOf(filter.getArray()[1][1]));
        Value6.setText(String.valueOf(filter.getArray()[1][2]));
        Value7.setText(String.valueOf(filter.getArray()[2][0]));
        Value8.setText(String.valueOf(filter.getArray()[2][1]));
        Value9.setText(String.valueOf(filter.getArray()[2][2]));

    }


    public void onDownloadButton(MouseEvent mouseEvent) throws IOException {

        Image imageToBeSaved = AfterImageView.getImage();
        try{
        File file = new File("C:\\Users\\Vojta\\Documents\\Kódy\\demo\\src\\main\\java\\cz\\spsmb\\images\\img2.png");
        ImageIO.write(SwingFXUtils.fromFXImage(imageToBeSaved, null), "png", file);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}