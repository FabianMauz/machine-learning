package de.fabianmauz.machinelearning.chapter.chapter_6;

import static de.fabianmauz.machinelearning.chapter.chapter_5.Chapter_5.printAccuracy;
import static de.fabianmauz.machinelearning.chapter.chapter_5.Chapter_5.train;
import de.fabianmauz.machinelearning.data.TextImport;
import de.fabianmauz.machinelearning.helper.Matrix;
import java.io.IOException;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author fmauz
 */
public class Chapter_6 {

    static protected String dataLocation_train_x = "src/main/resources/chapter_6/mnist_data_X_train.csv";
    static protected String dataLocation_train_y = "src/main/resources/chapter_6/mnist_data_Y_train.csv";
    static protected String dataLocation_test_x = "src/main/resources/chapter_6/mnist_data_X_test.csv";
    static protected String dataLocation_test_y = "src/main/resources/chapter_6/mnist_data_Y_test.csv";

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
        SimpleMatrix Y_train = prepareData(dataLocation_train_y, 1,new int[]{}, new int[]{0}, true);
       

        Matrix.apply(Y_train, (x) -> {
            return x == 5 ? 1 : 0;
        });
        int i = 0;
        SimpleMatrix parameter = train(X_train, Y_train, 100, 0.00001f);

        System.out.println("---------");

        printAccuracy(X_train, parameter, Y_train);

    }

    public static SimpleMatrix prepareData(String location, int matrixIndex, int[] xIndex, int[] yIndex, boolean addBias) throws Exception {
        TextImport importer = new TextImport(location, xIndex, yIndex, skipLines, valueSeparator, addBias);
        return importer.importData()[matrixIndex];
    }
}
