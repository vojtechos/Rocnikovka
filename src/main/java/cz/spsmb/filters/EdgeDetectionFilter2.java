package cz.spsmb.filters;

import cz.spsmb.model.Filter;

public class EdgeDetectionFilter2 implements Filter {

    @Override
    public int[][] getArray() {
        return new int[][]{
                {0,1,0},
                {1,-4,1},
                {0,1,0}
        };

    }
}
