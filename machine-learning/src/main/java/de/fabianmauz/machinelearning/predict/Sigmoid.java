package de.fabianmauz.machinelearning.predict;

import de.fabianmauz.machinelearning.helper.Functions;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author fmauz
 */
public class Sigmoid implements Predict {

    @Override
    public SimpleMatrix predict(SimpleMatrix X, SimpleMatrix w) {
        return Functions.sigmoid(X.mult(w));
    }

}
