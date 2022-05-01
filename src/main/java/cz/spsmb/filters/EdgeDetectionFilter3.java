package cz.spsmb.filters;

import cz.spsmb.model.Filter;

public class EdgeDetectionFilter3 implements Filter {

    @Override
    public int[][] getArray() {
        return new int[][]{
                {-1,-1,-1},
                {-1,8,-1},
                {-1,-1,-1}
        };

    }
}
