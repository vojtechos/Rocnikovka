package cz.spsmb.convolution;

import cz.spsmb.model.Filter;
import cz.spsmb.model.Image;
import java.util.Arrays;

public class SimpleConvolutionService implements ConvolutionService {

    @Override
    public int[][][] convolution(Image image, Filter filter) {
        int[][][] imageArray = image.getArray();
        int[][] filterArray = filter.getArray();

        int[][] convolutionR = doConvolution(imageArray[0], filterArray);
        int[][] convolutionG = doConvolution(imageArray[1], filterArray);
        int[][] convolutionB = doConvolution(imageArray[2], filterArray);

        return new int[][][] {
                convolutionR, convolutionG, convolutionB
        };
    }

    private int[][] doConvolution(int[][] image, int[][] filterArray) {

        int[][] output = new int[image.length][image[0].length];

        int xMiddleCoordinations = (filterArray.length-1)/2;
        int yMiddleCoordinations = (filterArray[0].length-1)/2;

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                for (int k = 0; k < filterArray.length; k++) {
                    for (int l = 0; l < filterArray[0].length; l++) {

                        if(k + i - xMiddleCoordinations < 0 || l + j - yMiddleCoordinations < 0 || k + i - xMiddleCoordinations > (image.length - 1) || l + j - yMiddleCoordinations > (image[0].length  - 1)){
                            continue;
                        }
                        else{
                            output[i][j] += image[i + k - xMiddleCoordinations][j + l - yMiddleCoordinations] * filterArray[k][l];
                        }
                    }
                }
            }
            System.out.println(Arrays.deepToString(output));
        }
        return output;
    }
}
