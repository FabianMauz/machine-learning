package de.fabianmauz.machinelearning.classifier;

import de.fabianmauz.machinelearning.predict.Sigmoid;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author fmauz
 */
public class Classify {
    public SimpleMatrix classify(SimpleMatrix X,SimpleMatrix w){
        SimpleMatrix values=new Sigmoid().predict(X, w);
        for(int i=0;i<values.getNumElements();i++){
            values.set(i,Math.round(values.get(i)));
        }
       return values;
    }
}
