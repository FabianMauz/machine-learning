package de.fabianmauz.machinelearning.chapter.chapter_2;

import de.fabianmauz.machinelearning.data.TextImport;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Fabian
 */
public class Chapter_2 {

    static protected String dataLocation = "src/main/resources/pizzas.txt";
    static protected int[] xValuesIndices = new int[]{0};
    static protected int[] yValuesIndices = new int[]{1};
    static protected int skipLines = 1;
    static protected String valueSeparator = " ";

    public static void main(String[] args) throws Exception {
        TextImport importer = new TextImport(dataLocation, xValuesIndices, yValuesIndices, skipLines, valueSeparator, false);
        SimpleMatrix[] matrices = importer.importData();

        train(matrices[0], matrices[1], 10000, 0.01f);
    }

    public static float[] train(SimpleMatrix X, SimpleMatrix Y, int maxIterations, float learnRate) {
        float w = 0;
        float b = 0;

        for (int i = 0; i < maxIterations; i++) {
            double currentLoss = loss(X, Y, w, b);
            System.out.println("Iteration " + i + " --> " + currentLoss);

            if (loss(X, Y, w + learnRate, b) < currentLoss) {
                w += learnRate;
            } else if (loss(X, Y, w - learnRate, b) < currentLoss) {
                w -= learnRate;
            } else if (loss(X, Y, w, b + learnRate) < currentLoss) {
                b += learnRate;
            } else if (loss(X, Y, w, b - learnRate) < currentLoss) {
                b -= learnRate;
            } else {
                return new float[]{w, b};
            }
        }
        return new float[]{w, b};
    }

    public static double loss(SimpleMatrix X, SimpleMatrix Y, float w, float b) {
        SimpleMatrix Y_roof = predict(X, w, b);
        double sum = Y.minus(Y_roof).elementPower(2).elementSum();
        return sum / Y.numRows();
    }

    public static SimpleMatrix predict(SimpleMatrix X, float w, float b) {
        return X.scale(w).plus(b);
    }
}
