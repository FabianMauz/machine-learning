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

        train(matrices[0], matrices[1], 10000, 0.0001f);
    }

    public static float[] train(SimpleMatrix X, SimpleMatrix Y, int maxIterations, float learnRate) {
        float w = 0;
        float b = 0;

        for (int i = 0; i < maxIterations; i++) {
            double currentLoss = loss(X, Y, w, b);
            System.out.println("Iteration " + i + " --> " + currentLoss);
            w-=gradient(X, Y, w)*learnRate;
        }
        return new float[]{w, b};
    }

    public static double loss(SimpleMatrix X, SimpleMatrix Y, double w, float b) {
        SimpleMatrix Y_roof = predict(X, w, b);
        double sum = Y.minus(Y_roof).elementPower(2).elementSum();
        return sum / Y.numRows();
    }
    
    public static double gradient (SimpleMatrix X,SimpleMatrix Y,double w){
        SimpleMatrix Y_roof = predict(X, w, 0);
        double sum =0;
        SimpleMatrix diff = Y_roof.minus(Y);
        for(int i=0;i<diff.numRows();i++){
           sum+=diff.get(i)*X.get(i);
        }
        return 2 * sum/diff.numRows();
    }

    public static SimpleMatrix predict(SimpleMatrix X, double w, double b) {
        return X.scale(w).plus(b);
    }
    
    public double average(SimpleMatrix M){
        return M.elementSum()/M.numRows();
    }
}
