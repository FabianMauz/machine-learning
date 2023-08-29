package de.fabianmauz.machinelearning.predict;

import de.fabianmauz.machinelearning.helper.Functions;
import de.fabianmauz.machinelearning.helper.Matrix;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author fmauz
 */
public class MultiLayerSigmoid implements Predict {

    @Override
    public SimpleMatrix predict(SimpleMatrix X, SimpleMatrix... w) {
        SimpleMatrix h = Functions.sigmoid(Matrix.addBias(X).mult(w[0]));
        return Functions.softMax(Matrix.addBias(h).mult(w[1]));
    }

}
