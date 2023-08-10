package de.fabianmauz.machinelearning.helper;

import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author fmauz
 */
public abstract class Matrix {

    public static double average(SimpleMatrix M) {
        double sum = M.elementSum();
        return sum / M.getNumElements();
    }

    public static SimpleMatrix crossProduct(SimpleMatrix M1, SimpleMatrix M2) {
        double[][] result = new double[M1.numRows()][M1.numCols()];
        for (int i = 0; i < M1.numRows(); i++) {
            for (int j = 0; j < M1.numCols(); j++) {
                result[i][j] = M1.get(i, j) * M2.get(i, j);
            }
        }
        return new SimpleMatrix(result);
    }

    public static void apply(SimpleMatrix M, ElementManipulation formula) {
        for (int i = 0; i < M.getNumElements(); i++) {
            M.set(i, formula.apply(M.get(i)));
        }
    }
}
