package de.fabianmauz.machinelearning;

import de.fabianmauz.machinelearning.data.TextImport;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Fabian
 */
public class Chapter_3 {

    static protected String dataLocation = "src/main/resources/pizzas.txt";
    static protected int[] xValuesIndices = new int[]{0};
    static protected int[] yValuesIndices = new int[]{1};
    static protected int skipLines = 1;
    static protected String valueSeparator = " ";

    public static void main(String[] args) throws Exception {
        TextImport importer = new TextImport(dataLocation, xValuesIndices, yValuesIndices, skipLines, valueSeparator);
        SimpleMatrix[] matrices = importer.importData();

        double[] parameter = train(matrices[0], matrices[1], 20000, 0.001f);

        System.out.println("---------");
        System.out.println("trained parameters");
        System.out.println("w -> " + parameter[0]);
        System.out.println("b -> " + parameter[1]);
    }

    public static double[] train(SimpleMatrix X, SimpleMatrix Y, int maxIterations, float learnRate) {
        double w = 0;
        double b = 0;

        for (int i = 0; i < maxIterations; i++) {
            double currentLoss = loss(X, Y, w, b);
            System.out.println("Iteration " + i + " --> " + currentLoss);
            double[] gradients = gradient(X, Y, w, b);
            w -= gradients[0] * learnRate;
            b -= gradients[1] * learnRate;
        }
        return new double[]{w, b};
    }

    public static double loss(SimpleMatrix X, SimpleMatrix Y, double w, double b) {
        SimpleMatrix Y_roof = predict(X, w, b);
        double sum = Y.minus(Y_roof).elementPower(2).elementSum();
        return sum / Y.numRows();
    }

    public static double[] gradient(SimpleMatrix X, SimpleMatrix Y, double w, double b) {
        SimpleMatrix Y_roof = predict(X, w, b);
        SimpleMatrix diff = Y_roof.minus(Y);
        double wGradient = 2 * average(crossProduct(diff, X));
        double bGradient = 2 * average(diff);

        return new double[]{wGradient, bGradient};
    }

    public static SimpleMatrix predict(SimpleMatrix X, double w, double b) {
        return X.scale(w).plus(b);
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
