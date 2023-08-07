package de.fabianmauz.machinelearning.loss;

import de.fabianmauz.machinelearning.predict.Predict;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author fmauz
 */
public class SquareSum implements Loss {

    private final SimpleMatrix X;
    private final SimpleMatrix Y;
    private final SimpleMatrix w;

    Predict predictor;

    public SquareSum(SimpleMatrix X, SimpleMatrix Y, SimpleMatrix w, Predict p) {
        this.X = X;
        this.Y = Y;
        this.w = w;
        this.predictor = p;
    }

    @Override
    public double loss() {
        SimpleMatrix Y_roof = predictor.predict(X, w);
        double sum = Y.minus(Y_roof).elementPower(2).elementSum();
        return sum / Y.numRows();
    }

}
