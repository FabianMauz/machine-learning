package de.fabianmauz.machinelearning.helper;

import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author fmauz
 */
public abstract class Functions {
    public static SimpleMatrix sigmoid(SimpleMatrix z){
        for(int i=0;i<z.getNumElements();i++){
           z.set(i, 1d /(1+Math.exp(-z.get(i))));
            
        }
        return z;
    }
}
