package cz.spsmb.convolution;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class ConvertImage {

    public static int[][][] convertImageToIntArray(javafx.scene.image.Image img) {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(img, null);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        int[][][] RGB = new int[3][width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                int pixelIntValue = bufferedImage.getRGB(i, j);

                int r = (pixelIntValue >> 16 << 24 >>> 24);
                int g = (pixelIntValue >> 8 << 24 >>> 24);
                int b = (pixelIntValue << 24 >>> 24);

                RGB[0][i][j] = r;
                RGB[1][i][j] = g;
                RGB[2][i][j] = b;
            }
        }

        return RGB;
    }

    static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public static javafx.scene.image.Image convertArrayToImg(Image image, int[][][] img) {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

        for(int i = 0; i < img[0].length; i++) {
            for(int j = 0; j < img[0][i].length; j++) {
                int pixel = (255 << 8);
                pixel = (pixel | img[0][i][j]) << 8;
                pixel = (pixel | img[1][i][j]) << 8;
                pixel = pixel | img[2][i][j];
                bufferedImage.setRGB(i,j, pixel);
            }
        }

        return SwingFXUtils.toFXImage(bufferedImage, null);
    }
}
