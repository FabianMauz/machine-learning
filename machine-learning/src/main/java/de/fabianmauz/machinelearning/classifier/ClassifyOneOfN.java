package de.fabianmauz.machinelearning.classifier;

import de.fabianmauz.machinelearning.predict.Predict;
import de.fabianmauz.machinelearning.predict.Sigmoid;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author fmauz
 */
public class ClassifyOneOfN {

    Predict predictor = new Sigmoid();

    public ClassifyOneOfN() {

    }

    public ClassifyOneOfN(Predict predictor) {
        this.predictor = predictor;
    }

    public SimpleMatrix classify(SimpleMatrix X, SimpleMatrix... w) {

        SimpleMatrix values = predictor.predict(X, w);
        
        double[][] back = new double[values.numRows()][2];
        for (int i = 0; i < values.numRows(); i++) {

            for (int j = 0; j < values.numCols(); j++) {
                if (values.get(i, j) > back[i][1]) {
                    back[i][1] = values.get(i, j);
                    back[i][0] = j;
                }
            }
            values.set(i, Math.round(values.get(i)));
        }
        return new SimpleMatrix(back);
    }
}
