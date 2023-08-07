package de.fabianmauz.machinelearning.predict;

import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author fmauz
 */
public interface Predict {

    public SimpleMatrix predict(SimpleMatrix X, SimpleMatrix w);
}
