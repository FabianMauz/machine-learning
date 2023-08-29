package de.fabianmauz.machinelearning.helper;

import org.ejml.simple.SimpleMatrix;
import org.json.simple.JSONArray;

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

    public static SimpleMatrix addBias(SimpleMatrix M) {
        double[][] oneValues = new double[M.numRows()][1];
        for (int i = 0; i < M.numRows(); i++) {
            oneValues[i][0] = 1;
        }

        return new SimpleMatrix(oneValues).concatColumns(M);
    }

    public static SimpleMatrix expandMatrix(SimpleMatrix M, int size) {
        double[][] mExpanded = new double[M.numRows()][size];
        for (int i = 0; i < M.numRows(); i++) {
            mExpanded[i][(int) M.get(i)] = 1;
        }

        return new SimpleMatrix(mExpanded);
    }

    public static SimpleMatrix extractWeightsFromJson(JSONArray json) {
        int rows = json.size();
        int cols = ((JSONArray) json.get(0)).size();
        double[][] weights = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                weights[i][j] = (double) ((JSONArray) json.get(i)).get(j);
            }
        }
        return new SimpleMatrix(weights);
    }
}
