package de.fabianmauz.machinelearning;

import de.fabianmauz.machinelearning.data.TextImport;
import java.io.IOException;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author fmauz
 */
public class Chapter_6 {

    static protected String dataLocation = "src/main/resources/chapter_6/mnist_data_X_train.csv";
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
        TextImport importer = new TextImport(dataLocation, createXIndexArray(), yValuesIndices, skipLines, valueSeparator, true);

        SimpleMatrix X = importer.importData()[0];

    }
}
