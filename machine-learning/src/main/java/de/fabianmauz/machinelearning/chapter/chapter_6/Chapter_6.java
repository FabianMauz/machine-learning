package de.fabianmauz.machinelearning.chapter.chapter_6;

import de.fabianmauz.machinelearning.classifier.Classify;
import de.fabianmauz.machinelearning.data.TextImport;
import de.fabianmauz.machinelearning.gradient.LogisticGradient;
import de.fabianmauz.machinelearning.helper.Matrix;
import de.fabianmauz.machinelearning.loss.LogarithmicLoss;
import de.fabianmauz.machinelearning.predict.Sigmoid;
import java.io.IOException;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author fmauz
 */
public class Chapter_6 {

    static protected String dataLocation_train_x = "src/main/resources/chapter_6/mnist_data_X_train.csv";
    static protected String dataLocation_train_y = "src/main/resources/chapter_6/mnist_data_Y_train.csv";

    static protected int[] xValuesIndices = new int[]{0, 1, 2};
    static protected int[] yValuesIndices = new int[]{};
    static protected int skipLines = 0;
    static protected String valueSeparator = ";";

    static int[] createXIndexArray() {
        int[] indices = new int[28 * 28];
        for (int i = 0; i < 28 * 28; i++) {
            indices[i] = i;
        }
        return indices;
    }

    public static void main(String[] args) throws IOException, Exception {
        SimpleMatrix X_train = prepareData(dataLocation_train_x, 0, createXIndexArray(), new int[]{}, true);
        double[] accuracy = new double[10];
        for (int digit_to_check = 0; digit_to_check < 10; digit_to_check++) {
            SimpleMatrix Y_train = prepareData(dataLocation_train_y, 1, new int[]{}, new int[]{0}, true);
            final int d = digit_to_check;
            Matrix.apply(Y_train, (x) -> {
                return x == d ? 1 : 0;
            });

            SimpleMatrix parameter = train(X_train, Y_train, 100, 0.00001f);

            System.out.println("---------");

            accuracy[digit_to_check]=calculate(X_train, parameter, Y_train);            
        }
        
        for(int i=0;i<accuracy.length;i++      ){
            System.out.println("Digit "+i+" -> "+(Math.round(accuracy[i]*10000d)/100d)+"%");
        }

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
        SimpleMatrix Y_classified = new Classify().classify(X, w);
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
}
