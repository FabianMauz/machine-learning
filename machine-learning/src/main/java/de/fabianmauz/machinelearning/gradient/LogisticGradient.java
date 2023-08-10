package de.fabianmauz.machinelearning.gradient;

import de.fabianmauz.machinelearning.predict.Sigmoid;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author fmauz
 */
public class LogisticGradient implements Gradient {

    private final SimpleMatrix X;
    private final SimpleMatrix Y;
    private final SimpleMatrix w;

    public LogisticGradient(SimpleMatrix X, SimpleMatrix Y, SimpleMatrix w) {
        this.X = X;
        this.Y = Y;
        this.w = w;
    }

    @Override
    public SimpleMatrix gradient() {
        SimpleMatrix Y_roof = new Sigmoid().predict(X, w);
        SimpleMatrix diff = Y_roof.minus(Y);
        SimpleMatrix wGradient = X.transpose().mult(diff);
        return wGradient.scale(1d / X.numRows());
    }

}
