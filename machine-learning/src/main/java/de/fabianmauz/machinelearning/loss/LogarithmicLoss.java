package de.fabianmauz.machinelearning.loss;

import de.fabianmauz.machinelearning.helper.Matrix;
import static de.fabianmauz.machinelearning.helper.Matrix.average;
import static de.fabianmauz.machinelearning.helper.Matrix.crossProduct;
import de.fabianmauz.machinelearning.predict.Predict;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author fmauz
 */
public class LogarithmicLoss implements Loss {

    private final SimpleMatrix X;
    private final SimpleMatrix Y;
    private final SimpleMatrix w;
    Predict predictor;

    public LogarithmicLoss(SimpleMatrix X, SimpleMatrix Y, SimpleMatrix w, Predict p) {
        this.X = new SimpleMatrix(X);
        this.Y = new SimpleMatrix(Y);
        this.w = new SimpleMatrix(w);
        this.predictor = p;
    }

    @Override
    public double loss() {
        SimpleMatrix y_hat = predictor.predict(X, w);
        SimpleMatrix left_term = crossProduct(Y, y_hat.elementLog());
        SimpleMatrix right_term = crossProduct(oneMinusValue(Y), oneMinusValue(y_hat).elementLog());
        return -average(left_term.plus(right_term));
    }

    private SimpleMatrix oneMinusValue(SimpleMatrix M) {
        Matrix.apply(M, (x) -> {
            return 1 - x;
        });
        return M;
    }
}
