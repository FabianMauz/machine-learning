package de.fabianmauz.machinelearning.loss;

import static de.fabianmauz.machinelearning.Chapter_4_1.predict;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author fmauz
 */
public class SquareSum implements Loss {

    private final SimpleMatrix X;
    private final SimpleMatrix Y;
    private final SimpleMatrix w;

    public SquareSum(SimpleMatrix X, SimpleMatrix Y, SimpleMatrix w) {
        this.X = X;
        this.Y = Y;
        this.w = w;
    }

    @Override
    public double loss() {
        SimpleMatrix Y_roof = predict(X, w);
        double sum = Y.minus(Y_roof).elementPower(2).elementSum();
        return sum / Y.numRows();
    }

}
