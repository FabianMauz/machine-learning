package de.fabianmauz.machinelearning.chapter.chapter_10;

import de.fabianmauz.machinelearning.classifier.ClassifyOneOfN;
import de.fabianmauz.machinelearning.data.TextImport;
import de.fabianmauz.machinelearning.gradient.LogisticGradient;
import de.fabianmauz.machinelearning.helper.Matrix;
import de.fabianmauz.machinelearning.loss.LogarithmicLoss;
import de.fabianmauz.machinelearning.predict.MultiLayerSigmoid;
import de.fabianmauz.machinelearning.predict.Sigmoid;
import java.io.FileReader;
import java.io.IOException;
import org.ejml.simple.SimpleMatrix;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author fmauz
 */
public class Chapter_10 {

    static protected String dataLocation_train_x = "src/main/resources/chapter_6/mnist_data_X_train.csv";
    static protected String dataLocation_train_y = "src/main/resources/chapter_6/mnist_data_Y_train.csv";
    static protected String dataLocation_test_x = "src/main/resources/chapter_6/mnist_data_X_test.csv";
    static protected String dataLocation_test_y = "src/main/resources/chapter_6/mnist_data_Y_test.csv";
    static protected String dataLocation_weights = "src/main/resources/chapter_10/weights.json";

    static protected int[] xValuesIndices = new int[]{0, 1, 2};
    static protected int[] yValuesIndices = new int[]{};
    static protected int skipLines = 0;
    static protected String valueSeparator = ";";
    static protected int iterations = 200;
    static protected float learnRate = 0.00001f;

    static int[] createXIndexArray() {
        int[] indices = new int[28 * 28];
        for (int i = 0; i < 28 * 28; i++) {
            indices[i] = i;
        }
        return indices;
    }

    public static void main(String[] args) throws IOException, Exception {
        JSONParser parser = new JSONParser();
        JSONArray obj = (JSONArray) parser.parse(new FileReader(dataLocation_weights));

        SimpleMatrix[] parameter = new SimpleMatrix[]{
            Matrix.extractWeightsFromJson((JSONArray) obj.get(0)),
            Matrix.extractWeightsFromJson((JSONArray) obj.get(1))
        };

        SimpleMatrix X_test = prepareData(dataLocation_test_x, 0, createXIndexArray(), new int[]{}, false);
        SimpleMatrix Y_test = prepareData(dataLocation_test_y, 1, new int[]{}, new int[]{0}, true);

        SimpleMatrix y_hat = new ClassifyOneOfN(new MultiLayerSigmoid()).classify(X_test, parameter);
        System.out.println("---------");

        System.out.println("Accurracy " + Math.round(10000 * calculateAccurary(Y_test, y_hat)) / 100d + " %");

    }

    public static SimpleMatrix train(SimpleMatrix X, SimpleMatrix Y, int maxIterations, float learnRate) {
        SimpleMatrix w = new SimpleMatrix(X.numCols(), Y.numCols());
        for (int i = 0; i < maxIterations; i++) {
            double loss = new LogarithmicLoss(X, Y, w, new Sigmoid()).loss();
            System.out.println("Iteration " + i + " --> Loss: " + loss);
            SimpleMatrix gradient = new LogisticGradient(X, Y, w).gradient();
            w = w.minus(gradient.scale(learnRate));
        }
        return w;
    }

    public static double calculate(SimpleMatrix X, SimpleMatrix w, SimpleMatrix Y) {
        SimpleMatrix Y_classified = new ClassifyOneOfN().classify(X, w);
        double sum = 0;
        for (int i = 0; i < Y.getNumElements(); i++) {
            sum += Math.abs(Y.get(i) - Y_classified.get(i));
        }
        return (1 - sum / Y.getNumElements());

    }

    public static SimpleMatrix prepareData(String location, int matrixIndex, int[] xIndex, int[] yIndex, boolean addBias) throws Exception {
        TextImport importer = new TextImport(location, xIndex, yIndex, skipLines, valueSeparator, addBias);
        return importer.importData()[matrixIndex];
    }

    public static double calculateAccurary(SimpleMatrix Y, SimpleMatrix Y_hat) {
        double correctHits = 0;
        for (int i = 0; i < Y.getNumElements(); i++) {
            if (Y.get(i) == Y_hat.get(i, 0)) {
                correctHits++;
            }
        }
        return correctHits / Y.getNumElements();
    }
}
