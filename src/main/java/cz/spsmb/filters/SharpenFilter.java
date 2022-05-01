package cz.spsmb.filters;

import cz.spsmb.model.Filter;

public class SharpenFilter implements Filter {

    @Override
    public int[][] getArray() {
        return new int[][]{
                {0,-1,0},
                {-1,5,-1},
                {0,-1,0}
        };

    }
}
