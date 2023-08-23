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
        double[][] back = new double[z.numRows()][z.numRows()];
        return new SimpleMatrix(back);
    }
}
