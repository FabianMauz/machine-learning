package de.fabianmauz.machinelearning;

import de.fabianmauz.machinelearning.data.TextImport;
import de.fabianmauz.machinelearning.loss.SquareSum;
import de.fabianmauz.machinelearning.gradient.SquareSumGradient;
import de.fabianmauz.machinelearning.predict.LinearMultiplication;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Fabian
 */
public class Chapter_4_OO {

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
        System.out.println("Loss " + new SquareSum(matrices[0], matrices[1], parameter, new LinearMultiplication()).loss());
        System.out.println("trained parameters");
        System.out.println("w1 -> " + parameter.get(0));
        System.out.println("w2 -> " + parameter.get(1));
        System.out.println("w3 -> " + parameter.get(2));
    }

    public static SimpleMatrix train(SimpleMatrix X, SimpleMatrix Y, int maxIterations, float learnRate) {
        SimpleMatrix w = new SimpleMatrix(X.numCols(), 1);
        for (int i = 0; i < maxIterations; i++) {
            SimpleMatrix gradient = new SquareSumGradient(X, Y, w).gradient();
            w = w.minus(gradient.scale(learnRate));
        }
        return w;
    }
}
