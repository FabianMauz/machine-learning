package de.fabianmauz.machinelearning.gradient;

import de.fabianmauz.machinelearning.predict.LinearMultiplication;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author fmauz
 */
public class SquareSumGradient implements Gradient {

    private final SimpleMatrix X;
    private final SimpleMatrix Y;
    private final SimpleMatrix w;

    public SquareSumGradient(SimpleMatrix X, SimpleMatrix Y, SimpleMatrix w) {
        this.X = X;
        this.Y = Y;
        this.w = w;
    }

    @Override
    public SimpleMatrix gradient() {
        SimpleMatrix Y_roof = new LinearMultiplication().predict(X, w);
        SimpleMatrix diff = Y_roof.minus(Y);
        SimpleMatrix wGradient = X.transpose().mult(diff);

        return wGradient.scale(2d / X.numRows());
    }

}
