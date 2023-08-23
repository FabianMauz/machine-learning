package de.fabianmauz.machinelearning.helper;

import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author fmauz
 */
public abstract class Functions {

    public static SimpleMatrix sigmoid(SimpleMatrix z) {
        for (int i = 0; i < z.getNumElements(); i++) {
            double d = z.get(i);
            double newValue = 1d / (1 + Math.exp(-d));
            z.set(i, newValue);
        }
        return z;
    }

    public static SimpleMatrix softMax(SimpleMatrix z) {
        double[][] back = new double[z.numRows()][z.numCols()];

        for (int i = 0; i < z.numRows(); i++) {
            double expOfSum = 0;
            for (int j = 0; j < z.numCols(); j++) {
                expOfSum += Math.exp(z.get(i, j));
            }
            for (int j = 0; j < z.numCols(); j++) {
                back[i][j] = Math.exp(z.get(i, j)) / expOfSum;
            }
        }
        return new SimpleMatrix(back);
    }
}
