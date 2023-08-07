package de.fabianmauz.machinelearning.predict;

import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author fmauz
 */
public class LinearMultiplication implements Predict {

    @Override
    public SimpleMatrix predict(SimpleMatrix X, SimpleMatrix w) {
        return X.mult(w);
    }

}
