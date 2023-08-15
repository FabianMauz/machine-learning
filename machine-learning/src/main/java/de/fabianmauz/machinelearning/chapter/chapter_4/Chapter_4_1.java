package de.fabianmauz.machinelearning.chapter.chapter_4;

import de.fabianmauz.machinelearning.data.TextImport;
import java.util.Date;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Fabian
 */
public class Chapter_4_1 {

    static protected String dataLocation = "src/main/resources/life_expectancy.txt";
    static protected int[] xValuesIndices = new int[]{1, 2, 3};
    static protected int[] yValuesIndices = new int[]{4};
    static protected int skipLines = 1;
    static protected String valueSeparator = ";";

    public static void main(String[] args) throws Exception {
        Date startTraining = new Date();
        TextImport importer = new TextImport(dataLocation, xValuesIndices, yValuesIndices, skipLines, valueSeparator, true);
        SimpleMatrix[] matrices = importer.importData();
        SimpleMatrix parameter = train(matrices[0], matrices[1], 10000000, 0.0001f);
        Date afterTraining = new Date();
        System.out.println("Gesamt Fehler(abs) " + getTotalAccuracy(matrices, parameter));
        System.out.println("---------");
        System.out.println("trained parameters");
        System.out.println("Pollution -> " + parameter.get(0));
        System.out.println("Healthcare -> " + parameter.get(1));
        System.out.println("Water -> " + parameter.get(2));
        System.out.println("---------");
        System.out.println("Some tests");
        System.out.println("Training finished after " + (afterTraining.getTime() - startTraining.getTime()) / 1000 + " seconds");
        checkAccuracyOfCountry(0, "Argentinia", matrices, parameter);
        checkAccuracyOfCountry(72, "Italy", matrices, parameter);
        checkAccuracyOfCountry(145, "Switzerland", matrices, parameter);
        checkAccuracyOfCountry(131, "Seychelles", matrices, parameter);

    }

    public static double getTotalAccuracy(SimpleMatrix[] matrices, SimpleMatrix parameter) {
        SimpleMatrix y_roof = predict(matrices[0], parameter);
        SimpleMatrix diff = matrices[1].minus(y_roof);
        SimpleMatrix diffs = diff.elementDiv(matrices[1]);
        double absSum = 0;
        for (int i = 0; i < matrices[1].getNumElements(); i++) {
            absSum += Math.abs(diffs.get(i));
        }
        return absSum;
    }

    public static void checkAccuracyOfCountry(int indexOfCountry, String nameOfCountry, SimpleMatrix[] matrices, SimpleMatrix parameter) {
        double y_roof = predict(matrices[0].rows(indexOfCountry, indexOfCountry + 1), parameter).get(0);
        double y = matrices[1].get(indexOfCountry);
        double accuracy = (double) Math.round(10000 * (Math.abs(y_roof - y) / y)) / 100;

        System.out.println(nameOfCountry + " " + (double) (Math.round(y * 100)) / 100 + " (error " + accuracy + "%)");
    }

    public static SimpleMatrix train(SimpleMatrix X, SimpleMatrix Y, int maxIterations, float learnRate) {
        SimpleMatrix w = new SimpleMatrix(X.numCols(), 1);

        for (int i = 0; i < maxIterations; i++) {
            double currentLoss = loss(X, Y, w);
            //System.out.println("Iteration " + i + " --> " + currentLoss + " -- " + w.get(0) + " " + w.get(1) + " " + w.get(2));
            // System.out.println("Iteration " + i + " --> " + currentLoss);
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
