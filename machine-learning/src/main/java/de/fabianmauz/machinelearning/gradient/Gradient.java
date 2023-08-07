package de.fabianmauz.machinelearning.gradient;

import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author fmauz
 */
public interface Gradient {

    public SimpleMatrix gradient();
}
