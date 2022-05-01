package cz.spsmb.convolution;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ConvertImage {

    /**
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    public static int[][][] convertImageToIntArray(Image img) {
        BufferedImage bufferedImage = toBufferedImage(img);

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        int[][][] RGB = new int[3][width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                int pixelIntValue = bufferedImage.getRGB(i, j);
            //    toBinaryString(pixelIntValue);

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

    public static Image convertIntArrayToImg(int[][][] img) {

        BufferedImage image = new BufferedImage(img[0].length, img[0][0].length, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < img[0].length; i++) {
            for (int j = 0; j < img[0][0].length; j++) {

                int r = img[0][i][j];
                int g = img[1][i][j];
                int b = img[2][i][j];

                int color = (r << 16) | (g << 8) | b;

                image.setRGB(i,j, color);

            }
        }

        return image;
    }
}
