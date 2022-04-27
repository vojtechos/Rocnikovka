package cz.spsmb;

import cz.spsmb.convolution.ConvolutionService;
import cz.spsmb.convolution.SimpleConvolutionService;
import cz.spsmb.model.Filter;
import cz.spsmb.model.Image;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
//import static org.junit.Assert.assertArrayEquals;

public class ConvolutionServiceTest {

    private ConvolutionService convolutionService = new SimpleConvolutionService();

    private Image getMockImage() {
        return new Image() {
            @Override
            public int[][][] getArray() {
                return new int[][][]{
                        {
                                {1, 0, 1},
                                {1, 0, 1},
                                {0, 0, 1}
                        }
                };
            }
        };
    }

    private Filter getMockFilter() {
        return new Filter() {
            @Override
            public int[][] getArray() {
                return new int[][]{
                        {1, 0, 1},
                        {1, 0, 1},
                        {0, 0, 1}
                };
            }
        };
    }

    @Test
    public void testConvolution() {
        int[][][] convolutionResult = convolutionService.convolution(getMockImage(), getMockFilter());

        assertArrayEquals(convolutionResult, new int[][][]{
                {
                        {0, 3, 0},
                        {0, 5, 0},
                        {0, 3, 0}
                }
        });
    }
}
