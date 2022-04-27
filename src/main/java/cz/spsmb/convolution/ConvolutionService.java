package cz.spsmb.convolution;


import cz.spsmb.model.Filter;
import cz.spsmb.model.Image;

public interface ConvolutionService {

    int[][][] convolution(Image image, Filter filter);

}
