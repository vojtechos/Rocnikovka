package cz.spsmb.filters;

import cz.spsmb.model.Filter;

public class EdgeDetectionFilter implements Filter {

    @Override
    public int[][] getArray() {
        return new int[][]{
                {1,0,-1},
                {1,0,-1},
                {1,0,-1}
        };

    }
}
