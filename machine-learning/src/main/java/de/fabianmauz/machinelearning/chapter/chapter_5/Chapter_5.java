package de.fabianmauz.machinelearning.chapter.chapter_5;

import de.fabianmauz.machinelearning.classifier.Classify;
import de.fabianmauz.machinelearning.data.TextImport;
import de.fabianmauz.machinelearning.gradient.LogisticGradient;
import de.fabianmauz.machinelearning.loss.LogarithmicLoss;
import de.fabianmauz.machinelearning.predict.Sigmoid;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Fabian
 */
public class Chapter_5 {

    static protected String dataLocation = "src/main/resources/chapter_4/police.txt";
    static protected int[] xValuesIndices = new int[]{0, 1, 2};
    static protected int[] yValuesIndices = new int[]{3};
    static protected int skipLines = 1;
    static protected String valueSeparator = " ";

    public static void main(String[] args) throws Exception {
        TextImport importer = new TextImport(dataLocation, xValuesIndices, yValuesIndices, skipLines, valueSeparator, true);
        SimpleMatrix[] matrices = importer.importData();

        SimpleMatrix parameter = train(matrices[0], matrices[1], 10000, 0.001f);

        System.out.println("---------");

        printAccuracy(matrices[0], parameter, matrices[1]);
    }

    public static SimpleMatrix train(SimpleMatrix X, SimpleMatrix Y, int maxIterations, float learnRate) {
        SimpleMatrix w = new SimpleMatrix(X.numCols(), Y.numRows());
        for (int i = 0; i < maxIterations; i++) {
            double loss = new LogarithmicLoss(X, Y, w, new Sigmoid()).loss();
            System.out.println("Iteration " + i + " --> Loss: " + loss);
            SimpleMatrix gradient = new LogisticGradient(X, Y, w).gradient();
            w = w.minus(gradient.scale(learnRate));
        }
        return w;
    }

    public static void printAccuracy(SimpleMatrix X, SimpleMatrix w, SimpleMatrix Y) {
        SimpleMatrix Y_classified = new Classify().classify(X, w);
        double sum = 0;
        for (int i = 0; i < Y.getNumElements(); i++) {
            sum += Math.abs(Y.get(i) - Y_classified.get(i));
        }
        System.out.println("Accuracy " + (1 - sum / Y.getNumElements()) * 100 + "% ");

    }
}
