package de.fabianmauz.machinelearning.chapter.chapter_4;

import de.fabianmauz.machinelearning.data.TextImport;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Fabian
 */
public class Chapter_4 {

    static protected String dataLocation = "src/main/resources/pizzas_3_vars.txt";
    static protected int[] xValuesIndices = new int[]{0, 1, 2};
    static protected int[] yValuesIndices = new int[]{3};
    static protected int skipLines = 1;
    static protected String valueSeparator = " ";

    public static void main(String[] args) throws Exception {
        TextImport importer = new TextImport(dataLocation, xValuesIndices, yValuesIndices, skipLines, valueSeparator, true);
        SimpleMatrix[] matrices = importer.importData();

        SimpleMatrix parameter = train(matrices[0], matrices[1], 100000, 0.001f);

        System.out.println("---------");
        System.out.println("trained parameters");
        System.out.println("w1 -> " + parameter.get(0));
        System.out.println("w2 -> " + parameter.get(1));
        System.out.println("w3 -> " + parameter.get(2));
    }

    public static SimpleMatrix train(SimpleMatrix X, SimpleMatrix Y, int maxIterations, float learnRate) {
        SimpleMatrix w = new SimpleMatrix(X.numCols(), 1);

        for (int i = 0; i < maxIterations; i++) {
            double currentLoss = loss(X, Y, w);
            //System.out.println("Iteration " + i + " --> " + currentLoss + " -- " + w.get(0) + " " + w.get(1) + " " + w.get(2));
            System.out.println("Iteration " + i + " --> " + currentLoss);
            w = w.minus(gradient(X, Y, w).scale(learnRate));
        }
        return w;
    }

    public static double loss(SimpleMatrix X, SimpleMatrix Y, SimpleMatrix w) {
        SimpleMatrix Y_roof = predict(X, w);
        double sum = Y.minus(Y_roof).elementPower(2).elementSum();
        return sum / Y.numRows();
    }

    public static SimpleMatrix gradient(SimpleMatrix X, SimpleMatrix Y, SimpleMatrix w) {
        SimpleMatrix Y_roof = predict(X, w);
        SimpleMatrix diff = Y_roof.minus(Y);
        SimpleMatrix wGradient = X.transpose().mult(diff);

        return wGradient.scale(2d / X.numRows());
    }

    public static SimpleMatrix predict(SimpleMatrix X, SimpleMatrix w) {
        return X.mult(w);
    }

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
}
